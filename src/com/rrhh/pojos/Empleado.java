package com.rrhh.pojos;

import java.sql.Timestamp;

public class Empleado {
	
	private String nombre, apellido;
	private Timestamp fecha_nacimiento;
	private double salario;
	private boolean jefe;
	private int id_departamento, id_puesto, id;
	
	public Empleado(int id,String nombre, String apellido, Timestamp fecha_nacimiento, Double salario, boolean jefe, int id_departamento, int id_puesto) {
		this.id = id;
		this.nombre = nombre;
		this.apellido= apellido;
		this.fecha_nacimiento = fecha_nacimiento;
		this.salario=salario;
		this.jefe = jefe;
		this.id_departamento = id_departamento;
		this.id_puesto = id_puesto;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public Timestamp getFecha_nacimiento() {
		return fecha_nacimiento;
	}
	public void setFecha_nacimiento(Timestamp fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
	}
	public double getSalario() {
		return salario;
	}
	public void setSalario(double salario) {
		this.salario = salario;
	}
	public boolean isJefe() {
		return jefe;
	}
	public void setJefe(boolean jefe) {
		this.jefe = jefe;
	}
	public int getId_departamento() {
		return id_departamento;
	}
	public void setId_departamento(int id_departtamento) {
		this.id_departamento = id_departtamento;
	}
	public int getId_puesto() {
		return id_puesto;
	}
	public void setId_puesto(int id_puesto) {
		this.id_puesto = id_puesto;
	}
	
	

}
