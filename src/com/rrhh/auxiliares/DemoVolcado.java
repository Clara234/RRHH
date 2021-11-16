package com.rrhh.auxiliares;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DemoVolcado {
	
	public static void main(String[] args)  throws SQLException, ClassNotFoundException{
		 Class.forName("com.mysql.cj.jdbc.Driver");
      
       Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ejercicioregiones?useSSL=false&serverTimezone=UTC","root","root");
       PreparedStatement ps = con.prepareStatement("SELECT idJefe from departamentos");
       		
       		ResultSet rs = ps.executeQuery();
       		
       		ArrayList<Integer> lista = new ArrayList<Integer>();
       		while(rs.next()) {
       			lista.add(rs.getInt("idJefe"));
       		}
       		
       
           
       		ps = con.prepareStatement("UPDATE empleados set jefe=true where id=?");
       		for(int j=0; j<lista.size();j++) {
       			ps.setInt(1, lista.get(j));
       			ps.executeUpdate();
       			
       			
       
           			
       		}
       		ps = con.prepareStatement("UPDATE empleados set jefe=false where jefe is null");
               ps.execute();
       		
       
	}
	


}
