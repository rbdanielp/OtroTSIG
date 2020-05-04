package uy.tsig.grupo16.persistencia.interfaces;

public interface IPersistencia {

    Object buscar(Class<?> tipo, Object id);
    
    Object buscarNom(Class<?> tipo, Object nombre);
    
	void alta(Object o);
}
