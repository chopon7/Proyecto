package controllers;

import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import exceptions.InvalidJTextFieldException;
import models.User;
import views.FormularioEmpleado;
import views.Ventana;

public class FormularioEmpleadoController {

	private FormularioEmpleado vista;
	private User empleado;

	public FormularioEmpleadoController(FormularioEmpleado vista, User empleado) {
		this.vista = vista;
		this.empleado = empleado;
		
		asignarOyenteCampotexto(vista.getNombreTxt());
		asignarOyenteCampotexto(vista.getApellidoPaternoTxt());
		asignarOyenteCampotexto(vista.getApellidoMaternoTxt());
		asignarOyenteCampotexto(vista.getEmailTxt());
		asignarOyenteCampotexto(vista.getPasswordTxt());
		
		asignarFocusCampoTexto(vista.getNombreTxt());
		asignarFocusCampoTexto(vista.getApellidoPaternoTxt());
		asignarFocusCampoTexto(vista.getApellidoMaternoTxt());
		asignarFocusCampoTexto(vista.getEmailTxt());
		asignarFocusCampoTexto(vista.getPasswordTxt());
		
		validarIngresoDatos(vista.getNombreTxt());
		validarIngresoDatos(vista.getApellidoPaternoTxt());
		validarIngresoDatos(vista.getApellidoMaternoTxt());
		validarIngresoDatos(vista.getPasswordTxt());
		

		vista.getNombreTxt().getDocument().addDocumentListener(new DocumentListener() {
			public void insertUpdate(DocumentEvent e) {
				validarNombre();
			}

			public void removeUpdate(DocumentEvent e) {
				validarNombre();
			}

			public void changedUpdate(DocumentEvent e) {
				validarNombre();
			}
		});

		vista.getApellidoPaternoTxt().getDocument().addDocumentListener(new DocumentListener() {
			public void insertUpdate(DocumentEvent e) {
				validarApellidoPaterno();
			}

			public void removeUpdate(DocumentEvent e) {
				validarApellidoPaterno();
			}

			public void changedUpdate(DocumentEvent e) {
				validarApellidoPaterno();
			}
		});

		vista.getApellidoMaternoTxt().getDocument().addDocumentListener(new DocumentListener() {
			public void insertUpdate(DocumentEvent e) {
				validarApellidoMaterno();
			}

			public void removeUpdate(DocumentEvent e) {
				validarApellidoMaterno();
			}

			public void changedUpdate(DocumentEvent e) {
				validarApellidoMaterno();
			}
		});

		vista.getPasswordTxt().getDocument().addDocumentListener(new DocumentListener() {
			public void insertUpdate(DocumentEvent e) {
				validarPassword();
			}

			public void removeUpdate(DocumentEvent e) {
				validarPassword();
			}

			public void changedUpdate(DocumentEvent e) {
				validarPassword();
			}
		});

		vista.getEmailTxt().getDocument().addDocumentListener(new DocumentListener() {
			public void insertUpdate(DocumentEvent e) {
				validarEmail();
			}

			public void removeUpdate(DocumentEvent e) {
				validarEmail();
			}

			public void changedUpdate(DocumentEvent e) {
				validarEmail();
			}
		});

		vista.getCboOpcionesGenero().addActionListener(e -> {
			validarComboBoxGenero();
		});
		vista.getCboOpcionesEdad().addActionListener(e -> {
			validarComboBoxEdad();
		});

		asignarOyenteMouse(vista.getBtnEnviar());
		asignarOyenteMouse(vista.getBtnAtras());

		vista.getBtnEnviar().addActionListener(e -> validar());

		vista.getBtnAtras().addActionListener(e -> {

			int option = JOptionPane.showConfirmDialog(vista,
					"¿Seguro que deseas regresar? Se perderán todos los datos");

			if (option == JOptionPane.YES_OPTION) {
				new Ventana();
				vista.dispose();
			}

		});
	}

