package uy.tsig.grupo12.entidades.Partidos;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import uy.tsig.grupo12.entidades.POI.Estadio;

@Entity(name = "Partido")
@NamedQueries({
    @NamedQuery(name = "Partido.findAll", query = "SELECT p FROM Partido p")
    ,
    @NamedQuery(name = "Partido.findByEquipo", query = "SELECT p FROM Partido p WHERE p.equipo_1 = :equipo or p.equipo_2 = :equipo")
    ,
    @NamedQuery(name = "Partido.findByEquipoOrdASC", query = "SELECT p FROM Partido p WHERE p.equipo_1 = :equipo or p.equipo_2 = :equipo ORDER BY p.fecha ASC ")
    ,
    @NamedQuery(name = "Partido.findByDateOrdASC", query = "SELECT p FROM Partido p ORDER BY p.fecha ASC ")
    ,
    @NamedQuery(name = "Partido.findByTipo", query = "SELECT p FROM Partido p WHERE p.tipo = :tipo ORDER BY p.fecha ASC ")
    
})
public class Partido implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    private DtTipo tipo;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;

    @ManyToOne
    private Estadio se_enfrentan_en;

    @ManyToOne
    private EquipoPais equipo_1;

    @ManyToOne
    private EquipoPais equipo_2;

    @Basic
    private int goles_1;

    @Basic
    private int goles_2;

    public Partido() {
    }

    public Partido(DtTipo tipo, Date fecha, EquipoPais equipo_1, EquipoPais equipo_2, Estadio se_enfrentan_en) {
        super();
        this.tipo = tipo;
        this.fecha = fecha;
        this.equipo_1 = equipo_1;
        this.equipo_2 = equipo_2;
        this.se_enfrentan_en = se_enfrentan_en;
    }

    public Partido(DtTipo tipo, Date fecha, int goles_1, int goles_2, EquipoPais equipo_1, EquipoPais equipo_2,
            Estadio se_enfrentan_en) {
        super();
        this.tipo = tipo;
        this.fecha = fecha;
        this.goles_1 = goles_1;
        this.goles_2 = goles_2;
        this.equipo_1 = equipo_1;
        this.equipo_2 = equipo_2;
        this.se_enfrentan_en = se_enfrentan_en;
    }

    public DtTipo getTipo() {
        return tipo;
    }

    public void setTipo(DtTipo tipo) {
        this.tipo = tipo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getGoles_1() {
        return goles_1;
    }

    public void setGoles_1(int goles_1) {
        this.goles_1 = goles_1;
    }

    public int getGoles_2() {
        return goles_2;
    }

    public void setGoles_2(int goles_2) {
        this.goles_2 = goles_2;
    }

    public EquipoPais getEquipo_1() {
        return equipo_1;
    }

    public void setEquipo_1(EquipoPais equipo_1) {
        this.equipo_1 = equipo_1;
    }

    public EquipoPais getEquipo_2() {
        return equipo_2;
    }

    public void setEquipo_2(EquipoPais equipo_2) {
        this.equipo_2 = equipo_2;
    }

    public Estadio getSe_enfrentan_en() {
        return se_enfrentan_en;
    }

    public void setSe_enfrentan_en(Estadio se_enfrentan_en) {
        this.se_enfrentan_en = se_enfrentan_en;
    }

    public int getId() {
        return id;
    }
    
    public  String getStrFecha(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yy       -        HH:mm ");
        return dateFormat.format(fecha)+" Hs.";
    }

}
