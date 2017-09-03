/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.autoeval.actions;

import com.autoeval.ejb.MuestraFacade;
import com.autoeval.ejb.ProgramaFacade;
import com.autoeval.entity.Muestra;
import com.autoeval.entity.Proceso;
import com.autoeval.entity.Programa;
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
public class ListarProgramasPorLineamiento implements Action {

    ProgramaFacade programaFacade = lookupProgramaFacadeBean();

    @Override
    public String procesar(HttpServletRequest request) throws IOException, ServletException {
        HttpSession sesion = request.getSession();
        String tipoLineamiento = request.getParameter("tipoLineamiento");
        List<Programa> programasPorLineamiento = null;
        if (tipoLineamiento.equals("3")) {
            programasPorLineamiento = programaFacade.findByList("tipoformacion", "Universitaria");
        } else if (tipoLineamiento.equals("4")) {
            programasPorLineamiento = programaFacade.findByList("tipoformacion", "Maestria");
        } else if (tipoLineamiento.equals("5")) {
            programasPorLineamiento = programaFacade.findByList("tipoformacion", "Especializacion");
        }

        sesion.setAttribute("programasXlineamiento", programasPorLineamiento);
        String url = "/WEB-INF/vista/comitePrograma/programas/programasPorLineamiento.jsp";
        return url;
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
