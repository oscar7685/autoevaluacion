<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<select name="programa" id="selectPrograma">
    <option value="--">Seleccione Programa</option>
    <option value="todos">Todos</option>
    <c:forEach items="${programasSelect}" var="programaX" varStatus="status">
        <option value="${programaX.id}">${programaX.nombre}</option>
    </c:forEach>
</select>