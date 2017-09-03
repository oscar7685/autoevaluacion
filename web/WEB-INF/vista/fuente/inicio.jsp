<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<c:choose>
    <c:when test="${encuesta!=null}">
        <div class="span10 offset2" style="text-align: justify">
            <div class="span8">
                <br/>
                <h3>${encuesta.nombre}</h3>
                <c:choose>
                    <c:when test="${programa.id == 1}">
                        <p>Actualmente la Escuela Naval de Cadetes "Almirante Padilla" se encuentra en proceso de 
                            autoevaluación con fines de Acreditación Institucional, identificando y valorando fortalezas
                            y debilidades, mediante un proceso amplio y participativo de la Comunidad Educativa.<br/>
                            Las preguntas que se registran a continuación tienen como objetivo primordial,
                            obtener información pertinente y confiable sobre las condiciones de calidad y cumplimiento
                            de cada uno de los factores estipulados por el Consejo Nacional de Acreditación.</p>
                        </c:when>
                        <c:otherwise>
                        <p>Actualmente el programa de ${programa.nombre} se encuentra en proceso de 
                            autoevaluación con fines de mejoramiento continuo, identificando y valorando fortalezas
                            y debilidades, mediante un proceso amplio y participativo de la Comunidad Educativa.<br/>
                            Las preguntas que se registran a continuación tienen como objetivo primordial,
                            obtener información pertinente y confiable sobre las condiciones de calidad y cumplimiento
                            de cada uno de los indicadores enunciados.</p>
                        </c:otherwise>
                    </c:choose>
                <div class="row">
                    <div class="span4">    
                        <p>Para contestar tenga en cuenta la siguiente valoración:</p>
                        <p>ESCALA VALORATIVA</p>
                        <p>5 - Muy alto / Totalmente / Excelente<br/>
                            4 - Alto / En alto grado / Buena<br/>
                            3 - Medio / Aceptablemente / Regular<br/>
                            2 - Bajo / En bajo grado / Mala<br/>
                            1 - Muy bajo / En ningún grado / Muy mala<br/>
                            0 - No sabe / No responde</p>
                    </div>
                    <div class="span4">    
                        <div style="text-align: center"><img src="img/logos-02.png"></div>
                    </div>
                </div>

                <br/>
                <a title="Responder Encuesta" class="btn btn-default" href="#responderEncuestaF&id=${encuesta.id}&fuente=${fuente.id}&persona=${persona.id}">Continuar >> </a>

            </div>
        </div> 
    </c:when>
    <c:when test="${encuesta0!=null && encuesta1 == null}">
        <div class="span10 offset2" style="text-align: justify">
            <div class="span8">
                <br/>
                <h3>${encuesta0.nombre}</h3>
                <c:choose>
                    <c:when test="${programa0.id == 1}">
                        <p>Actualmente la Escuela Naval de Cadetes "Almirante Padilla" se encuentra en proceso de 
                            autoevaluación con fines de Acreditación Institucional, identificando y valorando fortalezas
                            y debilidades, mediante un proceso amplio y participativo de la Comunidad Educativa.<br/>
                            Las preguntas que se registran a continuación tienen como objetivo primordial,
                            obtener información pertinente y confiable sobre las condiciones de calidad y cumplimiento
                            de cada uno de los factores estipulados por el Consejo Nacional de Acreditación.</p>
                        </c:when>
                        <c:otherwise>
                        <p>Actualmente el programa de ${programa0.nombre} se encuentra en proceso de 
                            autoevaluación con fines de mejoramiento continuo, identificando y valorando fortalezas
                            y debilidades, mediante un proceso amplio y participativo de la Comunidad Educativa.<br/>
                            Las preguntas que se registran a continuación tienen como objetivo primordial,
                            obtener información pertinente y confiable sobre las condiciones de calidad y cumplimiento
                            de cada uno de los indicadores enunciados.</p>
                        </c:otherwise>
                    </c:choose>

                <div class="row">
                    <div class="span4">    
                        <p>Para contestar tenga en cuenta la siguiente valoración:</p>
                        <p>ESCALA VALORATIVA</p>
                        <p>5 - Muy alto / Totalmente / Excelente<br/>
                            4 - Alto / En alto grado / Buena<br/>
                            3 - Medio / Aceptablemente / Regular<br/>
                            2 - Bajo / En bajo grado / Mala<br/>
                            1 - Muy bajo / En ningún grado / Muy mala<br/>
                            0 - No sabe / No responde</p>

                    </div>
                    <div class="span4">    
                        <div style="text-align: center"><img src="img/logos-02.png"></div>
                    </div>
                </div>

                <br/>
                <a title="Responder Encuesta" class="btn btn-default" href="#responderEncuestaF&id=${encuesta0.id}&fuente=${fuente0.id}&persona=${persona0.id}">Continuar >> </a>

            </div>
        </div> 
    </c:when>
    <c:when test="${encuesta0==null && encuesta1 != null}">
        <div class="span10 offset2" style="text-align: justify">
            <div class="span8">
                <br/>
                <h3>${encuesta1.nombre}</h3>
                <c:choose>
                    <c:when test="${programa1.id == 1}">
                        <p>Actualmente la Escuela Naval de Cadetes "Almirante Padilla" se encuentra en proceso de 
                            autoevaluación con fines de Acreditación Institucional, identificando y valorando fortalezas
                            y debilidades, mediante un proceso amplio y participativo de la Comunidad Educativa.<br/>
                            Las preguntas que se registran a continuación tienen como objetivo primordial,
                            obtener información pertinente y confiable sobre las condiciones de calidad y cumplimiento
                            de cada uno de los factores estipulados por el Consejo Nacional de Acreditación.</p>
                        </c:when>
                        <c:otherwise>
                        <p>Actualmente el programa de ${programa1.nombre} se encuentra en proceso de 
                            autoevaluación con fines de mejoramiento continuo, identificando y valorando fortalezas
                            y debilidades, mediante un proceso amplio y participativo de la Comunidad Educativa.<br/>
                            Las preguntas que se registran a continuación tienen como objetivo primordial,
                            obtener información pertinente y confiable sobre las condiciones de calidad y cumplimiento
                            de cada uno de los indicadores enunciados.</p>
                        </c:otherwise>
                    </c:choose>

                <div class="row">
                    <div class="span4">    
                        <p>Para contestar tenga en cuenta la siguiente valoración:</p>
                        <p>ESCALA VALORATIVA</p>
                        <p>5 - Muy alto / Totalmente / Excelente<br/>
                            4 - Alto / En alto grado / Buena<br/>
                            3 - Medio / Aceptablemente / Regular<br/>
                            2 - Bajo / En bajo grado / Mala<br/>
                            1 - Muy bajo / En ningún grado / Muy mala<br/>
                            0 - No sabe / No responde</p>

                    </div>
                    <div class="span4">    
                        <div style="text-align: center"><img src="img/logos-02.png"></div>
                    </div>
                </div>
                <br/>
                <a title="Responder Encuesta" class="btn btn-default" href="#responderEncuestaF&id=${encuesta1.id}&fuente=${fuente1.id}&persona=${persona1.id}">Continuar >> </a>
            </div>
        </div> 
    </c:when>
    <c:when test="${encuesta0!=null && encuesta1 != null}">
        <div class="row">
            <div class="span6" style="text-align: justify">
                <br/>
                <h3>${encuesta0.nombre}</h3>
                <c:choose>
                    <c:when test="${programa0.id == 1}">
                        <p>Actualmente la Escuela Naval de Cadetes "Almirante Padilla" se encuentra en proceso de 
                            autoevaluación con fines de Acreditación Institucional, identificando y valorando fortalezas
                            y debilidades, mediante un proceso amplio y participativo de la Comunidad Educativa.<br/>
                            Las preguntas que se registran a continuación tienen como objetivo primordial,
                            obtener información pertinente y confiable sobre las condiciones de calidad y cumplimiento
                            de cada uno de los factores estipulados por el Consejo Nacional de Acreditación.</p>
                        </c:when>
                        <c:otherwise>
                        <p>Actualmente el programa de ${programa0.nombre} se encuentra en proceso de 
                            autoevaluación con fines de mejoramiento continuo, identificando y valorando fortalezas
                            y debilidades, mediante un proceso amplio y participativo de la Comunidad Educativa.<br/>
                            Las preguntas que se registran a continuación tienen como objetivo primordial,
                            obtener información pertinente y confiable sobre las condiciones de calidad y cumplimiento
                            de cada uno de los indicadores enunciados.</p>
                        </c:otherwise>
                    </c:choose>
                <div class="row">
                    <div class="span4">    
                        <p>Para contestar tenga en cuenta la siguiente valoración:</p>
                        <p>ESCALA VALORATIVA</p>
                        <p>5 - Muy alto / Totalmente / Excelente<br/>
                            4 - Alto / En alto grado / Buena<br/>
                            3 - Medio / Aceptablemente / Regular<br/>
                            2 - Bajo / En bajo grado / Mala<br/>
                            1 - Muy bajo / En ningún grado / Muy mala<br/>
                            0 - No sabe / No responde</p>

                    </div>
                    <div class="span4">    
                        <div style="text-align: center"><img src="img/logos-02.png"></div>
                    </div>
                </div>
                <br/>
                <a title="Responder Encuesta" class="btn btn-default" href="#responderEncuestaF&id=${encuesta0.id}&fuente=${fuente0.id}&persona=${persona0.id}">Continuar >> </a>
            </div>
              <div class="span6" style="text-align: justify">
                <br/>
                <h3>${encuesta1.nombre}</h3>
                <c:choose>
                    <c:when test="${programa1.id == 1}">
                        <p>Actualmente la Escuela Naval de Cadetes "Almirante Padilla" se encuentra en proceso de 
                            autoevaluación con fines de Acreditación Institucional, identificando y valorando fortalezas
                            y debilidades, mediante un proceso amplio y participativo de la Comunidad Educativa.<br/>
                            Las preguntas que se registran a continuación tienen como objetivo primordial,
                            obtener información pertinente y confiable sobre las condiciones de calidad y cumplimiento
                            de cada uno de los factores estipulados por el Consejo Nacional de Acreditación.</p>
                        </c:when>
                        <c:otherwise>
                        <p>Actualmente el programa de ${programa1.nombre} se encuentra en proceso de 
                            autoevaluación con fines de mejoramiento continuo, identificando y valorando fortalezas
                            y debilidades, mediante un proceso amplio y participativo de la Comunidad Educativa.<br/>
                            Las preguntas que se registran a continuación tienen como objetivo primordial,
                            obtener información pertinente y confiable sobre las condiciones de calidad y cumplimiento
                            de cada uno de los indicadores enunciados.</p>
                        </c:otherwise>
                    </c:choose>
                <div class="row">
                    <div class="span4">    
                        <p>Para contestar tenga en cuenta la siguiente valoración:</p>
                        <p>ESCALA VALORATIVA</p>
                        <p>5 - Muy alto / Totalmente / Excelente<br/>
                            4 - Alto / En alto grado / Buena<br/>
                            3 - Medio / Aceptablemente / Regular<br/>
                            2 - Bajo / En bajo grado / Mala<br/>
                            1 - Muy bajo / En ningún grado / Muy mala<br/>
                            0 - No sabe / No responde</p>

                    </div>
                    <div class="span4">    
                        <div style="text-align: center"><img src="img/logos-02.png"></div>
                    </div>
                </div>
                <br/>
                <a title="Responder Encuesta" class="btn btn-default" href="#responderEncuestaF&id=${encuesta1.id}&fuente=${fuente1.id}&persona=${persona1.id}">Continuar >> </a>
            </div>
        </div>    
    </c:when>

    <c:when test="${encuesta==null && encuesta0==null && encuesta1 == null}">
        No Existen Encuestas Disponibles.
    </c:when>
</c:choose>