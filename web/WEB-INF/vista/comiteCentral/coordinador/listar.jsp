<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="hero-unit">
    <div class="row">
        <div id="conte" class="span10">
            <h3 style="margin: 0;">Listado de  Coordinadores de programa</h3>
            <c:choose>
                <c:when test="${fn:length(listaCoo)!= 0}">
                    <table id="tablaX" class="table table-striped table-bordered table-condensed">
                        <thead>
                        <th class="span1">C&oacute;digo</th>    
                        <th class="span4">Nombre</th>
                        <th class="span4">Programa</th>
                        <th class="span1">Acci&oacute;n</th>
                        </thead>
                        <tbody>
                            <c:forEach items="${listaCoo}" var="row" varStatus="iter">
                                <tr>
                                    <td>   
                                        <c:out value="${row.id}"/>
                                    </td>
                                    <td>   
                                        <c:out value="${row.nombre}"/>
                                    </td>
                                    <td>   
                                        <c:forEach items="${row.programaList}" var="programa" varStatus="iterP">
                                            <c:choose>
                                                <c:when test="${(iterP.index + 1) != row.programaList.size()}">
                                                    <c:out value="${programa.nombre}, "/>
                                                </c:when>
                                                <c:otherwise>
                                                    <c:out value="${programa.nombre}"/>
                                                </c:otherwise>
                                            </c:choose>

                                        </c:forEach>

                                    </td>
                                    <td class="action span2">
                                        <a href="#editarCoordinador&${row.id}" title="Editar"><i class="icon-edit"></i></a>
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
                                        title: 'Coordinadores'
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
                    No existen Coordinadores de programa registrados en el sistema.
                </c:otherwise>
            </c:choose>
            <br/>
            <a href="#crearCoordinador" class="btn btn-large btn-primary llamador"><i class="icon-plus"></i> Crear coordinador</a>
        </div>
    </div>
</div>    

