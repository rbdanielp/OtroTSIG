package uy.tsig.grupo16.util;

import java.util.logging.Logger;



public class Loguear {

	private static final Logger logger = Logger.getLogger("Loguear.class");

	// *******************************************************************************************

	/*
	 * Descripción: Se utiliza para resaltar titulos en el Log
	 * 
	 * EJEMPLO DE SALIDA
	 * 
	 * | ================= | | TITULO EN EL LOG | | ================= |
	 * 
	 * 
	 */
	public static void logTitulo(String titulo) {

		// logger.debug("logTitulo: titulo " + titulo );
		String linea = "";

		for (int i = 0; i < titulo.length(); i++) {
			linea = linea + "=";
		}

		// logger.info(" ");
		// logger.info(" ");
		logger.info(linea);
		logger.info(titulo.toUpperCase());
		logger.info(linea);
		// logger.info(" ");
		// logger.info(" ");

	}
	
	// Nivel de Importancia: Medio-Alto
	public static void errorConTitulo(String mensaje) {
		logger.info(" ");
		logger.info(" ");
		Loguear.logTitulo("  ***ERROR***  ");
		logger.info(mensaje);
		logger.info(" ");
		logger.info(" ");
	}
	
	// *******************************************************************************************

	// Nivel de Importancia: Menor (Loguea mayor cantidad de informacion)
	public static void debug(String mensaje) {
		logger.info(mensaje);
	}

	// Nivel de Importancia: Medio
	public static void info(String mensaje) {
		logger.info(mensaje);
	}

	// Nivel de Importancia: Medio-Alto
	public static void warning(String mensaje) {
		logger.info(mensaje);
	}

	// Nivel de Importancia: Medio-Alto
	public static void error(String mensaje) {
		logger.info(mensaje);
	}
}
