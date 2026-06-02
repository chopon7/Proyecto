package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import models.Vehiculo;
import repository.EspacioRepository;
import repository.VehiculoRepository;
import utils.AppFont;

public class VehiculoFormDialog extends JDialog {

	private JLabel lblErrorPlaca;
	private JLabel lblErrorMarca;
	private JLabel lblErrorModelo;
	private JLabel lblErrorColor;
	private JLabel lblErrorTipo;
	private JLabel lblErrorEstacionamiento;

	private JTextField placa;
	private JTextField marca;
	private JTextField modelo;
	private JTextField color;
	private JComboBox<String> cboTipoVehiculo;
	private JComboBox<String> cboNumeroEstacionamiento;

	private JButton btnGuardar;
	private JButton btnCancelar;
	private JPanel panelComponentes;

	private Vehiculo vehiculoForm;
	private boolean saved = false;

	private VehiculoRepository vehiculoRepo = new VehiculoRepository();
	private EspacioRepository espacioRepo = new EspacioRepository();

	public VehiculoFormDialog(JFrame parent, Vehiculo vehiculoForm) {
		super(parent, true);
		this.vehiculoForm = vehiculoForm;

		setSize(800, 650);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setResizable(true);
		setTitle(vehiculoForm == null ? "Agregar Vehículo" : "Editar Vehículo");
		setLocationRelativeTo(null);
		Toolkit tk = Toolkit.getDefaultToolkit();
		Image icono = tk.getImage("img/imagenVehiculo.png");
		setIconImage(icono);

		inicializarComponentes();
		if (vehiculoForm == null) {
			cargarEspaciosDisponibles(espacioRepo.getNumerosCajonesLibres());
		}

		loadData();
		setVisible(true);
	}

	public boolean isSaved() {
		return saved;
	}

	public Vehiculo getVehiculo() {
		return vehiculoForm;
	}

	private void cargarEspaciosDisponibles(List<Integer> disponibles) {
		cboNumeroEstacionamiento.removeAllItems();
		cboNumeroEstacionamiento.addItem("Seleccione un espacio");
		if (disponibles != null) {
			for (Integer numero : disponibles) {
				cboNumeroEstacionamiento.addItem("Espacio " + numero);
			}
		}
	}

	private void reiniciarMensajesError() {
		lblErrorPlaca.setText("");
		lblErrorMarca.setText("");
		lblErrorModelo.setText("");
		lblErrorColor.setText("");
		lblErrorTipo.setText("");
		lblErrorEstacionamiento.setText("");
	}

	private void inicializarComponentes() {
		addWindowListener(new WindowListener() {
			@Override
			public void windowOpened(WindowEvent e) {
			}

			@Override
			public void windowIconified(WindowEvent e) {
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
			}

			@Override
			public void windowClosing(WindowEvent e) {
				handleClose();
			}

			@Override
			public void windowClosed(WindowEvent e) {
			}

			@Override
			public void windowActivated(WindowEvent e) {
			}
		});

		JLabel lblTitulo = new JLabel("Registro de Vehículos");
		lblTitulo.setFont(AppFont.title());
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblTitulo, BorderLayout.NORTH);

		panelComponentes = new JPanel(new GridBagLayout());
		panelComponentes.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
		panelComponentes.setBackground(new Color(27, 38, 59));
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new java.awt.Insets(10, 10, 10, 10);
		c.fill = GridBagConstraints.HORIZONTAL;

		// Placa
		c.gridx = 0;
		c.gridy = 0;
		panelComponentes.add(crearLabel("Ingresa la placa del auto:"), c);
		c.gridx = 1;
		placa = new JTextField(20);
		panelComponentes.add(placa, c);
		c.gridy = 1;
		lblErrorPlaca = crearMensajeError("");
		panelComponentes.add(lblErrorPlaca, c);

		// Marca
		c.gridx = 0;
		c.gridy = 2;
		panelComponentes.add(crearLabel("Ingresa la marca del auto:"), c);
		c.gridx = 1;
		marca = new JTextField(20);
		panelComponentes.add(marca, c);
		c.gridy = 3;
		lblErrorMarca = crearMensajeError("");
		panelComponentes.add(lblErrorMarca, c);

		// Modelo
		c.gridx = 0;
		c.gridy = 4;
		panelComponentes.add(crearLabel("Ingresa el modelo del auto:"), c);
		c.gridx = 1;
		modelo = new JTextField(20);
		panelComponentes.add(modelo, c);
		c.gridy = 5;
		lblErrorModelo = crearMensajeError("");
		panelComponentes.add(lblErrorModelo, c);

