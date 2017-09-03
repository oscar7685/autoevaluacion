/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.autoeval.ejb;

import com.autoeval.entity.Estudiante;
import com.autoeval.entity.Programa;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Ususario
 */
@Stateless
public class EstudianteFacade extends AbstractFacade<Estudiante> {
    @PersistenceContext(unitName = "autoevalPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EstudianteFacade() {
        super(Estudiante.class);
    }
    public int cantEstudiantesEntre3y9ByPrograma(Programa p) {
        Query q = em.createNamedQuery("Estudiante.cantEstudiantesEntre3y9ByPrograma");
        q.setParameter("programa", p);
        return ((Long) q.getSingleResult()).intValue();
    }
    
}
