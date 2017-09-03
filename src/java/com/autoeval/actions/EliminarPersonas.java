/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.autoeval.actions;

import com.autoeval.ejb.AdministrativoFacade;
import com.autoeval.ejb.DirectorprogramaFacade;
import com.autoeval.ejb.DocenteFacade;
import com.autoeval.ejb.EgresadoFacade;
import com.autoeval.ejb.EmpleadorFacade;
import com.autoeval.ejb.EstudianteFacade;
import com.autoeval.ejb.FuenteFacade;
import com.autoeval.ejb.MuestraadministrativoFacade;
import com.autoeval.ejb.MuestraagenciaFacade;
import com.autoeval.ejb.MuestradirectorFacade;
import com.autoeval.ejb.MuestraegresadoFacade;
import com.autoeval.ejb.MuestraempleadorFacade;
import com.autoeval.ejb.MuestrapersonaFacade;
import com.autoeval.ejb.PersonaFacade;
import com.autoeval.entity.Administrativo;
import com.autoeval.entity.Directorprograma;
import com.autoeval.entity.Docente;
import com.autoeval.entity.Egresado;
import com.autoeval.entity.Empleador;
import com.autoeval.entity.Estudiante;
import com.autoeval.entity.Muestra;
import com.autoeval.entity.Muestraadministrativo;
import com.autoeval.entity.Muestraagencia;
import com.autoeval.entity.Muestradirector;
import com.autoeval.entity.Muestraegresado;
import com.autoeval.entity.Muestraempleador;
import com.autoeval.entity.Muestrapersona;
import com.autoeval.entity.Persona;
import com.autoeval.entity.Proceso;
import com.autoeval.interfaz.Action;
import java.io.IOException;
import java.util.Iterator;
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
public class EliminarPersonas implements Action {

    FuenteFacade fuenteFacade = lookupFuenteFacadeBean();
    EmpleadorFacade empleadorFacade = lookupEmpleadorFacadeBean();
    DirectorprogramaFacade directorprogramaFacade = lookupDirectorprogramaFacadeBean();
    AdministrativoFacade administrativoFacade = lookupAdministrativoFacadeBean();
    DocenteFacade docenteFacade = lookupDocenteFacadeBean();
    EstudianteFacade estudianteFacade = lookupEstudianteFacadeBean();
    EgresadoFacade egresadoFacade = lookupEgresadoFacadeBean();
    PersonaFacade personaFacade = lookupPersonaFacadeBean();
    MuestrapersonaFacade muestrapersonaFacade = lookupMuestrapersonaFacadeBean();

    @Override
    public String procesar(HttpServletRequest request) throws IOException, ServletException {
        HttpSession sesion = request.getSession();
        String fuente = request.getParameter("fuente");
        Proceso proce = (Proceso) sesion.getAttribute("Proceso");
        if (fuente.equals("Estudiante")) {

            personaFacade.eliminarPersonas("delete from estudiante where estudiante.proceso_id = '" + proce.getId() + "' \n"
                    + "                    and estudiante.persona_id not in(\n"
                    + "                    select muestrapersona.cedula from muestraestudiante\n"
                    + "                    inner join muestrapersona on muestrapersona.id = muestraestudiante.muestrapersona_id\n"
                    + "                    inner join muestra on muestra.id = muestrapersona.muestra_id\n"
                    + "                    where muestra.proceso_id = '" + proce.getId() + "')");

        } else if (fuente.equals("Docente")) {

            personaFacade.eliminarPersonas("delete from docente where docente.proceso_id = '" + proce.getId() + "' "
                    + "and docente.persona_id not in(\n"
                    + "select muestrapersona.cedula from muestradocente\n"
                    + "inner join muestrapersona on muestrapersona.id = muestradocente.muestrapersona_id\n"
                    + "inner join muestra on muestra.id = muestrapersona.muestra_id\n"
                    + "where muestra.proceso_id = '" + proce.getId() + "')");


        } else if (fuente.equals("Egresado")) {
            personaFacade.eliminarPersonas("delete from egresado where egresado.proceso_id = '" + proce.getId() + "' \n"
                    + "                    and egresado.persona_id not in(\n"
                    + "                    select muestrapersona.cedula from muestraegresado\n"
                    + "                    inner join muestrapersona on muestrapersona.id = muestraegresado.muestrapersona_id\n"
                    + "                    inner join muestra on muestra.id = muestrapersona.muestra_id\n"
                    + "                    where muestra.proceso_id = '" + proce.getId() + "')");

        } else if (fuente.equals("Administrativo")) {
            personaFacade.eliminarPersonas("delete from administrativo where administrativo.proceso_id = '" + proce.getId() + "' \n"
                    + "                    and administrativo.persona_id not in(\n"
                    + "                    select muestrapersona.cedula from muestraadministrativo\n"
                    + "                    inner join muestrapersona on muestrapersona.id = muestraadministrativo.muestrapersona_id\n"
                    + "                    inner join muestra on muestra.id = muestrapersona.muestra_id\n"
                    + "                    where muestra.proceso_id = '" + proce.getId() + "')");
        } else if (fuente.equals("Empleador")) {
            personaFacade.eliminarPersonas("delete from empleador where empleador.proceso_id = '" + proce.getId() + "' \n"
                    + "                    and empleador.persona_id not in(\n"
                    + "                    select muestrapersona.cedula from muestraempleador\n"
                    + "                    inner join muestrapersona on muestrapersona.id = muestraempleador.muestrapersona_id\n"
                    + "                    inner join muestra on muestra.id = muestrapersona.muestra_id\n"
                    + "                    where muestra.proceso_id = '" + proce.getId() + "')");
        } else if (fuente.equals("Directivo")) {
            personaFacade.eliminarPersonas("delete from directorprograma where directorprograma.proceso_id = '" + proce.getId() + "' \n"
                    + "                    and directorprograma.persona_id not in(\n"
                    + "                    select muestrapersona.cedula from muestradirector\n"
                    + "                    inner join muestrapersona on muestrapersona.id = muestradirector.muestrapersona_id\n"
                    + "                    inner join muestra on muestra.id = muestrapersona.muestra_id\n"
                    + "                    where muestra.proceso_id = '" + proce.getId() + "')");
        }


        return "NA";
    }

