/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.autoeval.actions;

import com.autoeval.ejb.EncabezadoFacade;
import com.autoeval.ejb.FuenteFacade;
import com.autoeval.entity.Encabezado;
import com.autoeval.entity.Fuente;
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
public class Comentarios implements Action {
    EncabezadoFacade encabezadoFacade = lookupEncabezadoFacadeBean();
    FuenteFacade fuenteFacade = lookupFuenteFacadeBean();

    @Override
    public String procesar(HttpServletRequest request) throws IOException, ServletException {
        HttpSession sesion = request.getSession();
        Proceso pro = (Proceso) sesion.getAttribute("Proceso");
        Fuente est = fuenteFacade.find(1);
        Fuente doc = fuenteFacade.find(2);
        Fuente admi = fuenteFacade.find(3);
        Fuente egr = fuenteFacade.find(4);
        Fuente dir = fuenteFacade.find(5);
        Fuente emp = fuenteFacade.find(6);

        List<Encabezado> estudiantes = encabezadoFacade.findByComentarios(est, pro);
        List<Encabezado> docentes = encabezadoFacade.findByComentarios(doc, pro);
        List<Encabezado> administrativos = encabezadoFacade.findByComentarios(admi, pro);
        List<Encabezado> egresados = encabezadoFacade.findByComentarios(egr, pro);
        List<Encabezado> directores = encabezadoFacade.findByComentarios(dir, pro);
        List<Encabezado> empleadores = encabezadoFacade.findByComentarios(emp, pro);

        sesion.setAttribute("estudiantes", estudiantes);
        sesion.setAttribute("docentes", docentes);
        sesion.setAttribute("administrativos", administrativos);
        sesion.setAttribute("egresados", egresados);
        sesion.setAttribute("directores", directores);
        sesion.setAttribute("empleadores", empleadores);

        return "/WEB-INF/vista/comitePrograma/proceso/informe/comentarios.jsp";
    }

    private FuenteFacade lookupFuenteFacadeBean() {
        try {
            Context c = new InitialContext();
            return (FuenteFacade) c.lookup("java:global/autoevaluacion/FuenteFacade!com.autoeval.ejb.FuenteFacade");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private EncabezadoFacade lookupEncabezadoFacadeBean() {
        try {
            Context c = new InitialContext();
            return (EncabezadoFacade) c.lookup("java:global/autoevaluacion/EncabezadoFacade!com.autoeval.ejb.EncabezadoFacade");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
