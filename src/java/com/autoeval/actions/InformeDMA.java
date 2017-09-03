/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.autoeval.actions;

import com.autoeval.ejb.CaracteristicaFacade;
import com.autoeval.ejb.EncabezadoFacade;
import com.autoeval.ejb.EncuestaFacade;
import com.autoeval.ejb.FuenteFacade;
import com.autoeval.ejb.ModeloFacade;
import com.autoeval.ejb.MuestraadministrativoFacade;
import com.autoeval.ejb.MuestradirectorFacade;
import com.autoeval.ejb.MuestradocenteFacade;
import com.autoeval.ejb.MuestraegresadoFacade;
import com.autoeval.ejb.MuestraempleadorFacade;
import com.autoeval.ejb.MuestraestudianteFacade;
import com.autoeval.ejb.MuestrapersonaFacade;
import com.autoeval.ejb.ResultadoevaluacionFacade;
import com.autoeval.entity.Caracteristica;
import com.autoeval.entity.Encabezado;
import com.autoeval.entity.Encuesta;
import com.autoeval.entity.Fuente;
import com.autoeval.entity.Modelo;
import com.autoeval.entity.Muestra;
import com.autoeval.entity.Pregunta;
import com.autoeval.entity.Proceso;
import com.autoeval.entity.Programa;
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
public class InformeDMA implements Action {

    ResultadoevaluacionFacade resultadoevaluacionFacade = lookupResultadoevaluacionFacadeBean();
    EncuestaFacade encuestaFacade = lookupEncuestaFacadeBean();
    FuenteFacade fuenteFacade = lookupFuenteFacadeBean();
    ModeloFacade modeloFacade = lookupModeloFacadeBean();
    CaracteristicaFacade caracteristicaFacade = lookupCaracteristicaFacadeBean();
    EncabezadoFacade encabezadoFacade = lookupEncabezadoFacadeBean();

