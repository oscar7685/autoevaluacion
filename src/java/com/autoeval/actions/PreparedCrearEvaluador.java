/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.autoeval.actions;

import com.autoeval.ejb.CaracteristicaFacade;
import com.autoeval.entity.Administrativo;
import com.autoeval.entity.Directorprograma;
import com.autoeval.entity.Docente;
import com.autoeval.entity.Egresado;
import com.autoeval.entity.Empleador;
import com.autoeval.entity.Estudiante;
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
public class PreparedCrearEvaluador implements Action {

    @Override
    public String procesar(HttpServletRequest request) throws IOException, ServletException {
        HttpSession sesion = request.getSession();
        Proceso proceso = (Proceso) sesion.getAttribute("Proceso");
        String fuente = request.getParameter("fuente");
        if (fuente.equals("Estudiante")) {
            sesion.setAttribute("selectorFuente", "Estudiante");
        } else if (fuente.equals("Docente")) {
            sesion.setAttribute("selectorFuente", "Docente");
        } else if (fuente.equals("Egresado")) {
            sesion.setAttribute("selectorFuente", "Egresado");
        } else if (fuente.equals("Administrativo")) {
            sesion.setAttribute("selectorFuente", "Administrativo");
        } else if (fuente.equals("Empleador")) {
            sesion.setAttribute("selectorFuente", "Empleador");
        } else if (fuente.equals("Directivo")) {
            sesion.setAttribute("selectorFuente", "Directivo");
        }


        return "/WEB-INF/vista/comitePrograma/muestra/crearEvaluador.jsp";
    }
}
