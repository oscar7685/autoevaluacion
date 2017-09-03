/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.autoeval.actions;

import com.autoeval.ejb.RepresentanteFacade;
import com.autoeval.entity.Representante;
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
public class CambiarClave implements Action {

    RepresentanteFacade representanteFacade = lookupRepresentanteFacadeBean();

    @Override
    public String procesar(HttpServletRequest request) throws IOException, ServletException {
        HttpSession sesion = request.getSession();
        Representante r = (Representante) sesion.getAttribute("representante");
        String actual = request.getParameter("actual");
        String nueva1 = request.getParameter("nueva1");
        String nueva2 = request.getParameter("nueva2");

        if (r.getPassword().equals(actual)) {
            if (nueva1.equals(nueva2)) {
                r.setPassword(nueva1);
                representanteFacade.edit(r);
                return "0";
            } else {
                return "1";
            }
        } else {
            return "1";
        }
    }

    private RepresentanteFacade lookupRepresentanteFacadeBean() {
        try {
            Context c = new InitialContext();
            return (RepresentanteFacade) c.lookup("java:global/autoevaluacion/RepresentanteFacade!com.autoeval.ejb.RepresentanteFacade");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