    @Override
    public String procesar(HttpServletRequest request) throws IOException, ServletException {
        HttpSession sesion = request.getSession();
        Proceso p = (Proceso) sesion.getAttribute("Proceso");
        if (p.getModeloId().getId() == 1) {

            Proceso proceso = (Proceso) sesion.getAttribute("Proceso");
            Modelo m = modeloFacade.find(Integer.parseInt("1"));
            List<Caracteristica> caractesticas = caracteristicaFacade.findByModeloOptimizada(m);

            List<List<Encuesta>> encuestas = new ArrayList<List<Encuesta>>(); //preguntasxencuestas

            String[][] resultados = new String[266][12];
            String[][] cerillos = new String[266][12];

            int[] relacionEncuestaFuente = {0, 1, 8, 7, 2, 11, 5, 3, 4, 9, 10, 6};

            List<Fuente> fuentes = fuenteFacade.findAll();

            for (int i = 0; i < 258; i++) {
                for (int j = 0; j < 12; j++) {
                    resultados[i][j] = "-1";
                }
            }
            for (Caracteristica caracteristica : caractesticas) {
                if (caracteristica.getPreguntaList().size() > 0) {
                    List<Pregunta> preguntas = caracteristica.getPreguntaList();
                    for (Pregunta pregunta : preguntas) {
                        if(pregunta.getCodigo().equals("61")){
                          System.out.println("preguntaId: "+pregunta.getCodigo());
                        }
                      
                        if (pregunta.getPreguntaList().size() > 0) {

                            List<Encuesta> encuestasDondeAplica = encuestaFacade.findByPreguntaYModelo(m, pregunta);
                            encuestas.add(encuestasDondeAplica);

                            for (Encuesta encuesta : encuestasDondeAplica) {
                                Fuente f = null;
                                f = fuenteFacade.find(relacionEncuestaFuente[encuesta.getId()]);

                                for (int i = 0; i < pregunta.getPreguntaList().size(); i++) {

                                    List<Resultadoevaluacion> rs = resultadoevaluacionFacade.findByProcesoPreguntaFuente(proceso, pregunta.getPreguntaList().get(i), f);
                                    int cuatros = 0, cincos = 0, ceros = 0;
                                    for (Resultadoevaluacion resultadoevaluacion : rs) {
                                        if (resultadoevaluacion.getRespuesta().equals("0")) {
                                            ceros++;
                                        } else if (resultadoevaluacion.getRespuesta().equals("4")) {
                                            cuatros++;
                                        } else if (resultadoevaluacion.getRespuesta().equals("5")) {
                                            cincos++;
                                        }
                                    }
                                    if (rs.isEmpty()) {
                                        // resultados[pregunta.getPreguntaList().get(i).getId()][rol.getId()] = "-1";
                                    } else {
                                        double dma = (double) ((cincos + cuatros) * 100) / rs.size();
                                        double cerosPorcentaje = (double) ((ceros) * 100) / rs.size();
                                        System.out.println("dma: "+dma);
                                        System.out.println("f.getId(): "+f.getId());
                                        System.out.println("pregunta.getPreguntaList().get(i).getId(): "+pregunta.getPreguntaList().get(i).getId());
                                        
                                        resultados[pregunta.getPreguntaList().get(i).getId()][f.getId()] = "" + dma + "";
                                        cerillos[pregunta.getPreguntaList().get(i).getId()][f.getId()] = "" + cerosPorcentaje;
                                    }
                                    //resultados[pregunta.getPreguntaList().get(i).getId()][1] = "" + rs.size() + "," + cincos + "," + cuatros + "," + tres + "," + dos + "," + unos + "," + ceros;
                                    //System.out.println(rs.size() + "," + cincos + "," + cuatros + "," + tres + "," + dos + "," + unos + "," + ceros);

                                }
                            }

                        } else {
                            List<Encuesta> encuestasDondeAplica = encuestaFacade.findByPreguntaYModelo(m, pregunta);
                            encuestas.add(encuestasDondeAplica);
                            for (Encuesta encuesta : encuestasDondeAplica) {
                                Fuente f = null;
                                f = fuenteFacade.find(relacionEncuestaFuente[encuesta.getId()]);

                                List<Resultadoevaluacion> rs = resultadoevaluacionFacade.findByProcesoPreguntaFuente(proceso, pregunta, f);
                                int cuatros = 0, cincos = 0, ceros = 0;
                                for (Resultadoevaluacion resultadoevaluacion : rs) {
                                    if (resultadoevaluacion.getRespuesta().equals("0")) {
                                        ceros++;
                                    } else if (resultadoevaluacion.getRespuesta().equals("4")) {
                                        cuatros++;
                                    } else if (resultadoevaluacion.getRespuesta().equals("5")) {
                                        cincos++;
                                    }
                                }
                                if (rs.isEmpty()) {
                                    // resultados[pregunta.getPreguntaList().get(i).getId()][rol.getId()] = "-1";
                                } else {
                                    double dma = (double) ((cincos + cuatros) * 100) / rs.size();
                                    double cerosPorcentaje = (double) ((ceros) * 100) / rs.size();
                                    resultados[pregunta.getId()][f.getId()] = "" + dma + "";
                                    cerillos[pregunta.getId()][f.getId()] = "" + cerosPorcentaje;
                                }
                            }
                        }
                    }
                }
            }
            sesion.setAttribute("resultados", resultados);
            sesion.setAttribute("cerillos", cerillos);

            sesion.setAttribute("caractesticas", caractesticas);
            sesion.setAttribute("encuestas", encuestas);
            return "/WEB-INF/vista/comitePrograma/proceso/informe/dmaInstitucional.jsp";
        } else {
            String url = "/WEB-INF/vista/comitePrograma/proceso/informe/dmaProgramas.jsp";

            Proceso proceso = (Proceso) sesion.getAttribute("Proceso");
            Programa prog = proceso.getProgramaId();
            String tipoF = prog.getTipoformacion();
            Modelo m = proceso.getModeloId();
            List<Caracteristica> caractesticas = caracteristicaFacade.findByModeloOptimizada(m);
            String[][] resultados = new String[258][12];
            String[][] cerillos = new String[258][12];
            List<Fuente> fuentes = fuenteFacade.findAll();
            for (Caracteristica caracteristica : caractesticas) {
                if (caracteristica.getPreguntaList().size() > 0) {
                    List<Pregunta> preguntas = caracteristica.getPreguntaList();
                    for (Pregunta pregunta : preguntas) {
                        if (pregunta.getPreguntaList().size() > 0) {
                            for (int i = 0; i < pregunta.getPreguntaList().size(); i++) {// Si la pregunta tiene subpreguntas
                                for (Fuente fuente : fuentes) {
                                    List<Resultadoevaluacion> rs = null;
                                    rs = resultadoevaluacionFacade.findByProcesoPreguntaFuente(proceso, pregunta.getPreguntaList().get(i), fuente);
                                    int cuatros = 0, cincos = 0, ceros = 0;
                                    for (Resultadoevaluacion respuestas : rs) {
                                        if (respuestas.getRespuesta().equals("0")) {
                                            ceros++;
                                        } else if (respuestas.getRespuesta().equals("4")) {
                                            cuatros++;
                                        } else if (respuestas.getRespuesta().equals("5")) {
                                            cincos++;
                                        }
                                    }
                                    if (rs.isEmpty()) {
                                        resultados[pregunta.getPreguntaList().get(i).getId()][fuente.getId()] = "-1";
                                    } else {
                                        double dma = (double) ((cincos + cuatros) * 100) / rs.size();
                                        double cerosPorcentaje = (double) ((ceros) * 100) / rs.size();
                                        resultados[pregunta.getPreguntaList().get(i).getId()][fuente.getId()] = "" + dma + "";
                                        cerillos[pregunta.getPreguntaList().get(i).getId()][fuente.getId()] = "" + cerosPorcentaje;

                                    }
                                }
                            }
                        } else { // Si la pregunta NO tiene subpreguntas
                            for (Fuente fuente : fuentes) {
                                List<Resultadoevaluacion> rs = null;
                                rs = resultadoevaluacionFacade.findByProcesoPreguntaFuente(proceso, pregunta, fuente);
                                int cuatros = 0, cincos = 0, ceros = 0;
                                for (Resultadoevaluacion respuestas : rs) {
                                    if (respuestas.getRespuesta().equals("0")) {
                                        ceros++;
                                    } else if (respuestas.getRespuesta().equals("4")) {
                                        cuatros++;
                                    } else if (respuestas.getRespuesta().equals("5")) {
                                        cincos++;
                                    }
                                }
                                if (rs.isEmpty()) {
                                    resultados[pregunta.getId()][fuente.getId()] = "-1";
                                } else {
                                    double dma = (double) ((cincos + cuatros) * 100) / rs.size();
                                    double cerosPorcentaje = (double) ((ceros) * 100) / rs.size();
                                    resultados[pregunta.getId()][fuente.getId()] = "" + dma + "";
                                    cerillos[pregunta.getId()][fuente.getId()] = "" + cerosPorcentaje;

                                }
                            }
                        }
                    }
                }
            }
            sesion.setAttribute("resultados", resultados);
            sesion.setAttribute("cerillos", cerillos);
            sesion.setAttribute("tipoF", tipoF);
            sesion.setAttribute("caractesticas", caractesticas);
            return "/WEB-INF/vista/comitePrograma/proceso/informe/dmaProgramas.jsp";
        }

    }

