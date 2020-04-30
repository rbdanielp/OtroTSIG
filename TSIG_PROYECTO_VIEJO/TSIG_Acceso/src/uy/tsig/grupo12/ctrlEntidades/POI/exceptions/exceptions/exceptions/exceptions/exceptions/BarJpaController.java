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
import uy.tsig.grupo12.entidades.POI.Bar;
import uy.tsig.grupo12.entidades.POI.Comentario;

/**
 *
 * @author diego
 */
public class BarJpaController implements Serializable {

    public BarJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Bar bar) {
        if (bar.getPromociones() == null) {
            bar.setPromociones(new ArrayList<Promocion>());
        }
        if (bar.getComentarios() == null) {
            bar.setComentarios(new ArrayList<Comentario>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Promocion> attachedPromociones = new ArrayList<Promocion>();
            for (Promocion promocionesPromocionToAttach : bar.getPromociones()) {
                promocionesPromocionToAttach = em.getReference(promocionesPromocionToAttach.getClass(), promocionesPromocionToAttach.getId());
                attachedPromociones.add(promocionesPromocionToAttach);
            }
            bar.setPromociones(attachedPromociones);
            List<Comentario> attachedComentarios = new ArrayList<Comentario>();
            for (Comentario comentariosComentarioToAttach : bar.getComentarios()) {
                comentariosComentarioToAttach = em.getReference(comentariosComentarioToAttach.getClass(), comentariosComentarioToAttach.getId());
                attachedComentarios.add(comentariosComentarioToAttach);
            }
            bar.setComentarios(attachedComentarios);
            em.persist(bar);
            for (Promocion promocionesPromocion : bar.getPromociones()) {
                uy.tsig.grupo12.entidades.POI.POI oldPoiOfPromocionesPromocion = promocionesPromocion.getPoi();
                promocionesPromocion.setPoi(bar);
                promocionesPromocion = em.merge(promocionesPromocion);
                if (oldPoiOfPromocionesPromocion != null) {
                    oldPoiOfPromocionesPromocion.getPromociones().remove(promocionesPromocion);
                    oldPoiOfPromocionesPromocion = em.merge(oldPoiOfPromocionesPromocion);
                }
            }
            for (Comentario comentariosComentario : bar.getComentarios()) {
                uy.tsig.grupo12.entidades.POI.POI oldPoiOfComentariosComentario = comentariosComentario.getPoi();
                comentariosComentario.setPoi(bar);
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

    public void edit(Bar bar) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Bar persistentBar = em.find(Bar.class, bar.getGid());
            List<Promocion> promocionesOld = persistentBar.getPromociones();
            List<Promocion> promocionesNew = bar.getPromociones();
            List<Comentario> comentariosOld = persistentBar.getComentarios();
            List<Comentario> comentariosNew = bar.getComentarios();
            List<Promocion> attachedPromocionesNew = new ArrayList<Promocion>();
            for (Promocion promocionesNewPromocionToAttach : promocionesNew) {
                promocionesNewPromocionToAttach = em.getReference(promocionesNewPromocionToAttach.getClass(), promocionesNewPromocionToAttach.getId());
                attachedPromocionesNew.add(promocionesNewPromocionToAttach);
            }
            promocionesNew = attachedPromocionesNew;
            bar.setPromociones(promocionesNew);
            List<Comentario> attachedComentariosNew = new ArrayList<Comentario>();
            for (Comentario comentariosNewComentarioToAttach : comentariosNew) {
                comentariosNewComentarioToAttach = em.getReference(comentariosNewComentarioToAttach.getClass(), comentariosNewComentarioToAttach.getId());
                attachedComentariosNew.add(comentariosNewComentarioToAttach);
            }
            comentariosNew = attachedComentariosNew;
            bar.setComentarios(comentariosNew);
            bar = em.merge(bar);
            for (Promocion promocionesOldPromocion : promocionesOld) {
                if (!promocionesNew.contains(promocionesOldPromocion)) {
                    promocionesOldPromocion.setPoi(null);
                    promocionesOldPromocion = em.merge(promocionesOldPromocion);
                }
            }
            for (Promocion promocionesNewPromocion : promocionesNew) {
                if (!promocionesOld.contains(promocionesNewPromocion)) {
                    Bar oldPoiOfPromocionesNewPromocion = (Bar) promocionesNewPromocion.getPoi();
                    promocionesNewPromocion.setPoi(bar);
                    promocionesNewPromocion = em.merge(promocionesNewPromocion);
                    if (oldPoiOfPromocionesNewPromocion != null && !oldPoiOfPromocionesNewPromocion.equals(bar)) {
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
                    Bar oldPoiOfComentariosNewComentario = (Bar) comentariosNewComentario.getPoi();
                    comentariosNewComentario.setPoi(bar);
                    comentariosNewComentario = em.merge(comentariosNewComentario);
                    if (oldPoiOfComentariosNewComentario != null && !oldPoiOfComentariosNewComentario.equals(bar)) {
                        oldPoiOfComentariosNewComentario.getComentarios().remove(comentariosNewComentario);
                        oldPoiOfComentariosNewComentario = em.merge(oldPoiOfComentariosNewComentario);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = bar.getGid();
                if (findBar(id) == null) {
                    throw new NonexistentEntityException("The bar with id " + id + " no longer exists.");
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
            Bar bar;
            try {
                bar = em.getReference(Bar.class, id);
                bar.getGid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The bar with id " + id + " no longer exists.", enfe);
            }
            List<Promocion> promociones = bar.getPromociones();
            for (Promocion promocionesPromocion : promociones) {
                promocionesPromocion.setPoi(null);
                promocionesPromocion = em.merge(promocionesPromocion);
            }
            List<Comentario> comentarios = bar.getComentarios();
            for (Comentario comentariosComentario : comentarios) {
                comentariosComentario.setPoi(null);
                comentariosComentario = em.merge(comentariosComentario);
            }
            em.remove(bar);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Bar> findBarEntities() {
        return findBarEntities(true, -1, -1);
    }

    public List<Bar> findBarEntities(int maxResults, int firstResult) {
        return findBarEntities(false, maxResults, firstResult);
    }

    private List<Bar> findBarEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Bar.class));
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

    public Bar findBar(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Bar.class, id);
        } finally {
            em.close();
        }
    }

    public int getBarCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Bar> rt = cq.from(Bar.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
