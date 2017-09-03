/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.autoeval.actions;

import com.autoeval.ejb.MetasFacade;
import com.autoeval.ejb.ObjetivosFacade;
import com.autoeval.entity.Metas;
import com.autoeval.entity.Objetivos;
import com.autoeval.interfaz.Action;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
public class Crear2Meta2 implements Action {
    MetasFacade metasFacade = lookupMetasFacadeBean();
    ObjetivosFacade objetivosFacade = lookupObjetivosFacadeBean();

    @Override
    public String procesar(HttpServletRequest request) throws IOException, ServletException {
        HttpSession sesion = request.getSession();
        Objetivos o = (Objetivos) sesion.getAttribute("objetivo");
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

        Metas m = new Metas();
        m.setMeta(meta);
        m.setEstrategia(estrategia);
        m.setIndicadorCumplimiento(indicador);
        m.setResponsable(responsables);
        m.setFinanciacion(recursos);
        m.setObjetivosIdobjetivos(o);
        m.setFechaInicio(fechaI);
        m.setFechaFinal(fechaF);

        metasFacade.create(m);

        Metas recienCreado = metasFacade.findUltimo("idmeta").get(0);
        List<Metas> metas = o.getMetasList();
        metas.add(recienCreado);
        o.setMetasList(metas);
        objetivosFacade.edit(o);
        return "NA";
    }

    private ObjetivosFacade lookupObjetivosFacadeBean() {
        try {
            Context c = new InitialContext();
            return (ObjetivosFacade) c.lookup("java:global/autoevaluacion/ObjetivosFacade!com.autoeval.ejb.ObjetivosFacade");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
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
