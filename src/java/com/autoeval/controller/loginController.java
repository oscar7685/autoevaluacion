/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.autoeval.controller;

import com.autoeval.ejb.AsignacionencuestaFacade;
import com.autoeval.ejb.EncabezadoFacade;
import com.autoeval.ejb.EncuestaFacade;
import com.autoeval.ejb.FuenteFacade;
import com.autoeval.ejb.MuestraadministrativoFacade;
import com.autoeval.ejb.MuestradirectorFacade;
import com.autoeval.ejb.MuestradocenteFacade;
import com.autoeval.ejb.MuestraegresadoFacade;
import com.autoeval.ejb.MuestraempleadorFacade;
import com.autoeval.ejb.MuestraestudianteFacade;
import com.autoeval.ejb.MuestrapersonaFacade;
import com.autoeval.ejb.ProcesoFacade;
import com.autoeval.ejb.RepresentanteFacade;
import com.autoeval.entity.Asignacionencuesta;
import com.autoeval.entity.Encabezado;
import com.autoeval.entity.Encuesta;
import com.autoeval.entity.Fuente;
import com.autoeval.entity.Modelo;
import com.autoeval.entity.Muestraadministrativo;
import com.autoeval.entity.Muestradirector;
import com.autoeval.entity.Muestradocente;
import com.autoeval.entity.Muestraegresado;
import com.autoeval.entity.Muestraempleador;
import com.autoeval.entity.Muestraestudiante;
import com.autoeval.entity.Muestrapersona;
import com.autoeval.entity.Pregunta;
import com.autoeval.entity.Proceso;
import com.autoeval.entity.Representante;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
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
public class loginController extends HttpServlet {

