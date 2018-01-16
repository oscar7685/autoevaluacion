<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<script type="text/javascript">
    $(function () {
        $("#selectListMuestra").change(function () {
            $("#listM").empty();
            var a = $("#selectListMuestra option:selected").index();
            if (a == 0) {
                $("#listM").empty();
                $("#help1").html('<div class="alert alert-info" role="alert"><strong>Atenci&oacute;n</strong> Seleccione una fuente para ver la muestra asignada a la misma.</div>');
            } else if (a == 1 || a == 2 || a == 3) {
                $("#divPrograma").show();
                $("#help1").html('Seleccione un programa para filtrar los resultados.');
                $("#listM").empty();
                $.ajax({
                    type: 'POST',
                    url: "/autoevaluacion/controladorCP?action=listarProgramasSelect&a=" + a,
                    success: function (datos) {
                        $("#selectPrograma").html(datos);
                        setTimeout(function () {
                            $("#dancing-dots-text").remove();
                        }, 200);
                    } //fin success
                }); //fin $.ajax 
            } else {//para hacer el editar muestra
                $("#help1").empty();
                $("#divPrograma").hide();
                $("#listM").empty();
                $.ajax({
                    type: 'POST',
                    url: "/autoevaluacion/controladorCP?action=selectorListMuestra",
                    data: $("#formListarMuestra").serialize(),
                    success: function (datos) {
                        $("#listM").append(datos);
                        setTimeout(function () {
                            $("#dancing-dots-text").remove();
                        }, 200);
                    } //fin success
                }); //fin $.ajax    
            }
        });
        
        $("#selectPrograma").change(function() {
            var a = $("#selectPrograma option:selected").index();
            if (a == 0) {
                $("#listM").empty();
            } else {

                $.ajax({
                    type: 'POST',
                    url: "/autoevaluacion/controladorCP?action=selectorListPrograma",
                    data: $("#formListarMuestra").serialize(),
                    success: function(datos) {
                        $("#listM").empty();
                        $("#listM").append(datos);
                        setTimeout(function() {
                            $("#dancing-dots-text").remove();
                        }, 200);

                    } //fin success
                }); //fin $.ajax    
            }
        });
        
        
    });
</script>
<div class="hero-unit">
    <div class="row">
        <div id="conte" class="span10">
            <ul class="nav nav-pills" style="margin-bottom: 0px">
                <form id="formListarMuestra" class="" method="post" style="margin-bottom: 0px">
                    <fieldset>
                        <legend>Asignación de  Muestra</legend>
                        <div class="span3" style="margin-left: 0px">
                            <div class="control-group">
                                <label for="selectListMuestra"  class="control-label">Fuente: </label>
                                <div class="controls">
                                    <select name="fuente" id="selectListMuestra">
                                        <option value="--">Seleccionar Fuente</option>
                                        <option value="EstudianteP">ESTUDIANTES PREGRADO</option>
                                        <option value="EstudianteM">ESTUDIANTES MAESTRIAS Y DOCTORADOS</option>
                                        <option value="EstudianteE">ESTUDIANTES DE ESPECIALIZACIONES</option>
                                        <option value="ProfesorP">PROFESORES DE PLANTA</option>
                                        <option value="ProfesorC">PROFESORES DE CÁTEDRA</option>
                                        <option value="Directivo">DIRECTIVOS</option>
                                        <option value="Administrativo">ADMINISTRATIVOS</option>
                                        <option value="EgresadoP">EGRESADOS PREGRADO</option>
                                        <option value="EgresadoE">EGRESADOS ESPECIALIZACIONES</option>
                                        <option value="EgresadoM">EGRESADOS MAESTRIA Y DOCTORADO</option>
                                        <option value="Empleador">EMPLEADORES</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="control-group" id="divPrograma" style="display: none" class="span7">
                            <label for="selectPrograma"  class="control-label">Programa: </label>
                            <div class="controls">
                                <form id="formSelectMuestra"  method="post">
                                    <select name="programa" id="selectPrograma">
                                        <option value="--">Seleccione Programa</option>
                                        <option value="todos">Todos</option>
                                    </select>
                                </form>
                            </div>
                        </div> 
                    </fieldset>
                </form>
                <div id="help1"><div class="alert alert-info" role="alert"><strong>Atenci&oacute;n</strong> Seleccione una fuente para ver la muestra asignada a la misma.</div></div>
            </ul>
            <div id="listM"></div>
        </div>
    </div>
</div>                       

