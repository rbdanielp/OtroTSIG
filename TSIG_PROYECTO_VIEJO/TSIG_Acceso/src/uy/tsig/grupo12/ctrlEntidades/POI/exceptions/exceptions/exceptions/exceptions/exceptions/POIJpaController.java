/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.tsig.grupo12.ctrlEntidades.POI.exceptions.exceptions.exceptions.exceptions.exceptions;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import uy.tsig.grupo12.entidades.POI.Promocion;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import uy.tsig.grupo12.ctrlEntidades.POI.exceptions.exceptions.exceptions.exceptions.exceptions.exceptions.NonexistentEntityException;
import uy.tsig.grupo12.entidades.POI.Comentario;
import uy.tsig.grupo12.entidades.POI.POI;

/**
 *
 * @author diego
 */
public class POIJpaController implements Serializable {

    public POIJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(POI POI) {
        if (POI.getPromociones() == null) {
            POI.setPromociones(new ArrayList<Promocion>());
        }
        if (POI.getComentarios() == null) {
            POI.setComentarios(new ArrayList<Comentario>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Promocion> attachedPromociones = new ArrayList<Promocion>();
            for (Promocion promocionesPromocionToAttach : POI.getPromociones()) {
                promocionesPromocionToAttach = em.getReference(promocionesPromocionToAttach.getClass(), promocionesPromocionToAttach.getId());
                attachedPromociones.add(promocionesPromocionToAttach);
            }
            POI.setPromociones(attachedPromociones);
            List<Comentario> attachedComentarios = new ArrayList<Comentario>();
            for (Comentario comentariosComentarioToAttach : POI.getComentarios()) {
                comentariosComentarioToAttach = em.getReference(comentariosComentarioToAttach.getClass(), comentariosComentarioToAttach.getId());
                attachedComentarios.add(comentariosComentarioToAttach);
            }
            POI.setComentarios(attachedComentarios);
            em.persist(POI);
            for (Promocion promocionesPromocion : POI.getPromociones()) {
                POI oldPoiOfPromocionesPromocion = promocionesPromocion.getPoi();
                promocionesPromocion.setPoi(POI);
                promocionesPromocion = em.merge(promocionesPromocion);
                if (oldPoiOfPromocionesPromocion != null) {
                    oldPoiOfPromocionesPromocion.getPromociones().remove(promocionesPromocion);
                    oldPoiOfPromocionesPromocion = em.merge(oldPoiOfPromocionesPromocion);
                }
            }
            for (Comentario comentariosComentario : POI.getComentarios()) {
                POI oldPoiOfComentariosComentario = comentariosComentario.getPoi();
                comentariosComentario.setPoi(POI);
                comentariosComentario = em.merge(comentariosComentario);
                if (oldPoiOfComentariosComentario != null) {
                    oldPoiOfComentariosComentario.getComentarios().remove(comentariosComentario);
                    oldPoiOfComentariosComentario = em.merge(oldPoiOfComentariosComentario);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(POI POI) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            POI persistentPOI = em.find(POI.class, POI.getGid());
            List<Promocion> promocionesOld = persistentPOI.getPromociones();
            List<Promocion> promocionesNew = POI.getPromociones();
            List<Comentario> comentariosOld = persistentPOI.getComentarios();
            List<Comentario> comentariosNew = POI.getComentarios();
            List<Promocion> attachedPromocionesNew = new ArrayList<Promocion>();
            for (Promocion promocionesNewPromocionToAttach : promocionesNew) {
                promocionesNewPromocionToAttach = em.getReference(promocionesNewPromocionToAttach.getClass(), promocionesNewPromocionToAttach.getId());
                attachedPromocionesNew.add(promocionesNewPromocionToAttach);
            }
            promocionesNew = attachedPromocionesNew;
            POI.setPromociones(promocionesNew);
            List<Comentario> attachedComentariosNew = new ArrayList<Comentario>();
            for (Comentario comentariosNewComentarioToAttach : comentariosNew) {
                comentariosNewComentarioToAttach = em.getReference(comentariosNewComentarioToAttach.getClass(), comentariosNewComentarioToAttach.getId());
                attachedComentariosNew.add(comentariosNewComentarioToAttach);
            }
            comentariosNew = attachedComentariosNew;
            POI.setComentarios(comentariosNew);
            POI = em.merge(POI);
            for (Promocion promocionesOldPromocion : promocionesOld) {
                if (!promocionesNew.contains(promocionesOldPromocion)) {
                    promocionesOldPromocion.setPoi(null);
                    promocionesOldPromocion = em.merge(promocionesOldPromocion);
                }
            }
            for (Promocion promocionesNewPromocion : promocionesNew) {
                if (!promocionesOld.contains(promocionesNewPromocion)) {
                    POI oldPoiOfPromocionesNewPromocion = promocionesNewPromocion.getPoi();
                    promocionesNewPromocion.setPoi(POI);
                    promocionesNewPromocion = em.merge(promocionesNewPromocion);
                    if (oldPoiOfPromocionesNewPromocion != null && !oldPoiOfPromocionesNewPromocion.equals(POI)) {
                        oldPoiOfPromocionesNewPromocion.getPromociones().remove(promocionesNewPromocion);
                        oldPoiOfPromocionesNewPromocion = em.merge(oldPoiOfPromocionesNewPromocion);
                    }
                }
            }
            for (Comentario comentariosOldComentario : comentariosOld) {
                if (!comentariosNew.contains(comentariosOldComentario)) {
                    comentariosOldComentario.setPoi(null);
                    comentariosOldComentario = em.merge(comentariosOldComentario);
                }
            }
            for (Comentario comentariosNewComentario : comentariosNew) {
                if (!comentariosOld.contains(comentariosNewComentario)) {
                    POI oldPoiOfComentariosNewComentario = comentariosNewComentario.getPoi();
                    comentariosNewComentario.setPoi(POI);
                    comentariosNewComentario = em.merge(comentariosNewComentario);
                    if (oldPoiOfComentariosNewComentario != null && !oldPoiOfComentariosNewComentario.equals(POI)) {
                        oldPoiOfComentariosNewComentario.getComentarios().remove(comentariosNewComentario);
                        oldPoiOfComentariosNewComentario = em.merge(oldPoiOfComentariosNewComentario);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = POI.getGid();
                if (findPOI(id) == null) {
                    throw new NonexistentEntityException("The pOI with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(int id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            POI POI;
            try {
                POI = em.getReference(POI.class, id);
                POI.getGid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The POI with id " + id + " no longer exists.", enfe);
            }
            List<Promocion> promociones = POI.getPromociones();
            for (Promocion promocionesPromocion : promociones) {
                promocionesPromocion.setPoi(null);
                promocionesPromocion = em.merge(promocionesPromocion);
            }
            List<Comentario> comentarios = POI.getComentarios();
            for (Comentario comentariosComentario : comentarios) {
                comentariosComentario.setPoi(null);
                comentariosComentario = em.merge(comentariosComentario);
            }
            em.remove(POI);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<POI> findPOIEntities() {
        return findPOIEntities(true, -1, -1);
    }

    public List<POI> findPOIEntities(int maxResults, int firstResult) {
        return findPOIEntities(false, maxResults, firstResult);
    }

    private List<POI> findPOIEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(POI.class));
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

    public POI findPOI(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(POI.class, id);
        } finally {
            em.close();
        }
    }

    public int getPOICount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<POI> rt = cq.from(POI.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
