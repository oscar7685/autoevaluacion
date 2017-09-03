<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/datepicker.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap-datepicker.js"></script>
<script type="text/javascript" language="JavaScript">
    $(document).ready(function() {
        $('.tool').tooltip().click(function(e) {
            $(this).tooltip('hide');
        });
        $('#inicio').datepicker({
            format: 'dd/mm/yyyy'
        });
        $('#final').datepicker({
            format: 'dd/mm/yyyy'
        });

        $("#formCrearMeta").validate({
            submitHandler: function() {
                $.ajax({
                    type: 'POST',
                    url: "/autoevaluacion/controladorCP?action=crearMeta2",
                    data: $("#formCrearMeta").serialize(),
                    success: function() {
                        location = "/autoevaluacion/#verMetas&${objetivo.idobjetivos}";
                    } //fin success
                }); //fin $.ajax    */
            }
        });
    });
</script>
<div class="hero-unit">
    <div style="margin-left: -30px;">
        <div id="conte" class="span10" style="text-align: justify">
            <div class="row">
                <ul class="nav nav-tabs" role="tablist">
                    <li class="active"><a href="#home" role="tab" data-toggle="tab">Plan de mejoramiento</a></li>
                    <li><a href="#profile" role="tab" data-toggle="tab">Plan de mantenimiento</a></li>
                </ul>

                <div class="tab-content">
                    <div class="tab-pane active" id="home">
                        <ul class="breadcrumb">
                            <li><a href="<%=request.getContextPath()%>/#listarHallazgos" class="tool" data-placement="top" rel="tooltip" data-original-title="Listar hallazgos">Hallazgos</a> <span class="divider">/</span></li>
                            <li><a href="<%=request.getContextPath()%>/#editarHallazgo&${hallazgo.idhallazgo}" class="tool" data-placement="top" rel="tooltip" data-original-title="${hallazgo.hallazgo}">Hallazgo</a> <span class="divider">/</span></li>
                            <li><a href="<%=request.getContextPath()%>/#listarObjetivos&${hallazgo.idhallazgo}" class="tool" data-placement="top" rel="tooltip" data-original-title="Listar objetivos">Objetivos</a> <span class="divider">/</span></li>
                            <li><a href="<%=request.getContextPath()%>/#editarObjetivo&${objetivo.idobjetivos}" class="tool" data-placement="top" rel="tooltip" data-original-title="${objetivo.objetivo}">Objetivo</a> <span class="divider">/</span></li>
                            <li><a href="<%=request.getContextPath()%>/#verMetas&${objetivo.idobjetivos}" class="tool" data-placement="top" rel="tooltip" data-original-title="Listar metas">Metas</a> <span class="divider">/</span></li>
                            <li>Crear</li>
                            <a id="printEnlace" target="_blank" href="/autoevaluacion/controladorCP?action=PM" style="float: right; cursor: pointer;"><i class="icon-eye-open"></i> Ver Plan de Mejoramiento</a>
                        </ul>

                        <form id="formCrearMeta" class="form-horizontal" method="post">
                            <fieldset>
                                <legend>Crear meta</legend>
                                <div class="control-group">
                                    <label for="meta" class="control-label">Meta</label>
                                    <div class="controls">
                                        <textarea rows="2" name="meta" id="meta" class="input-xxlarge {required:true}"></textarea>
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label for="estrategia" class="control-label">Estrategia</label>
                                    <div class="controls">
                                        <textarea rows="2" name="estrategia" id="estrategia" class="input-xxlarge {required:true}"></textarea>
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label for="indicador" class="control-label">Indicador de cumplimiento</label>
                                    <div class="controls">
                                        <textarea rows="2" name="indicador" id="indicador" class="input-xxlarge {required:true}"></textarea>
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label for="inicio" class="control-label">Fecha de Inicio</label>
                                    <div class="controls">
                                        <input type="text" name="inicio" id="inicio" class="form-control"
                                               value="" >
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label for="final" class="control-label">Fecha Final</label>
                                    <div class="controls">
                                        <input type="text" name="final" id="final" class="form-control"
                                               value="" >
                                    </div>
                                </div>    
                                <div class="control-group">
                                    <label for="responsables" class="control-label">Responsables</label>
                                    <div class="controls">
                                        <textarea rows="2" name="responsables" id="responsables" class="input-xxlarge {required:true}"></textarea>
                                    </div>
                                </div>    
                                <div class="control-group">
                                    <label for="recursos" class="control-label">Recursos o Financiación</label>
                                    <div class="controls">
                                        <textarea rows="2" name="recursos" id="recursos" class="input-xxlarge {required:true}"></textarea>
                                    </div>
                                </div>    
                                <div class="form-actions span8">
                                    <button class="btn btn-primary" type="submit">Crear meta</button>
                                    <button class="btn" type="reset">Cancelar</button>
                                </div>
                            </fieldset>
                        </form>

                    </div>

                    <div class="tab-pane" id="profile">
                        <ul class="breadcrumb">
                            <li>Fortalezas</li>
                            <a id="printEnlace" target="_blank" href="/autoevaluacion/controladorCP?action=PM2" style="float: right; cursor: pointer;"><i class="icon-eye-open"></i> Ver Plan de Mantenimiento</a>
                        </ul>
                        <h3>Listado de  Fortalezas</h3>
                      <c:choose>
                            <c:when test="${fn:length(listFortalezas)!= 0}">
                                <table class="table table-striped table-bordered table-condensed">
                                    <thead>
                                    <th>Fortaleza</th>
                                    <th>Caracteristica</th>    
                                    <th>Acci&oacute;n</th>    
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${listFortalezas}" var="item" varStatus="iter">
                                            <tr>
                                                <td>   
                                                    <c:out value="${item.hallazgo}"/>
                                                </td>
                                                <td>   
                                                    <c:out value="${item.caracteristicaId.nombre}"/>
                                                </td>
                                                <td>   
                                                    <a href="#editarFortaleza&${item.idhallazgo}" title="Editar"><i class="icon-edit"></i></a>
                                                    <a href="#listar2Objetivos&${item.idhallazgo}" title="Ver objetivos"><i class="icon-signin"></i></a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </c:when>
                            <c:otherwise>
                                Aun no existen Fortalezas para este plan de mantenimiento.<br/>
                            </c:otherwise>
                        </c:choose>
                        <a href="#crearFortaleza" class="btn btn-large btn-primary"><i class="icon-plus"></i> Crear fortaleza</a>    
                    </div>        
                </div>
            </div>
        </div>
    </div>
</div>
