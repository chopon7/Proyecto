package controllers;

import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import exceptions.InvalidJTextFieldException;
import models.Vehiculo;
import repository.EspacioRepository;
import views.EstacionamientoView;
import repository.VehiculoRepository;
import tablemodels.VehiculoTableModel;
import views.FormularioRegistro;

public class FormularioRegistroController {

	private FormularioRegistro vista;
	private VehiculoRepository vehiculoRepository;
	private EspacioRepository espacioRepository;
	private EstacionamientoView vistaEstacionamiento;
	private VehiculoController vehiculoController;

	public FormularioRegistroController(FormularioRegistro vista, EstacionamientoView vistaEstacionamiento,
			VehiculoController vehiculoController) {
		this.vista = vista;
		this.vehiculoController = vehiculoController;
		this.vistaEstacionamiento = vistaEstacionamiento;
		this.vehiculoRepository = new VehiculoRepository();
		this.espacioRepository = new EspacioRepository();

		vista.addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent e) {
				System.out.println("Se abrió la ventana");
				verificarEstacionamientoLleno();
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

		cargarEspaciosDisponibles();

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

		vista.getCboNumeroEstacionamiento().addActionListener(e -> {
			validarEspacioEstacionamiento();
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

	private void cargarEspaciosDisponibles() {
		vista.getCboNumeroEstacionamiento().removeAllItems();
		vista.getCboNumeroEstacionamiento().addItem("Seleccione un espacio");

		List<Integer> disponibles = espacioRepository.getNumerosCajonesLibres();

		for (Integer numero : disponibles) {
			vista.getCboNumeroEstacionamiento().addItem("Espacio " + numero);
		}
	}

	private void verificarEstacionamientoLleno() {
		List<Integer> disponibles = espacioRepository.getNumerosCajonesLibres();
		if (disponibles.isEmpty()) {
			JOptionPane.showMessageDialog(vista,
					"Estacionamiento Lleno. No se pueden registrar más vehículos por el momento.", "Capacidad Máxima",
					JOptionPane.WARNING_MESSAGE);
			vista.getEnviarRegistro().setEnabled(false);
		}
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
		vista.setLblErrorModelo((""));
		vista.setLblErrorColor((""));
		vista.setLblErrorPlaca((""));
		vista.setLblErrorMarca((""));
		vista.setLblErrorCombo((""));
		vista.setLblErrorEstacionamiento("");
	}

	private void validar() {
		reiniciarMensajeError();

		boolean validar = true;

		if (!validarMarca())
			validar = false;

		if (!validarPlaca())
			validar = false;

		if (!validarEspacioEstacionamiento())
			validar = false;

		if (!validarComboBox())
			validar = false;

		if (!validarModelo())
			validar = false;

		if (!validarColor())
			validar = false;

		if (validar) {
			String txtPlaca = vista.getPlaca().getText().trim();
			String txtMarca = vista.getMarca().getText().trim();
			String txtModelo = vista.getModelo().getText().trim();
			String txtColor = vista.getColor().getText().trim();
			String txtTipo = (String) vista.getCboOpcionesTipoCarro().getSelectedItem();

			String itemSeleccionado = (String) vista.getCboNumeroEstacionamiento().getSelectedItem();
			int numeroCajon = Integer.parseInt(itemSeleccionado.replace("Espacio ", ""));

			Vehiculo nuevoVehiculo = new Vehiculo(txtPlaca, txtMarca, txtModelo, txtColor, txtTipo);
			int idVehiculoGenerado = vehiculoRepository.save(nuevoVehiculo);
			
			if (idVehiculoGenerado != -1) {
				boolean exitoEspacio = espacioRepository.ocuparEspacio(numeroCajon, idVehiculoGenerado);

				if (exitoEspacio) {
					nuevoVehiculo.setIdVehiculo(idVehiculoGenerado);
					int indiceCajon = numeroCajon - 1;

					if (vistaEstacionamiento != null) {
						vistaEstacionamiento.ocuparEspacio(indiceCajon, idVehiculoGenerado, nuevoVehiculo);
					}

					if (vehiculoController != null) {
						vehiculoController.loadVehiculos();
					}

					JOptionPane.showMessageDialog(vista, "Vehículo registrado con éxito en el cajón: " + numeroCajon,
							"Registro Exitoso", JOptionPane.INFORMATION_MESSAGE);
					vista.dispose();
				} else {
					JOptionPane.showMessageDialog(vista, "El vehículo se registró, pero no se pudo asignar al cajón.",
							"Error de Asignación", JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(vista,
						"Error al guardar el vehículo en la base de datos.\nVerifique que la placa no esté duplicada.",
						"Error de Persistencia", JOptionPane.ERROR_MESSAGE);
			}

		}

		if (!validar) {
			JOptionPane.showMessageDialog(vista, "No se han completado los campos solicitados", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		return;
	}

	private boolean validarEspacioEstacionamiento() {
		try {
			if (vista.getCboNumeroEstacionamiento().getSelectedIndex() == 0) {
				throw new InvalidJTextFieldException("Seleccione un espacio de estacionamiento");
			}
			vista.setLblErrorEstacionamiento("");
			return true;
		} catch (InvalidJTextFieldException ex) {
			vista.setLblErrorEstacionamiento(ex.getMessage());
			return false;
		}
	}

	private boolean validarMarca() {
		try {
			if (vista.getMarca().getText().trim().isEmpty()) {
				throw new InvalidJTextFieldException("La marca es obligatoria");
			}
			vista.setLblErrorMarca((""));
			return true;
		} catch (InvalidJTextFieldException ex) {
			vista.setLblErrorMarca(ex.getMessage());
			return false;
		}

	}

	private boolean validarModelo() {

		try {
			if (vista.getModelo().getText().trim().isEmpty()) {
				throw new InvalidJTextFieldException("El modelo es obligatorio");
			}
			vista.setLblErrorModelo((""));
			return true;
		} catch (InvalidJTextFieldException ex) {
			vista.setLblErrorModelo(ex.getMessage());
			return false;
		}
	}

	private boolean validarPlaca() {
		try {
			String textoPlaca = vista.getPlaca().getText().trim();
			if (textoPlaca.isEmpty()) {
				throw new InvalidJTextFieldException("La placa es obligatoria");
			}
			if (vehiculoRepository.existePlaca(textoPlaca)) {
				throw new InvalidJTextFieldException("Esta placa ya está registrada en el sistema");
			}
			vista.setLblErrorPlaca("");
			return true;
		} catch (InvalidJTextFieldException ex) {
			vista.setLblErrorPlaca(ex.getMessage());
			return false;
		}
	}

	private boolean validarColor() {

		try {
			if (vista.getColor().getText().trim().isEmpty()) {
				throw new InvalidJTextFieldException("El color es obligatorio");
			}
			vista.setLblErrorColor("");
			return true;
		} catch (InvalidJTextFieldException ex) {
			vista.setLblErrorColor(ex.getMessage());
			return false;
		}
	}

	private boolean validarComboBox() {

		try {
			if (vista.getCboOpcionesTipoCarro().getSelectedIndex() == 0) {
				throw new InvalidJTextFieldException("Seleccione un tipo de carro");
			}
			vista.setLblErrorCombo((""));
			return true;
		} catch (InvalidJTextFieldException ex) {
			vista.setLblErrorCombo(ex.getMessage());
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
