<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <link href="css/layout-sitenav.css" type="text/css" rel="stylesheet" media="screen">
        <title></title>
        <link href="http://fonts.googleapis.com/css?family=Lobster|Oswald|Kaushan+Script" rel="stylesheet" type="text/css">
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width">
        <link href="css/slider.css" rel="stylesheet" type="text/css" />
        <link rel="stylesheet" href="css/bootstrap.css">
        <link rel="stylesheet" href="css/main.css">
        <link rel="stylesheet" href="css/font-awesome.min.css">
        <link rel="stylesheet" href="css/bootstrap-responsive.min.css">
        <script src="js/vendor/modernizr-2.6.2-respond-1.1.0.min.js"></script>
        <style>
            .table th, .table td {
                //padding: 4px;
            }
        </style>
    </head>
    <body style="padding-top: 0px;">
        <br>
        <div class="hero-unit">
            <div style="margin-left: -30px;">
                <div id="conte" class="span12" style="text-align: justify">
                    <table class="table table-bordered table-striped" style="font-weight: bold;">
                        <tbody>
                            <tr>
                                <td rowspan="3" style="width: 25%; text-align: center;"><img src="<%=request.getContextPath()%>/img/LogoU.png"></td>
                                <td style="width: 50%; text-align: center;">Universidad Tecnológica de Bolívar</td>
                                <td style="width: 25%;">CÓDIGO: FO-DO/004</td>
                            </tr>
                            <tr>
                                <td style="width: 50%; text-align: center;">AUTOEVALUACIÓN Y ACREDITACIÓN</td>
                                <td>VERSIÓN:00</td>
                            </tr>
                            <tr>
                                <td style="width: 50%; text-align: center;">PLAN DE MEJORAMIENTO</td>
                                <td>FECHA:07/04/2012</td>
                            </tr>
                        </tbody>
                    </table>


                    <c:forEach items="${Proceso.hallazgoList}" var="item">
                        <c:choose>
                            <c:when test="${item.tipo =='hallazgo'}">
                                <table class="table table-bordered table-striped">
                                    <tbody>
                                        <tr>
                                            <td colspan="3"></td>   
                                        </tr>
                                        <tr>
                                            <td><strong>Programa:</strong></td>
                                            <td colspan="2">${Proceso.programaId.nombre}</td>   
                                        </tr>
                                        <tr>
                                            <td><strong>Factor:</strong></td>
                                            <td colspan="2">${item.caracteristicaId.factorId.codigo} - ${item.caracteristicaId.factorId.nombre}</td>   
                                        </tr>
                                        <tr>
                                            <td><strong>Caracteristica:</strong></td>
                                            <td colspan="2">${item.caracteristicaId.codigo} - ${item.caracteristicaId.nombre}</td>   
                                        </tr>
                                        <tr>
                                            <td><strong>Hallazgo:</strong></td>
                                            <td colspan="2">${item.hallazgo}</td>   
                                        </tr>
                                    </tbody>
                                </table>
                                <table class="table table-bordered table-striped">
                                    <thead>
                                        <tr>
                                            <th colspan="8" style="text-align: center;">Formulación</th>   
                                        </tr>
                                        <tr>
                                            <th>Objetivo</th>   
                                            <th>Meta</th>   
                                            <th>Estrategia</th>   
                                            <th>Indicador de Cumplimiento</th>   
                                            <th>Inicio</th>   
                                            <th>Final</th>   
                                            <th>Responsables</th>   
                                            <th>Recursos o Financiaci&oacute;n</th>   
                                        </tr>
                                    </thead>
                                    <tbody>


                                        <!--objetivos-->
                                        <c:choose>
                                            <c:when test="${item.objetivosList.size()==0}">
                                                <tr>
                                                    <td colspan="8">No Existen registros hasta el momento</td>
                                                </tr>    
                                            </c:when>
                                        </c:choose>
                                        <c:forEach items="${item.objetivosList}" var="item2">
                                            <tr>
                                                <c:choose>
                                                    <c:when test="${item2.metasList.size() == 0}">
                                                        <!--no tiene metas-->
                                                        <td>${item2.objetivo}</td>
                                                        <td colspan="7"></td>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <td rowspan="${item2.metasList.size()}">${item2.objetivo}</td>
                                                        <c:forEach items="${item2.metasList}" var="item3" varStatus="status">
                                                            <c:choose>
                                                                <c:when test="${status.index!=0}">
                                                                <tr>    
                                                                </c:when>
                                                            </c:choose>
                                                            <td>
                                                                ${item3.meta}
                                                            </td>
                                                            <td>
                                                                ${item3.estrategia}
                                                            </td>
                                                            <td>
                                                                ${item3.indicadorCumplimiento}
                                                            </td>
                                                            <td>
                                                                <fmt:formatDate pattern='dd/MM/yyyy' value='${item3.fechaInicio}' />
                                                            </td>
                                                            <td>
                                                                <fmt:formatDate pattern='dd/MM/yyyy' value='${item3.fechaFinal}' />
                                                            </td>
                                                            <td>
                                                                ${item3.responsable}
                                                            </td>
                                                            <td>
                                                                ${item3.financiacion}
                                                            </td>
                                                        </tr>
                                                    </c:forEach>
                                                </c:otherwise>
                                            </c:choose>

                                        </c:forEach>
                                    </tbody>
                                </table>
                            </c:when>
                        </c:choose>
                    </c:forEach>

                </div>
            </div>
        </div>
    </body>
</html>