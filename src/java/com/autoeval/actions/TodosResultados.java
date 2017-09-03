/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.autoeval.actions;

import com.autoeval.ejb.CaracteristicaFacade;
import com.autoeval.ejb.FactorFacade;
import com.autoeval.ejb.IndicadorFacade;
import com.autoeval.ejb.NumericadocumentalFacade;
import com.autoeval.ejb.PonderacioncaracteristicaFacade;
import com.autoeval.entity.Caracteristica;
import com.autoeval.entity.Encuesta;
import com.autoeval.entity.Factor;
import com.autoeval.entity.Indicador;
import com.autoeval.entity.Instrumento;
import com.autoeval.entity.Modelo;
import com.autoeval.entity.Numericadocumental;
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
public class TodosResultados implements Action{
    NumericadocumentalFacade numericadocumentalFacade = lookupNumericadocumentalFacadeBean();
    PonderacioncaracteristicaFacade ponderacioncaracteristicaFacade = lookupPonderacioncaracteristicaFacadeBean();
    CaracteristicaFacade caracteristicaFacade = lookupCaracteristicaFacadeBean();
    FactorFacade factorFacade = lookupFactorFacadeBean();
    IndicadorFacade indicadorFacade = lookupIndicadorFacadeBean();

    @Override
    public String procesar(HttpServletRequest request) throws IOException, ServletException {
        HttpSession sesion = request.getSession();
        Proceso proceso = (Proceso) sesion.getAttribute("Proceso");
        Modelo modelo = (Modelo) sesion.getAttribute("Modelo");
        List<Indicador> indicadores = indicadorFacade.findByModeloYenOrden(modelo);

            double[] numerico = new double[indicadores.size()]; //numerica x indicador
            double[] documental = new double[indicadores.size()]; //documental x indicador
            double[] promedioE = new double[indicadores.size()]; //encuesta x indicador

            List<Factor> factores = factorFacade.findByModelo(modelo);
            List<Caracteristica> caracteristicas = caracteristicaFacade.findByModelo(modelo);

            List<Ponderacionfactor> ponderacionesF = new ArrayList<Ponderacionfactor>(); //ponderacion de los factores
            float cumplimientoF[] = new float[factores.size()];
            double ponderacionF[] = new double[factores.size()];

            float cumplimientoC[] = new float[caracteristicas.size()]; //cumplimiento x caracteristica
            double ponderacionC[] = new double[caracteristicas.size()]; //ponderacion x caracteristica
            float cumplimientoI[] = new float[indicadores.size()]; //cumplimiento indicadores

            float promedioPregunta = 0;
            float suma;
            float numP;

            List<List> PromedioPreguntasXindicador = new ArrayList<List>();
            List<List> PromedioPreguntasXindicadorEs = new ArrayList<List>();
            List<List> PromedioPreguntasXindicadorDo = new ArrayList<List>();
            List<List> PromedioPreguntasXindicadorAd = new ArrayList<List>();
            List<List> PromedioPreguntasXindicadorEg = new ArrayList<List>();
            List<List> PromedioPreguntasXindicadorDi = new ArrayList<List>();
            List<List> PromedioPreguntasXindicadorEm = new ArrayList<List>();

            int caracActual = indicadores.get(0).getCaracteristicaId().getId();
            int factorActual = indicadores.get(0).getCaracteristicaId().getFactorId().getId();
            double ponderacionCActual = ponderacioncaracteristicaFacade.findByCaracteristicaYProceso(indicadores.get(0).getCaracteristicaId(), proceso).getPonderacion();
            float sumaPonC = 0;
            float sumaC = 0;
            float sumaI = 0;
            float numI = 0;

            float calificacionNum = 0;
            float calificacionDoc = 0;
            float promedioEstudiantes, promedioDocentes, promedioAdmin, promedioEgre, promedioDire, promedioEmpl;
            int numEst, numDoc, numAdmi, numEgr, numDire, numEmp;
            int indice = 0;
            int indiceCarac = 0;
            int indiceFactor = 0;
            float sumaPromediosXpregunta;
            for (Indicador in : indicadores) {
                List promedioxPregunta = new ArrayList();
                List promedioxPreguntaEs = new ArrayList();
                List promedioxPreguntaDo = new ArrayList();
                List promedioxPreguntaAd = new ArrayList();
                List promedioxPreguntaEg = new ArrayList();
                List promedioxPreguntaDi = new ArrayList();
                List promedioxPreguntaEm = new ArrayList();
                sumaPromediosXpregunta = 0;
                suma = 0;
                numP = 0;
                calificacionNum = 0;
                calificacionDoc = 0;
                promedioPregunta = 0;
                List<Instrumento> instr = in.getInstrumentoList();
                for (int i = 0; i < instr.size(); i++) {
                    Instrumento instrumento = instr.get(i);
                    if (instrumento.getId() == 1) {
                        List<Pregunta> preguntas = in.getPreguntaList();
                        float promediorespuestas[] = new float[preguntas.size()];
                        int ceros[] = new int[preguntas.size()];
                        int unos[] = new int[preguntas.size()];
                        int dos[] = new int[preguntas.size()];
                        int tres[] = new int[preguntas.size()];
                        int cuatros[] = new int[preguntas.size()];
                        int cincos[] = new int[preguntas.size()];

                        for (int l = 0; l < preguntas.size(); l++) {
                            suma = 0;
                            int numR = 0;
                            Pregunta pregunta = preguntas.get(l);
                            promedioPregunta = 0;

                            List<Resultadoevaluacion> respuestas = pregunta.getResultadoevaluacionList();
                            List<Encuesta> encuestaPregunta = pregunta.getEncuestaList();
                            promedioEstudiantes = 0;
                            promedioDocentes = 0;
                            promedioAdmin = 0;
                            promedioEgre = 0;
                            promedioDire = 0;
                            promedioEmpl = 0;
                            numEst = 0;
                            numDoc = 0;
                            numAdmi = 0;
                            numEgr = 0;
                            numDire = 0;
                            numEmp = 0;
                            for (int n = 0; n < respuestas.size(); n++) {
                                if (respuestas.get(n).getEncabezadoId().getEstado().equals("terminado") && respuestas.get(n).getEncabezadoId().getProcesoId().getId() == proceso.getId()
                                        && respuestas.get(n).getRespuesta() != null && (respuestas.get(n).getRespuesta().equals("1") || respuestas.get(n).getRespuesta().equals("2")
                                        || respuestas.get(n).getRespuesta().equals("3") || respuestas.get(n).getRespuesta().equals("4") || respuestas.get(n).getRespuesta().equals("5"))) {

                                    if (respuestas.get(n).getEncabezadoId().getFuenteId().getId() == 1) {
                                        numEst++;
                                        promedioEstudiantes += Integer.parseInt(respuestas.get(n).getRespuesta());
                                    } else if (respuestas.get(n).getEncabezadoId().getFuenteId().getId() == 2) {
                                        numDoc++;
                                        promedioDocentes += Integer.parseInt(respuestas.get(n).getRespuesta());
                                    } else if (respuestas.get(n).getEncabezadoId().getFuenteId().getId() == 3) {
                                        numAdmi++;
                                        promedioAdmin += Integer.parseInt(respuestas.get(n).getRespuesta());
                                    } else if (respuestas.get(n).getEncabezadoId().getFuenteId().getId() == 4) {
                                        numEgr++;
                                        promedioEgre += Integer.parseInt(respuestas.get(n).getRespuesta());
                                    } else if (respuestas.get(n).getEncabezadoId().getFuenteId().getId() == 5) {
                                        numDire++;
                                        promedioDire += Integer.parseInt(respuestas.get(n).getRespuesta());
                                    } else if (respuestas.get(n).getEncabezadoId().getFuenteId().getId() == 6) {
                                        numEmp++;
                                        promedioEmpl += Integer.parseInt(respuestas.get(n).getRespuesta());
                                    }

                                    suma += Integer.parseInt(respuestas.get(n).getRespuesta());
                                    numR++;
                                }
                            }

                            float promedioXpregunta = suma / numR;
                            if (numEst != 0) {
                                promedioEstudiantes /= numEst;
                            }
                            if (numDoc != 0) {
                                promedioDocentes /= numDoc;
                            }
                            if (numAdmi != 0) {
                                promedioAdmin /= numAdmi;
                            }
                            if (numEgr != 0) {
                                promedioEgre /= numEgr;
                            }
                            if (numDire != 0) {
                                promedioDire /= numDire;
                            }
                            if (numEmp != 0) {
                                promedioEmpl /= numEmp;
                            }

                            sumaPromediosXpregunta += promedioXpregunta;
                            promedioxPregunta.add((Math.rint(promedioXpregunta * 10) / 10));
                            promedioxPreguntaEs.add((Math.rint(promedioEstudiantes * 10) / 10));
                            promedioxPreguntaDo.add((Math.rint(promedioDocentes * 10) / 10));
                            promedioxPreguntaAd.add((Math.rint(promedioAdmin * 10) / 10));
                            promedioxPreguntaEg.add((Math.rint(promedioEgre * 10) / 10));
                            promedioxPreguntaDi.add((Math.rint(promedioDire * 10) / 10));
                            promedioxPreguntaEm.add((Math.rint(promedioEmpl * 10) / 10));
                        }
                        if (suma > 0) {
                            promedioPregunta = (float) sumaPromediosXpregunta / preguntas.size();
                            //promediorespuestas[l] = (float) (Math.rint(promedioPregunta * 10) / 10);
                            promedioE[indice] = (double) (Math.rint(promedioPregunta * 10) / 10);
                        }
                    } else {
                        if (instrumento.getId() == 2) {
                            Numericadocumental numDoc1 = numericadocumentalFacade.findBySingle3("indicadorId", in, "procesoId", proceso, "instrumentoId", instrumento);
                            calificacionNum = numDoc1.getEvaluacion();
                            numerico[indice] = calificacionNum;

                        } else {
                            if (instrumento.getId() == 3) {
                                Numericadocumental numDoc2 = numericadocumentalFacade.findBySingle3("indicadorId", in, "procesoId", proceso, "instrumentoId", instrumento);
                                calificacionDoc = numDoc2.getEvaluacion();
                                documental[indice] = calificacionDoc;

                            }
                        }
                    }
                }
                if (calificacionNum != 0 && calificacionDoc != 0 && promedioPregunta != 0) {
                    cumplimientoI[indice] = (promedioPregunta + calificacionNum + calificacionDoc) / 3;
                } else {
                    if (calificacionNum != 0 && promedioPregunta != 0) {
                        cumplimientoI[indice] = (calificacionNum + promedioPregunta) / 2;
                    } else {
                        if (calificacionDoc != 0 && promedioPregunta != 0) {
                            cumplimientoI[indice] = (promedioPregunta + calificacionDoc) / 2;
                        } else {
                            if (calificacionDoc != 0 && calificacionNum != 0) {
                                cumplimientoI[indice] = (calificacionNum + calificacionDoc) / 2;
                            } else {
                                if (calificacionDoc != 0) {
                                    cumplimientoI[indice] = calificacionDoc;
                                } else {
                                    if (calificacionNum != 0) {
                                        cumplimientoI[indice] = calificacionNum;
                                    } else {
                                        if (promedioPregunta != 0) {
                                            cumplimientoI[indice] = promedioPregunta;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                cumplimientoI[indice] = (float) (Math.rint(cumplimientoI[indice] * 10) / 10); //cumplimiento de cada indicador

                if (in.getCaracteristicaId().getId() == caracActual) {
                    if (cumplimientoI[indice] != 0) {
                        numI++;//contamos los indicadores de una misma caracteristica
                        sumaI += cumplimientoI[indice];//sumamos los cumplimientos de los indicadores de una misma caracteristica
                    }

                } else {
                    //cumplimiento de caracteristicas tomando en cuenta solo los indicadores antes del cambio

                    cumplimientoC[indiceCarac] = sumaI / numI;
                    cumplimientoC[indiceCarac] = (float) (Math.rint(cumplimientoC[indiceCarac] * 10) / 10);
                    sumaPonC += ponderacionCActual; //vamos sumando las ponderaciones de las caracteristicas hasta antes del cambio
                    sumaC += cumplimientoC[indiceCarac] * ponderacionCActual; //vamos sumando los cumplimientos de cada una de las caracteristicas

                    //cambiamos la ponderacion a la actual
                    ponderacionCActual = ponderacioncaracteristicaFacade.findByCaracteristicaYProceso(indicadores.get(0).getCaracteristicaId(), proceso).getPonderacion();
                    ponderacionC[indiceCarac] = ponderacionCActual;
                    indiceCarac++; //acabamos de entrar a una nueva caracteristica
                    caracActual = in.getCaracteristicaId().getId();//sacamos el id de la caracteristica actual

                    sumaI = cumplimientoI[indice];//inicializamos la suma de cumplimientos xq acabamos de cambiar de caracteristica
                    numI = 1;//inicializamos en 1 el numero de indicadores
                }

                if (in.getCaracteristicaId().getFactorId().getId() != factorActual) {
                    if (sumaPonC != 0) {
                        cumplimientoF[indiceFactor] = sumaC / sumaPonC;//sacamos el cumplimiento del factor inmediatamente anterior
                        cumplimientoF[indiceFactor] = (float) (Math.rint(cumplimientoF[indiceFactor] * 10) / 10);
                    }
                    sumaPonC = 0;
                    sumaC = 0;
                    indiceFactor++;
                    factorActual = in.getCaracteristicaId().getFactorId().getId();//actualizamos el factor
                }
                indice++;
                if (indice == indicadores.size()) {
                    cumplimientoC[indiceCarac] = sumaI / numI;  //cumplimiento de la ultima caracteristica
                    ponderacionCActual = ponderacioncaracteristicaFacade.findByCaracteristicaYProceso(indicadores.get(0).getCaracteristicaId(), proceso).getPonderacion();
                    ponderacionC[indiceCarac] = ponderacionCActual;
                    sumaPonC += ponderacionCActual;
                    sumaC += cumplimientoC[indiceCarac] * ponderacionCActual;
                    if (sumaPonC != 0) {
                        cumplimientoF[indiceFactor] = sumaC / sumaPonC;//cumplimiento del ultimo factor
                        cumplimientoF[indiceFactor] = (float) (Math.rint(cumplimientoF[indiceFactor] * 10) / 10);
                    }
                }
                PromedioPreguntasXindicador.add(promedioxPregunta);
                PromedioPreguntasXindicadorEs.add(promedioxPreguntaEs);
                PromedioPreguntasXindicadorDo.add(promedioxPreguntaDo);
                PromedioPreguntasXindicadorAd.add(promedioxPreguntaAd);
                PromedioPreguntasXindicadorEg.add(promedioxPreguntaEg);
                PromedioPreguntasXindicadorDi.add(promedioxPreguntaDi);
                PromedioPreguntasXindicadorEm.add(promedioxPreguntaEm);
            }
            sesion.setAttribute("ponderacionesF", ponderacionesF);
            sesion.setAttribute("cumplimientoF", cumplimientoF);
            sesion.setAttribute("ponderacionesC", ponderacionC);
            sesion.setAttribute("cumplimientoC", cumplimientoC);
            sesion.setAttribute("cumplimientoI", cumplimientoI);
            sesion.setAttribute("listIndicadores", indicadores);
            sesion.setAttribute("promedioE", promedioE);
            sesion.setAttribute("PromedioPreguntasXindicadorEs", PromedioPreguntasXindicadorEs);
            sesion.setAttribute("PromedioPreguntasXindicadorDo", PromedioPreguntasXindicadorDo);
            sesion.setAttribute("PromedioPreguntasXindicadorAd", PromedioPreguntasXindicadorAd);
            sesion.setAttribute("PromedioPreguntasXindicadorEg", PromedioPreguntasXindicadorEg);
            sesion.setAttribute("PromedioPreguntasXindicadorDi", PromedioPreguntasXindicadorDi);
            sesion.setAttribute("PromedioPreguntasXindicadorEm", PromedioPreguntasXindicadorEm);
            sesion.setAttribute("PromedioPreguntasXindicador", PromedioPreguntasXindicador);

            sesion.setAttribute("numerico", numerico);
            sesion.setAttribute("documental", documental);

            return "/WEB-INF/vista/comitePrograma/proceso/informe/resultados.jsp";
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

    private FactorFacade lookupFactorFacadeBean() {
        try {
            Context c = new InitialContext();
            return (FactorFacade) c.lookup("java:global/autoevaluacion/FactorFacade!com.autoeval.ejb.FactorFacade");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
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

    private PonderacioncaracteristicaFacade lookupPonderacioncaracteristicaFacadeBean() {
        try {
            Context c = new InitialContext();
            return (PonderacioncaracteristicaFacade) c.lookup("java:global/autoevaluacion/PonderacioncaracteristicaFacade!com.autoeval.ejb.PonderacioncaracteristicaFacade");
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
