<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<script type="text/javascript">
    var chart;
            var pregunta = "${titulo}".split(" ");
            var max = 70;
            var acom = 0;
            var finali = "";
            for (var i = 0; i < pregunta.length; i++){
    acom += pregunta[i].length;
            if (acom < max){
    finali += pregunta[i] + " ";
    } else{
    acom = 0;
            finali += "<br/>" + pregunta[i] + " ";
    }
    }
    $(function() {
    chart = new Highcharts.Chart({
    chart: {
    renderTo: 'container22',
            type: 'bar',
    },
            title: {
    text: "" + finali + "",
            margin: 60
    },
            xAxis: {
    categories: [
    <c:forEach items="${ejeY}" var="y" varStatus ="status">
    '${y}'
        <c:if test="${!status.last}">,</c:if></c:forEach>
    ]
    },
            yAxis: {
    min: 0,
            title: {
    text: '% DMA',
            align: 'high'
    },
            labels: {
    overflow: 'justify'
    }
    },
            tooltip: {
    formatter: function() {
    return '<b>' + this.x + '</b><br/>' +
            '% DMA ' + this.y +'';
    }
    },
            plotOptions: {
    bar: {
    dataLabels: {
    enabled: true
    }
    }
    },
            legend: {
    enabled: false
    },
            credits: {
    enabled: false
    },
            series: [{
    data: [
    <c:forEach items="${ejeY}" var="y" varStatus ="status">
    {
    y: ${ejeX.get(status.index)},
        <c:if test="${ejeX.get(status.index) >= 70.0}">color: '#468847' </c:if>        
        <c:if test="${ejeX.get(status.index) < 70.0 && ejeX.get(status.index) >= 50.0}">color: '#f0ad4e' </c:if> 
        <c:if test="${ejeX.get(status.index) < 50.0}">color: '#b94a48' </c:if> 
            }
        <c:if test="${!status.last}">,</c:if></c:forEach>
            ]
            }]
            });
            });
        </script>

        <div id="container22">

        </div>



<%--
<c:forEach items="${ejeY}" var="y" varStatus ="status">
    <br/>${y} : ${ejeX.get(status.index)} 
</c:forEach>
--%>