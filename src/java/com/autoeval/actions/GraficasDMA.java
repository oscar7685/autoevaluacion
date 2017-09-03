/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.autoeval.actions;

import com.autoeval.ejb.CaracteristicaFacade;
import com.autoeval.ejb.FactorFacade;
import com.autoeval.ejb.ModeloFacade;
import com.autoeval.ejb.ProgramaFacade;
import com.autoeval.ejb.RespuestasFacade;
import com.autoeval.ejb.RolFacade;
import com.autoeval.entity.Caracteristica;
import com.autoeval.entity.Factor;
import com.autoeval.entity.Modelo;
import com.autoeval.entity.Pregunta;
import com.autoeval.entity.Proceso;
import com.autoeval.entity.Programa;
import com.autoeval.entity.Respuestas;
import com.autoeval.entity.Rol;
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
public class GraficasDMA implements Action {
    FactorFacade factorFacade = lookupFactorFacadeBean();

    ProgramaFacade programaFacade = lookupProgramaFacadeBean();
    RolFacade rolFacade = lookupRolFacadeBean();
    RespuestasFacade respuestasFacade = lookupRespuestasFacadeBean();
    ModeloFacade modeloFacade = lookupModeloFacadeBean();
    CaracteristicaFacade caracteristicaFacade = lookupCaracteristicaFacadeBean();
    

    @Override
    public String procesar(HttpServletRequest request) throws IOException, ServletException {
        String url = "/WEB-INF/vista/comitePrograma/proceso/informe/graficasDMA.jsp";
        HttpSession sesion = request.getSession();
        Proceso proceso = (Proceso) sesion.getAttribute("Proceso");
        Modelo m = proceso.getModeloId();
        List<Factor> factores = factorFacade.findByModelo(m);
        
        sesion.setAttribute("factores", factores);
        return url;
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

    private ModeloFacade lookupModeloFacadeBean() {
        try {
            Context c = new InitialContext();
            return (ModeloFacade) c.lookup("java:global/autoevaluacion/ModeloFacade!com.autoeval.ejb.ModeloFacade");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private RespuestasFacade lookupRespuestasFacadeBean() {
        try {
            Context c = new InitialContext();
            return (RespuestasFacade) c.lookup("java:global/autoevaluacion/RespuestasFacade!com.autoeval.ejb.RespuestasFacade");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private RolFacade lookupRolFacadeBean() {
        try {
            Context c = new InitialContext();
            return (RolFacade) c.lookup("java:global/autoevaluacion/RolFacade!com.autoeval.ejb.RolFacade");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private ProgramaFacade lookupProgramaFacadeBean() {
        try {
            Context c = new InitialContext();
            return (ProgramaFacade) c.lookup("java:global/autoevaluacion/ProgramaFacade!com.autoeval.ejb.ProgramaFacade");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
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
}
