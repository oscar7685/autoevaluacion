<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<div  class="span10 offset2" style="text-align: justify">
    <div class="span8">
        <div class="container">
            <div class="starter-template">
                <br/>
                <p class="lead">Apreciado miembro de la comunidad acad�mica UTB.<br/><br/>
                    En el marco de los procesos de mejoramiento continuo y como parte de nuestra cultura de autoevaluaci�n y autorregulaci�n, la universidad requiere conocer sus apreciaciones sobre los siguientes factores de calidad: Misi�n y Proyecto Educativo Institucional, Profesores, Estudiantes, Procesos Acad�micos, Visibilidad Nacional e Internacional, Investigaci�n, Pertinencia e Impacto Social, Bienestar Institucional, Planta F�sica, Recursos de Apoyo Acad�mico, Recursos Financieros, Organizaci�n Administraci�n y Gesti�n. 
                    Para ello le agradecemos dedique unos minutos de su tiempo para responder la totalidad de las preguntas de la siguiente encuesta de autoevaluaci�n con fines de la Renovaci�n de nuestra Acreditaci�n Institucional.
                        </p>
            </div>
        </div>
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
                                Autoevaluaci�n general (proceso conjunto)
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