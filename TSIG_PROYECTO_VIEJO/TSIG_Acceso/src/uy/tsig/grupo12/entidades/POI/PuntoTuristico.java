package uy.tsig.grupo12.entidades.POI;

import java.io.Serializable;

import javax.persistence.Entity;

@Entity(name = "PuntoTuristico")
public class PuntoTuristico extends POI implements Serializable {

    public PuntoTuristico() {
        super();
    }

    public PuntoTuristico(String nombre) {
        super(nombre);
    }
}
