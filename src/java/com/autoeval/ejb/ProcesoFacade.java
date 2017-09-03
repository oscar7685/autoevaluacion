/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.autoeval.ejb;

import com.autoeval.entity.Proceso;
import com.autoeval.entity.Programa;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Ususario
 */
@Stateless
public class ProcesoFacade extends AbstractFacade<Proceso> {

    @PersistenceContext(unitName = "autoevalPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProcesoFacade() {
        super(Proceso.class);
    }
    
    public List findByPrograma(Programa p) {

        TypedQuery<Proceso> query = em.createQuery(
                "SELECT c FROM Proceso c WHERE c.programaId = :name", Proceso.class);
        return query.setParameter("name", p).getResultList();

}
}
