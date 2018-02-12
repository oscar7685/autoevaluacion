/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.autoeval.actions;

import com.autoeval.ejb.EncabezadoFacade;
import com.autoeval.ejb.MuestraadministrativoFacade;
import com.autoeval.ejb.MuestradirectorFacade;
import com.autoeval.ejb.MuestradocenteFacade;
import com.autoeval.ejb.MuestraegresadoFacade;
import com.autoeval.ejb.MuestraempleadorFacade;
import com.autoeval.ejb.MuestraestudianteFacade;
import com.autoeval.ejb.MuestrapersonaFacade;
import com.autoeval.ejb.ParticipanteFacade;
import com.autoeval.ejb.RolFacade;
import com.autoeval.entity.Encabezado;
import com.autoeval.entity.Muestra;
import com.autoeval.entity.Proceso;
import com.autoeval.entity.Rol;
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
public class EstadoProceso implements Action {

    RolFacade rolFacade = lookupRolFacadeBean();
    ParticipanteFacade participanteFacade = lookupParticipanteFacadeBean();

    @Override
    public String procesar(HttpServletRequest request) throws IOException, ServletException {
        HttpSession sesion = request.getSession();
        Proceso p = (Proceso) sesion.getAttribute("Proceso");

       
        int total = participanteFacade.countByProperty("procesoId", p);
        int contestaron = participanteFacade.countPorProcesoContestados("procesoId", p);
        int totalEst = participanteFacade.countByPerfil(p, rolFacade.find(1));
        int totalEstEsp = participanteFacade.countByPerfil(p, rolFacade.find(7));
        int totalEstMae = participanteFacade.countByPerfil(p, rolFacade.find(8));
        int totalDoc = participanteFacade.countByPerfil(p, rolFacade.find(2));
        int totalDocCat = participanteFacade.countByPerfil(p, rolFacade.find(11));
        int totalEgr = participanteFacade.countByPerfil(p, rolFacade.find(4));
        int totalEgrEsp = participanteFacade.countByPerfil(p, rolFacade.find(9));
        int totalEgrMae = participanteFacade.countByPerfil(p, rolFacade.find(10));
        int totalAdm = participanteFacade.countByPerfil(p, rolFacade.find(3));
        int totalDir = participanteFacade.countByPerfil(p, rolFacade.find(5));
        int totalEmp = participanteFacade.countByPerfil(p, rolFacade.find(6));

        int terminadosEst = participanteFacade.countTerminadosByPerfil(p, rolFacade.find(1));
        int terminadosDoc = participanteFacade.countTerminadosByPerfil(p, rolFacade.find(2));
        int terminadosEgr = participanteFacade.countTerminadosByPerfil(p, rolFacade.find(4));
        int terminadosAdm = participanteFacade.countTerminadosByPerfil(p, rolFacade.find(3));
        int terminadosDir = participanteFacade.countTerminadosByPerfil(p, rolFacade.find(5));
        int terminadosEmp = participanteFacade.countTerminadosByPerfil(p, rolFacade.find(6));
        int terminadosEstEspe = participanteFacade.countTerminadosByPerfil(p, rolFacade.find(7));
        int terminadosEstMaes = participanteFacade.countTerminadosByPerfil(p, rolFacade.find(8));
        int terminadosEgrEspe = participanteFacade.countTerminadosByPerfil(p, rolFacade.find(9));
        int terminadosEgrMaes = participanteFacade.countTerminadosByPerfil(p, rolFacade.find(10));
        int terminadosDocCat = participanteFacade.countTerminadosByPerfil(p, rolFacade.find(11));

        sesion.setAttribute("terminadosX", contestaron);
        sesion.setAttribute("totalMuestraX", total);
        sesion.setAttribute("totalEst", totalEst);
        sesion.setAttribute("terminadosEst", terminadosEst);
        sesion.setAttribute("totalEstEsp", totalEstEsp);
        sesion.setAttribute("terminadosEstEsp", terminadosEstEspe);
        sesion.setAttribute("totalEstMae", totalEstMae);
        sesion.setAttribute("terminadosEstMae", terminadosEstMaes);
        sesion.setAttribute("totalDoc", totalDoc);
        sesion.setAttribute("terminadosDoc", terminadosDoc);
        sesion.setAttribute("totalDocCat", totalDocCat);
        sesion.setAttribute("terminadosDocCat", terminadosDocCat);
        sesion.setAttribute("totalEgr", totalEgr);
        sesion.setAttribute("terminadosEgr", terminadosEgr);
        sesion.setAttribute("totalEgrEsp", totalEgrEsp);
        sesion.setAttribute("terminadosEgrEsp", terminadosEgrEspe);
        sesion.setAttribute("totalEgrMae", totalEgrMae);
        sesion.setAttribute("terminadosEgrMae", terminadosEgrMaes);
        sesion.setAttribute("totalEmp", totalEmp);
        sesion.setAttribute("terminadosEmp", terminadosEmp);
        sesion.setAttribute("totalAdm", totalAdm);
        sesion.setAttribute("terminadosAdm", terminadosAdm);
        sesion.setAttribute("totalDir", totalDir);
        sesion.setAttribute("terminadosDir", terminadosDir);
        return "/WEB-INF/vista/comitePrograma/proceso/informe/estadoProceso.jsp";
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

}
