package uy.tsig.grupo12.entidades.POI;

import java.io.Serializable;

import javax.persistence.Entity;

@Entity(name = "Hotel")
public class Hotel extends POI implements Serializable {

    public Hotel() {
        super();
    }

    public Hotel(String nombre) {
        super(nombre);
    }

}
