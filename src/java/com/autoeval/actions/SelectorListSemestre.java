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
import com.autoeval.ejb.EncabezadoFacade;
import com.autoeval.ejb.EstudianteFacade;
import com.autoeval.ejb.FuenteFacade;
import com.autoeval.ejb.MuestraadministrativoFacade;
import com.autoeval.ejb.MuestradirectorFacade;
import com.autoeval.ejb.MuestradocenteFacade;
import com.autoeval.ejb.MuestraegresadoFacade;
import com.autoeval.ejb.MuestraempleadorFacade;
import com.autoeval.ejb.MuestraestudianteFacade;
import com.autoeval.entity.Muestra;
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
public class SelectorListSemestre implements Action {

    EmpleadorFacade empleadorFacade = lookupEmpleadorFacadeBean();
    DirectorprogramaFacade directorprogramaFacade = lookupDirectorprogramaFacadeBean();
    EgresadoFacade egresadoFacade = lookupEgresadoFacadeBean();
    AdministrativoFacade administrativoFacade = lookupAdministrativoFacadeBean();
    DocenteFacade docenteFacade = lookupDocenteFacadeBean();
    MuestradirectorFacade muestradirectorFacade = lookupMuestradirectorFacadeBean();
    MuestraadministrativoFacade muestraadministrativoFacade = lookupMuestraadministrativoFacadeBean();
    MuestraempleadorFacade muestraempleadorFacade = lookupMuestraempleadorFacadeBean();
    MuestraegresadoFacade muestraegresadoFacade = lookupMuestraegresadoFacadeBean();
    MuestradocenteFacade muestradocenteFacade = lookupMuestradocenteFacadeBean();
    EncabezadoFacade encabezadoFacade = lookupEncabezadoFacadeBean();
    EstudianteFacade estudianteFacade = lookupEstudianteFacadeBean();
    FuenteFacade fuenteFacade = lookupFuenteFacadeBean();
    MuestraestudianteFacade muestraestudianteFacade = lookupMuestraestudianteFacadeBean();

    @Override
    public String procesar(HttpServletRequest request) throws IOException, ServletException {
        HttpSession sesion = request.getSession();
        Programa programa = (Programa) sesion.getAttribute("Programa");
        Proceso proceso = (Proceso) sesion.getAttribute("Proceso");
        Muestra m = (Muestra) sesion.getAttribute("Muestra");
        String fuente;
        if (request.getParameter("botonCancelar") != null && request.getParameter("botonCancelar").equals("OK")) {
            return "/WEB-INF/vista/comitePrograma/muestra/selectorListMuestra.jsp";
        } else {
            if (request.getParameter("fuente") == null) {
                fuente = (String) sesion.getAttribute("selectorFuente");
            } else {
                fuente = request.getParameter("fuente");
                sesion.setAttribute("selectorFuente", fuente);
            }
            if (fuente.equals("Estudiante")) {
                //ESTUDIANTES
                sesion.setAttribute("listMuestraCon", muestraestudianteFacade.findByMuestraConEncabezado(proceso));
                sesion.setAttribute("listMuestraSin", muestraestudianteFacade.findByMuestraSinEncabezado(proceso));
                sesion.setAttribute("poblacion", estudianteFacade.findByList("procesoId", proceso));
            } else if (fuente.equals("Docente")) {
                //DOCENTES
                sesion.setAttribute("listMuestraCon", muestradocenteFacade.findByMuestraConEncabezado(proceso));
                sesion.setAttribute("listMuestraSin", muestradocenteFacade.findByMuestraSinEncabezado(proceso));
                sesion.setAttribute("poblacion", docenteFacade.findByList("procesoId", proceso));
            } else if (fuente.equals("Egresado")) {
                //EGRESADOS
                sesion.setAttribute("listMuestraCon", muestraegresadoFacade.findByMuestraConEncabezado(proceso));
                sesion.setAttribute("listMuestraSin", muestraegresadoFacade.findByMuestraSinEncabezado(proceso));
                sesion.setAttribute("poblacion", egresadoFacade.findByList("procesoId", proceso));
            } else if (fuente.equals("Administrativo")) {
                //ADMINISTRATIVOS
                sesion.setAttribute("listMuestraCon", muestraadministrativoFacade.findByMuestraConEncabezado(proceso));
                sesion.setAttribute("listMuestraSin", muestraadministrativoFacade.findByMuestraSinEncabezado(proceso));
                sesion.setAttribute("poblacion", administrativoFacade.findByList("procesoId", proceso));
            } else if (fuente.equals("Directivo")) {
                //DIRECTIVOS
                sesion.setAttribute("listMuestraCon", muestradirectorFacade.findByMuestraConEncabezado(proceso));
                sesion.setAttribute("listMuestraSin", muestradirectorFacade.findByMuestraSinEncabezado(proceso));
                sesion.setAttribute("poblacion", directorprogramaFacade.findByList("procesoId", proceso));
            } else if (fuente.equals("Empleador")) {
                //EMPLEADORES
                sesion.setAttribute("listMuestraCon", muestraempleadorFacade.findByMuestraConEncabezado(proceso));
                sesion.setAttribute("listMuestraSin", muestraempleadorFacade.findByMuestraSinEncabezado(proceso));
                sesion.setAttribute("poblacion", empleadorFacade.findByList("procesoId", proceso));
            }

            return "/WEB-INF/vista/comitePrograma/muestra/selectorListMuestra.jsp";
        }
    }

    private MuestraestudianteFacade lookupMuestraestudianteFacadeBean() {
        try {
            Context c = new InitialContext();
            return (MuestraestudianteFacade) c.lookup("java:global/autoevaluacion/MuestraestudianteFacade!com.autoeval.ejb.MuestraestudianteFacade");
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

    private EstudianteFacade lookupEstudianteFacadeBean() {
        try {
            Context c = new InitialContext();
            return (EstudianteFacade) c.lookup("java:global/autoevaluacion/EstudianteFacade!com.autoeval.ejb.EstudianteFacade");
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

    private MuestradocenteFacade lookupMuestradocenteFacadeBean() {
        try {
            Context c = new InitialContext();
            return (MuestradocenteFacade) c.lookup("java:global/autoevaluacion/MuestradocenteFacade!com.autoeval.ejb.MuestradocenteFacade");
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

    private MuestraempleadorFacade lookupMuestraempleadorFacadeBean() {
        try {
            Context c = new InitialContext();
            return (MuestraempleadorFacade) c.lookup("java:global/autoevaluacion/MuestraempleadorFacade!com.autoeval.ejb.MuestraempleadorFacade");
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

    private EgresadoFacade lookupEgresadoFacadeBean() {
        try {
            Context c = new InitialContext();
            return (EgresadoFacade) c.lookup("java:global/autoevaluacion/EgresadoFacade!com.autoeval.ejb.EgresadoFacade");
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
}
