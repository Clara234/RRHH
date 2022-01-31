package com.rrhh.graficos;

import java.awt.Color;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.intellijthemes.FlatNordIJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatArcDarkIJTheme;

public class Lan {
	
	
	public static void main(String[] args) {
		setMarco();
		try {
		UIManager.setLookAndFeel(new FlatNordIJTheme());
		}catch(Exception e) {
			e.printStackTrace();
		}
		}

	
	public static void setMarco() {
		JFrame marco = new JFrame("RRHH");            
		//top,left
		//marco.setLocation(0,0);
		marco.getToolkit().getScreenSize();
		marco.setForeground(Color.PINK);
	    JOptionPane.showMessageDialog(marco, "Bienvenido", null, JOptionPane.INFORMATION_MESSAGE);
	    Toolkit.getDefaultToolkit().beep();
		
		
		//es abstracta, toolkit no puede crear new, no es instanciable
		Toolkit tk = Toolkit.getDefaultToolkit();
		Image imagen = tk.getImage("C:\\Users\\dam\\eclipse-workspace\\RRHH\\src\\img\\icon.png");
		marco.setIconImage(imagen);
		marco.setMinimumSize(new Dimension(800,840));
		//almacenar las dimensiones de la pantalla del usuario
		Dimension dim=tk.getScreenSize();
		int anchoM=(int)(dim.width/2), altoM=(int)(dim.height/2);
		int anchoM1=(int)(dim.width/2), altoM1=(int)(dim.height/2);
		
		//crear pestaņas dentro del panel:
		JTabbedPane pestanha = new JTabbedPane();
		pestanha.setForeground(Color.DARK_GRAY);
		pestanha.add("Empleados", new PanelEmpleado(anchoM, altoM));
		pestanha.add("Usuarios", new PanelUsuario(anchoM1, altoM1));
		//Aqui es donde se visualiza, aņadiendolo al marco
		marco.getContentPane().add(pestanha);
		//imprimir por consola la resolucion de la pantalla
		System.out.println(dim.width+"px x "+dim.height+"px");
		//especificamos al programa que finalice cuando el marco se cierre
		marco.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		marco.setMinimumSize(new Dimension(800,700));
		//Se pon visible al final
		marco.setVisible(true);
	}
}
