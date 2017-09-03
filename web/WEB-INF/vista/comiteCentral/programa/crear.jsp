<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<script type="text/javascript">
    $(function() {
        $("#formX").validate({
            submitHandler: function() {
                $.ajax({
                    type: 'POST',
                    url: "/autoevaluacion/controladorCC?action=crearPrograma",
                    data: $("#formX").serialize(),
                    success: function() {
                        location = "/autoevaluacion/#listarProgramas";
                    } //fin success
                }); //fin $.ajax    
            }
        });
    });
</script>
<div class="hero-unit">
    <div class="row">
        <div id="conte" class="span10">
            <form id="formX" class="form-horizontal" method="post">
                <fieldset>
                    <legend>Nuevo programa</legend>
                    <div class="control-group">
                        <label for="nombre"  class="control-label">Nombre</label>
                        <div class="controls">
                            <input type="text" name="nombre" id="nombre" class="input-xxlarge {required:true}" value=""/>
                        </div>
                    </div>
                    <div class="control-group">
                        <label for="descripcion" class="control-label">Descripci&oacute;n</label>
                        <div class="controls">
                            <textarea rows="3" name="descripcion" id="descripcion" class="input-xxlarge {required:true}"></textarea>
                        </div>
                    </div>
                    <div class="control-group">
                        <label for="modalidad" class="control-label">Modalidad</label>
                        <div class="controls">
                            <select id="modalidad" name="modalidad" class="input-xlarge {required:true}">
                                <option></option>
                                <option value="Presencial">Presencial</option>
                                <option value="Distancia">Distancia</option>
                            </select>   
                        </div>
                    </div>
                    <div class="control-group">
                        <label for="tipoformacion" class="control-label">Tipo de formacion</label>
                        <div class="controls">
                            <select id="tipoformacion" name="tipoformacion" class="input-xlarge {required:true}">
                                <option></option>
                                <option value="Universitaria">Universitaria</option>
                                <option value="Especializacion">Especialización</option>
                                <option value="Maestria">Maestría</option>
                                <option value="Doctorado">Doctorado</option>
                                <option value="Tecnica">Técnica</option>
                                <option value="Tecnologica">Tecnológica</option>
                            </select>   
                        </div>
                    </div>
                    <div class="control-group">
                        <label for="facultad" class="control-label">Facultad</label>
                        <div class="controls">
                            <select id="facultad" name="facultad" class="input-xlarge {required:true}">
                                <option></option>
                                <c:forEach items="${listaFac}" var="row" varStatus="iter">
                                    <option value="${row.id}">${row.nombre}</option>
                                </c:forEach>
                            </select>       
                        </div>
                    </div>
                    <div class="form-actions">
                        <button class="btn btn-primary" type="submit">Crear</button>
                        <button class="btn" type="reset">Cancelar</button>
                    </div>
                </fieldset>
            </form>
        </div><!--/span-->        
    </div><!--/row-->    
</div>