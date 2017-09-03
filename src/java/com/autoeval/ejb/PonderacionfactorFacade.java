/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.autoeval.ejb;

import com.autoeval.entity.Factor;
import com.autoeval.entity.Ponderacionfactor;
import com.autoeval.entity.Proceso;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Ususario
 */
@Stateless
public class PonderacionfactorFacade extends AbstractFacade<Ponderacionfactor> {
    @PersistenceContext(unitName = "autoevalPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PonderacionfactorFacade() {
        super(Ponderacionfactor.class);
    }
    public Ponderacionfactor findByFactorYProceso(Factor f, Proceso p) {
        Query q = em.createNamedQuery("Ponderacionfactor.findByFactorYProceso");
        q.setParameter("factor", f);
        q.setParameter("proceso", p);
        return (Ponderacionfactor) q.getSingleResult();
    }
    
}
