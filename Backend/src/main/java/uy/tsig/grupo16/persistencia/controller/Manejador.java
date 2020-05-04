package uy.tsig.grupo16.persistencia.controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Manejador {

    private String PERSISTENCE_UNIT_NAME = "persistencia";
    
    private EntityManager em;

    private static Manejador _instancia;

    private Manejador() {
        EntityManagerFactory emFactoryObj = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        em = emFactoryObj.createEntityManager();

    }

    public static EntityManager getEntityManager() {
        if (_instancia == null) {
            _instancia = new Manejador();
        }

        return _instancia.em;
    }
    
}
