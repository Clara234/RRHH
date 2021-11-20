package com.rrhh.auxiliares;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class CreaBackupTablas {

	
	public static void creaBackupTablas() throws IOException{
		//definimos archivo Backup
        File fbackup = new File("ejercicioregiones.sql");
      
       String[]command  = new String[] {"cmd.exe", "/c", "mysqldump.exe --quick --lock-tables  --user=root  --password=root   ejercicioregiones"};
       
       final Process proceso = Runtime.getRuntime().exec(command);
       if(proceso != null) {
    	    new Thread(new Runnable() {
    	    	@Override
    	    	public void run() {
    	    		try {
    	    			try(BufferedReader  reader = new  BufferedReader(new InputStreamReader(new DataInputStream(proceso.getInputStream())));
    	    					BufferedWriter writer = new BufferedWriter(new FileWriter(fbackup))){
    	    					String line;
    	    					while((line = reader.readLine()) !=null) {
    	    						writer.write(line+"\n");
    	    						writer.newLine();
    	    					}
    	    				
    	    		}
    	    		
    	    		
    	    	}catch(IOException e) {
    	    		e.printStackTrace();
    	    	}
    	    }
       }).start();
    	    
       }
  }
	
	public static void main(String []args) throws IOException {

		
		
	}
}

