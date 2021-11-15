package com.rrhh.graficos;

import java.awt.Color;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.WindowConstants;

public class Lan {
	
	
	public static void main(String[] args) {
		setMarco();

	}
	public static void setMarco() {
		JFrame marco = new JFrame("CRM");            
		//top,left
		marco.setLocation(0,0);
		//JOptionPane.showMessageDialog(marco, "Bienvenido","",JOptionPane.DEFAULT_OPTION);
		
		
		
		
		//es abstracta, toolkit no puede crear new, no es instanciable
		Toolkit tk = Toolkit.getDefaultToolkit();
		Image imagen = tk.getImage("candy_icon.png");
		marco.setIconImage(imagen);
		marco.setSize(new Dimension(800,650));
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
		//especificamos al programa que finalice cuadno el marco se cierre
		marco.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		marco.setMinimumSize(new Dimension(800,650));
		//Se pon visible al final
		marco.setVisible(true);
	}
}
