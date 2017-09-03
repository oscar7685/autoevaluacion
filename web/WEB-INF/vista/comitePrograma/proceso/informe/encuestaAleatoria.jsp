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

<br>
<div class="container">
    <div style="margin-left: -30px;">
        <div id="conte" class="span12" style="text-align: justify">
            <div class="row">
                <table class="table table-striped table-bordered" style="font-weight: bold;">
                    <tbody>
                        <tr>
                            <td style="text-align: center;">ESCUELA NAVAL DE CADETES "ALMIRANTE PADILLA"</td>
                        </tr>
                    </tbody>
                </table>
                <br/>
            </div>
            <form id="formResponderE" method="POST">
                <c:set var="padre" value=""></c:set>
                <c:forEach items="${resultados}" var="resultado" varStatus="status">
                    <c:if test="${padre != '' && padre!= resultado.preguntaId.preguntaPadre.id}">
                        </tbody>
                        </table>
                    </c:if>
                    <c:choose>
                        <c:when test="${resultado.preguntaId.preguntaPadre == null}">
                            <c:choose>
                                <c:when test="${resultado.preguntaId.id == '11'}">
                                    <p style="font-weight: bold;"> Cómo califica la calidad general de los profesores de la Institución en cuanto a:</p>
                                </c:when>
                                <c:when test="${resultado.preguntaId.id == '19'}">
                                    <p style="font-weight: bold;"> Cómo califica en general la calidad de los programas académicos ofrecidos por ENAP en cuanto a:</p>
                                </c:when>    
                                <c:when test="${resultado.preguntaId.id == '31'}">
                                    <p style="font-weight: bold;"> Como califica el impacto de las acciones y proyectos de extensión o proyección social realizado por los profesores o estudiantes de la Institución:</p>
                                </c:when>
                                <c:when test="${resultado.preguntaId.id == '32'}">
                                    <p style="font-weight: bold;"> En qué grado el material bibliográfico con que cuenta la Institución es:</p>
                                </c:when>
                                <c:when test="${resultado.preguntaId.id == '35'}">
                                    <p style="font-weight: bold;"> Evalúe la calidad de los siguientes recursos de apoyo a la docencia e investigación con que cuenta la Institución para sus desarrollos académicos:</p>
                                </c:when>    
                                <c:when test="${resultado.preguntaId.id == '37'}">
                                    <p style="font-weight: bold;"> Evalúe en qué grado se dan las siguientes condiciones dentro de la Institución en cuanto a la investigación:</p>
                                </c:when>
                                <c:otherwise>
                                    <p style="font-weight: bold;"> ${resultado.preguntaId.getPregunta()}</p>
                                </c:otherwise>    
                            </c:choose>
                            <table class="table">
                                <c:choose>
                                    <c:when test="${resultado.preguntaId.getTipo()=='1'}">
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
                                    <c:when test="${resultado.preguntaId.getTipo()=='2'}">
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
                                    <c:when test="${resultado.preguntaId.getTipo()=='3'}">
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
                                    <tr>
                                        <td></td>
                                        <td>
                                            <label class="radio"><input type="radio" value="1" 
                                                                        <c:if test="${resultado.respuesta=='1'}">
                                                                            checked="checked"
                                                                        </c:if> /></label>
                                        </td>
                                        <td><label class="radio"><input type="radio" value="2" 
                                                                        <c:if test="${resultado.respuesta=='2'}">
                                                                            checked="checked"
                                                                        </c:if> /></label>
                                        </td>
                                        <td><label class="radio"><input type="radio" value="3" 
                                                                        <c:if test="${resultado.respuesta=='3'}">
                                                                            checked="checked"
                                                                        </c:if> /></label></td>
                                        <td><label class="radio"><input type="radio" value="4" 
                                                                        <c:if test="${resultado.respuesta=='4'}">
                                                                            checked="checked"
                                                                        </c:if> /></label>
                                        </td>
                                        <td><label class="radio"><input type="radio" value="5" 
                                                                        <c:if test="${resultado.respuesta=='5'}">
                                                                            checked="checked"
                                                                        </c:if> /></label>
                                        </td>
                                        <td><label class="radio"><input type="radio" value="0" 
                                                                        <c:if test="${resultado.respuesta=='0'}">
                                                                            checked="checked"
                                                                        </c:if> /></label>
                                        </td>
                                    </tr>
                                </tbody>      
                            </table>        
                        </c:when>
                        <c:otherwise>
                            <c:if test="${resultado.preguntaId.preguntaPadre.id != padre}">

                                <c:choose>
                                    <c:when test="${resultado.preguntaId.preguntaPadre.id == '11'}">
                                        <p style="font-weight: bold;"> Cómo califica la calidad general de los profesores de la Institución en cuanto a:</p>
                                    </c:when>
                                    <c:when test="${resultado.preguntaId.preguntaPadre.id == '19'}">
                                        <p style="font-weight: bold;"> Cómo califica en general la calidad de los programas académicos ofrecidos por ENAP en cuanto a:</p>
                                    </c:when>    
                                    <c:when test="${resultado.preguntaId.preguntaPadre.id == '31'}">
                                        <p style="font-weight: bold;"> Como califica el impacto de las acciones y proyectos de extensión o proyección social realizado por los profesores o estudiantes de la Institución:</p>
                                    </c:when>
                                    <c:when test="${resultado.preguntaId.preguntaPadre.id == '32'}">
                                        <p style="font-weight: bold;"> En qué grado el material bibliográfico con que cuenta la Institución es:</p>
                                    </c:when>
                                    <c:when test="${resultado.preguntaId.preguntaPadre.id == '35'}">
                                        <p style="font-weight: bold;"> Evalúe la calidad de los siguientes recursos de apoyo a la docencia e investigación con que cuenta la Institución para sus desarrollos académicos:</p>
                                    </c:when>    
                                    <c:when test="${resultado.preguntaId.preguntaPadre.id == '37'}">
                                        <p style="font-weight: bold;"> Evalúe en qué grado se dan las siguientes condiciones dentro de la Institución en cuanto a la investigación:</p>
                                    </c:when>
                                    <c:otherwise>
                                        <p style="font-weight: bold;"> ${resultado.preguntaId.preguntaPadre.getPregunta()}</p>
                                    </c:otherwise>    
                                </c:choose>
                                <table class="table">
                                    <c:choose>
                                        <c:when test="${resultado.preguntaId.preguntaPadre.getTipo()=='1'}">
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
                                        <c:when test="${resultado.preguntaId.preguntaPadre.getTipo()=='2'}">
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
                                        <c:when test="${resultado.preguntaId.preguntaPadre.getTipo()=='3'}">
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
                                    </c:if>
                                    <tr>
                                        <td>${resultado.preguntaId.pregunta}</td>
                                        <td><label class="radio"><input type="radio" value="1" 
                                                                        <c:if test="${resultado.respuesta=='1'}">
                                                                            checked="checked"
                                                                        </c:if>/></label></td>
                                        <td><label class="radio"><input type="radio" value="2" 
                                                                        <c:if test="${resultado.respuesta=='2'}">
                                                                            checked="checked"
                                                                        </c:if>/></label></td>
                                        <td><label class="radio"><input type="radio"  value="3" 
                                                                        <c:if test="${resultado.respuesta=='3'}">
                                                                            checked="checked"
                                                                        </c:if>/></label></td>
                                        <td><label class="radio"><input type="radio"  value="4" 
                                                                        <c:if test="${resultado.respuesta=='4'}">
                                                                            checked="checked"
                                                                        </c:if>/></label></td>
                                        <td><label class="radio"><input type="radio"  value="5" 
                                                                        <c:if test="${resultado.respuesta=='5'}">
                                                                            checked="checked"
                                                                        </c:if>/></label></td>
                                        <td><label class="radio"><input type="radio" value="0" 
                                                                        <c:if test="${resultado.respuesta=='0'}">
                                                                            checked="checked"
                                                                        </c:if>/></label></td>
                                    </tr>         
                                    <c:set var="padre" value="${resultado.preguntaId.preguntaPadre.id}"></c:set>
                                </c:otherwise>            
                            </c:choose>
                        </c:forEach>

                        </form>    
                        </div>
                        </div>


