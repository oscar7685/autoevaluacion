<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<style type="text/css">
    .popover{
        min-width:500px;
        width: auto;
    }

    .expanded-group {
        background-attachment: scroll;
        background-clip: border-box;
        background-color: rgba(0, 0, 0, 0);
        background-image: url("css/images/minus.jpg");
        background-origin: padding-box;
        background-position: left center;
        background-repeat: no-repeat;
        background-size: auto auto;
        padding-left: 15px !important;
    }
    .collapsed-group {
        background-attachment: scroll;
        background-clip: border-box;
        background-color: rgba(0, 0, 0, 0);
        background-image: url("css/images/plus.jpg");
        background-origin: padding-box;
        background-position: left center;
        background-repeat: no-repeat;
        background-size: auto auto;
        padding-left: 15px !important;
    }
</style>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.dataTables.rowGrouping.js"></script>
<script>
    $(function() {
        var table = $('#tableR').dataTable({
            "bLengthChange": false,
            "bPaginate": false,
            "bSort": false,
            "bFilter": true,
            "bInfo": false,
            "sScrollY": $(".ui-layout-center").height() - 100,
            "scrollCollapse": true,
            "paging": false,
            "oLanguage": {
                "sProcessing": "Procesando...",
                "sZeroRecords": "No se encontraron resultados",
                "sEmptyTable": "Ningún dato disponible en esta tabla",
                "sSearch": "Buscar:",
                "sLoadingRecords": "Cargando...",
            }
        }).rowGrouping({
            iGroupingColumnIndex2: 1,
            iExpandGroupOffset: -1,
            fnGroupLabelFormat: function(label) {
                return "<botton class='btn btn-success tool' data-title='" + label.split("#")[1].replace("amp;", "") + "'>Factor " + label.split("#")[0] + "</botton>";
            },
            bExpandableGrouping: true,
            bExpandableGrouping2: true,
            fnGroupLabelFormat2: function(label) {
                return "<botton class='btn btn-info tool' data-title='" + label.split("#")[1].replace("amp;", "") + "'>&nbsp;&nbsp;Caracteristica " + label.split("#")[0] + "</botton>";
            }});
        $('.dataTables_wrapper').children(":first").children(":first").append(""
                + '<button type = "button" class = "btn btn-default  expandedGroup" ><i class="icon-plus"></i></span> Expandir los grupos</button>'
                + '<button type = "button" class = "btn btn-default collapsedGroup" ><i class="icon-minus"></i> Contraer los grupos</button>');


        $('.expandedGroup').on('click', function() {
            $(this).parents('.dataTables_wrapper').find('.collapsed-group').trigger('click');
        });
        $('.collapsedGroup').on('click', function() {
            $(this).parents('.dataTables_wrapper').find('.expanded-group').trigger('click');
        });
        $('.collapsedGroup').trigger('click');

        $('.tool').tooltip({
            container: "body"
        }).click(function(e) {
            $(this).tooltip('hide');
        });
        var hideAllPopovers = function() {
            $('.popo').each(function() {
                $(this).popover('hide');
            });

        };
        $('.popo').popover({
            placement: "top",
            container: "body",
            trigger: "manual",
            title: "Detalle encuesta",
            html: true
        }).on('click', function(e) {
            // if any other popovers are visible, hide them
            //hideAllPopovers();
            $('.popo').not(this).popover('hide'); //all but this
            $(this).popover('toggle');
            e.stopPropagation();
        });
        $(document).on('click', function(e) {
            hideAllPopovers();
        });
        $(document).tooltip({
            selector: ".tool2",
            container: "body",
            placement: "left"

        });

    });

