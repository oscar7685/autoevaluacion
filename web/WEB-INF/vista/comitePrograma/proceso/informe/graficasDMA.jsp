<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<script type="text/javascript" language="JavaScript">
    $(document).ready(function() {
        $("#factor").change(function() {
            var factor = $("#factor").val();
            if (factor > 0) {
                $.ajax({
                    type: 'POST',
                    url: "/autoevaluacion/controladorCP?action=listarCaracteristicas&factorId=" + factor,
                    success: function(data) {
                        $("#caracteristica").empty();
                        $("#caracteristica").html(data);
                        setTimeout(function() {
                            $("#dancing-dots-text").remove();
                        }, 200);
                    }
                });
            } else if (factor === "-2") {
                $("#caracteristica").html("<option value='-1'>Vacio</option>");
                $("#pregunta").html("<option value='-1'>Vacio</option>");
            } else {
                $("#caracteristica").html("<option value='-1'>Vacio</option><option value='-2'>Todos</option>");
                $("#pregunta").html("<option value='-1'>Vacio</option><option value='-2'>Todos</option>");
            }


        });

        $("#caracteristica").change(function() {
            var caracteristica = $("#caracteristica").val();
            if (caracteristica > 0) {
                $.ajax({
                    type: 'POST',
                    url: "/autoevaluacion/controladorCP?action=listarPreguntasxCaracteristica&caracteristicaId=" + caracteristica,
                    success: function(data) {
                        $("#pregunta").empty();
                        $("#pregunta").html(data);
                        setTimeout(function() {
                            $("#dancing-dots-text").remove();
                        }, 200);
                    }
                });
            } else if (caracteristica === "-2") {
                $("#pregunta").html("<option value='-1'>Vacio</option>");
            } else {
                $("#pregunta").html("<option value='-1'>Vacio</option><option value='-2'>Todos</option>");
            }
        });


        $("#mostrarResultados").click(function() {
            var factor = $("#factor").val();
            var caracteristica = $("#caracteristica").val();
            var pregunta = $("#pregunta").val();
            var grupo = $("#grupo").val();
            var todos = 0;

            if (factor === "-1" && caracteristica === "-1" && pregunta === "-1" && grupo === "-1") {
                alert("Todos los campos no pueden ser vacio!!");
            } else {
                if (factor === "-2") {
                    todos++;
                }
                if (caracteristica === "-2") {
                    todos++;
                }
                if (pregunta === "-2") {
                    todos++;
                }
                if (grupo === "-2") {
                    todos++;
                }

                if (todos > 1) {
                    alert("No puede haber mas de un campo seleccionado con la opcion todos!!");
                } else {
                    $.ajax({
                        type: 'POST',
                        url: "/autoevaluacion/controladorCP?action=graficarDMA",
                        data: $("#resultadosDinamicos").serialize(),
                        success: function(data) {
                            $("#barras").empty();
                            $("#barras").html(data);
                            setTimeout(function() {
                                $("#dancing-dots-text").remove();
                            }, 200);
                        }
                    });
                }
            }
        });
    });
</script>     

<div class="hero-unit">
    <div class="row">
        <div id="conte" class="span10">
            <form id="resultadosDinamicos">
                <fieldset>
                    <legend>
                        Graficas DMA
                    </legend>
                    <div>
                        <div class="control-group span4">
                            <label for="factor"  class="control-label">Factor: </label>
                            <div class="controls">
                                <select id="factor" name="factor" class="span4">
                                    <option value="-1">Vacio</option>
                                    <c:forEach items="${factores}" var="factor" varStatus="iter">
                                        <option value="${factor.id}">${factor.nombre}</option>    
                                    </c:forEach>
                                    <option value="-2">Todos</option>

                                </select>
                            </div>
                        </div>   
                        <div class="control-group span4" id="divCaracteristicas">
                            <label for="caracteristica"  class="control-label">Caracteristica: </label>
                            <div class="controls">
                                <select id="caracteristica" name="caracteristica" class="span4">
                                    <option value="-1">Vacio</option>
                                    <option value="-2">Todos</option>
                                </select>
                            </div>
                        </div>  
                    </div>
                    <div>
                        <div class="control-group span4" id="divPreguntas">
                            <label for="pregunta"  class="control-label">Preguntas: </label>
                            <div class="controls">
                                <select id="pregunta" name="pregunta" class="span4">
                                    <option value="-1">Vacio</option>
                                    <option value="-2">Todos</option>
                                </select>
                            </div>
                        </div>   
                        <div class="control-group span4" id="divGrupos">
                            <label for="grupo"  class="control-label">Grupos: </label>
                            <div class="controls">
                                <select id="grupo" name="grupo" class="span4">
                                    <option value="-1">Vacio</option>
                                    <option value="1">ESTUDIANTES PREGRADO</option>
                                    <option value="2">ESTUDIANTES MAESTRIAS Y DOCTORADOS</option>
                                    <option value="3">ESTUDIANTES DE ESPECIALIZACIONES</option>
                                    <option value="4">PROFESORES DE PLANTA</option>
                                    <option value="5">PROFESORES DE CÁTEDRA</option>
                                    <option value="6">DIRECTIVOS</option>
                                    <option value="7">ADMINISTRATIVOS</option>
                                    <option value="8">EGRESADOS PREGRADO</option>
                                    <option value="9">EGRESADOS ESPECIALIZACIONES</option>
                                    <option value="10">EGRESADOS MAESTRIA Y DOCTORADO</option>
                                    <option value="11">EMPLEADORES</option>
                                    <option value="-2">Todos</option>
                                </select>
                            </div>
                        </div>  
                        <div class="control-group span2">
                            <button id="mostrarResultados" class="btn btn-primary" type="button">Mostrar resultados</button>
                        </div>
                    </div>
                </fieldset>
            </form>
            <div id="barras">

            </div>
        </div>
    </div>
</div> 