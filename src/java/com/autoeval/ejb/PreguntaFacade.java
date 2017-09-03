/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.autoeval.ejb;

import com.autoeval.entity.Caracteristica;
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
public class PreguntaFacade extends AbstractFacade<Pregunta> {

    @PersistenceContext(unitName = "autoevalPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PreguntaFacade() {
        super(Pregunta.class);
    }

    public List findByModelo(Modelo m) {
        Query q = em.createNamedQuery("Pregunta.findByModelo");
        q.setParameter("modelo", m);
        return q.getResultList();
    }

    public List findByCaracteristica(Caracteristica c) {
        Query q = em.createNamedQuery("Pregunta.findByCaracteristica");
        q.setParameter("caracteristica", c);
        return q.getResultList();
    }
}
