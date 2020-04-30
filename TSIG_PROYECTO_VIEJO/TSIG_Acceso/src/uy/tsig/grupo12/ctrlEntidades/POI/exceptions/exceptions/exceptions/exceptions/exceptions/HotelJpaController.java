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
import uy.tsig.grupo12.entidades.POI.Hotel;

/**
 *
 * @author diego
 */
public class HotelJpaController implements Serializable {

    public HotelJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Hotel hotel) {
        if (hotel.getPromociones() == null) {
            hotel.setPromociones(new ArrayList<Promocion>());
        }
        if (hotel.getComentarios() == null) {
            hotel.setComentarios(new ArrayList<Comentario>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Promocion> attachedPromociones = new ArrayList<Promocion>();
            for (Promocion promocionesPromocionToAttach : hotel.getPromociones()) {
                promocionesPromocionToAttach = em.getReference(promocionesPromocionToAttach.getClass(), promocionesPromocionToAttach.getId());
                attachedPromociones.add(promocionesPromocionToAttach);
            }
            hotel.setPromociones(attachedPromociones);
            List<Comentario> attachedComentarios = new ArrayList<Comentario>();
            for (Comentario comentariosComentarioToAttach : hotel.getComentarios()) {
                comentariosComentarioToAttach = em.getReference(comentariosComentarioToAttach.getClass(), comentariosComentarioToAttach.getId());
                attachedComentarios.add(comentariosComentarioToAttach);
            }
            hotel.setComentarios(attachedComentarios);
            em.persist(hotel);
            for (Promocion promocionesPromocion : hotel.getPromociones()) {
                uy.tsig.grupo12.entidades.POI.POI oldPoiOfPromocionesPromocion = promocionesPromocion.getPoi();
                promocionesPromocion.setPoi(hotel);
                promocionesPromocion = em.merge(promocionesPromocion);
                if (oldPoiOfPromocionesPromocion != null) {
                    oldPoiOfPromocionesPromocion.getPromociones().remove(promocionesPromocion);
                    oldPoiOfPromocionesPromocion = em.merge(oldPoiOfPromocionesPromocion);
                }
            }
            for (Comentario comentariosComentario : hotel.getComentarios()) {
                uy.tsig.grupo12.entidades.POI.POI oldPoiOfComentariosComentario = comentariosComentario.getPoi();
                comentariosComentario.setPoi(hotel);
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

    public void edit(Hotel hotel) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Hotel persistentHotel = em.find(Hotel.class, hotel.getGid());
            List<Promocion> promocionesOld = persistentHotel.getPromociones();
            List<Promocion> promocionesNew = hotel.getPromociones();
            List<Comentario> comentariosOld = persistentHotel.getComentarios();
            List<Comentario> comentariosNew = hotel.getComentarios();
            List<Promocion> attachedPromocionesNew = new ArrayList<Promocion>();
            for (Promocion promocionesNewPromocionToAttach : promocionesNew) {
                promocionesNewPromocionToAttach = em.getReference(promocionesNewPromocionToAttach.getClass(), promocionesNewPromocionToAttach.getId());
                attachedPromocionesNew.add(promocionesNewPromocionToAttach);
            }
            promocionesNew = attachedPromocionesNew;
            hotel.setPromociones(promocionesNew);
            List<Comentario> attachedComentariosNew = new ArrayList<Comentario>();
            for (Comentario comentariosNewComentarioToAttach : comentariosNew) {
                comentariosNewComentarioToAttach = em.getReference(comentariosNewComentarioToAttach.getClass(), comentariosNewComentarioToAttach.getId());
                attachedComentariosNew.add(comentariosNewComentarioToAttach);
            }
            comentariosNew = attachedComentariosNew;
            hotel.setComentarios(comentariosNew);
            hotel = em.merge(hotel);
            for (Promocion promocionesOldPromocion : promocionesOld) {
                if (!promocionesNew.contains(promocionesOldPromocion)) {
                    promocionesOldPromocion.setPoi(null);
                    promocionesOldPromocion = em.merge(promocionesOldPromocion);
                }
            }
            for (Promocion promocionesNewPromocion : promocionesNew) {
                if (!promocionesOld.contains(promocionesNewPromocion)) {
                    Hotel oldPoiOfPromocionesNewPromocion = (Hotel) promocionesNewPromocion.getPoi();
                    promocionesNewPromocion.setPoi(hotel);
                    promocionesNewPromocion = em.merge(promocionesNewPromocion);
                    if (oldPoiOfPromocionesNewPromocion != null && !oldPoiOfPromocionesNewPromocion.equals(hotel)) {
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
                    Hotel oldPoiOfComentariosNewComentario = (Hotel) comentariosNewComentario.getPoi();
                    comentariosNewComentario.setPoi(hotel);
                    comentariosNewComentario = em.merge(comentariosNewComentario);
                    if (oldPoiOfComentariosNewComentario != null && !oldPoiOfComentariosNewComentario.equals(hotel)) {
                        oldPoiOfComentariosNewComentario.getComentarios().remove(comentariosNewComentario);
                        oldPoiOfComentariosNewComentario = em.merge(oldPoiOfComentariosNewComentario);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = hotel.getGid();
                if (findHotel(id) == null) {
                    throw new NonexistentEntityException("The hotel with id " + id + " no longer exists.");
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
            Hotel hotel;
            try {
                hotel = em.getReference(Hotel.class, id);
                hotel.getGid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The hotel with id " + id + " no longer exists.", enfe);
            }
            List<Promocion> promociones = hotel.getPromociones();
            for (Promocion promocionesPromocion : promociones) {
                promocionesPromocion.setPoi(null);
                promocionesPromocion = em.merge(promocionesPromocion);
            }
            List<Comentario> comentarios = hotel.getComentarios();
            for (Comentario comentariosComentario : comentarios) {
                comentariosComentario.setPoi(null);
                comentariosComentario = em.merge(comentariosComentario);
            }
            em.remove(hotel);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Hotel> findHotelEntities() {
        return findHotelEntities(true, -1, -1);
    }

    public List<Hotel> findHotelEntities(int maxResults, int firstResult) {
        return findHotelEntities(false, maxResults, firstResult);
    }

    private List<Hotel> findHotelEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Hotel.class));
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

    public Hotel findHotel(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Hotel.class, id);
        } finally {
            em.close();
        }
    }

    public int getHotelCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Hotel> rt = cq.from(Hotel.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
