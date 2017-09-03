<script type="text/javascript">
    $(function() {
        $("#formCrearEncuesta").validate({
            submitHandler: function() {
                $.ajax({
                    type: 'POST',
                    url: "/autoevaluacion/controladorCC?action=crearEncuesta",
                    data: $("#formCrearEncuesta").serialize(),
                    success: function() {
                        location = "/autoevaluacion/#listarEncuestas";
                    } //fin success
                }); //fin $.ajax    
            }
        });
    });
</script>
<div class="hero-unit">
    <div class="row">
        <div id="conte" class="span10">
            <form id="formCrearEncuesta" class="form-horizontal" method="post">
                <fieldset>
                    <legend>Crear Encuesta</legend>
                    <div class="control-group">
                        <label for="nombre" class="control-label">Nombre</label>
                        <div class="controls">
                            <input type="text" name="nombre" id="nombre" class="input-xlarge {required:true}" value=""/>
                        </div>
                    </div>
                    <div class="control-group">
                        <label for="objetivo" class="control-label">Objetivo</label>
                        <div class="controls">
                            <textarea rows="3" name="objetivo" id="objetivo" class="input-xxlarge {required:true}"></textarea>
                        </div>
                    </div>
                    <div class="control-group">
                        <label for="instrucciones" class="control-label">Instrucciones</label>
                        <div class="controls">
                            <textarea rows="6" name="instrucciones" id="instrucciones" class="input-xxlarge {required:true}"></textarea>
                        </div>
                    </div>
                    <div class="control-group">
                        <label for="codigo" class="control-label">C&oacute;digo</label>
                        <div class="controls">
                            <input type="text" name="codigo" id="codigo" class="input-xlarge {required:true}" value=""/>
                        </div>
                    </div>
                    <div class="control-group">
                        <label for="version" class="control-label">Versi&oacute;n</label>
                        <div class="controls">
                            <input type="text" name="version" id="version" class="input-xlarge {required:true}" value=""/>
                        </div>
                    </div>
                    <div class="control-group">
                        <label for="fecha" class="control-label">Fecha</label>
                        <div class="controls">
                            <input type="text" name="fecha" id="fecha" class="input-xlarge {required:true}" value=""/>
                        </div>
                    </div>
                    <div class="form-actions">
                        <button class="btn btn-primary" type="submit">Crear Encuesta</button>
                        <button class="btn" type="reset">Cancelar</button>
                    </div>
                </fieldset>
            </form>
        </div><!--/span-->        
    </div><!--/row-->    
</div>