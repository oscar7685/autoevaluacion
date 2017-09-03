/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.autoeval.actions;

import com.autoeval.ejb.AdministrativoFacade;
import com.autoeval.ejb.AgenciagubernamentalFacade;
import com.autoeval.ejb.DirectorprogramaFacade;
import com.autoeval.ejb.DocenteFacade;
import com.autoeval.ejb.EgresadoFacade;
import com.autoeval.ejb.EmpleadorFacade;
import com.autoeval.ejb.EstudianteFacade;
import com.autoeval.ejb.MuestraFacade;
import com.autoeval.ejb.MuestraadministrativoFacade;
import com.autoeval.ejb.MuestraagenciaFacade;
import com.autoeval.ejb.MuestradirectorFacade;
import com.autoeval.ejb.MuestradocenteFacade;
import com.autoeval.ejb.MuestraegresadoFacade;
import com.autoeval.ejb.MuestraempleadorFacade;
import com.autoeval.ejb.MuestraestudianteFacade;
import com.autoeval.ejb.MuestrapersonaFacade;
import com.autoeval.entity.Administrativo;
import com.autoeval.entity.Agenciagubernamental;
import com.autoeval.entity.Directorprograma;
import com.autoeval.entity.Docente;
import com.autoeval.entity.Egresado;
import com.autoeval.entity.Empleador;
import com.autoeval.entity.Estudiante;
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
public class GenerarMuestra implements Action {

    MuestraagenciaFacade muestraagenciaFacade = lookupMuestraagenciaFacadeBean();
    AgenciagubernamentalFacade agenciagubernamentalFacade = lookupAgenciagubernamentalFacadeBean();
    MuestraempleadorFacade muestraempleadorFacade = lookupMuestraempleadorFacadeBean();
    EmpleadorFacade empleadorFacade = lookupEmpleadorFacadeBean();
    MuestraadministrativoFacade muestraadministrativoFacade = lookupMuestraadministrativoFacadeBean();
    AdministrativoFacade administrativoFacade = lookupAdministrativoFacadeBean();
    MuestradirectorFacade muestradirectorFacade = lookupMuestradirectorFacadeBean();
    DirectorprogramaFacade directorprogramaFacade = lookupDirectorprogramaFacadeBean();
    MuestraegresadoFacade muestraegresadoFacade = lookupMuestraegresadoFacadeBean();
    EgresadoFacade egresadoFacade = lookupEgresadoFacadeBean();
    MuestradocenteFacade muestradocenteFacade = lookupMuestradocenteFacadeBean();
    DocenteFacade docenteFacade = lookupDocenteFacadeBean();
    MuestraestudianteFacade muestraestudianteFacade = lookupMuestraestudianteFacadeBean();
    MuestrapersonaFacade muestrapersonaFacade = lookupMuestrapersonaFacadeBean();
    EstudianteFacade estudianteFacade = lookupEstudianteFacadeBean();
    MuestraFacade muestraFacade = lookupMuestraFacadeBean();