    private MuestrapersonaFacade lookupMuestrapersonaFacadeBean() {
        try {
            Context c = new InitialContext();
            return (MuestrapersonaFacade) c.lookup("java:global/autoevaluacion/MuestrapersonaFacade!com.autoeval.ejb.MuestrapersonaFacade");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private MuestraegresadoFacade lookupMuestraegresadoFacadeBean() {
        try {
            Context c = new InitialContext();
            return (MuestraegresadoFacade) c.lookup("java:global/autoevaluacion/MuestraegresadoFacade!com.autoeval.ejb.MuestraegresadoFacade");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private MuestraadministrativoFacade lookupMuestraadministrativoFacadeBean() {
        try {
            Context c = new InitialContext();
            return (MuestraadministrativoFacade) c.lookup("java:global/autoevaluacion/MuestraadministrativoFacade!com.autoeval.ejb.MuestraadministrativoFacade");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private MuestradirectorFacade lookupMuestradirectorFacadeBean() {
        try {
            Context c = new InitialContext();
            return (MuestradirectorFacade) c.lookup("java:global/autoevaluacion/MuestradirectorFacade!com.autoeval.ejb.MuestradirectorFacade");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private MuestraempleadorFacade lookupMuestraempleadorFacadeBean() {
        try {
            Context c = new InitialContext();
            return (MuestraempleadorFacade) c.lookup("java:global/autoevaluacion/MuestraempleadorFacade!com.autoeval.ejb.MuestraempleadorFacade");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private MuestraagenciaFacade lookupMuestraagenciaFacadeBean() {
        try {
            Context c = new InitialContext();
            return (MuestraagenciaFacade) c.lookup("java:global/autoevaluacion/MuestraagenciaFacade!com.autoeval.ejb.MuestraagenciaFacade");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private PersonaFacade lookupPersonaFacadeBean() {
        try {
            Context c = new InitialContext();
            return (PersonaFacade) c.lookup("java:global/autoevaluacion/PersonaFacade!com.autoeval.ejb.PersonaFacade");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private EgresadoFacade lookupEgresadoFacadeBean() {
        try {
            Context c = new InitialContext();
            return (EgresadoFacade) c.lookup("java:global/autoevaluacion/EgresadoFacade!com.autoeval.ejb.EgresadoFacade");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private EstudianteFacade lookupEstudianteFacadeBean() {
        try {
            Context c = new InitialContext();
            return (EstudianteFacade) c.lookup("java:global/autoevaluacion/EstudianteFacade!com.autoeval.ejb.EstudianteFacade");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private DocenteFacade lookupDocenteFacadeBean() {
        try {
            Context c = new InitialContext();
            return (DocenteFacade) c.lookup("java:global/autoevaluacion/DocenteFacade!com.autoeval.ejb.DocenteFacade");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private AdministrativoFacade lookupAdministrativoFacadeBean() {
        try {
            Context c = new InitialContext();
            return (AdministrativoFacade) c.lookup("java:global/autoevaluacion/AdministrativoFacade!com.autoeval.ejb.AdministrativoFacade");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private DirectorprogramaFacade lookupDirectorprogramaFacadeBean() {
        try {
            Context c = new InitialContext();
            return (DirectorprogramaFacade) c.lookup("java:global/autoevaluacion/DirectorprogramaFacade!com.autoeval.ejb.DirectorprogramaFacade");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private EmpleadorFacade lookupEmpleadorFacadeBean() {
        try {
            Context c = new InitialContext();
            return (EmpleadorFacade) c.lookup("java:global/autoevaluacion/EmpleadorFacade!com.autoeval.ejb.EmpleadorFacade");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
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
}
