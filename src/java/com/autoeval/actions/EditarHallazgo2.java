/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.autoeval.actions;

import com.autoeval.ejb.CaracteristicaFacade;
import com.autoeval.ejb.HallazgoFacade;
import com.autoeval.entity.Caracteristica;
import com.autoeval.entity.Hallazgo;
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
public class EditarHallazgo2 implements Action {
    HallazgoFacade hallazgoFacade = lookupHallazgoFacadeBean();
    CaracteristicaFacade caracteristicaFacade = lookupCaracteristicaFacadeBean();

    @Override
    public String procesar(HttpServletRequest request) throws IOException, ServletException {
        HttpSession sesion = request.getSession();
        Hallazgo h = (Hallazgo) sesion.getAttribute("hallazgo");
        String hallazgo = (String) request.getParameter("hallazgo");
        String caracteristica = (String) request.getParameter("caracteristica");
        int idCar = Integer.parseInt(caracteristica);
        Caracteristica c = caracteristicaFacade.find(idCar);

        h.setHallazgo(hallazgo);
        if (idCar != h.getCaracteristicaId().getId()) {
            h.setCaracteristicaId(c);
        }
        hallazgoFacade.edit(h);
        return "NA";
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

    private HallazgoFacade lookupHallazgoFacadeBean() {
        try {
            Context c = new InitialContext();
            return (HallazgoFacade) c.lookup("java:global/autoevaluacion/HallazgoFacade!com.autoeval.ejb.HallazgoFacade");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
