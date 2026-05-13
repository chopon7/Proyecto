package controllers;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import models.User;
import repository.LoginRepository;
import views.GridBagPanel;
import views.MenuPrincipal;
import views.FormularioEmpleado;

public class LoginController {

	private GridBagPanel view;
	private LoginRepository repository;

	public LoginController(GridBagPanel view) {
		repository = new LoginRepository();	
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
		
		if(!validateCredentials(new User(view.getUsuario(), view.getPassword()))){
			return;
		}
		
		User user = repository.login(view.getUsuario(), view.getPassword());
		
		if(user == null) {
			view.setMensajeContrasena("Credenciales incorrectas");
			return;
		}
		
		JOptionPane.showMessageDialog(view.getVentana(),  "Se inició la sesión", "Sesión iniciada", JOptionPane.INFORMATION_MESSAGE);
		new HomeController(new MenuPrincipal());
		
		view.getVentana().dispose();
		
	}

	private void handleRegister() {
		new FormularioEmpleadoController(new FormularioEmpleado());
		view.getVentana().dispose();
	}
	
	private boolean validateCredentials(User user) {
		view.limpiarMensajes();
		
		boolean valid = true;

		if (user.getEmail().trim().isEmpty()) {
			view.setMensajeUsuario("El empleado es obligatorio");
			valid = false;
		}

		if (user.getPassword().trim().isEmpty()) {
			view.setMensajeContrasena("La contraseña es obligatoria");
			valid = false;
		}
		
		return valid;
	}
}