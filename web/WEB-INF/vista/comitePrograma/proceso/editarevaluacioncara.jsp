<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<script type="text/javascript">

    $(function() {
        $("#formEditarEvaluacionCara").validate({
            errorElement: "em"
                    ,
            highlight: function(element, errorClass) {
                $(element).parent("td").find("div").addClass("in");
                $(element).parent("td").find("div").show();
            }, unhighlight: function(element, errorClass, validClass) {
                $(element).parent("td").find("div").removeClass("in");
                $(element).parent("td").find("div").hide();
            }, errorPlacement: function(error, element) {
                error.appendTo($(element).parent("td").find("div"));
            },
            submitHandler: function() {
                $.ajax({
                    type: 'POST',
                    url: "/autoevaluacion/controladorCP?action=editarEvaluacionCara2",
                    data: $("#formEditarEvaluacionCara").serialize(),
                    success: function() {
                        location = "/autoevaluacion/#listEvaluacionCara";
                    } //fin success
                }); //fin $.ajax 
            }
        });

    });
</script>
<div class="hero-unit">
    <div class="row">
        <div id="conte" class="span10">
            <ul class="nav nav-pills">
                <form id="formEditarEvaluacionCara" class="form-horizontal" method="post">
                    <fieldset>
                        <legend>Evaluación de Características</legend>
                        <table class="table table-striped">
                            <thead>
                                <tr>
                                    <th>#</th>
                                    <th>Característica</th>
                                    <th>Evaluación</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:set var="idfactor" value="0"></c:set>
                                <c:forEach items="${listEvaluacionCara}" var="row" varStatus="iter">
                                    <c:choose>
                                        <c:when test="${row.caracteristicaId.factorId.id != idfactor}">
                                            <tr class="info">
                                                <td colspan="4">Factor ${row.caracteristicaId.factorId.codigo}: ${row.caracteristicaId.factorId.nombre}</td>   
                                                <c:set var="idfactor" value="${row.caracteristicaId.factorId.id}"></c:set>
                                                </tr>   
                                        </c:when>
                                    </c:choose>
                                    <tr id="EvaluacionCaracteristica${iter.index+1}">    
                                        <td>   
                                            <c:out value="${row.caracteristicaId.codigo}"/>
                                        </td>
                                        <td>   
                                            <c:out value="${row.caracteristicaId.nombre}"/>
                                        </td>
                                        <td>
                                            <input value="${row.evaluacion}" name="evaluacionC${row.caracteristicaId.id}" class="span1 {required:true,number:true, max: 100}" type="text">
                                            <div class='alert alert-error fade' style="display: none">
                                                <a data-dismiss='alert' class='close'>×</a>  
                                                <strong>Error!</strong>
                                            </div>
                                            <input type="hidden"  value="${row.caracteristicaId.id}" name="id${row.caracteristicaId.id}">
                                        </td>
                                    </tr>
                                    <c:set var="iterador" value="${iter.index + 1}" />
                                </c:forEach>
                            </tbody>
                        </table>
                        <input type="hidden" name="count" id="count" value="${iterador}">
                        <div class="form-actions">
                            <button class="btn btn-primary" type="submit">Editar Evaluación de Caracteristicas</button>
                            <button class="btn" type="reset">Cancelar</button>
                        </div>
                    </fieldset>
                </form>
            </ul>
        </div><!--/span-->        
    </div><!--/row-->    
</div>