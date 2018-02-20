<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript" language="JavaScript">
    $(document).ready(function() {
        $(".printEnlace").click(function() {
            $('#conte').jqprint();
            return false;
        });
    });



    $(function() {
        var chart;
        $(document).ready(function() {
            chart = new Highcharts.Chart({
                chart: {
                    renderTo: 'container',
                    type: 'column'
                },
                title: {
                    text: 'Muestra seleccionada y evaluada'
                },
                xAxis: {
                    categories: [
                        'Estudiantes Pregrado',
                        'Estudiantes Especialización',
                        'Estudiantes Maestria',
                        'Docentes Planta',
                        'Docentes Catedra',
                        'Administrativos',
                        'Directivos',
                        'Egresados Pregrado',
                        'Egresados Especialización',
                        'Egresados Maestria',
                        'Empleadores'
                    ]
                },
                yAxis: {
                    min: 0,
                    title: {
                        text: 'Número de personas'
                    }
                },
                plotOptions: {
                    column: {
                        pointPadding: 0.2,
                        borderWidth: 0,
                        dataLabels: {
                            enabled: true,
                            color: "#4572A7",
                            style: {
                                fontWeight: 'bold'
                            },
                            formatter: function() {
                                return this.y + '';
                            }
                        }
                    }
                },
                series: [{
                        name: 'Seleccionados',
                        data: [${totalEst}, ${totalEstEsp}, ${totalEstMae}, ${totalDoc}, ${totalDocCat}, ${totalAdm}, ${totalDir}, ${totalEgr}, ${totalEgrEsp}, ${totalEgrMae},${totalEmp}]

                    }, {
                        name: 'Evaluados',
                        data: [${terminadosEst},${terminadosEstEsp},${terminadosEstMae}, ${terminadosDoc},${terminadosDocCat}, ${terminadosAdm}, ${terminadosDir}, ${terminadosEgr}, ${terminadosEgrEsp}, ${terminadosEgrMae},${terminadosEmp}],
                        color: '#89A54E'

                    }],
                tooltip: {
                    formatter: function() {
                        return '<b>' + this.x + '</b>: ' + this.y + ' personas';
                    }
                }

            });
        });
    });
