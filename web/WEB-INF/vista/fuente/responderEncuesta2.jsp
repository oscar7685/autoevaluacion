<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link media="print" href="<%=request.getContextPath()%>/css/print.css" rel="stylesheet"/>
<style type="text/css">
    @media all {
        div.saltopagina{
            display: none;
        }
        .insp{
            line-height: 22px;
            text-align: justify;
        }

        .span5{
            width: 360px;
            font-size: 14px;
            font-family: "Helvetica Neue",​Helvetica,​Arial,​sans-serif;
        }
    } 


</style>
<script type="text/javascript">
    $(function () {
        var validator = $("#formResponderE").bind("invalid-form.validate", function () {
            alert("usted ha dejado de contestar " + validator.numberOfInvalids() + " preguntas, por favor contestelas todas.");
        })
                .validate({
                    ignore: "",
                    submitHandler: function () {
                        $("button").attr("disabled", true);
                        $.ajax({
                            type: 'POST',
                            url: "<%=request.getContextPath()%>/controladorF?action=responderE",
                            data: $("#formResponderE").serialize(),
                            beforeSend: function () {
                                $("div.ui-layout-center").append(""
                                        + "<div id='dancing-dots-text'>"
                                        + "Enviando <span><span>.</span><span>.</span><span>.</span><span>.</span><span>.</span></span> "
                                        + "</div>");
                            },
                            success: function () {
                                $("#dancing-dots-text").remove();
                                $("#myModalGracias").modal();
                                $('#myModalGracias').on('hidden', function () {
                                    location = "<%=request.getContextPath()%>/#CerrarSesion";
                                });
                            } //fin success
                        }); //fin $.ajax
                    }
                });
        $("button").popover({trigger: "hover", placement: 'right'});
    });
</script>

