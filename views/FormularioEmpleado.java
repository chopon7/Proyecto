package views;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import utils.AppFont;

public class FormularioEmpleado extends JFrame {

	private JLabel lblErrorNombre;
	private JLabel lblErrorComboEdad;
	private JLabel lblErrorCodigo;
	private JLabel lblErrorEmail;
	private JLabel lblErrorCombo;
	private JLabel lblErrorApellidoPaterno;
	private JLabel lblErrorApellidoMaterno;
	private JTextField nombre;
	private JTextField apellidoPaterno;
	private JTextField apellidoMaterno;
	private JTextField codigo;
	private JTextField email;
	private JComboBox<String> cboOpcionesEdad;
	private JComboBox<String> cboOpcionesGenero;
	private Color botonColorNormal;
	
	public FormularioEmpleado() {

		setSize(1000, 700);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setResizable(true);
		setTitle("Formulario de Registro");
		setLocationRelativeTo(null);

		Toolkit tk = Toolkit.getDefaultToolkit();
		Image icono = tk.getImage("img/imagenVehiculo.png");
		setIconImage(icono);

		inicializarComponentes();

		setVisible(true);	
		
		
	}

	public void inicializarComponentes() {

		addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				System.out.println("Se abrió la ventana");
				
			}
			
			@Override
			public void windowIconified(WindowEvent e) {
				System.out.println("Se minimizó");
				
			}
			
			@Override
			public void windowDeiconified(WindowEvent e) {
				System.out.println("Se volvió a abrir");
				
			}
			
