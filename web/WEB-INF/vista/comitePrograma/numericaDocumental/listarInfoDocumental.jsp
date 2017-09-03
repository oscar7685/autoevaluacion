<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script>
    $(document).ready(function() {
        $("#printEnlace").click(function() {
            $('#conte').jqprint();
            return false;
        });
    });

</script>
<div class="hero-unit">
    <div class="row">
        <div id="conte" class="span10">
            <legend>Listado de evaluación información documental</legend>
            <table class="table table-bordered table-condensed">
                <thead>
                <th>C&oacute;digo</th>
                <th>Indicador</th>
                <th>Documento asociado</th>
                <th>Responsable</th>
                <th>Medio</th>
                <th>Lugar</th>
                <th>Estado</th>
                <th>Acci&oacute;n a implementar u observaci&oacute;n</th>
                </thead>
                <tbody>
                <tbody>
                    <c:choose>
                        <c:when test="${fn:length(listaDoc)!= 0}">
                            <c:forEach items="${listaDoc}" var="itemD" varStatus="iterD">
                                <tr>
                                    <td>${itemD.indicadorId.codigo}</td>
                                    <td style="text-align: justify;">${itemD.indicadorId.nombre}</td>
                                    <td>
                                        ${itemD.documento}
                                    </td>
                                    <td>
                                        ${itemD.responsable}
                                    </td>
                                    <td>
                                        ${itemD.medio}
                                    </td>
                                    <td>
                                        ${itemD.lugar}
                                    </td>
                                    <td>
                                       ${itemD.evaluacion}
                                    </td>
                                    <td>
                                        ${itemD.accion}
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            No hay informaci&oacute;n documental registrada en el sistema. 
                        </c:otherwise>                
                    </c:choose>          
                </tbody>
            </table>  
        </div>
    </div>
</div>    