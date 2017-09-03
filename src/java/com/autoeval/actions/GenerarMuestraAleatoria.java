/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.autoeval.actions;

import com.autoeval.controller.passwordGenerator;
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
import com.autoeval.ejb.MuestradocenteFacade;
import com.autoeval.ejb.MuestraegresadoFacade;
import com.autoeval.ejb.MuestraempleadorFacade;
import com.autoeval.ejb.MuestraestudianteFacade;
import com.autoeval.ejb.MuestrapersonaFacade;
import com.autoeval.ejb.PersonaFacade;
import com.autoeval.entity.Administrativo;
import com.autoeval.entity.Directorprograma;
import com.autoeval.entity.Docente;
import com.autoeval.entity.Egresado;
import com.autoeval.entity.Empleador;
import com.autoeval.entity.Estudiante;
import com.autoeval.entity.Fuente;
import com.autoeval.entity.Muestra;
import com.autoeval.entity.Muestraadministrativo;
import com.autoeval.entity.Muestraagencia;
import com.autoeval.entity.Muestradirector;
import com.autoeval.entity.Muestradocente;
import com.autoeval.entity.Muestraegresado;
import com.autoeval.entity.Muestraempleador;
import com.autoeval.entity.Muestraestudiante;
import com.autoeval.entity.Muestrapersona;
import com.autoeval.entity.Persona;
import com.autoeval.entity.Proceso;
import com.autoeval.entity.Programa;
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
public class GenerarMuestraAleatoria implements Action{
    MuestraagenciaFacade muestraagenciaFacade = lookupMuestraagenciaFacadeBean();
    MuestraempleadorFacade muestraempleadorFacade = lookupMuestraempleadorFacadeBean();
    EmpleadorFacade empleadorFacade = lookupEmpleadorFacadeBean();
    MuestradirectorFacade muestradirectorFacade = lookupMuestradirectorFacadeBean();
    DirectorprogramaFacade directorprogramaFacade = lookupDirectorprogramaFacadeBean();
    MuestraadministrativoFacade muestraadministrativoFacade = lookupMuestraadministrativoFacadeBean();
    AdministrativoFacade administrativoFacade = lookupAdministrativoFacadeBean();
    MuestraegresadoFacade muestraegresadoFacade = lookupMuestraegresadoFacadeBean();
    EgresadoFacade egresadoFacade = lookupEgresadoFacadeBean();
    MuestradocenteFacade muestradocenteFacade = lookupMuestradocenteFacadeBean();
    DocenteFacade docenteFacade = lookupDocenteFacadeBean();
    MuestraestudianteFacade muestraestudianteFacade = lookupMuestraestudianteFacadeBean();
    EstudianteFacade estudianteFacade = lookupEstudianteFacadeBean();
    FuenteFacade fuenteFacade = lookupFuenteFacadeBean();
    PersonaFacade personaFacade = lookupPersonaFacadeBean();
    MuestrapersonaFacade muestrapersonaFacade = lookupMuestrapersonaFacadeBean();

