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
<c:set var="indice" value="0"></c:set>
<%--<c:set value="${[0,1,8,7,2,11,5,3,4,10,9,6]}" var="rol"></c:set> --%>
<c:set value="${fn:split('0,1,8,7,2,11,5,3,4,10,9,6', ',')}" var="rol"></c:set>    

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

                <c:forEach items="${caractesticas}" var="caracteristica" varStatus="status">
                    <c:forEach items="${caracteristica.preguntaList}" var="pregunta" varStatus="status1">
                        <table class="table table-bordered" id="tableR">
                            <thead style="background-color: #ffffff;">
                                <tr>
                                    <th class="span1">FACTOR</th>
                                    <th class="span1">CARACTERISTICA</th>
                                    <th class="span4">PREGUNTA</th>
                                        <c:forEach items="${encuestas.get(indice)}" var="encuest" varStatus="status44">
                                        <th>${encuest.nombre}</th>
                                        </c:forEach>
                                </tr>
                            </thead>
                            <tbody>

                                <c:choose>
                                    <c:when test="${fn:length(pregunta.preguntaList)!= 0}">
                                        <tr>
                                            <td style="vertical-align: middle;" rowspan="${fn:length(pregunta.preguntaList) + 1}">Factor ${caracteristica.factorId.codigo}. ${caracteristica.factorId.nombre}</td>
                                            <td style="vertical-align: middle;" rowspan="${fn:length(pregunta.preguntaList) + 1}">Caracteristica ${caracteristica.codigo}. ${caracteristica.nombre}</td>
                                            <td style="font-weight: bold;">${pregunta.pregunta}</td>
                                            <td colspan="${fn:length(encuestas.get(indice))}"></td>
                                        </tr>
                                        <c:forEach items="${pregunta.preguntaList}" var="sub" varStatus="status2">
                                            <tr>
                                                <td>${sub.pregunta}</td>
                                                <c:forEach items="${encuestas.get(indice)}" var="encuestaX" varStatus="status55">
                                                    <c:choose>
                                                        <c:when test="${resultados[sub.id][rol[encuestaX.id]] == -1}">
                                                            <td>NP</td>
                                                        </c:when>
                                                        <c:when test="${cerillos[sub.id][rol[encuestaX.id]] >= 20.0}">
                                                            <td><fmt:formatNumber type="number" maxFractionDigits="2" value="${resultados[sub.id][rol[encuestaX.id]]}"/></td>
                                                        </c:when>    
                                                        <c:when test="${resultados[sub.id][rol[encuestaX.id]] >= 70.0}">
                                                            <td class="success"><fmt:formatNumber type="number" maxFractionDigits="2" value="${resultados[sub.id][rol[encuestaX.id]]}"/></td>
                                                        </c:when>
                                                        <c:when test="${resultados[sub.id][rol[encuestaX.id]] >= 50.0 && resultados[sub.id][rol[encuestaX.id]] < 70.0}">
                                                            <td class="warning"><fmt:formatNumber type="number" maxFractionDigits="2" value="${resultados[sub.id][rol[encuestaX.id]]}"/></td>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <td class="error"><fmt:formatNumber type="number" maxFractionDigits="2" value="${resultados[sub.id][rol[encuestaX.id]]}"/></td>
                                                        </c:otherwise>    
                                                    </c:choose> 
                                                </c:forEach>
                                            </tr>
                                        </c:forEach>
                                    </c:when>
                                    <c:otherwise>
                                        <tr>
                                            <td>Factor ${caracteristica.factorId.codigo}. ${caracteristica.factorId.nombre}</td>
                                            <td>Caracteristica ${caracteristica.codigo}. ${caracteristica.nombre}</td>
                                            <td style="font-weight: bold;">${pregunta.pregunta}</td>
                                            <c:forEach items="${encuestas.get(indice)}" var="encuestaX2" varStatus="status66">
                                                <c:choose>
                                                    <c:when test="${resultados[pregunta.id][rol[encuestaX2.id]]==-1}">
                                                        <td>NP</td>
                                                    </c:when>
                                                    <c:when test="${cerillos[pregunta.id][rol[encuestaX2.id]] >= 20.0}">
                                                        <td><fmt:formatNumber type="number" maxFractionDigits="2" value="${resultados[pregunta.id][rol[encuestaX2.id]]}"/></td>
                                                    </c:when>
                                                    <c:when test="${resultados[pregunta.id][rol[encuestaX2.id]] >= 70.0}">
                                                        <td class="success"><fmt:formatNumber type="number" maxFractionDigits="2" value="${resultados[pregunta.id][rol[encuestaX2.id]]}"/></td>
                                                    </c:when>    
                                                    <c:when test="${resultados[pregunta.id][rol[encuestaX2.id]] >= 50.0 && resultados[pregunta.id][rol[encuestaX2.id]] < 70.0}">
                                                        <td class="warning"><fmt:formatNumber type="number" maxFractionDigits="2" value="${resultados[pregunta.id][rol[encuestaX2.id]]}"/></td>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <td class="error"><fmt:formatNumber type="number" maxFractionDigits="2" value="${resultados[pregunta.id][rol[encuestaX2.id]]}"/></td>
                                                    </c:otherwise>    
                                                </c:choose> 
                                            </c:forEach>
                                        </tr>
                                    </c:otherwise> 
                                </c:choose>   

                            </tbody>
                        </table> 
                        <c:set var="indice" value="${indice+1}"></c:set>
                    </c:forEach>
                </c:forEach>
            </div>
        </div>
    </div>
</div>       

