package controllers;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import exceptions.InvalidPasswordException;
import exceptions.InvalidUserException;
import views.GridBagPanel;
import views.MenuPrincipal;
import views.FormularioEmpleado;

public class LoginController {

	private GridBagPanel view;

	public LoginController(GridBagPanel view) {
		this.view = view;
		registerListeners();
		agregarValidacionTiempoReal();
	}

	private void registerListeners() {

		view.getIniciarSesion().addActionListener(e -> handleLogin());
		view.getRegistrar().addActionListener(e -> handleRegister());

		KeyAdapter enter = new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					handleLogin();
				}
			}
		};

		view.getUsuarioField().addKeyListener(enter);
		view.getContrasenaField().addKeyListener(enter);
	}

	private void agregarValidacionTiempoReal() {

		view.getUsuarioField().getDocument().addDocumentListener(new DocumentListener() {

			public void insertUpdate(DocumentEvent e) {
				validarUsuarioTiempoReal();
			}

			public void removeUpdate(DocumentEvent e) {
				validarUsuarioTiempoReal();
			}

			public void changedUpdate(DocumentEvent e) {
				validarUsuarioTiempoReal();
			}
		});

		view.getContrasenaField().getDocument().addDocumentListener(new DocumentListener() {

			public void insertUpdate(DocumentEvent e) {
				validarContrasenaTiempoReal();
			}

			public void removeUpdate(DocumentEvent e) {
				validarContrasenaTiempoReal();
			}

			public void changedUpdate(DocumentEvent e) {
				validarContrasenaTiempoReal();
			}
		});

	}

	private void validarUsuarioTiempoReal() {

		String usuario = view.getUsuario();

		if (usuario.trim().isEmpty()) {
			view.setMensajeUsuario("El nombre del empleado es obligatorio");
		} else {
			view.setMensajeUsuario("");
		}
	}

	private void validarContrasenaTiempoReal() {

		String password = view.getPassword();

		if (password.trim().isEmpty()) {
			view.setMensajeContrasena("La contraseña es obligatoria");
		} else {
			view.setMensajeContrasena("");
		}
	}

	private void handleLogin() {

		view.limpiarMensajes();

		String usuario = view.getUsuario();
		String password = view.getPassword();

		try {

			if (usuario.trim().isEmpty()) {
				view.setMensajeUsuario("El empleado es obligatorio");
				return;
			}

			if (password.trim().isEmpty()) {
				view.setMensajeContrasena("La contraseña es obligatoria");
				return;
			}

			if (!usuario.equals("a@")) {
				throw new InvalidUserException("Las credenciales son incorrectas");
			}

			if (!password.equals("1234")) {
				throw new InvalidPasswordException("Las credenciales son incorrectas");
			}

			JOptionPane.showMessageDialog(view, "Bienvenido " + usuario, "Sesión iniciada",
					JOptionPane.INFORMATION_MESSAGE);

			new HomeController(new MenuPrincipal());
			view.getVentana().dispose();

		} catch (InvalidUserException e) {
			view.setMensajeUsuario(e.getMessage());
		} catch (InvalidPasswordException e) {
			view.setMensajeContrasena(e.getMessage());
		}
	}

	private void handleRegister() {
		new FormularioEmpleadoController(new FormularioEmpleado());
		view.getVentana().dispose();
	}
}