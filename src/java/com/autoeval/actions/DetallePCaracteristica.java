/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.autoeval.actions;

import com.autoeval.ejb.CaracteristicaFacade;
import com.autoeval.ejb.ResultadoevaluacionFacade;
import com.autoeval.entity.Caracteristica;
import com.autoeval.entity.Encuesta;
import com.autoeval.entity.Indicador;
import com.autoeval.entity.Instrumento;
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
public class DetallePCaracteristica implements Action {
    ResultadoevaluacionFacade resultadoevaluacionFacade = lookupResultadoevaluacionFacadeBean();
    CaracteristicaFacade caracteristicaFacade = lookupCaracteristicaFacadeBean();

    @Override
    public String procesar(HttpServletRequest request) throws IOException, ServletException {
        long t0 = System.currentTimeMillis();
        HttpSession sesion = request.getSession();
        Proceso p = (Proceso) sesion.getAttribute("Proceso");
        String idCaracteristica = request.getParameter("id");
        float calificacionPer;
        Caracteristica c = caracteristicaFacade.find(Integer.parseInt(idCaracteristica));
        List<Indicador> indicadores = c.getIndicadorList();
        float cumplimientoxindicador[] = new float[indicadores.size()];
        for (int j = 0; j < indicadores.size(); j++) {
            calificacionPer = 0;
            List<Instrumento> instr = indicadores.get(j).getInstrumentoList();
            for (int i = 0; i < instr.size(); i++) {
                Instrumento instrumento = instr.get(i);
                if (instrumento.getId() == 1) {

                    //RECUPERAMOS LAS PREGUNTAS DEL ACTUAL INDICADOR
                    List<Pregunta> preguntas = indicadores.get(j).getPreguntaList();
                    float promedioPregunta, suma, numP;

                    //declaramos promedio de respuestas para cada preguntas
                    float promediorespuestas[] = new float[preguntas.size()];

                    //recorremos cada pregunta
                    for (int l = 0; l < preguntas.size(); l++) {
                        Pregunta pregunta = preguntas.get(l);

                        //sacamos las encuestas asociadas a la pregunta actual
                        List<Encuesta> encuestas = pregunta.getEncuestaList();
                        int cantEn = encuestas.size();
                        //declaramos arreglo para tener los promedios de respuesta de la actual pregunta en cada encuesta
                        float promediorespuestasxencuesta[] = new float[cantEn];

                        //calculamos el promedio de respuesta de la pregunta actual en cada encuesta
                        for (int m = 0; m < cantEn; m++) {
                            suma = 0;
                            numP = 0;
                            List<Resultadoevaluacion> respuestas = resultadoevaluacionFacade.findResultadosxPreguntaxEncuestaxProceso(p, encuestas.get(m), pregunta);
                            for (Resultadoevaluacion resultadoevaluacion : respuestas) {
                                suma += Integer.parseInt(resultadoevaluacion.getRespuesta());
                                numP++;
                            }
                            if (suma > 0) {
                                promediorespuestasxencuesta[m] = (float) suma / numP;
                            }

                        }
                        //calculamos el promedio de respuesta por pregunta usando los promedios por encuesta
                        float promedioPreguntaAux = 0;
                        int cantidadEncuestas = encuestas.size();
                        for (int m = 0; m < cantidadEncuestas; m++) {
                            promedioPreguntaAux += promediorespuestasxencuesta[m];
                        }
                        promedioPregunta = (float) promedioPreguntaAux / encuestas.size();
                        promediorespuestas[l] = (float) (Math.rint(promedioPregunta * 10) / 10);

                    }
                    int numPre = 0;
                    for (int k = 0; k < promediorespuestas.length; k++) {
                        if (promediorespuestas[k] > 0) {
                            calificacionPer += promediorespuestas[k];
                            numPre++;
                        }
                    }
                    cumplimientoxindicador[j] = (float) calificacionPer / numPre;
                }
            }
            cumplimientoxindicador[j] = (float) (Math.rint(cumplimientoxindicador[j] * 10) / 10);
        }
        long t1 = System.currentTimeMillis();
        long t3 = (t1 - t0);
        System.out.println("el tiempo que demora detallePCaracteristica es: " + t3);
        sesion.setAttribute("indicadores", indicadores);
        sesion.setAttribute("cumplimientoIN", cumplimientoxindicador);
        sesion.setAttribute("caracteristica", c);
        return "/WEB-INF/vista/comitePrograma/proceso/informe/detalleCaracteristicaP.jsp";
    }

    private CaracteristicaFacade lookupCaracteristicaFacadeBean() {
        try {
            Context c = new InitialContext();
            return (CaracteristicaFacade) c.lookup("java:global/autoevaluacion/CaracteristicaFacade!com.autoeval.ejb.CaracteristicaFacade");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
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
}
