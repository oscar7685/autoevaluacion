/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.autoeval.ejb;

import com.autoeval.entity.Muestraadministrativo;
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
public class MuestraadministrativoFacade extends AbstractFacade<Muestraadministrativo> {
    @PersistenceContext(unitName = "autoevalPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MuestraadministrativoFacade() {
        super(Muestraadministrativo.class);
    }
    public List findByMuestraPersona(Muestrapersona p) {
        Query q = em.createNamedQuery("Muestraadministrativo.findByMuestraPersonaId");
        q.setParameter("muestrapersona", p);
        return q.getResultList();
    }
}
