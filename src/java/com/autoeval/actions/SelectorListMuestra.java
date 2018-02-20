/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.autoeval.actions;

import com.autoeval.ejb.AdministrativoFacade;
import com.autoeval.ejb.DirectorprogramaFacade;
import com.autoeval.ejb.DocenteFacade;
import com.autoeval.ejb.EgresadoFacade;
import com.autoeval.ejb.EmpleadorFacade;
import com.autoeval.ejb.EncabezadoFacade;
import com.autoeval.ejb.EstudianteFacade;
import com.autoeval.ejb.FuenteFacade;
import com.autoeval.ejb.MuestraFacade;
import com.autoeval.ejb.MuestraadministrativoFacade;
import com.autoeval.ejb.MuestraagenciaFacade;
import com.autoeval.ejb.MuestradirectorFacade;
import com.autoeval.ejb.MuestradocenteFacade;
import com.autoeval.ejb.MuestraegresadoFacade;
import com.autoeval.ejb.MuestraempleadorFacade;
import com.autoeval.ejb.MuestraestudianteFacade;
import com.autoeval.ejb.ParticipanteFacade;
import com.autoeval.ejb.RolFacade;
import com.autoeval.entity.Muestra;
import com.autoeval.entity.Participante;
import com.autoeval.entity.Proceso;
import com.autoeval.entity.Programa;
import com.autoeval.interfaz.Action;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

/**
 *
 * @author acreditacion
 */
public class SelectorListMuestra implements Action {

    RolFacade rolFacade = lookupRolFacadeBean();
    ParticipanteFacade participanteFacade = lookupParticipanteFacadeBean();

    private static final Logger LOGGER = Logger.getLogger(SelectorListMuestra.class);

    @Override
    public String procesar(HttpServletRequest request) throws IOException, ServletException {
        try {
            HttpSession sesion = request.getSession();
            Proceso proceso = (Proceso) sesion.getAttribute("Proceso");
            String fuente = request.getParameter("fuente");
            sesion.setAttribute("fuenteX", "" + fuente);

            /* sesion.setAttribute("listMuestraCon", muestraestudianteFacade.findByMuestraConEncabezado(proceso));
             sesion.setAttribute("listMuestraSin", muestraestudianteFacade.findByMuestraSinEncabezado(proceso));
             sesion.setAttribute("poblacion", estudianteFacade.findByList("procesoId", proceso));*/
            if (fuente.equals("EstudianteP")) {
                List<Object[]> participantes = participanteFacade.findByPerfil(proceso, rolFacade.find(1));
                sesion.setAttribute("participantes", participantes);
            } else if (fuente.equals("EstudianteM")) {
                List<Object[]> participantes = participanteFacade.findByPerfil(proceso, rolFacade.find(8));
                sesion.setAttribute("participantes", participantes);
            } else if (fuente.equals("EstudianteE")) {
                List<Object[]> participantes = participanteFacade.findByPerfil(proceso, rolFacade.find(7));
                sesion.setAttribute("participantes", participantes);
            } else if (fuente.equals("ProfesorP")) {
                List<Object[]> participantes = participanteFacade.findByPerfil(proceso, rolFacade.find(2));
                sesion.setAttribute("participantes", participantes);
            } else if (fuente.equals("ProfesorC")) {
                List<Object[]> participantes = participanteFacade.findByPerfil(proceso, rolFacade.find(11));
                sesion.setAttribute("participantes", participantes);
            } else if (fuente.equals("Directivo")) {
                List<Object[]> participantes = participanteFacade.findByPerfil(proceso, rolFacade.find(5));
                sesion.setAttribute("participantes", participantes);
            } else if (fuente.equals("Administrativo")) {
                List<Object[]> participantes = participanteFacade.findByPerfil(proceso, rolFacade.find(3));
                sesion.setAttribute("participantes", participantes);
            } else if (fuente.equals("EgresadoP")) {
                List<Object[]> participantes = participanteFacade.findByPerfil(proceso, rolFacade.find(4));
                sesion.setAttribute("participantes", participantes);
            } else if (fuente.equals("EgresadoE")) {
                List<Object[]> participantes = participanteFacade.findByPerfil(proceso, rolFacade.find(9));
                sesion.setAttribute("participantes", participantes);
            } else if (fuente.equals("EgresadoM")) {
                List<Object[]> participantes = participanteFacade.findByPerfil(proceso, rolFacade.find(10));
                sesion.setAttribute("participantes", participantes);
            } else if (fuente.equals("Empleador")) {
                List<Object[]> participantes = participanteFacade.findByPerfil(proceso, rolFacade.find(6));
                sesion.setAttribute("participantes", participantes);
            }

        } catch (Exception e) {
            LOGGER.error("Se ha presentado un error: ", e);
        } finally {
            return "/WEB-INF/vista/comitePrograma/muestra/selectorListMuestra.jsp";
        }

    }

    private ParticipanteFacade lookupParticipanteFacadeBean() {
        try {
            Context c = new InitialContext();
            return (ParticipanteFacade) c.lookup("java:global/autoevaluacion/ParticipanteFacade!com.autoeval.ejb.ParticipanteFacade");
        } catch (NamingException ne) {
            java.util.logging.Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private RolFacade lookupRolFacadeBean() {
        try {
            Context c = new InitialContext();
            return (RolFacade) c.lookup("java:global/autoevaluacion/RolFacade!com.autoeval.ejb.RolFacade");
        } catch (NamingException ne) {
            java.util.logging.Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
