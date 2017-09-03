<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript" language="JavaScript">
    $(document).ready(function() {
            $.ajax({
                type: 'POST',
                url: "/autoevaluacion/controladorCP?action=resultadosInstitucionales",
                success: function(data) {
                    $("#resultados").empty();
                     $("#resultados").html(data);
                     setTimeout(function() {
                     $("#dancing-dots-text").remove();
                     }, 200);
                 }
            });
    });


</script>
<div class="hero-unit">
    <div class="row">
        <div id="conte" class="span10">
            <fieldset>
                <legend>
                    Estado del proceso
                </legend>
                <br>
            </fieldset>
            <div id="resultados">
            </div>
        </div>
    </div>
</div>                        