			@Override
			public void windowDeactivated(WindowEvent e) {
				System.out.println("Perdió el focus");
				
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
				handleClose();
				
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				System.out.println("Se cerró");
				
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
				System.out.println("Obtuvo el focus");
				
			}
		});
		
		
		// Titulo de la ventana
		JLabel lblTitulo = new JLabel("Registro de Empleados");
		lblTitulo.setFont(AppFont.title());
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblTitulo, BorderLayout.NORTH);

		// GridBagPanel
		JPanel panelComponentes = new JPanel(new GridBagLayout());
		panelComponentes.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
		panelComponentes.setBackground(new Color(27, 38, 59));
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new java.awt.Insets(10, 10, 10, 10);
		c.fill = GridBagConstraints.HORIZONTAL;

		// Nombre del empleado 
		c.gridx = 0;
		c.gridy = 0;
		panelComponentes.add(crearLabel("Ingresa el nombre del empleado:"), c);
		c.gridx = 1;
		nombre =  new JTextField(20);
		validarIngresoDatos(nombre);
		panelComponentes.add(nombre, c);
		c.gridy = 1;
		lblErrorNombre = crearMensajeError("");
		crearMensajeError(lblErrorNombre.getText());
		panelComponentes.add(lblErrorNombre, c);
		
		// Apellido paterno del empleado
		c.gridx = 0;
		c.gridy = 2;
		panelComponentes.add(crearLabel("Ingresa el apellido paterno del empleado: "), c);
		c.gridx = 1;
		apellidoPaterno = new JTextField(20);
		validarIngresoDatos(apellidoPaterno);
		panelComponentes.add(apellidoPaterno, c);
		c.gridy = 3;
		lblErrorApellidoPaterno = crearMensajeError("");
		crearMensajeError(lblErrorApellidoPaterno.getText());
		panelComponentes.add(lblErrorApellidoPaterno, c);
		
		// Apellido materno del empleado
		c.gridx = 0;
		c.gridy = 4;
		panelComponentes.add(crearLabel("Ingresa el apellido materno del empleado: "), c);
		c.gridx = 1;
		apellidoMaterno = new JTextField(20);
		validarIngresoDatos(apellidoMaterno);
		panelComponentes.add(apellidoMaterno, c);
		c.gridy = 5;
		lblErrorApellidoMaterno = crearMensajeError("");
		crearMensajeError(lblErrorApellidoMaterno.getText());
		panelComponentes.add(lblErrorApellidoMaterno, c);

		// Email del empleado
		c.gridx = 0;
		c.gridy = 6;
		panelComponentes.add(crearLabel("Ingresa el email del empleado: "), c);
		c.gridx = 1;
		email = new JTextField(20);
		panelComponentes.add(email, c);
		lblErrorEmail = crearMensajeError("");
		c.gridy = 7;
		panelComponentes.add(lblErrorEmail, c);
		
		// Codigo unico del empleado
		c.gridx = 0;
		c.gridy = 8;
		panelComponentes.add(crearLabel("Ingresa la contraseña del empleado:"), c);
		c.gridx = 1;
		codigo = new JTextField(20);
		panelComponentes.add(codigo, c);
		c.gridy = 9;
		lblErrorCodigo = crearMensajeError("");
		crearMensajeError(lblErrorCodigo.getText());
		panelComponentes.add(lblErrorCodigo, c);
		
		// Edad del empleado
		c.gridx = 0;
		c.gridy = 10;
		panelComponentes.add(crearLabel("Ingresa la edad del empleado:"), c);
		c.gridx = 1;
		String[] opcionesEdad = {"Seleccione", "18", "19", "20",
				"21", "22", "23", "24", "25", "26", "27", "28", "29", "30",
				"31", "32", "33", "34", "35", "36", "37", "38", "39", "40",
				"41", "42", "43", "44", "45", "46", "47", "48", "49", "50",
				"51", "52", "53", "54", "55", "56", "57", "58", "59", "60",
				"61", "62", "63", "64", "65", "66", "67", "68", "69", "70",
				"71", "72", "73", "74", "75", "76", "77", "78", "79", "80",
				"81", "82", "83", "84", "85", "86", "87", "88", "89", "90", 
				"91", "92", "93", "94", "95", "96", "97", "98", "99", "100"};
		cboOpcionesEdad = new JComboBox<>(opcionesEdad);
		panelComponentes.add(cboOpcionesEdad, c);
		lblErrorComboEdad = crearMensajeError("");
		c.gridy = 11;
		crearMensajeError(lblErrorComboEdad.getText());
		panelComponentes.add(lblErrorComboEdad, c);
		
		// Genero del empleado
		c.gridx = 0;
		c.gridy = 12;
		panelComponentes.add(crearLabel("Ingresa el genero del empleado:"), c);
		c.gridx = 1;
		String[] opcionesGenero = {"Seleccione" ,"Masculino", "Femenino", "Prefiero no decirlo" };
		cboOpcionesGenero = new JComboBox<>(opcionesGenero);
		panelComponentes.add(cboOpcionesGenero, c);
		c.gridy = 13;
		lblErrorCombo = crearMensajeError("");
		crearMensajeError(lblErrorCombo.getText());
		panelComponentes.add(lblErrorCombo, c);
		

		// Boton de registro
		c.gridx = 0;
		c.gridy = 14;
		c.insets = new java.awt.Insets(20, 10, 10, 10);
		ImageIcon ilustracionBotonEnviar = new ImageIcon("img/enviar.png");
		Image imagenActualizadaBotonEnviar = ilustracionBotonEnviar.getImage().getScaledInstance(18, 18, Image.SCALE_SMOOTH);
		ImageIcon iconoFinalBotonEnviar = new ImageIcon(imagenActualizadaBotonEnviar);
		JButton enviarRegistro = new JButton("Enviar", iconoFinalBotonEnviar);
		botonColorNormal = enviarRegistro.getBackground();
		asignarOyenteMouse(enviarRegistro);
		enviarRegistro.addActionListener(e -> validar());
		panelComponentes.add(enviarRegistro, c);

		// Boton atras
		c.gridx = 1;
		c.gridy = 14;	
		ImageIcon ilustracionBotonAtras = new ImageIcon("img/atras.png");
		Image imagenActualizadaBotonAtras = ilustracionBotonAtras.getImage().getScaledInstance(18, 18, Image.SCALE_SMOOTH);
		ImageIcon iconoFinalBotonAtras = new ImageIcon(imagenActualizadaBotonAtras);
		
		JButton botonAtras = new JButton("Atras", iconoFinalBotonAtras);
		asignarOyenteMouse(botonAtras);
		botonAtras.addActionListener(e -> {
			
			int option = JOptionPane.showConfirmDialog(this, "¿Seguro que deseas regresar? Se perderán todos los datos");
			
			if(option == JOptionPane.YES_OPTION) {
				new Ventana();
				dispose();
			}
			
		});
		panelComponentes.add(botonAtras, c);

		// Scroll
		JScrollPane scroll = new JScrollPane(panelComponentes);
		add(scroll, BorderLayout.CENTER);
		botonColorNormal = botonAtras.getBackground();
		
		asignarOyentes();
		
	}

	private JLabel crearLabel(String texto) {
		JLabel label = new JLabel(texto);
		label.setFont(AppFont.normal());
		label.setForeground(Color.WHITE);
		return label;
	}

	private void reiniciarMensajeError() {
		lblErrorNombre.setText("");
		lblErrorComboEdad.setText("");
		lblErrorCodigo.setText("");
		lblErrorEmail.setText("");
		lblErrorCombo.setText("");
		lblErrorApellidoMaterno.setText("");
		lblErrorApellidoPaterno.setText("");		
	}
	
	private void validar() {
		reiniciarMensajeError();

		boolean validar = true;

		if (!validarNombre())
			validar = false;

		if (!validarCodigo())
			validar = false;

		if (!validarComboBoxGenero())
			validar = false;

		if (!validarComboBoxEdad())
			validar = false;

		if (!validarEmail())
			validar = false;
		
		if (!validarApellidoMaterno())
			validar = false;
		
		if (!validarApellidoPaterno())
			validar = false;

		if (validar) {
			JOptionPane.showMessageDialog(this, "Registro exitoso");
			new Ventana();
			dispose();
		}
		
		if (!validar) {
			JOptionPane.showMessageDialog(this, "No se han completado los campos solicitados", "Error", JOptionPane.ERROR_MESSAGE);
			return; 
		}
		
		return;	
	}
	
	private JLabel crearMensajeError(String mensaje) {
		JLabel label = new JLabel();
		label.setText(mensaje);
		label.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		label.setForeground(Color.RED);
		label.setHorizontalAlignment(SwingConstants.LEFT);
		label.setMaximumSize(new Dimension(Integer.MAX_VALUE, label.getPreferredSize().height));

		return label;
	}
	
	private void asignarOyentes() {
		
		agregarListener(nombre);
		agregarListener(codigo);
		agregarListener(email);
		agregarListener(apellidoMaterno);
		agregarListener(apellidoPaterno);
		
		asignarOyenteCampotexto(nombre);
		asignarOyenteCampotexto(apellidoPaterno);
		asignarOyenteCampotexto(apellidoMaterno);
		asignarOyenteCampotexto(codigo);
		asignarOyenteCampotexto(email);
		
		asignarFocusCampoTexto(nombre);
		asignarFocusCampoTexto(apellidoPaterno);
		asignarFocusCampoTexto(apellidoMaterno);
		asignarFocusCampoTexto(codigo);
		asignarFocusCampoTexto(email);
		
		cboOpcionesGenero.addActionListener(e -> {
			validarComboBoxGenero();
		});
		
		cboOpcionesEdad.addActionListener(e -> {
			validarComboBoxEdad();
		});
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				cboOpcionesGenero.requestFocusInWindow();
			}
		});
		
	}
	
	//Cree el metodo que agrega el listener al JTextField
	private void agregarListener(JTextField campo) {
		
		campo.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				validarCampo(campo);
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				validarCampo(campo);
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				validarCampo(campo);
			}
		});
	}
	
	//Este metodo revisa que campo fue modificado y llama a su validacion
	private void validarCampo(JTextField campo) {
		
		if(campo == nombre) {
			validarNombre();
		}
		
		if(campo == codigo) {
			validarCodigo();
		}
		
		if(campo == email) {
			validarEmail();
		}
		
		if(campo == apellidoMaterno) {
			validarApellidoMaterno();
		}
		
		if(campo == apellidoPaterno) {
			validarApellidoPaterno();
		}
		
	}
	
	private boolean validarNombre() {

		if (nombre.getText().trim().isEmpty()) {
			lblErrorNombre.setText("El nombre es obligatorio");
			return false;
		}

		
		lblErrorNombre.setText("");
		return true;
	}
	
	private boolean validarApellidoPaterno() {

		if (apellidoPaterno.getText().trim().isEmpty()) {
			lblErrorApellidoPaterno.setText("El apellido paterno es obligatorio");
			return false;
		}

		
		lblErrorApellidoPaterno.setText("");
		return true;
	}
	
	private boolean validarApellidoMaterno() {

		if (apellidoMaterno.getText().trim().isEmpty()) {
			lblErrorApellidoMaterno.setText("El apellido materno es obligatorio");
			return false;
		}

		
		lblErrorApellidoMaterno.setText("");
		return true;
	}
	
	
	
	private boolean validarCodigo() {

		if (codigo.getText().trim().isEmpty()) {
			lblErrorCodigo.setText("La contraseña es obligatoria");
			return false;
		}

		lblErrorCodigo.setText("");
		return true;
	}
	
	private boolean validarEmail() {

		if (email.getText().trim().isEmpty()) {
			lblErrorEmail.setText("El email es obligatorio");
			return false;
		}
		
		if (!email.getText().contains("@")) {
			lblErrorEmail.setText("Email inválido");
			return false;
		}

		lblErrorEmail.setText("");
		return true;
	}

	private boolean validarComboBoxGenero() {
		
		
		if (cboOpcionesGenero.getSelectedIndex() == 0) {
			lblErrorCombo.setText("Seleccione un genero");
			return false;
		}

		lblErrorCombo.setText("");
		return true;
	}
	
	private boolean validarComboBoxEdad() {
		
		
		if (cboOpcionesEdad.getSelectedIndex() == 0) {
			lblErrorComboEdad.setText("Seleccione una edad");
			return false;
		}

		lblErrorComboEdad.setText("");
		return true;
	}
	
	
	private void validarIngresoDatos(JTextField miTextito) {
		
		miTextito.addKeyListener(new KeyAdapter() {
			
			public void keyTyped(KeyEvent e) {
				
				// Al ingresar el o los nombre, no se puede poner el espacio (COMPLICA EL USO DE DOS NOMBRES)
				if(Character.isDigit(e.getKeyChar()) || !Character.isAlphabetic(e.getKeyChar())){
					System.out.println("Es numero o especial");
					e.consume();
				}
			}
			
		});
		
		
	}	
	
	private void asignarOyenteMouse(JButton miBoton) {
		
		miBoton.addMouseListener(new MouseAdapter() {
			
			public void mouseEntered(MouseEvent e) {
				miBoton.setBackground(Color.GRAY);
				
			}
			
			public void mouseExited(MouseEvent e) {
				miBoton.setBackground(botonColorNormal);
			}
			
		});
	
	}
	
	private void asignarOyenteCampotexto(JTextField miTextito) {
		
		miTextito.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				miTextito.selectAll();
			}
		});
		
	}
	
	
	private void asignarFocusCampoTexto(JTextField miTextito) {
		
		miTextito.addFocusListener(new FocusAdapter() {
			
			@Override
			public void focusGained(FocusEvent e) {
				miTextito.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
			}
			
			@Override
			public void focusLost(FocusEvent e) {
				miTextito.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
			}
			
		});
		
	}
	
	private void handleClose() {
		int opcion = JOptionPane.showConfirmDialog(this, "Seguro que desea cerrar la ventana? Se perderan los datos del formulario");
		
		if(opcion == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
		
	}
	
}