<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="hero-unit">
    <div class="row">
        <div id="conte" class="span10">
            <h3>Detalle de Proceso de Autoevaluación</h3>
            <c:choose>
                <c:when test="${fn:length(listProceso)!= 0}">
                    <table class="table table-striped table-bordered table-condensed">
                        <thead>
                        <th>Programa</th>
                        <th>Descripción</th>    
                        <th>Fecha de Inicio</th>
                        <th>Fecha de Cierre</th>
                        <th>Modelo</th>
                        </thead>
                        <tbody>
                            <c:forEach items="${listProceso}" var="item" varStatus="iter">
                                <tr>
                                    <td>   
                                        <c:out value="${item.programaId.nombre}"/>
                                    </td>
                                    <td>   
                                        <c:out value="${item.descripcion}"/>
                                    </td>
                                    <td>   
                                        <c:out value="${item.fechainicio}"/>
                                    </td>
                                    <td>   
                                        <c:out value="${item.fechacierre}"/>
                                    </td>
                                    <td>   
                                        <c:out value="${item.modeloId.nombre}"/>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:when>
                <c:otherwise>
                    No Existen Modelos Registrados en el Sistema.
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>    

