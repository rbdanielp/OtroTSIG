package uy.tsig.grupo12.entidades.POI;

import java.io.Serializable;

import javax.persistence.Entity;

@Entity(name = "Bar")
public class Bar extends POI implements Serializable {

    public Bar() {
        super();
    }

    public Bar(String nombre) {
        super(nombre);
    }
}