    private MuestrapersonaFacade lookupMuestrapersonaFacadeBean() {
        try {
            Context c = new InitialContext();
            return (MuestrapersonaFacade) c.lookup("java:global/autoevaluacion/MuestrapersonaFacade!com.autoeval.ejb.MuestrapersonaFacade");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private MuestraestudianteFacade lookupMuestraestudianteFacadeBean() {
        try {
            Context c = new InitialContext();
            return (MuestraestudianteFacade) c.lookup("java:global/autoevaluacion/MuestraestudianteFacade!com.autoeval.ejb.MuestraestudianteFacade");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private MuestradocenteFacade lookupMuestradocenteFacadeBean() {
        try {
            Context c = new InitialContext();
            return (MuestradocenteFacade) c.lookup("java:global/autoevaluacion/MuestradocenteFacade!com.autoeval.ejb.MuestradocenteFacade");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private MuestraegresadoFacade lookupMuestraegresadoFacadeBean() {
        try {
            Context c = new InitialContext();
            return (MuestraegresadoFacade) c.lookup("java:global/autoevaluacion/MuestraegresadoFacade!com.autoeval.ejb.MuestraegresadoFacade");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private MuestraadministrativoFacade lookupMuestraadministrativoFacadeBean() {
        try {
            Context c = new InitialContext();
            return (MuestraadministrativoFacade) c.lookup("java:global/autoevaluacion/MuestraadministrativoFacade!com.autoeval.ejb.MuestraadministrativoFacade");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private MuestradirectorFacade lookupMuestradirectorFacadeBean() {
        try {
            Context c = new InitialContext();
            return (MuestradirectorFacade) c.lookup("java:global/autoevaluacion/MuestradirectorFacade!com.autoeval.ejb.MuestradirectorFacade");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private MuestraempleadorFacade lookupMuestraempleadorFacadeBean() {
        try {
            Context c = new InitialContext();
            return (MuestraempleadorFacade) c.lookup("java:global/autoevaluacion/MuestraempleadorFacade!com.autoeval.ejb.MuestraempleadorFacade");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private EncabezadoFacade lookupEncabezadoFacadeBean() {
        try {
            Context c = new InitialContext();
            return (EncabezadoFacade) c.lookup("java:global/autoevaluacion/EncabezadoFacade!com.autoeval.ejb.EncabezadoFacade");
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

    private ModeloFacade lookupModeloFacadeBean() {
        try {
            Context c = new InitialContext();
            return (ModeloFacade) c.lookup("java:global/autoevaluacion/ModeloFacade!com.autoeval.ejb.ModeloFacade");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private FuenteFacade lookupFuenteFacadeBean() {
        try {
            Context c = new InitialContext();
            return (FuenteFacade) c.lookup("java:global/autoevaluacion/FuenteFacade!com.autoeval.ejb.FuenteFacade");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private EncuestaFacade lookupEncuestaFacadeBean() {
        try {
            Context c = new InitialContext();
            return (EncuestaFacade) c.lookup("java:global/autoevaluacion/EncuestaFacade!com.autoeval.ejb.EncuestaFacade");
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
