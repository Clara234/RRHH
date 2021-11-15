package com.rrhh.graficos;

import javax.print.attribute.standard.Media;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import javax.swing.event.MouseInputListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;

import com.rrhh.auxiliares.DameFecha;
import com.rrhh.persistencia.ConfigDir;
import com.rrhh.persistencia.MisConexiones;
import com.rrhh.pojos.Cliente;
import com.rrhh.pojos.Empleado;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.lang.NumberFormatException;
import java.util.Vector;
import java.awt.*;

public class PanelEmpleado<Reproductor> extends JPanel implements Servicios {
	
	Vector ve;
   Empleado seleccionado;
	DefaultTableModel dtm;
	JTable tabla;
	JTextField tf_idDepartamento, tf_idPuesto, tf_nombre, tf_apellidos, tf_salario, tf_fecha_nacimiento;
	JCheckBox chb_jefe, ch_iniciosesion;
	List<Empleado> listaEmpleados ;
	public PanelEmpleado(int ancho, int alto) {
		//disposiciones de los objetos
		setLayout(new BorderLayout());
		add(creaPanelNorte(alto,ancho), BorderLayout.NORTH);
		add(setTabla(alto, ancho), BorderLayout.CENTER);
		//add(setMenuBar(alto, ancho), BorderLayout.NORTH);
		add(setPanelEste(alto, ancho, setPanelEsteDatos(alto, ancho), setPanelEsteControl(ancho, alto)), BorderLayout.EAST);
	}
	public JScrollPane setTabla(int alto, int ancho) {
		//objeto previo configurador
		dtm = new DefaultTableModel();
		//todos los datos que tendra
		dtm.addColumn("ID Departamento");
		dtm.addColumn("ID Puesto");
		dtm.addColumn("Nombre");
		dtm.addColumn("Apellidos");
		dtm.addColumn("Salario");
		dtm.addColumn("Fecha nacimiento");
		dtm.addColumn("Jefe");
		//se crea una tabla con la configuracion dtm que hemos creado
		 tabla = new JTable(dtm);
		 tabla.addMouseListener(new gestorTabla());
		//creamos una panel con scroll al que añadirle la tabla que acabamos de crear
		JScrollPane sp = new JScrollPane(tabla);
		//damos valores al tamaño del JScrollPane
		sp.setPreferredSize(new Dimension((int)(ancho*0.8), (int)(alto*0.8)));
		//devolvemos el panel scroll con todo lo que hemos creado dentro
		return sp;
	}
	public JPanel setPanelEsteDatos(int alto, int ancho) {
		JPanel panelEsteDatos = new JPanel();
		panelEsteDatos.setLayout(new BoxLayout(panelEsteDatos, BoxLayout.Y_AXIS));
		JLabel l_idDepartamento = new JLabel("idDepartamento");
		tf_idDepartamento = new JTextField();
		tf_idDepartamento.setForeground(Color.gray);
		Font f = new Font("Italic", Font.ITALIC, 12);
		tf_idDepartamento.setFont(f);
		tf_idDepartamento.setMaximumSize(new Dimension(250,20));
		JLabel l_idPuesto = new JLabel("idPuesto");
		tf_idPuesto = new JTextField();
		tf_idPuesto.setForeground(Color.gray);
		Font f2 = new Font("Italic", Font.ITALIC, 12);
		tf_idPuesto.setFont(f2);
		tf_idPuesto.setMaximumSize(new Dimension(250,20));
		JLabel l_nombre = new JLabel("Nombre");
		tf_nombre = new JTextField();
		tf_nombre.setForeground(Color.gray);
		Font f3 = new Font("Italic", Font.ITALIC, 12);
		tf_nombre.setFont(f3);
		tf_nombre.setMaximumSize(new Dimension(250,20));
		JLabel l_apellidos = new JLabel("Apellidos");
		tf_apellidos = new JTextField();
		tf_apellidos .setForeground(Color.gray);
		Font f4 = new Font("Italic", Font.ITALIC, 12);
		tf_apellidos .setFont(f4);
		tf_apellidos.setMaximumSize(new Dimension(250,20));
		JLabel l_salario = new JLabel("Salario");
		tf_salario = new JTextField();
		tf_salario.setForeground(Color.gray);
		Font f5 = new Font("Italic", Font.ITALIC, 12);
		tf_salario.setFont(f5);
		tf_salario.setMaximumSize(new Dimension(250,20));
		JLabel l_fecha_nacimiento = new JLabel("Fecha de nacimiento");
		tf_fecha_nacimiento = new JTextField();
		tf_fecha_nacimiento.setForeground(Color.gray);
		Font f6 = new Font("Italic", Font.ITALIC, 12);
		tf_fecha_nacimiento.setFont(f6);
		tf_fecha_nacimiento.setMaximumSize(new Dimension(250,20));
		JLabel l_jefe = new JLabel("Jefe");
		l_jefe.setForeground(Color.black);
		chb_jefe = new JCheckBox();
		panelEsteDatos.add(l_idDepartamento);
		panelEsteDatos.add(tf_idDepartamento);
		panelEsteDatos.add(Box.createRigidArea(new Dimension(0,10)));
		panelEsteDatos.add(l_idPuesto);
		panelEsteDatos.add(tf_idPuesto);
		panelEsteDatos.add(Box.createRigidArea(new Dimension(0,10)));
		panelEsteDatos.add(l_nombre);
		panelEsteDatos.add(tf_nombre);
		panelEsteDatos.add(Box.createRigidArea(new Dimension(0,10)));
		panelEsteDatos.add(l_apellidos);
		panelEsteDatos.add(tf_apellidos);
		panelEsteDatos.add(Box.createRigidArea(new Dimension(0,10)));
		panelEsteDatos.add(l_salario);
		panelEsteDatos.add(tf_salario);
		panelEsteDatos.add(Box.createRigidArea(new Dimension(0,10)));
		panelEsteDatos.add(l_fecha_nacimiento);
		panelEsteDatos.add(tf_fecha_nacimiento);
		panelEsteDatos.add(Box.createRigidArea(new Dimension(0,10)));
		panelEsteDatos.add(l_jefe);
		panelEsteDatos.add(chb_jefe);
		panelEsteDatos.setPreferredSize(new Dimension((int)(ancho*0.1), (int)(alto*0.6)));
		//panelEsteDatos.setBackground(Color.red);
		return panelEsteDatos;
	}
	
