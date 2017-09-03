<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<script type="text/javascript">
    $(function() {
        $("#selectListMuestra").change(function() {
            $("#listM").empty();
            var a = $("#selectListMuestra option:selected").index();
            if (a == 0) {
                $("#listM").empty();
                $("#help1").html('<div class="alert alert-info" role="alert"><strong>Atenci&oacute;n</strong> Seleccione una fuente para ver la muestra asignada a la misma.</div>');
            }
            else {//para hacer el editar muestra
                $("#help1").empty();
                $("#listM").empty();
                $.ajax({
                    type: 'POST',
                    url: "/autoevaluacion/controladorCP?action=selectorListMuestra",
                    data: $("#formListarMuestra").serialize(),
                    success: function(datos) {
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
                                        <option value="Estudiante">Estudiantes</option>
                                        <option value="Docente">Docentes</option>
                                        <option value="Egresado">Egresados</option>
                                        <option value="Administrativo">Administrativos</option>
                                        <option value="Directivo">Directivos</option>
                                        <option value="Empleador">Empleadores</option>
                                    </select>
                                </div>
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

