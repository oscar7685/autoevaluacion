<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<link rel="stylesheet" href="css/jquery.fileupload.css">
<style type="text/css">
    #sharefLI_1 {
        box-sizing: border-box;
        color: rgb(95, 118, 118);
        height: 43px;
        width: 506.031px;
        perspective-origin: 253.016px 21.5px;
        transform-origin: 253.016px 21.5px;
        border: 0px none rgb(95, 118, 118);
        font: normal normal normal normal 14px / 20px 'Source Sans Pro', 'Segoe UI', 'Droid Sans', Tahoma, Arial, sans-serif;
        list-style: none outside none;
        outline: rgb(95, 118, 118) none 0px;
    }/*#sharefLI_1*/

    #sharefA_2 {
        box-sizing: border-box;
        color: rgb(52, 152, 219);
        cursor: pointer;
        display: inline-block;
        height: 43px;
        text-align: center;
        text-decoration: none;
        touch-action: manipulation;
        vertical-align: middle;
        white-space: nowrap;
        width: 235.297px;
        perspective-origin: 117.641px 21.5px;
        transform-origin: 117.641px 21.5px;
        background: rgba(255, 255, 255, 0) none repeat scroll 0% 0% / auto padding-box border-box;
        border: 1px solid rgb(52, 152, 219);
        border-radius: 3px 3px 3px 3px;
        font: normal normal normal normal 17px / 22.61px 'Source Sans Pro', 'Segoe UI', 'Droid Sans', Tahoma, Arial, sans-serif;
        list-style: none outside none;
        outline: rgb(52, 152, 219) none 0px;
        padding: 0px 29px 0px 21px;
    }/*#sharefA_2*/

    #sharefI_3 {
        box-shadow: rgba(0, 0, 0, 0.0980392) -1px 0px 0px 0px inset;
        box-sizing: border-box;
        color: rgb(52, 152, 219);
        cursor: pointer;
        display: inline-block;
        height: 41px;
        left: -21px;
        position: relative;
        text-align: center;
        white-space: nowrap;
        width: 48px;
        perspective-origin: 24px 20.5px;
        transform-origin: 24px 20.5px;
        background: rgba(0, 0, 0, 0.0196078) none repeat scroll 0% 0% / auto padding-box border-box;
        border: 0px none rgb(52, 152, 219);
        border-radius: 3px 0 0 3px;
        font: normal normal normal normal 17px / 23px FontAwesome;
        list-style: none outside none;
        outline: rgb(52, 152, 219) none 0px;
        padding: 9px;
    }/*#sharefI_3*/

    #sharefI_3:before {
        box-sizing: border-box;
        color: rgb(52, 152, 219);
        cursor: pointer;
        text-align: center;
        white-space: nowrap;
        border: 0px none rgb(52, 152, 219);
        font: normal normal normal normal 17px / 23px FontAwesome;
        list-style: none outside none;
        outline: rgb(52, 152, 219) none 0px;
    }/*#sharefI_3:before*/
</style>
<script type="text/javascript">
    $(function() {
        $("#botonGenerarMuestraCenso").click(function() {
            $.ajax({
                type: 'POST',
                url: "/autoevaluacion/controladorCP?action=generarMuestraCenso",
                success: function() {
                    location = "/autoevaluacion/#listMuestra";
                } //fin success
            }); //fin $.ajax    

        });

        $("#selectListMuestra").change(function() {
            $("#listM").empty();
            var a = $("#selectListMuestra option:selected").index();
            if (a == 0) {
                $("#listM").empty();
                $("#help1").html('<div class="alert alert-info" role="alert"><strong>Atenci&oacute;n</strong> Seleccione una fuente para ver la población asignada a la misma.</div>');
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

    });</script>


<div class="hero-unit">
    <div class="row">
        <div id="conte" class="span10">
            <div>
                <c:if test="${Muestra == null}">
                    No  se ha registrado la muestra en el sistema para este proceso.
                    <br><br>
                    <a id="botonGenerarMuestraCenso" href="#generarMuestraCenso" class="btn btn-large btn-primary llamador"><i class="icon-edit-sign"></i> Aplicar Censo</a>
                </c:if>
            </div>
            <ul class="nav nav-pills" style="margin-bottom: 0px">
                <form id="formListarMuestra" class="" method="post" style="margin-bottom: 0px">
                    <fieldset>
                        <legend>Adjuntar la Población</legend>
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
                <div id="help1"><div class="alert alert-info" role="alert"><strong>Atenci&oacute;n</strong> Seleccione una fuente para ver la población asignada a la misma.</div></div>
            </ul>
            <div id="listM"></div>
        </div>
    </div>
</div>