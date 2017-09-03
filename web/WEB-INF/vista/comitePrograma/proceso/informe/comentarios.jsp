<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<c:choose>
    <c:when test="${fn:length(estudiantes)!= 0}">
        <table class="table table-bordered">
            <thead style="background-color: #ffffff;">
                <tr>
                    <th>Comentarios de estudiantes</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${estudiantes}" var="est" varStatus="status">
                    <tr>
                        <td>${est.comentarios}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>   
    </c:when>
</c:choose>
<c:choose>
    <c:when test="${fn:length(docentes)!= 0}">
        <table class="table table-bordered">
            <thead style="background-color: #ffffff;">
                <tr>
                    <th>Comentarios de docentes</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${docentes}" var="doc" varStatus="status">
                    <tr>
                        <td>${doc.comentarios}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>   
    </c:when>
    </c:choose>
<c:choose>
    <c:when test="${fn:length(administrativos)!= 0}">
        <table class="table table-bordered">
            <thead style="background-color: #ffffff;">
                <tr>
                    <th>Comentarios de administrativos</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${administrativos}" var="adm" varStatus="status">
                    <tr>
                        <td>${adm.comentarios}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>   
    </c:when>
    </c:choose>
<c:choose>
    <c:when test="${fn:length(egresados)!= 0}">
        <table class="table table-bordered">
            <thead style="background-color: #ffffff;">
                <tr>
                    <th>Comentarios de egresados</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${egresados}" var="egr" varStatus="status">
                    <tr>
                        <td>${egr.comentarios}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>   
    </c:when>
    </c:choose>
<c:choose>
    <c:when test="${fn:length(directores)!= 0}">
        <table class="table table-bordered">
            <thead style="background-color: #ffffff;">
                <tr>
                    <th>Comentarios de directivos</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${directores}" var="dir" varStatus="status">
                    <tr>
                        <td>${dir.comentarios}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>   
    </c:when>
    </c:choose>
<c:choose>
    <c:when test="${fn:length(empleadores)!= 0}">
        <table class="table table-bordered">
            <thead style="background-color: #ffffff;">
                <tr>
                    <th>Comentarios de empleadores</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${empleadores}" var="emp" varStatus="status">
                    <tr>
                        <td>${emp.comentarios}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>   
    </c:when>
</c:choose>

