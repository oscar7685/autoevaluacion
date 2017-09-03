<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
        <title>Autoevaluacion</title>
        <link href="http://fonts.googleapis.com/css?family=Lobster|Oswald|Kaushan+Script" rel="stylesheet" type="text/css">
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width">
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/layout2.css" />
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/font-awesome.min.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/otro.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/docs.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css">
        <link rel="stylesheet" href="https://cdn.datatables.net/1.10.11/css/jquery.dataTables.min.css">
        <link rel="stylesheet" href="https://cdn.datatables.net/buttons/1.1.2/css/buttons.dataTables.min.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap-responsive.min.css">
        <script src="<%=request.getContextPath()%>/js/vendor/modernizr-2.6.2-respond-1.1.0.min.js"></script>

    </head>
    <body>
        <!--[if lt IE 7]>
            <p class="chromeframe">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> or <a href="http://www.google.com/chromeframe/?redirect=true">activate Google Chrome Frame</a> to improve your experience.</p>
        <![endif]-->

        <div class="ui-layout-north ui-widget-content">
            <div class="navbar navbar-inverse navbar-fixed-top">
                <div class="navbar-inner">
                    <div class="container-fluid">
                        <a data-target=".nav-collapse" data-toggle="collapse" class="btn btn-navbar">
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </a>
                        <a class="brand" style="padding-top: 10px; padding-bottom: 5px;" href="#"><img src="img/LETRAS.png"/> 
                            <c:choose>
                                <c:when test="${Programa!= null}">
                                    ${Programa.getNombre()}    
                                </c:when>    
                            </c:choose>
                        </a>
                        <div class="nav-collapse collapse">
                            <ul class="nav barra" >
                                <li class="active"><a href="#inicio"><i class="icon-home"></i> Inicio</a></li>
                                <li class="dropdown loggining"> 
                                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                                        <i class="icon-user"></i> ${representante.nombre}
                                        <span class="caret"></span>
                                    </a>
                                    <ul class="dropdown-menu">
                                        <li><a href="#">Perfil</a></li>
                                        <li><a href="#contrasena">Cambiar Contrase&ntilde;a</a></li>
                                        <li class="divider"></li>
                                        <li><a href="<%=request.getContextPath()%>/#CerrarSesion">Cerrar Sesion</a></li>
                                    </ul>

                                </li>
                            </ul>
                        </div>
                        <!--/.nav-collapse -->
                    </div>
                </div>
            </div>        

        </div><!--North-->

        <div class="ui-layout-south ui-widget-content"> 
            <footer id="footer" class="contenedor_footer fondo_footer">
                <div class="links">
                    <a style="font-weight: normal;-moz-text-decoration-line: none;"><span class="muted">&copy; Universidad Tecnológica de Bolívar</span></a>
                    <a href="#" target="_blank"><span>Ayuda</span></a>
                </div>
            </footer>
        </div><!--South--><!--South-->

        <div class="ui-layout-center">

        </div><!--/Center-->

        <div id="ui-layout-west" class="ui-layout-west">
            <div id="menu0" class="ui-layout-content">
                <c:choose>
                    <c:when test="${EstadoProceso2 == 4}">
                        <div id="menu" style="padding: 8px 0pt;" class="well">
                            <ul class="nav nav-list">  
                                <button id="west-closer" class="close">&laquo;</button>
                                <li class="nav-header">Proceso de Autoevaluación</li>
                                <li><a href="#listarProceso"><i class="icon-reorder"></i> Listar Procesos</a></li>
                            </ul>
                        </div>
                    </c:when>
                    <c:when test="${EstadoProceso == 0}">
                        <div  align="center" class="alert alert-block">
                            <i class="icon-info-sign"></i> No existen proceso activos
                        </div>
                        <div id="menu" style="padding: 8px 0pt;" class="well">

                            <ul class="nav nav-list">  
                                <button id="west-closer" class="close">&laquo;</button>
                                <li class="nav-header">Proceso de Autoevaluación</li>
                                    <%-- <li><a href="#preparedCrearProceso"><i class="icon-plus"></i> Crear Proceso</a></li>--%>
                                <li><a href="#listarProceso"><i class="icon-reorder"></i> Listar Procesos</a></li>
                            </ul>
                        </div>
                    </c:when>
                    <c:when test="${EstadoProceso == 1}">
                        <div align="center" class="alert alert-info"><i class="icon-cog"></i> Proceso en configuración</div>
                        <div id="menu" style="padding: 8px 0pt;" class="well">
                            <ul class="nav nav-list">  
                                <button id="west-closer" class="close">&laquo;</button>
                                <li class="nav-header">Proceso de Autoevaluación</li>
                                <li><a href="#detalleProceso"><i class="icon-cogs"></i> Detalle de Proceso</a></li>
                                <li><a href="#listPonderacionFactor"><i class="icon-list-ol"></i> Ponderar Factores</a></li>
                                <li><a href="#listPonderacionCara2"><i class="icon-list-ol"></i> Ponderar Características</a></li>
                                <!-- <li><a href="#listPonderacionInd"><i class="icon-signal"></i> Ponderar Indicadores</a></li>-->
                                <li><a href="#selectorListMuestra"><i class="icon-group"></i> Asignar Muestra</a></li>
                            </ul>
                        </div>
                    </c:when>
                    <c:when test="${EstadoProceso == 2}">
                        <div align="center" class="alert alert-success"><i class="icon-play-sign"></i> Proceso en ejecución</div>
                        <div id="menu" style="padding: 8px 0pt;" class="well">
                            <ul class="nav nav-list">  
                                <button id="west-closer" class="close">&laquo;</button>
                                <li class="nav-header">Proceso de Autoevaluación</li>
                                <li><a href="#detalleProceso"><i class="icon-cogs"></i> Detalle de Proceso</a></li>
                                <li><a href="#listPonderacionFactor"><i class="icon-list"></i> Factores</a></li>
                                <li><a href="#listPonderacionCara2"><i class="icon-list"></i> Características</a></li>
                                <!--<li><a href="#listPonderacionInd"><i class="icon-list"></i> Indicadores</a></li>-->
                                <li><a href="#listEncuestas"><i class="icon-check"></i> Encuestas</a></li>
                                <li><a href="#selectorListMuestra"><i class="icon-group"></i> Muestra Asignada</a></li>
                                <li><a href="#calificarCaracteristicas"><i class="icon-check"></i> Evaluar Caracteristica</a></li>
                                <li><a href="#subirAdjunto"><i class="icon-file-alt"></i> Anexos</a></li>
                                    <c:choose>
                                        <c:when test="${abiertas == 'true'}">
                                        <li><a href="#cerrarPreguntas"><i class="icon-lock"></i> Cerrar preguntas</a></li>
                                        </c:when>
                                    </c:choose>
                                <li class="nav-header">Estado del proceso</li>
                                <li><a  id="informeEncuesta"  href="#informeDMA"><i class="icon-bar-chart"></i> Estado del proceso</a></li>
                            </ul>
                        </div>
                    </c:when>
                    <c:when test="${EstadoProceso == 3}">
                        <div align="center" class="alert alert-error"><i class="icon-play-sign"></i> Proceso finalizado</div>
                        <div id="menu" style="padding: 8px 0pt;" class="well">
                            <ul class="nav nav-list">  
                                <button id="west-closer" class="close">&laquo;</button>
                                <li class="nav-header">Proceso de Autoevaluación</li>
                                <li><a href="#detalleProceso"><i class="icon-cogs"></i> Detalle de Proceso</a></li>
                                <li><a href="#listPonderacionFactor"><i class="icon-list"></i> Factores</a></li>
                                <li><a href="#listPonderacionCara2"><i class="icon-list"></i> Características</a></li>
                                <!--<li><a href="#listPonderacionInd"><i class="icon-list"></i> Indicadores</a></li>-->
                                <li><a href="#selectorListMuestra"><i class="icon-group"></i> Muestra Asignada</a></li>
                                <li><a href="#calificarCaracteristicas"><i class="icon-check"></i> Evaluar Caracteristica</a></li>
                                <li><a href="#subirAdjunto"><i class="icon-file-alt"></i> Anexos</a></li>
                                <li><a  id="informeEncuesta"  href="<%=request.getContextPath()%>/#informeDMA"><i class="icon-bar-chart"></i> Estado del proceso</a></li>
                                <li class="divider"></li>
                                <li><a href="#listarProceso"><i class="icon-reorder"></i> Listar Procesos</a></li>
                            </ul>
                        </div>
                    </c:when>
                    <c:otherwise>
                    </c:otherwise>
                </c:choose>
            </div>
        </div><!--/West-->

        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
        <script>window.jQuery || document.write('<script src="<%=request.getContextPath()%>/js/vendor/jquery-1.9.1.min.js"><\/script>')</script>
        <script src="<%=request.getContextPath()%>/js/jquery.layout-latest.min.js"></script>
        <script src="<%=request.getContextPath()%>/js/jquery.validate.js"></script>
        <script src="<%=request.getContextPath()%>/js/jquery.metadata.js"></script>
        <script src="<%=request.getContextPath()%>/js/jquery.PrintArea.js"></script>
        <script src="<%=request.getContextPath()%>/js/vendor/bootstrap.min.js"></script>
        <script src="<%=request.getContextPath()%>/js/jquery.ba-hashchange.min.js"></script>
        <script src="<%=request.getContextPath()%>/js/highcharts.js"></script>
        <script src="<%=request.getContextPath()%>/js/highcharts-more.js"></script>
        <script src="<%=request.getContextPath()%>/js/exporting.js"></script>
        <script src="https://cdn.datatables.net/1.10.11/js/jquery.dataTables.min.js"></script>
        <script src="https://cdn.datatables.net/buttons/1.1.2/js/dataTables.buttons.min.js"></script>
        <script src="//cdn.datatables.net/buttons/1.1.2/js/buttons.flash.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jszip/2.5.0/jszip.min.js"></script>
        <script src="//cdn.rawgit.com/bpampuch/pdfmake/0.1.18/build/pdfmake.min.js"></script>
        <script src="//cdn.rawgit.com/bpampuch/pdfmake/0.1.18/build/vfs_fonts.js"></script>
        <script src="//cdn.datatables.net/buttons/1.1.2/js/buttons.html5.min.js"></script>
        <script src="//cdn.datatables.net/buttons/1.1.2/js/buttons.print.min.js "></script>
        <script src="<%=request.getContextPath()%>/js/main1.js"></script>




        <div class="modal hide fade" id="modalCp3">
            <div class="modal-header">
                <a data-dismiss="modal" class="close">×</a>
                <h3>Atención!</h3>
            </div>
            <div class="modal-body">
                <h4>Ponderación de Características.</h4>
                <br>
                <p>Debe ponderar los factores antes de proceder con la ponderación de características.</p>
            </div>
            <div class="modal-footer">
                <a class="btn btn-primary" data-dismiss="modal" href="#">Cerrar</a>
            </div>
        </div>
        <div class="modal hide fade" id="myModalID">
            <div class="modal-header">
                <a data-dismiss="modal" class="close">×</a>
                <h3>Atenci&oacute;n!</h3>
            </div>
            <div class="modal-body">
                <h4>Evaluaci&oacute;n informaci&oacute;n documental.</h4>
                <br>
                <p>La informaci&oacute;n documental se ha evaluado satisfactoriamente.</p>
            </div>
            <div class="modal-footer">
                <a class="btn btn-primary" data-dismiss="modal" href="#">Cerrar</a>
            </div>
        </div>
        <div class="modal hide fade" id="myModalCP">
            <div class="modal-header">
                <a data-dismiss="modal" class="close">×</a>
                <h3>Atenci&oacute;n!</h3>
            </div>
            <div class="modal-body">
                <h4>Cerrar preguntas.</h4>
                <br>
                <p>Las preguntas se ha cerrado satisfactoriamente.</p>
            </div>
            <div class="modal-footer">
                <a class="btn btn-primary" data-dismiss="modal" href="#">Cerrar</a>
            </div>
        </div>
        <div class="modal hide fade" id="myModalIN">
            <div class="modal-header">
                <a data-dismiss="modal" class="close">×</a>
                <h3>Atenci&oacute;n!</h3>
            </div>
            <div class="modal-body">
                <h4>Evaluaci&oacute;n informaci&oacute;n num&eacute;rica.</h4>
                <br>
                <p>La información numérica se ha evaluado satisfactoriamente .</p>
            </div>
            <div class="modal-footer">
                <a class="btn btn-primary" data-dismiss="modal" href="#">Cerrar</a>
            </div>
        </div>
    </body>
</html>

