/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.autoeval.ejb;

import com.autoeval.entity.Muestradirector;
import com.autoeval.entity.Muestrapersona;
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
public class MuestradirectorFacade extends AbstractFacade<Muestradirector> {
    @PersistenceContext(unitName = "autoevalPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MuestradirectorFacade() {
        super(Muestradirector.class);
    }
    public List findByMuestraPersona(Muestrapersona p) {
        Query q = em.createNamedQuery("Muestradirector.findByMuestraPersonaId");
        q.setParameter("muestrapersona", p);
        return q.getResultList();
    }
}
