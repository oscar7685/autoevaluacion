<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<style type="text/css">

    .inicial td {
        text-align: right;
    }
</style>
<script type="text/javascript">
    $(function () {
    $('.tool').tooltip().click(function(e){
    $(this).tooltip('hide');
    });
    var chart;
    $(document).ready(function() {
    chart = new Highcharts.Chart({
    chart: {
    renderTo: 'grafica',
            type: 'column',
            margin: [ 50, 30, 100, 50]
    },
            title: {
            text: '${caracteristicasDF.get(0).getFactorId().nombre}'
            },
            xAxis: {
            categories: [
    <c:forEach items="${caracteristicasDF}" var="caracteristica2" varStatus="status2">
        <fmt:parseNumber var="cum4"  value="${cumplimientoDF[status2.index]}" />
        <c:choose>
            <c:when test="${cum4>0}">
                <c:choose>
                    <c:when test="${caracteristicasDF.size()!=status.index+1}">
            '${caracteristica2.codigo}-${caracteristica2.nombre}',</c:when><c:otherwise>
                        '${caracteristica2.codigo}-${caracteristica2.nombre}'
                    </c:otherwise>
                </c:choose>
            </c:when>
        </c:choose>


    </c:forEach>
                                    ],
                                            labels: {
                                            formatter: function() {
                                            var partes = this.value.split("-");
                                            return "" + partes[0];
                                            },
                                                    rotation: - 45,
                                                    align: 'right',
                                                    style: {
                                                    fontSize: '12px',
                                                            fontFamily: 'Verdana, sans-serif'
                                                    }
                                            }
                                    },
                                    plotOptions: {
                                    series: {
                                    cursor: 'pointer',
                                            point: {
                                            events: {
                                            click: function() {
                                            var partes2 = this.category.split("-");
                                            var a = $("a[data='" + partes2[1] + "']");
                                            location = a.attr("href");
                                            }
                                            }
                                            }
                                    }
                                    },
                                    yAxis: {
                                    min: 0,
                                            max: 100,
                                            title: {
                                            text: 'Grado de cumplimiento'
                                            }
                                    },
                                    legend: {
                                    enabled: false
                                    },
                                    tooltip: {
                                    formatter: function() {
                                    return '<b>' + this.x + '</b><br/>' +
                                            'Cumplimiento: ' + Highcharts.numberFormat(this.y, 1) +
                                            '';
                                    }
                                    },
                                    series: [{
                                    name: 'Caracteristicas',
                                            data: [
  <c:forEach items="${caracteristicas}" var="caracteristica4" varStatus="status33">
        <fmt:parseNumber var="cum5"  value="${cumplimiento[status33.index]}" />
        <c:choose>
            <c:when test="${cum5>0}">
                <c:choose>
                    <c:when test="${caracteristicas.size()!=status33.index+1}">
                        <c:choose>
                            <c:when test="${cumplimiento[status33.index]>=90.0}">
                                            {
                                            y: ${cumplimiento[status33.index]},
                                                    color: '#89A54E'
                                            },</c:when>
                            <c:when test="${cumplimiento[status33.index]<90.0 && cumplimiento[status33.index]>=80.0}">
                                            {
                                            y: ${cumplimiento[status33.index]},
                                                    color: '#80699B'
                                            },</c:when>
                            <c:when test="${cumplimiento[status33.index]<80.0 && cumplimiento[status33.index]>=60.0}">
                                            {
                                            y: ${cumplimiento[status33.index]},
                                                    color: '#3D96AE'
                                            },</c:when>
                            <c:when test="${cumplimiento[status33.index]<60.0 && cumplimiento[status33.index]>=40.0}">
                                            {
                                            y: ${cumplimiento[status33.index]},
                                                    color: '#DB843D'
                                            },</c:when>                            <c:otherwise>
                                            {
                                            y: ${cumplimiento[status33.index]},
                                                    color: '#AA4643'
                                            },</c:otherwise>                        </c:choose>






                    </c:when>
                    <c:otherwise>
                        <c:choose>
                            <c:when test="${cumplimiento[status33.index]>=90.0}">
                                            {
                                            y: ${cumplimiento[status33.index]},
                                                    color: '#89A54E'
                                            }

                            </c:when>
                            <c:when test="${cumplimiento[status33.index]<90.0 && cumplimiento[status33.index]>=80.0}">
                                            {
                                            y: ${cumplimiento[status33.index]},
                                                    color: '#80699B'
                                            }

                            </c:when>
                            <c:when test="${cumplimiento[status33.index]<80.0 && cumplimiento[status33.index]>=60.0}">
                                            {
                                            y: ${cumplimiento[status33.index]},
                                                    color: '#3D96AE'
                                            }

                            </c:when>
                            <c:when test="${cumplimiento[status33.index]<60.0 && cumplimiento[status33.index]>=40.0}">
                                            {
                                            y: ${cumplimiento[status33.index]},
                                                    color: '#DB843D'
                                            }

                            </c:when>
                            <c:otherwise>
                                            {
                                            y: ${cumplimiento[status33.index]},
                                                    color: '#AA4643'
                                            }
                            </c:otherwise>
                        </c:choose>
                    </c:otherwise>
                </c:choose>
            </c:when>
        </c:choose>


    </c:forEach>


                                            ],
                                            dataLabels: {
                                            enabled: true,
                                                    rotation: - 90,
                                                    color: '#FFFFFF',
                                                    align: 'right',
                                                    x: - 3,
                                                    y: 10,
                                                    formatter: function() {
                                                    return this.y;
                                                    },
                                                    style: {
                                                    fontSize: '13px',
                                                            fontFamily: 'Verdana, sans-serif'
                                                    }
                                            }
                                    }]
                            });
                            });
                            });
