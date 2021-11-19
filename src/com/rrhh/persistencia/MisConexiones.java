package com.rrhh.persistencia;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MisConexiones {
	
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	private String sentencia;
	private String parametro;
	private String parametro2;
	private ConfigDir atrapo;
	
	public MisConexiones() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		atrapo = ConfigDir.getInstance();
	
	}
	public Connection getConexion() throws SQLException {
		con = DriverManager.getConnection(atrapo.getProperty("url"),atrapo.getProperty("user"),atrapo.getProperty("pass"));
		if (con != null)
			System.out.println("Conexion CORRECTA");
		return con;
	}
	public PreparedStatement getPS(String sentencia) throws SQLException{
		this.sentencia = sentencia;
		ps=getConexion().prepareStatement(sentencia);
		return ps;
	}
	public ResultSet getRS(String sentencia) throws SQLException {
		this.sentencia = sentencia;
		rs=getPS(sentencia).executeQuery();
		return rs; 
	}
	public Connection getEstadoConexion() {
		return con;
	}
	public void cierraConexion(Connection cn) {
		try {
			if(cn !=null || !cn.isClosed()) {
				System.out.println("una conexion cerrada");
				cn.close();
			}
		}catch(Exception e) {
			
		}
	}
	public ResultSet dameResultSetSimple(String property) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	public PreparedStatement damePSValUsu(String sentencia, String parametro, String parametro2, int grupo) throws SQLException{
		this.parametro=parametro;
		this.parametro2= parametro2;
		this.sentencia = sentencia;
		ps= getConexion().prepareStatement(sentencia);
		ps.setString(1, parametro);
		ps.setString(2,parametro2);
		ps.setInt(3, grupo);
		return ps;
	}

}
