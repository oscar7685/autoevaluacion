/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.autoeval.ejb;

import com.autoeval.entity.Evaluarcaracteristica;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author acreditacion
 */
@Stateless
public class EvaluarcaracteristicaFacade extends AbstractFacade<Evaluarcaracteristica> {
    @PersistenceContext(unitName = "autoevalPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EvaluarcaracteristicaFacade() {
        super(Evaluarcaracteristica.class);
    }
    
}
