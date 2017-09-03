/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.autoeval.actions;

import com.autoeval.ejb.ObjetivosFacade;
import com.autoeval.entity.Objetivos;
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
public class EditarObjetivo2 implements Action {
    ObjetivosFacade objetivosFacade = lookupObjetivosFacadeBean();

    @Override
    public String procesar(HttpServletRequest request) throws IOException, ServletException {
        HttpSession sesion = request.getSession();
        Objetivos o = (Objetivos) sesion.getAttribute("objetivo");
        String objetivo = (String) request.getParameter("objetivo");
        o.setObjetivo(objetivo);
        objetivosFacade.edit(o);
        return "NA";
    }

    private ObjetivosFacade lookupObjetivosFacadeBean() {
        try {
            Context c = new InitialContext();
            return (ObjetivosFacade) c.lookup("java:global/autoevaluacion/ObjetivosFacade!com.autoeval.ejb.ObjetivosFacade");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
