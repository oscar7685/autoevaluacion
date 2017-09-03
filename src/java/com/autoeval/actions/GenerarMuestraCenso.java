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
public class GenerarMuestraCenso implements Action {

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
        m.setTipo("Censo");
        m.setProcesoId(proceso);
        muestraFacade.create(m);

        sesion.setAttribute("Muestra", m);



        //********************************Estudiante
        // double aux = estudianteFacade.countByProperty("programaId", programa);
        //ESTUDIANTES CADETES
        List<Estudiante> le = estudianteFacade.findByList2("procesoId", proceso, "tipo", "CADETES");
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
                Muestrapersona aux = muestrapersonaFacade.findUltimo("id").get(0);

                Muestraestudiante me = new Muestraestudiante();
                me.setCodigo(est.getId());
                me.setSemestre(est.getSemestre());
                me.setPeriodo(est.getPeriodo());
                me.setAnio(est.getAnio());
                me.setCurso(est.getCurso());
                me.setMuestrapersonaId(aux);
                me.setProgramaId(programa);
                me.setTipo("" + est.getFuenteId().getId());
                me.setTp("" + est.getTipo());
                muestraestudianteFacade.create(me);
            }
        }


        //ESTUDIANTES ESPECIALIZACION
        List<Estudiante> le2 = estudianteFacade.findByList2("procesoId", proceso, "tipo", "ESPECIALIZACION");

        for (Estudiante est : le2) {
            Persona per = est.getPersonaId();
            Muestrapersona mp = new Muestrapersona();
            mp.setCedula(per.getId());
            mp.setNombre(per.getNombre());
            mp.setApellido(per.getApellido());
            mp.setPassword(per.getPassword());
            mp.setMail(per.getMail());
            mp.setMuestraId(m);
            muestrapersonaFacade.create(mp);
            Muestrapersona aux = muestrapersonaFacade.findUltimo("id").get(0);

            Muestraestudiante me = new Muestraestudiante();
            me.setCodigo(est.getId());
            me.setSemestre(est.getSemestre());
            me.setPeriodo(est.getPeriodo());
            me.setAnio(est.getAnio());
            me.setMuestrapersonaId(aux);
            me.setProgramaId(programa);
            me.setCurso(est.getCurso());
            me.setTipo("" + est.getFuenteId().getId());
            me.setTp("" + est.getTipo());
            muestraestudianteFacade.create(me);
        }

        //ESTUDIANTES MAESTRIA
        List<Estudiante> le3 = estudianteFacade.findByList2("procesoId", proceso, "tipo", "MAESTRIA");

        for (Estudiante est : le3) {
            Persona per = est.getPersonaId();
            Muestrapersona mp = new Muestrapersona();
            mp.setCedula(per.getId());
            mp.setNombre(per.getNombre());
            mp.setApellido(per.getApellido());
            mp.setPassword(per.getPassword());
            mp.setMail(per.getMail());
            mp.setMuestraId(m);
            muestrapersonaFacade.create(mp);
            Muestrapersona aux = muestrapersonaFacade.findUltimo("id").get(0);

            Muestraestudiante me = new Muestraestudiante();
            me.setCodigo(est.getId());
            me.setSemestre(est.getSemestre());
            me.setPeriodo(est.getPeriodo());
            me.setAnio(est.getAnio());
            me.setMuestrapersonaId(aux);
            me.setCurso(est.getCurso());
            me.setProgramaId(programa);
            me.setTipo("" + est.getFuenteId().getId());
            me.setTp("" + est.getTipo());
            muestraestudianteFacade.create(me);
        }


        //ESTUDIANTES OFICIALES
        List<Estudiante> le4 = estudianteFacade.findByList2("procesoId", proceso, "tipo", "OFICIALES");

        int N = le4.size();
        double n = 0;
        if (N != 0.0) {
            n = (N * 0.9604) / (((N - 1) * 0.0025) + 0.9604);

            int tamanioMuestra = (int) Math.floor(n);
            List<Estudiante> ld = estudianteFacade.generarMuestra(proceso, tamanioMuestra, "OFICIALES");
            for (Estudiante est : ld) {
                Persona per = est.getPersonaId();
                Muestrapersona mp = new Muestrapersona();
                mp.setCedula(per.getId());
                mp.setNombre(per.getNombre());
                mp.setApellido(per.getApellido());
                mp.setPassword(per.getPassword());
                mp.setMail(per.getMail());
                mp.setMuestraId(m);
                muestrapersonaFacade.create(mp);
                Muestrapersona aux = muestrapersonaFacade.findUltimo("id").get(0);
                
                Muestraestudiante me = new Muestraestudiante();
                me.setCodigo(est.getId());
                me.setSemestre(est.getSemestre());
                me.setPeriodo(est.getPeriodo());
                me.setAnio(est.getAnio());
                me.setMuestrapersonaId(aux);
                me.setProgramaId(programa);
                me.setCurso(est.getCurso());
                me.setTp("" + est.getTipo());
                me.setTipo("" + est.getFuenteId().getId());
                muestraestudianteFacade.create(me);
            }

        }








        //********************************Docente
        //DOCENTE MILITAR
        List<Docente> le5 = docenteFacade.findByList2("procesoId", proceso, "tipo", "MILITAR");
        int Nd = le5.size();
        double nd = 0;
        if (Nd != 0.0) {
            nd = (Nd * 0.9604) / (((Nd - 1) * 0.0025) + 0.9604);

            int tamanioMuestra1 = (int) Math.floor(nd);
            List<Docente> ld = docenteFacade.generarMuestra(proceso, tamanioMuestra1, "MILITAR");
            for (Docente doc : ld) {
                Persona per = doc.getPersonaId();
                Muestrapersona mp = new Muestrapersona();
                mp.setCedula(per.getId());
                mp.setNombre(per.getNombre());
                mp.setApellido(per.getApellido());
                mp.setPassword(per.getPassword());
                mp.setMail(per.getMail());
                mp.setMuestraId(m);
                muestrapersonaFacade.create(mp);
                Muestrapersona aux = muestrapersonaFacade.findUltimo("id").get(0);

                Muestradocente md = new Muestradocente();
                md.setTipo("" + doc.getFuenteId().getId());
                md.setMuestrapersonaId(aux);
                md.setTp("" + doc.getTipo());
                muestradocenteFacade.create(md);
            }
        }
        //DOCENTE NOMINA
        List<Docente> le6 = docenteFacade.findByList2("procesoId", proceso, "tipo", "NOMINA");
        int Nd2 = le6.size();
        double nd2 = 0;
        if (Nd2 != 0.0) {
            nd2 = (Nd2 * 0.9604) / (((Nd2 - 1) * 0.0025) + 0.9604);

            int tamanioMuestra2 = (int) Math.floor(nd2);
            List<Docente> ld2 = docenteFacade.generarMuestra(proceso, tamanioMuestra2, "NOMINA");
            for (Docente doc : ld2) {
                Persona per = doc.getPersonaId();
                Muestrapersona mp = new Muestrapersona();
                mp.setCedula(per.getId());
                mp.setNombre(per.getNombre());
                mp.setApellido(per.getApellido());
                mp.setPassword(per.getPassword());
                mp.setMail(per.getMail());
                mp.setMuestraId(m);
                muestrapersonaFacade.create(mp);
                Muestrapersona aux = muestrapersonaFacade.findUltimo("id").get(0);

                Muestradocente md = new Muestradocente();
                md.setTipo("" + doc.getFuenteId().getId());
                md.setTp("" + doc.getTipo());
                md.setMuestrapersonaId(aux);
                muestradocenteFacade.create(md);
            }
        }

        //DOCENTE CATEDRA
        List<Docente> leC = docenteFacade.findByList2("procesoId", proceso, "tipo", "CATEDRA");
        int NdC = leC.size();
        double ndC = 0;
        if (NdC != 0.0) {
            ndC = (NdC * 0.9604) / (((NdC - 1) * 0.0025) + 0.9604);

            int tamanioMuestraC = (int) Math.floor(ndC);
            List<Docente> ldC = docenteFacade.generarMuestra(proceso, tamanioMuestraC, "CATEDRA");
            for (Docente doc : ldC) {
                Persona per = doc.getPersonaId();
                Muestrapersona mp = new Muestrapersona();
                mp.setCedula(per.getId());
                mp.setNombre(per.getNombre());
                mp.setApellido(per.getApellido());
                mp.setPassword(per.getPassword());
                mp.setMail(per.getMail());
                mp.setMuestraId(m);
                muestrapersonaFacade.create(mp);
                Muestrapersona aux = muestrapersonaFacade.findUltimo("id").get(0);

                Muestradocente md = new Muestradocente();
                md.setTipo("" + doc.getFuenteId().getId());
                md.setMuestrapersonaId(aux);
                md.setTp("" + doc.getTipo());
                muestradocenteFacade.create(md);
            }
        }
        //DOCENTE OCASIONALES
        List<Docente> leO = docenteFacade.findByList2("procesoId", proceso, "tipo", "OCASIONALES");
        int NdO = leO.size();
        double ndO = 0;
        if (NdO != 0.0) {
            ndO = (NdO * 0.9604) / (((NdO - 1) * 0.0025) + 0.9604);

            int tamanioMuestraO = (int) Math.floor(ndO);
            List<Docente> ldO = docenteFacade.generarMuestra(proceso, tamanioMuestraO, "OCASIONALES");
            for (Docente doc : ldO) {
                Persona per = doc.getPersonaId();
                Muestrapersona mp = new Muestrapersona();
                mp.setCedula(per.getId());
                mp.setNombre(per.getNombre());
                mp.setApellido(per.getApellido());
                mp.setPassword(per.getPassword());
                mp.setMail(per.getMail());
                mp.setMuestraId(m);
                muestrapersonaFacade.create(mp);
                Muestrapersona aux = muestrapersonaFacade.findUltimo("id").get(0);

                Muestradocente md = new Muestradocente();
                md.setTipo("" + doc.getFuenteId().getId());
                md.setMuestrapersonaId(aux);
                md.setTp("" + doc.getTipo());
                muestradocenteFacade.create(md);
            }
        }
        //********************************Egresado
        List<Egresado> leg = egresadoFacade.findByList("procesoId", proceso);

        int NdEG = leg.size();
        double ndEG = 0;
        if (NdEG != 0.0) {
            ndEG = (NdEG * 0.9604) / (((NdEG - 1) * 0.0025) + 0.9604);

            int tamanioMuestraEG = (int) Math.floor(ndEG);
            List<Egresado> ldEG = egresadoFacade.generarMuestraX(proceso, tamanioMuestraEG);

            for (Egresado eg : ldEG) {
                Persona per = eg.getPersonaId();
                Muestrapersona mp = new Muestrapersona();
                mp.setCedula(per.getId());
                mp.setNombre(per.getNombre());
                mp.setApellido(per.getApellido());
                mp.setPassword(per.getPassword());
                mp.setMail(per.getMail());
                mp.setMuestraId(m);
                muestrapersonaFacade.create(mp);
                Muestrapersona aux = muestrapersonaFacade.findUltimo("id").get(0);
                
                Muestraegresado meg = new Muestraegresado();
                meg.setMuestrapersonaId(aux);
                meg.setTipo("" + eg.getFuenteId().getId());
                meg.setTp("" + eg.getTipo());
                muestraegresadoFacade.create(meg);
            }
        }

        //********************************Director
        List<Directorprograma> ldp = directorprogramaFacade.findByList("procesoId", proceso);

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
                
                Muestrapersona aux = muestrapersonaFacade.findUltimo("id").get(0);
                Muestradirector mdp = new Muestradirector();
                mdp.setMuestrapersonaId(aux);

                muestradirectorFacade.create(mdp);
            }
        }



        //********************************Administrativo

        List<Administrativo> lad = administrativoFacade.findByList("procesoId", proceso);

        int NdAd = lad.size();
        double ndAd = 0;
        if (NdAd != 0.0) {
            ndAd = (NdAd * 0.9604) / (((NdAd - 1) * 0.0025) + 0.9604);

            int tamanioMuestraAd = (int) Math.floor(ndAd);
            List<Administrativo> ldAd = administrativoFacade.generarMuestraX(proceso, tamanioMuestraAd);

            for (Administrativo ad : ldAd) {
                Persona per = ad.getPersonaId();
                Muestrapersona mp = new Muestrapersona();
                mp.setCedula(per.getId());
                mp.setNombre(per.getNombre());
                mp.setApellido(per.getApellido());
                mp.setPassword(per.getPassword());
                mp.setMail(per.getMail());
                mp.setMuestraId(m);
                muestrapersonaFacade.create(mp);
                Muestrapersona aux = muestrapersonaFacade.findUltimo("id").get(0);


                Muestraadministrativo mad = new Muestraadministrativo();
                mad.setCargo(ad.getCargo());
                mad.setTp(ad.getTipo());
                mad.setMuestrapersonaId(aux);
                muestraadministrativoFacade.create(mad);
            }

        }





        //********************************EMpleador

        List<Empleador> lem = empleadorFacade.findByList("procesoId", proceso);

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


        //********************************Agencia
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
