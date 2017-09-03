<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<option value="-1">Vacio</option>
<c:forEach items="${caracteristicasxFactor}" var="caracteristica">
    <option value="${caracteristica.id}">${caracteristica.nombre}</option>
</c:forEach>
<option value="-2">Todas</option>

