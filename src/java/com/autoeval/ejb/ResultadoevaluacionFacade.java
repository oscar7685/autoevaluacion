/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.autoeval.ejb;

import com.autoeval.entity.Encabezado;
import com.autoeval.entity.Encuesta;
import com.autoeval.entity.Pregunta;
import com.autoeval.entity.Proceso;
import com.autoeval.entity.Resultadoevaluacion;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Ususario
 */
@Stateless
public class ResultadoevaluacionFacade extends AbstractFacade<Resultadoevaluacion> {

    @PersistenceContext(unitName = "autoevalPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ResultadoevaluacionFacade() {
        super(Resultadoevaluacion.class);
    }

    public List findByEncabezado(Encabezado e) {
        Query q = em.createNamedQuery("Resultadoevaluacion.findByEncabezado");
        q.setParameter("encabezado", e);
        return q.getResultList();
    }

    public List findResultadosxPreguntaxEncuestaxProceso(Proceso p, Encuesta e, Pregunta pre) {
        Query q = em.createNamedQuery("Resultadoevaluacion.findResultadosxPreguntaxEncuestaxProceso");
        q.setParameter("pregunta", pre);
        q.setParameter("encuesta", e);
        q.setParameter("proceso", p);
        return q.getResultList();
    }

    public List findResultadosxPreguntaxEncuestaxProcesoxValor(Proceso p, Encuesta e, Pregunta pre, String valor) {
        Query q = em.createNamedQuery("Resultadoevaluacion.findResultadosxPreguntaxEncuestaxProcesoxValor");
        q.setParameter("pregunta", pre);
        q.setParameter("encuesta", e);
        q.setParameter("proceso", p);
        q.setParameter("valor", valor);
        return q.getResultList();
    }

    public List findResultadosxPreguntaxEncuestaxProcesoxValor1(Proceso p, Pregunta pre) {
        Query q = em.createNamedQuery("Resultadoevaluacion.findResultadosxPreguntaxEncuestaxProcesoxValor1");
        q.setParameter("pregunta", pre);
        q.setParameter("proceso", p);
        return q.getResultList();
    }
    public List findResultadosxPreguntaxEncuestaxProcesoxValor2(Proceso p, Pregunta pre, Encuesta e) {
        Query q = em.createNamedQuery("Resultadoevaluacion.findResultadosxPreguntaxEncuestaxProcesoxValor2");
        q.setParameter("pregunta", pre);
        q.setParameter("proceso", p);
        q.setParameter("encuesta", e);
        return q.getResultList();
    }
    
}
