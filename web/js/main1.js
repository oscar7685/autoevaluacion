$(function() {
    if ($(".brand").text().length > 60) {
        $(".brand").css("font-size", "18px");
    }
    location = "/autoevaluacion/#inicio";
    $(document).ajaxStart(function() {
        $("div.ui-layout-center").append("<div id='contenido'></div>");
        //$("#contenido").hide();
        $("div.ui-layout-center").append(""
                + "<div id='dancing-dots-text'>"
                + "Cargando <span></span> "
                + "</div>");

    });

    var myLayout;
    myLayout = $('body').layout({
        //	enable showOverflow on west-pane so CSS popups will overlap north pane
        west__size: 280
                , center__paneSelector: ".ui-layout-center"
                , north__paneClass: "ui-layout-pane2"
                //	reference only - these options are NOT required because 'true' is the default
                , closable: true	// pane can open & close
                , resizable: false	// when open, pane can be resized 
                , slidable: false	// when closed, pane can 'slide' open over other panes - closes on mouse-out
                , north__size: 1
                , north__closable: false
                , north__maxSize: 1
                , north__slidable: false	// OVERRIDE the pane-default of 'slidable=true'
                , north__spacing_open: 0		// no resizer-bar when open (zero height)
                , south__resizable: false	// OVERRIDE the pane-default of 'resizable=true'
                , south__closable: false
                , south__spacing_open: 0		// no resizer-bar when open (zero height)
                , west__spacing_open: 0
                , west__spacing_closed: 20
                , west__togglerLength_closed: 35
                , west__togglerAlign_closed: "top"
                , west__togglerContent_closed: "<button id='west-open' class='close' style='float:left;margin-left:4px;opacity:1;margin-top:-10px;'>&raquo;</button>"
                , west__togglerTip_closed: "Mostrar menú"
                , west__togglerTip_open: "Ocultar menú"
                , west__enableCursorHotkey: false
                , west__onclose_end: function() {
            $("#conte").removeClass("span10").addClass("span12");
        }
        , west__onopen_end: function() {
            $("#conte").removeClass("span12").addClass("span10");
        }
        , south__paneClass: "ui-layout-pane"
                , west__togglerContent_open: ""
                , west__minSize: 200
                , west__maxSize: 350


    });
    myLayout.allowOverflow('north');
    // setTimeout( myLayout.resizeAll, 1000 ); /* allow time for browser to re-render with new theme */
    // save selector strings to vars so we don't have to repeat it
    // must prefix paneClass with "body > " to target ONLY the outerLayout panes
    myLayout.addCloseBtn("#west-closer", "west");




    var actualizaEnlaces = function(hash) {
        $("li").removeClass("active");
        $("a[href='" + hash + "']").parent().addClass("active");
    };

    $("a[href='#preparedInfoNumerica']").click(function() {
        setTimeout(function() {
            $("#west-closer").trigger("click");
        }, 500);

    });
    $("a[href='#preparedInfoDocumental']").click(function() {
        setTimeout(function() {
            $("#west-closer").trigger("click");
        }, 500);

    });
    var menuProceso0 = function() {
        $("#menu0").html('<div  align="center" class="alert alert-block">' +
                ' <i class="icon-info-sign"></i> No existen proceso activos' +
                '</div>' +
                '<div id="menu" style="padding: 8px 0pt;" class="well">' +
                '<ul class="nav nav-list">  ' +
                '<button id="west-closer" class="close">&laquo;</button>' +
                '<li class="nav-header">Proceso de Autoevaluación</li>' +
                // '<li><a href="#preparedCrearProceso"><i class="icon-plus"></i> Crear Proceso</a></li>' +
                '<li><a href="#listarProceso"><i class="icon-reorder"></i> Listar Procesos</a></li>' +
                '</ul>' +
                '</div>');
    };
    var menuProceso1 = function() {
        $("#menu0").html('<div align="center" class="alert alert-info"><i class="icon-cog"></i> Proceso en configuración</div>' +
                ' <div id="menu" style="padding: 8px 0pt;" class="well">' +
                '<ul class="nav nav-list">' +
                '<button id="west-closer" class="close">&laquo;</button>' +
                '<li class="nav-header">Proceso de Autoevaluación</li>' +
                '<li><a href="#detalleProceso"><i class="icon-cogs"></i> Detalle de Proceso</a></li>' +
                '<li><a href="#listPonderacionFactor"><i class="icon-list-ol"></i> Ponderar Factores</a></li>' +
                '<li><a href="#listPonderacionCara"><i class="icon-list-ol"></i> Ponderar Características</a></li>' +
                /*'<li><a href="#listPonderacionInd"><i class="icon-signal"></i> Ponderar Indicadores</a></li>' +*/
                '<li><a href="#listMuestra"><i class="icon-group"></i> Asignar Muestra</a></li>' +
                //  '<li class="divider"></li>' +
                //'<li><a href="#iniciarProceso"><i class="icon-play-sign"></i> Ejecutar proceso</a></li>' +
                '</ul>' +
                '</div>');
    };
    var menuProceso2 = function() {
        $("#menu0").html('<div align="center" class="alert alert-success"><i class="icon-play-sign"></i> Proceso en ejecuci&oacute;n</div>' +
                '<div id="menu" style="padding: 8px 0pt;" class="well">' +
                '<ul class="nav nav-list">' +
                '<button id="west-closer" class="close">&laquo;</button>' +
                '<li class="nav-header">Proceso de Autoevaluaci&oacute;n</li>' +
                '<li><a href="#detalleProceso"><i class="icon-cogs"></i> Detalle de Proceso</a></li>' +
                '<li><a href="#listPonderacionFactor"><i class="icon-list"></i>  Factores</a></li>' +
                '<li><a href="#listPonderacionCara"><i class="icon-list"></i> Caracter&iacute;sticas</a></li>' +
                /* '<li><a href="#listPonderacionInd"><i class="icon-signal"></i> Indicadores</a></li>' +*/
                '<li><a href="#listEncuestas"><i class="icon-check"></i> Encuestas</a></li>' +
                ' <li><a href="#listMuestra"><i class="icon-group"></i> Muestra Asignada</a></li>' +
                '<li><a href="#calificarCaracteristicas"><i class="icon-check"></i> Evaluar Caracteristica</a></li>' +
                '<li><a href="#subirAdjunto"><i class="icon-file-alt"></i> Anexos</a></li>' +
                '<li class="nav-header">Estado del proceso</li>' +
                '<li><a href="#estadoProceso"><i class="icon-bar-chart"></i> Estado del proceso</a></li>' +
                '</ul>' +
                '</div>'
                );
    };
    var menuProceso3 = function() {
        $("#menu0").html('<div align="center" class="alert alert-error" style="margin-bottom:5px;"><i class="icon-play-sign"></i> Proceso finalizado</div>' +
                '<div id="menu" style="padding: 8px 0pt;" class="well">' +
                '<ul class="nav nav-list">' +
                '<button id="west-closer" class="close">&laquo;</button>' +
                '<li class="nav-header">Proceso de Autoevaluación</li>' +
                '<li><a href="#detalleProceso"><i class="icon-cogs"></i> Detalle de Proceso</a></li>' +
                '<li><a href="#listPonderacionFactor"><i class="icon-list"></i>  Factores</a></li>' +
                '<li><a href="#listPonderacionCara"><i class="icon-list"></i> Características</a></li>' +
                /*'<li><a href="#listPonderacionInd"><i class="icon-signal"></i> Indicadores</a></li>' +*/
                ' <li><a href="#listMuestra"><i class="icon-group"></i> Muestra Asignada</a></li>' +
                '<li><a href="#calificarCaracteristicas"><i class="icon-check"></i> Evaluar Caracteristica</a></li>' +
                '<li><a href="#subirAdjunto"><i class="icon-file-alt"></i> Anexos</a></li>' +
                ' <li class = "nav-header"> Estado del proceso </li>' +
                ' <li><a  id = "informeEncuesta"  href = "#informeDMA"><i class = "icon-bar-chart"></i> Estado del proceso</a></li>' +
                ' <li class="divider"></li>' +
                '<li><a href="#listarProceso"><i class="icon-reorder"></i> Listar Procesos</a></li>' +
                '</ul>' +
                '</div>'
                );
    };




    $(window).hashchange(function() {
        var hash = location.hash;
        if (hash === "#CerrarSesion") {
            $.post('/autoevaluacion/loginController?action=CerrarSesion', function() {
                location = "/autoevaluacion";

            }); //fin post


        } else if (hash === "#listPonderacionCara2" || hash === "#inicio" || hash === "#preparedCrearProceso" || hash === "#listarProceso"
                || hash === "#detalleProceso" || hash === "#detalleProceso" || hash === "#preparedPonderarFactor" || hash === "#ponderarFactor"
                || hash === "#listPonderacionFactor" || hash === "#preparedPonderarCara" || hash === "#preparedAsignarMuestra"
                || hash === "#selectorListMuestra" || hash === "#listMuestra" || hash === "#preparedEvaluador" || hash === "#preparedEditPonderarFactor"
                || hash === "#preparedEditPonderarCara" || hash === "#generarMuestraAleatoria" || hash === "#preparedEditarMuestra"
                || hash === "#editarMuestra" || hash === "#selectorListSemestre" || hash === "#preparedInfoNumerica" || hash === "#preparedInfoDocumental"
                || hash === "#estadoProceso" || hash === "#informeMatrizFactores" || hash === "#informeMatrizCaracteristicas" || hash === "#listarEvaluarDoc" || hash === "#listarEvaluarNum"
                || hash === "#listEncuestas" || hash === "#cerrarPreguntas" || hash === "#encuestaAleatoria" || hash === "#informeMatrizFactoresP" || hash === "#informeMatrizCaracteristicasP"
                || hash === "#comentarios" || hash === "#todosResultados" || hash === "#listPonderacionInd" || hash === "#calificarCaracteristicas" || hash === "#listEvaluacionCara" || hash === "#editarEvaluacionCara" || hash === "#editarEvaluacionCara2" || hash === "#subirAdjunto" || hash === "#informeDMA" || hash === "#graficasDMA") {
            var url3 = "/autoevaluacion/" + hash;
            url3 = url3.replace('#', "controladorCP?action=");
            $("div.ui-layout-center").empty();
            $.ajax({
                type: "POST",
                url: url3,
                success: function(data)
                {
                    $("#contenido").append(data);
                    $("#contenido").show(400, function() {
                        $("#dancing-dots-text").remove();
                    }
                    );
                    if (hash === "#listPonderacionCara2") {
                        hash = "#listPonderacionCara";
                    }
                    actualizaEnlaces(hash);
                } //fin success
            }); //fin del $.ajax
        } else if (hash.indexOf("#finalizarPro") !== -1) {
            $('#modalCc2').modal();

            var cual = hash.split("&");
            hash = cual[0];
            var url3 = "/autoevaluacion/controladorCC?action=";
            url3 = url3.concat(cual[0].substring(1), "&id=", cual[1]);

            $("div.ui-layout-center").empty();
            $.ajax({
                type: "POST",
                url: url3,
                success: function(data)
                {

                    menuProceso2();
                    myLayout.addCloseBtn("#west-closer", "west");
                    actualizaEnlaces();
                    $("#contenido").show(200, function() {
                        $("#dancing-dots-text").remove();
                    });

                } //fin success
            }); //fin del $.ajax

            /* */
        } else if (hash.indexOf("#verPProceso") !== -1) {
            var cual = hash.split("&");
            hash = cual[0];
            var url3 = "/autoevaluacion/controladorCP?action=";
            url3 = url3.concat(cual[0].substring(1), "&id=", cual[1]);
            $("div.ui-layout-center").empty();
            $.ajax({
                type: "POST",
                url: url3,
                success: function(data)
                {
                    if (data === '1') {
                        menuProceso1();
                    } else if (data === '2') {
                        menuProceso2();
                    } else if (data === '3') {
                        menuProceso3();
                    }
                    myLayout.addCloseBtn("#west-closer", "west");
                    actualizaEnlaces();
                    $("#contenido").show(200, function() {
                        $("#dancing-dots-text").remove();
                    });
                    location = "/autoevaluacion/#inicio";

                } //fin success
            }); //fin del $.ajax
        } else if (hash === "#iniciarProceso") {

            $('#modalCp1').modal();

            /*  var url3 = "/autoevaluacion/" + hash;
             url3 = url3.replace('#', "controladorCP?action=");
             $("div.ui-layout-center").empty();
             $.ajax({
             type: "POST",
             url: url3,
             success: function(data)
             {
             
             // $("#contenido").append(data);
             menuProceso2();
             myLayout.addCloseBtn("#west-closer", "west");
             actualizaEnlaces();
             $("#contenido").show(200, function() {
             $(".page_loading").hide();
             });
             
             } //fin success
             }); //fin del $.ajax*/
        } else if (hash === "#listarFactores") {
            var url3 = "/autoevaluacion/" + hash;
            url3 = url3.replace('#', "controladorCC?action=") + "CC";
            $("div.ui-layout-center").empty();
            $.ajax({
                type: "POST",
                url: url3,
                success: function(data)
                {
                    $("#contenido").append(data);
                    if ($("ul.nav-list li:eq(0)").html() !== "Factores") {
                        menuFactores();
                        myLayout.addCloseBtn("#west-closer", "west");
                        actualizaEnlaces();
                    }
                    $("#contenido").show(200, function() {
                        $("#dancing-dots-text").remove();
                    });
                    actualizaEnlaces(hash);
                } //fin success
            }); //fin del $.ajax
        } else if (hash === "#preparedEvaluador") {
            var url3 = "/autoevaluacion/" + hash;
            url3 = url3.replace('#', "controladorCP?action=");
            //$("div.ui-layout-center").empty();
            $.ajax({
                type: "POST",
                url: url3,
                success: function(data)
                {
                    $("#contenido").after(data);
                    $("#dancing-dots-text").remove();


                } //fin success
            }); //fin del $.ajax
            actualizaEnlaces(hash);
        }
        else if (hash === "#preparedPonderarFactor") {
            var url3 = "/autoevaluacion/" + hash;
            url3 = url3.replace('#', "controladorCP?action=");
            $("div.ui-layout-center").empty();
            $.ajax({
                type: "POST",
                url: url3,
                success: function(data)
                {
                    $("#contenido").append(data);

                    setTimeout(function() {
                        $("#dancing-dots-text").remove();
                    }, 200);

                    setTimeout(function() {
                        $("div.ui-layout-center").affix();
                    }, 500);

                    // $("div.ui-layout-center").affix('refresh');
                    actualizaEnlaces(hash);
                }
                //fin success
            }); //fin del $.ajax
        } else if (hash === "#listPonderacionCara") {
            var url3 = "/autoevaluacion/" + hash;
            url3 = url3.replace('#', "controladorCP?action=");
            $("div.ui-layout-center").empty();
            $.ajax({
                type: "POST",
                url: url3,
                success: function(data)
                {
                    if (data == 1) {
                        location = "/autoevaluacion/#listPonderacionCara2";
                    } else {
                        $('#modalCp3').modal();
                        location = "/autoevaluacion/#listPonderacionFactor";
                    }
                    actualizaEnlaces(hash);
                }
                //fin success
            }); //fin del $.ajax
        } else if (hash.indexOf("#detalleFactor") !== -1 || hash.indexOf("#detalleCaracteristica") !== -1 || hash.indexOf("#detalleIndicador") !== -1
                || hash.indexOf("#detallePregunta") !== -1 || hash.indexOf("#vistaPreviaPEncuesta") !== -1 || hash.indexOf("#encuestaXaleatoria") !== -1 || hash.indexOf("#detallePFactor") !== -1 || hash.indexOf("#detallePCaracteristica") !== -1 || hash.indexOf("#detallePIndicador") !== -1) {

            var cual = hash.split("&");
            hash = cual[0];
            var url3 = "/autoevaluacion/controladorCP?action=";
            url3 = url3.concat(cual[0].substring(1), "&id=", cual[1]);
            $("div.ui-layout-center").empty();
            $.ajax({
                type: "POST",
                url: url3,
                success: function(data)
                {
                    $("#contenido").append(data);
                    myLayout.addCloseBtn("#west-closer", "west");
                    actualizaEnlaces();
                    $("#contenido").show(200, function() {
                        $("#dancing-dots-text").remove();
                    });
                } //fin success
            }); //fin del $.ajax
        } else {
            if (hash === "#contrasena") {
                var url3 = "/autoevaluacion/" + hash;
                url3 = url3.replace('#', "controladorCP?action=");
                $("div.ui-layout-center").empty();
                $.ajax({
                    type: "POST",
                    url: url3,
                    success: function(data)
                    {
                        $("#contenido").append(data);
                        setTimeout(function() {
                            $("#dancing-dots-text").remove();
                        }, 200);
                        actualizaEnlaces(hash);
                    } //fin success
                }); //fin del $.ajax
            }
        }
    });


    $('#modalCpb2').click(function() {
        location = "#detalleProceso";
    });

    $('#modalCpb1').click(function() {
        var url3 = "/autoevaluacion/" + "controladorCP?action=iniciarProceso";
        url3 = url3.replace('#', "controladorCP?action=");
        $("div.ui-layout-center").empty();
        $.ajax({
            type: "POST",
            url: url3,
            success: function(data)
            {
                if (data == 1) {
                    // $("#contenido").append(data);
                    menuProceso2();
                    myLayout.addCloseBtn("#west-closer", "west");
                    actualizaEnlaces();
                    $("#contenido").show(200, function() {
                        $("#dancing-dots-text").remove();
                    });
                } else {
                    $('#modalCp2').modal();
                    location = "#detalleProceso";
                }

            } //fin success
        }); //fin del $.ajax
    });

});
