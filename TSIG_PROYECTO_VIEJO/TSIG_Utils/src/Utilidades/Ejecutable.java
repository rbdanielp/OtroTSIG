package Utilidades;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import uy.tsig.grupo12.entidades.POI.*;
import uy.tsig.grupo12.entidades.Partidos.*;
import uy.tsig.grupo12.entidades.Usuario.*;
import uy.tsig.grupo12.logica.FachadaLogica;
import uy.tsig.grupo12.logica.LogicaException;
import uy.tsig.grupo12.persistencia.FachadaPersistencia;

public class Ejecutable {

    //Main de prueba, ingreso de datos
    public static void main(String[] args) throws LogicaException, ParseException {

           
        //***************************POIS*****************************
        /*POI poi1 = new Bar("Bar_Prueba");
        Hotel hotel1 = new Hotel("_Prueba");
        FachadaLogica.getPOIController().altaPOI(hotel1);
        FachadaLogica.getPOIController().altaPOI(poi1);*/
        
        //*************Usuarios Administradores************************
        
        Usuario admin1 = new Administrador("admin", "admin");
        Usuario admin2 = new Administrador("tecnoinf", "tecnoinf");
        FachadaLogica.getUsuarioController().alta(admin1);
        FachadaLogica.getUsuarioController().alta(admin2);
        
        
        
        //*************Usuarios Clientes************************
        EquipoPais uruguay = (EquipoPais) FachadaPersistencia.getPersistencia().buscar(EquipoPais.class, "Uruguay");
        EquipoPais españa = (EquipoPais) FachadaPersistencia.getPersistencia().buscar(EquipoPais.class, "España");
        Usuario u1 = new Cliente("tecnologo1", "tecnologo1", "Tecnologo Gp12",uruguay, null);
        Usuario u2 = new Cliente("tecnologo2", "tecnologo2", "Tecnologo Gp12 2", españa, null);
        FachadaLogica.getUsuarioController().alta(u1);
        FachadaLogica.getUsuarioController().alta(u2);
 
        /*
        //**********************Equipos*****************************
        EquipoPais equipo1 = new EquipoPais("Rusia");
        EquipoPais equipo2 = new EquipoPais("Arabia Saudita");
        EquipoPais equipo3 = new EquipoPais("Egipto");
        EquipoPais equipo4 = new EquipoPais("Uruguay");
        
        EquipoPais equipo5 = new EquipoPais("España");
        EquipoPais equipo6 = new EquipoPais("Irán");
        EquipoPais equipo7 = new EquipoPais("Portugal");
        EquipoPais equipo8 = new EquipoPais("Marruecos");
        
        EquipoPais equipo9 = new EquipoPais("Francia");
        EquipoPais equipo10 = new EquipoPais("Australia");
        EquipoPais equipo11 = new EquipoPais("Perú");
        EquipoPais equipo12 = new EquipoPais("Dinamarca");
        
        EquipoPais equipo13 = new EquipoPais("Argentina");
        EquipoPais equipo14 = new EquipoPais("Islandia");
        EquipoPais equipo15 = new EquipoPais("Croacia");
        EquipoPais equipo16 = new EquipoPais("Nigeria");
        
        EquipoPais equipo17 = new EquipoPais("Costa Rica");
        EquipoPais equipo18 = new EquipoPais("Brasil");
        EquipoPais equipo19 = new EquipoPais("Serbia");
        EquipoPais equipo20 = new EquipoPais("Suiza");
        
        EquipoPais equipo21 = new EquipoPais("Alemania");
        EquipoPais equipo22 = new EquipoPais("México");
        EquipoPais equipo23 = new EquipoPais("Suecia");
        EquipoPais equipo24 = new EquipoPais("Corea del Sur");
        
        EquipoPais equipo25 = new EquipoPais("Bélgica");
        EquipoPais equipo26 = new EquipoPais("Panamá");
        EquipoPais equipo27 = new EquipoPais("Túnez");
        EquipoPais equipo28 = new EquipoPais("Inglaterra");
        
        EquipoPais equipo29 = new EquipoPais("Polonia");
        EquipoPais equipo30 = new EquipoPais("Senegal");
        EquipoPais equipo31 = new EquipoPais("Colombia");
        EquipoPais equipo32 = new EquipoPais("Japón");
        
        FachadaLogica.getPartidoController().alta(equipo1);
        FachadaLogica.getPartidoController().alta(equipo2);
        FachadaLogica.getPartidoController().alta(equipo3);
        FachadaLogica.getPartidoController().alta(equipo4);
        FachadaLogica.getPartidoController().alta(equipo5);
        FachadaLogica.getPartidoController().alta(equipo6);
        FachadaLogica.getPartidoController().alta(equipo7);
        FachadaLogica.getPartidoController().alta(equipo8);
        FachadaLogica.getPartidoController().alta(equipo9);
        FachadaLogica.getPartidoController().alta(equipo10);
        FachadaLogica.getPartidoController().alta(equipo11);
        FachadaLogica.getPartidoController().alta(equipo12);
        FachadaLogica.getPartidoController().alta(equipo13);
        FachadaLogica.getPartidoController().alta(equipo14);
        FachadaLogica.getPartidoController().alta(equipo15);
        FachadaLogica.getPartidoController().alta(equipo16);
        FachadaLogica.getPartidoController().alta(equipo17);
        FachadaLogica.getPartidoController().alta(equipo18);
        FachadaLogica.getPartidoController().alta(equipo19);
        FachadaLogica.getPartidoController().alta(equipo20);
        FachadaLogica.getPartidoController().alta(equipo21);
        FachadaLogica.getPartidoController().alta(equipo22);
        FachadaLogica.getPartidoController().alta(equipo23);
        FachadaLogica.getPartidoController().alta(equipo24);
        FachadaLogica.getPartidoController().alta(equipo25);
        FachadaLogica.getPartidoController().alta(equipo26);
        FachadaLogica.getPartidoController().alta(equipo27);
        FachadaLogica.getPartidoController().alta(equipo28);
        FachadaLogica.getPartidoController().alta(equipo29);
        FachadaLogica.getPartidoController().alta(equipo30);
        FachadaLogica.getPartidoController().alta(equipo31);
        FachadaLogica.getPartidoController().alta(equipo32);

        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String stringFechaConHora = "2018-09-15";
        Date fechaConHora = sdf.parse(stringFechaConHora);

        Partido partido1 = new Partido(DtTipo.FINAL, new Date(), equipo1, equipo2, null);
        Partido partido2 = new Partido(DtTipo.GRUPO_A, fechaConHora, equipo2, equipo1, null);
        FachadaLogica.getPartidoController().alta(partido2);
        FachadaLogica.getPartidoController().alta(partido1);
*/
    }

}
