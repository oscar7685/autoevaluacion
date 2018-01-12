<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap-responsive.min.css">
        <link rel="stylesheet" href="https://cdn.datatables.net/1.10.11/css/jquery.dataTables.min.css">
        <link rel="stylesheet" href="https://cdn.datatables.net/buttons/1.1.2/css/buttons.dataTables.min.css">
        <style type="text/css">
            .table-condensed th,
            .table-condensed td {
                padding: 1px 5px;
            }
        </style>
        <script src="<%=request.getContextPath()%>/js/vendor/modernizr-2.6.2-respond-1.1.0.min.js"></script>

    </head>
    <body>


        <div class="ui-layout-north ui-widget-content">
            <div class="navbar navbar-inverse navbar-fixed-top">
                <div class="navbar-inner">
                    <div class="container-fluid">
                        <a data-target=".nav-collapse" data-toggle="collapse" class="btn btn-navbar">
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </a>
                        <a class="brand" style="padding-top: 10px; padding-bottom: 5px;" href="#"><img src="img/LETRAS.png"/></a>
                        <div class="nav-collapse collapse">
                            <ul class="nav barra" >
                                <li class="active"><a href="#inicio"><i class="icon-home"></i> Inicio</a></li>
                                <li class="dropdown loggining"> 
                                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                                        <i class="icon-user"></i> ${nombre}
                                        <span class="caret"></span>
                                    </a>
                                    <ul class="dropdown-menu">
                                        <li><a target="_blank" href="DescargarLog">Descargar Log</a></li>
                                        <li><a href="#">Cambiar Contrase&ntilde;a</a></li>
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
        </div><!--South-->

        <div class="ui-layout-center">
            <div id="conte" class="span12" style="text-align: justify">
                <div class="hero-unit">
                    <h2>Autoevaluación UNIVERSIDAD TECNOLÓGICA DE BOLÍVAR</h2>
                    <p style='font-size: 16px; font-weight: 200; line-height: 22px;'>Dentro de este contexto, la autoevaluación de los programas es una tarea permanente de las instituciones de educación superior y hace parte del proceso mejoramiento continuo de los mismos, con el fin de darle cumplimiento a los requisitos exigidos por el MEN para el funcionamiento de los programas y aún más para acreditar en alta calidad,   sin embargo, es importante tener en cuenta que, el proceso de acreditación de alta calidad de programas, es de carácter voluntario,  y es decisión de las instituciones acogerse a los lineamientos del Consejo Nacional de Acreditación CNA.
                        En este sentido, la UNIVERSIDAD TECNOLÓGICA DE BOLÍVAR asumió el proceso de autoevaluación con fines de acreditación desde el año 1996, el cual dio sus primeros frutos en el año 2002 al obtener la acreditación de alta calidad de los programas de Ingeniería Naval, Oceanografía Física y Administración Marítima.  Posteriormente, en los años 2007 y 2008 se obtuvo la renovación de acreditación a los programas mencionados. De igual manera, los programas de Ciencias Navales para oficiales Navales e Infantería de Marina obtuvieron su acreditación en el año 2011.
                        Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum
                    </p>
                </div>
                <div class="row-fluid">
                    <div class="span7">
                        <h3>Referencias Normativas</h3>
                        <ul> 
                            <li style="display: block;">1) Ley 30 de 1992, por la cual se organiza el servicio público de educación superior.</li>
                            <li style="display: block;">2) Ley 1188 del 25 de abril de 2008, por la cual se regula el registro calificado de programas de educación superior y se dictan otras disposiciones.</li>
                            <li style="display: block;">3) Decreto 1075 de 2015, por medio del cual se expide el Decreto Único Reglamentario del Sector Educación.</li>
                            <li style="display: block;">4) Decreto 1295 del 20 de julio de 2010, por el cual se reglamenta el registro calificado del que trata la Ley 1188 de 2008, la oferta y desarrollo de programas académicos de educación superior.</li>
                            <li style="display: block;">5) Decreto 2904 de 1994, por el cual se reglamenta el proceso de acreditación.</li>
                        </ul>
                    </div>
                    <div class="span5">
                        <h3>Modelo de Autoevaluación UNIVERSIDAD TECNOLÓGICA DE BOLÍVAR</h3>
                        <p>El modelo de autoevaluación de la UNIVERSIDAD TECNOLÓGICA DE BOLÍVAR, que involucra todos los componentes consignados en la Guía de Autoevaluación del C.N.A., es en primera instancia, una herramienta de verificación y apoyo, que brinda  estrategias para promover en la comunidad educativa, el fortalecimiento del concepto de calidad y el desarrollo de una cultura de evaluación. En segundo lugar, es un mecanismo para obtener información relevante y oportuna acerca de las fortalezas, debilidades, amenazas y oportunidades, sobre las cuales sea posible hacer un diagnóstico, formular un juicio crítico y fundamentar el proceso de toma de decisiones. 
                            <br/>Dentro de este contexto, se concibe el modelo de autoevaluación como la carta de navegación que permite trazar rumbos, fijar metas, orientar acciones, métodos y procedimientos, en pro de la evaluación de la calidad del servicio educativo, el manejo adecuado de los recursos, la eficiencia, la efectividad operativa y administrativa en el cumplimiento de la Misión y la Visión Institucional. 
                        </p>
                    </div><!--/span-->
                </div><!--/row-->
            </div> 
        </div><!--/Center-->

        <div id="ui-layout-west" class="ui-layout-west">
            <div id="menu0" class="ui-layout-content">
                <div id="menu" style="padding: 8px 0pt;" class="well">
                    <ul class="nav nav-list">  
                        <button id="west-closer" class="close">&laquo;</button>
                        <li class="nav-header">Modelo</li>
                        <li><a href="#listarModelo"><i class="icon-reorder"></i> Listar Modelos</a></li>
                        <li class="divider"></li>
                        <li class="nav-header">Coordinadores</li>
                        <li><a href="#listarCoordinadores"><i class="icon-reorder"></i> Listar Coordinadores</a></li>
                        <li class="divider"></li>
                        <li class="nav-header">Programas</li>
                        <li><a href="#listarProgramas"><i class="icon-reorder"></i> Listar Programas</a></li>
                        <li class="divider"></li>
                        <li><a href="#controlPanel"><i class="icon-th"></i> Panel de Control</a></li>
                    </ul>
                </div>
                <div>
                    <div style="padding: 8px 0pt;" class="well">
                        <ul class="nav nav-list">  
                            <li class="nav-header">¿Qui&eacute;n est&aacute; en l&iacute;nea&quest;</li>
                            <li id="popover" title="Personas logueadas" 
                                data-content="
                                <c:forEach items="${personasLogueadas}" var="persona" varStatus="index">
                                    ${persona}<br/>
                                </c:forEach>
                            ">
                            <a href="#"><i class="icon-group"></i> 
                                Hay ${personasLogueadas.size()} persona(s) en linea</a></li>

                            </ui>
                    </div>

                </div>
            </div>
        </div><!--/West-->



        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
        <script>window.jQuery || document.write('<script src="<%=request.getContextPath()%>/js/vendor/jquery-1.9.1.min.js"><\/script>')</script>
        <script src="<%=request.getContextPath()%>/js/jquery.layout-latest.min.js"></script>
        <script src="<%=request.getContextPath()%>/js/jquery.validate.js"></script>
        <script src="<%=request.getContextPath()%>/js/jquery.metadata.js"></script>
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
        <script src="<%=request.getContextPath()%>/js/jquery.PrintArea.js"></script>
        <script src="<%=request.getContextPath()%>/js/main2.js"></script>

    </body>
