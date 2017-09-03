/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.autoeval.actions;

import com.autoeval.ejb.CaracteristicaFacade;
import com.autoeval.ejb.HallazgoFacade;
import com.autoeval.ejb.ProcesoFacade;
import com.autoeval.entity.Caracteristica;
import com.autoeval.entity.Hallazgo;
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
public class CrearFortaleza2 implements Action {
    ProcesoFacade procesoFacade = lookupProcesoFacadeBean();
    HallazgoFacade hallazgoFacade = lookupHallazgoFacadeBean();
    CaracteristicaFacade caracteristicaFacade = lookupCaracteristicaFacadeBean();

    @Override
    public String procesar(HttpServletRequest request) throws IOException, ServletException {
        HttpSession sesion = request.getSession();
        Proceso proceso = (Proceso) sesion.getAttribute("Proceso");
        String hallazgo11 = (String) request.getParameter("hallazgo");
        String caracteristica11 = (String) request.getParameter("caracteristica");
        Caracteristica c11 = caracteristicaFacade.find(Integer.parseInt(caracteristica11));
        Hallazgo h11 = new Hallazgo();
        h11.setHallazgo(hallazgo11);
        h11.setCaracteristicaId(c11);
        h11.setProcesoId(proceso);
        h11.setTipo("fortaleza");
        hallazgoFacade.create(h11);
        Hallazgo recienCreado11 = hallazgoFacade.findUltimo("idhallazgo").get(0);
        List<Hallazgo> hallagos11 = proceso.getHallazgoList();
        hallagos11.add(recienCreado11);
        proceso.setHallazgoList(hallagos11);
        procesoFacade.edit(proceso);
        sesion.setAttribute("Proceso", proceso);
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

    private ProcesoFacade lookupProcesoFacadeBean() {
        try {
            Context c = new InitialContext();
            return (ProcesoFacade) c.lookup("java:global/autoevaluacion/ProcesoFacade!com.autoeval.ejb.ProcesoFacade");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
