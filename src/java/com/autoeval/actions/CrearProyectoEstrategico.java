/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.autoeval.actions;

import com.autoeval.interfaz.Action;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author acreditacion
 */
public class CrearProyectoEstrategico implements Action{

    @Override
    public String procesar(HttpServletRequest request) throws IOException, ServletException {
        return "/WEB-INF/vista/comitePrograma/proceso/planMejoramiento/estrategico/crear.jsp";
    }
    
}
