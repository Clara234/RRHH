package com.rrhh.graficos;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.MouseInputListener;
import javax.swing.table.DefaultTableModel;

import com.rrhh.persistencia.ConfigDir;
import com.rrhh.persistencia.MisConexiones;
import com.rrhh.pojos.Usuario;



public class PanelUsuario extends JPanel  implements Servicios2{
	DefaultTableModel dtm;
	Usuario seleccionado;
	Vector v;
	Usuario usuario;
	MisConexiones c;
	PreparedStatement ps;
	JTable tabla;
	JTextField tf_alias, tf_clave, tf_grupo;
	List<Usuario> listaUsuarios;
	
	
	public PanelUsuario(int ancho, int alto){
		setLayout(new BorderLayout());
		add(setMenuBar(alto,ancho),BorderLayout.NORTH);
		add(setTabla(alto, ancho), BorderLayout.CENTER);
		add(setPanelEste(alto,ancho, setPanelEsteDatos(alto,ancho), setPanelEsteControl(ancho,alto)), BorderLayout.EAST);
		
	}
	
	
	public JMenuBar setMenuBar(int alto, int ancho) {
		
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("Filtros");
		
		JMenuItem filtroAdmi = new JMenuItem("Admi");
		JMenuItem filtroUsu = new JMenuItem("Usuarios");
		
		filtroAdmi.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				filtroAdministradores();
			}
			
		});
		
		filtroUsu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		menu.add(filtroAdmi);
		menu.add(filtroUsu);
		menuBar.add(menu);
		
		return menuBar;
		
	}
	
	protected void filtroAdministradores() {
		// TODO Auto-generated method stub
		
		dtm.setRowCount(0);
		Usuario usuario;
		try {
			c = new MisConexiones();
			listaUsuarios = new ArrayList<Usuario>();
			ResultSet rs = c.getRS(ConfigDir.getInstance().getProperty("filtroAdmii"));
			while (rs.next()) {
				usuario = new Usuario(rs.getInt("id"),rs.getNString("alias"), rs.getNString("clave"),rs.getInt("grupo") );
				v = new Vector();
				v.addElement(usuario.getAlias());
				v.addElement(usuario.getClave());
				v.addElement(usuario.getGrupo());
			
				
			
				dtm.addRow(v);
				listaUsuarios.add(usuario);
			}
			
		}catch(Exception e) {e.printStackTrace();}
		
		
		
	}


	public JScrollPane setTabla(int alto, int ancho) {
		dtm = new DefaultTableModel();
		
		dtm.addColumn("Alias");
		dtm.addColumn("Clave");
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
		panelEste.setForeground(Color.YELLOW);
		panelEste.setPreferredSize(new Dimension((int) (ancho * 0.15), (int) (alto * 0.8)));
		panelEste.add(p1, BorderLayout.NORTH);
		panelEste.add(p2, BorderLayout.CENTER);
		return panelEste;
	}
	
	
	public JPanel setPanelEsteDatos(int alto, int ancho) {
		JPanel panelEsteDatos = new JPanel();
		panelEsteDatos.setLayout(new BoxLayout(panelEsteDatos,BoxLayout.Y_AXIS));
		JLabel l_alias = new JLabel("Alias");
		tf_alias = new JTextField();
		tf_alias.setForeground(Color.gray);
		Font f = new Font("Italic", Font.ITALIC, 12);
		tf_alias.setFont(f);
		tf_alias.setMaximumSize(new Dimension(250,20));
		JLabel l_clave = new JLabel("Clave");
		tf_clave = new JTextField();
		tf_clave.setForeground(Color.gray);
		tf_clave.setFont(f);
		tf_clave.setMaximumSize(new Dimension(250,20));
		JLabel l_fecha_entrada = new JLabel("Fecha_entrada");
		
		JLabel l_grupo = new JLabel("Grupo");
		tf_grupo = new JTextField();
		tf_grupo.setForeground(Color.gray);
	    tf_grupo.setFont(f);
		tf_grupo.setMaximumSize(new Dimension(250,20));
		
		
	
	
		panelEsteDatos.add(l_alias);
		panelEsteDatos.add(tf_alias);
		panelEsteDatos.add(Box.createRigidArea(new Dimension(0,10)));
		panelEsteDatos.add(l_clave);
		panelEsteDatos.add(tf_clave);
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
		panelEsteControl.add(Box.createRigidArea(new Dimension(0, 10)));
		JButton botonInsertar = new JButton("Insertar");
		botonInsertar.setForeground(Color.BLUE);
		panelEsteControl.add(botonInsertar);
		panelEsteControl.add(Box.createRigidArea(new Dimension(0, 10)));
		JButton botonBorrar = new JButton("Borrar");
		botonBorrar.setForeground(Color.BLUE);
		panelEsteControl.add(botonBorrar);
		panelEsteControl.add(Box.createRigidArea(new Dimension(0, 10)));
		JButton botonActualizar = new JButton("Actualizar");
		botonActualizar.setForeground(Color.BLUE);
		panelEsteControl.add(botonActualizar);
		panelEsteControl.add(Box.createRigidArea(new Dimension(0, 10)));
		
		
		
		botonInsertar.addActionListener(new gestorInsertar());
		botonVer.addActionListener(new gestorVer());
		botonActualizar.addActionListener(new gestorActualizar());
		botonBorrar.addActionListener(new gestorBorrar());
		
       
		
		
		
		return panelEsteControl;
	}
	
	public class gestorInsertar implements ActionListener{
		

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

			try {
				MisConexiones c = new MisConexiones();
				PreparedStatement ps = c.getPS(ConfigDir.getInstance().getProperty("query6"));
				ps.setString(1, tf_alias.getText());
				ps.setString(2, tf_clave.getText());
				ps.setInt(3, Integer.valueOf(tf_grupo.getText()));
				ps.executeUpdate();
				refresh();

			} catch (Exception e1) {
				e1.printStackTrace();
			}

		}
		
	}
	
	public class gestorVer implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			refresh();
		}
		
	}
	
	public class gestorBorrar implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			MisConexiones c1 = null;
			Object box2;

			int resp = JOptionPane.showConfirmDialog(null, "Usted eliminará a este usuario" + "¿Esta seguro?", // <- EL
																												// MENSAJE
					"Alerta!"/* <- El título de la ventana */, JOptionPane.YES_NO_OPTION/* Las opciones (si o no) */,
					JOptionPane.WARNING_MESSAGE/* El tipo de ventana, en este caso WARNING */);
			// Si la respuesta es sí(YES_OPTION)
			if (resp == JOptionPane.YES_OPTION) {

				try {
					c1 = new MisConexiones();
				} catch (InstantiationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IllegalAccessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				PreparedStatement ps = null;
				try {
					ps = c1.getPS(ConfigDir.getInstance().getProperty("query7"));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					ps.setInt(1, seleccionado.getId());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					ps.executeUpdate();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				refresh();

			} // El valor de box2 sera 1
				// Si la respuesta es no (NO_OPTION)
			if (resp == JOptionPane.NO_OPTION) {
				box2 = "0";
			} // El valor de box2 sera 0
		}

	}
	
	public class gestorActualizar implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			try {
				PreparedStatement ps = new MisConexiones().getPS(ConfigDir.getInstance().getProperty("query8"));
				ps.setString(1, tf_alias.getText());
				ps.setString(2, tf_clave.getText());
				ps.setInt(3, Integer.valueOf(tf_grupo.getText()));
				ps.setInt(4,seleccionado.getId());
				ps.executeUpdate();
				refresh();
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	
	public void refresh() {
		dtm.setRowCount(0);
		Usuario usuario;
	
		try {
			
			c = new MisConexiones();
			listaUsuarios = new ArrayList<Usuario>();
			ResultSet rs = c.getRS(ConfigDir.getInstance().getProperty("query5"));
			while (rs.next()) {
				usuario = new Usuario(rs.getInt("id"),rs.getNString("alias"), rs.getNString("clave"),rs.getInt("grupo") );
				v = new Vector();
				v.addElement(usuario.getAlias());
				v.addElement(usuario.getClave());
				v.addElement(usuario.getGrupo());
			
				
			
				dtm.addRow(v);
				listaUsuarios.add(usuario);
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			System.out.println(e1.getMessage());
		}
		
	}
	
	public class gestorTabla implements MouseInputListener{
	

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			int j = tabla.getSelectedRow();
			seleccionado = listaUsuarios.get(j);
			tf_alias.setText(seleccionado.getAlias());
			tf_clave.setText(seleccionado.getClave());
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


	@Override
	public void addUsuario(Usuario usuario) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Usuario> getAllUsuarios() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Usuario getbyId(int id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Usuario updateUsuario(Usuario usuario) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteUsuario(int id) throws SQLException {
		// TODO Auto-generated method stub
		
	}


}



	
	
	
	


