package com.rrhh.graficos;


import javax.swing.*;



import javax.swing.event.MouseInputListener;
import javax.swing.table.DefaultTableModel;


import com.rrhh.auxiliares.Auxiliar;
import com.rrhh.auxiliares.WordProcessing;
//import com.rrhh.auxiliares.CreaBackupTablas;
import com.rrhh.persistencia.ConfigDir;
import com.rrhh.persistencia.MisConexiones;
import com.rrhh.pojos.Empleado;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import java.util.Vector;
import java.awt.*;

public class PanelEmpleado<Reproductor> extends JPanel implements Servicios {

	Vector v;
	Empleado seleccionado;
	// Empleado emp;
	DefaultTableModel dtm;
	MisConexiones c;
	PreparedStatement ps;
	JTable tabla;
	JTextField tf_idDepartamento, tf_idPuesto, tf_nombre, tf_apellidos, tf_salario, tf_fecha_nacimiento, tf_grupo,tf_alias,tf_clave;
	public JButton botonVer, botonInsertar, botonBorrar, botonActualizar,botonInforme, botonAcceder, botonFiltrar, botonAlta,botonFuera;// cambiar botonConexion=botonVer
    JMenuItem miCalculadora, miNavegador, miCopiaBase, informes, jefe, salarios;
	JCheckBox chb_jefe, chb_root;
	List<Empleado> listaEmpleados;
	public JDialog dialogoinicial, dialogoUsuario;
	public JComboBox<Object> combo;
	public JPasswordField clave;
	public JTextField mialias;
	public JLabel etiqueta;
	public int grupo;
	protected double salario;

	public PanelEmpleado(int ancho, int alto) {
		// disposiciones de los objetos
		setLayout(new BorderLayout());
		add(setMenuBar(alto, ancho), BorderLayout.NORTH);
		add(setTabla(alto, ancho), BorderLayout.CENTER);
		add(setPanelEste(alto, ancho, setPanelEsteDatos(alto, ancho), setPanelEsteControl(ancho, alto)),
				BorderLayout.EAST);
		editHabCosas(0);
	}

	public JMenuBar setMenuBar(int alto, int ancho) {
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("Acessos Rápidos");

	   miCalculadora = new JMenuItem("Calculadora");
		miCalculadora.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					ejecutarComando("calc.exe");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		});
		 miNavegador = new JMenuItem("Navegador");
		miNavegador.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					ejecutarComando("MicrosoftEdge.exe");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		});
		 miCopiaBase = new JMenuItem("Backup");
		miCopiaBase.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
               JOptionPane.showMessageDialog(null, "Se realizara backup de la bbdd", "Backup", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("C:\\Users\\dam\\eclipse-workspace\\RRHH\\src\\img\\descarg"));
				try {
					creaBackupTablas();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}

			

		});
		
		JMenu busquedas = new JMenu("Buscar");
		 jefe = new JMenuItem("jefes");

		jefe.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(null, "Se mostran los empleados que son jefes", "Filtrar Jefes", JOptionPane.INFORMATION_MESSAGE);
				filtroJefe();
			}

		});

		 salarios = new JMenuItem("salario");
		salarios.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(null, "Se mostran los empleados con un salario mayor a 2400€, ", "Filtrar salario", JOptionPane.INFORMATION_MESSAGE);
				try {
					filtroSalario();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				}
	

		});

		busquedas.add(jefe);
		busquedas.add(salarios);
		menu.add(miNavegador);
		menu.add(miCalculadora);
		menu.add(miCopiaBase);
		menuBar.add(busquedas);
		menuBar.add(menu);
		setVisible(true);
		return menuBar;

	}

	





