<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script>
    $(function() {
        $('.tool').tooltip().click(function(e) {
            $(this).tooltip('hide');
        });
    });
</script>
<div class="hero-unit">
    <div style="margin-left: -30px;">
        <div id="conte" class="span10" style="text-align: justify">
            <div class="row">
                <ul class="nav nav-tabs" role="tablist">
                    <li><a href="#home" role="tab" data-toggle="tab">Plan de mejoramiento</a></li>
                    <li class="active"><a href="#profile" role="tab" data-toggle="tab">Plan de mantenimiento</a></li>
                </ul>

                <div class="tab-content">
                    <div class="tab-pane" id="home">
                        <ul class="breadcrumb">
                            <li>Hallazgos</li>
                            <a id="printEnlace" target="_blank" href="/autoevaluacion/controladorCP?action=PM" style="float: right; cursor: pointer;"><i class="icon-eye-open"></i> Ver Plan de Mejoramiento</a>
                        </ul>
                        <h3>Listado de  Hallazgos</h3>
                        <c:choose>
                            <c:when test="${fn:length(listHallazgos)!= 0}">
                                <table class="table table-striped table-bordered table-condensed">
                                    <thead>
                                    <th>Hallazgo</th>
                                    <th>Caracteristica</th>    
                                    <th>Acci&oacute;n</th>    
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${listHallazgos}" var="item" varStatus="iter">
                                            <tr>
                                                <td>   
                                                    <c:out value="${item.hallazgo}"/>
                                                </td>
                                                <td>   
                                                    <c:out value="${item.caracteristicaId.nombre}"/>
                                                </td>
                                                <td>   
                                                    <a href="#editarHallazgo&${item.idhallazgo}" title="Editar"><i class="icon-edit"></i></a>
                                                    <a href="#listarObjetivos&${item.idhallazgo}" title="Ver objetivos"><i class="icon-signin"></i></a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </c:when>
                            <c:otherwise>
                                Aun no existen Hallazgos para este plan de mantenimiento.<br/>
                            </c:otherwise>
                        </c:choose>
                        <a href="#crearHallazgo" class="btn btn-large btn-primary"><i class="icon-plus"></i> Crear hallazgo</a>    
                    </div>
                            
                     <div class="tab-pane active" id="profile">
                        <ul class="breadcrumb">
                            <li><a href="<%=request.getContextPath()%>/#listarFortalezas" class="tool" data-placement="top" rel="tooltip" data-original-title="Listar fortalezas">Fortalezas</a> <span class="divider">/</span></li>
                            <li><a href="<%=request.getContextPath()%>/#editarFortaleza&${fortaleza.idhallazgo}" class="tool" data-placement="top" rel="tooltip" data-original-title="${fortaleza.hallazgo}">Fortaleza</a> <span class="divider">/</span></li>
                            <li><a href="<%=request.getContextPath()%>/#listar2Objetivos&${fortaleza.idhallazgo}" class="tool" data-placement="top" rel="tooltip" data-original-title="Listar objetivos">Objetivos</a> <span class="divider">/</span></li>
                            <li><a href="<%=request.getContextPath()%>/#editar2Objetivo&${objetivo.idobjetivos}" class="tool" data-placement="top" rel="tooltip" data-original-title="${objetivo.objetivo}">Objetivo</a> <span class="divider">/</span></li>
                            <li>Metas</li>
                            <a id="printEnlace" target="_blank" href="/autoevaluacion/controladorCP?action=PM2" style="float: right; cursor: pointer;"><i class="icon-eye-open"></i> Ver Plan de Mantenimiento</a>
                        </ul>
                        <h3>Listado de  Metas</h3>
                        <c:choose>
                            <c:when test="${fn:length(listMetas)!= 0}">
                                <table class="table table-striped table-bordered table-condensed">
                                    <thead>
                                    <th>Metas</th>
                                    <th>Estrategias</th>    
                                    <th>Indicador de Cumplimiento</th>    
                                    <th>Fecha Inicio</th>    
                                    <th>Fecha Final</th>    
                                    <th>Responsables</th>    
                                    <th>Recursos o financiaci&oacute;n</th>    
                                    <th></th>    
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${listMetas}" var="item" varStatus="iter">
                                            <tr>
                                                <td>   
                                                    <c:out value="${item.meta}"/>
                                                </td>
                                                <td>   
                                                    <c:out value="${item.estrategia}"/>
                                                </td>
                                                <td>   
                                                    <c:out value="${item.indicadorCumplimiento}"/>
                                                </td>
                                                <td>   
                                                    <fmt:formatDate value="${item.fechaInicio}" pattern="yyyy/MM/dd" />
                                                </td>
                                                <td>   
                                                    <fmt:formatDate value="${item.fechaFinal}" pattern="yyyy/MM/dd"  />
                                                </td>
                                                <td>   
                                                    <c:out value="${item.responsable}"/>
                                                </td>
                                                <td>   
                                                    <c:out value="${item.financiacion}"/>
                                                </td>
                                                
                                                <td>   
                                                    <a href="#editar2Meta&${item.idmeta}" title="Editar"><i class="icon-edit"></i></a>
                                                 </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </c:when>
                            <c:otherwise>
                                Aun no existen Metas para este Objetivo.<br/>
                            </c:otherwise>
                        </c:choose>
                        <a href="#crear2Meta" class="btn btn-large btn-primary"><i class="icon-plus"></i> Crear Meta</a>
                    </div>        
                </div>
            </div>
        </div>
    </div>
</div>

