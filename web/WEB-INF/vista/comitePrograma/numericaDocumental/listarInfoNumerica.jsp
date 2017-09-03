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
            <legend>Listado de evaluación información num&eacute;rica</legend>
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
                        <c:when test="${fn:length(listaNum)!= 0}">
                            <c:forEach items="${listaNum}" var="itemN" varStatus="iterN">
                                <tr>
                                    <td>${itemN.indicadorId.codigo}</td>
                                    <td style="text-align: justify;">${itemN.indicadorId.nombre}</td>
                                    <td>
                                        ${itemN.documento}
                                    </td>
                                    <td>
                                        ${itemN.responsable}
                                    </td>
                                    <td>
                                        ${itemN.medio}
                                    </td>
                                    <td>
                                        ${itemN.lugar}
                                    </td>
                                    <td>
                                        ${itemN.evaluacion}
                                    </td>
                                    <td>
                                        ${itemN.accion}
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            No hay informaci&oacute;n num&eacute;rica registrada en el sistema. 
                        </c:otherwise>                
                    </c:choose>          
                </tbody>
            </table>  
        </div>
    </div>
</div>    