	private void validarIngresoDatos(JTextField miTextito) {
		miTextito.addKeyListener(new KeyAdapter() {

			public void keyTyped(KeyEvent e) {
				// Al ingresar el o los nombre, no se puede poner el espacio (COMPLICA EL USO DE DOS NOMBRES)
				if (Character.isDigit(e.getKeyChar()) || !Character.isAlphabetic(e.getKeyChar())) {
					System.out.println("Es numero o especial");
					e.consume();
				}
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

	private void asignarOyenteMouse(JButton miBoton) {

		miBoton.addMouseListener(new MouseAdapter() {

			public void mouseEntered(MouseEvent e) {
				miBoton.setBackground(Color.GRAY);

			}

			public void mouseExited(MouseEvent e) {
				miBoton.setBackground(vista.getColorBotonNormal());
			}

		});

	}

	private void reiniciarMensajeError() {
		vista.setLblErrorNombre("");
		vista.setLblErrorComboEdad("");
		vista.setLblErrorPassword("");
		vista.setLblErrorEmail("");
		vista.setLblErrorCombo("");
		vista.setLblErrorApellidoMaterno("");
		vista.setLblErrorApellidoPaterno("");
	}

	private void validar() {
		reiniciarMensajeError();

		boolean validar = true;

		if (!validarNombre())
			validar = false;

		if (!validarPassword())
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
			empleado.setNombre(vista.getNombre());
			empleado.setApellidoPaterno(vista.getApellidoPaterno());
			empleado.setApellidoMaterno(vista.getApellidoMaterno());
			empleado.setEdad(vista.getEdadSeleccionada());
			empleado.setEmail(vista.getEmail());
			empleado.setGenero(vista.getGeneroSelecionado());
			empleado.setPassword(vista.getPassword());
			JOptionPane.showMessageDialog(vista, "Registro exitoso");
			new Ventana();
			vista.dispose();
		}

		if (!validar) {
			JOptionPane.showMessageDialog(vista, "No se han completado los campos solicitados", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		return;
	}

	private boolean validarNombre() {
		try {
			if (vista.getNombre().trim().isEmpty()) {
				throw new InvalidJTextFieldException("El nombre es obligatorio");
			}
			vista.setLblErrorNombre("");
			return true;
		} catch (InvalidJTextFieldException ex) {
			vista.setLblErrorNombre(ex.getMessage());
			return false;
		}
	}

	private boolean validarApellidoPaterno() {
		try {
			if (vista.getApellidoPaterno().trim().isEmpty()) {
				throw new InvalidJTextFieldException("El apellido paterno es obligatorio");
			}
			vista.setLblErrorApellidoPaterno("");
			return true;
		} catch (InvalidJTextFieldException ex) {
			vista.setLblErrorApellidoPaterno(ex.getMessage());
			return false;
		}
	}

	private boolean validarApellidoMaterno() {
		try {
			if (vista.getApellidoMaterno().trim().isEmpty()) {
				throw new InvalidJTextFieldException("El apellido materno es obligatorio");
			}
			vista.setLblErrorApellidoMaterno("");
			return true;

		} catch (InvalidJTextFieldException ex) {
			vista.setLblErrorApellidoMaterno(ex.getMessage());
			return false;
		}
	}

	private boolean validarPassword() {
		try {
			if (vista.getPassword().trim().isEmpty()) {
				throw new InvalidJTextFieldException("La contraseña es obligatoria");
			}
			vista.setLblErrorPassword("");
			return true;

		} catch (InvalidJTextFieldException ex) {
			vista.setLblErrorPassword(ex.getMessage());
			return false;
		}
	}

	private boolean validarEmail() {
		try {
			if (vista.getEmail().trim().isEmpty()) {
				throw new InvalidJTextFieldException("El email es obligatorio");
			}

			if (!vista.getEmail().trim().contains("@")) {
				throw new InvalidJTextFieldException("Email inválido: Falta '@'");
			}

			vista.setLblErrorEmail("");
			return true;

		} catch (InvalidJTextFieldException ex) {
			vista.setLblErrorEmail(ex.getMessage());
			return false;
		}
	}

	private boolean validarComboBoxGenero() {
		try {
			if (vista.getIndiceGenero() == 0) {
				throw new InvalidJTextFieldException("Seleccione un genero");
			}

			vista.setLblErrorCombo("");
			return true;

		} catch (InvalidJTextFieldException ex) {
			vista.setLblErrorCombo(ex.getMessage());
			return false;
		}
	}

	private boolean validarComboBoxEdad() {
		try {
			if (vista.getIndiceEdad() == 0) {
				throw new InvalidJTextFieldException("Seleccione una edad");
			}

			vista.setLblErrorComboEdad("");
			return true;

		} catch (InvalidJTextFieldException ex) {
			vista.setLblErrorComboEdad(ex.getMessage());
			return false;
		}
	}
	
}
