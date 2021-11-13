package com.rrhh.persistencia;

import java.io.InputStream;

import java.util.Properties;

public class ConfigDir {
	
	//inicializamos properties
		private static Properties oProperties = new Properties();
		//el objeto se crea a si mismo, no necesitan new sino el objeto estatico instanciado
		private static ConfigDir conf = null;
		public ConfigDir() {
			try {
				InputStream oImputStream = getClass().getResourceAsStream("profundo/CSIDir.properties");
				oProperties.load(oImputStream);
			} catch(Exception e) {
				
			}
		
		}
		public static ConfigDir getInstance() {
			if(null==conf)
				conf = new ConfigDir();
			return conf;
		}
		
		public String getProperty(String prop) {
			return oProperties.getProperty(prop);
		}
		
		public boolean getBooleanProperty(String prop) {
			boolean ret = false;
			String salida = oProperties.getProperty(prop);
			if(salida == "true") {
				ret = true;
			}
			return ret;
		}
		public Object getReverso(boolean jefe) {
			// TODO Auto-generated method stub
			return null;
		}

}