</html>

<div class="modal hide fade" id="modalCp2">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h2 class="modal-title">Atención!</h2>
    </div>
    <div class="modal-body">
        <h4>Ejecutar Proceso de Autoevaluación</h4>
        <br>
        <p>Debe configurar todo el proceso para continuar.</p>
    </div>
    <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
    </div>
</div><!-- /.modal-content -->
</div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<div class="modal hide fade" id="modalCc1">
    <div class="modal-header">
        <a data-dismiss="modal" class="close">×</a>
        <h3>Atención!</h3>
    </div>
    <div class="modal-body">
        <h4>Ejecutar Proceso de Autoevaluación.</h4>
        <br>
        <p style="text-align: justify">Esta seguro que desea ejecutar el Proceso?.</p>
    </div>
    <div class="modal-footer">
        <a id="modalCc1b2" class="btn btn-secundary" data-dismiss="modal" href="#">Cancelar</a>
        <a id="modalCc1b1" class="btn btn-primary" data-dismiss="modal" href="#">Ejecutar Proceso</a>
    </div>
</div>
<div class="modal hide fade" id="modalCc2">
    <div class="modal-header">
        <a data-dismiss="modal" class="close">×</a>
        <h3>Atención!</h3>
    </div>
    <div class="modal-body">
        <h4>Finalizar Proceso de Autoevaluación.</h4>
        <br>
        <p style="text-align: justify">Esta seguro que desea finalizar el Proceso?.</p>
    </div>
    <div class="modal-footer">
        <a id="modalCcb2" class="btn btn-secundary" data-dismiss="modal" href="#">Cancelar</a>
        <a id="modalCcb1" class="btn btn-primary" data-dismiss="modal" href="#">Finalizar Proceso</a>
    </div>
</div>
<div class="modal hide fade" id="modalCc3">
    <div class="modal-header">
        <a data-dismiss="modal" class="close">×</a>
        <h3>Atención!</h3>
    </div>
    <div class="modal-body">
        <h4>Crear Proceso de Autoevaluación.</h4>
        <br>
        <p style="text-align: justify">El programa seleccionado ya tiene un proceso activo.</p>
    </div>
    <div class="modal-footer">
        <a id="modalCb2" class="btn btn-secundary" data-dismiss="modal" href="#">Cerrar</a>
    </div>
</div>
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