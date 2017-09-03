/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.autoeval.actions;

import com.autoeval.ejb.CaracteristicaFacade;
import com.autoeval.ejb.HallazgoFacade;
import com.autoeval.entity.Hallazgo;
import com.autoeval.entity.Modelo;
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
public class PlanMejoramiento implements Action {
    CaracteristicaFacade caracteristicaFacade = lookupCaracteristicaFacadeBean();
    HallazgoFacade hallazgoFacade = lookupHallazgoFacadeBean();

    @Override
    public String procesar(HttpServletRequest request) throws IOException, ServletException {
        HttpSession sesion = request.getSession();
        Proceso proceso = (Proceso) sesion.getAttribute("Proceso");
        Modelo modelo = (Modelo) sesion.getAttribute("Modelo");
        List<Hallazgo> ha = hallazgoFacade.findByList2("procesoId", proceso, "tipo", "hallazgo");
        List<Hallazgo> forta = hallazgoFacade.findByList2("procesoId", proceso, "tipo", "fortaleza");
        sesion.setAttribute("listaC", caracteristicaFacade.findByModelo(modelo));
        sesion.setAttribute("listHallazgos", ha);
        sesion.setAttribute("listFortalezas", forta);
        return  "/WEB-INF/vista/comitePrograma/proceso/planMejoramiento/hallazgos/listar.jsp";
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

    private CaracteristicaFacade lookupCaracteristicaFacadeBean() {
        try {
            Context c = new InitialContext();
            return (CaracteristicaFacade) c.lookup("java:global/autoevaluacion/CaracteristicaFacade!com.autoeval.ejb.CaracteristicaFacade");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
