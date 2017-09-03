/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.autoeval.actions;

import com.autoeval.ejb.MetasFacade;
import com.autoeval.entity.Metas;
import com.autoeval.interfaz.Action;
import java.io.IOException;
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
public class Editar2Meta implements Action {
    MetasFacade metasFacade = lookupMetasFacadeBean();

    @Override
    public String procesar(HttpServletRequest request) throws IOException, ServletException {
        HttpSession sesion = request.getSession();
        String id = request.getParameter("id");
        Metas m = metasFacade.find(Integer.parseInt(id));
        sesion.setAttribute("meta", m);
        return "/WEB-INF/vista/comitePrograma/proceso/planMejoramiento/metas2/editar.jsp";
    }

    private MetasFacade lookupMetasFacadeBean() {
        try {
            Context c = new InitialContext();
            return (MetasFacade) c.lookup("java:global/autoevaluacion/MetasFacade!com.autoeval.ejb.MetasFacade");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
