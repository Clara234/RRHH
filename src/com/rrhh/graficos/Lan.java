package com.rrhh.graficos;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;




import com.formdev.flatlaf.intellijthemes.FlatSolarizedLightIJTheme;


public class Lan {
	
	
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(new FlatSolarizedLightIJTheme());
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setMarco();
		
		}

	
	public static void setMarco() {
		JFrame marco = new JFrame("RRHH");
		// top,left
		// marco.setLocation(320,320);
		marco.setLocation(0, 0);

	/*	dim =marco.getToolkit().getScreenSize();
		marco.setSize(dim);
		marco.setUndecorated(true);
		marco.setVisible(true);*/
	

		JOptionPane.showConfirmDialog(marco, "Bienvenido", null, JOptionPane.CLOSED_OPTION);

		Toolkit.getDefaultToolkit().beep();

		// es abstracta, toolkit no puede crear new, no es instanciable
		Toolkit tk = Toolkit.getDefaultToolkit();
		//Image imagen = tk.getImage("icon.png");
		//marco.setIconImage(imagen);
		marco.setMinimumSize(new Dimension(800,700));

		Toolkit.getDefaultToolkit().beep();
		// almacenar las dimensiones de la pantalla del usuario
		Dimension dim = tk.getDefaultToolkit().getScreenSize();
		int anchoM = (int) (dim.width /2), altoM = (int) (dim.height /2);
		int anchoM1 = (int) (dim.width /2), altoM1 = (int) (dim.height /2);
	

		// crear pestañas dentro del panel:
		JTabbedPane pestanha = new JTabbedPane();
		pestanha.setForeground(Color.gray);
		pestanha.add("Empleados", new PanelEmpleado(anchoM, altoM));
		pestanha.add("Usuarios", new PanelUsuario(anchoM1, altoM1));


		// Aqui es donde se visualiza, añadiendolo al marco

		marco.getContentPane().add(pestanha);
		// imprimir por consola la resolucion de la pantalla
		System.out.println(dim.width + "px x " + dim.height + "px ");
		// especificamos al programa que finalice cuando el marco se cierre
		marco.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

		// Se pon visible al final
		marco.setVisible(true);
	}


	private static int getWidth(ImageObserver imageObserver) {
		// TODO Auto-generated method stub
		return 0;
	}
}
