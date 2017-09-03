<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<option value="">Selecione un programa..</option>
<c:forEach items="${programasXlineamiento}" var="programaAux">
    <option value="${programaAux.id}">${programaAux.nombre}</option>
</c:forEach>
