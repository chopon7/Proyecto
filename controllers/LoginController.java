package controllers;

import exceptions.InvalidPasswordException;
import exceptions.InvalidUserException;
import models.User;

public class LoginController {

	// Metodo principal que usa la vista
	public User iniciarSesion(String usuario, String password) throws InvalidUserException, InvalidPasswordException {

		validarCampos(usuario, password);

		// Por ahora es una simulacion del usuario para cuando la conectemos a una base de datos
		User user = new User("jacobo@gmail.com", "1234");

		// Se valida que las credenciales coincidan
		validarCredenciales(usuario, password);

		return user; // Si es correcto, se retorna el usuario
	}

	// Valida que los campos no esten vacios
	private void validarCampos(String usuario, String password) throws InvalidUserException, InvalidPasswordException {

		if (usuario == null || usuario.trim().isEmpty()) {
			throw new InvalidUserException("El empleado es obligatorio");
		}

		if (password == null || password.trim().isEmpty()) {
			throw new InvalidPasswordException("La contraseña es obligatoria");
		}
	}

	// Valida que las credenciales sean correctas
	private void validarCredenciales(String usuario, String password)
			throws InvalidUserException, InvalidPasswordException {

		if (!usuario.equals("jacobo@gmail.com")) {
			throw new InvalidUserException("Las credenciales son invalidas");
		}

		if (!password.equals("1234")) {
			throw new InvalidPasswordException("Las credenciales son invalidas");
		}
	}
}