</script>
<table class="table table-bordered" id="tableR">
    <thead style="background-color: #ffffff;">
        <tr>
            <th>Factor</th>
            <th>Caracteristica</th>
            <th>Indicador</th>
            <th>Cumplimiento</th>
            <th>Encuesta</th>
            <th>Inf. num/est</th>
            <th>Inf. doc</th>

        </tr>
    </thead>
    <tbody>
        <c:set var="indiceF" value="-1"></c:set>
        <c:set var="indiceC" value="-1"></c:set>
        <c:set var="indiceFactor" value=""></c:set>
        <c:set var="indiceCaracteristicas" value=""></c:set>
        <c:forEach items="${listIndicadores}" var="indicador" varStatus="status">
            <tr>
                <c:choose>
                    <c:when test="${indicador.caracteristicaId.factorId.id != indiceFactor}">
                        <c:set var="indiceF" value="${indiceF+1}"></c:set>
                        <c:set var="indiceFactor" value="${indicador.caracteristicaId.factorId.id}"></c:set>
                    </c:when>
                </c:choose>
                <c:choose>
                    <c:when test="${indicador.caracteristicaId.id != indiceCaracteristicas}">
                        <c:set var="indiceC" value="${indiceC+1}"></c:set>
                        <c:set var="indiceCaracteristicas" value="${indicador.caracteristicaId.id}"></c:set>
                    </c:when>
                </c:choose>
                <td>${indicador.caracteristicaId.factorId.codigo} Cumplimiento: ${cumplimientoF[indiceF]}#${indicador.caracteristicaId.factorId.nombre}</td>
                <td>${indicador.caracteristicaId.codigo} Cumplimiento: ${cumplimientoC[indiceC]}#${indicador.caracteristicaId.nombre}</td>
                <td data-original-title="${indicador.nombre}" class="tool">${indicador.codigo}</td>
                <c:choose>
                    <c:when test="${cumplimientoI[status.index] == 0}">
                        <td>N/A
                        </c:when>
                        <c:when test="${cumplimientoI[status.index] >= 1 && cumplimientoI[status.index] < 2}">
                        <td style="background-color: #AA4643;">${cumplimientoI[status.index]}
                        </c:when>    
                        <c:when test="${cumplimientoI[status.index] >= 2 && cumplimientoI[status.index] < 3}">
                        <td style="background-color: #DB843D;">${cumplimientoI[status.index]}
                        </c:when>    
                        <c:when test="${cumplimientoI[status.index] >= 3 && cumplimientoI[status.index] < 4}">
                        <td style="background-color: #3D96AE;">${cumplimientoI[status.index]}
                        </c:when>    
                        <c:otherwise>
                        <td>${cumplimientoI[status.index]}
                        </c:otherwise>    
                    </c:choose>
                </td>

                <c:choose>
                    <c:when test="${promedioE[status.index] == 0}">
                        <td> N/A
                        </c:when>  
                        <c:otherwise>
                            <c:choose>
                                <c:when test="${promedioE[status.index] >= 1 && promedioE[status.index] < 2}">
                                <td style="background-color: #AA4643;" class='popo' data-content='<table class="table table-bordered">
                                </c:when>    
                                <c:when test="${promedioE[status.index] >= 2 && promedioE[status.index] < 3}">
                                    <td style="background-color: #DB843D;" class='popo' data-content='<table class="table table-bordered">
                                    </c:when>    
                                    <c:when test="${promedioE[status.index] >= 3 && promedioE[status.index] < 4}">
                                        <td style="background-color: #3D96AE;" class='popo' data-content='<table class="table table-bordered">
                                        </c:when>
                                        <c:otherwise>
                                            <td class='popo' data-content='<table class="table table-bordered">
                                            </c:otherwise>    
                                        </c:choose>    

                                        <thead>
                                            <tr>
                                                <th>Pregunta</th>
                                                <th>Promedio</th>
                                                <th class="tool2" data-original-title="Estudiantes">Est</th>
                                                <th class="tool2" data-original-title="Docentes">Doc</th>
                                                <th class="tool2" data-original-title="Administrativos">Adm</th>
                                                <th class="tool2" data-original-title="Egresados">Egr</th>
                                                <th class="tool2" data-original-title="Directivos">Dir</th>
                                                <th class="tool2" data-original-title="Empleadores">Emp</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${indicador.preguntaList}" var="pregunta" varStatus="status3">
                                                <tr>
                                                    <td class="tool2" data-original-title="${pregunta.pregunta}">${pregunta.codigo}</td>
                                                    <c:choose>
                                                        <c:when test="${PromedioPreguntasXindicador.get(status.index).get(status3.index) >= 1 && PromedioPreguntasXindicador.get(status.index).get(status3.index) < 2}">
                                                            <td style="background-color: #AA4643;">${PromedioPreguntasXindicador.get(status.index).get(status3.index)}
                                                            </c:when>    
                                                            <c:when test="${PromedioPreguntasXindicador.get(status.index).get(status3.index) >= 2 && PromedioPreguntasXindicador.get(status.index).get(status3.index) < 3}">
                                                            <td style="background-color: #DB843D;">${PromedioPreguntasXindicador.get(status.index).get(status3.index)}
                                                            </c:when>    
                                                            <c:when test="${PromedioPreguntasXindicador.get(status.index).get(status3.index) >= 3 && PromedioPreguntasXindicador.get(status.index).get(status3.index) < 4}">
                                                            <td style="background-color: #3D96AE;">${PromedioPreguntasXindicador.get(status.index).get(status3.index)}</td>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <td>${PromedioPreguntasXindicador.get(status.index).get(status3.index)}</td>
                                                        </c:otherwise>    
                                                    </c:choose>
                                                    <c:choose>
                                                        <c:when test="${PromedioPreguntasXindicadorEs.get(status.index).get(status3.index) >= 1 && PromedioPreguntasXindicadorEs.get(status.index).get(status3.index) < 2}">
                                                            <td style="background-color: #AA4643;">${PromedioPreguntasXindicadorEs.get(status.index).get(status3.index)}
                                                            </c:when>    
                                                            <c:when test="${PromedioPreguntasXindicadorEs.get(status.index).get(status3.index) >= 2 && PromedioPreguntasXindicadorEs.get(status.index).get(status3.index) < 3}">
                                                            <td style="background-color: #DB843D;">${PromedioPreguntasXindicadorEs.get(status.index).get(status3.index)}
                                                            </c:when>    
                                                            <c:when test="${PromedioPreguntasXindicadorEs.get(status.index).get(status3.index) >= 3 && PromedioPreguntasXindicadorEs.get(status.index).get(status3.index) < 4}">
                                                            <td style="background-color: #3D96AE;">${PromedioPreguntasXindicadorEs.get(status.index).get(status3.index)}</td>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <td>${PromedioPreguntasXindicadorEs.get(status.index).get(status3.index)}</td>
                                                        </c:otherwise>    
                                                    </c:choose>        
                                                    <c:choose>
                                                        <c:when test="${PromedioPreguntasXindicadorDo.get(status.index).get(status3.index) >= 1 && PromedioPreguntasXindicadorDo.get(status.index).get(status3.index) < 2}">
                                                            <td style="background-color: #AA4643;">${PromedioPreguntasXindicadorDo.get(status.index).get(status3.index)}
                                                            </c:when>    
                                                            <c:when test="${PromedioPreguntasXindicadorDo.get(status.index).get(status3.index) >= 2 && PromedioPreguntasXindicadorDo.get(status.index).get(status3.index) < 3}">
                                                            <td style="background-color: #DB843D;">${PromedioPreguntasXindicadorDo.get(status.index).get(status3.index)}
                                                            </c:when>    
                                                            <c:when test="${PromedioPreguntasXindicadorDo.get(status.index).get(status3.index) >= 3 && PromedioPreguntasXindicadorDo.get(status.index).get(status3.index) < 4}">
                                                            <td style="background-color: #3D96AE;">${PromedioPreguntasXindicadorDo.get(status.index).get(status3.index)}</td>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <td>${PromedioPreguntasXindicadorDo.get(status.index).get(status3.index)}</td>
                                                        </c:otherwise>    
                                                    </c:choose>
                                                    <c:choose>
                                                        <c:when test="${PromedioPreguntasXindicadorAd.get(status.index).get(status3.index) >= 1 && PromedioPreguntasXindicadorAd.get(status.index).get(status3.index) < 2}">
                                                            <td style="background-color: #AA4643;">${PromedioPreguntasXindicadorAd.get(status.index).get(status3.index)}
                                                            </c:when>    
                                                            <c:when test="${PromedioPreguntasXindicadorAd.get(status.index).get(status3.index) >= 2 && PromedioPreguntasXindicadorAd.get(status.index).get(status3.index) < 3}">
                                                            <td style="background-color: #DB843D;">${PromedioPreguntasXindicadorAd.get(status.index).get(status3.index)}
                                                            </c:when>    
                                                            <c:when test="${PromedioPreguntasXindicadorAd.get(status.index).get(status3.index) >= 3 && PromedioPreguntasXindicadorAd.get(status.index).get(status3.index) < 4}">
                                                            <td style="background-color: #3D96AE;">${PromedioPreguntasXindicadorAd.get(status.index).get(status3.index)}</td>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <td>${PromedioPreguntasXindicadorAd.get(status.index).get(status3.index)}</td>
                                                        </c:otherwise>    
                                                    </c:choose>
                                                    <c:choose>
                                                        <c:when test="${PromedioPreguntasXindicadorEg.get(status.index).get(status3.index) >= 1 && PromedioPreguntasXindicadorEg.get(status.index).get(status3.index) < 2}">
                                                            <td style="background-color: #AA4643;">${PromedioPreguntasXindicadorEg.get(status.index).get(status3.index)}
                                                            </c:when>    
                                                            <c:when test="${PromedioPreguntasXindicadorEg.get(status.index).get(status3.index) >= 2 && PromedioPreguntasXindicadorEg.get(status.index).get(status3.index) < 3}">
                                                            <td style="background-color: #DB843D;">${PromedioPreguntasXindicadorEg.get(status.index).get(status3.index)}
                                                            </c:when>    
                                                            <c:when test="${PromedioPreguntasXindicadorEg.get(status.index).get(status3.index) >= 3 && PromedioPreguntasXindicadorEg.get(status.index).get(status3.index) < 4}">
                                                            <td style="background-color: #3D96AE;">${PromedioPreguntasXindicadorEg.get(status.index).get(status3.index)}</td>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <td>${PromedioPreguntasXindicadorEg.get(status.index).get(status3.index)}</td>
                                                        </c:otherwise>    
                                                    </c:choose>
                                                    <c:choose>
                                                        <c:when test="${PromedioPreguntasXindicadorDi.get(status.index).get(status3.index) >= 1 && PromedioPreguntasXindicadorDi.get(status.index).get(status3.index) < 2}">
                                                            <td style="background-color: #AA4643;">${PromedioPreguntasXindicadorDi.get(status.index).get(status3.index)}
                                                            </c:when>    
                                                            <c:when test="${PromedioPreguntasXindicadorDi.get(status.index).get(status3.index) >= 2 && PromedioPreguntasXindicadorDi.get(status.index).get(status3.index) < 3}">
                                                            <td style="background-color: #DB843D;">${PromedioPreguntasXindicadorDi.get(status.index).get(status3.index)}
                                                            </c:when>    
                                                            <c:when test="${PromedioPreguntasXindicadorDi.get(status.index).get(status3.index) >= 3 && PromedioPreguntasXindicadorDi.get(status.index).get(status3.index) < 4}">
                                                            <td style="background-color: #3D96AE;">${PromedioPreguntasXindicadorDi.get(status.index).get(status3.index)}</td>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <td>${PromedioPreguntasXindicadorDi.get(status.index).get(status3.index)}</td>
                                                        </c:otherwise>    
                                                    </c:choose>
                                                    <c:choose>
                                                        <c:when test="${PromedioPreguntasXindicadorEm.get(status.index).get(status3.index) >= 1 && PromedioPreguntasXindicadorEm.get(status.index).get(status3.index) < 2}">
                                                            <td style="background-color: #AA4643;">${PromedioPreguntasXindicadorEm.get(status.index).get(status3.index)}
                                                            </c:when>    
                                                            <c:when test="${PromedioPreguntasXindicadorEm.get(status.index).get(status3.index) >= 2 && PromedioPreguntasXindicadorEm.get(status.index).get(status3.index) < 3}">
                                                            <td style="background-color: #DB843D;">${PromedioPreguntasXindicadorEm.get(status.index).get(status3.index)}
                                                            </c:when>    
                                                            <c:when test="${PromedioPreguntasXindicadorEm.get(status.index).get(status3.index) >= 3 && PromedioPreguntasXindicadorEm.get(status.index).get(status3.index) < 4}">
                                                            <td style="background-color: #3D96AE;">${PromedioPreguntasXindicadorEm.get(status.index).get(status3.index)}</td>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <td>${PromedioPreguntasXindicadorEm.get(status.index).get(status3.index)}</td>
                                                        </c:otherwise>    
                                                    </c:choose>

                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>'> 
                                    ${promedioE[status.index]}
                                </c:otherwise>    
                            </c:choose>    

                        </td>

                        <c:choose>
                            <c:when test="${numerico[status.index] == 0}">
                                <td>N/A
                                </c:when>
                                <c:when test="${numerico[status.index] >= 1 && numerico[status.index] < 2}">
                                <td style="background-color: #AA4643;">${numerico[status.index]}
                                </c:when>    
                                <c:when test="${numerico[status.index] >= 2 && numerico[status.index] < 3}">
                                <td style="background-color: #DB843D;">${numerico[status.index]}
                                </c:when>    
                                <c:when test="${numerico[status.index] >= 3 && numerico[status.index] < 4}">
                                <td style="background-color: #3D96AE;">${numerico[status.index]}</td>
                            </c:when>
                            <c:otherwise>
                                <td>${numerico[status.index]}
                                </c:otherwise>    
                            </c:choose>
                        </td>
                        
                            <c:choose>
                            <c:when test="${documental[status.index] == 0}">
                                <td>N/A
                                </c:when>
                                <c:when test="${documental[status.index] >= 1 && documental[status.index] < 2}">
                                <td style="background-color: #AA4643;">${documental[status.index]}
                                </c:when>    
                                <c:when test="${documental[status.index] >= 2 && documental[status.index] < 3}">
                                <td style="background-color: #DB843D;">${documental[status.index]}
                                </c:when>    
                                <c:when test="${documental[status.index] >= 3 && documental[status.index] < 4}">
                                <td style="background-color: #3D96AE;">${documental[status.index]}</td>
                            </c:when>
                            <c:otherwise>
                                <td>${documental[status.index]}
                                </c:otherwise>    
                            </c:choose>
                        </td>
            </tr>
        </c:forEach>
    </tbody>
</table>   
