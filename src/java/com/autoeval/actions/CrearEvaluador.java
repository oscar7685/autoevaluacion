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
public class CrearEvaluador implements Action {

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
        String cedula = request.getParameter("cedula");

        String fuente = (String) sesion.getAttribute("selectorFuente");
        Proceso proce = (Proceso) sesion.getAttribute("Proceso");

        Muestrapersona mp = null;
        //BUSCAMOS A LA PERSONA A CREAR A VER SI EXISTE
        Persona per = personaFacade.find(cedula);

        //SI EXISTE
        if (per != null) {
            Muestra m = (Muestra) sesion.getAttribute("Muestra");
            List lmp = muestrapersonaFacade.findByList2("cedula", cedula, "muestraId", m);
            Iterator it = lmp.iterator();

            while (it.hasNext()) {
                mp = (Muestrapersona) it.next();
            }
            //VERIFICAMOS SI YA EXISTE EN EL PROCESO ACTUAL
            if (mp != null) {//ESTA EN LA MUESTRA ACTUAL --ERROR DUPLICADO
                return "9";
            }

        } else {// NO EXISTE LA PERSONA Y SE TIENE QUE CREAR
            String nombre = request.getParameter("nombre");
            String apellido = request.getParameter("apellido");
            String pass = request.getParameter("password");
            String mail = request.getParameter("mail");

            Persona p = new Persona();
            p.setId(cedula);
            p.setNombre(nombre);
            p.setApellido(apellido);
            p.setMail(mail);
            p.setPassword(pass);

            personaFacade.create(p);
            per = personaFacade.find(cedula);
        }

        //CREAMOS LA FUENTE ESPECIFICA
        if (fuente.equals("Estudiante")) {

            String curso = request.getParameter("curso");
            String tipo = "";
            if (proce.getModeloId().getId() != 1) {
                if (proce.getModeloId().getId() == 2) {
                    tipo = "PREGRADO";
                } else if (proce.getModeloId().getId() == 3) {
                    tipo = "MAESTRIA";
                } else if (proce.getModeloId().getId() == 4) {
                    tipo = "ESPECIALIZACION";
                }
            } else {
                tipo = request.getParameter("tipo");
            }

            Estudiante nuevo = new Estudiante();
            nuevo.setId("" + proce.getId() + "-" + cedula);
            nuevo.setSemestre("03");
            nuevo.setPeriodo("02");
            nuevo.setAnio("2016");
            nuevo.setPersonaId(per);
            nuevo.setProgramaId(proce.getProgramaId());
            nuevo.setTipo(tipo);
            nuevo.setProcesoId(proce);
            nuevo.setCurso(curso);

            if (tipo.equals("PREGRADO") || tipo.equals("OFICIALES") || tipo.equals("CADETES")) {
                nuevo.setFuenteId(fuenteFacade.find(1));
            } else if (tipo.equals("ESPECIALIZACION")) {
                nuevo.setFuenteId(fuenteFacade.find(7));
            } else if (tipo.equals("MAESTRIA")) {
                nuevo.setFuenteId(fuenteFacade.find(8));
            }
            estudianteFacade.create(nuevo);

        } else if (fuente.equals("Egresado")) {
            String tipo = "";
            if (proce.getModeloId().getId() != 1) {
                if (proce.getModeloId().getId() == 2) {
                    tipo = "PREGRADO";
                } else if (proce.getModeloId().getId() == 3) {
                    tipo = "MAESTRIA";
                } else if (proce.getModeloId().getId() == 4) {
                    tipo = "ESPECIALIZACION";
                }
            } else {
                tipo = request.getParameter("tipo");
            }

            Egresado nuevo = new Egresado();
            nuevo.setPersonaId(per);
            nuevo.setProgramaId(proce.getProgramaId());
            nuevo.setTipo(tipo);
            nuevo.setProcesoId(proce);
            if (tipo.equals("PREGRADO")) {
                nuevo.setFuenteId(fuenteFacade.find(4));
            } else if (tipo.equals("ESPECIALIZACION")) {
                nuevo.setFuenteId(fuenteFacade.find(9));
            } else if (tipo.equals("MAESTRIA")) {
                nuevo.setFuenteId(fuenteFacade.find(10));
            }

            egresadoFacade.create(nuevo);

        } else if (fuente.equals("Administrativo")) {
            String tipo = request.getParameter("tipo");

            Administrativo nuevo = new Administrativo();
            nuevo.setPersonaId(per);
            nuevo.setTipo(tipo);
            nuevo.setCargo("cargo");
            nuevo.setFuenteId(fuenteFacade.find(3));
            nuevo.setProgramaId(proce.getProgramaId());
            nuevo.setProcesoId(proce);

            administrativoFacade.create(nuevo);


        } else if (fuente.equals("Directivo")) {
            Directorprograma nuevo = new Directorprograma();
            nuevo.setPersonaId(per);
            nuevo.setFuenteId(fuenteFacade.find(5));
            nuevo.setProgramaId(proce.getProgramaId());
            nuevo.setProcesoId(proce);

            directorprogramaFacade.create(nuevo);

        } else if (fuente.equals("Empleador")) {

            String empresa = request.getParameter("empresa");
            Empleador nuevo = new Empleador();
            nuevo.setCargo("cargo");
            nuevo.setEmpresa(empresa);
            nuevo.setFuenteId(fuenteFacade.find(6));
            nuevo.setPersonaId(per);
            nuevo.setProcesoId(proce);
            nuevo.setProgramaId(proce.getProgramaId());

            empleadorFacade.create(nuevo);


        } else if (fuente.equals("Docente")) {
            String tipo = request.getParameter("tipo");

            Docente nuevo = new Docente();
            nuevo.setPersonaId(per);
            nuevo.setProcesoId(proce);
            nuevo.setProgramaId(proce.getProgramaId());
            nuevo.setTipo(tipo);

            if (tipo.equals("MILITAR") || tipo.equals("NOMINA") || tipo.equals("OCASIONALES")) {
                nuevo.setFuenteId(fuenteFacade.find(2));
            } else if (tipo.equals("CATEDRA")) {
                nuevo.setFuenteId(fuenteFacade.find(11));
            }
            docenteFacade.create(nuevo);

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
