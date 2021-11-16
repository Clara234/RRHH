package com.rrhh.auxiliares;


import java.sql.Date;
import java.sql.Timestamp;
import java.util.StringTokenizer;

public class DameFecha {

	
		
		
		
		public String dameDateUtilAqui(java.util.Date fechaAlli){
			String cadena=fechaAlli.toString();
		StringTokenizer st=new StringTokenizer(cadena," ");
		String uno=st.nextToken();
		String dos=st.nextToken();
		String tres=st.nextToken();
		String cuatro=st.nextToken();
		String cinco=st.nextToken();
		String seis=st.nextToken();

		String aqui=tres+" "+dos+" "+cuatro+" "+seis;



		return aqui;
		}
			
			
			
			
			public String dameTimestampAqui(Timestamp fechaAlli){
			String cadena=fechaAlli.toString();
		StringTokenizer st=new StringTokenizer(cadena," ");
		String uno=st.nextToken();
		String dos=st.nextToken();
		st=new StringTokenizer(uno,"-");
		String tres=st.nextToken();
		String cuatro=st.nextToken();
		String cinco=st.nextToken();

		String aqui=cinco+"/"+cuatro+"/"+tres+" "+dos;



		return aqui;
		}
			
			
			


		public Timestamp dameTimestampAlli(String fechaTAqui,String horas){
		StringTokenizer st=new StringTokenizer(fechaTAqui," ");
		String uno=st.nextToken();
		String dos=horas;
		st=new StringTokenizer(uno,"/");
		String tres=st.nextToken();
		String cuatro=st.nextToken();
		String cinco=st.nextToken();

		String alli=cinco+"-"+cuatro+"-"+tres+" "+dos;
		Timestamp fechaAlli=Timestamp.valueOf(alli);


		return fechaAlli;
		}


		public Date dameDateAlli(String fechaAqui){
		StringTokenizer st=new StringTokenizer(fechaAqui,"/");
		String uno=st.nextToken();
		String dos=st.nextToken();
		String tres=st.nextToken();

		String alli=tres+"-"+dos+"-"+uno;
		Date fechaAlli=Date.valueOf(alli);


		return fechaAlli;
		}


		public String dameDateAqui(Date fechaAlli){
			String cadena=fechaAlli.toString();
		StringTokenizer st=new StringTokenizer(cadena,"-");
		String uno=st.nextToken();
		String dos=st.nextToken();
		String tres=st.nextToken();

		String aqui=tres+"/"+dos+"/"+uno;



		return aqui;
		}




		public Object dameTime(Timestamp fecha_nacimiento) {
			// TODO Auto-generated method stub
			return null;
		}

}
