package views;

import java.awt.Color;	
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import utils.AppFont;

public class GridBagPanel extends JPanel {

	Ventana miVentana;
	JTextField usuario;
	JPasswordField contrasena;
	JLabel mensajeUsuario;
	JLabel mensajeContrasena;

	// Por ahora todo se mantiene en el centro
	public GridBagPanel(Ventana miVentana) {

		this.miVentana = miVentana;
		setLayout(new GridBagLayout());
		setBackground(new Color(27, 38, 59));

		GridBagConstraints gbc = new GridBagConstraints();

		gbc.insets = new Insets(8, 8, 8, 8);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.CENTER; // Esto hace que nada se salga del centro

		// Texto de bienvenida
		JLabel saludo = new JLabel("Bienvenido");
		saludo.setFont(AppFont.bigTitle());
		saludo.setForeground(Color.WHITE);

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;

		add(saludo, gbc);

		// Texto Usuario
		gbc.gridwidth = 1;

		JLabel textoUsuario = new JLabel("Empleado:");
		textoUsuario.setFont(AppFont.normal());
		textoUsuario.setForeground(Color.WHITE);

		gbc.gridx = 0;
		gbc.gridy = 1;

		add(textoUsuario, gbc);

		usuario = new JTextField(15);
		gbc.gridx = 1;

		add(usuario, gbc);

		// Para el mensaje del usuario
		mensajeUsuario = new JLabel("");
		mensajeUsuario.setFont(AppFont.small());
		mensajeUsuario.setForeground(Color.YELLOW);

		gbc.gridx = 1;
		gbc.gridy = 2;

		add(mensajeUsuario, gbc);

		// Texto contraseña
		JLabel textoContrasena = new JLabel("Contraseña:");
		textoContrasena.setFont(AppFont.normal());
		textoContrasena.setForeground(Color.WHITE);

		gbc.gridx = 0;
		gbc.gridy = 3;

		add(textoContrasena, gbc);
		contrasena = new JPasswordField(15);
		gbc.gridx = 1;

		add(contrasena, gbc);

		// Para el mensaje de la contraseña
		mensajeContrasena = new JLabel("");
		mensajeContrasena.setFont(AppFont.small());
		mensajeContrasena.setForeground(Color.YELLOW);

		gbc.gridx = 1;
		gbc.gridy = 4;

		add(mensajeContrasena, gbc);

		// Estos son los botones, se colocan en las coordenadas indicadas
		JButton iniciarSesion = new JButton("Iniciar sesion");
		gbc.gridx = 0;
		gbc.gridy = 5;

		add(iniciarSesion, gbc);

		JButton registrar = new JButton("Registrar");
		gbc.gridx = 1;
		add(registrar, gbc);

		registrar.addActionListener(e -> registrar());
		iniciarSesion.addActionListener(e -> login());
	}

	// Metodo para la validacion del login
	private void login() {

		String empleado = usuario.getText();
		String password = String.valueOf(contrasena.getPassword());

		mensajeUsuario.setText("");
		mensajeContrasena.setText("");

		boolean camposValidos = true;

		// Verifica si el campo de empleado esta vacio
		if (empleado.trim().isEmpty()) {
			mensajeUsuario.setText("El nombre del empleado es obligatorio");
			camposValidos = false;
		}

		// Verifica si el campo contraseña esta vacio
		if (password.trim().isEmpty()) {
			mensajeContrasena.setText("La contraseña es obligatoria");
			camposValidos = false;
		}

		// Si alguno de los dos campos esta vacio, se muestra el mensaje de error
		if (!camposValidos) {
			JOptionPane.showMessageDialog(this, "No se han completado los campos solicitados", "Error", JOptionPane.ERROR_MESSAGE);
			return; // Se detiene la ejecucion del metodo
		}

		// Si esta todo correcto, se muestra el mensaje de exito
		JOptionPane.showMessageDialog(null, "Se inicio sesion", "Sesion iniciada", JOptionPane.INFORMATION_MESSAGE);
		//new FormularioRegistro();
		new MenuPrincipal();
		miVentana.dispose();
		
	}
	
	private void registrar() {
		new FormularioEmpleado();
		miVentana.dispose();
	}
	

}
