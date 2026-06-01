package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

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

import org.mindrot.jbcrypt.BCrypt;

import models.User;
import repository.UserRepository;
import utils.AppFont;

public class UserFormDialog extends JDialog {

	private JLabel lblErrorNombre;
	private JLabel lblErrorComboEdad;
	private JLabel lblErrorPassword;
	private JLabel lblErrorEmail;
	private JLabel lblErrorCombo;
	private JLabel lblErrorApellidoPaterno;
	private JLabel lblErrorApellidoMaterno;
	private JTextField nombre;
	private JTextField apellidoPaterno;
	private JTextField apellidoMaterno;
	private JTextField password;
	private JTextField email;
	private JComboBox<String> cboOpcionesEdad;
	private JComboBox<String> cboOpcionesGenero;
	private Color botonColorNormal;
	private JButton enviarRegistro;
	private JButton botonAtras;
	private JPanel panelComponentes;

	public User userForm;
	private boolean saved = false;

	private UserRepository userRepository = new UserRepository();

	public UserFormDialog(JFrame parent, User userForm) {
		super(parent, true);
		this.userForm = userForm;

		setSize(1000, 700);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setResizable(true);
		setTitle(userForm == null ? "Agregar usuario" : "Editar usuario");
		setLocationRelativeTo(null);
		Toolkit tk = Toolkit.getDefaultToolkit();
		Image icono = tk.getImage("img/imagenVehiculo.png");
		setIconImage(icono);

		inicializarComponentes();
		loadData();
		setVisible(true);
	}

	// Getters para recuperar datos limpios
	public String getNombre() {
		return nombre.getText().trim();
	}

	public String getApellidoPaterno() {
		return apellidoPaterno.getText().trim();
	}

	public String getApellidoMaterno() {
		return apellidoMaterno.getText().trim();
	}

	public String getPassword() {
		return password.getText().trim();
	}

	public String getEmail() {
		return email.getText().trim();
	}

	public String getEdadSeleccionada() {
		return (String) cboOpcionesEdad.getSelectedItem();
	}

	public String getGeneroSeleccionado() {
		return (String) cboOpcionesGenero.getSelectedItem();
	}

	public boolean isSaved() {
		return saved;
	}

	public User getUser() {
		return userForm;
	}

	private void reiniciarMensajesError() {
		lblErrorNombre.setText("");
		lblErrorApellidoPaterno.setText("");
		lblErrorApellidoMaterno.setText("");
		lblErrorEmail.setText("");
		lblErrorPassword.setText("");
		lblErrorComboEdad.setText("");
		lblErrorCombo.setText("");
	}

	public void inicializarComponentes() {
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

		JLabel lblTitulo = new JLabel("Registro de Empleados");
		lblTitulo.setFont(AppFont.title());
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblTitulo, BorderLayout.NORTH);

		panelComponentes = new JPanel(new GridBagLayout());
		panelComponentes.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
		panelComponentes.setBackground(new Color(27, 38, 59));
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new java.awt.Insets(10, 10, 10, 10);
		c.fill = GridBagConstraints.HORIZONTAL;

		// Nombre
		c.gridx = 0;
		c.gridy = 0;
		panelComponentes.add(crearLabel("Ingresa el nombre del empleado:"), c);
		c.gridx = 1;
		nombre = new JTextField(20);
		panelComponentes.add(nombre, c);
		c.gridy = 1;
		lblErrorNombre = crearMensajeError("");
		panelComponentes.add(lblErrorNombre, c);

		// Apellido Paterno
		c.gridx = 0;
		c.gridy = 2;
		panelComponentes.add(crearLabel("Ingresa el apellido patorno del empleado: "), c);
		c.gridx = 1;
		apellidoPaterno = new JTextField(20);
		panelComponentes.add(apellidoPaterno, c);
		c.gridy = 3;
		lblErrorApellidoPaterno = crearMensajeError("");
		panelComponentes.add(lblErrorApellidoPaterno, c);

		// Apellido Materno
		c.gridx = 0;
		c.gridy = 4;
		panelComponentes.add(crearLabel("Ingresa el apellido materno del empleado: "), c);
		c.gridx = 1;
		apellidoMaterno = new JTextField(20);
		panelComponentes.add(apellidoMaterno, c);
		c.gridy = 5;
		lblErrorApellidoMaterno = crearMensajeError("");
		panelComponentes.add(lblErrorApellidoMaterno, c);

		// Email
		c.gridx = 0;
		c.gridy = 6;
		panelComponentes.add(crearLabel("Ingresa el email del empleado: "), c);
		c.gridx = 1;
		email = new JTextField(20);
		panelComponentes.add(email, c);
		c.gridy = 7;
		lblErrorEmail = crearMensajeError("");
		panelComponentes.add(lblErrorEmail, c);

		// Contraseña
		c.gridx = 0;
		c.gridy = 8;
		panelComponentes.add(crearLabel("Ingresa la contraseña del empleado:"), c);
		c.gridx = 1;
		password = new JTextField(20);
		panelComponentes.add(password, c);
		c.gridy = 9;
		lblErrorPassword = crearMensajeError("");
		panelComponentes.add(lblErrorPassword, c);

