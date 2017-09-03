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
import com.autoeval.ejb.MuestraFacade;
import com.autoeval.ejb.MuestraadministrativoFacade;
import com.autoeval.ejb.MuestraagenciaFacade;
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
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

/**
 *
 * @author acreditacion
 */
public class SelectorListMuestra implements Action {

    private static final Logger LOGGER = Logger.getLogger(SelectorListMuestra.class);
    MuestraagenciaFacade muestraagenciaFacade = lookupMuestraagenciaFacadeBean();
    EmpleadorFacade empleadorFacade = lookupEmpleadorFacadeBean();
    MuestraempleadorFacade muestraempleadorFacade = lookupMuestraempleadorFacadeBean();
    DirectorprogramaFacade directorprogramaFacade = lookupDirectorprogramaFacadeBean();
    MuestradirectorFacade muestradirectorFacade = lookupMuestradirectorFacadeBean();
    AdministrativoFacade administrativoFacade = lookupAdministrativoFacadeBean();
    MuestraadministrativoFacade muestraadministrativoFacade = lookupMuestraadministrativoFacadeBean();
    EgresadoFacade egresadoFacade = lookupEgresadoFacadeBean();
    MuestraegresadoFacade muestraegresadoFacade = lookupMuestraegresadoFacadeBean();
    EncabezadoFacade encabezadoFacade = lookupEncabezadoFacadeBean();
    DocenteFacade docenteFacade = lookupDocenteFacadeBean();
    MuestradocenteFacade muestradocenteFacade = lookupMuestradocenteFacadeBean();
    MuestraestudianteFacade muestraestudianteFacade = lookupMuestraestudianteFacadeBean();
    FuenteFacade fuenteFacade = lookupFuenteFacadeBean();
    MuestraFacade muestraFacade = lookupMuestraFacadeBean();
    EstudianteFacade estudianteFacade = lookupEstudianteFacadeBean();

    @Override
    public String procesar(HttpServletRequest request) throws IOException, ServletException {
        try {
            HttpSession sesion = request.getSession();
            Proceso proceso = (Proceso) sesion.getAttribute("Proceso");
            List<Muestra> lm = muestraFacade.findByList("procesoId", proceso);
            String fuente = request.getParameter("fuente");
            sesion.setAttribute("fuenteX", "" + fuente);
            Muestra m = null;
            if (!lm.isEmpty()) {
                Iterator i = lm.iterator();
                while (i.hasNext()) {
                    m = (Muestra) i.next();
                    sesion.setAttribute("Muestra", m);

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
                }
            } else {
                sesion.setAttribute("Muestra", m); //null
                if (fuente.equals("Estudiante")) {
                        //ESTUDIANTES
                        sesion.setAttribute("poblacion", estudianteFacade.findByList("procesoId", proceso));
                    } else if (fuente.equals("Docente")) {
                        //DOCENTES
                        sesion.setAttribute("poblacion", docenteFacade.findByList("procesoId", proceso));
                    } else if (fuente.equals("Egresado")) {
                        //EGRESADOS
                        sesion.setAttribute("poblacion", egresadoFacade.findByList("procesoId", proceso));
                    } else if (fuente.equals("Administrativo")) {
                        //ADMINISTRATIVOS
                        sesion.setAttribute("poblacion", administrativoFacade.findByList("procesoId", proceso));
                    } else if (fuente.equals("Directivo")) {
                        //DIRECTIVOS
                        sesion.setAttribute("poblacion", directorprogramaFacade.findByList("procesoId", proceso));
                    } else if (fuente.equals("Empleador")) {
                        //EMPLEADORES
                        sesion.setAttribute("poblacion", empleadorFacade.findByList("procesoId", proceso));
                    }
                
            }
        } catch (Exception e) {
            LOGGER.error("Se ha presentado un error: ", e);
        } finally {
            return "/WEB-INF/vista/comitePrograma/muestra/selectorListMuestra.jsp";
        }

    }

