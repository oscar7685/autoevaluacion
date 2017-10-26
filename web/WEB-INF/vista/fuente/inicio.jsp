<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<div  class="span10 offset2" style="text-align: justify">
    <div class="span8">
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
                                Encuesta de autoevaluación
                            </td>
                            <td>   
                                <a title="Responder Encuesta" href="#responderEncuestaF">Responder encuesta >></a>
                            </td>
                        </tr>
                    </tbody>
                </table>
                <br/>
                <h5 style="text-align: center;">Usted ha sido seleccionado al azar para diligenciar una encuesta de caracter an&oacute;nimo y voluntario, Recuerde que su participaci&oacute;n es fundamental en el proceso de autoevaluaci&oacute;n </h5>
                <br/>
                <p class="marketing-byline">Universidad Tecnol&oacute;gica de Bolivar</p>              

            </c:when>
            <c:otherwise>
                No Existen Encuestas Disponibles.
            </c:otherwise>
        </c:choose>


    </div>
</div> 