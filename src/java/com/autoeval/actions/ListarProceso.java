/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.autoeval.actions;

import com.autoeval.ejb.ProcesoFacade;
import com.autoeval.entity.Programa;
import com.autoeval.interfaz.Action;
import java.io.IOException;
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
public class ListarProceso implements Action {

    ProcesoFacade procesoFacade = lookupProcesoFacadeBean();
    private final Logger LOGGER = Logger.getLogger(ListarProceso.class);

    @Override
    public String procesar(HttpServletRequest request) throws IOException, ServletException {
        HttpSession sesion = request.getSession();
        Programa programa = null;
        List<Programa> programas = null;

        try {
            programa = (Programa) sesion.getAttribute("Programa");
        } catch (Exception e) {
            LOGGER.error("ha ocurrido un error buscando el programa" + e);
        }
        try {
            if (programa == null) {
                programas = (List<Programa>) sesion.getAttribute("Programas");
            }
        } catch (Exception e) {
            LOGGER.error("ha ocurrido un error buscando los programas" + e);
        }

        
        if (programa != null) {
            sesion.setAttribute("listProceso", procesoFacade.findByList("programaId", programa));
        } else if (programas != null) {
            sesion.setAttribute("listProceso", procesoFacade.findByListReprestanteMultiple("programaId", programas));
        }
         
        return "/WEB-INF/vista/comitePrograma/proceso/listar.jsp";
    }

    private ProcesoFacade lookupProcesoFacadeBean() {
        try {
            Context c = new InitialContext();
            return (ProcesoFacade) c.lookup("java:global/autoevaluacion/ProcesoFacade!com.autoeval.ejb.ProcesoFacade");
        } catch (NamingException ne) {
            java.util.logging.Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
