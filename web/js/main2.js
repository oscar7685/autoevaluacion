$(function() {
    var urlx;

    $("#popover").popover({
        trigger: 'hover',
        html: true,
        placement: 'top',
        title: ("Personas logueadas"),
        content: function() {
            return '' + $("#popover").attr("data-content");
        }
    }).popover('show');
    location = "/autoevaluacion/#inicio";
    $(document).ajaxStart(function() {
        if ($("div.ui-layout-center").find('div#contenido').length === 0) {
            $("div.ui-layout-center").append("<div id='contenido'></div>");
            //$("#contenido").hide();
            $("div.ui-layout-center").append(""
                    + "<div id='dancing-dots-text'>"
                    + "Cargando <span></span> "
                    + "</div>");
        } else {
            $("div.ui-layout-center").append(""
                    + "<div id='dancing-dots-text'>"
                    + "Cargando <span></span> "
                    + "</div>");
        }

    });

    var myLayout;
    myLayout = $('body').layout({
        //	enable showOverflow on west-pane so CSS popups will overlap north pane
        west__size: 270
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
        $(".ui-layout-west .nav li").removeClass("active");
        $(".ui-layout-north .nav li").removeClass("active");
        $("a[href='" + hash + "']").parent().addClass("active");
    };


    var menuModelo = function() {
        $(".ui-layout-content > .alert").remove();
        $("#menu").html('<ul class="nav nav-list">' +
                '<button id="west-closer" class="close">&laquo;</button>' +
                '<li class="nav-header">Modelo</li>' +
                '<li><a href="#listarModelo"><i class="icon-reorder"></i> Listar Modelos</a></li>' +
                '<li class="divider"></li>' +
                '<li class="nav-header">Coordinadores</li>' +
                '<li><a href="#listarCoordinadores"><i class="icon-reorder"></i> Listar Coordinadores</a></li>' +
                '<li class="divider"></li>' +
                '<li class="nav-header">Programas</li>' +
                '<li><a href="#listarProgramas"><i class="icon-reorder"></i> Listar Programas</a></li>' +
                '<li class="divider"></li>' +
                '<li><a href="#controlPanel"><i class="icon-th"></i> Panel de Control</a></li>' +
                '</ul>');
    };
    var menuFactores = function() {
        $(".ui-layout-content > .alert").remove();
        $("#menu").html('<ul class="nav nav-list">' +
                '<button id="west-closer" class="close">&laquo;</button>' +
                '<li><a href="#listarModelo"><i class="icon-level-up"></i>Men&uacute; modelo</a></li>' +
                '<li class="nav-header">Factores</li>' +
                '<li><a href="#listarFactores"><i class="icon-th-large"></i> Listar factores</a></li>' +
                '<li class="divider"></li>' +
                '<li class="nav-header">Caracteristicas</li>' +
                '<li><a href="#listarCaracteristicas"><i class="icon-th-list"></i> Listar caracteristicas</a></li>' +
                '<li class="divider"></li>' +
                '<li class="nav-header">Indicadores</li>' +
                '<li><a href="#listarIndicadores"><i class="icon-list"></i> Listar indicadores</a></li>' +
                '<li class="divider"></li>' +
                '<li class="nav-header">Preguntas</li>' +
                '<li><a href="#listarPreguntas"><i class="icon-question"></i> Listar preguntas</a></li>' +
                '<li class="divider"></li>' +
                '<li class="nav-header">Encuestas</li>' +
                '<li><a href="#listarEncuestas"><i class="icon-tasks"></i> Listar encuestas</a></li>' +
                '</ul>');
    };

    var menuProceso3 = function() {
        $("#menu0").html('<div align="center" class="alert alert-error" style="margin-bottom:5px;"><i class="icon-play-sign"></i> Proceso finalizado</div>' +
                '<div id="menu" style="padding: 8px 0pt;" class="well">' +
                '<ul class="nav nav-list">' +
                '<button id="west-closer" class="close">&laquo;</button>' +
                '<li><a href="#controlPanel"><i class="icon-level-up"></i>Regresar al panel</a></li>' +
                '<li class="nav-header">Proceso de Autoevaluación</li>' +
                '<li><a href="#detalleProceso"><i class="icon-cogs"></i> Detalle de Proceso</a></li>' +
                '<li><a href="#listPonderacionFactor"><i class="icon-list"></i>  Factores</a></li>' +
                '<li><a href="#listPonderacionCara"><i class="icon-list"></i> Características</a></li>' +
                /*'<li><a href="#listPonderacionInd"><i class="icon-signal"></i> Indicadores</a></li>' +*/
                '<li><a href="#selectorListMuestra"><i class="icon-group"></i> Muestra Asignada</a></li>' +
                '<li><a href="#calificarCaracteristicas"><i class="icon-check"></i> Evaluar Caracteristica</a></li>' +
                '<li><a href="#subirAdjunto"><i class="icon-file-alt"></i> Anexos</a></li>' +
                '<li class = "nav-header"> Estado del proceso </li>' +
                '<li><a id = "informeEncuesta"  href = "#estadoProceso"><i class = "icon-bar-chart"></i> Estado Proceso</a></li>' +
                '<li class = "nav-header"> Plan de Aseguramiento </li>' +
                '<li><a href="#planMejoramiento"><i class="icon-exchange"></i> Plan de aseguramiento</a></li>' +
                '</ul>' +
                '</div>'
                );
    };
    var hash;
    $(window).hashchange(function() {
        hash = location.hash;
        if (hash === "#CerrarSesion") {
            $.post('/autoevaluacion/loginController?action=CerrarSesion', function() {
                location = "/autoevaluacion";

            });//fin post

        } else if (hash.indexOf("#ejecutarPro") !== -1) {
            $('#modalCc1').modal();

            var cual = hash.split("&");
            hash = cual[0];
            var url3 = "/autoevaluacion/controladorCC?action=";
            url3 = url3.concat(cual[0].substring(1), "CC&id=", cual[1]);

            urlx = url3;


        } else if (hash === "#todosResultados") {
            var url3 = "/autoevaluacion/" + hash;
            url3 = url3.replace('#', "controladorCP?action=");
            $("div.ui-layout-center").empty();
            $.ajax({
                type: "POST",
                url: url3,
                success: function(data)
                {
                    $("#contenido").append(data);
                    $("#contenido").show(200, function() {
                        $("#dancing-dots-text").remove();
                    });

                } //fin success
            }); //fin del $.ajax

        } else if (hash.indexOf("#finalizarPro") !== -1) {
            $('#modalCc2').modal();

            var cual = hash.split("&");
            hash = cual[0];
            var url3 = "/autoevaluacion/controladorCC?action=";
            url3 = url3.concat(cual[0].substring(1), "CC&id=", cual[1]);

            urlx = url3;

            /* */
        } else if (hash === "#preparedCrearProceso") {
            var url3 = "/autoevaluacion/" + hash;
            url3 = url3.replace('#', "controladorCP?action=");
            $("div.ui-layout-center").empty();
            $.ajax({
                type: "POST",
                url: url3,
                success: function(data)
                {
                    $("#contenido").append(data);
                    $("#contenido").show(200, function() {
                        $("#dancing-dots-text").remove();
                    });

                } //fin success
            }); //fin del $.ajax
        } else {
            if (hash === "#inicio") {
                var url3 = "/autoevaluacion/" + hash;
                url3 = url3.replace('#', "controladorCC?action=") + "CC";
                $("div.ui-layout-center").empty();
                $.ajax({
                    type: "POST",
                    url: url3,
                    success: function(data)
                    {
                        $("#contenido").append(data);
                        $("#contenido").show(200, function() {
                            menuModelo();
                            myLayout.addCloseBtn("#west-closer", "west");
                            $("#dancing-dots-text").remove();
                        });
                        actualizaEnlaces(hash);
                    } //fin success
                }); //fin del $.ajax
            } else {
                if (hash.indexOf("#entrarModelo") !== -1 || hash.indexOf("#editarEncuesta") !== -1
                        || hash.indexOf("#vistaPreviaEncuesta") !== -1 || hash.indexOf("#editarFactor") !== -1
                        || hash.indexOf("#editarCaracteristica") !== -1 || hash.indexOf("#editarIndicador") !== -1
                        || hash.indexOf("#editarPregunta") !== -1) {
                    var cual = hash.split("&");
                    hash = cual[0];
                    var url3 = "/autoevaluacion/controladorCC?action=";
                    url3 = url3.concat(cual[0].substring(1), "CC&id=", cual[1]);
                    $("div.ui-layout-center").empty();
                    $.ajax({
                        type: "POST",
                        url: url3,
                        success: function(data)
                        {
                            $("#contenido").append(data);

                            if ($("ul.nav-list li:eq(1)").html() !== "Factores") {
                                menuFactores();
                                myLayout.addCloseBtn("#west-closer", "west");
                            }
                            $("#contenido").show(200, function() {
                                $("#dancing-dots-text").remove();
                            });



                            actualizaEnlaces(hash);
                        } //fin success
                    }); //fin del $.ajax

                } else {
                    if (hash === "#crearModelo" || hash === "#crearPrograma" || hash === "#listarProgramas" || hash === "#listarModelo" || hash === "#listarCoordinadores" || hash === "#crearCoordinador" || hash === "#controlPanel") {
                        var url3 = "/autoevaluacion/" + hash;
                        url3 = url3.replace('#', "controladorCC?action=") + "CC";
                        $("div.ui-layout-center").empty();
                        $.ajax({
                            type: "POST",
                            url: url3,
                            success: function(data)
                            {
                                $("#contenido").append(data);
                                if ($("ul.nav-list li:eq(0)").html() !== "Modelo") {
                                    menuModelo();
                                    myLayout.addCloseBtn("#west-closer", "west");

                                }
                                $("#contenido").show(200, function() {
                                    $("#dancing-dots-text").remove();
                                });
                                actualizaEnlaces(hash);
                            }

                        }); //fin del $.ajax
                    } else {
                        if (hash === "#listarFactores" || hash === "#crearFactor"
                                || hash === "#listarCaracteristicas" || hash === "#crearCaracteristica"
                                || hash === "#listarIndicadores" || hash === "#crearIndicador"
                                || hash === "#listarPreguntas" || hash === "#crearPregunta"
                                || hash === "#listarEncuestas" || hash === "#crearEncuesta") {
                            var url3 = "/autoevaluacion/" + hash;
                            url3 = url3.replace('#', "controladorCC?action=") + "CC";
                            $("div.ui-layout-center").empty();
                            $.ajax({
                                type: "POST",
                                url: url3,
                                success: function(data)
                                {
                                    $("#contenido").append(data);
                                    if ($("ul.nav-list li:eq(1)").html() !== "Factores") {
                                        menuFactores();
                                        myLayout.addCloseBtn("#west-closer", "west");
                                    }
                                    $("#contenido").show(200, function() {
                                        $("#dancing-dots-text").remove();
                                    });
                                    actualizaEnlaces(hash);
                                } //fin success
                            }); //fin del $.ajax
                        } else {
                            if (hash.indexOf("#editarModelo") !== -1 || hash.indexOf("#editarCoordinador") !== -1 || hash.indexOf("#editarPrograma") !== -1) {
                                var cual = hash.split("&");
                                hash = cual[0];
                                var url3 = "/autoevaluacion/controladorCC?action=";
                                url3 = url3.concat(cual[0].substring(1), "CC&id=", cual[1]);
                                $("div.ui-layout-center").empty();
                                $.ajax({
                                    type: "POST",
                                    url: url3,
                                    success: function(data)
                                    {
                                        $("#contenido").append(data);
                                        if ($("ul.nav-list li:eq(0)").html() !== "Modelo") {
                                            menuModelo();
                                            myLayout.addCloseBtn("#west-closer", "west");

                                        }
                                        $("#contenido").show(200, function() {
                                            $("#dancing-dots-text").remove();
                                        });
                                        actualizaEnlaces(hash);
                                    } //fin success
                                }); //fin del $.ajax

                            } else {
                                if (hash.indexOf("#verProcesos") !== -1) {
                                    var cual = hash.split("&");
                                    hash = cual[0];
                                    var url3 = "/autoevaluacion/controladorCC?action=";
                                    url3 = url3.concat(cual[0].substring(1), "CC&id=", cual[1]);
                                    $("div.ui-layout-center").empty();
                                    $("div.ui-layout-content").empty();
                                    $.ajax({
                                        type: "POST",
                                        url: url3,
                                        success: function(data)
                                        {
                                            $("div.ui-layout-content").append(data);
                                            setTimeout(function() {
                                                $("#dancing-dots-text").remove();
                                                myLayout.addCloseBtn("#west-closer", "west");
                                            }, 200);

                                        } //fin success
                                    }); //fin del $.ajax


                                } else if (hash === "#listPonderacionCara2" || hash === "#listarProceso"
                                        || hash === "#detalleProceso" || hash === "#ponderarFactor"
                                        || hash === "#listPonderacionFactor" || hash === "#preparedPonderarCara" || hash === "#preparedAsignarMuestra"
                                        || hash === "#selectorListMuestra" || hash === "#listMuestra" || hash === "#preparedEditPonderarFactor"
                                        || hash === "#preparedEditPonderarCara" || hash === "#generarMuestraAleatoria" || hash === "#preparedEditarMuestra"
                                        || hash === "#editarMuestra" || hash === "#selectorListSemestre" || hash === "#preparedInfoNumerica" || hash === "#preparedInfoDocumental"
                                        || hash === "#estadoProceso" || hash === "#informeMatrizFactores" || hash === "#informeMatrizCaracteristicas" || hash === "#listarEvaluarDoc" || hash === "#listarEvaluarNum"
                                        || hash === "#listEncuestas" || hash === "#cerrarPreguntas" || hash === "#encuestaAleatoria" || hash === "#informeMatrizFactoresP" || hash === "#informeMatrizCaracteristicasP"
                                        || hash === "#planMejoramiento" || hash === "#crearHallazgo" || hash === "#listarHallazgos" || hash === "#crearObjetivo" || hash === "#crearMeta" || hash === "#crear2Meta" || hash === "#listarFortalezas"
                                        || hash === "#comentarios" || hash === "#crearFortaleza" || hash === "#crear2Objetivo" || hash === "#listPonderacionInd" || hash === "#graficasDMA" || hash === "#calificarCaracteristicas" || hash === "#listEvaluacionCara" || hash === "#editarEvaluacionCara" || hash === "#editarEvaluacionCara2" || hash === "#subirAdjunto" || hash === "#informeDMA") {
                                    var url3 = "/autoevaluacion/" + hash;
                                    url3 = url3.replace('#', "controladorCP?action=");
                                    $("div.ui-layout-center").empty();
                                    $.ajax({
                                        type: "POST",
                                        url: url3,
                                        success: function(data)
                                        {
                                            if (hash === "#planMejoramiento") {
                                                actualizaEnlaces(hash);
                                            }
                                            $("#contenido").append(data);
                                            $("#contenido").show(400, function() {
                                                $("#dancing-dots-text").remove();
                                            }
                                            );
                                            if (hash === "#listPonderacionCara2") {
                                                hash = "#listPonderacionCara";
                                            }
                                            if (hash === "#preparedInfoNumerica" || hash === "#preparedInfoDocumental" || hash === "#informeDMA") {
                                                setTimeout(function() {
                                                    $("#west-closer").trigger("click");
                                                }, 500);
                                            }
                                            if (hash !== "#planMejoramiento" && hash !== "#crearHallazgo" && hash !== "#listarHallazgos" && hash !== "#listarFortalezas"
                                                    && hash !== "#crearObjetivo" && hash !== "#crear2Objetivo" && hash !== "#crearMeta" && hash !== "#crear2Meta" && hash !== "#crearFortaleza") {
                                                actualizaEnlaces(hash);
                                            }

                                        } //fin success
                                    }); //fin del $.ajax
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
                                            //location = "/autoevaluacion/#inicio";

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
                                } else if (hash === "#preparedPonderarFactor") {
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

                                            //$("div.ui-layout-center").affix('refresh');
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
                                } else if (hash.indexOf("#listarObjetivos") !== -1 || hash.indexOf("#editarHallazgo") !== -1 || hash.indexOf("#editarFortaleza") !== -1
                                        || hash.indexOf("#verMetas") !== -1 || hash.indexOf("#ver2Metas") !== -1 || hash.indexOf("#editarObjetivo") !== -1 || hash.indexOf("#editar2Objetivo") !== -1 || hash.indexOf("#editarMeta") !== -1 || hash.indexOf("#editar2Meta") !== -1 || hash.indexOf("#listar2Objetivos") !== -1) {
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
                            }



                        }

                    }
                }
            }

        }

    });



    $('#modalCc1b1').click(function() {

        $("div.ui-layout-center").empty();
        $.ajax({
            type: "POST",
            url: urlx,
            success: function(data)
            {
                if (data == 1) {
                    location = "#controlPanel";
                } else {
                    $('#modalCp2').modal();
                    location = "#controlPanel";
                }

            } //fin success
        }); //fin del $.ajax
    });

    $('#modalCcb1').click(function() {

        $("div.ui-layout-center").empty();
        $.ajax({
            type: "POST",
            url: urlx,
            success: function(data)
            {

                location = "#controlPanel";

            } //fin success
        }); //fin del $.ajax
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
