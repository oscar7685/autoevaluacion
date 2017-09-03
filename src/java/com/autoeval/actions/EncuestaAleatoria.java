/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.autoeval.actions;

import com.autoeval.ejb.EncabezadoFacade;
import com.autoeval.ejb.ResultadoevaluacionFacade;
import com.autoeval.entity.Asignacionencuesta;
import com.autoeval.entity.Encabezado;
import com.autoeval.entity.Encuesta;
import com.autoeval.entity.Modelo;
import com.autoeval.entity.Proceso;
import com.autoeval.entity.Resultadoevaluacion;
import com.autoeval.interfaz.Action;
import java.io.IOException;
import java.util.List;
import java.util.Random;
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
public class EncuestaAleatoria implements Action {
    ResultadoevaluacionFacade resultadoevaluacionFacade = lookupResultadoevaluacionFacadeBean();
    EncabezadoFacade encabezadoFacade = lookupEncabezadoFacadeBean();

    @Override
    public String procesar(HttpServletRequest request) throws IOException, ServletException {
        HttpSession sesion = request.getSession();
        Proceso pro = (Proceso) sesion.getAttribute("Proceso");
        String idFuente = request.getParameter("id");
        int idf = Integer.parseInt(idFuente);
        Modelo m = pro.getModeloId();
        int indiceEncuesta;
        int indiceEncabezado;
        Encuesta en = null;
        if (idFuente != null && !idFuente.equals("")) {
            List<Asignacionencuesta> asignacionEncuesta = m.getAsignacionencuestaList();
            for (int i = 0; i < asignacionEncuesta.size(); i++) {
                if (asignacionEncuesta.get(i).getFuenteId().getId() == idf) {
                    en = asignacionEncuesta.get(i).getEncuestaId();
                    break;
                }
            }
            sesion.setAttribute("preguntas", en.getPreguntaList());
            List<Encabezado> encabezados = encabezadoFacade.findByList3("procesoId", pro, "fuenteId.id", idf, "estado", "terminado");
            Random generador2 = new Random();
            if (encabezados.size() > 0) {
                indiceEncabezado = generador2.nextInt(encabezados.size());
                List<Resultadoevaluacion> resultados = resultadoevaluacionFacade.findByList("encabezadoId", encabezados.get(indiceEncabezado));
                sesion.setAttribute("resultados", resultados);
            }
        } /*else {
            List<Encuesta> encuestas = m.getEncuestaList();

            boolean busca = true;
            int limite = 0;
            while (busca && limite < 20) {
                Random generador = new Random();
                indiceEncuesta = generador.nextInt(encuestas.size());
                en = encuestas.get(indiceEncuesta);
                sesion.setAttribute("encuesta", en);
                List<Encabezado> encabezados = encabezadoFacade.findByList3("procesoId", pro, "encuestaId", en, "estado", "terminado");
                Random generador2 = new Random();
                if (encabezados.size() > 0) {
                    indiceEncabezado = generador2.nextInt(encabezados.size());
                    List<Resultadoevaluacion> resultados = resultadoevaluacionFacade.findByList("encabezadoId", encabezados.get(indiceEncabezado));
                    sesion.setAttribute("resultados", resultados);
                    busca = false;
                }
                limite++;
            }
        }*/
        return "/WEB-INF/vista/comitePrograma/proceso/informe/encuestaAleatoria.jsp";
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

    private ResultadoevaluacionFacade lookupResultadoevaluacionFacadeBean() {
        try {
            Context c = new InitialContext();
            return (ResultadoevaluacionFacade) c.lookup("java:global/autoevaluacion/ResultadoevaluacionFacade!com.autoeval.ejb.ResultadoevaluacionFacade");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
