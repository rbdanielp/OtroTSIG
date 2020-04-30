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
import uy.tsig.grupo12.entidades.POI.PuntoTuristico;

/**
 *
 * @author diego
 */
public class PuntoTuristicoJpaController implements Serializable {

    public PuntoTuristicoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PuntoTuristico puntoTuristico) {
        if (puntoTuristico.getPromociones() == null) {
            puntoTuristico.setPromociones(new ArrayList<Promocion>());
        }
        if (puntoTuristico.getComentarios() == null) {
            puntoTuristico.setComentarios(new ArrayList<Comentario>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Promocion> attachedPromociones = new ArrayList<Promocion>();
            for (Promocion promocionesPromocionToAttach : puntoTuristico.getPromociones()) {
                promocionesPromocionToAttach = em.getReference(promocionesPromocionToAttach.getClass(), promocionesPromocionToAttach.getId());
                attachedPromociones.add(promocionesPromocionToAttach);
            }
            puntoTuristico.setPromociones(attachedPromociones);
            List<Comentario> attachedComentarios = new ArrayList<Comentario>();
            for (Comentario comentariosComentarioToAttach : puntoTuristico.getComentarios()) {
                comentariosComentarioToAttach = em.getReference(comentariosComentarioToAttach.getClass(), comentariosComentarioToAttach.getId());
                attachedComentarios.add(comentariosComentarioToAttach);
            }
            puntoTuristico.setComentarios(attachedComentarios);
            em.persist(puntoTuristico);
            for (Promocion promocionesPromocion : puntoTuristico.getPromociones()) {
                uy.tsig.grupo12.entidades.POI.POI oldPoiOfPromocionesPromocion = promocionesPromocion.getPoi();
                promocionesPromocion.setPoi(puntoTuristico);
                promocionesPromocion = em.merge(promocionesPromocion);
                if (oldPoiOfPromocionesPromocion != null) {
                    oldPoiOfPromocionesPromocion.getPromociones().remove(promocionesPromocion);
                    oldPoiOfPromocionesPromocion = em.merge(oldPoiOfPromocionesPromocion);
                }
            }
            for (Comentario comentariosComentario : puntoTuristico.getComentarios()) {
                uy.tsig.grupo12.entidades.POI.POI oldPoiOfComentariosComentario = comentariosComentario.getPoi();
                comentariosComentario.setPoi(puntoTuristico);
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

    public void edit(PuntoTuristico puntoTuristico) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PuntoTuristico persistentPuntoTuristico = em.find(PuntoTuristico.class, puntoTuristico.getGid());
            List<Promocion> promocionesOld = persistentPuntoTuristico.getPromociones();
            List<Promocion> promocionesNew = puntoTuristico.getPromociones();
            List<Comentario> comentariosOld = persistentPuntoTuristico.getComentarios();
            List<Comentario> comentariosNew = puntoTuristico.getComentarios();
            List<Promocion> attachedPromocionesNew = new ArrayList<Promocion>();
            for (Promocion promocionesNewPromocionToAttach : promocionesNew) {
                promocionesNewPromocionToAttach = em.getReference(promocionesNewPromocionToAttach.getClass(), promocionesNewPromocionToAttach.getId());
                attachedPromocionesNew.add(promocionesNewPromocionToAttach);
            }
            promocionesNew = attachedPromocionesNew;
            puntoTuristico.setPromociones(promocionesNew);
            List<Comentario> attachedComentariosNew = new ArrayList<Comentario>();
            for (Comentario comentariosNewComentarioToAttach : comentariosNew) {
                comentariosNewComentarioToAttach = em.getReference(comentariosNewComentarioToAttach.getClass(), comentariosNewComentarioToAttach.getId());
                attachedComentariosNew.add(comentariosNewComentarioToAttach);
            }
            comentariosNew = attachedComentariosNew;
            puntoTuristico.setComentarios(comentariosNew);
            puntoTuristico = em.merge(puntoTuristico);
            for (Promocion promocionesOldPromocion : promocionesOld) {
                if (!promocionesNew.contains(promocionesOldPromocion)) {
                    promocionesOldPromocion.setPoi(null);
                    promocionesOldPromocion = em.merge(promocionesOldPromocion);
                }
            }
            for (Promocion promocionesNewPromocion : promocionesNew) {
                if (!promocionesOld.contains(promocionesNewPromocion)) {
                    PuntoTuristico oldPoiOfPromocionesNewPromocion = (PuntoTuristico) promocionesNewPromocion.getPoi();
                    promocionesNewPromocion.setPoi(puntoTuristico);
                    promocionesNewPromocion = em.merge(promocionesNewPromocion);
                    if (oldPoiOfPromocionesNewPromocion != null && !oldPoiOfPromocionesNewPromocion.equals(puntoTuristico)) {
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
                    PuntoTuristico oldPoiOfComentariosNewComentario = (PuntoTuristico) comentariosNewComentario.getPoi();
                    comentariosNewComentario.setPoi(puntoTuristico);
                    comentariosNewComentario = em.merge(comentariosNewComentario);
                    if (oldPoiOfComentariosNewComentario != null && !oldPoiOfComentariosNewComentario.equals(puntoTuristico)) {
                        oldPoiOfComentariosNewComentario.getComentarios().remove(comentariosNewComentario);
                        oldPoiOfComentariosNewComentario = em.merge(oldPoiOfComentariosNewComentario);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = puntoTuristico.getGid();
                if (findPuntoTuristico(id) == null) {
                    throw new NonexistentEntityException("The puntoTuristico with id " + id + " no longer exists.");
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
            PuntoTuristico puntoTuristico;
            try {
                puntoTuristico = em.getReference(PuntoTuristico.class, id);
                puntoTuristico.getGid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The puntoTuristico with id " + id + " no longer exists.", enfe);
            }
            List<Promocion> promociones = puntoTuristico.getPromociones();
            for (Promocion promocionesPromocion : promociones) {
                promocionesPromocion.setPoi(null);
                promocionesPromocion = em.merge(promocionesPromocion);
            }
            List<Comentario> comentarios = puntoTuristico.getComentarios();
            for (Comentario comentariosComentario : comentarios) {
                comentariosComentario.setPoi(null);
                comentariosComentario = em.merge(comentariosComentario);
            }
            em.remove(puntoTuristico);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PuntoTuristico> findPuntoTuristicoEntities() {
        return findPuntoTuristicoEntities(true, -1, -1);
    }

    public List<PuntoTuristico> findPuntoTuristicoEntities(int maxResults, int firstResult) {
        return findPuntoTuristicoEntities(false, maxResults, firstResult);
    }

    private List<PuntoTuristico> findPuntoTuristicoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PuntoTuristico.class));
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

    public PuntoTuristico findPuntoTuristico(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PuntoTuristico.class, id);
        } finally {
            em.close();
        }
    }

    public int getPuntoTuristicoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PuntoTuristico> rt = cq.from(PuntoTuristico.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
