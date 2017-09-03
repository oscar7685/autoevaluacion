<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="hero-unit">
    <div class="row">
        <div id="conte" class="span10">
            <h3 style="margin: 0;">Listado de  Factores</h3>
            <c:choose>
                <c:when test="${fn:length(listaF)!= 0}">
                    <table id="tablaX" class="table table-striped table-bordered table-condensed">
                        <thead>
                        <th class="span1">C&oacute;digo</th>    
                        <th class="span8">Factor</th>
                        <th class="span1">Acci&oacute;n</th>
                        </thead>
                        <tbody>
                            <c:forEach items="${listaF}" var="row" varStatus="iter">
                                <tr>
                                    <td>   
                                        <c:out value="${row.codigo}"/>
                                    </td>
                                    <td>   
                                        <c:out value="${row.nombre}"/>
                                    </td>
                                    <td class="action span2">
                                        <a href="#editarFactor&${row.id}" title="Editar"><i class="icon-edit"></i></a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
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
                                        title: 'Factores'
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
                    No existen factores registrados en el sistema para este modelo.
                </c:otherwise>
            </c:choose>
            <br/>
            <a href="#crearFactor" class="btn btn-large btn-primary llamador"><i class="icon-plus"></i> Crear factor</a>

        </div>
    </div>
</div>    
