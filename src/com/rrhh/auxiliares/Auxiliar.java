package com.rrhh.auxiliares;

public class Auxiliar {

	public static String dameContrasenna(char[] con) {
		String contrasenna = "";
		for(int i = 0; i < con.length; i++) {
			contrasenna += con[i];
		}
		return contrasenna;
	}
	
	
	
}
