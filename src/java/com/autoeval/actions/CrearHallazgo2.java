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
public class CrearHallazgo2 implements Action {

    ProcesoFacade procesoFacade = lookupProcesoFacadeBean();
    HallazgoFacade hallazgoFacade = lookupHallazgoFacadeBean();
    CaracteristicaFacade caracteristicaFacade = lookupCaracteristicaFacadeBean();

    @Override
    public String procesar(HttpServletRequest request) throws IOException, ServletException {
        HttpSession sesion = request.getSession();
        Proceso pr = (Proceso) sesion.getAttribute("Proceso");
        String hallazgo = (String) request.getParameter("hallazgo");
        String caracteristica = (String) request.getParameter("caracteristica");
        Caracteristica c = caracteristicaFacade.find(Integer.parseInt(caracteristica));
        Hallazgo h = new Hallazgo();
        h.setHallazgo(hallazgo);
        h.setCaracteristicaId(c);
        h.setProcesoId(pr);
        h.setTipo("hallazgo");
        hallazgoFacade.create(h);
        Hallazgo recienCreado = hallazgoFacade.findUltimo("idhallazgo").get(0);
        List<Hallazgo> hallagos = pr.getHallazgoList();
        hallagos.add(recienCreado);
        pr.setHallazgoList(hallagos);
        procesoFacade.edit(pr);
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
