/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.autoeval.ejb;

import com.autoeval.entity.Empleador;
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
public class EmpleadorFacade extends AbstractFacade<Empleador> {
    @PersistenceContext(unitName = "autoevalPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EmpleadorFacade() {
        super(Empleador.class);
    }

    public List findByPrograma(Programa p) {
        Query q = em.createNamedQuery("Empleador.findByPrograma");
        q.setParameter("programa", p);
        return q.getResultList();
    }
}
