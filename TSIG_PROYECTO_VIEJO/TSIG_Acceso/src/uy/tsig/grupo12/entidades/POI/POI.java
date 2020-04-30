package uy.tsig.grupo12.entidades.POI;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity(name = "pois")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class POI implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int gid;

    @Column(unique = true, nullable = true)
    private String nombre;

    @Column(nullable = true)
    private double puntaje;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "poi")
    private List<Promocion> promociones;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "poi")
    private List<Comentario> comentarios;


    public POI() {
        this.nombre = "";
        this.puntaje = 0;
        this.promociones = new ArrayList<>();
        this.comentarios = new ArrayList<>();
    }
    

    public POI(String nombre) {
        this();
        this.nombre = nombre;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    public int getGid() {
        return gid;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Promocion> getPromociones() {
        return promociones;
    }

    public void setPromociones(List<Promocion> promociones) {
        this.promociones = promociones;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public double getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(double puntaje) {
        this.puntaje = puntaje;
    }

}
