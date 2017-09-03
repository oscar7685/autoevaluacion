<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="<%=request.getContextPath()%>/js/typeahead.bundle.js"></script>
<script type="text/javascript">
    $(function() {

        $('#cedula').blur(function() {
            var cedula = $('#cedula').val();
            $.ajax({
                type: 'POST',
                url: "/autoevaluacion/controladorCP?action=buscarPersona",
                data: 'cedula=' + cedula,
                success: function(persona) {
                    $("#dancing-dots-text").remove();
                    if (persona !== "1") {
                        $("#nombre").val(persona.nombre);
                        $("#apellido").val(persona.apellido);
                        $("#password").val(persona.pass);
                        $("#mail").val(persona.correo);
                        $("#nombre").attr("disabled", true);
                        $("#apellido").attr("disabled", true);
                        $("#password").attr("disabled", true);
                        $("#mail").attr("disabled", true);
                    } else {
                        $("#nombre").attr("disabled", false);
                        $("#apellido").attr("disabled", false);
                        $("#password").attr("disabled", false);
                        $("#mail").attr("disabled", false);
                    }

                } //fin success
            }); //fin $.ajax   
        });


        $("#formCrearEvaluador").validate({
            submitHandler: function() {
                $("#btnCrearEvaluador").attr("disabled", true);
                $.ajax({
                    type: 'POST',
                    url: "/autoevaluacion/controladorCP?action=crearEvaluador",
                    data: $("#formCrearEvaluador").serialize(),
                    success: function() {
                        $("#listM").empty();
                        $.ajax({
                            type: 'POST',
                            url: "/autoevaluacion/controladorCP?action=selectorListMuestra",
                            data: $("#formListarMuestra").serialize(),
                            success: function(datos) {
                                $(".divEvaluador").remove();
                                $("#listM").append(datos);
                                $("#contenido").show(200, function() {
                                    $("#dancing-dots-text").remove();
                                });
                            } //fin success
                        }); //fin $.ajax 
                    } //fin success
                }); //fin $.ajax    
            }
        });
    });
</script>
<div class="hero-unit divEvaluador">
    <div class="row">
        <div id="conte" class="span10">
            <form id="formCrearEvaluador" class="form-horizontal" method="post">
                <fieldset>
                    <legend>Crear Evaluador</legend>
                    <div class="control-group">
                        <label for="cedula" class="control-label">Cédula</label>
                        <div class="controls">
                            <input type="text" name="cedula" id="cedula" class="input-xlarge {required:true}" value=""/>
                        </div>
                    </div>
                    <div class="control-group">
                        <label for="nombre"  class="control-label">Nombres</label>
                        <div class="controls">
                            <input type="text" name="nombre" id="nombre" class="input-xlarge {required:true}" value=""/>
                        </div>
                    </div>
                    <div class="control-group">
                        <label for="apellido"  class="control-label">Apellidos</label>
                        <div class="controls">
                            <input type="text" name="apellido" id="apellido" class="input-xlarge {required:true}" value=""/>
                        </div>
                    </div>
                    <div class="control-group">
                        <label for="password"  class="control-label">Contraseña</label>
                        <div class="controls">
                            <input type="text" name="password" id="password" class="input-xlarge {required:true}" value=""/>
                        </div>
                    </div>
                    <div class="control-group">
                        <label for="mail"  class="control-label">Correo electrónico</label>
                        <div class="controls">
                            <input type="text" name="mail" id="mail" class="input-xlarge {required:true}" value=""/>
                        </div>
                    </div>
                    <c:choose>
                        <c:when test="${selectorFuente == 'Estudiante'}">
                            <div class="control-group">
                                <label for="curso"  class="control-label">Curso</label>
                                <div class="controls">
                                    <input type="text" name="curso" id="curso" class="input-xlarge {required:true}" value=""/>
                                </div>
                            </div>
                            <c:if test="${Proceso.modeloId.id == '1'}">
                                <div class="control-group">
                                    <label for="tipo"  class="control-label">Tipo</label>
                                    <div class="controls">
                                        <select name="tipo" id="tipo" class="input-xlarge {required:true}">
                                        <option value="OFICIALES">OFICIALES</option>
                                        <option value="CADETES">CADETES</option>
                                        <option value="ESPECIALIZACION">ESPECIALIZACIÓN</option>
                                        <option value="MAESTRIA">MAESTRÍA</option>
                                        </select>
                                    </div>
                                </div>
                            </c:if>
                        </c:when>
                        <c:when test="${selectorFuente == 'Docente'}">
                            <div class="control-group">
                                <label for="tipo"  class="control-label">Tipo</label>
                                <div class="controls">
                                    <select name="tipo" id="tipo" class="input-xlarge {required:true}">
                                    <option value="MILITAR">MILITAR</option>
                                    <option value="NOMINA">NÓMINA</option>
                                    <option value="CATEDRA">CÁTEDRA</option>
                                    <option value="OCASIONALES">OCASIONALES</option>
                                    </select>
                                </div>
                            </div>
                        </c:when>
                        <c:when test="${selectorFuente == 'Egresado' && Proceso.modeloId.id == '1'}">
                            <div class="control-group">
                                <label for="tipo"  class="control-label">Tipo</label>
                                <div class="controls">
                                    <select name="tipo" id="tipo" class="input-xlarge {required:true}">
                                    <option value="PREGRADO">PREGRADO</option>
                                    <option value="ESPECIALIZACION">ESPECIALIZACIÓN</option>
                                    <option value="MAESTRIA">MAESTRÍA</option>
                                    </select>
                                </div>
                            </div>
                        </c:when>
                        <c:when test="${selectorFuente == 'Administrativo'}">
                            <div class="control-group">
                                <label for="tipo"  class="control-label">Tipo</label>
                                <div class="controls">
                                    <div class="controls">
                                        <select name="tipo" id="tipo" class="input-xlarge {required:true}">
                                        <option value="CIVILES">CIVILES</option>
                                        <option value="MILITARES">MILITARES</option>
                                        <option value="SUBOFICIALES">SUBOFICIALES</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                        </c:when>
                        <c:when test="${selectorFuente == 'Empleador'}">
                            <div class="control-group">
                                <label for="empresa"  class="control-label">Empresa</label>
                                <div class="controls">
                                    <input type="text" name="empresa" id="empresa" class="input-xlarge {required:true}" value=""/>
                                </div>
                            </div>
                        </c:when>
                        <c:otherwise>
                        </c:otherwise>
                    </c:choose>

                    <div class="form-actions">
                        <button class="btn btn-primary" type="submit" id="btnCrearEvaluador">Crear Evaluador</button>
                        <button class="btn" type="reset">Cancelar</button>
                    </div>
                </fieldset>
            </form>
        </div><!--/span-->        
    </div><!--/row-->    
</div>