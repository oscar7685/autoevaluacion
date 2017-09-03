<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<script type="text/javascript">
    $(function() {
        $(".cambiable").each(function() {
            // Save current value of element
            $(this).data('oldVal', $(this).val());

            // Look for changes in the value
            $(this).change(function() {
                // If value has changed...
                if ($(this).data('oldVal') != $(this).val()) {
                    $(this).parents('tr').find('input[name^=InfoCambio]').attr("value", "1");
                }
            });
        });

        $("#actualizaR").click(function() {
            $(this).button('loading');
            $("#formCerrarP").submit();

        });

        $("#formCerrarP").submit(function(event) {
            event.preventDefault();
            $.ajax({
                type: 'POST',
                url: "<%=request.getContextPath()%>/controladorCP?action=cerrarP",
                data: $("#formCerrarP").serialize(),
                success: function() {
                    $('#myModalCP').modal();
                    $('#myModalCP').on('hidden', function() {
                        location = "/autoevaluacion/#inicio";
                    });
                } //fin success
            }); //fin $.ajax
        }); //fin submit
    });
</script>
<div class="hero-unit">
    <div class="row">
        <div id="conte" class="span10">
            <c:choose>
                <c:when test="${fn:length(acerrar)!= 0}">
                    <form id="formCerrarP" class="form-horizontal" method="post" action="">
                        <fieldset>
                            <legend>Preguntas para cerrar</legend>
                            <table  class="table table-striped table-bordered table-condensed">
                                <thead>
                                <th class="span6">Pregunta</th>    
                                <th class="span3">Respuesta</th>
                                <th class="span1">Calificaci&oacute;n</th>
                                </thead>
                                <tbody>
                                    <c:forEach items="${acerrar}" var="row" varStatus="iter">
                                        <tr>
                                    <input type="hidden" name="InfoCambio${row.id}" value="0">    
                                    <td>   
                                        <c:out value="${row.preguntaId.pregunta}"/>
                                    </td>
                                    <td>   
                                        <c:out value="${row.respuestaAbierta}"/>
                                    </td>
                                    <td>
                                        <select class="cambiable span1" name="resultado${row.id}">
                                            <option></option>
                                            <c:choose>
                                                <c:when test="${row.respuesta == 5}">
                                                    <option selected="selected" value="5">5</option>
                                                </c:when>
                                                <c:otherwise>
                                                    <option value="5">5</option>
                                                </c:otherwise>
                                            </c:choose>
                                            <c:choose>
                                                <c:when test="${row.respuesta == 4}">
                                                    <option selected="selected" value="4">4</option>
                                                </c:when>
                                                <c:otherwise>
                                                    <option value="4">4</option>
                                                </c:otherwise>
                                            </c:choose>
                                            <c:choose>
                                                <c:when test="${row.respuesta == 3}">
                                                    <option selected="selected" value="3">3</option>
                                                </c:when>
                                                <c:otherwise>
                                                    <option value="3">3</option>
                                                </c:otherwise>
                                            </c:choose>
                                            <c:choose>
                                                <c:when test="${row.respuesta == 2}">
                                                    <option selected="selected" value="2">2</option>
                                                </c:when>
                                                <c:otherwise>
                                                    <option value="2">2</option>
                                                </c:otherwise>
                                            </c:choose>
                                            <c:choose>
                                                <c:when test="${row.respuesta == 1}">
                                                    <option selected="selected" value="1">1</option>
                                                </c:when>
                                                <c:otherwise>
                                                    <option value="1">1</option>
                                                </c:otherwise>
                                            </c:choose>
                                            <c:choose>
                                                <c:when test="${row.respuesta == 0}">
                                                    <option selected="selected" value="0">0</option>
                                                </c:when>
                                                <c:otherwise>
                                                    <option value="0">0</option>
                                                </c:otherwise>
                                            </c:choose>

                                        </select>
                                    </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                            <div class="form-actions">
                                <button class="btn btn-primary" type="button" id="actualizaR" data-original-title="Guardar respuestas"  data-loading-text="Guardando..." autocomplete="off">Guardar respuestas</button>
                                <button class="btn" type="reset">Cancelar</button>
                            </div>
                        </fieldset>
                    </form>
                </c:when>
            </c:choose>
        </div>
    </div>
</div>
