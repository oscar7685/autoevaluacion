/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.autoeval.actions;

import com.autoeval.ejb.PonderacioncaracteristicaFacade;
import com.autoeval.ejb.PonderacionfactorFacade;
import com.autoeval.ejb.ResultadoevaluacionFacade;
import com.autoeval.entity.Caracteristica;
import com.autoeval.entity.Encuesta;
import com.autoeval.entity.Factor;
import com.autoeval.entity.Indicador;
import com.autoeval.entity.Instrumento;
import com.autoeval.entity.Modelo;
import com.autoeval.entity.Ponderacioncaracteristica;
import com.autoeval.entity.Ponderacionfactor;
import com.autoeval.entity.Pregunta;
import com.autoeval.entity.Proceso;
import com.autoeval.entity.Resultadoevaluacion;
import com.autoeval.interfaz.Action;
import java.io.IOException;
import java.util.ArrayList;
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
public class InformeMatrizFactoresP implements Action {
    PonderacionfactorFacade ponderacionfactorFacade = lookupPonderacionfactorFacadeBean();
    PonderacioncaracteristicaFacade ponderacioncaracteristicaFacade = lookupPonderacioncaracteristicaFacadeBean();
    ResultadoevaluacionFacade resultadoevaluacionFacade = lookupResultadoevaluacionFacadeBean();

    @Override
    public String procesar(HttpServletRequest request) throws IOException, ServletException {
        HttpSession sesion = request.getSession();
        Proceso p= (Proceso) sesion.getAttribute("Proceso");
        Modelo mod = p.getModeloId();

        float sumaPon;
        float suma2;
        float calificacionPer;

        List<Ponderacionfactor> ponderacionesF = new ArrayList<Ponderacionfactor>();
        List<Factor> factores = mod.getFactorList();
        float cumplimientoF[] = new float[factores.size()];
        for (int i2 = 0; i2 < factores.size(); i2++) {
            suma2 = 0;
            sumaPon = 0;
            List<Caracteristica> caracteristicas = factores.get(i2).getCaracteristicaList();
            float cumplimientoC2[] = new float[caracteristicas.size()];
            for (int j = 0; j < caracteristicas.size(); j++) {
                List<Indicador> indicadores = caracteristicas.get(j).getIndicadorList();
                float cumplimiento[] = new float[indicadores.size()];
                for (int k = 0; k < indicadores.size(); k++) {
                    calificacionPer = 0;
                    List<Instrumento> instr = indicadores.get(k).getInstrumentoList();
                    for (int i = 0; i < instr.size(); i++) {
                        Instrumento instrumento = instr.get(i);
                        if (instrumento.getId() == 1) {

                            //RECUPERAMOS LAS PREGUNTAS DEL ACTUAL INDICADOR
                            List<Pregunta> preguntas = indicadores.get(k).getPreguntaList();
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
                            for (int q = 0; q < promediorespuestas.length; q++) {
                                if (promediorespuestas[q] > 0) {
                                    calificacionPer += promediorespuestas[q];
                                    numPre++;
                                }
                            }
                            cumplimiento[k] = (float) calificacionPer / numPre;
                        }
                    }
                    cumplimiento[k] = (float) (Math.rint(cumplimiento[k] * 10) / 10);
                }
                float sumaCumplimientoIndicadores = 0;
                int calificados = 0;
                for (int i = 0; i < cumplimiento.length; i++) {
                    if (cumplimiento[i] > 0.0) {
                        sumaCumplimientoIndicadores += cumplimiento[i];
                        calificados++;
                    }
                }
                if (sumaCumplimientoIndicadores > 0) {
                    cumplimientoC2[j] = (float) sumaCumplimientoIndicadores / calificados;
                    cumplimientoC2[j] = (float) (Math.rint(cumplimientoC2[j] * 10) / 10);
                }


            }
            for (int i = 0; i < factores.get(i2).getCaracteristicaList().size(); i++) {
                if (cumplimientoC2[i] != 0) {
                    Ponderacioncaracteristica pc = ponderacioncaracteristicaFacade.findByCaracteristicaYProceso(factores.get(i2).getCaracteristicaList().get(i), p);
                    sumaPon += pc.getPonderacion();
                    suma2 += cumplimientoC2[i] * pc.getPonderacion();
                }
            }
            if (sumaPon != 0) {
                ponderacionesF.add(ponderacionfactorFacade.findByFactorYProceso(factores.get(i2), p));
                cumplimientoF[i2] = suma2 / sumaPon;
                cumplimientoF[i2] = (float) (Math.rint(cumplimientoF[i2] * 10) / 10);
            }

        }
        sesion.setAttribute("factores", factores);
        sesion.setAttribute("ponderacionesF", ponderacionesF);
        sesion.setAttribute("cumplimientoF", cumplimientoF);

        return "/WEB-INF/vista/comitePrograma/proceso/informe/matrizFactoresP.jsp";
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

    private PonderacioncaracteristicaFacade lookupPonderacioncaracteristicaFacadeBean() {
        try {
            Context c = new InitialContext();
            return (PonderacioncaracteristicaFacade) c.lookup("java:global/autoevaluacion/PonderacioncaracteristicaFacade!com.autoeval.ejb.PonderacioncaracteristicaFacade");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private PonderacionfactorFacade lookupPonderacionfactorFacadeBean() {
        try {
            Context c = new InitialContext();
            return (PonderacionfactorFacade) c.lookup("java:global/autoevaluacion/PonderacionfactorFacade!com.autoeval.ejb.PonderacionfactorFacade");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
