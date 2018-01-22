<%
    HttpSession session1 = request.getSession();
    String aux = (String) session1.getAttribute("tipoLogin");
    if (aux == null || aux.isEmpty()) {
        session1.invalidate();
    } else {
        if (aux.equals("Comite programa")) {
            RequestDispatcher rd = request.getRequestDispatcher("/controladorCP?action=indexCP");
            rd.forward(request, response);
        } else {
            if (aux.equals("Comite central")) {
                RequestDispatcher rd = request.getRequestDispatcher("/controladorCC?action=indexCC");
                rd.forward(request, response);
            } else {
                if (aux.equals("Fuente")) {
                    RequestDispatcher rd = request.getRequestDispatcher("/controladorF?action=indexF");
                    rd.forward(request, response);
                }
            }
        }
    }

%>


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
        <!-- <link href="css/slider.css" rel="stylesheet" type="text/css" />-->
        <link rel="stylesheet" href="css/bootstrap.css">
        <link rel="stylesheet" href="css/otro.css">
        <link rel="stylesheet" href="css/main.css">
        <link rel="stylesheet" href="css/font-awesome.min.css">
        <link rel="stylesheet" href="css/bootstrap-responsive.min.css">
        <!-- Custom styles for this template -->
        <link href="css/jumbotron-narrow.css" rel="stylesheet">
        <script src="js/vendor/modernizr-2.6.2-respond-1.1.0.min.js"></script>

    </head>
    <body style="padding-top: 0px; background-image: url(img/UTB_autoevaluacionBanner2.jpg); background-repeat: no-repeat;background-size: 100%;">
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
                        </ul>
                    </div>
                    <!--/.nav-collapse -->
                </div>
            </div>
        </div>
        <header style="padding-top:2px;">

        </header>
         <!--<div class="container">-->
             <div class="row" id="login">
                 <form name="formularioLogin" class="form-signin" id="formulario_login" style="max-width: 700px;">
                     <div class="alert alert-error fade in" id="login-error" style="display:none;">
                         <button type="button" class="close" id="close1">×</button>
                     </div>
                     <!--<h2 class="form-signin-heading">Acceder</h2>-->
                     <div style="text-align: center">
                     <input type="text" placeholder="Usuario" name="codigo" id="codigo" class="{required:true}">
                     <input type="password" placeholder="Contraseña" name="clave" id="pass" class="{required:true}" >
                     <button id="btnIniciar" type="submit" class="btn btn-large btn-primary" style="margin-bottom: 15px;">Acceder</button>
                     </div>
                 </form>
             </div>
 
        <!--</div>-->
        
        <!-- /container -->

        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
        <script>window.jQuery || document.write('<script src="<%=request.getContextPath()%>/js/vendor/jquery-1.9.1.min.js"><\/script>')</script>
        <script src="js/jquery.validate.js"></script>
        <script src="js/jquery.metadata.js"></script>
        <script src="js/vendor/bootstrap.min.js"></script>
        <script src="js/jquery.ba-hashchange.min.js"></script>
        <script type='text/javascript' src='js/slider.js'></script>
        <script src="js/main.js"></script>

        
    </body>
</html>
