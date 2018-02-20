<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/print.css" media="print">
<link rel="stylesheet" href="css/jquery.fileupload.css">
<style type="text/css">
    #sharefLI_1 {
        box-sizing: border-box;
        color: rgb(95, 118, 118);
        height: 43px;
        width: 506.031px;
        perspective-origin: 253.016px 21.5px;
        transform-origin: 253.016px 21.5px;
        border: 0px none rgb(95, 118, 118);
        font: normal normal normal normal 14px / 20px 'Source Sans Pro', 'Segoe UI', 'Droid Sans', Tahoma, Arial, sans-serif;
        list-style: none outside none;
        outline: rgb(95, 118, 118) none 0px;
    }/*#sharefLI_1*/

    #sharefA_2 {
        box-sizing: border-box;
        color: rgb(52, 152, 219);
        cursor: pointer;
        display: inline-block;
        height: 43px;
        text-align: center;
        text-decoration: none;
        touch-action: manipulation;
        vertical-align: middle;
        white-space: nowrap;
        width: 235.297px;
        perspective-origin: 117.641px 21.5px;
        transform-origin: 117.641px 21.5px;
        background: rgba(255, 255, 255, 0) none repeat scroll 0% 0% / auto padding-box border-box;
        border: 1px solid rgb(52, 152, 219);
        border-radius: 3px 3px 3px 3px;
        font: normal normal normal normal 17px / 22.61px 'Source Sans Pro', 'Segoe UI', 'Droid Sans', Tahoma, Arial, sans-serif;
        list-style: none outside none;
        outline: rgb(52, 152, 219) none 0px;
        padding: 0px 29px 0px 21px;
    }/*#sharefA_2*/

    #sharefI_3 {
        box-shadow: rgba(0, 0, 0, 0.0980392) -1px 0px 0px 0px inset;
        box-sizing: border-box;
        color: rgb(52, 152, 219);
        cursor: pointer;
        display: inline-block;
        height: 41px;
        left: -21px;
        position: relative;
        text-align: center;
        white-space: nowrap;
        width: 48px;
        perspective-origin: 24px 20.5px;
        transform-origin: 24px 20.5px;
        background: rgba(0, 0, 0, 0.0196078) none repeat scroll 0% 0% / auto padding-box border-box;
        border: 0px none rgb(52, 152, 219);
        border-radius: 3px 0 0 3px;
        font: normal normal normal normal 17px / 23px FontAwesome;
        list-style: none outside none;
        outline: rgb(52, 152, 219) none 0px;
        padding: 9px;
    }/*#sharefI_3*/

    #sharefI_3:before {
        box-sizing: border-box;
        color: rgb(52, 152, 219);
        cursor: pointer;
        text-align: center;
        white-space: nowrap;
        border: 0px none rgb(52, 152, 219);
        font: normal normal normal normal 17px / 23px FontAwesome;
        list-style: none outside none;
        outline: rgb(52, 152, 219) none 0px;
    }/*#sharefI_3:before*/
