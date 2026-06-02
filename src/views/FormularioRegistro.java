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

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import utils.AppFont;

public class FormularioRegistro extends JFrame {

	private JLabel lblErrorMarca;
	private JLabel lblErrorPlaca;
	private JLabel lblErrorModelo;
	private JLabel lblErrorColor;
	private JLabel lblErrorCombo;
	private JLabel lblErrorEstacionamiento;
	private JTextField color;
	private JTextField marca;
	private JTextField modelo;
	private JButton enviarRegistro;
	private JButton botonAtras;
	private JTextField placa;
	private Color botonColorNormal;
	private JComboBox<String> cboNumeroEstacionamiento;
	private JComboBox<String> cboOpcionesTipoCarro;

	// Constructores
	public FormularioRegistro() {

		setSize(1000, 600);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setResizable(true);
		setTitle("Formulario de Registro");
		setLocationRelativeTo(null);

		Toolkit tk = Toolkit.getDefaultToolkit();
		Image icono = tk.getImage("img/imagenVehiculo.png");
		setIconImage(icono);

		inicializarComponentes();
		setVisible(true);

	}

	// Getters y setters
	public JTextField getColor() {
		return color;
	}

	public JTextField getMarca() {
		return marca;
	}

	public JTextField getModelo() {
		return modelo;
	}

	public JTextField getPlaca() {
		return placa;
	}

	public JComboBox<String> getCboOpcionesTipoCarro() {
		return cboOpcionesTipoCarro;
	}

	public void setColor(JTextField color) {
		this.color = color;
	}

	public void setMarca(JTextField marca) {
		this.marca = marca;
	}

	public void setModelo(JTextField modelo) {
		this.modelo = modelo;
	}

	public void setPlaca(JTextField placa) {
		this.placa = placa;
	}

	public void setCboOpcionesTipoCarro(JComboBox<String> cboOpcionesTipoCarro) {
		this.cboOpcionesTipoCarro = cboOpcionesTipoCarro;
	}

	public JLabel getLblErrorMarca() {
		return lblErrorMarca;
	}

	public JLabel getLblErrorPlaca() {
		return lblErrorPlaca;
	}

	public JLabel getLblErrorModelo() {
		return lblErrorModelo;
	}

	public JLabel getLblErrorColor() {
		return lblErrorColor;
	}

	public JLabel getLblErrorCombo() {
		return lblErrorCombo;
	}

	public void setLblErrorMarca(String lblErrorMarca) {
		this.lblErrorMarca.setText(lblErrorMarca);
	}

	public void setLblErrorPlaca(String lblErrorPlaca) {
		this.lblErrorPlaca.setText(lblErrorPlaca);
	}

	public void setLblErrorModelo(String lblErrorModelo) {
		this.lblErrorModelo.setText(lblErrorModelo);
	}

	public void setLblErrorColor(String lblErrorColor) {
		this.lblErrorColor.setText(lblErrorColor);
	}

	public void setLblErrorCombo(String lblErrorCombo) {
		this.lblErrorCombo.setText(lblErrorCombo);
	}

	public Color getBotonColorNormal() {
		return botonColorNormal;
	}

	public void setBotonColorNormal(Color botonColorNormal) {
		this.botonColorNormal = botonColorNormal;
	}

	public JButton getEnviarRegistro() {
		return enviarRegistro;
	}

	public JButton getBotonAtras() {
		return botonAtras;
	}

	public void setEnviarRegistro(JButton enviarRegistro) {
		this.enviarRegistro = enviarRegistro;
	}

	public void setBotonAtras(JButton botonAtras) {
		this.botonAtras = botonAtras;
	}

	public JComboBox<String> getCboNumeroEstacionamiento() {
	    return cboNumeroEstacionamiento;
	}

	public JLabel getLblErrorEstacionamiento() {
	    return lblErrorEstacionamiento;
	}

	public void setLblErrorEstacionamiento(String mensaje) {
	    this.lblErrorEstacionamiento.setText(mensaje);
	}
	
	public void inicializarComponentes() {

		// Titulo de la ventana
		JLabel lblTitulo = new JLabel("Registro de Autos");
		lblTitulo.setFont(AppFont.title());
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblTitulo, BorderLayout.NORTH);

		// GridBagPanel
		JPanel panelComponentes = new JPanel(new GridBagLayout());
		panelComponentes.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
		panelComponentes.setBackground(new Color(27, 38, 59));
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new java.awt.Insets(10, 10, 10, 10);
		c.fill = GridBagConstraints.HORIZONTAL;

