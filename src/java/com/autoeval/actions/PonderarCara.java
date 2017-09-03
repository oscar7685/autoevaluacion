/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.autoeval.actions;

import com.autoeval.ejb.PonderacioncaracteristicaFacade;
import com.autoeval.ejb.PonderacionfactorFacade;
import com.autoeval.entity.Caracteristica;
import com.autoeval.entity.Factor;
import com.autoeval.entity.Ponderacioncaracteristica;
import com.autoeval.entity.Ponderacionfactor;
import com.autoeval.entity.Proceso;
import com.autoeval.interfaz.Action;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Iterator;
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
public class PonderarCara implements Action {
    PonderacionfactorFacade ponderacionfactorFacade = lookupPonderacionfactorFacadeBean();
    PonderacioncaracteristicaFacade ponderacioncaracteristicaFacade = lookupPonderacioncaracteristicaFacadeBean();

    @Override
    public String procesar(HttpServletRequest request) throws IOException, ServletException {
        HttpSession sesion = request.getSession();
        Proceso proceso = (Proceso) sesion.getAttribute("Proceso");
        Ponderacioncaracteristica pc = new Ponderacioncaracteristica();

        List listCara = (List) sesion.getAttribute("listCara");

        Iterator i = listCara.iterator();

        while (i.hasNext()) {
            Caracteristica c = (Caracteristica) i.next();
            Double ponderacion = Double.parseDouble(request.getParameter("ponderacionC" + c.getId()));
            String justificacion = request.getParameter("justificacion" + c.getId());

            pc.setCaracteristicaId(c);
            pc.setNivelimportancia(new Float("0.0"));
            pc.setJustificacion(justificacion);
            pc.setPonderacion(ponderacion);
            pc.setProcesoId(proceso);

            ponderacioncaracteristicaFacade.create(pc);
        }

        return "NA";
    }

    private PonderacioncaracteristicaFacade lookupPonderacioncaracteristicaFacadeBean() {
        try {
            Context c = new InitialContext();
            return (PonderacioncaracteristicaFacade) c.lookup("java:global/autoevaluacion/PonderacioncaracteristicaFacade!com.autoeval.ejb.PonderacioncaracteristicaFacade");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private PonderacionfactorFacade lookupPonderacionfactorFacadeBean() {
        try {
            Context c = new InitialContext();
            return (PonderacionfactorFacade) c.lookup("java:global/autoevaluacion/PonderacionfactorFacade!com.autoeval.ejb.PonderacionfactorFacade");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