</style>
<script type="text/javascript">
    $(function () {
        $(".btn-group > .btn").click(function () {
            $("tr.terminadoC").hide();
            $("tr.pendienteC").hide();
            $(".btn-group input").each(function (index) {
                if ($(this).prop("checked") && index == 0) {
                    $("tr.pendienteC").show();
                } else if ($(this).prop("checked") && index == 1) {
                    $("tr.terminadoC").show();
                }
            });
            $("#total").text("Total: " + ($("tr.terminadoC:visible").length + $("tr.pendienteC:visible").length));
        });
        marcacion = new Date();
        Hora = marcacion.getHours();
        Minutos = marcacion.getMinutes();
        Segundos = marcacion.getSeconds();
        if (Hora <= 9)
            Hora = "0" + Hora
        if (Minutos <= 9)
            Minutos = "0" + Minutos
        if (Segundos <= 9)
            Segundos = "0" + Segundos
        var Dia = new Array("Domingo", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo");
        var Mes = new Array("Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre");
        var Hoy = new Date();
        var Anio = Hoy.getFullYear();
        var Fecha = Dia[Hoy.getDay()] + " " + Hoy.getDate() + " de " + Mes[Hoy.getMonth()] + " de " + Anio + ", a las " + Hora + ":" + Minutos + ":" + Segundos;
        $("#hora").html(" " + Fecha);

        $("#bpreparedCrearPersona").click(function () {
            $.ajax({
                type: 'POST',
                url: "/autoevaluacion/controladorCP?action=preparedCrearEvaluador&fuente=${fuenteX}",
                success: function (datos) {
                    $("#editM").empty();
                    $("#editM").append(datos);
                    $("#contenido").show(200, function () {
                        $("#dancing-dots-text").remove();
                    });
                } //fin success
            }); //fin $.ajax    

        });
        $("#bpreparedEliminarPersonas").click(function () {
            $.ajax({
                type: 'POST',
                url: "/autoevaluacion/controladorCP?action=eliminarPersonas&fuente=${fuenteX}",
                success: function () {
                    $("#listM").empty();
                    $.ajax({
                        type: 'POST',
                        url: "/autoevaluacion/controladorCP?action=selectorListMuestra",
                        data: $("#formListarMuestra").serialize(),
                        success: function (datos) {
                            $(".divEvaluador").remove();
                            $("#listM").append(datos);
                            $("#contenido").show(200, function () {
                                $("#dancing-dots-text").remove();
                            });
                        } //fin success
                    }); //fin $.ajax 
                } //fin success
            }); //fin $.ajax    

        });

        $("#bpreparedEditarMuestra").click(function () {
            $.ajax({
                type: 'POST',
                url: "/autoevaluacion/controladorCP?action=preparedEditarMuestra&fuente=${fuenteX}",
                success: function (datos) {
                    $("#editM").empty();
                    $("#editM").append(datos);
                    $("#contenido").show(200, function () {
                        $("#dancing-dots-text").remove();
                    });
                } //fin success
            }); //fin $.ajax    

        });


        $("#printEnlace").click(function () {
            $('#printMuestra').printArea();
            return false;
        });
        $("#actEnlace").click(function () {
            marcacion = new Date()
            Hora = marcacion.getHours()
            Minutos = marcacion.getMinutes()
            Segundos = marcacion.getSeconds()
            if (Hora <= 9)
                Hora = "0" + Hora
            if (Minutos <= 9)
                Minutos = "0" + Minutos
            if (Segundos <= 9)
                Segundos = "0" + Segundos
            var Dia = new Array("Domingo", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo");
            var Mes = new Array("Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre");
            var Hoy = new Date();
            var Anio = Hoy.getFullYear();
            var Fecha = Dia[Hoy.getDay()] + " " + Hoy.getDate() + " de " + Mes[Hoy.getMonth()] + " de " + Anio + ", a las " + Hora + ":" + Minutos + ":" + Segundos;
            $("#hora").html(" " + Fecha);
            $.ajax({
                type: 'POST',
                url: "/autoevaluacion/controladorCP?action=selectorListSemestre",
                data: $("#formListarMuestra").serialize(),
                success: function (datos) {
                    $("#listM").empty();
                    $("#listM").append(datos);
                    $("#contenido").show(200, function () {
                        $("#dancing-dots-text").remove();
                    });
                } //fin success
            }); //fin $.ajax   
        });
    });</script>
    <c:if test="${EstadoProceso == 2}">
    <div class="span10">
        <a style="float:right; cursor: pointer" id="actEnlace"><i class="icon-refresh"></i> Actualizar</a>  
        <a style="float:right; padding-right: 10px; cursor: pointer" id="printEnlace"><i class="icon-print"></i> Imprimir</a>      
    </div>

</c:if>
<c:if test="${EstadoProceso != 2}">
    <div class="span10">
        <a  style="float: right; cursor: pointer" id="printEnlace"><i class="icon-print"></i> Imprimir</a>  
    </div>

</c:if>
<div id="printMuestra">
    <c:if test="${EstadoProceso == 2}">
        <div>
            <div style="margin-left: 0px;" class="span1"><span style="margin-left: 0px;" id="spanActualizado" class="label label-info span1">Actualizado</span></div>
            <div class="span9"><p id="hora" class="help-block"></p></div>
        </div>
    </c:if>
    <div id="listM2" class="span10" style="margin-left: 0px;">
        <div class="span10" style="margin-left: 0px;">
            <div class="btn-group" data-toggle="buttons">
                <label class="btn btn-danger">
                    <input type="checkbox" autocomplete="off" checked>Pendiente
                </label>
                <label class="btn btn-success">
                    <input type="checkbox" autocomplete="off" checked>Terminado
                </label>
            </div> 								
            <div id="editM">
                <ul class="nav nav-tabs" id="myTab">
                    <li class="active"><a href="#poblacionest" data-toggle="tab">Población</a></li>
                </ul>
                <div class="tab-content">
                    <div class="tab-pane active" id="poblacionest">
                        <table id="tablaY1" class="table table-striped table-bordered" cellspacing="0" width="100%">
                            <thead>
                                <tr>
                                    <th>Identificación</th>
                                    <th>Nombre</th>
                                    <th>Programa</th>
                                </tr>
                            </thead>
                            <tbody id="bodytablaestudiante">
                                <c:choose>
                                    <c:when test="${fn:length(participantes)!= 0}">
                                        <c:forEach items="${participantes}" var="participante" varStatus="iter1">
                                            <c:choose>   
                                                <c:when test="${participante[3] != null}">
                                                    <tr class="terminadoC">
                                                    </c:when>
                                                    <c:otherwise>
                                                    <tr class="pendienteC"> 
                                                    </c:otherwise>    
                                                </c:choose>
                                                <td <c:if test="${participante[3] != null}">style="background-color: #DFF0D8; color: #468847;"</c:if>>${participante[0]}</td>
                                                <td <c:if test="${participante[3] != null}">style="background-color: #DFF0D8; color: #468847;"</c:if>>${participante[1]}</td>
                                                <td <c:if test="${participante[3] != null}">style="background-color: #DFF0D8; color: #468847;"</c:if>>${participante[2]}</td>
                                                </tr>
                                        </c:forEach>
                                    </c:when>
                                </c:choose>
                            </tbody>
                        </table>
                        <p id="total0" style="font-weight: bold">Total: ${fn:length(participantes)}</p>

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="js/vendor/jquery.ui.widget.js"></script>
<script src="js/jquery.iframe-transport.js"></script>
<script src="js/jquery.fileupload.js"></script>
<script src="js/jquery.fileupload-process.js"></script>
<script src="js/jquery.fileupload-validate.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        'use strict';

        // Initialize the jQuery File Upload widget:
        $('#fileupload').fileupload({
            // Uncomment the following to send cross-domain cookies:
            //xhrFields: {withCredentials: true},
            url: 'SubirArchivo',
            acceptFileTypes: /(\.|\/)(gif|jpg|png|pdf|xlsx)$/i,
            previewFileTypes: /^.*\/(gif|jpeg|png|pdf|PDF)$/

        });

        // Enable iframe cross-domain access via redirect option:
        $('#fileupload').fileupload(
                'option',
                'redirect',
                window.location.href.replace(
                        /\/[^\/]*$/,
                        '/cors/result.html?%s'
                        )
                ).bind('fileuploaddestroy', function (e, data) {
            if (e.isDefaultPrevented()) {
                return false;
            }
            var that = $(this).data('blueimp-fileupload') ||
                    $(this).data('fileupload'),
                    removeNode = function () {
                        that._transition(data.context).done(
                                function () {
                                    $(this).remove();
                                    that._trigger('destroyed', e, data);
                                }
                        );
                    };
            if (data.url) {
                data.dataType = data.dataType || that.options.dataType;
                $.ajax(data).done(removeNode).fail(function () {
                    that._trigger('destroyfailed', e, data);
                    removeNode();
                });
            } else {
                removeNode();
            }

        }).bind('fileuploaddone', function (e, data) {
            $("#dancing-dots-text").remove();
            $('#selectListMuestra').val('--').trigger('change');
        });


        $('#tablaY1').DataTable({
            bPaginate: false,
            aaSorting: [],
            dom: 'Bfrtip',
            buttons: [
                {
                    extend: 'copy',
                    text: 'Copiar'
                },
                {
                    extend: 'excelHtml5',
                    exportOptions: {
                        rows: ':visible'
                    },
                    text: 'Exportar a excel',
                    title: 'Muestra ${fuenteX}'
                }
            ],
            language: {
                buttons: {
                    copyTitle: 'Copiar'
                },
                "lengthMenu": "Mostrando _MENU_ registros por pagina",
                "zeroRecords": "No hay registros",
                "info": "Mostrando page _PAGE_ of _PAGES_",
                "infoEmpty": "No hay registros disponibles",
                "sInfo": "Mostrando registros del _START_ al _END_ de _TOTAL_",
                "infoFiltered": "(filtrados de un total de _MAX_ registros)",
                sSearch: "Buscar:"
            }
        });
    });
</script>
