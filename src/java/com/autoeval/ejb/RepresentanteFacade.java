/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.autoeval.ejb;

import com.autoeval.entity.Representante;
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
public class RepresentanteFacade extends AbstractFacade<Representante> {

    @PersistenceContext(unitName = "autoevalPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RepresentanteFacade() {
        super(Representante.class);
    }

    public List findByRol(String rol) {
        Query q = em.createNamedQuery("Representante.findByRol");
        q.setParameter("rol", rol);
        return q.getResultList();
    }
}
