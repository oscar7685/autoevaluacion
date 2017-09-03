/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.autoeval.interfaz;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author acreditacion
 */
public interface Action {
    /**
     *
     * @param request
     * @return
     */
    public String procesar(HttpServletRequest request)
        throws IOException, ServletException;
    
}