    private FuenteFacade lookupFuenteFacadeBean() {
        try {
            Context c = new InitialContext();
            return (FuenteFacade) c.lookup("java:global/autoevaluacion/FuenteFacade!com.autoeval.ejb.FuenteFacade");
        } catch (NamingException ne) {
            LOGGER.error("exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private MuestraestudianteFacade lookupMuestraestudianteFacadeBean() {
        try {
            Context c = new InitialContext();
            return (MuestraestudianteFacade) c.lookup("java:global/autoevaluacion/MuestraestudianteFacade!com.autoeval.ejb.MuestraestudianteFacade");
        } catch (NamingException ne) {
            LOGGER.error("exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private MuestradocenteFacade lookupMuestradocenteFacadeBean() {
        try {
            Context c = new InitialContext();
            return (MuestradocenteFacade) c.lookup("java:global/autoevaluacion/MuestradocenteFacade!com.autoeval.ejb.MuestradocenteFacade");
        } catch (NamingException ne) {
            LOGGER.error("exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private DocenteFacade lookupDocenteFacadeBean() {
        try {
            Context c = new InitialContext();
            return (DocenteFacade) c.lookup("java:global/autoevaluacion/DocenteFacade!com.autoeval.ejb.DocenteFacade");
        } catch (NamingException ne) {
            LOGGER.error("exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private EncabezadoFacade lookupEncabezadoFacadeBean() {
        try {
            Context c = new InitialContext();
            return (EncabezadoFacade) c.lookup("java:global/autoevaluacion/EncabezadoFacade!com.autoeval.ejb.EncabezadoFacade");
        } catch (NamingException ne) {
            LOGGER.error("exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private MuestraegresadoFacade lookupMuestraegresadoFacadeBean() {
        try {
            Context c = new InitialContext();
            return (MuestraegresadoFacade) c.lookup("java:global/autoevaluacion/MuestraegresadoFacade!com.autoeval.ejb.MuestraegresadoFacade");
        } catch (NamingException ne) {
            LOGGER.error("exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private EgresadoFacade lookupEgresadoFacadeBean() {
        try {
            Context c = new InitialContext();
            return (EgresadoFacade) c.lookup("java:global/autoevaluacion/EgresadoFacade!com.autoeval.ejb.EgresadoFacade");
        } catch (NamingException ne) {
            LOGGER.error("exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private MuestraadministrativoFacade lookupMuestraadministrativoFacadeBean() {
        try {
            Context c = new InitialContext();
            return (MuestraadministrativoFacade) c.lookup("java:global/autoevaluacion/MuestraadministrativoFacade!com.autoeval.ejb.MuestraadministrativoFacade");
        } catch (NamingException ne) {
            LOGGER.error("exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private AdministrativoFacade lookupAdministrativoFacadeBean() {
        try {
            Context c = new InitialContext();
            return (AdministrativoFacade) c.lookup("java:global/autoevaluacion/AdministrativoFacade!com.autoeval.ejb.AdministrativoFacade");
        } catch (NamingException ne) {
            LOGGER.error("exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private MuestradirectorFacade lookupMuestradirectorFacadeBean() {
        try {
            Context c = new InitialContext();
            return (MuestradirectorFacade) c.lookup("java:global/autoevaluacion/MuestradirectorFacade!com.autoeval.ejb.MuestradirectorFacade");
        } catch (NamingException ne) {
            LOGGER.error("exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private DirectorprogramaFacade lookupDirectorprogramaFacadeBean() {
        try {
            Context c = new InitialContext();
            return (DirectorprogramaFacade) c.lookup("java:global/autoevaluacion/DirectorprogramaFacade!com.autoeval.ejb.DirectorprogramaFacade");
        } catch (NamingException ne) {
            LOGGER.error("exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private MuestraempleadorFacade lookupMuestraempleadorFacadeBean() {
        try {
            Context c = new InitialContext();
            return (MuestraempleadorFacade) c.lookup("java:global/autoevaluacion/MuestraempleadorFacade!com.autoeval.ejb.MuestraempleadorFacade");
        } catch (NamingException ne) {
            LOGGER.error("exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private EmpleadorFacade lookupEmpleadorFacadeBean() {
        try {
            Context c = new InitialContext();
            return (EmpleadorFacade) c.lookup("java:global/autoevaluacion/EmpleadorFacade!com.autoeval.ejb.EmpleadorFacade");
        } catch (NamingException ne) {
            LOGGER.error("exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private MuestraagenciaFacade lookupMuestraagenciaFacadeBean() {
        try {
            Context c = new InitialContext();
            return (MuestraagenciaFacade) c.lookup("java:global/autoevaluacion/MuestraagenciaFacade!com.autoeval.ejb.MuestraagenciaFacade");
        } catch (NamingException ne) {
            LOGGER.error("exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private MuestraFacade lookupMuestraFacadeBean() {
        try {
            Context c = new InitialContext();
            return (MuestraFacade) c.lookup("java:global/autoevaluacion/MuestraFacade!com.autoeval.ejb.MuestraFacade");
        } catch (NamingException ne) {
            java.util.logging.Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private EstudianteFacade lookupEstudianteFacadeBean() {
        try {
            Context c = new InitialContext();
            return (EstudianteFacade) c.lookup("java:global/autoevaluacion/EstudianteFacade!com.autoeval.ejb.EstudianteFacade");
        } catch (NamingException ne) {
            java.util.logging.Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
