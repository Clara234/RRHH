package com.rrhh.pojos;

public class Usuario {
	public String alias, password;
	public int grupo;
	
	
	public Usuario(String alias, String password, int grupo) {
		super();
		this.alias = alias;
		this.password = password;
		this.grupo = grupo;
	}


	public String getAlias() {
		return alias;
	}


	public void setAlias(String alias) {
		this.alias = alias;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public int getGrupo() {
		return grupo;
	}


	public void setGrupo(int grupo) {
		this.grupo = grupo;
	}



	
	
	
	
    
}
