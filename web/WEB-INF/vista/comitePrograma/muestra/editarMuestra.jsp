<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<c:if test="${EstadoProceso == 2}">
    <div>
        <span class="label label-success" style="background-color: #F2DEDE;
              border-color: #EED3D7;
              color: #B94A48;">Pendiente</span>
        <span class="label label-success" style="background-color: #DFF0D8;
              border-color: #D6E9C6;
              color: #468847;">Terminado</span>
        <span class="label label-success" style="background-color: #D9EDF7;
              border-color: #BCE8F1;
              color: #3A87AD; margin-bottom: 5px">Guardado</span>
    </div>
</c:if>
<form id="formEditarMuestra">
    <fieldset>
        <ul id="fcbklist" class="span10">
            <c:forEach items="${listPoblacion}" var="item" varStatus="iter">
                <li>
                    <strong>${item.personaId.nombre} ${item.personaId.apellido}</strong><br/> 
                    <span class="fcbkitem_text">${item.personaId.id}</span>
                    <input name="${item.personaId.id}" type="hidden" value="0"/>
                </li>
            </c:forEach>
            <c:forEach items="${listMuestraSeleccionada}" var="item2" varStatus="iter2">
                <li id="itemblockRojo">
                    <c:set var="auxx" value="0"></c:set>
                    <strong>${item2.muestrapersonaId.nombre} ${item2.muestrapersonaId.apellido}</strong><br/> 
                    <span class="fcbkitem_text">${item2.muestrapersonaId.cedula}</span>
                    <input name="${item2.muestrapersonaId.cedula}" type="hidden" checked="checked" value="0"/>
                </li>
            </c:forEach>       

        </ul>
    </fieldset>
    <div class="form-actions">
        <button class="btn btn-primary" id="botonActualizarMuestra" type="button">Actualizar Muestra Para Fuente Seleccionada</button>
        <button class="btn btn-secundary" id="botonCancelar" type="button">Cancelar</button>
    </div>
</form>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/js/dragDrop/selector.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/dragDrop/selector.js"></script>
<script type="text/javascript">
    $(document).ready(function() {
        $.fcbkListSelection("#fcbklist", "", "50", "6");

        $("#botonActualizarMuestra").click(function() {
            $.ajax({
                type: 'POST',
                url: "/autoevaluacion/controladorCP?action=editarMuestra",
                data: $("#formEditarMuestra").serialize(),
                success: function() {
                    $("#listM").empty();
                    $.ajax({
                        type: 'POST',
                        url: "/autoevaluacion/controladorCP?action=selectorListSemestre",
                        data: $("#formListarMuestra").serialize(),
                        success: function(datos) {
                            $("#listM").append(datos);
                            $("#contenido").show(200, function() {
                                $("#dancing-dots-text").remove();
                            });
                        } //fin success
                    }); //fin $.ajax   

                } //fin success
            }); //fin $.ajax    

        });

        $("#botonCancelar").click(function() {
            $("#listM").empty();
            $.ajax({
                type: 'POST',
                url: "/autoevaluacion/controladorCP?action=selectorListSemestre&botonCancelar=OK",
                data: $("#formListarMuestra").serialize(),
                success: function(datos) {
                    $("#listM").append(datos);
                    $("#contenido").show(200, function() {
                        $("#dancing-dots-text").remove();
                    });
                } //fin success
            }); //fin $.ajax    
        });
    });
</script>