    private final Logger LOGGER = Logger.getLogger(loginController.class);
    @EJB
    private EncabezadoFacade encabezadoFacade;
    @EJB
    private MuestraempleadorFacade muestraempleadorFacade;
    @EJB
    private MuestraadministrativoFacade muestraadministrativoFacade;
    @EJB
    private MuestradirectorFacade muestradirectorFacade;
    @EJB
    private MuestradocenteFacade muestradocenteFacade;
    @EJB
    private MuestraegresadoFacade muestraegresadoFacade;
    @EJB
    private FuenteFacade fuenteFacade;
    @EJB
    private AsignacionencuestaFacade asignacionencuestaFacade;
    @EJB
    private MuestraestudianteFacade muestraestudianteFacade;
    @EJB
    private MuestrapersonaFacade muestrapersonaFacade;
    @EJB
    private RepresentanteFacade representanteFacade;
    @EJB
    private EncuestaFacade encuestaFacade;
    @EJB
    private ProcesoFacade procesoFacade;

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet loginController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet loginController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
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
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        List<Muestrapersona> usuario = null;
        String action = (String) request.getParameter("action");
        String respuesta = "1";
        Representante r = null;
        if (action != null) {
            request.getSession().invalidate();
        } else {
            response.setContentType("text/plain");
            /*parametros*/
            String un = (String) request.getParameter("un");
            String pw = (String) request.getParameter("pw");



            try {
                r = representanteFacade.find(Integer.parseInt(un));
            } catch (NumberFormatException e) {
            }
            if (r == null) {

                /*COMPROBAMOS SI ESTA EN LA MUESTRA Y SE ESTA UNA SOLA VEZ*/
                usuario = muestrapersonaFacade.findByCedula(un);

                for (int aux15 = 0; aux15 < usuario.size(); aux15++) {
                    if (usuario != null && usuario.size() == 1) {
                        //VARIABLES
                        Fuente f = null;
                        Muestrapersona Mpersona = null;
                        Proceso proceso;

                        //Estudiantes
                        Muestraestudiante Mestudiante = null;
                        List<Muestraestudiante> auxEstudiantes = null;
                        //Docentes
                        Muestradocente Mdocente = null;
                        List<Muestradocente> auxDocentes = null;
                        //Administrativo
                        Muestraadministrativo Madministrativo = null;
                        List<Muestraadministrativo> auxAdministrativos = null;
                        //Egresado
                        Muestraegresado Megresado = null;
                        List<Muestraegresado> auxEgresados = null;
                        //Directivo
                        Muestradirector Mdirectivo = null;
                        List<Muestradirector> auxDirectivo = null;
                        //Empleador
                        Muestraempleador Mempleador = null;
                        List<Muestraempleador> auxEmpleadores = null;



                        Mpersona = usuario.get(0);
                        if (Mpersona != null && Mpersona.getPassword().equals(pw)) {
                            //verificamos que tipo de fuente es
                            auxEstudiantes = muestraestudianteFacade.findByMuestraPersona(Mpersona);
                            if (auxEstudiantes == null || auxEstudiantes.isEmpty()) {//NO ES ESTUDIANTE
                                auxDocentes = muestradocenteFacade.findByMuestraPersona(Mpersona);
                                if (auxDocentes == null || auxDocentes.isEmpty()) { //NO ES DOCENTE
                                    auxEgresados = muestraegresadoFacade.findByMuestraPersona(Mpersona);
                                    if (auxEgresados == null || auxEgresados.isEmpty()) { //NO ES EGRESADO
                                        auxAdministrativos = muestraadministrativoFacade.findByMuestraPersona(Mpersona);
                                        if (auxAdministrativos == null || auxAdministrativos.isEmpty()) { //NO ES ADMINISTRATIVO
                                            auxDirectivo = muestradirectorFacade.findByMuestraPersona(Mpersona);
                                            if (auxDirectivo == null || auxDirectivo.isEmpty()) { //NO ES DIRECTIVO
                                                auxEmpleadores = muestraempleadorFacade.findByMuestraPersona(Mpersona);
                                                if (auxEmpleadores == null || auxEmpleadores.isEmpty()) { //NO ES EMPLEADOR
                                                    //no es nadie
                                                }
                                            }
                                        }
                                    }
                                }
                            }

                            if (auxEstudiantes != null && auxEstudiantes.size() == 1) {
                                Mestudiante = auxEstudiantes.get(0);
                                if (Mestudiante != null) {
                                    respuesta = "0";
                                    session.setAttribute("tipoLogin", "Fuente");
                                    session.setAttribute("programa", Mestudiante.getProgramaId());
                                    session.setAttribute("persona", Mpersona);

                                    if (Mestudiante.getTipo().equals("1")) {
                                        f = fuenteFacade.find(1);
                                        session.setAttribute("fuente", f);
                                    } else if (Mestudiante.getTipo().equals("7")) {
                                        f = fuenteFacade.find(7);
                                        session.setAttribute("fuente", f);
                                    } else if (Mestudiante.getTipo().equals("8")) {
                                        f = fuenteFacade.find(8);
                                        session.setAttribute("fuente", f);
                                    }

                                }
                            } else if (auxDocentes != null && auxDocentes.size() == 1) {
                                Mdocente = auxDocentes.get(0);
                                if (Mdocente != null) {
                                    respuesta = "0";
                                    session.setAttribute("tipoLogin", "Fuente");
                                    session.setAttribute("programa", Mdocente.getMuestrapersonaId().getMuestraId().getProcesoId().getProgramaId());
                                    session.setAttribute("persona", Mpersona);

                                    if (Mdocente.getTipo().equals("2")) {
                                        f = fuenteFacade.find(2);
                                        session.setAttribute("fuente", f);
                                    } else if (Mdocente.getTipo().equals("11")) {
                                        f = fuenteFacade.find(11);
                                        session.setAttribute("fuente", f);
                                    }
                                }
                            } else if (auxAdministrativos != null && auxAdministrativos.size() == 1) {
                                Madministrativo = auxAdministrativos.get(0);
                                if (Madministrativo != null) {
                                    respuesta = "0";
                                    session.setAttribute("tipoLogin", "Fuente");
                                    session.setAttribute("programa", Madministrativo.getMuestrapersonaId().getMuestraId().getProcesoId().getProgramaId());
                                    session.setAttribute("persona", Mpersona);

                                    f = fuenteFacade.find(3);
                                    session.setAttribute("fuente", f);

                                }
                            } else if (auxEgresados != null && auxEgresados.size() == 1) {
                                Megresado = auxEgresados.get(0);
                                if (Megresado != null) {
                                    respuesta = "0";
                                    session.setAttribute("tipoLogin", "Fuente");
                                    session.setAttribute("programa", Megresado.getMuestrapersonaId().getMuestraId().getProcesoId().getProgramaId());
                                    session.setAttribute("persona", Mpersona);

                                    if (Megresado.getTipo().equals("4")) {
                                        f = fuenteFacade.find(4);
                                        session.setAttribute("fuente", f);
                                    } else if (Megresado.getTipo().equals("9")) {
                                        f = fuenteFacade.find(9);
                                        session.setAttribute("fuente", f);
                                    } else if (Megresado.getTipo().equals("10")) {
                                        f = fuenteFacade.find(10);
                                        session.setAttribute("fuente", f);
                                    }

                                }
                            } else if (auxDirectivo != null && auxDirectivo.size() == 1) {
                                Mdirectivo = auxDirectivo.get(0);
                                if (Mdirectivo != null) {
                                    respuesta = "0";
                                    session.setAttribute("tipoLogin", "Fuente");
                                    session.setAttribute("programa", Mdirectivo.getMuestrapersonaId().getMuestraId().getProcesoId().getProgramaId());
                                    session.setAttribute("persona", Mpersona);

                                    f = fuenteFacade.find(5);
                                    session.setAttribute("fuente", f);

                                }
                            } else if (auxEmpleadores != null && auxEmpleadores.size() == 1) {
                                Mempleador = auxEmpleadores.get(0);
                                if (Mempleador != null) {
                                    respuesta = "0";
                                    session.setAttribute("tipoLogin", "Fuente");
                                    session.setAttribute("programa", Mempleador.getMuestrapersonaId().getMuestraId().getProcesoId().getProgramaId());
                                    session.setAttribute("persona", Mpersona);

                                    f = fuenteFacade.find(6);
                                    session.setAttribute("fuente", f);

                                }
                            }

                            proceso = Mpersona.getMuestraId().getProcesoId();
                            if (!proceso.getFechainicio().equals("En Configuración") && proceso.getFechacierre().equals("--")) {
                                Modelo m = proceso.getModeloId();
                                session.setAttribute("proceso", proceso);
                                Asignacionencuesta asignacionEncuesta = asignacionencuestaFacade.findBySingle2("modeloId", m, "fuenteId", f);

                                List<Encabezado> encabExistentes =
                                        encabezadoFacade.findByVars(proceso, asignacionEncuesta.getEncuestaId(), f, Mpersona);
                                if (encabExistentes.size() > 0 && encabExistentes.get(0).getEstado().equals("terminado")) {
                                } else {
                                    session.setAttribute("encuesta", asignacionEncuesta.getEncuestaId());
                                }
                            }
                        }
                    } else if (usuario != null && usuario.size() > 1) {//////////////SI ESTA MAS DE UNA VEZ EN LA MUESTRA///////////////////////////
                        //VARIABLES
                        Fuente f = null;
                        Muestrapersona Mpersona = null;
                        Proceso proceso;

                        //Estudiantes
                        Muestraestudiante Mestudiante = null;
                        List<Muestraestudiante> auxEstudiantes = null;
                        //Docentes
                        Muestradocente Mdocente = null;
                        List<Muestradocente> auxDocentes = null;
                        //Administrativo
                        Muestraadministrativo Madministrativo = null;
                        List<Muestraadministrativo> auxAdministrativos = null;
                        //Egresado
                        Muestraegresado Megresado = null;
                        List<Muestraegresado> auxEgresados = null;
                        //Directivo
                        Muestradirector Mdirectivo = null;
                        List<Muestradirector> auxDirectivo = null;
                        //Empleador
                        Muestraempleador Mempleador = null;
                        List<Muestraempleador> auxEmpleadores = null;



                        Mpersona = usuario.get(aux15);
                        if (Mpersona != null && Mpersona.getPassword().equals(pw)) {
                            //verificamos que tipo de fuente es
                            auxEstudiantes = muestraestudianteFacade.findByMuestraPersona(Mpersona);
                            if (auxEstudiantes == null || auxEstudiantes.isEmpty()) {//NO ES ESTUDIANTE
                                auxDocentes = muestradocenteFacade.findByMuestraPersona(Mpersona);
                                if (auxDocentes == null || auxDocentes.isEmpty()) { //NO ES DOCENTE
                                    auxEgresados = muestraegresadoFacade.findByMuestraPersona(Mpersona);
                                    if (auxEgresados == null || auxEgresados.isEmpty()) { //NO ES EGRESADO
                                        auxAdministrativos = muestraadministrativoFacade.findByMuestraPersona(Mpersona);
                                        if (auxAdministrativos == null || auxAdministrativos.isEmpty()) { //NO ES ADMINISTRATIVO
                                            auxDirectivo = muestradirectorFacade.findByMuestraPersona(Mpersona);
                                            if (auxDirectivo == null || auxDirectivo.isEmpty()) { //NO ES DIRECTIVO
                                                auxEmpleadores = muestraempleadorFacade.findByMuestraPersona(Mpersona);
                                                if (auxEmpleadores == null || auxEmpleadores.isEmpty()) { //NO ES EMPLEADOR
                                                    //no es nadie
                                                }
                                            }
                                        }
                                    }
                                }
                            }

                            if (auxEstudiantes != null && auxEstudiantes.size() == 1) {
                                Mestudiante = auxEstudiantes.get(0);
                                if (Mestudiante != null) {
                                    respuesta = "0";
                                    session.setAttribute("tipoLogin", "Fuente");
                                    session.setAttribute("programa" + aux15, Mestudiante.getProgramaId());
                                    session.setAttribute("persona" + aux15, Mpersona);

                                    if (Mestudiante.getTipo().equals("1")) {
                                        f = fuenteFacade.find(1);
                                        session.setAttribute("fuente" + aux15, f);
                                    } else if (Mestudiante.getTipo().equals("7")) {
                                        f = fuenteFacade.find(7);
                                        session.setAttribute("fuente" + aux15, f);
                                    } else if (Mestudiante.getTipo().equals("8")) {
                                        f = fuenteFacade.find(8);
                                        session.setAttribute("fuente" + aux15, f);
                                    }

                                }
                            } else if (auxDocentes != null && auxDocentes.size() == 1) {
                                Mdocente = auxDocentes.get(0);
                                if (Mdocente != null) {
                                    respuesta = "0";
                                    session.setAttribute("tipoLogin", "Fuente");
                                    session.setAttribute("programa" + aux15, Mdocente.getMuestrapersonaId().getMuestraId().getProcesoId().getProgramaId());
                                    session.setAttribute("persona" + aux15, Mpersona);

                                    if (Mdocente.getTipo().equals("2")) {
                                        f = fuenteFacade.find(2);
                                        session.setAttribute("fuente" + aux15, f);
                                    } else if (Mdocente.getTipo().equals("11")) {
                                        f = fuenteFacade.find(11);
                                        session.setAttribute("fuente" + aux15, f);
                                    }
                                }
                            } else if (auxAdministrativos != null && auxAdministrativos.size() == 1) {
                                Madministrativo = auxAdministrativos.get(0);
                                if (Madministrativo != null) {
                                    respuesta = "0";
                                    session.setAttribute("tipoLogin", "Fuente");
                                    session.setAttribute("programa" + aux15, Madministrativo.getMuestrapersonaId().getMuestraId().getProcesoId().getProgramaId());
                                    session.setAttribute("persona" + aux15, Mpersona);

                                    f = fuenteFacade.find(3);
                                    session.setAttribute("fuente" + aux15, f);

                                }
                            } else if (auxEgresados != null && auxEgresados.size() == 1) {
                                Megresado = auxEgresados.get(0);
                                if (Megresado != null) {
                                    respuesta = "0";
                                    session.setAttribute("tipoLogin", "Fuente");
                                    session.setAttribute("programa" + aux15, Megresado.getMuestrapersonaId().getMuestraId().getProcesoId().getProgramaId());
                                    session.setAttribute("persona" + aux15, Mpersona);

                                    if (Megresado.getTipo().equals("4")) {
                                        f = fuenteFacade.find(4);
                                        session.setAttribute("fuente" + aux15, f);
                                    } else if (Megresado.getTipo().equals("9")) {
                                        f = fuenteFacade.find(9);
                                        session.setAttribute("fuente" + aux15, f);
                                    } else if (Megresado.getTipo().equals("10")) {
                                        f = fuenteFacade.find(10);
                                        session.setAttribute("fuente" + aux15, f);
                                    }

                                }
                            } else if (auxDirectivo != null && auxDirectivo.size() == 1) {
                                Mdirectivo = auxDirectivo.get(0);
                                if (Mdirectivo != null) {
                                    respuesta = "0";
                                    session.setAttribute("tipoLogin", "Fuente");
                                    session.setAttribute("programa" + aux15, Mdirectivo.getMuestrapersonaId().getMuestraId().getProcesoId().getProgramaId());
                                    session.setAttribute("persona" + aux15, Mpersona);

                                    f = fuenteFacade.find(5);
                                    session.setAttribute("fuente" + aux15, f);

                                }
                            } else if (auxEmpleadores != null && auxEmpleadores.size() == 1) {
                                Mempleador = auxEmpleadores.get(0);
                                if (Mempleador != null) {
                                    respuesta = "0";
                                    session.setAttribute("tipoLogin", "Fuente");
                                    session.setAttribute("programa" + aux15, Mempleador.getMuestrapersonaId().getMuestraId().getProcesoId().getProgramaId());
                                    session.setAttribute("persona" + aux15, Mpersona);

                                    f = fuenteFacade.find(6);
                                    session.setAttribute("fuente" + aux15, f);

                                }
                            }

                            proceso = Mpersona.getMuestraId().getProcesoId();
                            if (!proceso.getFechainicio().equals("En Configuración") && proceso.getFechacierre().equals("--")) {
                                Modelo m = proceso.getModeloId();
                                session.setAttribute("proceso" + aux15, proceso);
                                Asignacionencuesta asignacionEncuesta = asignacionencuestaFacade.findBySingle2("modeloId", m, "fuenteId", f);

                                List<Encabezado> encabExistentes =
                                        encabezadoFacade.findByVars(proceso, asignacionEncuesta.getEncuestaId(), f, Mpersona);
                                if (encabExistentes.size() > 0 && encabExistentes.get(0).getEstado().equals("terminado")) {
                                } else {
                                    session.setAttribute("encuesta" + aux15, asignacionEncuesta.getEncuestaId());
                                }
                            }
                        }
                    }
                }



            } else {
                if (r.getPassword().equals(pw) && r.getRol().equals("Comite central")) {
                    if (LOGGER.isDebugEnabled()) {
                        LOGGER.debug("Credenciales validas");
                    }
                    session.setAttribute("tipoLogin", "Comite central");
                    session.setAttribute("nombre", "" + r.getNombre() + " " + r.getApellido());
                    respuesta = "0";
                } else {
                    /*COMITE PROGRAMA*/
                    if (r.getPassword().equals(pw) && r.getRol().equals("Comite programa")) {
                        if (LOGGER.isDebugEnabled()) {
                            LOGGER.debug("Credenciales validas");
                        }
                        respuesta = "0";
                        session.setAttribute("tipoLogin", "Comite programa");
                        session.setAttribute("representante", r);
                        //SessionCountListener.representantesLogueados.add(r);
                        if (r.getProgramaList() != null && r.getProgramaList().size() == 1) {
                            session.setAttribute("Programa", r.getProgramaList().get(0));
                            List procesos = (List) procesoFacade.findByPrograma(r.getProgramaList().get(0));
                            if (!procesos.isEmpty()) {
                                Iterator iter = procesos.iterator();
                                while (iter.hasNext()) {
                                    Proceso p = (Proceso) iter.next();
                                    if (p.getFechainicio().equals("En Configuración")) {
                                        session.setAttribute("EstadoProceso", 1);
                                        session.setAttribute("Proceso", p);
                                        session.setAttribute("Modelo", p.getModeloId());
                                    } else if (p.getFechacierre().equals("--")) {
                                        session.setAttribute("EstadoProceso", 2);
                                        session.setAttribute("Proceso", p);
                                        session.setAttribute("Modelo", p.getModeloId());

                                    } else {
                                        session.setAttribute("EstadoProceso", 0);
                                        //  session.setAttribute("Proceso", p);
                                        //session.setAttribute("Modelo", p.getModeloId());
                                    }
                                }
                            } else {
                                session.setAttribute("EstadoProceso", 0);
                            }
                        } else if (r.getProgramaList() != null && r.getProgramaList().size() > 1) {
                            session.setAttribute("Programas", r.getProgramaList());
                            session.setAttribute("EstadoProceso2", 4);
                        }

                    }
                }
            }
        }
        if (respuesta.equals("0")) {
            if (usuario != null) {
                session.setAttribute("personaLogueada", usuario.get(0).getNombre() + " " + usuario.get(0).getApellido());
            } else if (r != null) {
                session.setAttribute("personaLogueada", r.getNombre() + " " + r.getApellido());
            }
            String personaLogueada = (String) session.getAttribute("personaLogueada");
            SessionCountListener.addPersonaLogueada(personaLogueada);
            session.setAttribute("personasLogueadas", SessionCountListener.getPersonasLogueadas());
        }
        out.print(respuesta);
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
