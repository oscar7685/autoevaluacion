/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.autoeval.actions;

import com.autoeval.ejb.EvaluarcaracteristicaFacade;
import com.autoeval.ejb.FactorFacade;
import com.autoeval.ejb.NumericadocumentalFacade;
import com.autoeval.ejb.PonderacioncaracteristicaFacade;
import com.autoeval.ejb.ResultadoevaluacionFacade;
import com.autoeval.entity.Caracteristica;
import com.autoeval.entity.Encuesta;
import com.autoeval.entity.Evaluarcaracteristica;
import com.autoeval.entity.Factor;
import com.autoeval.entity.Indicador;
import com.autoeval.entity.Instrumento;
import com.autoeval.entity.Numericadocumental;
import com.autoeval.entity.Ponderacioncaracteristica;
import com.autoeval.entity.Pregunta;
import com.autoeval.entity.Proceso;
import com.autoeval.entity.Resultadoevaluacion;
import com.autoeval.interfaz.Action;
import java.io.IOException;
import java.util.ArrayList;
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
public class DetalleFactor implements Action {

    PonderacioncaracteristicaFacade ponderacioncaracteristicaFacade = lookupPonderacioncaracteristicaFacadeBean();
    EvaluarcaracteristicaFacade evaluarcaracteristicaFacade = lookupEvaluarcaracteristicaFacadeBean();
    FactorFacade factorFacade = lookupFactorFacadeBean();

    @Override
    public String procesar(HttpServletRequest request) throws IOException, ServletException {
        HttpSession sesion = request.getSession();
        Proceso p = (Proceso) sesion.getAttribute("Proceso");
        String idFactor = request.getParameter("id");
        
        Factor f = factorFacade.find(Integer.parseInt(idFactor));
        float sumaPon = 0;
        float suma2 = 0;
        float cumplimientoF=0;
        
        List<Ponderacioncaracteristica> ponderacionesC = new ArrayList<Ponderacioncaracteristica>();
        List<Caracteristica> caracteristicas = f.getCaracteristicaList();
        float cumplimiento2[] = new float[caracteristicas.size()];
        for (int j = 0; j < caracteristicas.size(); j++) {

            Evaluarcaracteristica ev = evaluarcaracteristicaFacade.findBySingle2("caracteristicaId", caracteristicas.get(j), "procesoId", p);
            cumplimiento2[j] = ev.getEvaluacion();
            ponderacionesC.add(ponderacioncaracteristicaFacade.findByCaracteristicaYProceso(caracteristicas.get(j), p));
        }


        sesion.setAttribute("caracteristicas", caracteristicas);
        sesion.setAttribute("ponderacionesC", ponderacionesC);
        sesion.setAttribute("cumplimiento", cumplimiento2);

        
        sesion.setAttribute("factor", f);
        
        return "/WEB-INF/vista/comitePrograma/proceso/informe/detalleFactor.jsp";
    }

    private FactorFacade lookupFactorFacadeBean() {
        try {
            Context c = new InitialContext();
            return (FactorFacade) c.lookup("java:global/autoevaluacion/FactorFacade!com.autoeval.ejb.FactorFacade");
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

    private EvaluarcaracteristicaFacade lookupEvaluarcaracteristicaFacadeBean() {
        try {
            Context c = new InitialContext();
            return (EvaluarcaracteristicaFacade) c.lookup("java:global/autoevaluacion/EvaluarcaracteristicaFacade!com.autoeval.ejb.EvaluarcaracteristicaFacade");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
