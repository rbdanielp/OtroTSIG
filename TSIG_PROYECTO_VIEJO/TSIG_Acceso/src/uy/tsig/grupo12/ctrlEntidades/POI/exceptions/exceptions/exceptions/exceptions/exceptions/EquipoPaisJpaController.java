/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.tsig.grupo12.ctrlEntidades.POI.exceptions.exceptions.exceptions.exceptions.exceptions;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import uy.tsig.grupo12.ctrlEntidades.POI.exceptions.exceptions.exceptions.exceptions.exceptions.exceptions.NonexistentEntityException;
import uy.tsig.grupo12.ctrlEntidades.POI.exceptions.exceptions.exceptions.exceptions.exceptions.exceptions.PreexistingEntityException;
import uy.tsig.grupo12.entidades.Partidos.EquipoPais;

/**
 *
 * @author diego
 */
public class EquipoPaisJpaController implements Serializable {

    public EquipoPaisJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(EquipoPais equipoPais) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(equipoPais);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEquipoPais(equipoPais.getNombrePais()) != null) {
                throw new PreexistingEntityException("EquipoPais " + equipoPais + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(EquipoPais equipoPais) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            equipoPais = em.merge(equipoPais);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = equipoPais.getNombrePais();
                if (findEquipoPais(id) == null) {
                    throw new NonexistentEntityException("The equipoPais with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            EquipoPais equipoPais;
            try {
                equipoPais = em.getReference(EquipoPais.class, id);
                equipoPais.getNombrePais();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The equipoPais with id " + id + " no longer exists.", enfe);
            }
            em.remove(equipoPais);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<EquipoPais> findEquipoPaisEntities() {
        return findEquipoPaisEntities(true, -1, -1);
    }

    public List<EquipoPais> findEquipoPaisEntities(int maxResults, int firstResult) {
        return findEquipoPaisEntities(false, maxResults, firstResult);
    }

    private List<EquipoPais> findEquipoPaisEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(EquipoPais.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public EquipoPais findEquipoPais(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(EquipoPais.class, id);
        } finally {
            em.close();
        }
    }

    public int getEquipoPaisCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<EquipoPais> rt = cq.from(EquipoPais.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
