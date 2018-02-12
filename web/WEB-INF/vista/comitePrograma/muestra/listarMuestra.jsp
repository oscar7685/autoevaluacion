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
    });
</script>   

<div class="hero-unit">
    <div class="row">
        <div id="conte" class="span12">
            <ul class="nav nav-pills" style="margin-bottom: 0px">

            </ul>
            <div id="listM">
                <div id="printMuestra">
                    <div id="listM2" class="span12" style="margin-left: 0px;">
                        <div class="span12" style="margin-left: 0px;">
                            <div id="editM">
                                <ul class="nav nav-tabs" id="myTab">
                                    <li class="active"><a href="#poblacionest" data-toggle="tab">Población</a></li>
                                </ul>
                                <div class="btn-group" data-toggle="buttons">
                                    <label class="btn btn-danger">
                                        <input type="checkbox" autocomplete="off" checked>Pendiente
                                    </label>
                                    <label class="btn btn-success">
                                        <input type="checkbox" autocomplete="off" checked>Terminado
                                    </label>
                                </div>
                                <div class="tab-content">
                                    <div class="tab-pane active" id="poblacionest">
                                        <table id="tablaX" class="table table-striped table-bordered" cellspacing="0" width="100%">
                                            <thead>
                                                <tr>
                                                    <th>Identificación</th>
                                                    <th>Nombre</th>
                                                    <th>Perfil</th>
                                                    <th>Programa</th>
                                                </tr>
                                            </thead>
                                            <tbody id="bodytablaestudiante">
                                                <c:choose>
                                                    <c:when test="${fn:length(participantes)!= 0}">
                                                        <c:forEach items="${participantes}" var="participante" varStatus="iter1">
                                                            <c:choose>
                                                                <c:when test="${participante[4] != null}">
                                                                    <tr class="terminadoC">
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                    <tr class="pendienteC"> 
                                                                    </c:otherwise>    
                                                                </c:choose>
                                                                <td <c:if test="${participante[4] != null}">style="background-color: #DFF0D8; color: #468847;"</c:if>>${participante[0]}</td>
                                                                <td <c:if test="${participante[4] != null}">style="background-color: #DFF0D8; color: #468847;"</c:if>>${participante[1]}</td>
                                                                <td <c:if test="${participante[4] != null}">style="background-color: #DFF0D8; color: #468847;"</c:if>>${participante[2]}</td>
                                                                <td <c:if test="${participante[4] != null}">style="background-color: #DFF0D8; color: #468847;"</c:if>>${participante[3]}</td>
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

            </div>
            <c:if test="${(EstadoProceso == 2 || EstadoProceso == 1) && tipoLogin=='Comite central'}">
                <h2>Adjuntar Archivo con la población</h2> 
                <form action="Formulario" class="form row-border" enctype='multipart/form-data'>
                    <div class="form-group">
                        <!-- The global progress bar -->
                        <div class="col-sm-12">
                            <div id="progress" class="progress">
                                <div class="progress-bar progress-bar-success"></div>
                            </div>
                        </div>
                        <div class="col-sm-5">

                            <span class="btn btn-success fileinput-button">      
                                <i class="glyphicon glyphicon-plus"></i>
                                <span>Seleccionar archivo...</span>
                                <input id="fileupload" type="file" name="files[]" multiple>
                            </span>
                        </div>
                        <label class="col-sm-10 control-label">Ingrese el archivo excel con la poblaci&oacute;n.<br>Solamente se aceptan archivos con el formato brindado.</label>
                        <div class="col-sm-10">
                            <div class="col-sm-5">
                                <li id="sharefLI_1">
                                    <a href="DescargarFormato" id="sharefA_2" target="_blank"><i id="sharefI_3" class="icon-download-alt"></i> Descargar formato</a>
                                </li>
                            </div>
                            <!-- The file input field used as target for the file upload widget -->
                            <!-- The container for the uploaded files -->
                            <div id="files" class="files"></div>
                        </div>
                    </div>
                </form>
            </c:if>
        </div>
    </div>
</div>                       
<script type="text/javascript">
    /* Table initialisation */
    $(document).ready(function () {
        var oTable = $('#tablaX').dataTable({
            "paging": false,
            // "sPaginationType": "bootstrap",
            "oLanguage": {
                "sProcessing": "Procesando...",
                "sLengthMenu": "Mostrar _MENU_ registros",
                "sZeroRecords": "No se encontraron resultados",
                "sEmptyTable": "Ningún dato disponible en esta tabla",
                "sInfo": "Mostrando registros del _START_ al _END_ de un total de _TOTAL_ registros",
                "sInfoEmpty": "Mostrando registros del 0 al 0 de un total de 0 registros",
                "sInfoFiltered": "(filtrado de un total de _MAX_ registros)",
                "sInfoPostFix": "",
                "sSearch": "Buscar:",
                "sUrl": "",
                "sInfoThousands": ",",
                "sLoadingRecords": "Cargando...",
                "oAria": {
                    "sSortAscending": ": Activar para ordenar la columna de manera ascendente",
                    "sSortDescending": ": Activar para ordenar la columna de manera descendente"
                }
            }
        });
    });
</script>
