/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.autoeval.actions;

import com.autoeval.ejb.EncuestaFacade;
import com.autoeval.ejb.ProgramaFacade;
import com.autoeval.entity.Modelo;
import com.autoeval.entity.Proceso;
import com.autoeval.entity.Programa;
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
public class ListarProgramasSelect implements Action {
    ProgramaFacade programaFacade = lookupProgramaFacadeBean();
   
    @Override
    public String procesar(HttpServletRequest request) throws IOException, ServletException {
        HttpSession sesion = request.getSession();
        Proceso p = (Proceso) sesion.getAttribute("Proceso");
        String tipoEstudiantes = request.getParameter("a");
        
        if(tipoEstudiantes.equals("1")){//estudiantes de pregrado
            sesion.setAttribute("programasSelect", programaFacade.findByList("tipoformacion", "Pregrado"));
        }else if(tipoEstudiantes.equals("2")){//estudiantes de maestria
            List<Programa> maestrias = programaFacade.findByList("tipoformacion", "Maestria");
            List<Programa> doctorados = programaFacade.findByList("tipoformacion", "Doctorado");
            for (Programa doctorado : doctorados) {
                maestrias.add(doctorado);
            }
            sesion.setAttribute("programasSelect", maestrias);
        }else if(tipoEstudiantes.equals("3")){//estudiantes de especializacion
            sesion.setAttribute("programasSelect", programaFacade.findByList("tipoformacion", "Especializacion"));
        }
        
        return "/WEB-INF/vista/comitePrograma/muestra/programas.jsp";
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

   
}
