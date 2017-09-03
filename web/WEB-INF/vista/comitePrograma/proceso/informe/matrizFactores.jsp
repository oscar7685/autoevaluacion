<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<style type="text/css">

    .inicial td {
        text-align: right;
    }
</style>
<script type="text/javascript">
    var chart;
    $(document).ready(function() {
    chart = new Highcharts.Chart({
    chart: {
    renderTo: 'grafica',
            type: 'column',
            margin: [ 50, 30, 100, 50]
    },
            title: {
            text: 'Matriz de calidad de factores'
            },
            xAxis: {
            categories: [
    <c:forEach items="${factores}" var="factorG" varStatus="status">
        <fmt:parseNumber var="cum3"  value="${cumplimientoF[status.index]}" />
        <c:choose>
            <c:when test="${cum3>0}">
                <c:choose>
                    <c:when test="${factores.size()!=status.index+1}">
            '${factorG.codigo}-${factorG.nombre}',</c:when><c:otherwise>
                        '${factorG.codigo}-${factorG.nombre}'
                    </c:otherwise>
                </c:choose>
            </c:when>
        </c:choose>


    </c:forEach>
                                    ],
                                            labels: {
                                            formatter: function() {
                                            var partes = this.value.split("-");
                                            return "Factor " + partes[0];
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
                                    name: 'Factores',
                                            data: [
    <c:forEach items="${factores}" var="fact" varStatus="status">
        <fmt:parseNumber var="cum4"  value="${cumplimientoF[status.index]}" />
        <c:choose>
            <c:when test="${cum4>0}">
                <c:choose>
                    <c:when test="${factores.size()!=status.index+1}">
                        <c:choose>
                            <c:when test="${cumplimientoF[status.index]>=90.0}">
                                            {
                                            y: ${cumplimientoF[status.index]},
                                                    color: '#89A54E'
                                            },</c:when>
                            <c:when test="${cumplimientoF[status.index]<90.0 && cumplimientoF[status.index]>=80.0}">
                                            {
                                            y: ${cumplimientoF[status.index]},
                                                    color: '#80699B'
                                            },</c:when>
                            <c:when test="${cumplimientoF[status.index]<80.0 && cumplimientoF[status.index]>=60.0}">
                                            {
                                            y: ${cumplimientoF[status.index]},
                                                    color: '#3D96AE'
                                            },</c:when>
                            <c:when test="${cumplimientoF[status.index]<60.0 && cumplimientoF[status.index]>=40.0}">
                                            {
                                            y: ${cumplimientoF[status.index]},
                                                    color: '#DB843D'
                                            },</c:when><c:otherwise>
                                            {
                                            y: ${cumplimientoF[status.index]},
                                                    color: '#AA4643'
                                            },</c:otherwise></c:choose></c:when>
                    <c:otherwise>
                        <c:choose>
                            <c:when test="${cumplimientoF[status.index]>=90.0}">
                                            {
                                            y: ${cumplimientoF[status.index]},
                                                    color: '#89A54E'
                                            }

                            </c:when>
                            <c:when test="${cumplimientoF[status.index]<90.0 && cumplimientoF[status.index]>=80.0}">
                                            {
                                            y: ${cumplimientoF[status.index]},
                                                    color: '#80699B'
                                            }

                            </c:when>
                            <c:when test="${cumplimientoF[status.index]<80.0 && cumplimientoF[status.index]>=60.0}">
                                            {
                                            y: ${cumplimientoF[status.index]},
                                                    color: '#3D96AE'
                                            }

                            </c:when>
                            <c:when test="${cumplimientoF[status.index]<60.0 && cumplimientoF[status.index]>=40.0}">
                                            {
                                            y: ${cumplimientoF[status.index]},
                                                    color: '#DB843D'
                                            }

                            </c:when>
                            <c:otherwise>
                                            {
                                            y: ${cumplimientoF[status.index]},
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
                                                    x: 4,
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


</script>
<div class="hero-unit">
    <div class="row">
        <div id="conte" class="span10">
            <legend>Matriz de Calidad de Factores</legend>
            <ul class="breadcrumb">
                <li class="active">Matriz de Calidad de Factores  <span class="divider">/</span></li>
                <li><a href="<%=request.getContextPath()%>/#informeMatrizCaracteristicas">Matriz de Calidad de Características</a></li>
            </ul>
            <br>
            <c:choose>
                <c:when test="${factores.size()!= 0}">

                    <table class="table table-striped table-bordered table-condensed inicial">
                        <thead>
                        <th>Factor</th>
                        <th>Ponderacion Factor</th>
                        <th>Calificación del factor</th>
                        <th>Grado</th>
                        </thead>
                        <tbody>
                            <c:set var="indice2" value="0"></c:set>
                            <c:set var="final" value="0"></c:set>
                            <c:set var="ponderaciones" value="0"></c:set>
                            <c:forEach items="${factores}" var="factor" varStatus="iter2">
                                <fmt:parseNumber var="cum2"  value="${cumplimientoF[iter2.index]}" />
                                <c:choose>
                                    <c:when test="${cum2>0}"> 
                                        <tr>
                                            <td style="text-align: left">   
                                                <a href="#detalleFactor&${factor.getId()}" data="${factor.nombre}">${factor.nombre}</a>
                                            </td>
                                            <td>   
                                                ${ponderacionesF.get(indice2).ponderacion} %
                                            </td>
                                            <td>   
                                                <fmt:formatNumber type="number" maxFractionDigits="1" value="${cumplimientoF[iter2.index]}"/>%
                                            </td>
                                            <td>   
                                                <c:if test="${cumplimientoF[iter2.index]  >= 90.0}"> A </c:if>        
                                                <c:if test="${cumplimientoF[iter2.index]  < 90.0 && cumplimientoF[iter2.index]  >=80.0}"> B </c:if>        
                                                <c:if test="${cumplimientoF[iter2.index]  < 80.0 && cumplimientoF[iter2.index]  >=60.0}"> C </c:if>        
                                                <c:if test="${cumplimientoF[iter2.index]  < 60.0 && cumplimientoF[iter2.index]  >=40.0}"> D </c:if>        
                                                <c:if test="${cumplimientoF[iter2.index]  < 40.0}"> E </c:if>        
                                                </td>
                                            </tr>
                                        <c:set var="final" value="${final+((ponderacionesF.get(indice2).ponderacion)*(cumplimientoF[iter2.index]))}"></c:set>
                                        <c:set var="ponderaciones" value="${ponderaciones+ponderacionesF.get(indice2).ponderacion}"></c:set>
                                        <c:set var="indice2" value="${indice2+1}"></c:set>

                                    </c:when>
                                </c:choose>       
                            </c:forEach>
                        </tbody>
                        <tfoot>
                        <td style="text-align: left;font-weight: bold;">   
                            Total
                        </td>            
                        <td>   
                          ${ponderaciones} %  
                        </td>

                        <td>   
                            <fmt:formatNumber type="number" maxFractionDigits="1" value="${(final/ponderaciones)}"/> %
                        </td>

                        <td>   
                            <c:if test="${(final/ponderaciones) >= 90.0}"> A </c:if>        
                            <c:if test="${(final/ponderaciones) < 90.0 && (final/ponderaciones) >=80.0}"> B </c:if>        
                            <c:if test="${(final/ponderaciones) < 80.0 && (final/ponderaciones) >=60.0}"> C </c:if>        
                            <c:if test="${(final/ponderaciones) < 60.0 && (final/ponderaciones) >=40.0}"> D </c:if>        
                            <c:if test="${(final/ponderaciones) < 40.0}"> E </c:if>        
                            </td>
                            </tfoot>
                        </table>
                        <br/>          
                        <div id="grafica" style="height: 500px; margin: 0 auto" class="span10"></div>

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