		// Color
		c.gridx = 0;
		c.gridy = 6;
		panelComponentes.add(crearLabel("Ingresa el color del auto:"), c);
		c.gridx = 1;
		color = new JTextField(20);
		panelComponentes.add(color, c);
		c.gridy = 7;
		lblErrorColor = crearMensajeError("");
		panelComponentes.add(lblErrorColor, c);

		// Tipo de Vehículo
		c.gridx = 0;
		c.gridy = 8;
		panelComponentes.add(crearLabel("Ingresa el tipo de vehículo:"), c);
		c.gridx = 1;
		String[] tipos = { "Seleccione", "Automovil", "Camioneta", "Super Deportivo", "SUV", "Otro" };
		cboTipoVehiculo = new JComboBox<>(tipos);
		panelComponentes.add(cboTipoVehiculo, c);
		c.gridy = 9;
		lblErrorTipo = crearMensajeError("");
		panelComponentes.add(lblErrorTipo, c);

		// Cajón de Estacionamiento
		c.gridx = 0;
		c.gridy = 10;
		panelComponentes.add(crearLabel("Selecciona el cajón del auto:"), c);
		c.gridx = 1;
		cboNumeroEstacionamiento = new JComboBox<>();
		cboNumeroEstacionamiento.addItem("Seleccione un espacio");
		panelComponentes.add(cboNumeroEstacionamiento, c);
		c.gridy = 11;
		lblErrorEstacionamiento = crearMensajeError("");
		panelComponentes.add(lblErrorEstacionamiento, c);

		// Botones
		c.gridx = 0;
		c.gridy = 12;
		c.insets = new java.awt.Insets(20, 10, 10, 10);
		ImageIcon ilustracionBotonEnviar = new ImageIcon("img/enviar.png");
		Image imagenActualizadaBotonEnviar = ilustracionBotonEnviar.getImage().getScaledInstance(18, 18,
				Image.SCALE_SMOOTH);
		btnGuardar = new JButton("Guardar", new ImageIcon(imagenActualizadaBotonEnviar));
		panelComponentes.add(btnGuardar, c);

		c.gridx = 1;
		ImageIcon ilustracionBotonAtras = new ImageIcon("img/atras.png");
		Image imagenActualizadaBotonAtras = ilustracionBotonAtras.getImage().getScaledInstance(18, 18,
				Image.SCALE_SMOOTH);
		btnCancelar = new JButton("Cancelar", new ImageIcon(imagenActualizadaBotonAtras));
		panelComponentes.add(btnCancelar, c);

		btnCancelar.addActionListener(e -> dispose());
		btnGuardar.addActionListener(e -> save());
		asignarOyentesValidacion();

