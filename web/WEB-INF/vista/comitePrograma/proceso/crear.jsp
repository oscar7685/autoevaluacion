<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<script type="text/javascript">

    $(function() {
        $("#formCrearProceso").validate({
            submitHandler: function() {
                $.ajax({
                    type: 'POST',
                    url: "/autoevaluacion/controladorCP?action=crearProceso",
                    data: $("#formCrearProceso").serialize(),
                    success: function(data) {
                        $("#dancing-dots-text").remove();
                        if (data == 1) {
                            location = "/autoevaluacion/#controlPanel";
                        }
                        if (data == 0) {
                            $('#modalCc3').modal();
                            location = "/autoevaluacion/#controlPanel";
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
            <form id="formCrearProceso" class="form-horizontal" method="post">
                <fieldset>
                    <legend>Nuevo Proceso de Autoevaluaci&oacute;n</legend>
                    <div class="control-group">
                        <label for="programa"  class="control-label">Programa</label>
                        <div class="controls">
                            <select id="programa" name="programa" class="input-xlarge">
                                <c:forEach items="${listPrograma}" var="item" >
                                    <option value="${item.id}">${item.nombre}</option>
                                </c:forEach>
                            </select>   
                        </div>
                    </div>
                    <div class="control-group">
                        <label for="descripcion" class="control-label">Descripci&oacute;n</label>
                        <div class="controls">
                            <textarea rows="3" name="descripcion" id="descripcion" class="input-xxlarge {required:true}"></textarea>
                        </div>
                    </div>
                    <div class="control-group">
                        <label for="modelo" class="control-label">Modelo</label>
                        <div class="controls">
                            <select id="modelo" name="modelo" class="input-xlarge">
                                <c:forEach items="${listModelo}" var="item" >
                                    <option value="${item.id}">${item.nombre}</option>
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