package com.rrhh.pojos;

public class Usuario {

	private String alias;

	private String clave;
	private int grupo,id;

	public Usuario(int id,String alias, String clave, int grupo ) {

		this.alias = alias;
		this.clave = clave;
		this.grupo = grupo;
		this.id =id;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
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

	public int getGrupo() {
		return grupo;
	}

	public void setGrupo(int grupo) {
		this.grupo = grupo;
	}
	
	

}
