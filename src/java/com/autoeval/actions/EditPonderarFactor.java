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
public class EditPonderarFactor implements Action {
    PonderacioncaracteristicaFacade ponderacioncaracteristicaFacade = lookupPonderacioncaracteristicaFacadeBean();
    PonderacionfactorFacade ponderacionfactorFacade = lookupPonderacionfactorFacadeBean();

    @Override
    public String procesar(HttpServletRequest request) throws IOException, ServletException {
        HttpSession sesion = request.getSession();
        Proceso proceso = (Proceso) sesion.getAttribute("Proceso");
        List listFactor2 = ponderacionfactorFacade.findByList("procesoId", proceso);

        Iterator it2 = listFactor2.iterator();

        while (it2.hasNext()) {
            Ponderacionfactor pf2 = (Ponderacionfactor) it2.next();
            Double ponderacion = Double.parseDouble(request.getParameter("ponderacion" + pf2.getId()));
            String justificacion = request.getParameter("justificacion" + pf2.getId());

            pf2.setJustificacion(justificacion);
            pf2.setPonderacion(ponderacion);
            ponderacionfactorFacade.edit(pf2);

            Factor f = pf2.getFactorId();

            List suma0 = f.getCaracteristicaList();

            Iterator i1 = suma0.iterator();

            double suma = 0;
            List<Ponderacioncaracteristica> listpondC = (List<Ponderacioncaracteristica>) sesion.getAttribute("listPonderacionCara");
            if (listpondC != null && listpondC.size() > 0) {
                while (i1.hasNext()) {
                    Caracteristica c = (Caracteristica) i1.next();
                    Ponderacioncaracteristica pc1;
                    pc1 = ponderacioncaracteristicaFacade.findBySingle2("caracteristicaId", c, "procesoId", proceso);
                    suma += pc1.getNivelimportancia();
                }

                Iterator i2 = suma0.iterator();

                while (i2.hasNext()) {
                    Caracteristica c = (Caracteristica) i2.next();
                    Ponderacioncaracteristica pc1 = ponderacioncaracteristicaFacade.findBySingle2("caracteristicaId", c, "procesoId", proceso);

                    double vi = pc1.getNivelimportancia();

                    //System.out.println("Ponderacion FActor: " + pf.getPonderacion());
                    double a = (100 * vi) / suma;
                    double b = ((pf2.getPonderacion() * a) / 100);

                    double r;

                    int decimalPlaces = 2;
                    BigDecimal bde = new BigDecimal(b);

                    // setScale is immutable
                    bde = bde.setScale(decimalPlaces, BigDecimal.ROUND_HALF_UP);
                    r = bde.doubleValue();

                    pc1.setPonderacion(r);
                    ponderacioncaracteristicaFacade.edit(pc1);
                }
            }

        }
        return "NA";
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

    private PonderacioncaracteristicaFacade lookupPonderacioncaracteristicaFacadeBean() {
        try {
            Context c = new InitialContext();
            return (PonderacioncaracteristicaFacade) c.lookup("java:global/autoevaluacion/PonderacioncaracteristicaFacade!com.autoeval.ejb.PonderacioncaracteristicaFacade");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
