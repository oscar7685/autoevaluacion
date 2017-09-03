<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="hero-unit">
    <div class="row">
        <div id="conte" class="span10">
            <h3>Listado de  Encuestas</h3>
            <c:choose>
                <c:when test="${fn:length(listaE2)!= 0}">

                    <table class="table table-striped table-bordered table-condensed">
                        <thead>
                        <th>Nombre</th>    
                        <th>Objetivo</th>
                        <th>Vista de impresi&oacute;n</th>
                        </thead>
                        <tbody>
                            <c:forEach items="${listaE2}" var="row" varStatus="iter">
                                <tr>
                                    <td>   
                                        <c:out value="${row.nombre}"/>
                                    </td>
                                    <td>   
                                        <c:out value="${row.objetivo}"/>
                                    </td>
                                    <td class="action span2" style="text-align:center;">
                                        <a href="#vistaPreviaPEncuesta&${row.id}" title="Vista previa"><i class="icon-eye-open"></i></a>
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
        </div>
    </div>
</div>    

