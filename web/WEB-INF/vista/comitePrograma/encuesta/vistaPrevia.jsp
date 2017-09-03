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

<a class="span10" style="text-align: right;  margin-left: 60px; cursor: pointer;" id="printEnlace"><i class="icon-print"></i> Imprimir</a>  
<br>
<div class="hero-unit" id="toPrint">
    <div>
        <div id="conte" class="span10" style="text-align: justify">
            <div class="row">
                <table class="table table-striped table-bordered" style="font-weight: bold;">
                    <tbody>
                        <tr>
                            <td style="text-align: center;">ESCUELA NAVAL DE CADETES "ALMIRANTE PADILLA"
                                <!--<br/><span id="spanprograma">PROGRAMA: _PROGRAMA_</span>-->
                                <br/>${encuesta.getNombre()}</td>
                        </tr>
                    </tbody>
                </table>
                <br/>
                <p class="insp">${encuesta.getObjetivo()}</p>
                <p class="insp">${encuesta.getInstrucciones()}</p>
                <br/>
            </div>

            <c:forEach items="${encuesta.getPreguntaList()}" var="pregunta" varStatus="status">

                <div class="row" id="pregunta${pregunta.id}">
                    <div class="span10">
                        <p style="font-weight: bold;">${status.index+1} ${pregunta.getPregunta()}</p>


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
                            </c:choose>
                            <tbody>
                                <c:choose>
                                    <c:when test="${fn:length(pregunta.preguntaList)!= 0}">
                                        <c:forEach items="${pregunta.preguntaList}" var="sub">
                                            <tr>
                                                <td>${sub.getPregunta()}</td>
                                                <td><label class="radio"><input type="radio" name="pregunta${sub.id}" value="1" /></label></td>
                                                <td><label class="radio"><input type="radio" name="pregunta${sub.id}" value="2" /></label></td>
                                                <td><label class="radio"><input type="radio" name="pregunta${sub.id}" value="3" /></label></td>
                                                <td><label class="radio"><input type="radio" name="pregunta${sub.id}" value="4" /></label></td>
                                                <td><label class="radio"><input type="radio" name="pregunta${sub.id}" value="5" /></label></td>
                                                <td><label class="radio"><input type="radio" name="pregunta${sub.id}" value="0" /></label></td>
                                            </tr>
                                        </c:forEach>
                                    </c:when>
                                    <c:otherwise>
                                        <tr>
                                            <td></td>
                                            <td><label class="radio"><input type="radio" name="pregunta${pregunta.id}" value="1" /></label></td>
                                            <td><label class="radio"><input type="radio" name="pregunta${pregunta.id}" value="2" /></label></td>
                                            <td><label class="radio"><input type="radio" name="pregunta${pregunta.id}" value="3" /></label></td>
                                            <td><label class="radio"><input type="radio" name="pregunta${pregunta.id}" value="4" /></label></td>
                                            <td><label class="radio"><input type="radio" name="pregunta${pregunta.id}" value="5" /></label></td>
                                            <td><label class="radio"><input type="radio" name="pregunta${pregunta.id}" value="0" /></label></td>
                                        </tr>
                                    </c:otherwise>        
                                </c:choose>

                            </tbody>
                        </table>
                    </div> 
                </div>
            </c:forEach>  

        </div>
    </div>
</div>

<script type="text/javascript">
    $(function() {
        setTimeout(function() {
            $("#printEnlace").click(function() {
                var contents = $("#toPrint").html();
                var frame1 = $('<iframe />');
                frame1[0].name = "frame1";
                frame1.css({"position": "absolute", "top": "-1000000px"});
                $("body").append(frame1);
                var frameDoc = frame1[0].contentWindow ? frame1[0].contentWindow : frame1[0].contentDocument.document ? frame1[0].contentDocument.document : frame1[0].contentDocument;
                frameDoc.document.open();
                //Create a new HTML document.
                frameDoc.document.write('<html><head><title>${encuesta.getNombre()}</title>');
                frameDoc.document.write('</head><body>');
                //Append the external CSS file.
                frameDoc.document.write('<link href="css/print.css" rel="stylesheet" type="text/css" />');
                //Append the DIV contents.
                frameDoc.document.write(contents);
                frameDoc.document.write('</body></html>');
                frameDoc.document.close();
                setTimeout(function() {
                    window.frames["frame1"].focus();
                    window.frames["frame1"].print();
                    frame1.remove();
                }, 500);
            });
        }, 1000);
    });
</script>