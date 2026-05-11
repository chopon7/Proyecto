package controllers;

import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import exceptions.InvalidJTextFieldException;
import views.FormularioRegistro;

public class FormularioRegistroController {

	private FormularioRegistro vista;

	public FormularioRegistroController(FormularioRegistro vista) {
		this.vista = vista;

		vista.addWindowListener(new WindowListener() {

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

		asignarOyenteCampotexto(vista.getColor());
		asignarOyenteCampotexto(vista.getMarca());
		asignarOyenteCampotexto(vista.getModelo());
		asignarOyenteCampotexto(vista.getPlaca());

		asignarFocusCampoTexto(vista.getColor());
		asignarFocusCampoTexto(vista.getMarca());
		asignarFocusCampoTexto(vista.getModelo());
		asignarFocusCampoTexto(vista.getPlaca());

		vista.getColor().getDocument().addDocumentListener(new DocumentListener() {
			public void insertUpdate(DocumentEvent e) {
				validarColor();
			}

			public void removeUpdate(DocumentEvent e) {
				validarColor();
			}

			public void changedUpdate(DocumentEvent e) {
				validarColor();
			}
		});

		vista.getMarca().getDocument().addDocumentListener(new DocumentListener() {
			public void insertUpdate(DocumentEvent e) {
				validarMarca();
			}

			public void removeUpdate(DocumentEvent e) {
				validarMarca();
			}

			public void changedUpdate(DocumentEvent e) {
				validarMarca();
			}
		});

		vista.getModelo().getDocument().addDocumentListener(new DocumentListener() {
			public void insertUpdate(DocumentEvent e) {
				validarModelo();
			}

			public void removeUpdate(DocumentEvent e) {
				validarModelo();
			}

			public void changedUpdate(DocumentEvent e) {
				validarModelo();
			}
		});

		vista.getPlaca().getDocument().addDocumentListener(new DocumentListener() {
			public void insertUpdate(DocumentEvent e) {
				validarPlaca();
			}

			public void removeUpdate(DocumentEvent e) {
				validarPlaca();
			}

			public void changedUpdate(DocumentEvent e) {
				validarPlaca();
			}
		});

		vista.getCboOpcionesTipoCarro().addActionListener(e -> {
			validarComboBox();
		});

		asignarOyenteMouse(vista.getEnviarRegistro());
		asignarOyenteMouse(vista.getBotonAtras());

		vista.getEnviarRegistro().addActionListener(e -> validar());

		vista.getBotonAtras().addActionListener(e -> {

			int option = JOptionPane.showConfirmDialog(vista,
					"¿Seguro que deseas regresar? Se perderán todos los datos");

			if (option == JOptionPane.YES_OPTION) {
				vista.dispose();
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
				miBoton.setBackground(vista.getBotonColorNormal());
			}

		});

	}

	private void reiniciarMensajeError() {
		vista.setLblErrorModelo(new JLabel(""));
		vista.setLblErrorColor(new JLabel(""));
		vista.setLblErrorPlaca(new JLabel(""));
		vista.setLblErrorMarca(new JLabel(""));
		vista.setLblErrorCombo(new JLabel(""));
	}

	private void validar() {
		reiniciarMensajeError();

		boolean validar = true;

		if (!validarMarca())
			validar = false;

		if (!validarPlaca())
			validar = false;

		if (!validarComboBox())
			validar = false;

		if (!validarModelo())
			validar = false;

		if (!validarColor())
			validar = false;

		if (validar) {
			//REGISTRO POR TERMINAR
			vista.dispose();
		}

		if (!validar) {
			JOptionPane.showMessageDialog(vista, "No se han completado los campos solicitados", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		return;
	}

	private boolean validarMarca() {
		try {
			if (vista.getMarca().getText().trim().isEmpty()) {
				throw new InvalidJTextFieldException("La marca es obligatoria");
			}
			vista.setLblErrorMarca(new JLabel(""));
			return true;
		} catch (InvalidJTextFieldException ex) {
			vista.setLblErrorMarca(new JLabel(ex.getMessage()));
			return false;
		}

	}

	private boolean validarModelo() {

		try {
			if (vista.getModelo().getText().trim().isEmpty()) {
				throw new InvalidJTextFieldException("El modelo es obligatorio");
			}
			vista.setLblErrorModelo(new JLabel(""));
			return true;
		} catch (InvalidJTextFieldException ex) {
			vista.setLblErrorModelo(new JLabel(ex.getMessage()));
			return false;
		}
	}

	private boolean validarPlaca() {

		try {
			if (vista.getPlaca().getText().trim().isEmpty()) {
				throw new InvalidJTextFieldException("La placa es obligatoria");
			}
			vista.setLblErrorPlaca(new JLabel(""));
			return true;
		} catch (InvalidJTextFieldException ex) {
			vista.setLblErrorPlaca(new JLabel(ex.getMessage()));
			return false;
		}
	}

	private boolean validarColor() {

		try {
			if (vista.getColor().getText().trim().isEmpty()) {
				throw new InvalidJTextFieldException("El color es obligatorio");
			}
			vista.setLblErrorColor(new JLabel(""));
			return true;
		} catch (InvalidJTextFieldException ex) {
			vista.setLblErrorColor(new JLabel(ex.getMessage()));
			return false;
		}
	}

	private boolean validarComboBox() {

		try {
			if (vista.getCboOpcionesTipoCarro().getSelectedIndex() == 0) {
				throw new InvalidJTextFieldException("Seleccione un tipo de carro");
			}
			vista.setLblErrorCombo(new JLabel(""));
			return true;
		} catch (InvalidJTextFieldException ex) {
			vista.setLblErrorCombo(new JLabel(ex.getMessage()));
			return false;
		}
	}

	private void handleClose() {
		int opcion = JOptionPane.showConfirmDialog(vista,
				"Seguro que desea cerrar la ventana? Se perderan los datos del formulario");

		if (opcion == JOptionPane.YES_OPTION) {
			vista.dispose();
		}

	}

}
