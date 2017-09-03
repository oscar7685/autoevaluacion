<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="hero-unit">
    <div class="row">
        <div id="conte" class="span10">
            <h3>Evaluación de Características</h3>
                <c:choose>
                    <c:when test="${fn:length(listEvaluacionCara)!= 0}">
                    <table id="tablaX" class="table table-striped table-bordered table-condensed">
                        <thead>
                        <th>C&oacute;digo</th>    
                        <th>Característica</th>
                        <th>Evaluación</th>
                        </thead>
                        <tbody>
                            <c:forEach items="${listEvaluacionCara}" var="row" varStatus="iter">
                                <tr>
                                    <td>   
                                        <c:out value="${row.caracteristicaId.codigo}"/>
                                    </td>
                                    <td>   
                                        <c:out value="${row.caracteristicaId.nombre}"/>
                                    </td>
                                    <td>   
                                        <c:out value="${row.evaluacion}"/>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <c:if test="${EstadoProceso == 2}">
                        <a href="#editarEvaluacionCara" class="btn btn-large btn-primary llamador"><i class="icon-edit-sign"></i> Editar Evaluación</a>
                    </c:if>
                    <script type="text/javascript">
                        $(document).ready(function() {
                            $('#tablaX').DataTable({
                                dom: 'Bfrtip',
                                buttons: [
                                    {
                                        extend: 'copy',
                                        text: 'Copiar'
                                    },
                                    {
                                        extend: 'excelHtml5',
                                        text: 'Exportar a excel',
                                        title: 'Evaluacion caracteristicas'
                                    }
                                ],
                                language: {
                                    buttons: {
                                        copyTitle: 'Copiar'
                                    }
                                }
                            });
                        });
                    </script>
                </c:when>
                <c:otherwise>
                    No se han evaluado las características en el sistema para este proceso.
                    <br><br>
                    <a href="#" class="btn btn-large btn-primary llamador"><i class="icon-plus-sign"></i> Evaluar Caracteristicas</a>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>    

