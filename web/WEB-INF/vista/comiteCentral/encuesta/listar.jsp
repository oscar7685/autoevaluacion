<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<link media="print" href="<%=request.getContextPath()%>/css/print.css" rel="stylesheet"/>
<div class="hero-unit">
    <div class="row">
        <div id="conte" class="span10">
            <h3>Listado de  Encuestas</h3>
            <c:choose>
                <c:when test="${fn:length(listaE)!= 0}">

                    <table class="table table-striped table-bordered table-condensed">
                        <thead>
                        <th>Nombre</th>    
                        <th>Objetivo</th>
                        <th>Acci&oacute;n</th>
                        </thead>
                        <tbody>
                            <c:forEach items="${listaE}" var="row" varStatus="iter">
                                <tr>
                                    <td>   
                                        <c:out value="${row.nombre}"/>
                                    </td>
                                    <td>   
                                        <c:out value="${row.objetivo}"/>
                                    </td>
                                    <td class="action span2">
                                        <a href="#editarEncuesta&${row.id}" title="Editar"><i class="icon-edit"></i></a>
                                        <a href="#vistaPreviaEncuesta&${row.id}" title="Vista previa"><i class="icon-eye-open"></i></a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:when>
                <c:otherwise>
                    No existen encuestas registradas en el sistema para este modelo.
                </c:otherwise>
            </c:choose>
            <br/>        
            <a href="#crearEncuesta" class="btn btn-large btn-primary llamador"><i class="icon-plus"></i> Crear encuesta</a>

        </div>
    </div>
</div>    

