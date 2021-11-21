package com.rrhh.pojos;

import java.sql.Timestamp;

public class Usuario {
      
   private int id;
   private int grupo;
   private String nombre;
   private String apellido;
   private String alias;
   private String clave;
   private Timestamp fecha_entrada;
   
   
    public Usuario(int id, int grupo, String nombre, String apellido, String alias, String clave, Timestamp fecha_entrada) {super();
	this.setId(id);
	this.setGrupo(grupo);
	this.setNombre(nombre);
	this.setApellido(apellido);
	this.setAlias(alias);
	this.setClave(clave);
	this.setFecha_entrada(fecha_entrada);
}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getGrupo() {
		return grupo;
	}


	public void setGrupo(int grupo) {
		this.grupo = grupo;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getApellido() {
		return apellido;
	}


	public void setApellido(String apellido) {
		this.apellido = apellido;
	}


	public String getAlias() {
		return alias;
	}


	public void setAlias(String alias) {
		this.alias = alias;
	}


	public String getClave() {
		return clave;
	}


	public void setClave(String clave) {
		this.clave = clave;
	}


	public Timestamp getFecha_entrada() {
		return fecha_entrada;
	}


	public void setFecha_entrada(Timestamp fecha_entrada) {
		this.fecha_entrada = fecha_entrada;
	}
   
   

	
	
	
	
    
}
