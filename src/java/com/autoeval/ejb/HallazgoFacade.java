/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.autoeval.ejb;

import com.autoeval.entity.Hallazgo;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Ususario
 */
@Stateless
public class HallazgoFacade extends AbstractFacade<Hallazgo> {
    @PersistenceContext(unitName = "autoevalPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public HallazgoFacade() {
        super(Hallazgo.class);
    }
    
}
