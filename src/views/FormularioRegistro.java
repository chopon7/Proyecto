package views;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.BorderFactory;
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
	private JTextField color;
	private JTextField marca;
	private JTextField modelo;
	private JTextField placa;
	private JComboBox<String> cboOpcionesTipoCarro;
	
	public FormularioRegistro() {

		setSize(1000, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(true);
		setTitle("Formulario de Registro");
		setLocationRelativeTo(null);

		Toolkit tk = Toolkit.getDefaultToolkit();
		Image icono = tk.getImage("img/imagenVehiculo.png");
		setIconImage(icono);

		inicializarComponentes();

		setVisible(true);

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
		marca =  new JTextField(20);
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
		lblErrorPlaca = crearMensajeError("");
		c.gridy = 3;
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
		lblErrorColor = crearMensajeError("");
		c.gridy = 7;
		panelComponentes.add(lblErrorColor, c);
		
		
		// Tipo de auto
		c.gridx = 0;
		c.gridy = 8;
		panelComponentes.add(crearLabel("Ingresa el tipo de auto:"), c);
		c.gridx = 1;
		String[] opcionesTipoCarro = {"Seleccione" ,"Camioneta", "Sedan", "Motocicleta", "Super Deportivo" };
		cboOpcionesTipoCarro = new JComboBox<>(opcionesTipoCarro);
		panelComponentes.add(cboOpcionesTipoCarro, c);
		c.gridy = 9;
		lblErrorCombo = crearMensajeError("");
		crearMensajeError(lblErrorCombo.getText());
		panelComponentes.add(lblErrorCombo, c);
		

		// Boton de registro
		c.gridx = 0;
		c.gridy = 10;
		c.insets = new java.awt.Insets(20, 10, 10, 10);
		JButton enviarRegistro = new JButton("Enviar");
		enviarRegistro.addActionListener(e -> validar());
		panelComponentes.add(enviarRegistro, c);

		// Boton atras
		c.gridx = 1;
		c.gridy = 10;
		JButton botonAtras = new JButton("Atras");
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

	private void reiniciarMensajeError() {
		lblErrorModelo.setText("");
		lblErrorColor.setText("");
		lblErrorPlaca.setText("");
		lblErrorMarca.setText("");
		lblErrorCombo.setText("");
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
			JOptionPane.showMessageDialog(this, "Registro exitoso");
		}
		
		if (!validar) {
			JOptionPane.showMessageDialog(this, "No se han completado los campos solicitados", "Error", JOptionPane.ERROR_MESSAGE);
			return; 
		}
		
		
		return;	
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
	
	private boolean validarMarca() {

		if (marca.getText().trim().isEmpty()) {
			lblErrorMarca.setText("La marca es obligatoria");
			return false;
		}

		return true;
	}
	
	private boolean validarModelo() {

		if (modelo.getText().trim().isEmpty()) {
			lblErrorModelo.setText("El modelo es obligatorio");
			return false;
		}

		return true;
	}
	
	private boolean validarPlaca() {

		if (placa.getText().trim().isEmpty()) {
			lblErrorPlaca.setText("La placa es obligatoria");
			return false;
		}

		return true;
	}
	
	private boolean validarColor() {

		if (color.getText().trim().isEmpty()) {
			lblErrorColor.setText("El color es obligatorio");
			return false;
		}

		return true;
	}

	private boolean validarComboBox() {

		if (cboOpcionesTipoCarro.getSelectedIndex() == 0) {
			lblErrorCombo.setText("Seleccione un tipo de carro");
			return false;
		}

		return true;
	}
	
}