    @Override
    public String procesar(HttpServletRequest request) throws IOException, ServletException {
        HttpSession sesion = request.getSession();
        Proceso proceso = (Proceso) sesion.getAttribute("Proceso");
        Programa programa = (Programa) sesion.getAttribute("Programa");
        Muestra muestra = (Muestra) sesion.getAttribute("Muestra");
            List l = muestrapersonaFacade.findLast(sesion.getAttribute("Muestra"));

            Iterator it = l.iterator();

            int id = 0;

            while (it.hasNext()) {
                Muestrapersona mp = (Muestrapersona) it.next();
                id = mp.getId();
            }

            int numero = Integer.valueOf(request.getParameter("numero"));

            for (int i = 0; i < numero; i++) {

                id++;

                String pass = passwordGenerator.getPassword(
                        passwordGenerator.NUMEROS
                        + passwordGenerator.NUMEROS, 6);

                String nombre = "Usuario";
                String apellido = "Aleatorio";
                String cedula = programa.getId() + proceso.getId() + " - ";
                String mail = "correo@correo.com";

                Muestrapersona mp1 = new Muestrapersona();
                Persona per = new Persona();
                String idx = "00" + programa.getId() + proceso.getId() + muestra.getId() + "-" + id;
                mp1.setCedula(idx);
                mp1.setNombre(nombre);
                mp1.setApellido(apellido);
                mp1.setPassword(pass);
                mp1.setMail(mail);
                mp1.setMuestraId((Muestra) sesion.getAttribute("Muestra"));

                per.setId(idx);
                per.setNombre(nombre);
                per.setApellido(apellido);
                per.setPassword(pass);
                per.setMail(mail);
                personaFacade.create(per);

                muestrapersonaFacade.create(mp1);

                String fuente = (String) sesion.getAttribute("selectorFuente");

                if (fuente.equals("Estudiante")) {
                    Estudiante est = new Estudiante();
                    Fuente fuenteEst = fuenteFacade.find(1);
                    est.setFuenteId(fuenteEst);
                    est.setSemestre("03");
                    est.setPeriodo("2");
                    est.setAnio("2013");
                    est.setPersonaId(per);
                    est.setId(idx);
                    est.setProgramaId(programa);
                    estudianteFacade.create(est);

                    Muestra m = (Muestra) sesion.getAttribute("Muestra");

                    Muestraestudiante me1 = new Muestraestudiante();
                    me1.setCodigo(idx);
                    me1.setPeriodo("2");
                    me1.setProgramaId(programa);
                    me1.setSemestre("03");
                    me1.setAnio("2013");
                    me1.setMuestrapersonaId(mp1);
                    muestraestudianteFacade.create(me1);
                } else if (fuente.equals("Docente")) {
                    Docente doc = new Docente();
                    Fuente fuenteDoc = fuenteFacade.find(2);
                    doc.setFuenteId(fuenteDoc);
                    doc.setTipo("--");
                    doc.setPersonaId(per);
                    doc.setProgramaId(programa);
                    docenteFacade.create(doc);

                    Muestra m = (Muestra) sesion.getAttribute("Muestra");

                    Muestradocente md1 = new Muestradocente();
                    md1.setTipo("--");
                    md1.setMuestrapersonaId(mp1);
                    muestradocenteFacade.create(md1);
                } else if (fuente.equals("Egresado")) {
                    Egresado egre = new Egresado();
                    Fuente fuenteEgr = fuenteFacade.find(4);
                    egre.setFuenteId(fuenteEgr);
                    egre.setPersonaId(per);
                    egre.setProgramaId(programa);
                    egresadoFacade.create(egre);

                    Muestra m = (Muestra) sesion.getAttribute("Muestra");

                    Muestraegresado meg1 = new Muestraegresado();
                    meg1.setMuestrapersonaId(mp1);
                    muestraegresadoFacade.create(meg1);
                } else if (fuente.equals("Administrativo")) {
                    Administrativo admin = new Administrativo();
                    Fuente fuenteAdmin = fuenteFacade.find(3);
                    admin.setFuenteId(fuenteAdmin);
                    admin.setPersonaId(per);
                    admin.setProgramaId(programa);
                    admin.setCargo("--");
                    administrativoFacade.create(admin);

                    Muestra m = (Muestra) sesion.getAttribute("Muestra");

                    Muestraadministrativo mad1 = new Muestraadministrativo();
                    mad1.setCargo("--");
                    mad1.setMuestrapersonaId(mp1);
                    muestraadministrativoFacade.create(mad1);
                } else if (fuente.equals("Directivo")) {
                    Directorprograma dir = new Directorprograma();
                    Fuente fuenteDir = fuenteFacade.find(5);
                    dir.setFuenteId(fuenteDir);
                    dir.setPersonaId(per);
                    dir.setProgramaId(programa);
                    directorprogramaFacade.create(dir);

                    Muestra m = (Muestra) sesion.getAttribute("Muestra");

                    Muestradirector mdir1 = new Muestradirector();
                    mdir1.setMuestrapersonaId(mp1);
                    muestradirectorFacade.create(mdir1);
                } else if (fuente.equals("Empleador")) {
                    Empleador emp = new Empleador();
                    Fuente fuenteEmp = fuenteFacade.find(6);
                    emp.setFuenteId(fuenteEmp);
                    emp.setPersonaId(per);
                    emp.setProgramaId(programa);
                    emp.setCargo("Representante");
                    emp.setEmpresa("Empresa Aleatoria");
                    empleadorFacade.create(emp);

                    Muestra m = (Muestra) sesion.getAttribute("Muestra");

                    Muestraempleador mem1 = new Muestraempleador();
                    mem1.setCargo("--");
                    mem1.setEmpresa("--");
                    mem1.setMuestrapersonaId(mp1);
                    muestraempleadorFacade.create(mem1);
                } else if (fuente.equals("Agencia")) {
                    Muestra m = (Muestra) sesion.getAttribute("Muestra");

                    Muestraagencia mag1 = new Muestraagencia();
                    mag1.setDescripcion("--");
                    mag1.setMuestrapersonaId(mp1);
                    muestraagenciaFacade.create(mag1);
                }
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

    private PersonaFacade lookupPersonaFacadeBean() {
        try {
            Context c = new InitialContext();
            return (PersonaFacade) c.lookup("java:global/autoevaluacion/PersonaFacade!com.autoeval.ejb.PersonaFacade");
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

    private MuestraestudianteFacade lookupMuestraestudianteFacadeBean() {
        try {
            Context c = new InitialContext();
            return (MuestraestudianteFacade) c.lookup("java:global/autoevaluacion/MuestraestudianteFacade!com.autoeval.ejb.MuestraestudianteFacade");
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

    private MuestradocenteFacade lookupMuestradocenteFacadeBean() {
        try {
            Context c = new InitialContext();
            return (MuestradocenteFacade) c.lookup("java:global/autoevaluacion/MuestradocenteFacade!com.autoeval.ejb.MuestradocenteFacade");
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

    private MuestraegresadoFacade lookupMuestraegresadoFacadeBean() {
        try {
            Context c = new InitialContext();
            return (MuestraegresadoFacade) c.lookup("java:global/autoevaluacion/MuestraegresadoFacade!com.autoeval.ejb.MuestraegresadoFacade");
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

    private MuestraadministrativoFacade lookupMuestraadministrativoFacadeBean() {
        try {
            Context c = new InitialContext();
            return (MuestraadministrativoFacade) c.lookup("java:global/autoevaluacion/MuestraadministrativoFacade!com.autoeval.ejb.MuestraadministrativoFacade");
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

    private MuestradirectorFacade lookupMuestradirectorFacadeBean() {
        try {
            Context c = new InitialContext();
            return (MuestradirectorFacade) c.lookup("java:global/autoevaluacion/MuestradirectorFacade!com.autoeval.ejb.MuestradirectorFacade");
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
    
}
