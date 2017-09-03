<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<style type="text/css">
    .table td{
        font-size: 8px;
    }
    .table th {
        font-size: 8px;
    }
    .table .success{
        background-color: #DFF0D8; 
        color: #468847;
    }
    .table .error{
        background-color: #F2DEDE; 
        color: #B94A48;
    }
    .table .warning{
        background-color: #f0ad4e;
        color: #fff;
    }
</style>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div class="hero-unit">
    <div class="row">
        <div id="conte" class="span10">
            <fieldset>
                <legend>
                    Estado del proceso
                </legend>
                <br>
            </fieldset>
            <div>
                <table class="table table-bordered" id="tableR">
                    <thead style="background-color: #ffffff;">
                        <tr>
                            <th class="span1">FACTOR</th>
                            <th class="span1">CARACTERISTICA</th>
                            <th class="span4">PREGUNTA</th>
                            <th class="span1">Estudiantes</th>
                            <th class="span1">Profesores Planta</th>
                            <th class="span1">Profesores Cátedra</th>
                            <th class="span1">Directivos</th>
                            <th class="span1">Administrativos</th>
                            <th class="span1">Egresados</th>
                            <th class="span1">Empleadores</th>
                        </tr>
                    </thead>
                    <c:choose>
                        <c:when test="${tipoF == 'Universitaria'}">
                            <c:set var="itms" value="1,2,11,5,3,4,6"></c:set>
                        </c:when>
                        <c:when test="${tipoF == 'Maestria'}">
                            <c:set var="itms" value="8,2,11,5,3,10,6"></c:set>
                        </c:when>
                        <c:otherwise>
                            <c:set var="itms" value="7,2,11,5,3,9,6"></c:set>
                        </c:otherwise>
                    </c:choose>
                    <tbody>
                        <c:forEach items="${caractesticas}" var="caracteristica" varStatus="status">
                            <c:forEach items="${caracteristica.preguntaList}" var="pregunta" varStatus="status1">
                                <c:choose>
                                    <c:when test="${fn:length(pregunta.preguntaList)!= 0}">
                                        <tr>
                                            <td style="vertical-align: middle;" rowspan="${fn:length(pregunta.preguntaList) + 1}">Factor ${caracteristica.factorId.codigo}. ${caracteristica.factorId.nombre}</td>
                                            <td style="vertical-align: middle;" rowspan="${fn:length(pregunta.preguntaList) + 1}">Caracteristica ${caracteristica.codigo}. ${caracteristica.nombre}</td>
                                            <td style="font-weight: bold;">${pregunta.pregunta}</td>
                                            <td colspan="11"></td>
                                        </tr>
                                        <c:forEach items="${pregunta.preguntaList}" var="sub" varStatus="status2">
                                            <tr>
                                                <td>${sub.pregunta}</td>
                                                <c:forTokens items="${itms}" delims="," var="rol">
                                                    <c:choose>
                                                        <c:when test="${resultados[sub.id][rol] == -1}">
                                                            <td>NA</td>
                                                        </c:when>
                                                        <c:when test="${cerillos[sub.id][rol] >= 20.0}">
                                                            <td><fmt:formatNumber type="number" maxFractionDigits="2" value="${resultados[sub.id][rol]}"/></td>
                                                        </c:when>    
                                                        <c:when test="${resultados[sub.id][rol] >= 70.0}">
                                                            <td class="success"><fmt:formatNumber type="number" maxFractionDigits="2" value="${resultados[sub.id][rol]}"/></td>
                                                        </c:when>
                                                        <c:when test="${resultados[sub.id][rol] >= 50.0 && resultados[sub.id][rol] < 70.0}">
                                                            <td class="warning"><fmt:formatNumber type="number" maxFractionDigits="2" value="${resultados[sub.id][rol]}"/></td>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <td class="error"><fmt:formatNumber type="number" maxFractionDigits="2" value="${resultados[sub.id][rol]}"/></td>
                                                        </c:otherwise>    
                                                    </c:choose> 
                                                </c:forTokens>
                                            </tr>
                                        </c:forEach> 
                                    </c:when>
                                    <c:when test="${fn:length(pregunta.preguntaList)== 0 && pregunta.id == '131'}">
                                        <tr>
                                            <td style="vertical-align: middle;" rowspan="7">Factor ${caracteristica.factorId.codigo}. ${caracteristica.factorId.nombre}</td>
                                            <td style="vertical-align: middle;" rowspan="7">Caracteristica ${caracteristica.codigo}. ${caracteristica.nombre}</td>
                                            <td style="font-weight: bold;">${pregunta.preguntaPadre.pregunta}</td>
                                            <td colspan="11"></td>
                                        </tr>
                                        <tr>
                                            <td>${pregunta.pregunta}</td>
                                            <c:forTokens items="${itms}" delims="," var="rol">
                                                <c:choose>
                                                    <c:when test="${resultados[pregunta.id][rol]==-1}">
                                                        <td>NA</td>
                                                    </c:when>
                                                    <c:when test="${cerillos[pregunta.id][rol] >= 20.0}">
                                                        <td><fmt:formatNumber type="number" maxFractionDigits="2" value="${resultados[pregunta.id][rol]}"/></td>
                                                    </c:when>
                                                    <c:when test="${resultados[pregunta.id][rol] >= 70.0}">
                                                        <td class="success"><fmt:formatNumber type="number" maxFractionDigits="2" value="${resultados[pregunta.id][rol]}"/></td>
                                                    </c:when>    
                                                    <c:when test="${resultados[pregunta.id][rol] >= 50.0 && resultados[pregunta.id][rol] < 70.0}">
                                                        <td class="warning"><fmt:formatNumber type="number" maxFractionDigits="2" value="${resultados[pregunta.id][rol]}"/></td>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <td class="error"><fmt:formatNumber type="number" maxFractionDigits="2" value="${resultados[pregunta.id][rol]}"/></td>
                                                    </c:otherwise>    
                                                </c:choose> 
                                            </c:forTokens>
                                        </tr>
                                    </c:when>        
                                    <c:when test="${fn:length(pregunta.preguntaList)== 0 && pregunta.id == '135'}">
                                        <tr>
                                            <td style="vertical-align: middle;" rowspan="4">Factor ${caracteristica.factorId.codigo}. ${caracteristica.factorId.nombre}</td>
                                            <td style="vertical-align: middle;" rowspan="4">Caracteristica ${caracteristica.codigo}. ${caracteristica.nombre}</td>
                                            <td style="font-weight: bold;">${pregunta.preguntaPadre.pregunta}</td>
                                            <td colspan="11"></td>
                                        </tr>
                                        <tr>
                                            <td>${pregunta.pregunta}</td>
                                            <c:forTokens items="${itms}" delims="," var="rol">
                                                <c:choose>
                                                    <c:when test="${resultados[pregunta.id][rol]==-1}">
                                                        <td>NA</td>
                                                    </c:when>
                                                    <c:when test="${cerillos[pregunta.id][rol] >= 20.0}">
                                                        <td><fmt:formatNumber type="number" maxFractionDigits="2" value="${resultados[pregunta.id][rol]}"/></td>
                                                    </c:when>
                                                    <c:when test="${resultados[pregunta.id][rol] >= 70.0}">
                                                        <td class="success"><fmt:formatNumber type="number" maxFractionDigits="2" value="${resultados[pregunta.id][rol]}"/></td>
                                                    </c:when>    
                                                    <c:when test="${resultados[pregunta.id][rol] >= 50.0 && resultados[pregunta.id][rol] < 70.0}">
                                                        <td class="warning"><fmt:formatNumber type="number" maxFractionDigits="2" value="${resultados[pregunta.id][rol]}"/></td>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <td class="error"><fmt:formatNumber type="number" maxFractionDigits="2" value="${resultados[pregunta.id][rol]}"/></td>
                                                    </c:otherwise>    
                                                </c:choose> 
                                            </c:forTokens>
                                        </tr>
                                    </c:when>    
                                    <c:when test="${fn:length(pregunta.preguntaList)== 0 && pregunta.id == '137'}">
                                        <tr>
                                            <td style="vertical-align: middle;" rowspan="2">Factor ${caracteristica.factorId.codigo}. ${caracteristica.factorId.nombre}</td>
                                            <td style="vertical-align: middle;" rowspan="2">Caracteristica ${caracteristica.codigo}. ${caracteristica.nombre}</td>
                                            <td style="font-weight: bold;">${pregunta.preguntaPadre.pregunta}</td>
                                            <td colspan="11"></td>
                                        </tr>
                                        <tr>
                                            <td>${pregunta.pregunta}</td>
                                            <c:forTokens items="${itms}" delims="," var="rol">
                                                <c:choose>
                                                    <c:when test="${resultados[pregunta.id][rol]==-1}">
                                                        <td>NA</td>
                                                    </c:when>
                                                    <c:when test="${cerillos[pregunta.id][rol] >= 20.0}">
                                                        <td><fmt:formatNumber type="number" maxFractionDigits="2" value="${resultados[pregunta.id][rol]}"/></td>
                                                    </c:when>
                                                    <c:when test="${resultados[pregunta.id][rol] >= 70.0}">
                                                        <td class="success"><fmt:formatNumber type="number" maxFractionDigits="2" value="${resultados[pregunta.id][rol]}"/></td>
                                                    </c:when>    
                                                    <c:when test="${resultados[pregunta.id][rol] >= 50.0 && resultados[pregunta.id][rol] < 70.0}">
                                                        <td class="warning"><fmt:formatNumber type="number" maxFractionDigits="2" value="${resultados[pregunta.id][rol]}"/></td>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <td class="error"><fmt:formatNumber type="number" maxFractionDigits="2" value="${resultados[pregunta.id][rol]}"/></td>
                                                    </c:otherwise>    
                                                </c:choose> 
                                            </c:forTokens>
                                        </tr>
                                    </c:when>    
                                    <c:when test="${fn:length(pregunta.preguntaList)== 0 && pregunta.id > 130 && pregunta.id < 141}">
                                        <tr>
                                            <td>${pregunta.pregunta}</td>
                                            <c:forTokens items="${itms}" delims="," var="rol">
                                                <c:choose>
                                                    <c:when test="${resultados[pregunta.id][rol]==-1}">
                                                        <td>NA</td>
                                                    </c:when>
                                                    <c:when test="${cerillos[pregunta.id][rol] >= 20.0}">
                                                        <td><fmt:formatNumber type="number" maxFractionDigits="2" value="${resultados[pregunta.id][rol]}"/></td>
                                                    </c:when>
                                                    <c:when test="${resultados[pregunta.id][rol] >= 70.0}">
                                                        <td class="success"><fmt:formatNumber type="number" maxFractionDigits="2" value="${resultados[pregunta.id][rol]}"/></td>
                                                    </c:when>    
                                                    <c:when test="${resultados[pregunta.id][rol] >= 50.0 && resultados[pregunta.id][rol] < 70.0}">
                                                        <td class="warning"><fmt:formatNumber type="number" maxFractionDigits="2" value="${resultados[pregunta.id][rol]}"/></td>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <td class="error"><fmt:formatNumber type="number" maxFractionDigits="2" value="${resultados[pregunta.id][rol]}"/></td>
                                                    </c:otherwise>    
                                                </c:choose> 
                                            </c:forTokens>
                                        </tr>
                                    </c:when>    
                                    <c:otherwise>
                                        <tr>
                                            <td>Factor ${caracteristica.factorId.codigo}. ${caracteristica.factorId.nombre}</td>
                                            <td>Caracteristica ${caracteristica.codigo}. ${caracteristica.nombre}</td>
                                            <td style="font-weight: bold;">${pregunta.pregunta}</td>
                                            <c:forTokens items="${itms}" delims="," var="rol">
                                                <c:choose>
                                                    <c:when test="${resultados[pregunta.id][rol]==-1}">
                                                        <td>NA</td>
                                                    </c:when>
                                                    <c:when test="${cerillos[pregunta.id][rol] >= 20.0}">
                                                        <td><fmt:formatNumber type="number" maxFractionDigits="2" value="${resultados[pregunta.id][rol]}"/></td>
                                                    </c:when>
                                                    <c:when test="${resultados[pregunta.id][rol] >= 70.0}">
                                                        <td class="success"><fmt:formatNumber type="number" maxFractionDigits="2" value="${resultados[pregunta.id][rol]}"/></td>
                                                    </c:when>    
                                                    <c:when test="${resultados[pregunta.id][rol] >= 50.0 && resultados[pregunta.id][rol] < 70.0}">
                                                        <td class="warning"><fmt:formatNumber type="number" maxFractionDigits="2" value="${resultados[pregunta.id][rol]}"/></td>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <td class="error"><fmt:formatNumber type="number" maxFractionDigits="2" value="${resultados[pregunta.id][rol]}"/></td>
                                                    </c:otherwise>    
                                                </c:choose> 
                                            </c:forTokens>
                                        </tr>
                                    </c:otherwise> 
                                </c:choose>   
                            </c:forEach>
                        </c:forEach>
                    </tbody>
                </table>   
            </div>
        </div>
    </div>
</div>




