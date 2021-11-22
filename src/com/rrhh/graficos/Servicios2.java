package com.rrhh.graficos;

import java.sql.SQLException;
import java.util.List;

import com.rrhh.pojos.Usuario;


public interface Servicios2 {
	
	  public void addUsuario(Usuario usuario)throws SQLException;//Post 
		public List<Usuario> getAllUsuarios()throws SQLException;//GetAll
	    public Usuario getbyId(int id)throws SQLException;//get uno
	    public Usuario updateUsuario(Usuario usuario)throws SQLException;//PUT(actualizado)
	    public void deleteUsuario(int id)throws SQLException;// delete
	    


}
