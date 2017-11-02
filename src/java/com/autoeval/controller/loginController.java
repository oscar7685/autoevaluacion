/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.autoeval.controller;

import com.autoeval.ejb.AsignacionencuestaFacade;
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
import com.autoeval.ejb.ParticipanteFacade;
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
import com.autoeval.entity.Participante;
import com.autoeval.entity.ParticipanteHasRol;
import com.autoeval.entity.Pregunta;
import com.autoeval.entity.Proceso;
import com.autoeval.entity.Programa;
import com.autoeval.entity.Representante;
import com.autoeval.entity.Rol;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
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
public class loginController extends HttpServlet {
    @EJB
    private ModeloFacade modeloFacade;
    @EJB
    private ParticipanteFacade participanteFacade;

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
            
           Participante p =  participanteFacade.find(un);
           
           if( p != null && un.equals(pw)  ){
                session.setAttribute("tipoLogin", "Fuente");
                session.setAttribute("participante", p);
                out.print("0");
               /*List<ParticipanteHasRol>  phr = p.getParticipanteHasRolList();
               for (int i = 0; i < phr.size(); i++) {
                 Programa prog = phr.get(i).getProgramaId();
                 Rol rol = phr.get(i).getRolId();
                 Modelo m = null;
                 if(prog.getTipoformacion().equals("Institucional")){
                   m  = modeloFacade.find(1);
                 }
                 Fuente f = fuenteFacade.find(rol.getId());
                 Asignacionencuesta ae = asignacionencuestaFacade.findBySingle2("fuenteId", f, "modeloId", m);
                 Encuesta encuestaAplicar = ae.getEncuestaId();
                 List<Pregunta> preguntas = encuestaAplicar.getPreguntaList();
                 
                 session.setAttribute("preguntas", preguntas);
                 String url = "/WEB-INF/vista/fuente/responderEncuesta.jsp";
                 RequestDispatcher rd = request.getRequestDispatcher(url);
                 rd.forward(request, response);
               }*/
           }else{
               out.print("1");
           }
        }
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
