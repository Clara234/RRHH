package com.rrhh.graficos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.MouseInputListener;
import javax.swing.table.DefaultTableModel;

import com.rrhh.pojos.Usuario;

public class PanelUsuario extends JPanel {
	DefaultTableModel dtm;
	Usuario seleccionado;
	
	JTable tabla;
	JTextField  tf_nombre, tf_apellido, tf_alias, tf_clave, tf_fecha_entrada, tf_grupo;
	List<Usuario> listaUsuarios;
	
	
	public PanelUsuario(int ancho, int alto){
		setLayout(new BorderLayout());
		add(setTabla(alto, ancho), BorderLayout.CENTER);
		add(setPanelEste(alto,ancho, setPanelEsteDatos(alto,ancho), setPanelEsteControl(ancho,alto)), BorderLayout.EAST);
		
	}
	
	public JScrollPane setTabla(int alto, int ancho) {
		dtm = new DefaultTableModel();
		dtm.addColumn("Nombre");
		dtm.addColumn("Apellido");
		dtm.addColumn("Alias");
		dtm.addColumn("Clave");
		dtm.addColumn("Fecha_entrada");
		dtm.addColumn("Grupo");
		
		tabla = new JTable(dtm);
		tabla.addMouseListener(new gestorTabla());
		
		JScrollPane sp = new JScrollPane(tabla);
		
		sp.setPreferredSize(new Dimension((int) (ancho * 0.8), (int) (alto * 0.8)));
		return sp;
	}
	
	
	public JPanel setPanelEste(int alto, int ancho, JPanel p1, JPanel p2) {
		JPanel panelEste = new JPanel();
		panelEste.setLayout(new BorderLayout());
		panelEste.setPreferredSize(new Dimension((int) (ancho * 0.15), (int) (alto * 0.8)));
		panelEste.add(p1, BorderLayout.NORTH);
		panelEste.add(p2, BorderLayout.CENTER);
		return panelEste;
	}
	
	
	public JPanel setPanelEsteDatos(int alto, int ancho) {
		JPanel panelEsteDatos = new JPanel();
		panelEsteDatos.setLayout(new BoxLayout(panelEsteDatos,BoxLayout.Y_AXIS));
		JLabel l_nombre = new JLabel("Nombre");
		tf_nombre = new JTextField();
		tf_nombre.setForeground(Color.gray);
		Font f = new Font("Italic", Font.ITALIC,12);
		tf_nombre.setFont(f);
		tf_nombre.setMaximumSize(new Dimension(250,20));
		JLabel l_apellido = new JLabel("Apellido");
		tf_apellido = new JTextField();
		tf_apellido.setForeground(Color.gray);
		tf_apellido.setFont(f);
		tf_apellido.setMaximumSize(new Dimension(250,20));
		JLabel l_alias = new JLabel("Alias");
		tf_alias = new JTextField();
		tf_alias.setForeground(Color.gray);
		tf_alias.setFont(f);
		tf_alias.setMaximumSize(new Dimension(250,20));
		JLabel l_clave = new JLabel("Clave");
		tf_clave = new JTextField();
		tf_clave.setForeground(Color.gray);
		tf_clave.setFont(f);
		tf_clave.setMaximumSize(new Dimension(250,20));
		JLabel l_fecha_entrada = new JLabel("Fecha_entrada");
		tf_fecha_entrada = new JTextField();
		tf_fecha_entrada.setForeground(Color.gray);
		tf_fecha_entrada.setFont(f);
		tf_fecha_entrada.setMaximumSize(new Dimension(250,20));
		JLabel l_grupo = new JLabel("Grupo");
		tf_grupo = new JTextField();
		tf_grupo.setForeground(Color.gray);
	    tf_grupo.setFont(f);
		tf_grupo.setMaximumSize(new Dimension(250,20));
		
		
		panelEsteDatos.add(l_nombre);
		panelEsteDatos.add(tf_nombre);
		panelEsteDatos.add(Box.createRigidArea(new Dimension(0,10)));
		panelEsteDatos.add(l_apellido);
		panelEsteDatos.add(tf_apellido);
		panelEsteDatos.add(Box.createRigidArea(new Dimension(0,10)));
		panelEsteDatos.add(l_alias);
		panelEsteDatos.add(tf_alias);
		panelEsteDatos.add(Box.createRigidArea(new Dimension(0,10)));
		panelEsteDatos.add(l_clave);
		panelEsteDatos.add(tf_clave);
		panelEsteDatos.add(Box.createRigidArea(new Dimension(0,10)));
		panelEsteDatos.add(l_fecha_entrada);
		panelEsteDatos.add(tf_fecha_entrada);
		panelEsteDatos.add(Box.createRigidArea(new Dimension(0,10)));
		panelEsteDatos.add(l_grupo);
		panelEsteDatos.add(tf_grupo);
		panelEsteDatos.add(Box.createRigidArea(new Dimension(0,10)));
		
		return panelEsteDatos;
		
		
	}
	
	
	public JPanel setPanelEsteControl(int alto, int ancho) {
		
		JPanel panelEsteControl = new JPanel();
		panelEsteControl.setLayout(new BoxLayout(panelEsteControl, BoxLayout.Y_AXIS));
		panelEsteControl.add(Box.createRigidArea(new Dimension(0,60)));
		panelEsteControl.setPreferredSize(new Dimension((int) (alto * 0.01), (int) (ancho * 0.01)));
		
		JButton botonVer = new JButton("Ver");
		botonVer.setForeground(Color.BLUE);
		panelEsteControl.add(botonVer);
		JButton botonInsertar = new JButton("Insertar");
		botonInsertar.setForeground(Color.BLUE);
		panelEsteControl.add(botonInsertar);
		JButton botonBorrar = new JButton("Borrar");
		botonBorrar.setForeground(Color.BLUE);
		panelEsteControl.add(botonBorrar);
		JButton botonActualizar = new JButton("Actualizar");
		botonActualizar.setForeground(Color.BLUE);
		panelEsteControl.add(botonActualizar);
		
		
		
		/*botonInsertar.addActionListener(new gestorInsertar());
		botonVer.addActionListener(new gestorVer());
		botonActualizar.addActionListener(new gestorActualizar());
		botonBorrar.addActionListener(new gestorBorrar());*/
		
       
		
		
		
		return panelEsteControl;
	}
	
	
	public class gestorTabla implements MouseInputListener{
	

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			int j = tabla.getSelectedRow();
			seleccionado = listaUsuarios.get(j);
			tf_nombre.setText(seleccionado.getNombre());
			tf_apellido.setText(seleccionado.getApellido());
			tf_alias.setText(seleccionado.getAlias());
			tf_clave.setText(seleccionado.getClave());
			tf_fecha_entrada.setText("" +seleccionado.getFecha_entrada());
			tf_grupo.setText("" +seleccionado.getGrupo());
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	
	
	
	

}
