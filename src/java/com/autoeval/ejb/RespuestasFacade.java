/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.autoeval.ejb;

import com.autoeval.entity.Caracteristica;
import com.autoeval.entity.Encuesta;
import com.autoeval.entity.Factor;
import com.autoeval.entity.Pregunta;
import com.autoeval.entity.Programa;
import com.autoeval.entity.Respuestas;
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
public class RespuestasFacade extends AbstractFacade<Respuestas> {

    @PersistenceContext(unitName = "autoevalPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public List findByPreguntaRol(Pregunta p, Rol r) {
        Query q = em.createNamedQuery("Respuestas.findByPreguntaRol");
        q.setParameter("idPregunta", p);
        q.setParameter("idRol", r);
        return q.getResultList();
    }

    public List findByEncuesta(Encuesta e) {
        Query q = em.createNamedQuery("Respuestas.findByEncuesta");
        q.setParameter("idEncuesta", e);
        return q.getResultList();
    }

    public List findByFactor(Factor f) {
        Query q = em.createNamedQuery("Respuestas.findByFactor");
        q.setParameter("idFactor", f);
        return q.getResultList();
    }

    public List findByFactor2(Factor f) {
        Query q = em.createNativeQuery("select respuestas.respuesta from respuestas\n"
                + "inner join pregunta on pregunta.id = respuestas.pregunta_id\n"
                + "left join pregunta as padre on padre.id = pregunta.pregunta_padre\n"
                + "inner join pregunta_has_caracteristica on (pregunta_has_caracteristica.pregunta_id =  pregunta.id  or pregunta_has_caracteristica.pregunta_id  = padre.id)\n"
                + "inner join caracteristica on caracteristica.id = pregunta_has_caracteristica.caracteristica_id\n"
                + "where caracteristica.factor_id = '" + f.getId() + "'");
        //q.setParameter("idFactor", f);
        return q.getResultList();
    }

    public List findByCaracteristica2(Caracteristica c) {
        Query q = em.createNativeQuery("select respuestas.respuesta from respuestas\n"
                + "inner join pregunta on pregunta.id = respuestas.pregunta_id\n"
                + "left join pregunta as padre on padre.id = pregunta.pregunta_padre\n"
                + "inner join pregunta_has_caracteristica on (pregunta_has_caracteristica.pregunta_id =  pregunta.id  or pregunta_has_caracteristica.pregunta_id  = padre.id)\n"
                + "inner join caracteristica on caracteristica.id = pregunta_has_caracteristica.caracteristica_id\n"
                + "where caracteristica.id = '" + c.getId() + "'");
        //q.setParameter("idFactor", f);
        return q.getResultList();
    }

    public List findByPreguntayEncuesta(int pregunta, int encuesta, int proceso) {
        Query q = em.createNativeQuery("select sum(case when resultadoevaluacion.respuesta = '4' then '1' when resultadoevaluacion.respuesta = '5' then '1' else '0' end)  as dma,\n"
                + "count(*) as total, \n"
                + "sum(case when resultadoevaluacion.respuesta = '0' then '1' else '0' end)  as ceros \n"
                + "from resultadoevaluacion\n"
                + "inner join encabezado on encabezado.id = resultadoevaluacion.encabezado_id\n"
                + "where resultadoevaluacion.pregunta_id = '" + pregunta + "' and encabezado.encuesta_id = '" + encuesta + "' and encabezado.proceso_id = '" + proceso + "'");
        return q.getResultList();
    }

    public List findByEncuestaYproceso(int encuesta, int proceso) {
        Query q = em.createNativeQuery("select sum(case when resultadoevaluacion.respuesta = '4' then '1' when resultadoevaluacion.respuesta = '5' then '1' else '0' end)  as dma, \n"
                + "count(*) as total,\n"
                + "sum(case when resultadoevaluacion.respuesta = '0' then '1' else '0' end)  as ceros \n"
                + "from resultadoevaluacion \n"
                + "inner join encabezado on encabezado.id = resultadoevaluacion.encabezado_id\n"
                + "where encabezado.encuesta_id = '" + encuesta + "' and encabezado.proceso_id ='" + proceso + "'");
        return q.getResultList();
    }

    public List findByPregunta(int pregunta, int proceso) {
        Query q = em.createNativeQuery("select sum(case when resultadoevaluacion.respuesta = '4' then '1' when resultadoevaluacion.respuesta = '5' then '1' else '0' end)  as dma,\n"
                + "count(*) as total, \n"
                + "sum(case when resultadoevaluacion.respuesta = '0' then '1' else '0' end)  as ceros \n"
                + "from resultadoevaluacion\n"
                + "inner join encabezado on encabezado.id = resultadoevaluacion.encabezado_id\n"
                + "where resultadoevaluacion.pregunta_id = '" + pregunta + "' and encabezado.proceso_id = '" + proceso + "'");
        return q.getResultList();
    }

