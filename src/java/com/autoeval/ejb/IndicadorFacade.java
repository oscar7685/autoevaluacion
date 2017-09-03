/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.autoeval.ejb;

import com.autoeval.entity.Caracteristica;
import com.autoeval.entity.Indicador;
import com.autoeval.entity.Modelo;
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
public class IndicadorFacade extends AbstractFacade<Indicador> {
    @PersistenceContext(unitName = "autoevalPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public IndicadorFacade() {
        super(Indicador.class);
    }
    public List findByModelo(Modelo m) {
        Query q = em.createNamedQuery("Indicador.findByModelo");
        q.setParameter("modelo", m);
        return q.getResultList();
    }
    public List findByModeloYenOrden(Modelo m) {
        Query q = em.createNamedQuery("Indicador.findByModeloYenOrden");
        q.setParameter("modelo", m);
        return q.getResultList();
    }
    
        public List findByCaracteristica(Caracteristica c) {
        Query q = em.createNamedQuery("Indicador.findByCaracteristica");
        q.setParameter("caracteristica", c);
        return q.getResultList();
}
        public Indicador findByUltimo() {
        Query q = em.createNamedQuery("Indicador.findByUltimo");
        q.setMaxResults(1);
        return (Indicador)q.getSingleResult() ;
    }
    
}
