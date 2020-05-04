package uy.tsig.grupo16.util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
	private static final String PERSISTENCE_UNIT_NAME = "persistencia";
	private static EntityManagerFactory factory;

	//Obtinen conexion a la BD
	public static EntityManagerFactory getEntityManagerFactory() {
		if (factory==null) {
			factory=Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		}
		return factory;				
	}
	
	//Cierra conexion a la BD
	public static void shutdown() {
		if (factory!=null) {
			factory.close();
		}		
	}
}
