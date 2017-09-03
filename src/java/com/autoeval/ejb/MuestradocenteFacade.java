/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.autoeval.ejb;

import com.autoeval.entity.Muestradocente;
import com.autoeval.entity.Muestrapersona;
import com.autoeval.entity.Proceso;
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
public class MuestradocenteFacade extends AbstractFacade<Muestradocente> {

    @PersistenceContext(unitName = "autoevalPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MuestradocenteFacade() {
        super(Muestradocente.class);
    }

    public List findByMuestraPersona(Muestrapersona p) {
        Query q = em.createNamedQuery("Muestradocente.findByMuestraPersonaId");
        q.setParameter("muestrapersona", p);
        return q.getResultList();
    }
}
