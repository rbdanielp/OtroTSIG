package uy.tsig.grupo12.entidades.POI;

import java.io.Serializable;

import javax.persistence.Entity;

@Entity(name = "Estadio")
public class Estadio extends POI implements Serializable {

    public Estadio() {
        super();
    }

    public Estadio(String nombre) {
        super(nombre);
    }
}
