<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<script type="text/javascript">

    $(function() {
        $("#formEvaluarCara").validate({
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
                    url: "/autoevaluacion/controladorCP?action=evaluarCara",
                    data: $("#formEvaluarCara").serialize(),
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
                <form id="formEvaluarCara" class="form-horizontal" method="post">
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
                                <c:forEach items="${listCara}" var="row" varStatus="iter">
                                    <c:choose>
                                        <c:when test="${row.factorId.id != idfactor}">
                                            <tr class="info">
                                                <td colspan="4">Factor ${row.factorId.codigo}: ${row.factorId.nombre}</td>   
                                                <c:set var="idfactor" value="${row.factorId.id}"></c:set>
                                                </tr>   
                                        </c:when>
                                    </c:choose>
                                    <tr id="EvaluacionCaracteristica${iter.index+1}">    
                                        <td>   
                                            <c:out value="${row.codigo}"/>
                                        </td>
                                        <td>   
                                            <c:out value="${row.nombre}"/>
                                        </td>
                                        <td>
                                            <input id="evaluacion{row.factorId.codigo}-${row.id}" name="evaluacionC${row.id}" class="factorX${row.factorId.id} span1 {required:true,number:true, max: 100}" type="text">
                                            <div class='alert alert-error fade' style="display: none">
                                                <a data-dismiss='alert' class='close'>×</a>  
                                                <strong>Error!</strong>
                                            </div>
                                            <input type="hidden"  value="${row.id}" name="id${row.id}">
                                        </td>
                                    </tr>
                                    <c:set var="iterador" value="${iter.index + 1}" />
                                </c:forEach>
                            </tbody>
                        </table>
                        <input type="hidden" name="count" id="count" value="${iterador}">
                        <div class="form-actions">
                            <button class="btn btn-primary" type="submit">Evaluar Caracteristicas</button>
                            <button class="btn" type="reset">Cancelar</button>
                        </div>
                    </fieldset>
                </form>
            </ul>
        </div><!--/span-->        
    </div><!--/row-->    
</div>