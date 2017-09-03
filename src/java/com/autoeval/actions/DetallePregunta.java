/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.autoeval.actions;

import com.autoeval.ejb.PreguntaFacade;
import com.autoeval.ejb.ResultadoevaluacionFacade;
import com.autoeval.entity.Encuesta;
import com.autoeval.entity.Pregunta;
import com.autoeval.entity.Proceso;
import com.autoeval.entity.Resultadoevaluacion;
import com.autoeval.interfaz.Action;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author acreditacion
 */
public class DetallePregunta implements Action {
    PreguntaFacade preguntaFacade = lookupPreguntaFacadeBean();
    ResultadoevaluacionFacade resultadoevaluacionFacade = lookupResultadoevaluacionFacadeBean();

    @Override
    public String procesar(HttpServletRequest request) throws IOException, ServletException {
        HttpSession sesion = request.getSession();
        Proceso p = (Proceso) sesion.getAttribute("Proceso");
        sesion.setAttribute("numerico", null);
        sesion.setAttribute("documental", null);
        long t0 = System.currentTimeMillis();
        float suma;
        float numP;
      
        String idPregunta = request.getParameter("id");
        Pregunta pre = preguntaFacade.find(Integer.parseInt(idPregunta));

        List<Encuesta> encuestas = pre.getEncuestaList();
        int ceros[] = new int[encuestas.size()];
        int unos[] = new int[encuestas.size()];
        int dos[] = new int[encuestas.size()];
        int tres[] = new int[encuestas.size()];
        int cuatros[] = new int[encuestas.size()];
        int cincos[] = new int[encuestas.size()];

        //aqui va la cosa
        int cantEn = encuestas.size();
        //declaramos arreglo para tener los promedios de respuesta de la actual pregunta en cada encuesta
        float promediorespuestasxencuesta[] = new float[cantEn];

        //calculamos el promedio de respuesta de la pregunta actual en cada encuesta
        for (int m = 0; m < cantEn; m++) {
            suma = 0;
            numP = 0;
            List<Resultadoevaluacion> respuestas = resultadoevaluacionFacade.findResultadosxPreguntaxEncuestaxProceso(p, encuestas.get(m), pre);
            for (Resultadoevaluacion resultadoevaluacion : respuestas) {
                suma += Integer.parseInt(resultadoevaluacion.getRespuesta());
                numP++;
            }
            if (suma > 0) {
                promediorespuestasxencuesta[m] = (float) (Math.rint((suma / numP) * 10) / 10);
            }

            //contamos cuantos 5,4,3,2,1,0 se respondieron x encuesta
            List<Object[]> results = resultadoevaluacionFacade.findResultadosxPreguntaxEncuestaxProcesoxValor2(p, pre, encuestas.get(m));
            for (Object[] result : results) {
                ceros[m] = ((Number) result[5]).intValue();
                cincos[m] = ((Number) result[4]).intValue();
                cuatros[m] = ((Number) result[3]).intValue();
                tres[m] = ((Number) result[2]).intValue();
                dos[m] = ((Number) result[1]).intValue();
                unos[m] = ((Number) result[0]).intValue();
            }
        }

        long t1 = System.currentTimeMillis();
        long t3 = (t1 - t0);
        System.out.println("el tiempo que demora detallePregunta es: " + t3);
        sesion.setAttribute("promediorepuestasE", promediorespuestasxencuesta);
        sesion.setAttribute("cerosE", ceros);
        sesion.setAttribute("unosE", unos);
        sesion.setAttribute("dosE", dos);
        sesion.setAttribute("tresE", tres);
        sesion.setAttribute("cuatrosE", cuatros);
        sesion.setAttribute("cincosE", cincos);
        sesion.setAttribute("pregunta", pre);
        sesion.setAttribute("encuestas", encuestas);

        return "/WEB-INF/vista/comitePrograma/proceso/informe/detallePregunta.jsp";
    }

    private ResultadoevaluacionFacade lookupResultadoevaluacionFacadeBean() {
        try {
            Context c = new InitialContext();
            return (ResultadoevaluacionFacade) c.lookup("java:global/autoevaluacion/ResultadoevaluacionFacade!com.autoeval.ejb.ResultadoevaluacionFacade");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private PreguntaFacade lookupPreguntaFacadeBean() {
        try {
            Context c = new InitialContext();
            return (PreguntaFacade) c.lookup("java:global/autoevaluacion/PreguntaFacade!com.autoeval.ejb.PreguntaFacade");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