		// Marca del auto
		c.gridx = 0;
		c.gridy = 0;
		panelComponentes.add(crearLabel("Ingresa la marca del auto:"), c);
		c.gridx = 1;
		marca = new JTextField(20);
		panelComponentes.add(marca, c);
		c.gridy = 1;
		lblErrorMarca = crearMensajeError("");
		crearMensajeError(lblErrorMarca.getText());
		panelComponentes.add(lblErrorMarca, c);

		// Placa del auto
		c.gridx = 0;
		c.gridy = 2;
		panelComponentes.add(crearLabel("Ingresa la placa del auto:"), c);
		c.gridx = 1;
		placa = new JTextField(20);
		panelComponentes.add(placa, c);
		c.gridy = 3;
		lblErrorPlaca = crearMensajeError("");
		crearMensajeError(lblErrorPlaca.getText());
		panelComponentes.add(lblErrorPlaca, c);

		// Modelo del auto
		c.gridx = 0;
		c.gridy = 4;
		panelComponentes.add(crearLabel("Ingresa el modelo del auto:"), c);
		c.gridx = 1;
		modelo = new JTextField(20);
		panelComponentes.add(modelo, c);
		c.gridy = 5;
		lblErrorModelo = crearMensajeError("");
		crearMensajeError(lblErrorModelo.getText());
		panelComponentes.add(lblErrorModelo, c);

		// Color del auto
		c.gridx = 0;
		c.gridy = 6;
		panelComponentes.add(crearLabel("Ingresa el color del auto:"), c);
		c.gridx = 1;
		color = new JTextField(20);
		panelComponentes.add(color, c);
		c.gridy = 7;
		lblErrorColor = crearMensajeError("");
		crearMensajeError(lblErrorColor.getText());
		panelComponentes.add(lblErrorColor, c);

		// Tipo de auto
		c.gridx = 0;
		c.gridy = 8;
		panelComponentes.add(crearLabel("Ingresa el tipo de auto:"), c);
		c.gridx = 1;
		String[] opcionesTipoCarro = { "Seleccione", "Automovil", "Camioneta", "Super Deportivo", "SUV", "Otro" };
		cboOpcionesTipoCarro = new JComboBox<>(opcionesTipoCarro);
		panelComponentes.add(cboOpcionesTipoCarro, c);
		c.gridy = 9;
		lblErrorCombo = crearMensajeError("");
		crearMensajeError(lblErrorCombo.getText());
		panelComponentes.add(lblErrorCombo, c);
		
		// Numero de estacionamiento
		c.gridx = 0;
		c.gridy = 10;
		panelComponentes.add(crearLabel("Selecciona el cajón:"), c);
		c.gridx = 1;
		cboNumeroEstacionamiento = new JComboBox<>();
		cboNumeroEstacionamiento.addItem("Seleccione un espacio"); 
		panelComponentes.add(cboNumeroEstacionamiento, c);
		c.gridy = 11;
		lblErrorEstacionamiento = crearMensajeError("");
		crearMensajeError(lblErrorEstacionamiento.getText());
		panelComponentes.add(lblErrorEstacionamiento, c);
		
		// Boton de registro
		c.gridx = 0;
		c.gridy = 12;
		c.insets = new java.awt.Insets(20, 10, 10, 10);
		ImageIcon ilustracionBotonEnviar = new ImageIcon("img/enviar.png");
		Image imagenActualizadaBotonEnviar = ilustracionBotonEnviar.getImage().getScaledInstance(18, 18, Image.SCALE_SMOOTH);
		ImageIcon iconoFinalBotonEnviar = new ImageIcon(imagenActualizadaBotonEnviar);
		enviarRegistro = new JButton("Enviar", iconoFinalBotonEnviar);
		botonColorNormal = enviarRegistro.getBackground();
		panelComponentes.add(enviarRegistro, c);

		// Boton atras
		c.gridx = 1;
		c.gridy = 12;
		ImageIcon ilustracionBotonAtras = new ImageIcon("img/atras.png");
		Image imagenActualizadaBotonAtras = ilustracionBotonAtras.getImage().getScaledInstance(18, 18, Image.SCALE_SMOOTH);
		ImageIcon iconoFinalBotonAtras = new ImageIcon(imagenActualizadaBotonAtras);
		botonAtras = new JButton("Atras", iconoFinalBotonAtras);
		botonColorNormal = botonAtras.getBackground();
		panelComponentes.add(botonAtras, c);

		// Scroll
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
		JLabel label = new JLabel();
		label.setText(mensaje);
		label.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		label.setForeground(Color.RED);
		label.setHorizontalAlignment(SwingConstants.LEFT);
		label.setMaximumSize(new Dimension(Integer.MAX_VALUE, label.getPreferredSize().height));

		return label;
	}
	
}
