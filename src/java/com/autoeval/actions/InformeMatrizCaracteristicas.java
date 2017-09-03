/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.autoeval.actions;

import com.autoeval.ejb.EvaluarcaracteristicaFacade;
import com.autoeval.ejb.NumericadocumentalFacade;
import com.autoeval.ejb.PonderacioncaracteristicaFacade;
import com.autoeval.ejb.ResultadoevaluacionFacade;
import com.autoeval.entity.Caracteristica;
import com.autoeval.entity.Encuesta;
import com.autoeval.entity.Evaluarcaracteristica;
import com.autoeval.entity.Indicador;
import com.autoeval.entity.Instrumento;
import com.autoeval.entity.Modelo;
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
public class InformeMatrizCaracteristicas implements Action {

    EvaluarcaracteristicaFacade evaluarcaracteristicaFacade = lookupEvaluarcaracteristicaFacadeBean();
    PonderacioncaracteristicaFacade ponderacioncaracteristicaFacade = lookupPonderacioncaracteristicaFacadeBean();

    @Override
    public String procesar(HttpServletRequest request) throws IOException, ServletException {
        HttpSession sesion = request.getSession();
        Proceso p = (Proceso) sesion.getAttribute("Proceso");
        Modelo mod = p.getModeloId();



        List<Ponderacioncaracteristica> ponderacionesC = new ArrayList<Ponderacioncaracteristica>();
        List<Caracteristica> caracteristicas = mod.getCaracteristicaList();
        float cumplimiento2[] = new float[caracteristicas.size()];
        for (int j = 0; j < caracteristicas.size(); j++) {

            Evaluarcaracteristica ev = evaluarcaracteristicaFacade.findBySingle2("caracteristicaId", caracteristicas.get(j), "procesoId", p);
            cumplimiento2[j] = ev.getEvaluacion();
            ponderacionesC.add(ponderacioncaracteristicaFacade.findByCaracteristicaYProceso(caracteristicas.get(j), p));
        }


        sesion.setAttribute("caracteristicas", caracteristicas);
        sesion.setAttribute("ponderacionesC", ponderacionesC);
        sesion.setAttribute("cumplimiento", cumplimiento2);

        return "/WEB-INF/vista/comitePrograma/proceso/informe/matrizCaracteristicas.jsp";
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
