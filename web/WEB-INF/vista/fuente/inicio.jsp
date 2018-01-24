<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<div  class="span10 offset2" style="text-align: justify">
    <div class="span8">
        <br/>
        <p class="lead">Apreciado miembro de la comunidad académica UTB.<br/><br/>
            En el marco de los procesos de mejoramiento continuo y como parte de nuestra cultura de autoevaluación y autorregulación, la universidad requiere conocer sus apreciaciones sobre los siguientes factores de calidad: Misión y Proyecto Educativo Institucional, Profesores, Estudiantes, Procesos Académicos, Visibilidad Nacional e Internacional, Investigación, Pertinencia e Impacto Social, Bienestar Institucional, Planta Física, Recursos de Apoyo Académico, Recursos Financieros, Organización Administración y Gestión. 
            Para ello le agradecemos dedique unos minutos de su tiempo para responder la totalidad de las preguntas de la siguiente encuesta de autoevaluación con fines de la Renovación de nuestra Acreditación Institucional.
        </p>
        <br/>
        <h2>Listado de  Encuestas Disponibles</h2>
        <br/>
        <c:choose>
            <c:when test="${participante!=null}">
                <table class="table table-striped table-bordered table-condensed">
                    <thead>
                    <th>Encuesta</th>
                    <th></th>
                    </thead>
                    <tbody>
                        <tr>
                            <td>   
                                Autoevaluación general (proceso conjunto)
                            </td>
                            <td>   
                                <a title="Responder Encuesta" href="#responderEncuesta2F">Responder encuesta</a>
                            </td>
                        </tr>
                    </tbody>
                </table>
                <br/>
            </c:when>
            <c:otherwise>
                No Existen Encuestas Disponibles.
            </c:otherwise>
        </c:choose>


    </div>
</div> 