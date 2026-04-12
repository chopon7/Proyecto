package views;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import exceptions.InvalidPasswordException;
import exceptions.InvalidUserException;
import utils.AppFont;

import controllers.LoginController;
import models.User;

public class GridBagPanel extends JPanel {

	Ventana miVentana;
	JTextField usuario;
	JPasswordField contrasena;
	JLabel mensajeUsuario;
	JLabel mensajeContrasena;
	Color botonColorNormal;
	LoginController controller;

	// Por ahora todo se mantiene en el centro
	public GridBagPanel(Ventana miVentana) {

		this.miVentana = miVentana;
		controller = new LoginController();
		setLayout(new GridBagLayout());
		setBackground(new Color(27, 38, 59));

		miVentana.addWindowListener(new WindowListener() {

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

		inicializarComponentes();
	}

	private void inicializarComponentes() {

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
		asignarFocusCampoTexto(usuario);
		agregarListener(usuario);
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
		asignarFocusCampoCodigo(contrasena);
		agregarListener(contrasena);
		gbc.gridx = 1;

		add(contrasena, gbc);

		// Para el mensaje de la contraseña
		mensajeContrasena = new JLabel("");
		mensajeContrasena.setFont(AppFont.small());
		mensajeContrasena.setForeground(Color.YELLOW);

		gbc.gridx = 1;
		gbc.gridy = 4;

		add(mensajeContrasena, gbc);

		ImageIcon ilustracionBotonInicioSesion = new ImageIcon("img/inicioSesion.png");
		Image imagenActualizadaBotonInicioSesion = ilustracionBotonInicioSesion.getImage().getScaledInstance(18, 18,
				Image.SCALE_SMOOTH);
		ImageIcon iconoFinalBotonInicioSesion = new ImageIcon(imagenActualizadaBotonInicioSesion);

		// Estos son los botones, se colocan en las coordenadas indicadas
		JButton iniciarSesion = new JButton("Iniciar sesion", iconoFinalBotonInicioSesion);
		gbc.gridx = 0;
		gbc.gridy = 5;

		add(iniciarSesion, gbc);
		asignarOyenteMouse(iniciarSesion);

		ImageIcon ilustracionBotonRegistro = new ImageIcon("img/usuarioNuevo.png");
		Image imagenActualizada = ilustracionBotonRegistro.getImage().getScaledInstance(18, 18, Image.SCALE_SMOOTH);
		ImageIcon iconoFinal = new ImageIcon(imagenActualizada);

		JButton registrar = new JButton("Registrar", iconoFinal);
		gbc.gridx = 1;
		add(registrar, gbc);
		botonColorNormal = registrar.getBackground();

		// Evento cambio de imagen del boton (registrar)
		asignarOyenteMouse(registrar);

		registrar.addActionListener(e -> registrar());

		iniciarSesion.addActionListener(e -> {
			login();
		});

	}

	// Metodo para la validacion del login
	private void login() {

		String empleado = usuario.getText();
		String password = String.valueOf(contrasena.getPassword());

		mensajeUsuario.setText("");
		mensajeContrasena.setText("");

		try {
			User user = controller.iniciarSesion(empleado, password);

			JOptionPane.showMessageDialog(this, "Bienvenido " + user.getEmail(), "Sesión iniciada",
					JOptionPane.INFORMATION_MESSAGE);

			new MenuPrincipal();
			miVentana.dispose();

		} catch (InvalidUserException e) {
			mensajeUsuario.setText(e.getMessage());

		} catch (InvalidPasswordException e) {
			mensajeContrasena.setText(e.getMessage());
		}

	}

	private void registrar() {
		new FormularioEmpleado();
		miVentana.dispose();
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

	private void asignarFocusCampoCodigo(JPasswordField miTextito) {

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
		int opcion = JOptionPane.showConfirmDialog(miVentana, "Seguro que desea cerrar la ventana?");

		if (opcion == JOptionPane.YES_OPTION) {
			System.exit(0);
		}

	}

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

	private void validarCampo(JTextField campo) {

		if (campo == usuario) {
			validarUsuario();
		}

		if (campo == contrasena) {
			validarContrasena();
		}
	}

	private boolean validarUsuario() {

		if (usuario.getText().trim().isEmpty()) {
			mensajeUsuario.setText("El nombre del empleado es obligatorio");
			return false;
		}

		mensajeUsuario.setText("");
		return true;
	}

	private boolean validarContrasena() {

		String password = String.valueOf(contrasena.getPassword());

		if (password.trim().isEmpty()) {
			mensajeContrasena.setText("La contraseña es obligatoria");
			return false;
		}

		mensajeContrasena.setText("");
		return true;
	}

}
