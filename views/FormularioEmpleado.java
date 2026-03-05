package views;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import utils.AppFont;

public class FormularioEmpleado extends JFrame {

	private JLabel lblErrorNombre;
	private JLabel lblErrorEdad;
	private JLabel lblErrorCodigo;
	private JLabel lblErrorEmail;
	private JLabel lblErrorCombo;
	private JTextField nombre;
	private JTextField edad;
	private JTextField codigo;
	private JTextField email;
	private JComboBox<String> cboOpcionesGenero;
	
	public FormularioEmpleado() {

		setSize(1000, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

		// Marca del auto
		c.gridx = 0;
		c.gridy = 0;
		panelComponentes.add(crearLabel("Ingresa el nombre del empleado:"), c);
		c.gridx = 1;
		nombre =  new JTextField(20);
		panelComponentes.add(nombre, c);
		c.gridy = 1;
		lblErrorNombre = crearMensajeError("");
		crearMensajeError(lblErrorNombre.getText());
		panelComponentes.add(lblErrorNombre, c);

		// Placa del auto
		c.gridx = 0;
		c.gridy = 2;
		panelComponentes.add(crearLabel("Ingresa la edad del empleado:"), c);
		c.gridx = 1;
		edad = new JTextField(20);
		panelComponentes.add(edad, c);
		lblErrorEdad = crearMensajeError("");
		c.gridy = 3;
		crearMensajeError(lblErrorEdad.getText());
		panelComponentes.add(lblErrorEdad, c);

		// Modelo del auto
		c.gridx = 0;
		c.gridy = 4;
		panelComponentes.add(crearLabel("Ingresa el codigo unico del empleado:"), c);
		c.gridx = 1;
		codigo = new JTextField(20);
		panelComponentes.add(codigo, c);
		c.gridy = 5;
		lblErrorCodigo = crearMensajeError("");
		crearMensajeError(lblErrorCodigo.getText());
		panelComponentes.add(lblErrorCodigo, c);
		
		// Color del auto
		c.gridx = 0;
		c.gridy = 6;
		panelComponentes.add(crearLabel("Ingresa el email del empleado"), c);
		c.gridx = 1;
		email = new JTextField(20);
		panelComponentes.add(email, c);
		lblErrorEmail = crearMensajeError("");
		c.gridy = 7;
		panelComponentes.add(lblErrorEmail, c);
		
		
		// Tipo de auto
		c.gridx = 0;
		c.gridy = 8;
		panelComponentes.add(crearLabel("Ingresa el genero del empleado:"), c);
		c.gridx = 1;
		String[] opcionesGenero = {"Seleccione" ,"Masculino", "Femenino", "Prefiero no decirlo" };
		cboOpcionesGenero = new JComboBox<>(opcionesGenero);
		panelComponentes.add(cboOpcionesGenero, c);
		c.gridy = 9;
		lblErrorCombo = crearMensajeError("");
		crearMensajeError(lblErrorCombo.getText());
		panelComponentes.add(lblErrorCombo, c);
		

		// Boton de registro
		c.gridx = 0;
		c.gridy = 10;
		c.insets = new java.awt.Insets(20, 10, 10, 10);
		JButton enviarRegistro = new JButton("Enviar");
		enviarRegistro.addActionListener(e -> validar());
		panelComponentes.add(enviarRegistro, c);

		// Boton atras
		c.gridx = 1;
		c.gridy = 10;
		JButton botonAtras = new JButton("Atras");
		panelComponentes.add(botonAtras, c);

		// Scroll
		JScrollPane scroll = new JScrollPane(panelComponentes);
		add(scroll, BorderLayout.CENTER);

		
		
		
	}

	private JLabel crearLabel(String texto) {
		JLabel label = new JLabel(texto);
		label.setFont(AppFont.normal());
		label.setForeground(Color.WHITE);
		return label;
	}

	private void reiniciarMensajeError() {
		lblErrorNombre.setText("");
		lblErrorEdad.setText("");
		lblErrorCodigo.setText("");
		lblErrorEmail.setText("");
		lblErrorCombo.setText("");
	}
	
	private void validar() {
		reiniciarMensajeError();

		boolean validar = true;

		if (!validarNombre())
			validar = false;

		if (!validarCodigo())
			validar = false;

		if (!validarComboBox())
			validar = false;

		if (!validarEdad())
			validar = false;

		if (!validarEmail())
			validar = false;

		if (validar) {
			JOptionPane.showMessageDialog(this, "Registro exitoso");
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
		label.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		label.setForeground(Color.RED);
		label.setHorizontalAlignment(SwingConstants.LEFT);
		label.setMaximumSize(new Dimension(Integer.MAX_VALUE, label.getPreferredSize().height));

		return label;
	}
	
	private boolean validarNombre() {

		if (nombre.getText().trim().isEmpty()) {
			lblErrorNombre.setText("El nombre es obligatorio");
			return false;
		}

		return true;
	}
	
	private boolean validarEdad() {

		if (edad.getText().trim().isEmpty()) {
			lblErrorEdad.setText("La edad es obligatoria");
			return false;
		}

		return true;
	}
	
	private boolean validarCodigo() {

		if (codigo.getText().trim().isEmpty()) {
			lblErrorCodigo.setText("El codigo es obligatorio");
			return false;
		}

		return true;
	}
	
	private boolean validarEmail() {

		if (email.getText().trim().isEmpty()) {
			lblErrorEmail.setText("El email es obligatorio");
			return false;
		}

		return true;
	}

	private boolean validarComboBox() {

		if (cboOpcionesGenero.getSelectedIndex() == 0) {
			lblErrorCombo.setText("Seleccione un genero");
			return false;
		}

		return true;
	}
	
}