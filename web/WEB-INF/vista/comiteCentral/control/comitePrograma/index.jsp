<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:choose>
    <c:when test="${EstadoProceso == 0}">
        <div  align="center" class="alert alert-block">
            <i class="icon-info-sign"></i> No existen proceso activos
        </div>
        <div id="menu" style="padding: 8px 0pt;" class="well">
            <ul class="nav nav-list">  
                <button id="west-closer" class="close">&laquo;</button>
                <li><a href="#controlPanel"><i class="icon-level-up"></i>Regresar al panel</a></li>
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
                <li><a href="#controlPanel"><i class="icon-level-up"></i>Regresar al panel</a></li>
                <li class="nav-header">Proceso de Autoevaluación</li>
                <li><a href="#detalleProceso"><i class="icon-cogs"></i> Detalle de Proceso</a></li>
                <li><a href="#listPonderacionFactor"><i class="icon-list-ol"></i> Ponderar Factores</a></li>
                <li><a href="#listPonderacionCara2"><i class="icon-list-ol"></i> Ponderar Características</a></li>
                <!--<li><a href="#listPonderacionInd"><i class="icon-signal"></i> Ponderar Indicadores</a></li>-->
                <li><a href="#listMuestra"><i class="icon-group"></i> Asignar Muestra</a></li>
            </ul>
        </div>
    </c:when>
    <c:when test="${EstadoProceso == 2}">
        <div align="center" class="alert alert-success"><i class="icon-play-sign"></i> Proceso en ejecución</div>
        <div id="menu" style="padding: 8px 0pt;" class="well">
            <ul class="nav nav-list">  
                <button id="west-closer" class="close">&laquo;</button>
                <li><a href="#controlPanel"><i class="icon-level-up"></i>Regresar al panel</a></li>
                <li class="nav-header">Proceso de Autoevaluación</li>
                <li><a href="#detalleProceso"><i class="icon-cogs"></i> Detalle de Proceso</a></li>
                <li><a href="#listPonderacionFactor"><i class="icon-list"></i> Factores</a></li>
                <li><a href="#listPonderacionCara2"><i class="icon-list"></i> Características</a></li>
                <li><a href="#listEncuestas"><i class="icon-check"></i> Encuestas</a></li>
                <li><a href="#listMuestra"><i class="icon-group"></i> Muestra Asignada</a></li>
                <li><a href="#calificarCaracteristicas"><i class="icon-check"></i> Evaluar Caracteristica</a></li>
                <li><a href="#subirAdjunto"><i class="icon-file-alt"></i> Anexos</a></li>
                <li class="nav-header">Estado del proceso</li>
                <li><a href="#estadoProceso"><i class="icon-bar-chart"></i> Estado del proceso</a></li>
                <!--<li><a  id="informeEncuesta"  href="#informeDMA"><i class="icon-table"></i> Informe DMA</a></li>
                <li><a href="#graficasDMA"><i class="icon-bar-chart"></i> Graficas DMA</a></li>
                <li><a href="#informeMatrizFactores"><i class="icon-bar-chart"></i> Matriz factores</a></li>
                <li><a href="#informeMatrizCaracteristicas"><i class="icon-bar-chart"></i> Matriz caracter&iacute;sticas</a></li>-->
            </ul>
        </div>
        <div class="alert alert-success">
            <p>Estado del proceso: <c:out value="${porcentaje}"></c:out></p>
                <div class="progress progress-success progress-striped">
                    <div class="bar" style="width: ${porcentaje}%;">
                </div>
            </div>
            <p>Han realizado la encuesta ${contestaron} personas de un total de ${total}</p>   
        </div>
    </c:when>
    <c:when test="${EstadoProceso == 3}">
        <div align="center" class="alert alert-error"><i class="icon-play-sign"></i> Proceso finalizado</div>
        <div id="menu" style="padding: 8px 0pt;" class="well">
            <ul class="nav nav-list">  
                <button id="west-closer" class="close">&laquo;</button>
                <li><a href="#controlPanel"><i class="icon-level-up"></i>Regresar al panel</a></li>
                <li class="nav-header">Proceso de Autoevaluación</li>
                <li><a href="#detalleProceso"><i class="icon-cogs"></i> Detalle de Proceso</a></li>
                <li><a href="#listPonderacionFactor"><i class="icon-list"></i> Factores</a></li>
                <li><a href="#listPonderacionCara2"><i class="icon-list"></i> Características</a></li>
                <li><a href="#listMuestra"><i class="icon-group"></i> Muestra Asignada</a></li>
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

<c:choose>
    <c:when test="${EstadoProceso == 0}">
        <script type="text/javascript" >
            $(function () {
                location = "#listarProceso";
            });
        </script>
    </c:when>
</c:choose>     