</script>
<div class="hero-unit">
    <a  class="span10 printEnlace" style="text-align: right; margin-left: 0px; text-align: right; cursor: pointer"><i class="icon-print"></i> Imprimir</a>  
    <div class="row">
        <div id="conte" class="span10">
            <fieldset>
                <legend>
                    Estado del proceso
                </legend>
                <div>
                    <p>
                        Detalle:
                    </p>
                </div>
                <table class="table table-striped table-bordered table-condensed">
                    <thead>
                    <th>Descripción</th>
                    <th>Fecha Inicio</th>
                    <th>Fecha Cierre</th>
                    <th>Programa</th>
                    </thead>
                    <tbody>
                        <tr> 
                            <td class="span4">${Proceso.descripcion}</td>
                            <td>${Proceso.fechainicio}</td>
                            <td>${Proceso.fechacierre}</td>
                            <td>${Proceso.programaId.nombre}</td>
                        </tr>
                    </tbody>
                </table> 
                <br>
                <p>Informes: </p>
                <div>
                    <a href="<%=request.getContextPath()%>/#" class="btn btn-warning"><i class="icon-bar-chart"></i> Graficas DMA</a>
                    <a href="<%=request.getContextPath()%>/#informeDMA" class="btn btn-warning"><i class="icon-bar-chart"></i> Informe DMA</a>
                    <%--<a href="<%=request.getContextPath()%>/#informeMatrizFactores" class="btn btn-warning"><i class="icon-bar-chart"></i>Matriz de calidad por factores</a>
                    <a href="<%=request.getContextPath()%>/#informeMatrizCaracteristicas" class="btn btn-warning"><i class="icon-bar-chart">  </i>Matriz de calidad por caracter&iacute;sticas</a>
                    <a  href="<%=request.getContextPath()%>/#resultadosGenerales"><i class="icon-bar-chart"></i> Resultados Generales</a>-->

                    <div class="btn-group">
                        <a class="btn btn-warning" href="<%=request.getContextPath()%>/#encuestaAleatoria"><i class="icon-random"></i> Ver al azar encuesta respondida</a>
                        <button type="button" class="btn btn-warning dropdown-toggle" data-toggle="dropdown">
                            <span class="caret"></span>
                        </button>
                        <ul class="dropdown-menu">
                            <li><a href="#encuestaXaleatoria&1">de un estudiante</a></li>
                            <li><a href="#encuestaXaleatoria&2">de un docente</a></li>
                            <li><a href="#encuestaXaleatoria&4">de un egresado</a></li>
                            <li><a href="#encuestaXaleatoria&3">de un administrativo</a></li>
                            <li><a href="#encuestaXaleatoria&5">de un director de programa</a></li>
                            <li><a href="#encuestaXaleatoria&6">de un empleador</a></li>
                        </ul>
                    </div>
                    --%>
                </div>
                <br>
                <p>Estado general del proceso:</p>
                <table class="table table-striped table-bordered table-condensed">
                    <thead>
                    <th>Número total de muestra</th>
                    <th>Número de personas que han evaluado las encuestas</th>
                    <th>Porcentaje de personas que han evaluado las encuestas</th>
                    <th>Número de personas que faltan por evaluar las encuestas</th>
                    <th>Porcentaje de personas que faltan por evaluar las encuestas</th>
                    </thead>
                    <tbody>
                        <tr>  
                            <td>   
                                <c:out value="${totalMuestraX}"/>
                            </td>
                            <td>   
                                <c:out value="${terminadosX}"/>
                            </td>
                            <td>   
                                <fmt:formatNumber type="number" maxFractionDigits="2" value="${terminadosX*100/totalMuestraX}"/>%
                            </td>
                            <td>   
                                <c:out value="${totalMuestraX-terminadosX}"/>
                            </td>
                            <td>   
                                <fmt:formatNumber type="number" maxFractionDigits="2" value="${100-(terminadosX*100/totalMuestraX)}"/>%
                            </td>
                        </tr>


                    </tbody>
                </table><br>
                <p>Estado por fuente del proceso:</p>
                <table class="table table-striped table-bordered table-condensed">
                    <thead>
                    <th>Fuente</th>
                    <th>Número total de muestra</th>
                    <th>Número de personas que han evaluado las encuestas</th>
                    <th>Porcentaje de personas que han evaluado las encuestas</th>
                    <th>Número de personas que faltan por evaluar las encuestas</th>
                    <th>Porcentaje de personas que faltan por evaluar las encuestas</th>
                    </thead>
                    <tbody>
                        <tr>
                            <td>
                                ESTUDIANTES PREGRADO
                            </td>
                            <td>
                                ${totalEst}
                            </td>
                            <td>
                                ${terminadosEst}
                            </td>
                            <td>   
                                <c:choose>
                                    <c:when test="${totalEst!=0}">
                                        <fmt:formatNumber type="number" maxFractionDigits="2" value="${terminadosEst*100/totalEst}"/>%
                                    </c:when>
                                    <c:otherwise>
                                        0%
                                    </c:otherwise>
                                </c:choose>
                
                            </td>
                            <td>   
                                <c:out value="${totalEst-terminadosEst}"/>
                            </td>
                            <td>   
                                <c:choose>
                                    <c:when test="${totalEst!=0}">
                                        <fmt:formatNumber type="number" maxFractionDigits="2" value="${100-(terminadosEst*100/totalEst)}"/>%
                                    </c:when>
                                    <c:otherwise>
                                        0%
                                    </c:otherwise>
                                </c:choose>

                            </td>
                        </tr>
                        <tr>
                            <td>
                                ESTUDIANTES ESPECIALIZACIÓN
                            </td>
                            <td>
                                ${totalEstEsp}
                            </td>
                            <td>
                                ${terminadosEstEsp}
                            </td>
                            <td>   
                                <c:choose>
                                    <c:when test="${totalEstEsp!=0}">
                                        <fmt:formatNumber type="number" maxFractionDigits="2" value="${terminadosEstEsp*100/totalEstEsp}"/>%
                                    </c:when>
                                    <c:otherwise>
                                        0%
                                    </c:otherwise>
                                </c:choose>

                            </td>
                            <td>   
                                <c:out value="${totalEstEsp-terminadosEstEsp}"/>
                            </td>
                            <td>   
                                <c:choose>
                                    <c:when test="${totalEstEsp!=0}">
                                        <fmt:formatNumber type="number" maxFractionDigits="2" value="${100-(terminadosEstEsp*100/totalEstEsp)}"/>%
                                    </c:when>
                                    <c:otherwise>
                                        0%
                                    </c:otherwise>
                                </c:choose>

                            </td>
                        </tr>
                        <tr>
                            <td>
                                ESTUDIANTES MAESTRIA
                            </td>
                            <td>
                                ${totalEstMae}
                            </td>
                            <td>
                                ${terminadosEstMae}
                            </td>
                            <td>   
                                <c:choose>
                                    <c:when test="${totalEstMae!=0}">
                                        <fmt:formatNumber type="number" maxFractionDigits="2" value="${terminadosEstMae*100/totalEstMae}"/>%
                                    </c:when>
                                    <c:otherwise>
                                        0%
                                    </c:otherwise>
                                </c:choose>

                            </td>
                            <td>   
                                <c:out value="${totalEstMae-terminadosEstMae}"/>
                            </td>
                            <td>   
                                <c:choose>
                                    <c:when test="${totalEstMae!=0}">
                                        <fmt:formatNumber type="number" maxFractionDigits="2" value="${100-(terminadosEstMae*100/totalEstMae)}"/>%
                                    </c:when>
                                    <c:otherwise>
                                        0%
                                    </c:otherwise>
                                </c:choose>

                            </td>
                        </tr>
                        <tr>
                            <td>
                                DOCENTES PLANTA
                            </td>
                            <td>
                                ${totalDoc}
                            </td>
                            <td>
                                ${terminadosDoc}
                            </td>
                            <td>   
                                <c:choose>
                                    <c:when test="${totalDoc!=0}">
                                        <fmt:formatNumber type="number" maxFractionDigits="2" value="${terminadosDoc*100/totalDoc}"/>%
                                    </c:when>
                                    <c:otherwise>
                                        0%
                                    </c:otherwise>
                                </c:choose>

                            </td>
                            <td>   
                                <c:out value="${totalDoc-terminadosDoc}"/>
                            </td>
                            <td>   
                                <c:choose>
                                    <c:when test="${totalDoc!=0}">
                                        <fmt:formatNumber type="number" maxFractionDigits="2" value="${100-(terminadosDoc*100/totalDoc)}"/>%
                                    </c:when>
                                    <c:otherwise>
                                        0%
                                    </c:otherwise>
                                </c:choose>

                            </td>
                        </tr>
                        <tr>
                            <td>
                                DOCENTES CATEDRA
                            </td>
                            <td>
                                ${totalDocCat}
                            </td>
                            <td>
                                ${terminadosDocCat}
                            </td>
                            <td>   
                                <c:choose>
                                    <c:when test="${totalDocCat!=0}">
                                        <fmt:formatNumber type="number" maxFractionDigits="2" value="${terminadosDocCat*100/totalDocCat}"/>%
                                    </c:when>
                                    <c:otherwise>
                                        0%
                                    </c:otherwise>
                                </c:choose>

                            </td>
                            <td>   
                                <c:out value="${totalDocCat-terminadosDocCat}"/>
                            </td>
                            <td>   
                                <c:choose>
                                    <c:when test="${totalDocCat!=0}">
                                        <fmt:formatNumber type="number" maxFractionDigits="2" value="${100-(terminadosDocCat*100/totalDocCat)}"/>%
                                    </c:when>
                                    <c:otherwise>
                                        0%
                                    </c:otherwise>
                                </c:choose>

                            </td>
                        </tr>
                        <tr>
                            <td>
                                ADMINISTRATIVOS
                            </td>
                            <td>
                                ${totalAdm}
                            </td>
                            <td>
                                ${terminadosAdm}
                            </td>
                            <td>   
                                <c:choose>
                                    <c:when test="${totalAdm!=0}">
                                        <fmt:formatNumber type="number" maxFractionDigits="2" value="${terminadosAdm*100/totalAdm}"/>%
                                    </c:when>
                                    <c:otherwise>
                                        0%
                                    </c:otherwise>
                                </c:choose>

                            </td>
                            <td>   
                                <c:out value="${totalAdm-terminadosAdm}"/>
                            </td>
                            <td>   
                                <c:choose>
                                    <c:when test="${totalAdm!=0}">
                                        <fmt:formatNumber type="number" maxFractionDigits="2" value="${100-(terminadosAdm*100/totalAdm)}"/>%
                                    </c:when>
                                    <c:otherwise>
                                        0%
                                    </c:otherwise>
                                </c:choose>

                            </td>

                        </tr>
                        <tr>
                            <td>
                                DIRECTIVOS
                            </td>
                            <td>
                                ${totalDir}
                            </td>
                            <td>
                                ${terminadosDir}
                            </td>
                            <td>   
                                <c:choose>
                                    <c:when test="${totalDir!=0}">
                                        <fmt:formatNumber type="number" maxFractionDigits="2" value="${terminadosDir*100/totalDir}"/>%
                                    </c:when>
                                    <c:otherwise>
                                        0%
                                    </c:otherwise>
                                </c:choose>

                            </td>
                            <td>   
                                <c:out value="${totalDir-terminadosDir}"/>
                            </td>
                            <td>   
                                <c:choose>
                                    <c:when test="${totalDir!=0}">
                                        <fmt:formatNumber type="number" maxFractionDigits="2" value="${100-(terminadosDir*100/totalDir)}"/>%
                                    </c:when>
                                    <c:otherwise>
                                        0%
                                    </c:otherwise>
                                </c:choose>

                            </td>

                        </tr>
                        <tr>
                            <td>
                                EGRESADOS PREGRADO
                            </td>
                            <td>
                                ${totalEgr}
                            </td>
                            <td>
                                ${terminadosEgr}
                            </td>
                            <td>   
                                <c:choose>
                                    <c:when test="${totalEgr!=0}">
                                        <fmt:formatNumber type="number" maxFractionDigits="2" value="${terminadosEgr*100/totalEgr}"/>%
                                    </c:when>
                                    <c:otherwise>
                                        0%
                                    </c:otherwise>
                                </c:choose>

                            </td>
                            <td>   
                                <c:out value="${totalEgr-terminadosEgr}"/>
                            </td>
                            <td>   
                                <c:choose>
                                    <c:when test="${totalEgr!=0}">
                                        <fmt:formatNumber type="number" maxFractionDigits="2" value="${100-(terminadosEgr*100/totalEgr)}"/>%
                                    </c:when>
                                    <c:otherwise>
                                        0%
                                    </c:otherwise>
                                </c:choose>

                            </td>

                        </tr>
                        <tr>
                            <td>
                                EGRESADOS ESPECIALIZACIÓN
                            </td>
                            <td>
                                ${totalEgrEsp}
                            </td>
                            <td>
                                ${terminadosEgrEsp}
                            </td>
                            <td>   
                                <c:choose>
                                    <c:when test="${totalEgrEsp!=0}">
                                        <fmt:formatNumber type="number" maxFractionDigits="2" value="${terminadosEgrEsp*100/totalEgrEsp}"/>%
                                    </c:when>
                                    <c:otherwise>
                                        0%
                                    </c:otherwise>
                                </c:choose>

                            </td>
                            <td>   
                                <c:out value="${totalEgrEsp-terminadosEgrEsp}"/>
                            </td>
                            <td>   
                                <c:choose>
                                    <c:when test="${totalEgrEsp!=0}">
                                        <fmt:formatNumber type="number" maxFractionDigits="2" value="${100-(terminadosEgrEsp*100/totalEgrEsp)}"/>%
                                    </c:when>
                                    <c:otherwise>
                                        0%
                                    </c:otherwise>
                                </c:choose>

                            </td>

                        </tr>
                        <tr>
                            <td>
                                EGRESADOS MAESTRIA
                            </td>
                            <td>
                                ${totalEgrMae}
                            </td>
                            <td>
                                ${terminadosEgrMae}
                            </td>
                            <td>   
                                <c:choose>
                                    <c:when test="${totalEgrMae!=0}">
                                        <fmt:formatNumber type="number" maxFractionDigits="2" value="${terminadosEgrMae*100/totalEgrMae}"/>%
                                    </c:when>
                                    <c:otherwise>
                                        0%
                                    </c:otherwise>
                                </c:choose>

                            </td>
                            <td>   
                                <c:out value="${totalEgrMae-terminadosEgrMae}"/>
                            </td>
                            <td>   
                                <c:choose>
                                    <c:when test="${totalEgrMae!=0}">
                                        <fmt:formatNumber type="number" maxFractionDigits="2" value="${100-(terminadosEgrMae*100/totalEgrMae)}"/>%
                                    </c:when>
                                    <c:otherwise>
                                        0%
                                    </c:otherwise>
                                </c:choose>

                            </td>

                        </tr>
                        <tr>
                            <td>
                                EMPLEADORES
                            </td>
                            <td>
                                ${totalEmp}
                            </td>
                            <td>
                                ${terminadosEmp}
                            </td>
                            <td>   
                                <c:choose>
                                    <c:when test="${totalEmp!=0}">
                                        <fmt:formatNumber type="number" maxFractionDigits="2" value="${terminadosEmp*100/totalEmp}"/>%
                                    </c:when>
                                    <c:otherwise>
                                        0%
                                    </c:otherwise>
                                </c:choose>

                            </td>
                            <td>   
                                <c:out value="${totalEmp-terminadosEmp}"/>
                            </td>
                            <td>   
                                <c:choose>
                                    <c:when test="${totalEmp!=0}">
                                        <fmt:formatNumber type="number" maxFractionDigits="2" value="${100-(terminadosEmp*100/totalEmp)}"/>%
                                    </c:when>
                                    <c:otherwise>
                                        0%
                                    </c:otherwise>
                                </c:choose>

                            </td>
                        </tr>
                    <div id="container" style="height: 500px; margin: 0 auto" class="span10"></div>             
                    <br>
                    </tbody>
                </table>
            </fieldset>
        </div>
    </div>
</div>                        
