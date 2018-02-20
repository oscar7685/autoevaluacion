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
import com.autoeval.ejb.MuestraFacade;
import com.autoeval.ejb.MuestraadministrativoFacade;
import com.autoeval.ejb.MuestraagenciaFacade;
import com.autoeval.ejb.MuestradirectorFacade;
import com.autoeval.ejb.MuestradocenteFacade;
import com.autoeval.ejb.MuestraegresadoFacade;
import com.autoeval.ejb.MuestraempleadorFacade;
import com.autoeval.ejb.MuestraestudianteFacade;
import com.autoeval.ejb.ParticipanteFacade;
import com.autoeval.ejb.RespuestasFacade;
import com.autoeval.entity.Muestra;
import com.autoeval.entity.Participante;
import com.autoeval.entity.Proceso;
import com.autoeval.interfaz.Action;
import java.io.IOException;
import java.util.Iterator;
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
public class ListMuestra implements Action {

    ParticipanteFacade participanteFacade = lookupParticipanteFacadeBean();

    private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(ListMuestra.class);

    @Override
    public String procesar(HttpServletRequest request) throws IOException, ServletException {
        HttpSession sesion = request.getSession();
        Proceso proceso = (Proceso) sesion.getAttribute("Proceso");
        String url;

        List<Object[]> participantes = participanteFacade.listarPersonas(proceso);
        sesion.setAttribute("participantes", participantes);

        url = "/WEB-INF/vista/comitePrograma/muestra/listarMuestra.jsp";//cuando ya la muestra ha sido asignada
        return url;
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
}
