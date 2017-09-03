<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<style type="text/css">
    .popover-content{
        font-size: 14px;
        font-weight: normal;
        line-height: 40px;
        font-family: "Helvetica Neue",Helvetica,Arial,sans-serif;
        line-height: 1.42857;
    }
</style>
<div class="hero-unit">
    <div class="row">
        <div id="conte" class="span10">
            <h3>Ponderación de  Indicadores</h3>
            <c:choose>
                <c:when test="${fn:length(listPonderacionIndicadores)!= 0}">
                    <table id="tablaX" class="table table-striped table-bordered table-condensed">
                        <thead>
                        <th>C&oacute;digo</th>    
                        <th>Indicador</th>
                        <th>Ponderaci&oacute;n</th>
                        </thead>
                        <tbody>
                            <c:forEach items="${listPonderacionIndicadores}" var="row" varStatus="iter">
                                <tr>
                                    <td>   
                                        <c:out value="${row.indicadorId.codigo}"/>
                                    </td>
                                    <td>   
                                        <c:out value="${row.indicadorId.nombre}"/>
                                    </td>
                                    <td>   
                                        <c:out value="${row.ponderacion}"/>
                                    </td> 
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <c:if test="${EstadoProceso == 1}">
                        <a href="#preparedEditPonderarIndicador" class="btn btn-large btn-primary llamador"><i class="icon-edit-sign"></i> Editar Ponderación</a>
                    </c:if>
                    <script type="text/javascript" src="<%=request.getContextPath()%>/js/dataTable.js"></script>
                </c:when>
                <c:otherwise>
                    No  se han ponderado los indicadores en el sistema para este proceso.
                    <br><br>
                    <a href="#preparedPonderarIndicador" class="btn btn-large btn-primary llamador"><i class="icon-plus-sign"></i> Asignar Ponderación</a>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>    
