<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/multiple-select.css" />
<style>
    .ms-drop input[type="checkbox"]{
        margin: 3px 3px 3px 4px;
    }
</style>
<div  class="span10 offset2" style="text-align: justify">
    <br/>
    <h2>Selección del perfil</h2>
    <br/>
    <form name="seleccionPerfil" id="seleccionPerfil">
        <div>
            <div class="form-control">
                <label>
                    <h4>Estudiante</h4>
                </label>
            </div>
            <div>
                <select name="ms1" id="ms1" multiple="multiple" class="form-control">
                    <c:forEach items="${programas}" var="row" varStatus="iter">
                        <c:choose>
                            <c:when test="${row.id != 1}">
                                <option value="${row.id}">${row.nombre}</option>
                            </c:when>
                        </c:choose>
                    </c:forEach>
                </select>
            </div>    
        </div>
        <div>
            <div class="form-control">
                <label>
                    <h4>Docente de Planta</h4>
                </label>
            </div>
            <div>
                <select name="ms2"  id="ms2" multiple="multiple" class="form-control">
                    <c:forEach items="${programas}" var="row" varStatus="iter">
                        <c:choose>
                            <c:when test="${row.id != 1}">
                                <option value="${row.id}">${row.nombre}</option>
                            </c:when>
                        </c:choose>
                    </c:forEach>
                </select>
            </div>    
        </div>
        <div>
            <div class="form-control">
                <label>
                    <h4>Docente de Catedra</h4>
                </label>
            </div>
            <div>
                <select name="ms3" id="ms3" multiple="multiple" class="form-control">
                    <c:forEach items="${programas}" var="row" varStatus="iter">
                        <c:choose>
                            <c:when test="${row.id != 1}">
                                <option value="${row.id}">${row.nombre}</option>
                            </c:when>
                        </c:choose>
                    </c:forEach>
                </select>
            </div>    
        </div>
        <div>
            <div class="form-control">
                <label>
                    <h4>Egresado</h4>
                </label>
            </div>
            <div>
                <select name="ms4"  id="ms4" multiple="multiple" class="form-control">
                    <c:forEach items="${programas}" var="row" varStatus="iter">
                        <c:choose>
                            <c:when test="${row.id != 1}">
                                <option value="${row.id}">${row.nombre}</option>
                            </c:when>
                        </c:choose>
                    </c:forEach>
                </select>
            </div>    
        </div>
        <div>
            <div class="form-control">
                <label>
                    <h4>Directivo</h4>
                </label>
            </div>
            <div>
                <select name="ms5"  id="ms5" multiple="multiple" class="form-control">
                    <c:forEach items="${programas}" var="row" varStatus="iter">
                        <option value="${row.id}">${row.nombre}</option>
                    </c:forEach>
                </select>
            </div>    
        </div>
        <div>
            <div class="form-control">
                <label>
                    <h4>Administrativo</h4>
                </label>
            </div>
            <div>
                <select name="ms6" id="ms6" multiple="multiple" class="form-control">
                    <c:forEach items="${programas}" var="row" varStatus="iter">
                        <option value="${row.id}">${row.nombre}</option>
                    </c:forEach>
                </select>
            </div>    
        </div>
        <div>
            <div class="checkbox">
                <label>
                    <input name="empleador" type="checkbox"> <h4>Empleador</h4>
                </label>
            </div>
        </div>
        <button class="btn btn-large btn-primary" type="submit" id="crearPerfil">Continuar</button>
    </form>

</div> 
<script src="<%=request.getContextPath()%>/js/multiple-select.js"></script>
<script>
    $(function() {
        $('#ms1').multipleSelect({
            width: '100%',
            selectAll: false
        });
        $('#ms2').multipleSelect({
            width: '100%',
            selectAll: false
        });
        $('#ms3').multipleSelect({
            width: '100%',
            selectAll: false
        });
        $('#ms4').multipleSelect({
            width: '100%',
            selectAll: false
        });
        $('#ms5').multipleSelect({
            width: '100%',
            selectAll: false
        });
        $('#ms6').multipleSelect({
            width: '100%',
            selectAll: false
        });

        $("#seleccionPerfil").validate({
            submitHandler: function() {
                $.ajax({
                    type: 'POST',
                    url: "/autoevaluacion/controladorF?action=responderEncuestaF",
                    data: $("#seleccionPerfil").serialize(),
                    beforeSend: function() {
                        var a = $("#seleccionPerfil").serialize();
                        if (a === null || a.length === 0)
                            $("#modalSeleccionPerfil").modal("show");
                    },
                    success: function(data) {
                        $("div.ui-layout-center").empty();
                        $("div.ui-layout-center").append("<div id='contenido'></div>");
                        $("#contenido").hide();
                        $("#contenido").append(data);
                        $("#contenido").show(400, function() {
                            $("#dancing-dots-text").remove();
                        });
                    } //fin success
                }); //fin $.ajax    
            }
        });

    });
</script>