    @Override
    public String procesar(HttpServletRequest request) throws IOException, ServletException {
        HttpSession sesion = request.getSession();
        Proceso proceso = (Proceso) sesion.getAttribute("Proceso");
        Programa programa = (Programa) sesion.getAttribute("Programa");
        Muestra m = new Muestra();
        m.setProcesoId(proceso);
        muestraFacade.create(m);

        sesion.setAttribute("Muestra", m);

        //Tamaño muestra
        double n = 0;
        //Nivel de confianza
        double z = 1.96;
        //Probabilidad Ocurrencia
        double p = 0.5;
        //Nivel de tolerancia 
        double e = 0.04;
        //Porbabilidad de no ocurrencia
        double q = 0.5;
        //Tamaño de la población
        double N = 0.0;

        //********************************Estudiante
        double aux = estudianteFacade.countByProperty("programaId", programa);

        N = aux;

        if (N != 0.0) {
            n = (N * p * q * (z * z)) / ((N - 1) * (e * e) + p * q * (z * z));
        }

        double cociente = n / N;

        for (int i = 3; i < 10; i++) {

            int tamanioMuestra1 = 0;

            int tamaniosem = estudianteFacade.countByProperty2("programaId", programa, "semestre", "0" + i);

            tamanioMuestra1 = (int) Math.round(tamaniosem * cociente);

            List<Estudiante> le = estudianteFacade.generarMuestraEst(programa, tamanioMuestra1, "semestre", "0" + i);

            Iterator it = le.iterator();

            if (!le.isEmpty()) {
                while (it.hasNext()) {
                    Estudiante est = (Estudiante) it.next();
                    Persona per = est.getPersonaId();

                    Muestrapersona mp = new Muestrapersona();

                    mp.setCedula(per.getId());
                    mp.setNombre(per.getNombre());
                    mp.setApellido(per.getApellido());
                    mp.setPassword(per.getPassword());
                    mp.setMail(per.getMail());
                    mp.setMuestraId(m);

                    muestrapersonaFacade.create(mp);

                    Muestraestudiante me = new Muestraestudiante();
                    me.setCodigo(est.getId());
                    me.setSemestre(est.getSemestre());
                    me.setPeriodo(est.getPeriodo());
                    me.setAnio(est.getAnio());
                    me.setMuestrapersonaId(mp);
                    me.setProgramaId(programa);
                    me.setTipo(est.getTipo());

                    muestraestudianteFacade.create(me);
                }
            }

        }

        //********************************Docente
        int tamanioMuestra = 0;
        Iterator it;
        aux = docenteFacade.countByProperty("programaId", programa);

        N = aux;

        if (N != 0.0) {
            n = (N * p * q * (z * z)) / ((N - 1) * (e * e) + p * q * (z * z));

            tamanioMuestra = (int) Math.floor(n);
            //no se está usando
            List<Docente> ld = docenteFacade.generarMuestraX(programa, tamanioMuestra);

            it = ld.iterator();

            if (!ld.isEmpty()) {
                while (it.hasNext()) {
                    Docente doc = (Docente) it.next();
                    Persona per = doc.getPersonaId();

                    Muestrapersona mp = new Muestrapersona();

                    mp.setCedula(per.getId());
                    mp.setNombre(per.getNombre());
                    mp.setApellido(per.getApellido());
                    mp.setPassword(per.getPassword());
                    mp.setMail(per.getMail());
                    mp.setMuestraId(m);

                    muestrapersonaFacade.create(mp);

                    Muestradocente md = new Muestradocente();
                    md.setTipo(doc.getTipo());
                    md.setMuestrapersonaId(mp);

                    muestradocenteFacade.create(md);
                }
            }

        }

        //********************************Egresado
        aux = egresadoFacade.countByProperty("programaId", programa);

        N = aux;

        if (N != 0.0) {
            n = (N * p * q * (z * z)) / ((N - 1) * (e * e) + p * q * (z * z));

            tamanioMuestra = (int) Math.floor(n);
            //no se está usando
            List<Egresado> leg = egresadoFacade.generarMuestraX(programa, tamanioMuestra);

            it = leg.iterator();

            if (!leg.isEmpty()) {
                while (it.hasNext()) {
                    Egresado eg = (Egresado) it.next();
                    Persona per = eg.getPersonaId();

                    Muestrapersona mp = new Muestrapersona();

                    mp.setCedula(per.getId());
                    mp.setNombre(per.getNombre());
                    mp.setApellido(per.getApellido());
                    mp.setPassword(per.getPassword());
                    mp.setMail(per.getMail());
                    mp.setMuestraId(m);

                    muestrapersonaFacade.create(mp);

                    Muestraegresado meg = new Muestraegresado();
                    meg.setMuestrapersonaId(mp);
                    meg.setTipo(eg.getTipo());

                    muestraegresadoFacade.create(meg);
                }
            }
        }

        //********************************Director
        aux = directorprogramaFacade.countByProperty("programaId", programa);

        N = aux;

        if (N != 0.0) {
            n = (N * p * q * (z * z)) / ((N - 1) * (e * e) + p * q * (z * z));

            tamanioMuestra = (int) Math.floor(n);
            //no se está usando
            List<Directorprograma> ldp = directorprogramaFacade.generarMuestraX(programa, tamanioMuestra);

            it = ldp.iterator();

            if (!ldp.isEmpty()) {
                while (it.hasNext()) {
                    Directorprograma dp = (Directorprograma) it.next();
                    Persona per = dp.getPersonaId();

                    Muestrapersona mp = new Muestrapersona();

                    mp.setCedula(per.getId());
                    mp.setNombre(per.getNombre());
                    mp.setApellido(per.getApellido());
                    mp.setPassword(per.getPassword());
                    mp.setMail(per.getMail());
                    mp.setMuestraId(m);

                    muestrapersonaFacade.create(mp);

                    Muestradirector mdp = new Muestradirector();
                    mdp.setMuestrapersonaId(mp);

                    muestradirectorFacade.create(mdp);
                }
            }

        }

        //********************************Administrativo
        aux = administrativoFacade.countByProperty("programaId", programa);

        N = aux;

        if (N != 0.0) {
            n = (N * p * q * (z * z)) / ((N - 1) * (e * e) + p * q * (z * z));

            tamanioMuestra = (int) Math.floor(n);
            //no se está usando
            List<Administrativo> lad = administrativoFacade.generarMuestraX(programa, tamanioMuestra);

            it = lad.iterator();

            if (!lad.isEmpty()) {
                while (it.hasNext()) {
                    Administrativo ad = (Administrativo) it.next();
                    Persona per = ad.getPersonaId();

                    Muestrapersona mp = new Muestrapersona();

                    mp.setCedula(per.getId());
                    mp.setNombre(per.getNombre());
                    mp.setApellido(per.getApellido());
                    mp.setPassword(per.getPassword());
                    mp.setMail(per.getMail());
                    mp.setMuestraId(m);

                    muestrapersonaFacade.create(mp);

                    Muestraadministrativo mad = new Muestraadministrativo();
                    mad.setCargo(ad.getCargo());
                    mad.setMuestrapersonaId(mp);

                    muestraadministrativoFacade.create(mad);
                }
            }

        }

        //********************************EMpleador
        aux = empleadorFacade.countByProperty("programaId", programa);

        N = aux;

        if (N != 0.0) {
            n = (N * p * q * (z * z)) / ((N - 1) * (e * e) + p * q * (z * z));

            tamanioMuestra = (int) Math.floor(n);
            //no se está usando
            List<Empleador> lem = empleadorFacade.generarMuestraX(programa, tamanioMuestra);

            it = lem.iterator();

            if (!lem.isEmpty()) {
                while (it.hasNext()) {
                    Empleador em = (Empleador) it.next();
                    Persona per = em.getPersonaId();

                    Muestrapersona mp = new Muestrapersona();

                    mp.setCedula(per.getId());
                    mp.setNombre(per.getNombre());
                    mp.setApellido(per.getApellido());
                    mp.setPassword(per.getPassword());
                    mp.setMail(per.getMail());
                    mp.setMuestraId(m);

                    muestrapersonaFacade.create(mp);

                    Muestraempleador mem = new Muestraempleador();
                    mem.setEmpresa(em.getEmpresa());
                    mem.setCargo(em.getCargo());
                    mem.setMuestrapersonaId(mp);

                    muestraempleadorFacade.create(mem);
                }
            }
        }

        //********************************Agencia
        aux = agenciagubernamentalFacade.count();

        N = aux;

        if (N != 0.0) {
            n = (N * p * q * (z * z)) / ((N - 1) * (e * e) + p * q * (z * z));

            tamanioMuestra = (int) Math.floor(n);

            List<Agenciagubernamental> lag = agenciagubernamentalFacade.generarMuestraSinPrograma(tamanioMuestra);

            it = lag.iterator();

            if (!lag.isEmpty()) {
                while (it.hasNext()) {
                    Agenciagubernamental ag = (Agenciagubernamental) it.next();
                    Persona per = ag.getPersonaId();

                    Muestrapersona mp = new Muestrapersona();

                    mp.setCedula(per.getId());
                    mp.setNombre(per.getNombre());
                    mp.setApellido(per.getApellido());
                    mp.setPassword(per.getPassword());
                    mp.setMail(per.getMail());
                    mp.setMuestraId(m);

                    muestrapersonaFacade.create(mp);

                    Muestraagencia mag = new Muestraagencia();
                    mag.setDescripcion(ag.getDescripcion());
                    mag.setMuestrapersonaId(mp);

                    muestraagenciaFacade.create(mag);
                }
            }
        }
        return "NA";
    }

    private MuestraFacade lookupMuestraFacadeBean() {
        try {
            Context c = new InitialContext();
            return (MuestraFacade) c.lookup("java:global/autoevaluacion/MuestraFacade!com.autoeval.ejb.MuestraFacade");
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

    private MuestrapersonaFacade lookupMuestrapersonaFacadeBean() {
        try {
            Context c = new InitialContext();
            return (MuestrapersonaFacade) c.lookup("java:global/autoevaluacion/MuestrapersonaFacade!com.autoeval.ejb.MuestrapersonaFacade");
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

    private AgenciagubernamentalFacade lookupAgenciagubernamentalFacadeBean() {
        try {
            Context c = new InitialContext();
            return (AgenciagubernamentalFacade) c.lookup("java:global/autoevaluacion/AgenciagubernamentalFacade!com.autoeval.ejb.AgenciagubernamentalFacade");
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
