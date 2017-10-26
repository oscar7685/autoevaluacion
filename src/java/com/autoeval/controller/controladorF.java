/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.autoeval.controller;

import com.autoeval.ejb.AsignacionencuestaFacade;
import com.autoeval.ejb.EncabezadoFacade;
import com.autoeval.ejb.EncuestaFacade;
import com.autoeval.ejb.FuenteFacade;
import com.autoeval.ejb.GlosarioFacade;
import com.autoeval.ejb.ModeloFacade;
import com.autoeval.ejb.MuestrapersonaFacade;
import com.autoeval.ejb.ParticipanteFacade;
import com.autoeval.ejb.ParticipanteHasRolFacade;
import com.autoeval.ejb.ProgramaFacade;
import com.autoeval.ejb.RespuestasFacade;
import com.autoeval.ejb.ResultadoevaluacionFacade;
import com.autoeval.ejb.RolFacade;
import com.autoeval.entity.Asignacionencuesta;
import com.autoeval.entity.Encabezado;
import com.autoeval.entity.Encuesta;
import com.autoeval.entity.Fuente;
import com.autoeval.entity.Glosario;
import com.autoeval.entity.Modelo;
import com.autoeval.entity.Muestrapersona;
import com.autoeval.entity.Participante;
import com.autoeval.entity.ParticipanteHasRol;
import com.autoeval.entity.Persona;
import com.autoeval.entity.Pregunta;
import com.autoeval.entity.Proceso;
import com.autoeval.entity.Programa;
import com.autoeval.entity.Respuestas;
import com.autoeval.entity.Resultadoevaluacion;
import com.autoeval.entity.Rol;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

/**
 *
 * @author Ususario
 */
public class controladorF extends HttpServlet {