    public List findByCaracteristicayEncuesta(int caracteristica, int encuesta, int proceso) {
        Query q = em.createNativeQuery("select sum(case when resultadoevaluacion.respuesta = '4' then '1' when resultadoevaluacion.respuesta = '5' then '1' else '0' end)  as dma,\n"
                + "count(*) as total,\n"
                + "sum(case when resultadoevaluacion.respuesta = '0' then '1' else '0' end)  as ceros \n"
                + "from resultadoevaluacion   \n"
                + "inner join encabezado on encabezado.id = resultadoevaluacion.encabezado_id\n"
                + "inner join pregunta on pregunta.id = resultadoevaluacion.pregunta_id   \n"
                + "left join pregunta as padre on padre.id = pregunta.pregunta_padre   \n"
                + "inner join pregunta_has_caracteristica on (pregunta_has_caracteristica.pregunta_id = pregunta.id or pregunta_has_caracteristica.pregunta_id = padre.id) \n"
                + "inner join caracteristica on caracteristica.id = pregunta_has_caracteristica.caracteristica_id  \n"
                + "where caracteristica.id = '" + caracteristica + "' and encabezado.encuesta_id = '" + encuesta + "' and encabezado.proceso_id = '" + proceso + "' ");
        return q.getResultList();
    }
    public List findByCaracteristicayFuente(int caracteristica, int fuente, int proceso) {
        Query q = em.createNativeQuery("select sum(case when resultadoevaluacion.respuesta = '4' then '1' when resultadoevaluacion.respuesta = '5' then '1' else '0' end)  as dma,\n"
                + "count(*) as total,\n"
                + "sum(case when resultadoevaluacion.respuesta = '0' then '1' else '0' end)  as ceros \n"
                + "from resultadoevaluacion   \n"
                + "inner join encabezado on encabezado.id = resultadoevaluacion.encabezado_id\n"
                + "inner join pregunta on pregunta.id = resultadoevaluacion.pregunta_id   \n"
                + "left join pregunta as padre on padre.id = pregunta.pregunta_padre   \n"
                + "inner join pregunta_has_caracteristica on (pregunta_has_caracteristica.pregunta_id = pregunta.id or pregunta_has_caracteristica.pregunta_id = padre.id) \n"
                + "inner join caracteristica on caracteristica.id = pregunta_has_caracteristica.caracteristica_id  \n"
                + "where caracteristica.id = '" + caracteristica + "' and encabezado.fuente_id = '" + fuente + "' and encabezado.proceso_id = '" + proceso + "' ");
        return q.getResultList();
    }

    public List findByFactoryEncuesta(int factor, int encuesta, int proceso) {
        Query q = em.createNativeQuery("select sum(case when resultadoevaluacion.respuesta = '4' then '1' when resultadoevaluacion.respuesta = '5' then '1' else '0' end)  as dma, \n"
                + " count(*) as total,  \n"
                + " sum(case when resultadoevaluacion.respuesta = '0' then '1' else '0' end)  as ceros  \n"
                + " from resultadoevaluacion      \n"
                + " inner join encabezado on encabezado.id = resultadoevaluacion.encabezado_id \n"
                + " inner join pregunta on pregunta.id = resultadoevaluacion.pregunta_id      \n"
                + " left join pregunta as padre on padre.id = pregunta.pregunta_padre      \n"
                + " inner join pregunta_has_caracteristica on (pregunta_has_caracteristica.pregunta_id = pregunta.id or pregunta_has_caracteristica.pregunta_id = padre.id)    \n"
                + " inner join caracteristica on caracteristica.id = pregunta_has_caracteristica.caracteristica_id   \n"
                + " inner join factor on factor.id = caracteristica.factor_id  \n"
                + " where factor.id = '" + factor + "' and encabezado.encuesta_id = '" + encuesta + "' and encabezado.proceso_id = '" + proceso + "'");
        return q.getResultList();
    }
    
    public List findByFactoryFuente(int factor, int fuente, int proceso) {
        Query q = em.createNativeQuery("select sum(case when resultadoevaluacion.respuesta = '4' then '1' when resultadoevaluacion.respuesta = '5' then '1' else '0' end)  as dma, \n"
                + " count(*) as total,  \n"
                + " sum(case when resultadoevaluacion.respuesta = '0' then '1' else '0' end)  as ceros  \n"
                + " from resultadoevaluacion      \n"
                + " inner join encabezado on encabezado.id = resultadoevaluacion.encabezado_id \n"
                + " inner join pregunta on pregunta.id = resultadoevaluacion.pregunta_id      \n"
                + " left join pregunta as padre on padre.id = pregunta.pregunta_padre      \n"
                + " inner join pregunta_has_caracteristica on (pregunta_has_caracteristica.pregunta_id = pregunta.id or pregunta_has_caracteristica.pregunta_id = padre.id)    \n"
                + " inner join caracteristica on caracteristica.id = pregunta_has_caracteristica.caracteristica_id   \n"
                + " inner join factor on factor.id = caracteristica.factor_id  \n"
                + " where factor.id = '" + factor + "' and encabezado.fuente_id = '" + fuente + "' and encabezado.proceso_id = '" + proceso + "'");
        return q.getResultList();
    }
    

    public List findByPreguntaRol(Pregunta p, Rol r, Programa prog) {
        Query q = em.createNamedQuery("Respuestas.findByPreguntaRolxPrograma");
        q.setParameter("idPregunta", p);
        q.setParameter("idRol", r);
        q.setParameter("idPrograma", prog);
        return q.getResultList();
    }

    public List findByContestados() {
        Query q = em.createNativeQuery("select participante.idparticipante, rol.rol, programa.nombre from respuestas\n"
                + "inner join participante on participante.idparticipante = respuestas.participante_idparticipante\n"
                + "inner join participante_has_rol on participante_has_rol.participante_idparticipante = participante.idparticipante\n"
                + "inner join rol on rol.id = participante_has_rol.rol_id\n"
                + "INNER JOIN programa on programa.id = participante_has_rol.programa_id\n"
                + "where participante.proceso_id = '1'\n"
                + "GROUP BY participante.idparticipante, rol.rol, programa.nombre");
        return q.getResultList();
    }

    public RespuestasFacade() {
        super(Respuestas.class);
    }
}
