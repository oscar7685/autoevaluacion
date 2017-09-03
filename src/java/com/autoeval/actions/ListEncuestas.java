/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.autoeval.actions;

import com.autoeval.ejb.EncuestaFacade;
import com.autoeval.entity.Modelo;
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
public class ListEncuestas implements Action {
    EncuestaFacade encuestaFacade = lookupEncuestaFacadeBean();

    @Override
    public String procesar(HttpServletRequest request) throws IOException, ServletException {
        HttpSession sesion = request.getSession();
        Modelo modelo = (Modelo) sesion.getAttribute("Modelo");
        sesion.setAttribute("listaE2", encuestaFacade.findByModelo(modelo));
        return "/WEB-INF/vista/comitePrograma/encuesta/listar.jsp";
    }

    private EncuestaFacade lookupEncuestaFacadeBean() {
        try {
            Context c = new InitialContext();
            return (EncuestaFacade) c.lookup("java:global/autoevaluacion/EncuestaFacade!com.autoeval.ejb.EncuestaFacade");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
