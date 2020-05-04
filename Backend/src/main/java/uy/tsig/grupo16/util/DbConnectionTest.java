package uy.tsig.grupo16.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnectionTest {

	public static void main(String[] args) {
		Loguear.logTitulo("Test de Conexion");
		
		String jdbcUrl	="jdbc:postgresql://localhost:5432/postgres";
		String user		="postgres";
		String password	="postgres";
		
		try {
			Connection myConn = DriverManager.getConnection(jdbcUrl, user, password);
			
			Loguear.logTitulo("Conexion EXITOSA!!!!");
		} catch (SQLException e) {
			
			Loguear.errorConTitulo("Error al crear la conexion.");
			e.printStackTrace();
		}

	}

}