		// Edad
		c.gridx = 0;
		c.gridy = 10;
		panelComponentes.add(crearLabel("Ingresa la edad del empleado:"), c);
		c.gridx = 1;
		String[] opcionesEdad = { "Seleccione", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29",
				"30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46",
				"47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "60", "61", "62", "63",
				"64", "65", "66", "67", "68", "69", "70", "71", "72", "73", "74", "75", "76", "77", "78", "79", "80",
				"81", "82", "83", "84", "85", "86", "87", "88", "89", "90", "91", "92", "93", "94", "95", "96", "97",
				"98", "99", "100" };
		cboOpcionesEdad = new JComboBox<>(opcionesEdad);
		panelComponentes.add(cboOpcionesEdad, c);
		c.gridy = 11;
		lblErrorComboEdad = crearMensajeError("");
		panelComponentes.add(lblErrorComboEdad, c);

		// Género
		c.gridx = 0;
		c.gridy = 12;
		panelComponentes.add(crearLabel("Ingresa el genero del empleado:"), c);
		c.gridx = 1;
		String[] opcionesGenero = { "Seleccione", "Masculino", "Femenino", "Prefiero no decirlo" };
		cboOpcionesGenero = new JComboBox<>(opcionesGenero);
		panelComponentes.add(cboOpcionesGenero, c);
		c.gridy = 13;
		lblErrorCombo = crearMensajeError("");
		panelComponentes.add(lblErrorCombo, c);

		// Botón Enviar
		c.gridx = 0;
		c.gridy = 14;
		c.insets = new java.awt.Insets(20, 10, 10, 10);
		ImageIcon ilustracionBotonEnviar = new ImageIcon("img/enviar.png");
		Image imagenActualizadaBotonEnviar = ilustracionBotonEnviar.getImage().getScaledInstance(18, 18,
				Image.SCALE_SMOOTH);
		enviarRegistro = new JButton("Enviar", new ImageIcon(imagenActualizadaBotonEnviar));
		botonColorNormal = enviarRegistro.getBackground();
		panelComponentes.add(enviarRegistro, c);

		// Botón Atrás
		c.gridx = 1;
		ImageIcon ilustracionBotonAtras = new ImageIcon("img/atras.png");
		Image imagenActualizadaBotonAtras = ilustracionBotonAtras.getImage().getScaledInstance(18, 18,
				Image.SCALE_SMOOTH);
		botonAtras = new JButton("Atras", new ImageIcon(imagenActualizadaBotonAtras));
		panelComponentes.add(botonAtras, c);

		botonAtras.addActionListener(e -> dispose());
		enviarRegistro.addActionListener(e -> save());

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
		if (userForm != null) {
			nombre.setText(userForm.getNombre());
			apellidoPaterno.setText(userForm.getApellidoPaterno());
			apellidoMaterno.setText(userForm.getApellidoMaterno());
			password.setText(userForm.getPassword());
			email.setText(userForm.getEmail());
			cboOpcionesEdad.setSelectedItem(userForm.getEdad());
			cboOpcionesGenero.setSelectedItem(userForm.getGenero());
		}
	}

	private void save() {
		reiniciarMensajesError();
		boolean esValido = true;

		String nombreTxt = getNombre();
		String apellidoPaternoTxt = getApellidoPaterno();
		String apellidoMaternoTxt = getApellidoMaterno();
		String passwordTxt = getPassword();
		String emailTxt = getEmail();
		String generoTxt = getGeneroSeleccionado();
		String edadTxt = getEdadSeleccionada();

		if (nombreTxt.isEmpty()) {
			lblErrorNombre.setText("El nombre es obligatorio");
			esValido = false;
		}
		if (apellidoPaternoTxt.isEmpty()) {
			lblErrorApellidoPaterno.setText("El apellido paterno es obligatorio");
			esValido = false;
		}
		if (apellidoMaternoTxt.isEmpty()) {
			lblErrorApellidoMaterno.setText("El apellido materno es obligatorio");
			esValido = false;
		}
		if (passwordTxt.isEmpty()) {
			lblErrorPassword.setText("La contraseña es obligatoria");
			esValido = false;
		}

		if (emailTxt.isEmpty()) {
			lblErrorEmail.setText("El email es obligatorio");
			esValido = false;
		} else if (!emailTxt.contains("@")) {
			lblErrorEmail.setText("Email inválido: Falta '@'");
			esValido = false;
		}

		if (cboOpcionesEdad.getSelectedIndex() == 0) {
			lblErrorComboEdad.setText("Seleccione una edad");
			esValido = false;
		}
		if (cboOpcionesGenero.getSelectedIndex() == 0) {
			lblErrorCombo.setText("Seleccione un genero");
			esValido = false;
		}

		if (!esValido)
			return;

		try {
			if (userForm == null) {
				String hashedPassword = BCrypt.hashpw(passwordTxt, BCrypt.gensalt());

				userForm = new User(emailTxt, hashedPassword, "ADMIN", nombreTxt, apellidoPaternoTxt,
						apellidoMaternoTxt, edadTxt, generoTxt);

				userRepository.save(userForm);
				JOptionPane.showMessageDialog(this, "Usuario registrado con éxito en la base de datos.",
						"Registro Exitoso", JOptionPane.INFORMATION_MESSAGE);

			} else {
				String passwordFinal = passwordTxt;

				if (!passwordTxt.equals(userForm.getPassword())) {
					passwordFinal = BCrypt.hashpw(passwordTxt, BCrypt.gensalt());
				}

				userForm.setEmail(emailTxt);
				userForm.setPassword(passwordFinal);
				userForm.setNombre(nombreTxt);
				userForm.setApellidoPaterno(apellidoPaternoTxt);
				userForm.setApellidoMaterno(apellidoMaternoTxt);
				userForm.setEdad(edadTxt);
				userForm.setGenero(generoTxt);
			}

			saved = true;
			dispose();

		} catch (Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(this, "Error al guardar el usuario: " + ex.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}
}