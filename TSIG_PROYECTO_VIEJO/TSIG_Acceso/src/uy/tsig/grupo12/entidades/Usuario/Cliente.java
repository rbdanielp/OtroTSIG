package uy.tsig.grupo12.entidades.Usuario;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import uy.tsig.grupo12.entidades.POI.Hotel;
import uy.tsig.grupo12.entidades.Partidos.EquipoPais;

@Entity(name = "Cliente")
public class Cliente extends Usuario implements Serializable {

    @Column(unique = true)
    private String nombre;

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    private EquipoPais nacionalidad;

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    private Hotel se_hospeda_en;

    public Cliente() {
    }

    public Cliente(String loginUser, String loginPass, String nombre) {
        super(loginUser, loginPass);
        this.nombre = nombre;
    }

    public Cliente(String loginUser, String loginPass, String nombre, EquipoPais nacionalidad, Hotel se_hospeda_en) {
        this(loginUser, loginPass, nombre);
        this.nacionalidad = nacionalidad;
        this.se_hospeda_en = se_hospeda_en;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public EquipoPais getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(EquipoPais nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public Hotel getSe_hospeda_en() {
        return se_hospeda_en;
    }

    public void setSe_hospeda_en(Hotel se_hospeda_en) {
        this.se_hospeda_en = se_hospeda_en;
    }

}