</script>
<div class="hero-unit">
    <div class="row">
        <div id="conte" class="span10">
            <legend>Factor: ${factor.nombre}</legend>
            <ul class="breadcrumb">
                <li><a href="<%=request.getContextPath()%>/#informeMatrizFactores">Matriz de Calidad de Factores</a> <span class="divider">/</span></li>
                <li><a href="<%=request.getContextPath()%>/#informeMatrizCaracteristicas">Matriz de Calidad de Características</a> <span class="divider">/</span></li>
                <li class="active tool" data-placement="top" rel="tooltip" data-original-title="${factor.nombre}">Factor ${factor.codigo}</li>

            </ul>
            <br>
            <c:choose>
               <c:when test="${caracteristicas.size()!= 0}">

                    <table class="table table-striped table-bordered table-condensed inicial">
                        <thead>
                        <th>Caracteristica</th>
                        <th>Calificacion (CA)</th>
                        <th>Grado (CA)</th>
                        <th>Ponderacion dentro del factor</th>
                        </thead>
                        <tbody>
                            <c:set var="indice" value="0"></c:set>
                            <c:forEach items="${caracteristicas}" var="caracteristica" varStatus="iter">
                                <fmt:parseNumber var="cum"  value="${cumplimiento[iter.index]}" />
                                <c:choose>
                                    <c:when test="${cum>0}"> 
                                        <tr>
                                            <td style="text-align: left">   
                                                ${caracteristica.codigo}. ${caracteristica.nombre}
                                            </td>
                                            <td>   
                                                <fmt:formatNumber type="number" maxFractionDigits="1" value="${cumplimiento[iter.index] }"/>%
                                            </td>
                                            <td>   
                                                <c:if test="${cumplimiento[iter.index]  >= 90.0}"> A </c:if>        
                                                <c:if test="${cumplimiento[iter.index]  < 90.0 && cumplimiento[iter.index]  >=80.0}"> B </c:if>        
                                                <c:if test="${cumplimiento[iter.index]  < 80.0 && cumplimiento[iter.index]  >=60.0}"> C </c:if>        
                                                <c:if test="${cumplimiento[iter.index]  < 60.0 && cumplimiento[iter.index]  >=40.0}"> D </c:if>        
                                                <c:if test="${cumplimiento[iter.index]  < 40.0}"> E </c:if>        
                                                </td>
                                                <td>   
                                                ${ponderacionesC.get(indice).ponderacion} %
                                            </td>
                                        </tr>
                                        <c:set var="indice" value="${indice+1}"></c:set>
                                    </c:when>
                                </c:choose>

                            </c:forEach>
                        </tbody>
                    </table>
                    <br/>
                    <div id="grafica" style="height: 500px; margin: 0 auto" class="span10">

                    </div>
                   <table class="table">
                            <thead>
                                <tr>
                                    <th>Escala</th>
                                    <th>Descripci&oacute;n</th>
                                    <th>Grado de cumplimiento</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr style="background-color: #89A54E;">
                                    <td>
                                        A
                                    </td>
                                    <td>
                                        Se cumple plenamente
                                    </td>
                                    <td>
                                        90% a 100%
                                    </td>
                                </tr>
                                <tr style="background-color: #80699B;">
                                    <td>
                                        B
                                    </td>
                                    <td>
                                        Se cumple en alto grado
                                    </td>
                                    <td>
                                        80% a 89%
                                    </td>
                                </tr>
                                <tr style="background-color: #3D96AE;">
                                    <td>
                                        C
                                    </td>
                                    <td>
                                        Se cumple en aceptablemente
                                    </td>
                                    <td>
                                        60% a 79%
                                    </td>
                                </tr>
                                <tr style="background-color: #DB843D;">
                                    <td>
                                        D
                                    </td>
                                    <td>
                                        Se cumple insatisfactoriamente
                                    </td>
                                    <td>
                                        40% - 59%
                                    </td>
                                </tr>
                                <tr style="background-color: #AA4643;">
                                    <td>
                                        E
                                    </td>
                                    <td>
                                        No se cumple
                                    </td>
                                    <td>
                                        0% - 39%
                                    </td>
                                </tr>

                            </tbody>
                        </table>
                </c:when>
                <c:otherwise>
                    No Existen Hay datos Registrados en el Sistema.
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>    
