/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.autoeval.ejb;

import com.autoeval.entity.Proceso;
import com.autoeval.entity.Programa;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.apache.log4j.Logger;

/**
 *
 * @author Ususario
 */
public abstract class AbstractFacade<T> {

    private final Logger LOGGER = Logger.getLogger(AbstractFacade.class);
    private Class<T> entityClass;

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    public void create(T entity) {
        getEntityManager().persist(entity);
    }

    public void edit(T entity) {
        getEntityManager().merge(entity);
    }

    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    public List<T> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public List<T> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    public T findBySingle(String property, Object m) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery("SELECT c FROM " + entityClass.getSimpleName() + " c WHERE c." + property + " = :name", entityClass).setParameter("name", m).getSingleResult();
    }

    public T findBySingle2(String property1, Object m1, String property2, Object m2) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        Query q = getEntityManager().createQuery("SELECT c FROM " + entityClass.getSimpleName() + " c WHERE c." + property1 + " = :name1 and c." + property2 + " = :name2", entityClass);
        q.setParameter("name1", m1);
        q.setParameter("name2", m2);
        try {
            return (T) q.getSingleResult();
        } catch (Exception e) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Excepcion en el metodo findBySingle2", e);
            }
            return null;
        }
    }

    public List<Proceso> findByListReprestanteMultiple(String property, Object m) {
        List<Programa> programas = (List<Programa>) m;
        List<Proceso> procesos = new ArrayList<Proceso>();
        for (Programa programa : programas) {
            javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
            cq.select(cq.from(entityClass));
            Query q = getEntityManager().createQuery("SELECT c FROM " + entityClass.getSimpleName() + " c WHERE c." + property + " = :name1", entityClass);
            q.setParameter("name1", programa);
            List<Proceso> procesoxprograma = q.getResultList();
            if (!procesoxprograma.isEmpty()) {
                procesos.addAll(procesoxprograma);
            }
        }
        return procesos;
    }

    public T findBySingle3(String property1, Object m1, String property2, Object m2, String property3, Object m3) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        Query q = getEntityManager().createQuery("SELECT c FROM " + entityClass.getSimpleName() + " c WHERE c." + property1 + " = :name1 and c." + property2 + " = :name2 and c." + property3 + " = :name3", entityClass);
        q.setParameter("name1", m1);
        q.setParameter("name2", m2);
        q.setParameter("name3", m3);
        try {
            return (T) q.getSingleResult();
        } catch (Exception e) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Excepcion en el metodo findBySingle3", e);
            }
            return null;
        }

    }

    public List<T> findByPersonasQueNOEstanEnlaMuestra(String muestrafuente, Object proceso) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        Query q = getEntityManager().
                createQuery("SELECT c FROM " + entityClass.getSimpleName()
                + " c WHERE c.procesoId = :proceso and c.personaId.id not IN  "
                + "(select me.muestrapersonaId.cedula from " + muestrafuente + " me "
                + "where me.muestrapersonaId.muestraId.procesoId =:proceso)", entityClass);
        q.setParameter("proceso", proceso);
        try {
            return q.getResultList();
        } catch (Exception e) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Excepcion en el metodo findBySingle3", e);
            }
            return null;
        }

    }

    public List<T> findByMuestraQueNOHaContestado(Object proceso) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        Query q = getEntityManager().
                createQuery("SELECT c FROM " + entityClass.getSimpleName()
                + " c WHERE c.muestrapersonaId.muestraId.procesoId =:proceso and c.muestrapersonaId.id not IN "
                + "(select en.muestrapersonaId.id from Encabezado en where en.procesoId =:proceso)", entityClass);

        q.setParameter("proceso", proceso);
        try {
            return q.getResultList();
        } catch (Exception e) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Excepcion en el metodo findBySingle3", e);
            }
            return null;
        }

    }

    public List<T> findByList(String property, Object m) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        Query q = getEntityManager().createQuery("SELECT c FROM " + entityClass.getSimpleName() + " c WHERE c." + property + " = :name", entityClass);
        q.setParameter("name", m);
        return q.getResultList();
    }

    public List<T> generarMuestra(Object m, int tamanio, String tipo) {
        if (tamanio != 0) {
            javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
            cq.select(cq.from(entityClass));
            Long size = getEntityManager().createQuery("SELECT count(c) FROM " + entityClass.getSimpleName() + " c WHERE c.procesoId = :name and c.tipo = :tipo", Long.class).setParameter("name", m).setParameter("tipo", tipo).getSingleResult();
            int size2 = size.intValue();
            Random random = new Random();
            List<T> lista = new ArrayList<T>();
            List<Integer> generated = new ArrayList<Integer>();
            int i = 0;
            while (i < tamanio) {
                int aux = random.nextInt(size2);
                Query q2 = getEntityManager().createQuery("SELECT c FROM " + entityClass.getSimpleName() + " c WHERE c.procesoId = :name and c.tipo = :tipo", entityClass).setParameter("name", m).setParameter("tipo", tipo);
                q2.setFirstResult(aux);
                q2.setMaxResults(1);
                if (!generated.contains(aux)) {
                    generated.add(aux);
                    lista.add((T) q2.getSingleResult());
                    i++;
                }

            }
            return lista;
        } else {

            return null;
        }

    }

    public List<T> generarMuestraX(Object m, int tamanio) {
        if (tamanio != 0) {
            javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
            cq.select(cq.from(entityClass));
            Long size = getEntityManager().createQuery("SELECT count(c) FROM " + entityClass.getSimpleName() + " c WHERE c.procesoId = :name", Long.class).setParameter("name", m).getSingleResult();
            int size2 = size.intValue();
            Random random = new Random();
            List<T> lista = new ArrayList<T>();
            List<Integer> generated = new ArrayList<Integer>();
            int i = 0;
            while (i < tamanio) {
                int aux = random.nextInt(size2);
                Query q2 = getEntityManager().createQuery("SELECT c FROM " + entityClass.getSimpleName() + " c WHERE c.procesoId = :name", entityClass).setParameter("name", m);
                q2.setFirstResult(aux);
                q2.setMaxResults(1);
                if (!generated.contains(aux)) {
                    generated.add(aux);
                    lista.add((T) q2.getSingleResult());
                    i++;
                }

            }
            return lista;
        } else {

            return null;
        }

    }

    public List<T> generarMuestraEst(Object m1, int tamanio, String property2, Object m2) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        Long size = getEntityManager().createQuery("SELECT count(c) FROM " + entityClass.getSimpleName() + " c WHERE c.programaId = :name and c." + property2 + "= :name2", Long.class).setParameter("name", m1).setParameter("name2", m2).getSingleResult();
        int size2 = size.intValue();
        Random random = new Random();
        List<T> lista = new ArrayList<T>();
        List<Integer> generated = new ArrayList<Integer>();
        int i = 0;
        while (i < tamanio) {
            int aux = random.nextInt(size2);
            Query q2 = getEntityManager().createQuery("SELECT c FROM " + entityClass.getSimpleName() + " c WHERE c.programaId = :name and c." + property2 + "= :name2", entityClass).setParameter("name", m1).setParameter("name2", m2);
            q2.setFirstResult(aux);
            q2.setMaxResults(1);
            if (!generated.contains(aux)) {
                generated.add(aux);
                lista.add((T) q2.getSingleResult());
                i++;
            }

        }
        return lista;
    }

    public List<T> generarMuestraSinPrograma(int tamanio) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        Query q = getEntityManager().createQuery("SELECT c FROM " + entityClass.getSimpleName() + " c", entityClass);
        q.setMaxResults(tamanio);
        return q.getResultList();
    }

    public List<T> findUltimo(String id) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        Query q = getEntityManager().createQuery("SELECT c FROM " + entityClass.getSimpleName() + " c  ORDER BY c." + id + " DESC", entityClass);
        q.setMaxResults(1);
        return q.getResultList();
    }

    public List<T> findLast(Object muestra) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        Query q = getEntityManager().createQuery("SELECT c FROM " + entityClass.getSimpleName() + " c WHERE c.muestraId = :name ORDER BY c.id DESC", entityClass);
        q.setMaxResults(1);
        return q.setParameter("name", muestra).getResultList();
    }

    public List<T> findByList2(String property1, Object m1, String property2, Object m2) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        Query q = getEntityManager().createQuery("SELECT c FROM " + entityClass.getSimpleName() + " c WHERE c." + property1 + " = :name1 and c." + property2 + " = :name2", entityClass);
        q.setParameter("name1", m1);
        q.setParameter("name2", m2);
        return q.getResultList();
    }

    public List<T> findByProcesoPreguntaFuente(Object proceso, Object pregunta, Object fuente) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        Query q = getEntityManager().createQuery("SELECT c FROM " + entityClass.getSimpleName() + " c WHERE c.encabezadoId.procesoId =:proc and c.preguntaId = :preg and c.encabezadoId.fuenteId =:fuen", entityClass);
        q.setParameter("proc", proceso);
        q.setParameter("preg", pregunta);
        q.setParameter("fuen", fuente);
        return q.getResultList();
    }

    public List<T> findPreguntasCerrarDesdeResultado(Object m1) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        Query q = getEntityManager().createQuery("SELECT c FROM " + entityClass.getSimpleName() + " c  JOIN c.encabezadoId c2 "
                + "JOIN c.preguntaId c3 WHERE c2.estado = 'terminado' and c2.procesoId=:name1 and c3.tipo='2' ORDER BY c3.id", entityClass);
        q.setParameter("name1", m1);
        System.out.println("query: " + q.toString());
        return q.getResultList();
    }

    public List<T> findByList3(String property1, Object m1, String property2, Object m2, String property3, Object m3) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        Query q = getEntityManager().createQuery("SELECT c FROM " + entityClass.getSimpleName() + " c WHERE c." + property1 + " = :name1 and c." + property2 + " = :name2 and c." + property3 + " = :name3", entityClass);
        q.setParameter("name1", m1);
        q.setParameter("name2", m2);
        q.setParameter("name3", m3);
        return q.getResultList();
    }

    public int countByProperty(String property, Object m) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        Query q = getEntityManager().createQuery("SELECT COUNT(c) FROM " + entityClass.getSimpleName() + " c WHERE c." + property + " = :name", entityClass);
        q.setParameter("name", m);
        return ((Long) q.getSingleResult()).intValue();
    }

    public int countByProperty2(String property, Object m1, String property2, Object m2) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        Query q = getEntityManager().createQuery("SELECT COUNT(c) FROM " + entityClass.getSimpleName() + " c WHERE c." + property + " = :name1 and c." + property2 + " = :name2", entityClass);
        q.setParameter("name1", m1);
        q.setParameter("name2", m2);
        return ((Long) q.getSingleResult()).intValue();
    }

    public List<T> findByDistinct(String property, Object m) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery("SELECT c FROM " + entityClass.getSimpleName() + " c WHERE c." + property + " <> :name", entityClass).setParameter("name", m).getResultList();
    }

    public List findByMuestraConEncabezado(Proceso p) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery("SELECT m FROM " + entityClass.getSimpleName() + " m join m.muestrapersonaId mp join mp.encabezadoList en WHERE en.muestrapersonaId = mp and en.procesoId=:proceso", entityClass).setParameter("proceso", p).getResultList();
    }

    public List findByMuestraSinEncabezado(Proceso p) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery("SELECT m2 FROM " + entityClass.getSimpleName() + " m2 where m2.id NOT IN (SELECT m.id FROM " + entityClass.getSimpleName() + " m join m.muestrapersonaId mp join mp.encabezadoList en WHERE en.muestrapersonaId = mp and en.procesoId=:proceso) and m2.muestrapersonaId.muestraId.procesoId =:proceso", entityClass).setParameter("proceso", p).getResultList();
    }
}