	public JPanel setPanelEsteControl(int alto, int ancho) {
		JPanel panelEsteControl = new JPanel();
		panelEsteControl.setLayout( new BoxLayout (panelEsteControl, BoxLayout.Y_AXIS));
		panelEsteControl.add(Box.createRigidArea(new Dimension(0,10)));
		panelEsteControl.setPreferredSize(new Dimension((int)(alto*0.09), (int)(ancho*0.01)));
		JButton botonConexion = new JButton("Ver");
		botonConexion.setForeground(Color.gray);
		panelEsteControl.add(botonConexion);
		JButton botonInsertar = new JButton("Insertar");
		botonInsertar.setForeground(Color.gray);
		panelEsteControl.add(botonInsertar);
		JButton botonBorrar = new JButton("Borrar");
		botonBorrar.setForeground(Color.gray);
		panelEsteControl.add(botonBorrar);
		JButton botonActualizar = new JButton("Actualizar");
		botonActualizar.setForeground(Color.gray);
		panelEsteControl.add(botonActualizar);
		JButton botonMusica = new JButton("Activar Musica");
		botonMusica.setForeground(Color.gray);
		panelEsteControl.add(botonMusica);
		
		
		
		JCheckBox chb_root = new JCheckBox("Inicio Sesion");
		chb_root.setForeground(Color.gray);
		chb_root.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(chb_root.isSelected()) {
					Dialog dialogo = new JDialog(new JFrame(), true);
					dialogo.setSize(new Dimension(350, 250));
					dialogo.setLocation((int)(ancho/2),(int)(alto/4));
					dialogo.setResizable(false);
					JPanel panelDialogo = new JPanel();
					
					panelDialogo.setLayout( new BoxLayout (panelDialogo, BoxLayout.X_AXIS));
					JLabel l_alias = new JLabel("Alias:");
					JTextField tf_alias = new JTextField();
					Font f1 = new Font("Garamond", Font.ITALIC, 12);
					tf_alias.setFont(f1);
					tf_alias.setMaximumSize(new Dimension(80,20));
					JLabel l_contrasenna = new JLabel("Contraseña:");
					JPasswordField tf_contrasenna = new JPasswordField();
					Font f = new Font("Italic", Font.ITALIC, 12);
					tf_contrasenna.setFont(f);
					tf_contrasenna.setMaximumSize(new Dimension(80,20));
				
	
					JButton b_inicio   = new JButton("Iniciar Sesion");
					Font f3 = new Font("Italic", Font.ITALIC, 12);
					b_inicio.setFont(f3);
					b_inicio.setMaximumSize(new Dimension(60,30));
					
				//colocar diseño de panel inicio/registro
					
					setVisible(true);
					
					
					JComboBox cb_grupos = new JComboBox();
					cb_grupos.addItem("Usuario");
					cb_grupos.addItem("1");
					cb_grupos.addItem("2");
					cb_grupos.addItem("3");
					cb_grupos.setMaximumSize(new Dimension(90,30));
					cb_grupos.setLocation(-100,20);
					panelDialogo.add(l_alias);
					panelDialogo.add(tf_alias);
					panelDialogo.add(l_contrasenna);
					panelDialogo.add(tf_contrasenna);
					panelDialogo.add(b_inicio);
					panelDialogo.add(Box.createRigidArea(new Dimension(0,10)));
					panelDialogo.add(cb_grupos);
					dialogo.add(panelDialogo);
					
					
					
					dialogo.setVisible(true);
				}
			}
			
		});
		panelEsteControl.add(botonInsertar);
		botonInsertar.addActionListener(new gestorInsertar());
		botonConexion.addActionListener(new gestorVer());
		//botonMusica.addActionListener(new gestorMusica());
		//botonActualizar.addActionListener(new gestorActualizar());
		botonBorrar.addActionListener(new gestorBorrar());
		panelEsteControl.add(chb_root);
		//devolvemos el panel de control
		return panelEsteControl;
		}
  	public JPanel creaPanelNorte(int alto, int ancho) {
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
	

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			borrarTabla();
			ResultSet rs =null;
			Empleado emp=null;
			Vector <Object>v = null;
			try {
			//	rs = new MisConexiones().dameResultSetSimple(ConfigDir.getInstance().getProperty("consultaJefes"));
				while(rs.next()) {
					
					emp.setId_puesto(rs.getInt("Id puesto"));
					emp.setId_departamento(rs.getInt("Id departamento"));
					emp.setNombre(rs.getString("Nombre"));
					emp.setApellido(rs.getString("Apellido"));
					emp.setFecha_nacimiento(rs.getTimestamp("Fecha"));
					
					emp.setSalario(rs.getDouble("Salario"));
					emp.setJefe(rs.getBoolean("Jefe"));
					v.addElement(emp.getId_puesto());
					v.addElement(emp.getId_departamento());
					v.addElement(emp.getNombre());
					v.addElement(emp.getApellido());
					v.addElement(emp.getSalario());
					v.addElement(new DameFecha().dameTime(emp.getFecha_nacimiento()));
					v.addElement(ConfigDir.getInstance().getReverso(emp.isJefe()));
					
					dtm.addRow(v);
				}
			}catch(Exception e1) {e1.printStackTrace();}
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
	
	public void borrarTabla() {
	// TODO Auto-generated method stub
	
}
	/*public JMenuBar setMenuBar(int alto, int ancho) {
		JMenuBar menuBar = new JMenuBar();
		menuBar.setPreferredSize(new Dimension(ancho, (int)(alto*0.045)));
		JMenu menu = new JMenu("Accesos Rápidos");
		JMenuItem miCalculadora = new JMenuItem("Calculadora");
		miCalculadora.setForeground(Color.gray);
		miCalculadora.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ejecutarComando("calc.exe");
				
			}

			private void ejecutarComando(String string) {
				// TODO Auto-generated method stub
				
			}
			
		});
		JMenuItem miNavegador = new JMenuItem("Navegador");
		miNavegador.setForeground(Color.gray);
		miNavegador.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ejecutarComando("MicrosoftEdge.exe","www.google.com");
			}

			private void ejecutarComando(String string, String string2) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		JMenuItem copiaBase = new JMenuItem("Base de datos");
		copiaBase.setForeground(Color.gray);
		copiaBase.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e)  {
				
				try {
					Process p = new ProcessBuilder("Explorer.exe", "/select,C:\\directory\\selectedFile").start();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		menu.add(miCalculadora);
		menu.add(miNavegador);
		menu.add(copiaBase);
		menuBar.add(menu);
		
		return menuBar;
	}*/
	
	public class gestorVer implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			refresh();
		}

		
		
	}
	
	public class gestorInsertar implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			  //empleado emp = new Empleado();
			//Emp.setNombre(txtNombre.getText());
			//addEmpleado(emp);
			//MisConexiones c = new MisConexiones();
			
				try {
					MisConexiones c = new MisConexiones();
					PreparedStatement ps = c.getPS(ConfigDir.getInstance().getProperty("query2"));
					ps.setInt(1, Integer.valueOf(tf_idDepartamento.getText()));
					ps.setInt(2, Integer.valueOf(tf_idPuesto.getText()));
					ps.setString(3, tf_nombre.getText());
					ps.setString(4, tf_apellidos.getText());
					ps.setDouble(5, Double.valueOf(tf_salario.getText()));
					ps.setTimestamp(6,Timestamp.valueOf(tf_fecha_nacimiento.getText()));
					ps.setBoolean(7, chb_jefe.isSelected());
					ps.executeUpdate();
					refresh();
				
				} catch (Exception e1) {
					e1.printStackTrace();
				} 
			
		}
	}
	public class gestorTabla implements MouseInputListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			int j = tabla.getSelectedRow();
			seleccionado = listaEmpleados.get(j);
			tf_idDepartamento.setText(""+seleccionado.getId_departamento());
			tf_idPuesto.setText(""+seleccionado.getId_puesto());
			tf_nombre.setText(seleccionado.getNombre());
			tf_apellidos.setText(seleccionado.getApellido());
			tf_salario.setText(String.valueOf(seleccionado.getSalario()));
			tf_fecha_nacimiento.setText(String.valueOf(fechaEsp(seleccionado.getFecha_nacimiento())));
			chb_jefe.setSelected(seleccionado.isJefe());
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
	
	
	public class gestorBorrar implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
        MisConexiones c1 = null;
        try {
			c1 = new MisConexiones();
			PreparedStatement ps = c1.getPS(ConfigDir.getInstance().getProperty("query3"));
			ps.setInt(1, seleccionado.getId());
			ps.executeUpdate();
			refresh();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
			
    }
		
    public class gestorActualizar implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			try {
				PreparedStatement ps = new MisConexiones().getPS(ConfigDir.getInstance().getProperty("query4"));
				ps.setInt(1, Integer.valueOf(tf_idDepartamento.getText()));
				ps.setInt(2, Integer.valueOf(tf_idPuesto.getText()));
				ps.setString(3, tf_nombre.getText());
				ps.setString(4, tf_apellidos.getText());
				ps.setDouble(5, Double.valueOf(tf_salario.getText()));
				ps.setTimestamp(6,Timestamp.valueOf(fechaIng(tf_fecha_nacimiento.getText())));
				ps.setBoolean(7, chb_jefe.isSelected());
				ps.setInt(8, seleccionado.getId());
				ps.executeUpdate();
				refresh();
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		}
    	
    }
	
	public String fechaIng(String fechahora) {
		String fechaIng ="", fecha="", tiempo="", anno="", mes="", dia="", hora="", minuto="", segundo="";
		StringTokenizer st = new StringTokenizer(fechahora.toString()," ");
		fecha = st.nextToken();
		tiempo = st.nextToken();
		st = new StringTokenizer(fecha.toString(),"-");
		dia=st.nextToken();
		mes=st.nextToken();
		anno=st.nextToken();
		st = new StringTokenizer(tiempo.toString(),":");
		hora=st.nextToken();
		minuto=st.nextToken();
		segundo=st.nextToken();
		//no modifico el orden del tiempo pero lo almaceno por si fuese necesario en el futuro
		fechaIng=anno+"-"+mes+"-"+dia+" "+tiempo;
		return fechaIng;
	}

	public String fechaEsp(Timestamp fechahora) {
		String fechaEsp ="", fecha="", tiempo="", anno="", mes="", dia="", hora="", minuto="", segundo="";
		StringTokenizer st = new StringTokenizer(fechahora.toString()," ");
		fecha = st.nextToken();
		tiempo = st.nextToken();
		st = new StringTokenizer(fecha.toString(),"-");
		anno=st.nextToken();
		mes=st.nextToken();
		dia=st.nextToken();
		st = new StringTokenizer(tiempo.toString(),":");
		hora=st.nextToken();
		minuto=st.nextToken();
		segundo=st.nextToken();
		//no modifico el orden del tiempo pero lo almaceno por si fuese necesario en el futuro
		fechaEsp=dia+"-"+mes+"-"+anno+" "+tiempo;
		return fechaEsp;
	}
	
	
	
	
	
	public String formateoBoolean(boolean boo) {
		String sino="";
		if(boo == true)sino ="Si";
		else sino="No";
		return sino;
	}
	
	public void refresh() {
		dtm.setRowCount(0);
		Empleado empleado;
        try {
    		MisConexiones c;
    		c = new MisConexiones();
    		listaEmpleados = new ArrayList<Empleado>();
        	ResultSet rs = c.getRS(ConfigDir.getInstance().getProperty("query1"));
			while(rs.next()) {
				empleado = new Empleado(rs.getInt("id"),rs.getNString("nombre"),rs.getNString("apellido"),rs.getTimestamp("fecha_nacimiento"),rs.getDouble("salario"),rs.getBoolean("jefe"),rs.getInt("idDepartamento"),rs.getInt("idPuesto"));
				ve  = new Vector();
				ve.addElement(empleado.getId_departamento());
				ve.addElement(empleado.getId_puesto());
				ve.addElement(empleado.getNombre());
				ve.addElement(empleado.getApellido());
				ve.addElement(empleado.getSalario());
				//listaEmpleado.addElement(fechaEsp(empleado.getFecha_nacimiento()));
				ve.addElement(empleado.getFecha_nacimiento());
				ve.addElement(formateoBoolean(empleado.isJefe()));
				dtm.addRow(ve);
				listaEmpleados.add(empleado);
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			System.out.println(e1.getMessage());
		}
	}
	
	
	public void ejecutarComando(String comando) throws IOException {
		String[] comandito = new String[] {comando};
		final Process proceso = Runtime.getRuntime().exec(comandito);
	}
	public void ejecutarComando(String comando1, String comando2) throws IOException {
		String[] comandito = new String[] {comando1,comando2};
		final Process proceso = Runtime.getRuntime().exec(comandito);
	}
	@Override
	public void addEmpleado(Empleado emp) throws SQLException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public List<Empleado> getAllEmpleados() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Empleado getbyId(int id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Empleado updateEmp(Empleado emp) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void deleteEmp(int id) throws SQLException {
		// TODO Auto-generated method stub
		
	}




}
	


	
	


