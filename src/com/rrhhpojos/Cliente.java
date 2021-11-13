package com.rrhhpojos;

import java.sql.Timestamp;
public class Cliente {
	
	private int id;
	private String Nombre, Apellidos,Dni_nie, Email, Direccion_vivienda, Ciudad;
	private int Telefono;
	private int CP;
	private Timestamp Fecha_alta;
	
	
	
	
	public Cliente(int id, String nombre, String apellidos, String dni_nie, String email, String direccion_vivienda,
			String ciudad, int telefono, int cP, Timestamp fecha_alta) {
		super();
		this.id = id;
		Nombre = nombre;
		Apellidos = apellidos;
		Dni_nie = dni_nie;
		Email = email;
		Direccion_vivienda = direccion_vivienda;
		Ciudad = ciudad;
		Telefono = telefono;
		CP = cP;
		Fecha_alta = fecha_alta;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String Nombre) {
		this.Nombre = Nombre;
	}
	
	
	
	public String getApellidos() {
		return Apellidos;
	}
	public void setApellidos(String Apellidos) {
		this.Apellidos = Apellidos;
	}
	
	public String getDni_nie() {
		return Dni_nie;
	}
	public void setDni_nie(String Dni_nie) {
		this.Dni_nie= Dni_nie;
	}
	
	public String getEmail() {
		return Email;
	}
	public void setEmail(String Email) {
		this.Email = Email;
	}
	
	public String getDireccion_vivienda() {
		return Direccion_vivienda;
	}
	public void setDireccion_vivienda(String Direccion_vivienda) {
		this.Direccion_vivienda = Direccion_vivienda;
	}
	
	public String getCiudad() {
		return Ciudad;
	}
	public void setCiudad(String Ciudad) {
		this.Ciudad = Ciudad;
	}
	
	public int getTelefono() {
		return Telefono;
	}
	public void setTelefono(int Telefono) {
		this.Telefono = Telefono;
	}
	public int getICP() {
		return CP;
	}
	public void setCP(int CP) {
		this.CP = CP;
	}
	public Timestamp getFecha_alta() {
		return Fecha_alta;
	}
	public void setFecha_alta(Timestamp fecha_alta) {
		Fecha_alta = fecha_alta;
	}
}
