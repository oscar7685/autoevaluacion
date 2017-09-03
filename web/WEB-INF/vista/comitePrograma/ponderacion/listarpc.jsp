<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<style type="text/css">
    .popover{
        min-width:550px;
        width: auto;
        text-align: center;
    }
    .popover-title {
        font-weight: bold;
    }
</style>
<script type="text/javascript">
    $(function() {
        $("#popover").popover({
            trigger: 'hover',
            placement: 'bottom',
            html: true,
            content: function() {
                return '<img src="<%=request.getContextPath()%>/img/escalaCaract.png" />';
            }
        });
    });
</script>
<div class="hero-unit">
    <div class="row">
        <div id="conte" class="span10">
            <h3>Ponderación de  Características <i id="popover" class="icon-question-sign" rel="popover" data-title="Escala nivel de importancia"></i></h3>
                <c:choose>
                    <c:when test="${fn:length(listPonderacionCara)!= 0}">
                    <table id="tablaX" class="table table-striped table-bordered table-condensed">
                        <thead>
                        <th>C&oacute;digo</th>    
                        <th>Característica</th>
                        <th>Ponderaci&oacute;n</th>
                        <th>Justificaci&oacute;n</th>
                        </thead>
                        <tbody>
                            <c:forEach items="${listPonderacionCara}" var="row" varStatus="iter">
                                <tr>
                                    <td>   
                                        <c:out value="${row.caracteristicaId.codigo}"/>
                                    </td>
                                    <td>   
                                        <c:out value="${row.caracteristicaId.nombre}"/>
                                    </td>
                                    <td>   
                                        <c:out value="${row.ponderacion}"/>
                                    </td>
                                    <td>   
                                        <c:out value="${row.justificacion}"/>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <c:if test="${EstadoProceso == 1}">
                        <a href="#preparedEditPonderarCara" class="btn btn-large btn-primary llamador"><i class="icon-edit-sign"></i> Editar Ponderación</a>
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
                                        title: 'Ponderacion caracteristicas'
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
                    No  se han ponderado las características en el sistema para este proceso.
                    <br><br>
                    <a href="#preparedPonderarCara" class="btn btn-large btn-primary llamador"><i class="icon-plus-sign"></i> Asignar Ponderación</a>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>    

