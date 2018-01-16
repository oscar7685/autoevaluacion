/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.autoeval.ejb;

import com.autoeval.entity.Participante;
import com.autoeval.entity.Proceso;
import com.autoeval.entity.Programa;
import com.autoeval.entity.Rol;
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
public class ParticipanteFacade extends AbstractFacade<Participante> {

    @PersistenceContext(unitName = "autoevalPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ParticipanteFacade() {
        super(Participante.class);
    }

    public List findByPerfil(Proceso p, Rol r) {
        Query q = em.createNamedQuery("Participante.findByPerfil");
        q.setParameter("proceso", p);
        q.setParameter("rol", r.getId());
        return q.getResultList();
    }

    public List findByPerfilyPrograma(Proceso p, Rol r, Programa programa) {
        Query q = em.createNamedQuery("Participante.findByPerfilPrograma");
        q.setParameter("proceso", p);
        q.setParameter("rol", r.getId());
        q.setParameter("programa", programa);
        return q.getResultList();
    }
}
