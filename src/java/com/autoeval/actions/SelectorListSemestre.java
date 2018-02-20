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
import com.autoeval.ejb.MuestraadministrativoFacade;
import com.autoeval.ejb.MuestradirectorFacade;
import com.autoeval.ejb.MuestradocenteFacade;
import com.autoeval.ejb.MuestraegresadoFacade;
import com.autoeval.ejb.MuestraempleadorFacade;
import com.autoeval.ejb.MuestraestudianteFacade;
import com.autoeval.ejb.ParticipanteFacade;
import com.autoeval.ejb.ProgramaFacade;
import com.autoeval.ejb.RolFacade;
import com.autoeval.entity.Muestra;
import com.autoeval.entity.Participante;
import com.autoeval.entity.Proceso;
import com.autoeval.entity.Programa;
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
public class SelectorListSemestre implements Action {

    ProgramaFacade programaFacade = lookupProgramaFacadeBean();

    RolFacade rolFacade = lookupRolFacadeBean();
    ParticipanteFacade participanteFacade = lookupParticipanteFacadeBean();

    @Override
    public String procesar(HttpServletRequest request) throws IOException, ServletException {
        HttpSession sesion = request.getSession();
        //Programa programa = (Programa) sesion.getAttribute("Programa");
        Proceso proceso = (Proceso) sesion.getAttribute("Proceso");
        String fuente = request.getParameter("fuente");
        String programa = request.getParameter("programa");

        if (programa.equals("todos")) {
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
            }
        } else {
            if (fuente.equals("EstudianteP")) {
                List<Object[]> participantes = participanteFacade.findByPerfilyPrograma(proceso, rolFacade.find(1), programaFacade.find(Integer.parseInt(programa)));
                sesion.setAttribute("participantes", participantes);
            } else if (fuente.equals("EstudianteM")) {
                List<Object[]> participantes = participanteFacade.findByPerfilyPrograma(proceso, rolFacade.find(8), programaFacade.find(Integer.parseInt(programa)));
                sesion.setAttribute("participantes", participantes);
            } else if (fuente.equals("EstudianteE")) {
                List<Object[]> participantes = participanteFacade.findByPerfilyPrograma(proceso, rolFacade.find(7), programaFacade.find(Integer.parseInt(programa)));
                sesion.setAttribute("participantes", participantes);
            }else if (fuente.equals("ProfesorP")) {
                List<Object[]> participantes = participanteFacade.findByPerfilyPrograma(proceso, rolFacade.find(2), programaFacade.find(Integer.parseInt(programa)));
                sesion.setAttribute("participantes", participantes);
            }else if (fuente.equals("ProfesorC")) {
                List<Object[]> participantes = participanteFacade.findByPerfilyPrograma(proceso, rolFacade.find(11), programaFacade.find(Integer.parseInt(programa)));
                sesion.setAttribute("participantes", participantes);
            }

        }

        return "/WEB-INF/vista/comitePrograma/muestra/selectorListMuestra.jsp";
    }

    private ParticipanteFacade lookupParticipanteFacadeBean() {
        try {
            Context c = new InitialContext();
            return (ParticipanteFacade) c.lookup("java:global/autoevaluacion/ParticipanteFacade!com.autoeval.ejb.ParticipanteFacade");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private RolFacade lookupRolFacadeBean() {
        try {
            Context c = new InitialContext();
            return (RolFacade) c.lookup("java:global/autoevaluacion/RolFacade!com.autoeval.ejb.RolFacade");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private ProgramaFacade lookupProgramaFacadeBean() {
        try {
            Context c = new InitialContext();
            return (ProgramaFacade) c.lookup("java:global/autoevaluacion/ProgramaFacade!com.autoeval.ejb.ProgramaFacade");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
