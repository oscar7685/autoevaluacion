<script type="text/javascript">
    $(function() {
        $.validator.addMethod("coincidencia", function(value, element) {
            return $('#nueva1').val() === $('#nueva2').val();
        }, "Las contraseñas ingresadas no coinciden");

        $("#formularioContrasena").validate({
            submitHandler: function() {
                $.ajax({
                    type: 'POST',
                    url: "/autoevaluacion/controladorCP?action=cambiarClave",
                    data: $("#formularioContrasena").serialize(),
                    success: function(data) {
                        $("#dancing-dots-text").remove();
                        if (data == "0") {
                            $("#modalClaveOK").modal();
                            location = "/autoevaluacion/#inicio";
                        } else {
                            if (data == "1") {
                                $("#modalClaveERROR").modal();
                            }
                        }

                    } //fin success
                }); //fin $.ajax    
            }
        });
    });
</script>    
<div class="hero-unit">
    <div class="row">
        <div id="conte" class="span10">
            <form name="formularioContrasena"  id="formularioContrasena">
                <fieldset>
                    <legend>Cambiar Contrase&nacute;a</legend>
                    <div class="control-group">
                        <label for="actual" class="control-label">Contrase&nacute;a actual</label>
                        <div class="controls">
                            <input type="password" placeholder="Contraseña actual" name="actual" id="actual" class="input-xlarge {required:true}">
                        </div>
                    </div>
                    <div class="control-group">
                        <label for="nueva1" class="control-label">Nueva contrase&nacute;a</label>
                        <div class="controls">
                            <input type="password" placeholder="Nueva contraseña" name="nueva1" id="nueva1" class="input-xlarge {required:true}" >
                        </div>
                    </div>
                    <div class="control-group">
                        <label for="nueva2" class="control-label">Nueva contrase&nacute;a</label>
                        <div class="controls">
                            <input type="password" placeholder="Nueva contraseña" name="nueva2" id="nueva2" class="input-xlarge {required:true, coincidencia:true }" >
                        </div>
                    </div>
                    <div class="form-actions">
                        <button class="btn btn-primary" type="submit">Cambiar contrase&nacute;a</button>
                    </div>

                </fieldset>
            </form>
        </div>
    </div> 
</div> 
<div class="modal hide fade" id="modalClaveOK">
    <div class="modal-header">
        <a data-dismiss="modal" class="close">×</a>
        <h3>Atención!</h3>
    </div>
    <div class="modal-body">
        <h4>Contrase&nacute;a cambiada exitosamente.</h4>
    </div>
    <div class="modal-footer">
        <a class="btn btn-primary" data-dismiss="modal" href="#">Cerrar</a>
    </div>
</div>
<div class="modal hide fade" id="modalClaveERROR">
    <div class="modal-header">
        <a data-dismiss="modal" class="close">×</a>
        <h3>Error!</h3>
    </div>
    <div class="modal-body">
        <h4>Ha ocurrido un error.</h4>
    </div>
    <div class="modal-footer">
        <a class="btn btn-primary" data-dismiss="modal" href="#">Cerrar</a>
    </div>
</div>