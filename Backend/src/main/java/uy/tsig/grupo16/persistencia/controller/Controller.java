package uy.tsig.grupo16.persistencia.controller;

import uy.tsig.grupo16.persistencia.interfaces.IPersistencia;

public class Controller  implements IPersistencia{


    
    
    public Object buscar(Class<?> tipo, Object id) {
        return Manejador.getEntityManager().find(tipo, id);
    }
    
	public Object buscarNom(Class<?> tipo, Object nombre) {
		return Manejador.getEntityManager().find(tipo, nombre);
	}
    
	public void alta(Object o) {
        Manejador.getEntityManager().getTransaction().begin();
        Manejador.getEntityManager().persist(o);
        Manejador.getEntityManager().getTransaction().commit();

        Manejador.getEntityManager().clear();//ver si no da problemas
		
	}




}
