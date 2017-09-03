/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.autoeval.actions;

import com.autoeval.ejb.MetasFacade;
import com.autoeval.entity.Metas;
import com.autoeval.interfaz.Action;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
public class EditarMeta2 implements Action {
    MetasFacade metasFacade = lookupMetasFacadeBean();

    @Override
    public String procesar(HttpServletRequest request) throws IOException, ServletException {
        HttpSession sesion = request.getSession();
        Metas m = (Metas) sesion.getAttribute("meta");
        String meta = (String) request.getParameter("meta");
        String estrategia = (String) request.getParameter("estrategia");
        String indicador = (String) request.getParameter("indicador");
        String finicio = (String) request.getParameter("inicio");
        String ffinal = (String) request.getParameter("final");
        String responsables = (String) request.getParameter("responsables");
        String recursos = (String) request.getParameter("recursos");

        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
        Date fechaI = null;
        try {

            fechaI = formatoDelTexto.parse(finicio);

        } catch (Exception ex) {

            ex.printStackTrace();

        }
        Date fechaF = null;
        try {

            fechaF = formatoDelTexto.parse(ffinal);

        } catch (Exception ex) {

            ex.printStackTrace();

        }

        m.setMeta(meta);
        m.setEstrategia(estrategia);
        m.setIndicadorCumplimiento(indicador);
        m.setResponsable(responsables);
        m.setFinanciacion(recursos);
        m.setFechaInicio(fechaI);
        m.setFechaFinal(fechaF);

        metasFacade.edit(m);
        return "NA";
    }

    private MetasFacade lookupMetasFacadeBean() {
        try {
            Context c = new InitialContext();
            return (MetasFacade) c.lookup("java:global/autoevaluacion/MetasFacade!com.autoeval.ejb.MetasFacade");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
