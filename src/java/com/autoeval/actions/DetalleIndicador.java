/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.autoeval.actions;

import com.autoeval.ejb.IndicadorFacade;
import com.autoeval.ejb.NumericadocumentalFacade;
import com.autoeval.ejb.ResultadoevaluacionFacade;
import com.autoeval.entity.Encuesta;
import com.autoeval.entity.Indicador;
import com.autoeval.entity.Instrumento;
import com.autoeval.entity.Numericadocumental;
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
public class DetalleIndicador implements Action{
    NumericadocumentalFacade numericadocumentalFacade = lookupNumericadocumentalFacadeBean();
    ResultadoevaluacionFacade resultadoevaluacionFacade = lookupResultadoevaluacionFacadeBean();
    IndicadorFacade indicadorFacade = lookupIndicadorFacadeBean();

    @Override
    public String procesar(HttpServletRequest request) throws IOException, ServletException {
        long t0 = System.currentTimeMillis();
            HttpSession sesion = request.getSession();
            sesion.setAttribute("numerico", null);
            sesion.setAttribute("documental", null);
            //RECUPERAMOS EL INDICADOR
            String idIndicador = request.getParameter("id");
            Indicador in = indicadorFacade.find(Integer.parseInt(idIndicador));
            Proceso p = (Proceso) sesion.getAttribute("Proceso");
            List<Instrumento> instr = in.getInstrumentoList();
            for (int i = 0; i < instr.size(); i++) {
                Instrumento instrumento = instr.get(i);
                //VERIFICAMOS QUE EL INDICADOR ESTE ASOCIADO AL INSTRUMENTO ENCUESTA
                if (instrumento.getId() == 1) {

                    //RECUPERAMOS LAS PREGUNTAS
                    List<Pregunta> preguntas = in.getPreguntaList();
                    float promedioPregunta, suma, numP;

                    //declaramos promedio de respuestas para cada preguntas
                    float promediorespuestas[] = new float[preguntas.size()];

                    //declaramos cantidades x respuesta por opcion de respuesta
                    int ceros[] = new int[preguntas.size()];
                    int unos[] = new int[preguntas.size()];
                    int dos[] = new int[preguntas.size()];
                    int tres[] = new int[preguntas.size()];
                    int cuatros[] = new int[preguntas.size()];
                    int cincos[] = new int[preguntas.size()];

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


                        //contamos cuantos 5,4,3,2,1,0 se respondieron sin importar las encuestas
                        List<Object[]> results = resultadoevaluacionFacade.findResultadosxPreguntaxEncuestaxProcesoxValor1(p, pregunta);

                        for (Object[] result : results) {
                            ceros[l] = ((Number) result[5]).intValue();
                            cincos[l] = ((Number) result[4]).intValue();
                            cuatros[l] = ((Number) result[3]).intValue();
                            tres[l] = ((Number) result[2]).intValue();
                            dos[l] = ((Number) result[1]).intValue();
                            unos[l] = ((Number) result[0]).intValue();
                        }
                    }

                    //enviamos los datos relevantes
                    sesion.setAttribute("promediorepuestas", promediorespuestas);
                    sesion.setAttribute("ceros", ceros);
                    sesion.setAttribute("unos", unos);
                    sesion.setAttribute("dos", dos);
                    sesion.setAttribute("tres", tres);
                    sesion.setAttribute("cuatros", cuatros);
                    sesion.setAttribute("cincos", cincos);
                } else {
                    if (instrumento.getId() == 2) {
                        Numericadocumental numDoc1 = numericadocumentalFacade.findBySingle3("indicadorId", in, "procesoId", p, "instrumentoId", instrumento);
                        sesion.setAttribute("numerico", numDoc1);

                    } else {
                        if (instrumento.getId() == 3) {
                            Numericadocumental numDoc2 = numericadocumentalFacade.findBySingle3("indicadorId", in, "procesoId", p, "instrumentoId", instrumento);
                            sesion.setAttribute("documental", numDoc2);

                        }
                    }
                }
            }
            sesion.setAttribute("indicador", in);

            long t1 = System.currentTimeMillis();
            long t3 = (t1 - t0);
            System.out.println("el tiempo que demora detalleIndicador es: " + t3);

            return "/WEB-INF/vista/comitePrograma/proceso/informe/detalleIndicador.jsp";
    }

    private IndicadorFacade lookupIndicadorFacadeBean() {
        try {
            Context c = new InitialContext();
            return (IndicadorFacade) c.lookup("java:global/autoevaluacion/IndicadorFacade!com.autoeval.ejb.IndicadorFacade");
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

    private NumericadocumentalFacade lookupNumericadocumentalFacadeBean() {
        try {
            Context c = new InitialContext();
            return (NumericadocumentalFacade) c.lookup("java:global/autoevaluacion/NumericadocumentalFacade!com.autoeval.ejb.NumericadocumentalFacade");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
}
