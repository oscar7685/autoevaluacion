<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style type="text/css">
    .popover{
        min-width:500px;
        width: auto;
    }
</style>
<script type="text/javascript">
    $(function() {
        $("#indicador").on('mouseleave', function(e) {
            $('#indicador').popover('destroy');
        });

       $("#indicador").on('mouseover', function(e) {
            var $e = $(e.target);
            if ($e.is('option')) {
                $('#indicador').popover('destroy');
                $("#indicador").popover({
                    trigger: 'manual',
                    placement: 'right',
                    title: $e.attr("data-original-title"),
                    content: $e.attr("data-content")
                }).popover('show');
            }
        });
        $("#formEditarPregunta").validate({
            submitHandler: function() {
                $.ajax({
                    type: 'POST',
                    url: "/autoevaluacion/controladorCC?action=editarPregunta",
                    data: $("#formEditarPregunta").serialize(),
                    success: function() {
                        location = "/autoevaluacion/#listarPreguntas";
                    } //fin success
                }); //fin $.ajax    
            }
        });
    });
</script>
<div class="hero-unit">
    <div class="row">
        <div id="conte" class="span10">
            <form id="formEditarPregunta" class="form-horizontal" method="post">
                <fieldset>
                    <legend>Editar Pregunta</legend>
                    <div class="control-group">
                        <label for="codigo" class="control-label">C&oacute;digo</label>
                        <div class="controls">
                            <input type="text" name="codigo" id="codigo" class="input-xlarge {required:true}" value="${pregunta.codigo}"/>
                        </div>
                    </div>
                    <div class="control-group">
                        <label for="nombre"  class="control-label">Pregunta</label>
                        <div class="controls">
                            <input type="text" name="nombre" id="nombre" class="input-xlarge {required:true}" value="${pregunta.pregunta}"/>
                        </div>
                    </div>
                    <div class="control-group">
                        <label for="tipo" class="control-label">Tipo de la Pregunta</label>
                        <div class="controls">
                            <select name="tipo" id="tipo">
                                <c:choose>
                                    <c:when test="${pregunta.getTipo().equals('1to5')}">
                                        <option selected="selected"  value="1to5">Elegir del 1 al 5</option>
                                        <option value="abierta">Pregunta abierta</option>
                                        <option value="smur">Selección multiple unica respuesta</option>
                                        <option value="multiple">Selección multiple multiple respuesta</option>
                                        <option value="matriz15">Matriz 1-5</option>
                                        <option value="simatriz">Matriz Si-No</option>
                                        <option value="6matriz">Matriz 0-5</option>
                                        <option value="vecesmatriz">Matriz Veces</option>
                                    </c:when>
                                    <c:when test="${pregunta.getTipo().equals('abierta')}">
                                        <option value="1to5">Elegir del 1 al 5</option>
                                        <option value="abierta" selected="selected">Pregunta abierta</option>
                                        <option value="smur">Selección multiple unica respuesta</option>
                                        <option value="multiple">Selección multiple multiple respuesta</option>
                                        <option value="matriz15">Matriz 1-5</option>
                                        <option value="simatriz">Matriz Si-No</option>
                                        <option value="6matriz">Matriz 0-5</option>
                                        <option value="vecesmatriz">Matriz Veces</option>
                                    </c:when>
                                    <c:when test="${pregunta.getTipo().equals('smur')}">
                                        <option value="1to5">Elegir del 1 al 5</option>
                                        <option value="abierta" >Pregunta abierta</option>
                                        <option value="smur" selected="selected">Selección multiple unica respuesta</option>
                                        <option value="multiple">Selección multiple multiple respuesta</option>
                                        <option value="matriz15">Matriz 1-5</option>
                                        <option value="simatriz">Matriz Si-No</option>
                                        <option value="6matriz">Matriz 0-5</option>
                                        <option value="vecesmatriz">Matriz Veces</option>
                                    </c:when>
                                    <c:when test="${pregunta.getTipo().equals('multiple')}">
                                        <option value="1to5">Elegir del 1 al 5</option>
                                        <option value="abierta" >Pregunta abierta</option>
                                        <option value="smur">Selección multiple unica respuesta</option>
                                        <option value="multiple" selected="selected">Selección multiple multiple respuesta</option>
                                        <option value="matriz15">Matriz 1-5</option>
                                        <option value="simatriz">Matriz Si-No</option>
                                        <option value="6matriz">Matriz 0-5</option>
                                        <option value="vecesmatriz">Matriz Veces</option>
                                    </c:when>
                                    <c:when test="${pregunta.getTipo().equals('matriz15')}">
                                        <option value="1to5">Elegir del 1 al 5</option>
                                        <option value="abierta" >Pregunta abierta</option>
                                        <option value="smur">Selección multiple unica respuesta</option>
                                        <option value="multiple">Selección multiple multiple respuesta</option>
                                        <option value="matriz15" selected="selected">Matriz 1-5</option>
                                        <option value="simatriz">Matriz Si-No</option>
                                        <option value="6matriz">Matriz 0-5</option>
                                        <option value="vecesmatriz">Matriz Veces</option>
                                    </c:when>
                                    <c:when test="${pregunta.getTipo().equals('simatriz')}">
                                        <option value="1to5">Elegir del 1 al 5</option>
                                        <option value="abierta" >Pregunta abierta</option>
                                        <option value="smur">Selección multiple unica respuesta</option>
                                        <option value="multiple">Selección multiple multiple respuesta</option>
                                        <option value="matriz15">Matriz 1-5</option>
                                        <option value="simatriz" selected="selected">Matriz Si-No</option>
                                        <option value="6matriz">Matriz 0-5</option>
                                        <option value="vecesmatriz">Matriz Veces</option>
                                    </c:when>
                                    <c:when test="${pregunta.getTipo().equals('6matriz')}">
                                        <option value="1to5">Elegir del 1 al 5</option>
                                        <option value="abierta" >Pregunta abierta</option>
                                        <option value="smur">Selección multiple unica respuesta</option>
                                        <option value="multiple">Selección multiple multiple respuesta</option>
                                        <option value="matriz15">Matriz 1-5</option>
                                        <option value="simatriz">Matriz Si-No</option>
                                        <option value="6matriz" selected="selected">Matriz 0-5</option>
                                        <option value="vecesmatriz">Matriz Veces</option>
                                    </c:when>
                                    <c:when test="${pregunta.getTipo().equals('6matriz')}">
                                        <option value="1to5">Elegir del 1 al 5</option>
                                        <option value="abierta" >Pregunta abierta</option>
                                        <option value="smur">Selección multiple unica respuesta</option>
                                        <option value="multiple">Selección multiple multiple respuesta</option>
                                        <option value="matriz15">Matriz 1-5</option>
                                        <option value="simatriz">Matriz Si-No</option>
                                        <option value="6matriz">Matriz 0-5</option>
                                        <option value="vecesmatriz" selected="selected">Matriz Veces</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value=""></option>
                                        <option value="1to5">Elegir del 1 al 5</option>
                                        <option value="abierta" >Pregunta abierta</option>
                                        <option value="smur">Selección multiple unica respuesta</option>
                                        <option value="multiple">Selección multiple multiple respuesta</option>
                                        <option value="matriz15">Matriz 1-5</option>
                                        <option value="simatriz">Matriz Si-No</option>
                                        <option value="6matriz">Matriz 0-5</option>
                                        <option value="vecesmatriz" selected="selected">Matriz Veces</option>
                                    </c:otherwise>   
                                </c:choose>
                            </select>
                        </div>
                    </div>

                    <div class="control-group">
                        <label for="indicador" class="control-label">Asignar Indicador</label>
                        <div class="controls">
                            <select id="indicador" name="indicador">
                                <option></option>
                                <c:forEach items="${listaI}" var="row" varStatus="iter">
                                    <c:choose>
                                        <c:when test="${pregunta.getIndicadorId()!= row}">
                                            <option value="${row.id}" data-content="${row.nombre}" rel="popover2" data-original-title="Indicador">${row.codigo}</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option selected="selected" value="${row.id}" data-content="${row.nombre}" rel="popover2" data-original-title="Indicador">${row.codigo}</option>
                                        </c:otherwise>       
                                    </c:choose>    

                                </c:forEach>
                            </select>                

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