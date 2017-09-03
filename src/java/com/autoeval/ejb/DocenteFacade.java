/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.autoeval.ejb;

import com.autoeval.entity.Docente;
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
public class DocenteFacade extends AbstractFacade<Docente> {
    @PersistenceContext(unitName = "autoevalPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DocenteFacade() {
        super(Docente.class);
    }
    public List findByPrograma(Programa p) {
        Query q = em.createNamedQuery("Docente.findByPrograma");
        q.setParameter("programa", p);
        return q.getResultList();
    }
}
