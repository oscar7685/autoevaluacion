<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript">
    $(function() {
        $("#tipo").on('mouseleave', function(e) {
            $('#tipo').popover('destroy');
        });
        $("#indicador").on('mouseleave', function(e) {
            $('#indicador').popover('destroy');
        });

        $("#tipo").on('mouseover', function(e) {
            var $e = $(e.target);
            if ($e.is('option')) {
                $('#tipo').popover('destroy');
                $("#tipo").popover({
                    trigger: 'manual',
                    placement: 'right',
                    html:true,
                    title: $e.attr("data-title"),
                    content:function () {
                    return '<img src="'+($e).data('img') + '" />';
                    }
                    
                }).popover('show');
            }
        });
        $("#indicador").on('mouseover', function(e) {
            var $e = $(e.target);
            if ($e.is('option')) {
                $('#indicador').popover('destroy');
                $("#indicador").popover({
                    trigger: 'manual',
                    placement: 'right',
                    title: $e.attr("data-original-title"),
                    content:$e.attr("data-content")
                }).popover('show');
            }
        });
        
        $("#formCrearPregunta").validate({
            submitHandler: function() {
                $.ajax({
                    type: 'POST',
                    url: "/autoevaluacion/controladorCC?action=crearPregunta",
                    data: $("#formCrearPregunta").serialize(),
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
            <form id="formCrearPregunta" class="form-horizontal" method="post">
                <fieldset>
                    <legend>Crear Pregunta</legend>
                    <div class="control-group">
                        <label for="codigo" class="control-label">C&oacute;digo</label>
                        <div class="controls">
                            <input type="text" name="codigo" id="codigo" class="input-xlarge {required:true}" value=""/>
                        </div>
                    </div>
                    <div class="control-group">
                        <label for="nombre"  class="control-label">Pregunta</label>
                        <div class="controls">
                            <input type="text" name="nombre" id="nombre" class="input-xlarge {required:true}" value=""/>
                        </div>
                    </div>
                    <div class="control-group">
                        <label for="tipo" class="control-label">Tipo de la Pregunta</label>
                        <div class="controls">
                            <select name="tipo" id="tipo">
                                <option value="1" data-img="<%=request.getContextPath()%>/img/1-5.png" rel="popover" data-title="Elegir del 1 al 5" >Elegir del 1 al 5</a></option>
                                <option value="2" data-img="<%=request.getContextPath()%>/img/Si-No.png" rel="popover" data-title="Pregunta abierta">Pregunta abierta</option>
                            </select>
                        </div>
                    </div>

                    <div class="control-group">
                        <label for="indicador" class="control-label">Asignar Indicador</label>
                        <div class="controls">
                            <select id="indicador" name="indicador" class="span">
                                <option></option>
                                <c:forEach items="${listaI}" var="row" varStatus="iter">
                                    <option data-content="${row.nombre}" rel="popover2" data-original-title="Indicador" value="${row.id}">${row.codigo}</option>
                                </c:forEach>
                            </select>                

                        </div>
                    </div>

                    <div class="form-actions">
                        <button class="btn btn-primary" type="submit">Crear Pregunta</button>
                        <button class="btn" type="reset">Cancelar</button>
                    </div>
                </fieldset>
            </form>
        </div><!--/span-->        
    </div><!--/row-->    
</div>