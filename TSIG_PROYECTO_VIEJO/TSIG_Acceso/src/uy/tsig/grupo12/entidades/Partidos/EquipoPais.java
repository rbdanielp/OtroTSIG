package uy.tsig.grupo12.entidades.Partidos;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity(name = "EquipoPais")
@NamedQueries({
    @NamedQuery(name = "EquipoPais.findAll", query = "SELECT e FROM EquipoPais e")})
public class EquipoPais implements Serializable {

    @Id
    private String nombrePais;

    public EquipoPais() {
    }

    public EquipoPais(String nombrePais) {
        this.nombrePais = nombrePais;
    }

    public String getNombrePais() {
        return nombrePais;
    }

    public void setNombrePais(String nombrePais) {
        this.nombrePais = nombrePais;
    }

}