    @EJB
    private AsignacionencuestaFacade asignacionencuestaFacade;
    @EJB
    private ModeloFacade modeloFacade;
    @EJB
    private FuenteFacade fuenteFacade;
    @EJB
    private MuestrapersonaFacade muestrapersonaFacade;
    @EJB
    private RespuestasFacade respuestasFacade;
    @EJB
    private EncuestaFacade encuestaFacade;
    @EJB
    private ProgramaFacade programaFacade;
    @EJB
    private GlosarioFacade glosarioFacade;
    @EJB
    private ResultadoevaluacionFacade resultadoevaluacionFacade;
    @EJB
    private EncabezadoFacade encabezadoFacade;
    private final static String RESPONDER = "responderE";
    private final static String GUARDAR = "guardarE";
    private final Logger LOGGER = Logger.getLogger(controladorF.class);

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        String action = (String) request.getParameter("action");
        try {
            if (action.equals("indexF")) {
                String url = "/WEB-INF/vista/fuente/index.jsp";
                RequestDispatcher rd = request.getRequestDispatcher(url);
                rd.forward(request, response);

            } else {
                if (action.equals("responderEncuestaF")) {
                    Participante p = (Participante) session.getAttribute("participante");
                    List<ParticipanteHasRol> phr = p.getParticipanteHasRolList();
                    List<Encuesta> encuestaCombinar = new ArrayList<Encuesta>();
                    List<Programa> pregunta4 = new ArrayList<Programa>();
                    List<Programa> pregunta15 = new ArrayList<Programa>();
                    List<Programa> pregunta24 = new ArrayList<Programa>();
                    List<Programa> pregunta25 = new ArrayList<Programa>();
                    List<Programa> pregunta26 = new ArrayList<Programa>();
                    List<Programa> pregunta27 = new ArrayList<Programa>();
                    List<Programa> pregunta28 = new ArrayList<Programa>();
                    List<Programa> pregunta29 = new ArrayList<Programa>();
                    List<Programa> pregunta32 = new ArrayList<Programa>();
                    List<Programa> pregunta37 = new ArrayList<Programa>();
                    List<Programa> pregunta39 = new ArrayList<Programa>();
                    List<Programa> pregunta55 = new ArrayList<Programa>();
                    for (int i = 0; i < phr.size(); i++) {
                        Programa prog = phr.get(i).getProgramaId();
                        Rol rol = phr.get(i).getRolId();
                        Modelo m = null;
                        if (prog.getTipoformacion().equals("Institucional")) {
                            m = modeloFacade.find(1);
                            Fuente f = fuenteFacade.find(rol.getId());
                            Asignacionencuesta ae = asignacionencuestaFacade.findBySingle2("fuenteId", f, "modeloId", m);
                            Encuesta encuestaAplicar = ae.getEncuestaId();
                            encuestaCombinar.add(encuestaAplicar);
                        } else if (prog.getTipoformacion().equals("Pregrado")) {
                            m = modeloFacade.find(2);
                            Fuente f = fuenteFacade.find(rol.getId());
                            Asignacionencuesta ae = asignacionencuestaFacade.findBySingle2("fuenteId", f, "modeloId", m);
                            Encuesta encuestaAplicar = ae.getEncuestaId();
                            encuestaCombinar.add(encuestaAplicar);

                            List<Pregunta> preguntasPregrado = encuestaAplicar.getPreguntaList();
                            for (int j = 0; j < preguntasPregrado.size(); j++) {
                                if ("si".equals(preguntasPregrado.get(j).getRepetir())) {
                                    if (preguntasPregrado.get(j).getId() == 4 && !pregunta4.contains(prog)) {
                                        pregunta4.add(prog);
                                    } else if (preguntasPregrado.get(j).getId() == 15 && !pregunta15.contains(prog)) {
                                        pregunta15.add(prog);
                                    } else if (preguntasPregrado.get(j).getId() == 24 && !pregunta24.contains(prog)) {
                                        pregunta24.add(prog);
                                    } else if (preguntasPregrado.get(j).getId() == 25 && !pregunta25.contains(prog)) {
                                        pregunta25.add(prog);
                                    } else if (preguntasPregrado.get(j).getId() == 26 && !pregunta26.contains(prog)) {
                                        pregunta26.add(prog);
                                    } else if (preguntasPregrado.get(j).getId() == 27 && !pregunta27.contains(prog)) {
                                        pregunta27.add(prog);
                                    } else if (preguntasPregrado.get(j).getId() == 28 && !pregunta28.contains(prog)) {
                                        pregunta28.add(prog);
                                    } else if (preguntasPregrado.get(j).getId() == 29 && !pregunta29.contains(prog)) {
                                        pregunta29.add(prog);
                                    } else if (preguntasPregrado.get(j).getId() == 32 && !pregunta32.contains(prog)) {
                                        pregunta32.add(prog);
                                    } else if (preguntasPregrado.get(j).getId() == 37 && !pregunta37.contains(prog)) {
                                        pregunta37.add(prog);
                                    } else if (preguntasPregrado.get(j).getId() == 39 && !pregunta39.contains(prog)) {
                                        pregunta39.add(prog);
                                    } else if (preguntasPregrado.get(j).getId() == 55 && !pregunta55.contains(prog)) {
                                        pregunta55.add(prog);
                                    }
                                }
                            }

                        } else if (prog.getTipoformacion().equals("Maestria")) {
                            m = modeloFacade.find(3);
                            Fuente f = fuenteFacade.find(rol.getId());
                            Asignacionencuesta ae = asignacionencuestaFacade.findBySingle2("fuenteId", f, "modeloId", m);
                            Encuesta encuestaAplicar = ae.getEncuestaId();
                            encuestaCombinar.add(encuestaAplicar);

                            List<Pregunta> preguntasMaestria = encuestaAplicar.getPreguntaList();
                            for (int j = 0; j < preguntasMaestria.size(); j++) {
                                if ("si".equals(preguntasMaestria.get(j).getRepetir())) {
                                    if (preguntasMaestria.get(j).getId() == 4 && !pregunta4.contains(prog)) {
                                        pregunta4.add(prog);
                                    } else if (preguntasMaestria.get(j).getId() == 15 && !pregunta15.contains(prog)) {
                                        pregunta15.add(prog);
                                    } else if (preguntasMaestria.get(j).getId() == 24 && !pregunta24.contains(prog)) {
                                        pregunta24.add(prog);
                                    } else if (preguntasMaestria.get(j).getId() == 25 && !pregunta25.contains(prog)) {
                                        pregunta25.add(prog);
                                    } else if (preguntasMaestria.get(j).getId() == 26 && !pregunta26.contains(prog)) {
                                        pregunta26.add(prog);
                                    } else if (preguntasMaestria.get(j).getId() == 27 && !pregunta27.contains(prog)) {
                                        pregunta27.add(prog);
                                    } else if (preguntasMaestria.get(j).getId() == 28 && !pregunta28.contains(prog)) {
                                        pregunta28.add(prog);
                                    } else if (preguntasMaestria.get(j).getId() == 29 && !pregunta29.contains(prog)) {
                                        pregunta29.add(prog);
                                    } else if (preguntasMaestria.get(j).getId() == 32 && !pregunta32.contains(prog)) {
                                        pregunta32.add(prog);
                                    } else if (preguntasMaestria.get(j).getId() == 37 && !pregunta37.contains(prog)) {
                                        pregunta37.add(prog);
                                    } else if (preguntasMaestria.get(j).getId() == 39 && !pregunta39.contains(prog)) {
                                        pregunta39.add(prog);
                                    } else if (preguntasMaestria.get(j).getId() == 55 && !pregunta55.contains(prog)) {
                                        pregunta55.add(prog);
                                    }
                                }
                            }

                        } else if (prog.getTipoformacion().equals("Especializacion")) {
                            m = modeloFacade.find(4);
                            Fuente f = fuenteFacade.find(rol.getId());
                            Asignacionencuesta ae = asignacionencuestaFacade.findBySingle2("fuenteId", f, "modeloId", m);
                            Encuesta encuestaAplicar = ae.getEncuestaId();
                            encuestaCombinar.add(encuestaAplicar);
                            List<Pregunta> preguntasEspecializacion = encuestaAplicar.getPreguntaList();
                            for (int j = 0; j < preguntasEspecializacion.size(); j++) {
                                if ("si".equals(preguntasEspecializacion.get(j).getRepetir())) {
                                    if (preguntasEspecializacion.get(j).getId() == 4 && !pregunta4.contains(prog)) {
                                        pregunta4.add(prog);
                                    } else if (preguntasEspecializacion.get(j).getId() == 15 && !pregunta15.contains(prog)) {
                                        pregunta15.add(prog);
                                    } else if (preguntasEspecializacion.get(j).getId() == 24 && !pregunta24.contains(prog)) {
                                        pregunta24.add(prog);
                                    } else if (preguntasEspecializacion.get(j).getId() == 25 && !pregunta25.contains(prog)) {
                                        pregunta25.add(prog);
                                    } else if (preguntasEspecializacion.get(j).getId() == 26 && !pregunta26.contains(prog)) {
                                        pregunta26.add(prog);
                                    } else if (preguntasEspecializacion.get(j).getId() == 27 && !pregunta27.contains(prog)) {
                                        pregunta27.add(prog);
                                    } else if (preguntasEspecializacion.get(j).getId() == 28 && !pregunta28.contains(prog)) {
                                        pregunta28.add(prog);
                                    } else if (preguntasEspecializacion.get(j).getId() == 29 && !pregunta29.contains(prog)) {
                                        pregunta29.add(prog);
                                    } else if (preguntasEspecializacion.get(j).getId() == 32 && !pregunta32.contains(prog)) {
                                        pregunta32.add(prog);
                                    } else if (preguntasEspecializacion.get(j).getId() == 37 && !pregunta37.contains(prog)) {
                                        pregunta37.add(prog);
                                    } else if (preguntasEspecializacion.get(j).getId() == 39 && !pregunta39.contains(prog)) {
                                        pregunta39.add(prog);
                                    } else if (preguntasEspecializacion.get(j).getId() == 55 && !pregunta55.contains(prog)) {
                                        pregunta55.add(prog);
                                    }
                                }
                            }
                        }

                    }

                    List<Pregunta> preguntas = new ArrayList<Pregunta>();
                    for (int i = 0; i < encuestaCombinar.size(); i++) {
                        List<Pregunta> auxP = encuestaCombinar.get(i).getPreguntaList();
                        for (Pregunta x : auxP) {
                            if (!preguntas.contains(x)) {
                                preguntas.add(x);
                            }
                        }
                    }
                    Collections.sort(preguntas);

                    
                    
                    
                    List<List<Programa>> preguntasQueSeRepiten = new ArrayList<List<Programa>>();
                    
                    for (int i = 0; i < 56; i++) {
                        preguntasQueSeRepiten.add(null);
                    }
                    
                    
                    
                    preguntasQueSeRepiten.add(4, pregunta4);
                    preguntasQueSeRepiten.add(15, pregunta15);
                    preguntasQueSeRepiten.add(24, pregunta24);
                    preguntasQueSeRepiten.add(25, pregunta25);
                    preguntasQueSeRepiten.add(26,pregunta26);
                    preguntasQueSeRepiten.add(27, pregunta27);
                    preguntasQueSeRepiten.add(28, pregunta28);
                    preguntasQueSeRepiten.add(29, pregunta29);
                    preguntasQueSeRepiten.add(32, pregunta32);
                    preguntasQueSeRepiten.add(37, pregunta37);
                    preguntasQueSeRepiten.add(39, pregunta39);
                    preguntasQueSeRepiten.add(55, pregunta55);
                    
                    session.setAttribute("preguntas", preguntas);
                    session.setAttribute("preguntasQueSeRepiten", preguntasQueSeRepiten);
                    
                    
                    String url = "/WEB-INF/vista/fuente/responderEncuesta.jsp";
                    RequestDispatcher rd = request.getRequestDispatcher(url);
                    rd.forward(request, response);
                } else {
                    if (RESPONDER.equals(request.getParameter("action"))) {

                        List<Pregunta> preguntas = (List<Pregunta>) session.getAttribute("preguntas");
                        Encuesta e = (Encuesta) session.getAttribute("encuesta");
                        Muestrapersona persona = (Muestrapersona) session.getAttribute("persona");
                        Fuente fuente = (Fuente) session.getAttribute("fuente");
                        String estado = "terminado";

                        Encabezado enc = new Encabezado();
                        enc.setProcesoId(persona.getMuestraId().getProcesoId());
                        enc.setEncuestaId(e);
                        enc.setEstado(estado);
                        enc.setFuenteId(fuente);
                        enc.setMuestrapersonaId(persona);
                        enc.setFecha(new Date(new java.util.Date().getTime()));
                        encabezadoFacade.create(enc);

                        Encabezado recienCreado = encabezadoFacade.findByUltimo();
                        try {
                            for (Pregunta pregunta : preguntas) {

                                if (pregunta.getPreguntaList() != null && pregunta.getPreguntaList().size() > 0) {

                                    for (int i = 0; i < pregunta.getPreguntaList().size(); i++) {
                                        Resultadoevaluacion re = new Resultadoevaluacion();
                                        re.setEncabezadoId(recienCreado);
                                        re.setPreguntaId(pregunta.getPreguntaList().get(i));
                                        String respuesta1 = (String) request.getParameter("pregunta" + pregunta.getPreguntaList().get(i).getId());
                                        re.setRespuesta(respuesta1);
                                        re.setRespuestaAbierta(null);
                                        resultadoevaluacionFacade.create(re);

                                    }
                                } else {
                                    Resultadoevaluacion re = new Resultadoevaluacion();
                                    re.setEncabezadoId(recienCreado);
                                    re.setPreguntaId(pregunta);
                                    String respuesta1 = (String) request.getParameter("pregunta" + pregunta.getId());
                                    re.setRespuesta(respuesta1);
                                    re.setRespuestaAbierta(null);
                                    resultadoevaluacionFacade.create(re);
                                }
                            }
                        } catch (Exception ex) {
                            LOGGER.error("Ha ocurrido un error guardando las repuestas: ", ex);
                        }

                        recienCreado.setResultadoevaluacionList(resultadoevaluacionFacade.findByEncabezado(recienCreado));
                        encabezadoFacade.edit(recienCreado);
                        if (RESPONDER.equals(request.getParameter("action"))) {
                            session.setAttribute("encuesta", null);
                            session.setAttribute("preguntas", null);
                        }

                    } else {
                        if (action.equals("inicioCC")) {
                            String url = "/WEB-INF/vista/fuente/inicio.jsp";
                            RequestDispatcher rd = request.getRequestDispatcher(url);
                            rd.forward(request, response);

                        } else if (action.equals("perfilCC")) {
                            session.setAttribute("programas", programaFacade.findAll());
                            String url = "/WEB-INF/vista/fuente/perfil.jsp";
                            RequestDispatcher rd = request.getRequestDispatcher(url);
                            rd.forward(request, response);

                        } else {
                            if (action.equals("contrasena")) {
                                String url = "/WEB-INF/vista/comitePrograma/contrasena.jsp";
                                RequestDispatcher rd = request.getRequestDispatcher(url);
                                rd.forward(request, response);
                            } else {
                                if (action.equals("cambiarClave")) {
                                }
                            }
                        }
                    }
                }
            }
        } catch (ServletException e) {
            LOGGER.error("Ha ocurrido un error de tipo ServletException: ", e);
        } catch (IOException e) {
            LOGGER.error("Ha ocurrido un error de tipo IOException: ", e);
        } catch (Exception e) {
            LOGGER.error("Ha ocurrido un error: ", e);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
