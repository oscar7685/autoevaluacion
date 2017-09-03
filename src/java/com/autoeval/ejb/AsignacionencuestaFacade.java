/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.autoeval.ejb;

import com.autoeval.entity.Asignacionencuesta;
import com.autoeval.entity.Encuesta;
import com.autoeval.entity.Fuente;
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
public class AsignacionencuestaFacade extends AbstractFacade<Asignacionencuesta> {

    @PersistenceContext(unitName = "autoevalPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AsignacionencuestaFacade() {
        super(Asignacionencuesta.class);
    }
    
    public List findByEncuesta(Encuesta e) {
        Query q = em.createNamedQuery("Asignacionencuesta.findByEncuesta");
        q.setParameter("encuesta", e);
        return q.getResultList();
}
    public List findByEncuestayFuenteyModelo(Encuesta e, Fuente f, Modelo m) {
        Query q = em.createNamedQuery("Asignacionencuesta.findByEncuestayFuenteyModelo");
        q.setParameter("encuesta", e);
        q.setParameter("fuente", f);
        q.setParameter("modelo", m);
        return q.getResultList();
    }
}