<br>
<div class="container">
    <div style="margin-left: -30px;">
        <div id="conte" class="span12" style="text-align: justify">
            <div class="row">
                <table class="table table-bordered table-striped" style="font-weight: bold;">
                    <tbody>
                        <tr>
                            <td rowspan="2" style="width: 25%; text-align: center;"><img src="/autoevaluacion/img/LogoUTB.png"></td>
                            <td style="width: 50%; text-align: center;">UNIVERSIDAD TECNOLÓGICA DE BOLÍVAR</td>
                            <td rowspan="2" style="width: 25%; text-align: center;"><img src="/autoevaluacion/img/CalidadUTB.jpg"></td>
                        </tr>
                        <tr>
                            <td style="width: 50%; text-align: center;">${encuesta.getNombre()}</td>
                        </tr>
                    </tbody>
                </table>
                <br/>
            </div>
            <form id="formResponderE" method="POST">
                <c:forEach items="${preguntas}" var="pregunta" varStatus="status">
                    <div class="row" id="pregunta${pregunta.id}">
                        <div class="span12">
                            <p style="font-weight: bold;">${status.index+1} ${pregunta.getPregunta()}</p>
                            <c:choose>
                                <c:when test="${pregunta.repetir == null}" >
                                    <table class="table">
                                        <c:choose>
                                            <c:when test="${pregunta.getTipo()=='1'}">
                                                <thead>
                                                    <tr>
                                                        <th class="span3"></th>
                                                        <th class="span2" style="font-size: 12px">1:Muy bajo</th>
                                                        <th class="span2" style="font-size: 12px">2:Bajo</th>
                                                        <th class="span2" style="font-size: 12px">3:Medio</th>
                                                        <th class="span2" style="font-size: 12px">4:Alto</th>
                                                        <th class="span2" style="font-size: 12px">5:Muy alto</th>
                                                        <th class="span2" style="font-size: 12px">NS/NR</th>
                                                    </tr>
                                                </thead>
                                            </c:when>
                                            <c:when test="${pregunta.getTipo()=='2'}">
                                                <thead>
                                                    <tr>
                                                        <th class="span3"></th>
                                                        <th class="span2" style="font-size: 12px">1:En ningún grado</th>
                                                        <th class="span2" style="font-size: 12px">2:En bajo grado</th>
                                                        <th class="span2" style="font-size: 12px">3:Aceptablemente</th>
                                                        <th class="span2" style="font-size: 12px">4:En alto grado</th>
                                                        <th class="span2" style="font-size: 12px">5:Totalmente</th>
                                                        <th class="span2" style="font-size: 12px">NS/NR</th>
                                                    </tr>
                                                </thead>
                                            </c:when>
                                            <c:when test="${pregunta.getTipo()=='3'}">
                                                <thead>
                                                    <tr>
                                                        <th class="span3"></th>
                                                        <th class="span2" style="font-size: 12px">1:Muy mala</th>
                                                        <th class="span2" style="font-size: 12px">2:Mala</th>
                                                        <th class="span2" style="font-size: 12px">3:Regular</th>
                                                        <th class="span2" style="font-size: 12px">4:Buena</th>
                                                        <th class="span2" style="font-size: 12px">5:Excelente</th>
                                                        <th class="span2" style="font-size: 12px">NS/NR</th>
                                                    </tr>
                                                </thead>
                                            </c:when>
                                            <c:when test="${pregunta.getTipo()=='4'}">
                                                <thead>
                                                    <tr>
                                                        <th class="span3"></th>
                                                        <th class="span4" style="font-size: 12px">1:No</th>
                                                        <th class="span4" style="font-size: 12px">2:Si</th>
                                                    </tr>
                                                </thead>
                                            </c:when>    
                                        </c:choose>
                                        <tbody>
                                            <c:choose>
                                                <c:when test="${fn:length(pregunta.preguntaList)!= 0}">
                                                    <c:forEach items="${pregunta.preguntaList}" var="sub">
                                                        <tr>
                                                            <td>${sub.getPregunta()}</td>
                                                            <c:choose>
                                                                <c:when test="${pregunta.getTipo()!='4'}">
                                                                    <td><label class="radio"><input type="radio" class="{required:true}" name="pregunta${sub.id}" value="1" /></label></td>
                                                                    <td><label class="radio"><input type="radio" class="{required:true}" name="pregunta${sub.id}" value="2" /></label></td>
                                                                    <td><label class="radio"><input type="radio" class="{required:true}" name="pregunta${sub.id}" value="3" /></label></td>
                                                                    <td><label class="radio"><input type="radio" class="{required:true}" name="pregunta${sub.id}" value="4" /></label></td>
                                                                    <td><label class="radio"><input type="radio" class="{required:true}" name="pregunta${sub.id}" value="5" /></label></td>
                                                                    <td><label class="radio"><input type="radio" class="{required:true}" name="pregunta${sub.id}" value="0" /></label></td>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <td><label class="radio"><input type="radio" class="{required:true}" name="pregunta${sub.id}" value="1" /></label></td>
                                                                    <td><label class="radio"><input type="radio" class="{required:true}" name="pregunta${sub.id}" value="5" /></label></td>    
                                                                </c:otherwise>        
                                                            </c:choose>
                                                        </tr>
                                                    </c:forEach>
                                                </c:when>
                                                <c:otherwise>
                                                    <tr>
                                                        <td></td>
                                                        <td><label class="radio"><input type="radio" class="{required:true}" name="pregunta${pregunta.id}" value="1" /></label></td>
                                                        <td><label class="radio"><input type="radio" class="{required:true}" name="pregunta${pregunta.id}" value="2" /></label></td>
                                                        <td><label class="radio"><input type="radio" class="{required:true}" name="pregunta${pregunta.id}" value="3" /></label></td>
                                                        <td><label class="radio"><input type="radio" class="{required:true}" name="pregunta${pregunta.id}" value="4" /></label></td>
                                                        <td><label class="radio"><input type="radio" class="{required:true}" name="pregunta${pregunta.id}" value="5" /></label></td>
                                                        <td><label class="radio"><input type="radio" class="{required:true}" name="pregunta${pregunta.id}" value="0" /></label></td>
                                                    </tr>
                                                </c:otherwise>        
                                            </c:choose>

                                        </tbody>
                                    </table>
                                </c:when>
                                <c:when test="${pregunta.repetir == 'si'}" >  <!--TIENE al menos un programa asociados (distintos de institucional)-->  
                                    <c:choose>
                                        <c:when test="${fn:length(preguntasQueSeRepiten.get(pregunta.id))== 0}"><!--no hay programas asociados (distintos de institucional)-->
                                            <table class="table">
                                                <c:choose>
                                                    <c:when test="${pregunta.getTipo()=='1'}">
                                                        <thead>
                                                            <tr>
                                                                <th class="span3"></th>
                                                                <th class="span2" style="font-size: 12px">1:Muy bajo</th>
                                                                <th class="span2" style="font-size: 12px">2:Bajo</th>
                                                                <th class="span2" style="font-size: 12px">3:Medio</th>
                                                                <th class="span2" style="font-size: 12px">4:Alto</th>
                                                                <th class="span2" style="font-size: 12px">5:Muy alto</th>
                                                                <th class="span2" style="font-size: 12px">NS/NR</th>
                                                            </tr>
                                                        </thead>
                                                    </c:when>
                                                    <c:when test="${pregunta.getTipo()=='2'}">
                                                        <thead>
                                                            <tr>
                                                                <th class="span3"></th>
                                                                <th class="span2" style="font-size: 12px">1:En ningún grado</th>
                                                                <th class="span2" style="font-size: 12px">2:En bajo grado</th>
                                                                <th class="span2" style="font-size: 12px">3:Aceptablemente</th>
                                                                <th class="span2" style="font-size: 12px">4:En alto grado</th>
                                                                <th class="span2" style="font-size: 12px">5:Totalmente</th>
                                                                <th class="span2" style="font-size: 12px">NS/NR</th>
                                                            </tr>
                                                        </thead>
                                                    </c:when>
                                                    <c:when test="${pregunta.getTipo()=='3'}">
                                                        <thead>
                                                            <tr>
                                                                <th class="span3"></th>
                                                                <th class="span2" style="font-size: 12px">1:Muy mala</th>
                                                                <th class="span2" style="font-size: 12px">2:Mala</th>
                                                                <th class="span2" style="font-size: 12px">3:Regular</th>
                                                                <th class="span2" style="font-size: 12px">4:Buena</th>
                                                                <th class="span2" style="font-size: 12px">5:Excelente</th>
                                                                <th class="span2" style="font-size: 12px">NS/NR</th>
                                                            </tr>
                                                        </thead>
                                                    </c:when>
                                                    <c:when test="${pregunta.getTipo()=='4'}">
                                                        <thead>
                                                            <tr>
                                                                <th class="span3"></th>
                                                                <th class="span4" style="font-size: 12px">1:No</th>
                                                                <th class="span4" style="font-size: 12px">2:Si</th>
                                                            </tr>
                                                        </thead>
                                                    </c:when>     
                                                </c:choose>
                                                <tbody>
                                                    <c:choose>
                                                        <c:when test="${fn:length(pregunta.preguntaList)!= 0}">
                                                            <c:forEach items="${pregunta.preguntaList}" var="sub">
                                                                <tr>
                                                                    <td>${sub.getPregunta()}</td>
                                                            <c:choose>
                                                                <c:when test="${pregunta.getTipo()!='4'}">
                                                                    <td><label class="radio"><input type="radio" class="{required:true}" name="pregunta${sub.id}" value="1" /></label></td>
                                                                    <td><label class="radio"><input type="radio" class="{required:true}" name="pregunta${sub.id}" value="2" /></label></td>
                                                                    <td><label class="radio"><input type="radio" class="{required:true}" name="pregunta${sub.id}" value="3" /></label></td>
                                                                    <td><label class="radio"><input type="radio" class="{required:true}" name="pregunta${sub.id}" value="4" /></label></td>
                                                                    <td><label class="radio"><input type="radio" class="{required:true}" name="pregunta${sub.id}" value="5" /></label></td>
                                                                    <td><label class="radio"><input type="radio" class="{required:true}" name="pregunta${sub.id}" value="0" /></label></td>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <td><label class="radio"><input type="radio" class="{required:true}" name="pregunta${sub.id}" value="1" /></label></td>
                                                                    <td><label class="radio"><input type="radio" class="{required:true}" name="pregunta${sub.id}" value="5" /></label></td>    
                                                                </c:otherwise>        
                                                            </c:choose>
                                                                </tr>
                                                            </c:forEach>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <tr>
                                                                <td></td>
                                                                <td><label class="radio"><input type="radio" class="{required:true}" name="pregunta${pregunta.id}" value="1" /></label></td>
                                                                <td><label class="radio"><input type="radio" class="{required:true}" name="pregunta${pregunta.id}" value="2" /></label></td>
                                                                <td><label class="radio"><input type="radio" class="{required:true}" name="pregunta${pregunta.id}" value="3" /></label></td>
                                                                <td><label class="radio"><input type="radio" class="{required:true}" name="pregunta${pregunta.id}" value="4" /></label></td>
                                                                <td><label class="radio"><input type="radio" class="{required:true}" name="pregunta${pregunta.id}" value="5" /></label></td>
                                                                <td><label class="radio"><input type="radio" class="{required:true}" name="pregunta${pregunta.id}" value="0" /></label></td>
                                                            </tr>
                                                        </c:otherwise>        
                                                    </c:choose>
                                                </tbody>
                                            </table>
                                        </c:when>
                                        <c:otherwise><!--TIENE al menos un programa asociados (distintos de institucional)-->
                                            <table class="table">
                                                <thead>
                                                <tr>
                                                <th class="span2"></th>
                                                <c:forEach items="${preguntasQueSeRepiten.get(pregunta.id)}" var="programa" varStatus="programaIndex">
                                                    <th class="span2">${programa.nombre}</th>
                                                </c:forEach>
                                                </tr>
                                                </thead>
                                                <tbody>
                                                        <c:choose>
                                                            <c:when test="${fn:length(pregunta.preguntaList)!= 0}"> <!--tiene subpreguntas-->
                                                                <c:forEach items="${pregunta.preguntaList}" var="sub">
                                                                    <tr>
                                                                        <td class="span2">${sub.getPregunta()}</td>
                                                                         <c:forEach items="${preguntasQueSeRepiten.get(pregunta.id)}" var="programa2" varStatus="programaIndex2">
                                                                             <td class="span2">
                                                                               <c:choose>
                                                                                   <c:when test="${pregunta.getTipo()=='1'}">
                                                                                       <select name="pregunta${sub.id}programa${programa2.id}" class="{required:true} span2">
                                                                                           <option></option>
                                                                                           <option value="1">1:Muy bajo</option>
                                                                                           <option value="2">2:Bajo</option>
                                                                                           <option value="3">3:Medio</option>
                                                                                           <option value="4">4:Alto</option>
                                                                                           <option value="5">5:Muy Alto</option>
                                                                                           <option value="0">NS/NR</option>
                                                                                       </select>    
                                                                                   </c:when>
                                                                                   <c:when test="${pregunta.getTipo()=='2'}">
                                                                                       <select name="pregunta${sub.id}programa${programa2.id}" class="{required:true} span2">
                                                                                           <option></option>
                                                                                           <option value="1">1:En ningún grado</option>
                                                                                           <option value="2">2:En bajo grado</option>
                                                                                           <option value="3">3:Aceptablemente</option>
                                                                                           <option value="4">4:En alto grado</option>
                                                                                           <option value="5">5:Totalmente</option>
                                                                                           <option value="0">NS/NR</option>
                                                                                       </select>    
                                                                                   </c:when>
                                                                                   <c:when test="${pregunta.getTipo()=='3'}">
                                                                                       <select name="pregunta${sub.id}programa${programa2.id}" class="{required:true} span2">
                                                                                           <option></option>
                                                                                           <option value="1">1:Muy mala</option>
                                                                                           <option value="2">2:Mala</option>
                                                                                           <option value="3">3:Regular</option>
                                                                                           <option value="4">4:Buena</option>
                                                                                           <option value="5">5:Excelente</option>
                                                                                           <option value="0">NS/NR</option>
                                                                                       </select>    
                                                                                   </c:when>
                                                                                   <c:when test="${pregunta.getTipo()=='4'}">
                                                                                       <select name="pregunta${sub.id}programa${programa2.id}" class="{required:true} span2">
                                                                                           <option></option>
                                                                                           <option value="1">1:No</option>
                                                                                           <option value="5">2:Si</option>
                                                                                       </select>    
                                                                                   </c:when>
                                                                               </c:choose>    
                                                                             </td>
                                                                         </c:forEach>
                                                                    </tr>
                                                                </c:forEach>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <tr>
                                                                        <td></td>
                                                                         <c:forEach items="${preguntasQueSeRepiten.get(pregunta.id)}" var="programa2" varStatus="programaIndex2">
                                                                             <td class="span2">
                                                                               <c:choose>
                                                                                   <c:when test="${pregunta.getTipo()=='1'}">
                                                                                       <select name="pregunta${pregunta.id}programa${programa2.id}" class="{required:true} span2">
                                                                                           <option ></option>
                                                                                           <option value="1">1:Muy bajo</option>
                                                                                           <option value="2">2:Bajo</option>
                                                                                           <option value="3">3:Medio</option>
                                                                                           <option value="4">4:Alto</option>
                                                                                           <option value="5">5:Muy Alto</option>
                                                                                           <option value="0">NS/NR</option>
                                                                                       </select>    
                                                                                   </c:when>
                                                                                   <c:when test="${pregunta.getTipo()=='2'}">
                                                                                       <select name="pregunta${pregunta.id}programa${programa2.id}" class="{required:true} span2">
                                                                                           <option ></option>
                                                                                           <option value="1">1:En ningún grado</option>
                                                                                           <option value="2">2:En bajo grado</option>
                                                                                           <option value="3">3:Aceptablemente</option>
                                                                                           <option value="4">4:En alto grado</option>
                                                                                           <option value="5">5:Totalmente</option>
                                                                                           <option value="0">NS/NR</option>
                                                                                       </select>    
                                                                                   </c:when>
                                                                                   <c:when test="${pregunta.getTipo()=='3'}">
                                                                                       <select name="pregunta${pregunta.id}programa${programa2.id}" class="{required:true} span2">
                                                                                           <option ></option>
                                                                                           <option value="1">1:Muy mala</option>
                                                                                           <option value="2">2:Mala</option>
                                                                                           <option value="3">3:Regular</option>
                                                                                           <option value="4">4:Buena</option>
                                                                                           <option value="5">5:Excelente</option>
                                                                                           <option value="0">NS/NR</option>
                                                                                       </select>    
                                                                                   </c:when>
                                                                                   <c:when test="${pregunta.getTipo()=='4'}">
                                                                                       <select name="pregunta${pregunta.id}programa${programa2.id}" class="{required:true} span2">
                                                                                           <option ></option>
                                                                                           <option value="1">1:No</option>
                                                                                           <option value="5">2:Si</option>
                                                                                       </select>    
                                                                                   </c:when>
                                                                               </c:choose>    
                                                                             </td>
                                                                         </c:forEach>
                                                                    </tr>
                                                            </c:otherwise>        
                                                        </c:choose>
                                                    </tbody>
                                            </table>
                                          </c:otherwise>
                                    </c:choose>
                                </c:when>   
                            </c:choose>
                        </div> 
                    </div>
                </c:forEach>  
                <div class="row"> 
                    <div class="span2">
                        <div style="text-align: left; margin-top: 22px;">
                            <button class="btn btn-primary" data-content="Env&iacute;a la encuesta evaluada. Verifique que todas las preguntas han sido respondidas correctamente. Esta operación no se podrá deshacer."  value="1" data-original-title="Enviar encuesta" type="submit">Enviar</button>
                        </div>    
                    </div>
                </div>
            </form>   
        </div>
    </div>


