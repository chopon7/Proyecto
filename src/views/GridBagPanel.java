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
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import utils.AppFont;
import controllers.LoginController;

public class GridBagPanel extends JPanel {

	Ventana miVentana;
	JTextField usuario;
	JPasswordField contrasena;
	JLabel mensajeUsuario;
	JLabel mensajeContrasena;
	Color botonColorNormal;
	JButton iniciarSesion;
	JButton registrar;
	LoginController controller;

	public GridBagPanel(Ventana miVentana) {

		this.miVentana = miVentana;

		setLayout(new GridBagLayout());
		setBackground(new Color(27, 38, 59));

		miVentana.addWindowListener(new WindowListener() {

			public void windowOpened(WindowEvent e) {
			}

			public void windowIconified(WindowEvent e) {
			}

			public void windowDeiconified(WindowEvent e) {
			}

			public void windowDeactivated(WindowEvent e) {
			}

			public void windowClosed(WindowEvent e) {
			}

			public void windowActivated(WindowEvent e) {
			}

			public void windowClosing(WindowEvent e) {
				handleClose();
			}
		});

		inicializarComponentes();

		controller = new LoginController(this);
	}

	private void inicializarComponentes() {

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(8, 8, 8, 8);
		gbc.fill = GridBagConstraints.HORIZONTAL;

		JLabel saludo = new JLabel("Bienvenido");
		saludo.setFont(AppFont.bigTitle());
		saludo.setForeground(Color.WHITE);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		add(saludo, gbc);

		gbc.gridwidth = 1;

		JLabel textoUsuario = new JLabel("Empleado:");
		textoUsuario.setFont(AppFont.normal());
		textoUsuario.setForeground(Color.WHITE);
		gbc.gridx = 0;
		gbc.gridy = 1;
		add(textoUsuario, gbc);

		usuario = new JTextField(15);
		asignarFocusCampoTexto(usuario);
		gbc.gridx = 1;
		add(usuario, gbc);

		mensajeUsuario = new JLabel("");
		mensajeUsuario.setForeground(Color.YELLOW);
		gbc.gridx = 1;
		gbc.gridy = 2;
		add(mensajeUsuario, gbc);

		JLabel textoContrasena = new JLabel("Contraseña:");
		textoContrasena.setFont(AppFont.normal());
		textoContrasena.setForeground(Color.WHITE);
		gbc.gridx = 0;
		gbc.gridy = 3;
		add(textoContrasena, gbc);

		contrasena = new JPasswordField(15);
		asignarFocusCampoCodigo(contrasena);
		gbc.gridx = 1;
		add(contrasena, gbc);

		mensajeContrasena = new JLabel("");
		mensajeContrasena.setForeground(Color.YELLOW);
		gbc.gridx = 1;
		gbc.gridy = 4;
		add(mensajeContrasena, gbc);

		ImageIcon iconLogin = new ImageIcon("img/inicioSesion.png");
		Image imgLogin = iconLogin.getImage().getScaledInstance(18, 18, Image.SCALE_SMOOTH);
		iniciarSesion = new JButton("Iniciar sesion", new ImageIcon(imgLogin));

		gbc.gridx = 0;
		gbc.gridy = 5;
		add(iniciarSesion, gbc);

		asignarOyenteMouse(iniciarSesion);

		ImageIcon iconReg = new ImageIcon("img/usuarioNuevo.png");
		Image imgReg = iconReg.getImage().getScaledInstance(18, 18, Image.SCALE_SMOOTH);
		registrar = new JButton("Registrar", new ImageIcon(imgReg));

		gbc.gridx = 1;
		add(registrar, gbc);

		botonColorNormal = registrar.getBackground();
		asignarOyenteMouse(registrar);
	}

	private void asignarOyenteMouse(JButton boton) {
		boton.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				boton.setBackground(Color.GRAY);
			}

			public void mouseExited(MouseEvent e) {
				boton.setBackground(botonColorNormal);
			}
		});
	}

	private void asignarFocusCampoTexto(JTextField campo) {
		campo.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				campo.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
			}

			public void focusLost(FocusEvent e) {
				campo.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
			}
		});
	}

	private void asignarFocusCampoCodigo(JPasswordField campo) {
		campo.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				campo.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
			}

			public void focusLost(FocusEvent e) {
				campo.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
			}
		});
	}

	private void handleClose() {
		int opcion = javax.swing.JOptionPane.showConfirmDialog(miVentana, "Seguro que desea cerrar la ventana?");
		if (opcion == javax.swing.JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}

	public JTextField getUsuarioField() {
		return usuario;
	}

	public JPasswordField getContrasenaField() {
		return contrasena;
	}

	public JButton getIniciarSesion() {
		return iniciarSesion;
	}

	public JButton getRegistrar() {
		return registrar;
	}

	public String getUsuario() {
		return usuario.getText();
	}

	public String getPassword() {
		return String.valueOf(contrasena.getPassword());
	}

	public Ventana getVentana() {
		return miVentana;
	}

	public void setMensajeUsuario(String mensaje) {
		mensajeUsuario.setText(mensaje);
	}

	public void setMensajeContrasena(String mensaje) {
		mensajeContrasena.setText(mensaje);
	}

	public void limpiarMensajes() {
		mensajeUsuario.setText("");
		mensajeContrasena.setText("");
	}
}