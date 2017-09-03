/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.autoeval.ejb;

import com.autoeval.entity.Directorprograma;
import com.autoeval.entity.Programa;
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
public class DirectorprogramaFacade extends AbstractFacade<Directorprograma> {
    @PersistenceContext(unitName = "autoevalPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DirectorprogramaFacade() {
        super(Directorprograma.class);
    }
    public List findByPrograma(Programa p) {
        Query q = em.createNamedQuery("Directorprograma.findByPrograma");
        q.setParameter("programa", p);
        return q.getResultList();
    }
}