//clase para crear copia de la base de datos ejercicio regiones

	public void creaBackupTablas() throws IOException {
		File fbackup = new File("ejercicioregiones.sql");

		String[] command = new String[] { "cmd.exe", "/c",
				"mysqldump.exe --quick --lock-tables  --user=root  --password=root   ejercicioregiones" };

		final Process proceso = Runtime.getRuntime().exec(command);
		if (proceso != null) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						try (BufferedReader reader = new BufferedReader(
								new InputStreamReader(new DataInputStream(proceso.getInputStream())));
								BufferedWriter writer = new BufferedWriter(new FileWriter(fbackup))) {
							Object line;
							while ((line = reader.readLine()) != null) {
								writer.write(line + "\n");
								writer.newLine();
							}

						}

					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}).start();
		}
	}

	public JScrollPane setTabla(int alto, int ancho) {
		// objeto previo configurador
		dtm = new DefaultTableModel();
		// todos los datos que tendra
		dtm.addColumn("ID Departamento");
		dtm.addColumn("ID Puesto");
		dtm.addColumn("Nombre");
		dtm.addColumn("Apellidos");
		dtm.addColumn("Salario");
		dtm.addColumn("Fecha nacimiento");
		dtm.addColumn("Jefe");

		// se crea una tabla con la configuracion dtm que hemos creado
		tabla = new JTable(dtm);
		tabla.addMouseListener(new gestorTabla());
		// creamos una panel con scroll al que añadirle la tabla que acabamos de crear
		JScrollPane sp = new JScrollPane(tabla);
		// damos valores al tamaño del JScrollPane
		sp.setPreferredSize(new Dimension((int) (ancho * 0.8), (int) (alto * 1.2)));
		// devolvemos el panel scroll con todo lo que hemos creado dentro
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
		tf_idDepartamento.setMaximumSize(new Dimension(250, 20));
		JLabel l_idPuesto = new JLabel("idPuesto");
		tf_idPuesto = new JTextField();
		tf_idPuesto.setForeground(Color.gray);
		Font f2 = new Font("Italic", Font.ITALIC, 12);
		tf_idPuesto.setFont(f2);
		tf_idPuesto.setMaximumSize(new Dimension(250, 20));
		JLabel l_nombre = new JLabel("Nombre");
		tf_nombre = new JTextField();
		tf_nombre.setForeground(Color.gray);
		Font f3 = new Font("Italic", Font.ITALIC, 12);
		tf_nombre.setFont(f3);
		tf_nombre.setMaximumSize(new Dimension(250, 20));
		JLabel l_apellidos = new JLabel("Apellidos");
		tf_apellidos = new JTextField();
		tf_apellidos.setForeground(Color.gray);
		Font f4 = new Font("Italic", Font.ITALIC, 12);
		tf_apellidos.setFont(f4);
		tf_apellidos.setMaximumSize(new Dimension(250, 20));
		JLabel l_salario = new JLabel("Salario");
		tf_salario = new JTextField();
		tf_salario.setForeground(Color.gray);
		Font f5 = new Font("Italic", Font.ITALIC, 12);
		tf_salario.setFont(f5);
		tf_salario.setMaximumSize(new Dimension(250, 20));
		JLabel l_fecha_nacimiento = new JLabel("Fecha de nacimiento");
		tf_fecha_nacimiento = new JTextField();
		tf_fecha_nacimiento.setForeground(Color.gray);
		Font f6 = new Font("Italic", Font.ITALIC, 12);
		tf_fecha_nacimiento.setFont(f6);
		tf_fecha_nacimiento.setMaximumSize(new Dimension(250, 20));
		JLabel l_jefe = new JLabel("Jefe");
		l_jefe.setForeground(Color.black);
		chb_jefe = new JCheckBox();
		JLabel l_europa = new JLabel("Jefe");
		panelEsteDatos.add(l_idDepartamento);
		panelEsteDatos.add(tf_idDepartamento);
		panelEsteDatos.add(Box.createRigidArea(new Dimension(0, 10)));
		panelEsteDatos.add(l_idPuesto);
		panelEsteDatos.add(tf_idPuesto);
		panelEsteDatos.add(Box.createRigidArea(new Dimension(0, 10)));
		panelEsteDatos.add(l_nombre);
		panelEsteDatos.add(tf_nombre);
		panelEsteDatos.add(Box.createRigidArea(new Dimension(0, 10)));
		panelEsteDatos.add(l_apellidos);
		panelEsteDatos.add(tf_apellidos);
		panelEsteDatos.add(Box.createRigidArea(new Dimension(0, 10)));
		panelEsteDatos.add(l_salario);
		panelEsteDatos.add(tf_salario);
		panelEsteDatos.add(Box.createRigidArea(new Dimension(0, 10)));
		panelEsteDatos.add(l_fecha_nacimiento);
		panelEsteDatos.add(tf_fecha_nacimiento);
		panelEsteDatos.add(Box.createRigidArea(new Dimension(0, 10)));
		panelEsteDatos.add(l_jefe);
		panelEsteDatos.add(chb_jefe);
		panelEsteDatos.setPreferredSize(new Dimension((int) (ancho * 0.1), (int) (alto * 1.1)));

		return panelEsteDatos;
	}

	public JPanel setPanelEsteControl(int alto, int ancho) {
		JPanel panelEsteControl = new JPanel();
		panelEsteControl.setLayout(new BoxLayout(panelEsteControl, BoxLayout.Y_AXIS));
		panelEsteControl.add(Box.createRigidArea(new Dimension(0, 5)));
		panelEsteControl.setPreferredSize(new Dimension((int) (alto * 0.01), (int) (ancho * 0.01)));
		botonVer = new JButton("Ver");
		botonVer.setForeground(Color.blue);
		panelEsteControl.add(botonVer);
		panelEsteControl.add(Box.createRigidArea(new Dimension(0, 05)));
		botonInsertar = new JButton("Insertar");
		botonInsertar.setForeground(Color.blue);
		panelEsteControl.add(botonInsertar);
		panelEsteControl.add(Box.createRigidArea(new Dimension(0, 05)));
		botonBorrar = new JButton("Borrar");
		botonBorrar.setForeground(Color.blue);
		panelEsteControl.add(botonBorrar);
		panelEsteControl.add(Box.createRigidArea(new Dimension(0, 05)));
		botonActualizar = new JButton("Actualizar");
		botonActualizar.setForeground(Color.blue);
		panelEsteControl.add(botonActualizar);
		panelEsteControl.add(Box.createRigidArea(new Dimension(0, 05)));
		botonInforme = new JButton("Informe");
		botonInforme.setForeground(Color.blue);
		panelEsteControl.add(botonInforme);
		panelEsteControl.add(Box.createRigidArea(new Dimension(0, 05)));

		chb_root = new JCheckBox("InicioSesion");
		chb_root.setForeground(Color.BLUE);
		chb_root.addActionListener(new gestorEdicion());

		botonInsertar.addActionListener(new gestorInsertar());
		botonVer.addActionListener(new gestorVer());
		botonActualizar.addActionListener(new gestorActualizar());
		botonInforme.addActionListener(new gestorInforme());
		botonBorrar.addActionListener(new gestorBorrar());
		panelEsteControl.add(chb_root);
		// devolvemos el panel de control
		return panelEsteControl;
	}
	

	public JPanel setPanelEste(int alto, int ancho, JPanel p1, JPanel p2) {
		JPanel panelEste = new JPanel();
		panelEste.setLayout(new BorderLayout());
		panelEste.setPreferredSize(new Dimension((int) (ancho * 0.15), (int) (alto * 0.8)));
		panelEste.add(p1, BorderLayout.NORTH);
		panelEste.add(p2, BorderLayout.CENTER);
		return panelEste;
	}

	public void borrarTabla() throws SQLException {
		// TODO Auto-generated method stub

		try {
			int numRows = tabla.getRowCount();
			do {
				numRows--;
				deleteEmp(numRows);
			} while (numRows >= 0);
		} catch (ArrayIndexOutOfBoundsException aiob) {
			System.out.println("en borrar tabla " + aiob.getMessage());
		}

	}

	public class gestorVer implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			refresh();
		}

	}

	public class gestorInsertar implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			try {
				 c = new MisConexiones();
				PreparedStatement ps = c.getPS(ConfigDir.getInstance().getProperty("query2"));
				ps.setInt(1, Integer.valueOf(tf_idDepartamento.getText()));
				ps.setInt(2, Integer.valueOf(tf_idPuesto.getText()));
				ps.setString(3, tf_nombre.getText());
				ps.setString(4, tf_apellidos.getText());
				ps.setDouble(5, Double.valueOf(tf_salario.getText()));
				ps.setTimestamp(6, Timestamp.valueOf(tf_fecha_nacimiento.getText()));
				ps.setBoolean(7, chb_jefe.isSelected());
				ps.executeUpdate();
				refresh();

			} catch (Exception e1) {
				e1.printStackTrace();
			}

		}
	}

	public class gestorTabla implements MouseInputListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			int j = tabla.getSelectedRow();
			seleccionado = listaEmpleados.get(j);
			tf_idDepartamento.setText("" + seleccionado.getId_departamento());
			tf_idPuesto.setText("" + seleccionado.getId_puesto());
			tf_nombre.setText(seleccionado.getNombre());
			tf_apellidos.setText(seleccionado.getApellido());
			tf_salario.setText("" + seleccionado.getSalario());
			tf_fecha_nacimiento.setText("" + seleccionado.getFecha_nacimiento());
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
	
	public class gestorInforme implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			WordProcessing();
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
					JOptionPane.WARNING_MESSAGE/* El tipo de ventana, en este caso WARNING */, new ImageIcon(""));
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
				
				try {
					ps = c1.getPS(ConfigDir.getInstance().getProperty("query3"));
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
				PreparedStatement ps = new MisConexiones().getPS(ConfigDir.getInstance().getProperty("query4"));
				ps.setInt(1, Integer.valueOf(tf_idDepartamento.getText()));
				ps.setInt(2, Integer.valueOf(tf_idPuesto.getText()));
				ps.setString(3, tf_nombre.getText());
				ps.setString(4, tf_apellidos.getText());
				ps.setDouble(5, Double.valueOf(tf_salario.getText()));
				ps.setTimestamp(6, Timestamp.valueOf(tf_fecha_nacimiento.getText()));
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

	
	public void WordProcessing() {
		// TODO Auto-generated method stub
		gestorTabla();
		
		WordProcessing.createNewDocumentFromTemplate("info_empleado");
		WordProcessing.typeTextAtBookmark("jefe", chb_jefe);
		WordProcessing.exec();
		try {
			Process p = new ProcessBuilder("explorer.exe","/select","C:\\Users\\dam\\AppData\\Roaming\\Microsoft\\Templates").start();
			
		  }catch(IOException e) {e.printStackTrace();}
		}
	

	private void gestorTabla() {
		// TODO Auto-generated method stub
		
	}

	public String fechaEsp(Timestamp fechahora) {
		String fechaEsp = "", fecha = "", tiempo = "", dia="",mes="", anno="", hora = "", minuto = "",
				segundo = "";
		StringTokenizer st = new StringTokenizer(fechahora.toString(), " ");
		fecha = st.nextToken();
		tiempo = st.nextToken();
		st = new StringTokenizer(fecha.toString(), "-");
		anno = st.nextToken();
		mes = st.nextToken();
		dia = st.nextToken();
		st = new StringTokenizer(tiempo.toString(), ":");
		hora = st.nextToken();
		minuto = st.nextToken();
		segundo = st.nextToken();
		// no modifico el orden del tiempo pero lo almaceno por si fuese necesario en el
		// futuro
		fechaEsp = dia + "-" + mes + "-" + anno + " " + tiempo;
		return fechaEsp;
	}

	public String formateoBoolean(boolean boo) {
		String sino = "";
		if (boo == true)
			sino = "Si";
		else
			sino = "No";
		return sino;
	}

	public void refresh() {

		dtm.setRowCount(0);
		Empleado empleado;
		try {
		
			c = new MisConexiones();
			listaEmpleados = new ArrayList<Empleado>();
			ResultSet rs = c.getRS(ConfigDir.getInstance().getProperty("query1"));
			while (rs.next()) {
				empleado = new Empleado(rs.getInt("id"), rs.getNString("nombre"), rs.getNString("apellido"),
						rs.getTimestamp("fecha_nacimiento"), rs.getDouble("salario"), rs.getBoolean("jefe"),
						rs.getInt("idDepartamento"), rs.getInt("idPuesto"));
				v = new Vector();
				v.addElement(empleado.getId_departamento());
				v.addElement(empleado.getId_puesto());
				v.addElement(empleado.getNombre());
				v.addElement(empleado.getApellido());
				v.addElement(empleado.getSalario());
				// listaEmpleado.addElement(fechaEsp(empleado.getFecha_nacimiento()));
				v.addElement(empleado.getFecha_nacimiento());
				v.addElement(formateoBoolean(empleado.isJefe()));
				dtm.addRow(v);
				listaEmpleados.add(empleado);
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			System.out.println(e1.getMessage());
		}
	}

	

	public void ejecutarComando(String comando) throws IOException {
		String[] comandito = new String[] { comando };
		final Process proceso = Runtime.getRuntime().exec(comandito);
	}

	public void ejecutarComando(String comando1, String comando2) throws IOException {
		String[] comandito = new String[] { comando1, comando2 };
		final Process proceso = Runtime.getRuntime().exec(comandito);
	}

// private MiPractica veamos;
	public class gestorEdicion implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			if (chb_root.isSelected()) {

				dialogoinicial = new JDialog(new JFrame(), true);
				dialogoinicial.setResizable(false);
				dialogoinicial.setBackground(new Color(206, 238, 244));
				dialogoinicial.setForeground(new Color(206, 237, 244));
				dialogoinicial.setSize(250, 250);
				dialogoinicial.setMinimumSize(new Dimension(250, 250));
				dialogoinicial.setLocation(250, 250);
				dialogoinicial.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				mialias = new JTextField(16);
				clave = new JPasswordField(16);
				dialogoinicial.setTitle("VALIDACION USUARIOS");
				combo = new JComboBox<Object>();
				combo.addItem(dameObjeto("1"));
				combo.addItem(dameObjeto("2"));
				combo.addItem(dameObjeto("3"));
				botonAcceder = new JButton("Acceder");

				botonAcceder.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						try {

							PreparedStatement ps = new MisConexiones()
									.getPS(ConfigDir.getInstance().getProperty("validacionUsu"));
							ps.setString(1, mialias.getText());
							ps.setString(2, Auxiliar.dameContrasenna(clave.getPassword()));
							ps.setInt(3, combo.getSelectedIndex() + 1);
							ResultSet rs = ps.executeQuery();
							if (rs.next()) {
								editHabCosas(rs.getInt("grupo"));
								dialogoinicial.dispose();
							} else {
								JOptionPane.showMessageDialog(null, "Ese usuario no existe");
							}

						} catch (Exception e1) {
							e1.printStackTrace();
						}

					}

				});

				botonAcceder.setForeground(Color.pink);

				dialogoinicial.add(botonAcceder);
				dialogoinicial.add(mialias);
				dialogoinicial.add(clave);
				dialogoinicial.add(combo);

				JPanel panelentrada = new JPanel();
				panelentrada.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 10));

				panelentrada.add(new JLabel("Introduzca su  alias"));
				panelentrada.add(mialias);
				panelentrada.add(new JLabel("Introduzca su contraseña"));
				panelentrada.add(clave);
				panelentrada.add(new JLabel("    GRUPOS"));
				panelentrada.add(combo);
				panelentrada.add(new JLabel("  copyright by Clara"));

				panelentrada.add(botonAcceder);

				panelentrada.setSize(250, 250);
				panelentrada.setBackground(new Color(209, 222, 244));
				panelentrada.setForeground(new Color(209, 222, 224));
				dialogoinicial.add(panelentrada);
				dialogoinicial.setVisible(true);
				panelentrada.setVisible(true);

			} else {
				chb_root.setSelected(false);
				editHabCosas(0);
			}
		}

	}

	private Object dameObjeto(final String item) {
		return new Object() {
			public String toString() {
				return item;
			}
		};
	}

	public void editHabCosas(int grado) {
		switch (grado) {
		case 1:
			editHabCosas(0);
			refresh();
		
			int n = JOptionPane.showConfirmDialog(new JDialog(), "Desee dar de alta alguno?", "Usuarios",
					JOptionPane.YES_NO_OPTION, 0, new ImageIcon("C:\\\\Users\\dam\\eclipse-workspace\\RRHH\\src\\img\\adduser.png"));
			if (n == JOptionPane.YES_OPTION) {
				// veamos = new MiPractica();
				//hacer dar de alta a nuevo usuaerio
				//dialogoinicial.dispose();
				altaUsuario();
			}

			else if (n == JOptionPane.NO_OPTION) {
				dialogoinicial.dispose();
			}
			System.out.println("Administrador");
			botonVer.setEnabled(true);
			botonInsertar.setEnabled(true);
			botonBorrar.setEnabled(true);
			botonInforme.setEnabled(true);
			botonActualizar.setEnabled(true);
			tf_idDepartamento.setEditable(true);
			tf_idPuesto.setEditable(true);
			tf_nombre.setEditable(true);
			tf_apellidos.setEditable(true);
			tf_salario.setEditable(true);
			tf_fecha_nacimiento.setEditable(true);
			miCalculadora.setEnabled(true);
			miNavegador.setEnabled(true);
			miCopiaBase.setEnabled(true);
			jefe.setEnabled(true);
			salarios.setEnabled(true);
			
			

			break;
		case 2:
			editHabCosas(0);
			refresh();

			System.out.println("Avanzado");
			botonVer.setEnabled(true);
			botonInsertar.setEnabled(true);
			

			break;
		case 3:
			editHabCosas(0);
			refresh();
			botonVer.setEnabled(true);
			System.out.println("Usuario basico");
			break;
		case 0:
			dtm.setRowCount(0);
			botonVer.setEnabled(false);
			botonInsertar.setEnabled(false);
			botonInforme.setEnabled(false);
			botonBorrar.setEnabled(false);
			botonActualizar.setEnabled(false);
			tf_idDepartamento.setEditable(false);
			tf_idPuesto.setEditable(false);
			tf_nombre.setEditable(false);
			tf_apellidos.setEditable(false);
			tf_salario.setEditable(false);
			tf_fecha_nacimiento.setEditable(false);
			miCalculadora.setEnabled(false);
			miNavegador.setEnabled(false);
			miCopiaBase.setEnabled(false);
			jefe.setEnabled(false);
			salarios.setEnabled(false);
			break;

		}

	}
	
   public void altaUsuario() {
		// TODO Auto-generated method stub
		
		dialogoUsuario = new JDialog(new JFrame());
		dialogoUsuario.setResizable(true);
		dialogoUsuario.setBackground(Color.CYAN);
		dialogoUsuario.setForeground(Color.PINK);
		dialogoUsuario.setSize(250, 250);
		dialogoUsuario.setMinimumSize(new Dimension(250, 250));
		dialogoUsuario.setLocation(250, 250);
		dialogoUsuario.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		mialias = new JTextField(16);
		clave = new JPasswordField(16);
		dialogoUsuario.setTitle("ALTA USUARIOS");
		combo = new JComboBox<Object>();
		combo.addItem(dameObjeto("1"));
		combo.addItem(dameObjeto("2"));
		combo.addItem(dameObjeto("3"));
		botonAlta = new JButton("Dar de Alta");
		botonFuera = new JButton("Salir");
	    botonAlta.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					c = new MisConexiones();
					PreparedStatement ps = c.getPS(ConfigDir.getInstance().getProperty("altaUsu"));
					ps.setString(1, mialias.getText());
					ps.setString(2, Auxiliar.dameContrasenna(clave.getPassword()));
					ps.setInt(3, combo.getSelectedIndex() + 1);
					ps.execute();
					
					refresh();
					 
					
				}catch(Exception e1) {e1.printStackTrace();}
			}
	    	
	    });
	    
	    botonFuera.addActionListener(new ActionListener() {
	    	@Override
	    	public void actionPerformed(ActionEvent e) {
	    		
	    		dialogoUsuario.dispose();
	    	}
	    });
	    
	    
	    dialogoUsuario.add(mialias);
	    dialogoUsuario.add(clave);
	    dialogoUsuario.add(combo);
	    dialogoUsuario.add(botonAlta);
	    dialogoUsuario.add(botonFuera);
	    
	    JPanel panel = new JPanel();
	    panel.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 10));

	    panel.add(new JLabel("Introduzca su  alias"));
	    panel.add(mialias);
	    panel.add(new JLabel("Introduzca su contraseña"));
		panel.add(clave);
		panel.add(new JLabel(" GRUPOS"));
		panel.add(combo);
		panel.add(new JLabel("  copyright by Clara"));

		panel.add(botonAlta);
		panel.add(botonFuera);

		panel.setSize(250, 250);
		panel.setBackground(new Color(209, 222, 244));
		dialogoUsuario.add(panel);
		dialogoUsuario.setVisible(true);
		panel.setVisible(true);
	    
	    
	    
	}

	protected void filtroSalario() throws SQLException  {
		// TODO Auto-generated method stub
		
		
		


		dtm.setRowCount(0);
		Empleado empleado;
		try {
		
			c = new MisConexiones();
			listaEmpleados = new ArrayList<Empleado>();
			ResultSet rs = c.getRS(ConfigDir.getInstance().getProperty("filtroSalario"));
			while (rs.next()) {
				empleado = new Empleado(rs.getInt("id"), rs.getNString("nombre"), rs.getNString("apellido"),
						rs.getTimestamp("fecha_nacimiento"), rs.getDouble("salario"), rs.getBoolean("jefe"),
						rs.getInt("idDepartamento"), rs.getInt("idPuesto"));
				v = new Vector();
				v.addElement(empleado.getId_departamento());
				v.addElement(empleado.getId_puesto());
				v.addElement(empleado.getNombre());
				v.addElement(empleado.getApellido());
				v.addElement(empleado.getSalario());
				// listaEmpleado.addElement(fechaEsp(empleado.getFecha_nacimiento()));
				v.addElement(empleado.getFecha_nacimiento());
				v.addElement(formateoBoolean(empleado.isJefe()));
				dtm.addRow(v);
				listaEmpleados.add(empleado);
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			System.out.println(e1.getMessage());
		}}
		
	
	
	
	protected void filtroJefe() {
	            //  Icon c = new ImageIcon(getClass().getResource("C:\\Users\\dam\\eclipse-workspace\\RRHH\\src\\img\\jefe.jpg"));
				// TODO Auto-generated method stub
		dtm.setRowCount(0);
		Empleado empleado;
		try {
		
			c = new MisConexiones();
			listaEmpleados = new ArrayList<Empleado>();
			ResultSet rs = c.getRS(ConfigDir.getInstance().getProperty("filtroJefe"));
			while (rs.next()) {
				empleado = new Empleado(rs.getInt("id"), rs.getNString("nombre"), rs.getNString("apellido"),
						rs.getTimestamp("fecha_nacimiento"), rs.getDouble("salario"), rs.getBoolean("jefe"),
						rs.getInt("idDepartamento"), rs.getInt("idPuesto"));
				v = new Vector();
				v.addElement(empleado.getId_departamento());
				v.addElement(empleado.getId_puesto());
				v.addElement(empleado.getNombre());
				v.addElement(empleado.getApellido());
				v.addElement(empleado.getSalario());
				// listaEmpleado.addElement(fechaEsp(empleado.getFecha_nacimiento()));
				v.addElement(empleado.getFecha_nacimiento());
				v.addElement(formateoBoolean(empleado.isJefe()));
				dtm.addRow(v);
				listaEmpleados.add(empleado);
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			System.out.println(e1.getMessage());
		}
				
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
