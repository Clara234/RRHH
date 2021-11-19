package com.rrhh.persistencia;

import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.rrhh.graficos.PanelEmpleado;
import com.rrhh.persistencia.MisConexiones;

public class ValUsuarios {
	 private Object datos;
	  private void MisConexiones() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		
		datos = new MisConexiones();
	}
	
	
	public boolean validar(String alias, String clave, int grupo) throws SQLException, ClassNotFoundException {
		boolean chequeo =false;
		
		PreparedStatement ps = datos.damePSValUsu(ConfigDir.getInstance().getProperty("validacionUsu"),alias,clave,grupo);
		
		ResultSet rs = ps.executeQuery();
		chequeo = rs.next();
		datos.cierraConexion(MisConexiones());
		
		
		return chequeo;
		

		}
	
	private void cierraConexion() {
		
	}
	private Object MiConexion() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
