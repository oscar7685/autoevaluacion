<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<option value="-1">Vacio</option>
<c:forEach items="${preguntasxCaracteristica}" var="pregunta">

    <c:choose>
        <c:when test="${fn:length(pregunta.preguntaList)!= 0}">
            <c:forEach items="${pregunta.preguntaList}" var="sub">
            <option value="${sub.id}">${pregunta.pregunta} ${sub.pregunta}</option>    
            </c:forEach>
        </c:when>
        <c:otherwise>
            <option value="${pregunta.id}">${pregunta.pregunta}</option>
        </c:otherwise>
    </c:choose>
</c:forEach>
<option value="-2">Todas</option>