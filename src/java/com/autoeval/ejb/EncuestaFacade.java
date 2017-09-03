/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.autoeval.ejb;

import com.autoeval.entity.Encuesta;
import com.autoeval.entity.Modelo;
import com.autoeval.entity.Pregunta;
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
public class EncuestaFacade extends AbstractFacade<Encuesta> {

    @PersistenceContext(unitName = "autoevalPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EncuestaFacade() {
        super(Encuesta.class);
    }

    public List findByModelo(Modelo m) {
        Query q = em.createNamedQuery("Encuesta.findByModelo");
        q.setParameter("modelo", m);
        return q.getResultList();
    }
    
    public List findByPreguntaYModelo(Modelo m, Pregunta p) {
        Query q = em.createNamedQuery("Encuesta.findByPreguntaYModelo");
        q.setParameter("modelo", m);
        q.setParameter("pregunta", p);
        return q.getResultList();
    }
}
