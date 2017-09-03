<script type="text/javascript">
    $(function() {
        $("#formEditarModelo").validate({
            submitHandler: function() {
                $.ajax({
                    type: 'POST',
                    url: "/autoevaluacion/controladorCC?action=editarModelo",
                    data: $("#formEditarModelo").serialize(),
                    success: function() {
                        location = "/autoevaluacion/#listarModelo";
                    } //fin success
                }); //fin $.ajax    
            }
        });
    });
</script>
<div class="hero-unit">
    <div class="row">
        <div id="conte" class="span10">
            <form id="formEditarModelo" class="form-horizontal" method="post">
                <fieldset>
                    <legend>Editar Modelo</legend>
                    <div class="control-group">
                        <label for="nombre"  class="control-label">Modelo</label>
                        <div class="controls">
                            <input type="text" name="nombre" id="nombre" class="input-xlarge {required:true}" value="${modelo.nombre}"/>
                        </div>
                    </div>
                    <div class="control-group">
                        <label for="descripcion" class="control-label">Descripci&oacute;n</label>
                        <div class="controls">
                            <textarea rows="3" name="descripcion" id="descripcion" class="input-xxlarge {required:true}">${modelo.descripcion}</textarea>
                        </div>
                    </div>

                    <div class="form-actions">
                        <button class="btn btn-primary" type="submit">Guardar cambios</button>
                        <button class="btn" type="reset">Cancelar</button>
                    </div>
                </fieldset>
            </form>
        </div><!--/span-->        
    </div><!--/row-->    
</div>