/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.autoeval.actions;

import com.autoeval.ejb.HallazgoFacade;
import com.autoeval.ejb.ObjetivosFacade;
import com.autoeval.entity.Hallazgo;
import com.autoeval.entity.Objetivos;
import com.autoeval.entity.Proceso;
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
public class Listar2Objetivos implements Action {
    ObjetivosFacade objetivosFacade = lookupObjetivosFacadeBean();
    HallazgoFacade hallazgoFacade = lookupHallazgoFacadeBean();

    @Override
    public String procesar(HttpServletRequest request) throws IOException, ServletException {
        HttpSession sesion = request.getSession();
        Proceso proceso = (Proceso) sesion.getAttribute("Proceso");
        String id = request.getParameter("id");
        Hallazgo fortaleza = hallazgoFacade.find(Integer.parseInt(id));
        List<Objetivos> objetivos = objetivosFacade.findByList("hallazgoIdhallazgo", fortaleza);
        List<Hallazgo> halla = hallazgoFacade.findByList2("procesoId", proceso, "tipo", "hallazgo");
        sesion.setAttribute("listObjetivos", objetivos);
        sesion.setAttribute("fortaleza", fortaleza);
        sesion.setAttribute("listHallazgos", halla);
        return "/WEB-INF/vista/comitePrograma/proceso/planMejoramiento/objetivos2/listar.jsp";

    }

    private HallazgoFacade lookupHallazgoFacadeBean() {
        try {
            Context c = new InitialContext();
            return (HallazgoFacade) c.lookup("java:global/autoevaluacion/HallazgoFacade!com.autoeval.ejb.HallazgoFacade");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
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
