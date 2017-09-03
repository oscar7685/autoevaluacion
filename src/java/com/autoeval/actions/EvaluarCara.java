/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.autoeval.actions;

import com.autoeval.ejb.CaracteristicaFacade;
import com.autoeval.ejb.EvaluarcaracteristicaFacade;
import com.autoeval.entity.Caracteristica;
import com.autoeval.entity.Evaluarcaracteristica;
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
public class EvaluarCara implements Action {

    CaracteristicaFacade caracteristicaFacade = lookupCaracteristicaFacadeBean();
    EvaluarcaracteristicaFacade evaluarcaracteristicaFacade = lookupEvaluarcaracteristicaFacadeBean();

    @Override
    public String procesar(HttpServletRequest request) throws IOException, ServletException {
        HttpSession sesion = request.getSession();
        Proceso proceso = (Proceso) sesion.getAttribute("Proceso");
        List<Caracteristica> caracteristicas = (List<Caracteristica>) sesion.getAttribute("listCara");

        for (Caracteristica caracteristica : caracteristicas) {
            String evaluacionC = request.getParameter("evaluacionC" + caracteristica.getId());
            Evaluarcaracteristica e = new Evaluarcaracteristica();
            e.setCaracteristicaId(caracteristica);
            e.setProcesoId(proceso);
            e.setEvaluacion(Float.parseFloat(evaluacionC));
            evaluarcaracteristicaFacade.create(e);
        }
        return "NA";
    }

    private EvaluarcaracteristicaFacade lookupEvaluarcaracteristicaFacadeBean() {
        try {
            Context c = new InitialContext();
            return (EvaluarcaracteristicaFacade) c.lookup("java:global/autoevaluacion/EvaluarcaracteristicaFacade!com.autoeval.ejb.EvaluarcaracteristicaFacade");
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
