package com.rrhh.graficos;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.rrhh.graficos.PanelEmpleado.gestorJefes;
import com.rrhhpojos.Cliente;
import com.rrhhpojos.Empleado;

public class PanelCliente  extends JPanel implements Servicios{
	DefaultTableModel dtm;
	Vector listaCliente;
	JTextField tf_id_cliente, tf_nombre, tf_apellidos, tf_dnie_nie, tf_telefono, tf_email, tf_direccion_vivienda, tf_ciudad, tf_cp, tf_fecha_alta;
	public PanelCliente(int ancho, int alto){
		
		setLayout(new BorderLayout());
		add(creaPanelNorte(alto,ancho), BorderLayout.NORTH);
	  add(setTabla(alto,ancho), BorderLayout.CENTER);

	
		
		
	}
	

	public JScrollPane setTabla(int ancho, int alto) {
		dtm = new DefaultTableModel();
		dtm.addColumn("ID CLIENTE");
		dtm.addColumn("NOMBRE");
		dtm.addColumn("APELLIDOS");
		dtm.addColumn("DNIE_NIE");
		dtm.addColumn("TELEFONO");
		dtm.addColumn("EMAIL");
		dtm.addColumn("DIRECCION_VIVIENDA");
		dtm.addColumn("CIUDAD");
		dtm.addColumn("CP");
		dtm.addColumn("FECHA_ALTA");
		
		JTable tabla2 = new JTable(dtm);
		JScrollPane sp = new JScrollPane(tabla2);
		sp.setPreferredSize(new Dimension((int)(ancho*0.8),(int)(alto*0.8)));
		return sp;
	}
	
	public JPanel creaPanelNorte(int alto,int ancho) {
		
		JPanel panel = new JPanel();
		panel.setLocation(200, 400);
		panel.setVisible(true);
		panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		JMenuBar jmb = new JMenuBar();
		JMenu busquedas = new JMenu("Buscar");
		JMenuItem jmi1= new JMenuItem("por salarios");
		JMenuItem jmi2 = new JMenuItem("por jefes");
        jmi2.addActionListener(new gestorJefes());
		busquedas.add(jmi1);
		busquedas.add(jmi1);
		jmb.add(busquedas);
		panel.add(jmb);
		return panel;
		
	}
	
	public class gestorJefes implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	
	public JPanel setPanelEste(int alto, int ancho, JPanel p1, JPanel p2) {
		JPanel panelEste = new JPanel();
		panelEste.setLayout(new BorderLayout());
		panelEste.setPreferredSize(new Dimension((int)(ancho*0.15), (int)(alto*0.8)));
		panelEste.add(p1, BorderLayout.NORTH);
		panelEste.add(p2, BorderLayout.CENTER);
		return panelEste;
	}

	public JPanel setPanelEsteDatos(int alto, int ancho) {
		
		JPanel panelEsteDatos = new JPanel();
		panelEsteDatos.setLayout(new BoxLayout(panelEsteDatos, BoxLayout.Y_AXIS));
		JLabel l_id_cliente = new JLabel("id");
		tf_id_cliente = new JTextField();
		tf_id_cliente.setMaximumSize(new Dimension(250,250));
		
		JLabel l_nombre = new JLabel("Nombre");
		tf_nombre = new JTextField();
		tf_nombre.setForeground(Color.gray);
		Font f = new Font("Italic", Font.ITALIC, 12);
		tf_nombre.setFont(f);
		tf_nombre.setMaximumSize(new Dimension(250,20));
		
		
		JLabel l_apellidos = new JLabel("Apellidos");
		tf_apellidos = new JTextField();
		tf_apellidos.setForeground(Color.gray);
		Font f3 = new Font("Italic", Font.ITALIC, 12);
		tf_apellidos.setFont(f3);
		tf_apellidos.setMaximumSize(new Dimension(250,20));
		
		JLabel l_dnie_nie = new JLabel("Dni/nie");
		tf_dnie_nie = new JTextField();
		tf_dnie_nie.setForeground(Color.gray);
		Font f4 = new Font("Italic", Font.ITALIC, 12);
		tf_dnie_nie.setFont(f4);
		tf_dnie_nie.setMaximumSize(new Dimension(250,20));
		
		
		JLabel l_telefono = new JLabel("Telefono");
		tf_telefono  = new JTextField();
		tf_telefono.setForeground(Color.gray);
		Font f5 = new Font("Italic", Font.ITALIC, 12);
		tf_telefono.setFont(f5);
		tf_telefono.setMaximumSize(new Dimension(250,20));
		
		
		JLabel l_email= new JLabel("Email");
		tf_email  = new JTextField();
		tf_email.setForeground(Color.gray);
		Font f6= new Font("Italic", Font.ITALIC, 12);
		tf_email.setFont(f6);
		tf_email.setMaximumSize(new Dimension(250,20));
		
		
		
		JLabel l_direccion_vivienda= new JLabel("Direccion_vivienda");
		tf_direccion_vivienda  = new JTextField();
		tf_direccion_vivienda.setForeground(Color.gray);
		Font f7= new Font("Italic", Font.ITALIC, 12);
		tf_direccion_vivienda.setFont(f7);
		tf_direccion_vivienda.setMaximumSize(new Dimension(250,20));
		
		
		JLabel l_ciudad= new JLabel("Ciudad");
		tf_ciudad  = new JTextField();
		tf_ciudad.setForeground(Color.gray);
		Font f8= new Font("Italic", Font.ITALIC, 12);
		tf_ciudad.setFont(f8);
		tf_ciudad.setMaximumSize(new Dimension(250,20));
		
		
		JLabel l_cp= new JLabel("CP");
		tf_cp  = new JTextField();
		tf_cp.setForeground(Color.gray);
		Font f9= new Font("Italic", Font.ITALIC, 12);
		tf_cp.setFont(f9);
		tf_cp.setMaximumSize(new Dimension(250,20));
		
		
		JLabel l_fecha_alta= new JLabel("Fecha alta");
		tf_fecha_alta = new JTextField();
		tf_fecha_alta.setForeground(Color.gray);
		Font f10= new Font("Italic", Font.ITALIC, 12);
		tf_cp.setFont(f10);
		tf_cp.setMaximumSize(new Dimension(250,20));
		
		
		panelEsteDatos.add(l_id_cliente);
		panelEsteDatos.add(tf_id_cliente);
		panelEsteDatos.add(l_nombre);
		panelEsteDatos.add(tf_nombre);
		panelEsteDatos.add(l_apellidos);
		panelEsteDatos.add(tf_apellidos);
		panelEsteDatos.add(l_dnie_nie);
		panelEsteDatos.add(tf_dnie_nie);
		panelEsteDatos.add(l_telefono);
		panelEsteDatos.add(tf_telefono);
		panelEsteDatos.add(l_email);
		panelEsteDatos.add(tf_email);
		panelEsteDatos.add(l_direccion_vivienda);
		panelEsteDatos.add(tf_direccion_vivienda);
		panelEsteDatos.add(l_ciudad);
		panelEsteDatos.add(l_cp);
		panelEsteDatos.add(tf_cp);
		panelEsteDatos.add(l_fecha_alta);
		panelEsteDatos.add(tf_fecha_alta);
	
		
		
		
		
		
		return null;
		
	}




	@Override
	public void addEmpleado(Empleado emp) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public List<Empleado> getAllEmpleados() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Empleado getbyId(int id) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Empleado updateEmp(Empleado emp) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void deleteEmp(int id) {
		// TODO Auto-generated method stub
		
	}
}
