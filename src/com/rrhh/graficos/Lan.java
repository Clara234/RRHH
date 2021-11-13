package com.rrhh.graficos;

import java.awt.Color;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class Lan {
	
	
	public static void main(String[] args) {
		setMarco();

	}
	public static void setMarco() {
		JFrame marco = new JFrame("CRM");            
		//top,left
		marco.setLocation(320,320);
		
		//es abstracta, toolkit no puede crear new, no es instanciable
		Toolkit tk = Toolkit.getDefaultToolkit();
		Image imagen = tk.getImage("candy_icon.png");
		marco.setIconImage(imagen);
		//almacenar las dimensiones de la pantalla del usuario
		Dimension dim=tk.getScreenSize();
		int anchoM=(int)(dim.width/2), altoM=(int)(dim.height/2);
		int anchoM1=(int)(dim.width/2), altoM1=(int)(dim.height/2);
		
		//crear pestañas dentro del panel:
		JTabbedPane pestanha = new JTabbedPane();
		pestanha.setForeground(Color.gray);
		pestanha.add("Directorio cliente", new PanelEmpleado(anchoM, altoM));
		pestanha.add("Hipotecas", new PanelCliente(anchoM1, altoM1));
		//Aqui es donde se visualiza, añadiendolo al marco
		
		
		
		
		
		marco.getContentPane().add(pestanha);
		//imprimir por consola la resolucion de la pantalla
		System.out.println(dim.width+"px x "+dim.height+"px");
		marco.setSize(anchoM,altoM);
		marco.setSize(anchoM1,altoM1);
		//Se pon visible al final
		marco.setVisible(true);
	}
}
