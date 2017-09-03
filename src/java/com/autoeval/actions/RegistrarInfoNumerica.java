/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.autoeval.actions;

import com.autoeval.ejb.IndicadorFacade;
import com.autoeval.ejb.InstrumentoFacade;
import com.autoeval.ejb.NumericadocumentalFacade;
import com.autoeval.entity.Indicador;
import com.autoeval.entity.Instrumento;
import com.autoeval.entity.Numericadocumental;
import com.autoeval.entity.Proceso;
import com.autoeval.interfaz.Action;
import java.io.IOException;
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
public class RegistrarInfoNumerica implements Action {
    NumericadocumentalFacade numericadocumentalFacade = lookupNumericadocumentalFacadeBean();
    InstrumentoFacade instrumentoFacade = lookupInstrumentoFacadeBean();
    IndicadorFacade indicadorFacade = lookupIndicadorFacadeBean();

    @Override
    public String procesar(HttpServletRequest request) throws IOException, ServletException {
        HttpSession sesion = request.getSession();
        Proceso p = (Proceso) sesion.getAttribute("Proceso");
        String indicador = request.getParameter("indicador");
        String columna = request.getParameter("columna");
        String valor = request.getParameter("valor");


        Indicador aux = indicadorFacade.find(Integer.parseInt(indicador));
        Instrumento ins = instrumentoFacade.find(2);

        Numericadocumental infonumerica = numericadocumentalFacade.findBySingle3("procesoId", p, "indicadorId", aux, "instrumentoId", ins);

        if (infonumerica == null) {
            Numericadocumental infonumerica3 = new Numericadocumental();
            infonumerica3.setProcesoId(p);
            infonumerica3.setIndicadorId(aux);
            infonumerica3.setInstrumentoId(ins);

            if (columna.equals("1")) {
                infonumerica3.setDocumento(valor);
            } else if (columna.equals("2")) {
                infonumerica3.setResponsable(valor);
            } else if (columna.equals("3")) {
                infonumerica3.setMedio(valor);
            } else if (columna.equals("4")) {
                infonumerica3.setLugar(valor);
            } else if (columna.equals("5")) {
                infonumerica3.setEvaluacion(Integer.parseInt(valor));
            } else if (columna.equals("6")) {
                infonumerica3.setAccion(valor);
            }
            numericadocumentalFacade.create(infonumerica3);
        } else {
            if (columna.equals("1")) {
                infonumerica.setDocumento(valor);
            } else if (columna.equals("2")) {
                infonumerica.setResponsable(valor);
            } else if (columna.equals("3")) {
                infonumerica.setMedio(valor);
            } else if (columna.equals("4")) {
                infonumerica.setLugar(valor);
            } else if (columna.equals("5")) {
                infonumerica.setEvaluacion(Integer.parseInt(valor));
            } else if (columna.equals("6")) {
                infonumerica.setAccion(valor);
            }
            numericadocumentalFacade.edit(infonumerica);
        }
        return "NA";
    }

    private IndicadorFacade lookupIndicadorFacadeBean() {
        try {
            Context c = new InitialContext();
            return (IndicadorFacade) c.lookup("java:global/autoevaluacion/IndicadorFacade!com.autoeval.ejb.IndicadorFacade");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private InstrumentoFacade lookupInstrumentoFacadeBean() {
        try {
            Context c = new InitialContext();
            return (InstrumentoFacade) c.lookup("java:global/autoevaluacion/InstrumentoFacade!com.autoeval.ejb.InstrumentoFacade");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private NumericadocumentalFacade lookupNumericadocumentalFacadeBean() {
        try {
            Context c = new InitialContext();
            return (NumericadocumentalFacade) c.lookup("java:global/autoevaluacion/NumericadocumentalFacade!com.autoeval.ejb.NumericadocumentalFacade");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