		JScrollPane scroll = new JScrollPane(panelComponentes);
		add(scroll, BorderLayout.CENTER);
	}

	private JLabel crearLabel(String texto) {
		JLabel label = new JLabel(texto);
		label.setFont(AppFont.normal());
		label.setForeground(Color.WHITE);
		return label;
	}

	private JLabel crearMensajeError(String mensaje) {
		JLabel label = new JLabel(mensaje);
		label.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		label.setForeground(Color.RED);
		label.setHorizontalAlignment(SwingConstants.LEFT);
		return label;
	}

	private void handleClose() {
		int opcion = JOptionPane.showConfirmDialog(this,
				"¿Seguro que desea cerrar la ventana? Se perderán los datos del formulario");
		if (opcion == JOptionPane.YES_OPTION) {
			dispose();
		}
	}

	private void loadData() {
		if (vehiculoForm != null) {
			placa.setText(vehiculoForm.getPlaca());
			marca.setText(vehiculoForm.getMarca());
			modelo.setText(vehiculoForm.getModelo());
			color.setText(vehiculoForm.getColor());
			cboTipoVehiculo.setSelectedItem(vehiculoForm.getTipoVehiculo());

			cboNumeroEstacionamiento.addItem("Asignado en BD");
			cboNumeroEstacionamiento.setSelectedIndex(1);
			cboNumeroEstacionamiento.setEnabled(false);
		}
	}

	private void save() {
		reiniciarMensajesError();

		boolean esValido = true;

		if (!validarPlaca())
			esValido = false;
		if (!validarMarca())
			esValido = false;
		if (!validarModelo())
			esValido = false;
		if (!validarColor())
			esValido = false;
		if (!validarTipo())
			esValido = false;
		if (!validarEstacionamiento())
			esValido = false;

		if (!esValido)
			return;

		try {
			String placaTxt = placa.getText().trim();
			String marcaTxt = marca.getText().trim();
			String modeloTxt = modelo.getText().trim();
			String colorTxt = color.getText().trim();
			String tipoTxt = (String) cboTipoVehiculo.getSelectedItem();

			if (vehiculoForm == null) {
				String itemSeleccionado = (String) cboNumeroEstacionamiento.getSelectedItem();
				int numeroCajon = Integer.parseInt(itemSeleccionado.replace("Espacio ", ""));

				vehiculoForm = new Vehiculo(0, placaTxt, marcaTxt, modeloTxt, colorTxt, tipoTxt);
				int idGenerado = vehiculoRepo.save(vehiculoForm);

				if (idGenerado != -1) {
					vehiculoForm.setIdVehiculo(idGenerado);
					espacioRepo.ocuparEspacio(numeroCajon, idGenerado);
				} else {
					JOptionPane.showMessageDialog(this, "Error crítico al guardar en la Base de Datos.", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				saved = true;
				dispose();

			} else {
				vehiculoForm.setPlaca(placaTxt);
				vehiculoForm.setMarca(marcaTxt);
				vehiculoForm.setModelo(modeloTxt);
				vehiculoForm.setColor(colorTxt);
				vehiculoForm.setTipoVehiculo(tipoTxt);

				saved = true;
				dispose();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private boolean validarPlaca() {
		String placaTxt = placa.getText().trim();
		if (placaTxt.isEmpty()) {
			lblErrorPlaca.setText("La placa es obligatoria");
			return false;
		}

		if (vehiculoForm == null && vehiculoRepo.existePlaca(placaTxt)) {
			lblErrorPlaca.setText("Esta placa ya está registrada");
			return false;
		}
		lblErrorPlaca.setText("");
		return true;
	}

	private boolean validarMarca() {
		if (marca.getText().trim().isEmpty()) {
			lblErrorMarca.setText("La marca es obligatoria");
			return false;
		}
		lblErrorMarca.setText("");
		return true;
	}

	private boolean validarModelo() {
		if (modelo.getText().trim().isEmpty()) {
			lblErrorModelo.setText("El modelo es obligatorio");
			return false;
		}
		lblErrorModelo.setText("");
		return true;
	}

	private boolean validarColor() {
		if (color.getText().trim().isEmpty()) {
			lblErrorColor.setText("El color es obligatorio");
			return false;
		}
		lblErrorColor.setText("");
		return true;
	}

	private boolean validarTipo() {
		if (cboTipoVehiculo.getSelectedIndex() == 0) {
			lblErrorTipo.setText("Seleccione un tipo");
			return false;
		}
		lblErrorTipo.setText("");
		return true;
	}

	private boolean validarEstacionamiento() {
		if (vehiculoForm == null && cboNumeroEstacionamiento.getSelectedIndex() == 0) {
			lblErrorEstacionamiento.setText("Seleccione un espacio");
			return false;
		}
		lblErrorEstacionamiento.setText("");
		return true;
	}

	private void asignarOyentesValidacion() {
		placa.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
			public void insertUpdate(javax.swing.event.DocumentEvent e) {
				validarPlaca();
			}

			public void removeUpdate(javax.swing.event.DocumentEvent e) {
				validarPlaca();
			}

			public void changedUpdate(javax.swing.event.DocumentEvent e) {
				validarPlaca();
			}
		});

		marca.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
			public void insertUpdate(javax.swing.event.DocumentEvent e) {
				validarMarca();
			}

			public void removeUpdate(javax.swing.event.DocumentEvent e) {
				validarMarca();
			}

			public void changedUpdate(javax.swing.event.DocumentEvent e) {
				validarMarca();
			}
		});

		modelo.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
			public void insertUpdate(javax.swing.event.DocumentEvent e) {
				validarModelo();
			}

			public void removeUpdate(javax.swing.event.DocumentEvent e) {
				validarModelo();
			}

			public void changedUpdate(javax.swing.event.DocumentEvent e) {
				validarModelo();
			}
		});

		color.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
			public void insertUpdate(javax.swing.event.DocumentEvent e) {
				validarColor();
			}

			public void removeUpdate(javax.swing.event.DocumentEvent e) {
				validarColor();
			}

			public void changedUpdate(javax.swing.event.DocumentEvent e) {
				validarColor();
			}
		});

		cboTipoVehiculo.addActionListener(e -> validarTipo());
		cboNumeroEstacionamiento.addActionListener(e -> validarEstacionamiento());
	}
}