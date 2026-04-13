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

public class FormularioEmpleado extends JFrame {

	//Atributos
	private FormularioEmpleado formularioEmpleado;
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
	
	//Constructores
	public FormularioEmpleado() {

		setSize(1000, 700);
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
	
	//Getters y Setters de los mensajes de Error
	public String getLblErrorNombre() {
		return lblErrorNombre.getText();
	}

	public void setLblErrorNombre(String mensaje) {
		this.lblErrorNombre.setText(mensaje);
	}

	public String getLblErrorComboEdad() {
		return lblErrorComboEdad.getText();
	}

	public void setLblErrorComboEdad(String mensaje) {
		this.lblErrorComboEdad.setText(mensaje);
	}

	public String getLblErrorPassword() {
		return lblErrorPassword.getText();
	}

	public void setLblErrorPassword(String mensaje) {
		this.lblErrorPassword.setText(mensaje);
	}

	public String getLblErrorEmail() {
		return lblErrorEmail.getText();
	}

	public void setLblErrorEmail(String mensaje) {
		this.lblErrorEmail.setText(mensaje);
	}

	public String getLblErrorCombo() {
		return lblErrorCombo.getText();
	}

	public void setLblErrorCombo(String mensaje) {
		this.lblErrorCombo.setText(mensaje);
	}

	public String getLblErrorApellidoPaterno() {
		return lblErrorApellidoPaterno.getText();
	}

	public void setLblErrorApellidoPaterno(String mensaje) {
		this.lblErrorApellidoPaterno.setText(mensaje);
	}

	public String getLblErrorApellidoMaterno() {
		return lblErrorApellidoMaterno.getText();
	}

	public void setLblErrorApellidoMaterno(String mensaje) {
		this.lblErrorApellidoMaterno.setText(mensaje);
	}

	//Getters y Setters para los datos 
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

	public int getIndiceEdad() {
	    return cboOpcionesEdad.getSelectedIndex();
	}
	
	public String getGeneroSelecionado() {
		return (String) cboOpcionesGenero.getSelectedItem();
	}
	
	public int getIndiceGenero() {
		 return cboOpcionesGenero.getSelectedIndex();
	}
	
	public JButton getBtnEnviar() {
	    return enviarRegistro; 
	}
	
	public JButton getBtnAtras() {
	    return botonAtras; 
	}
	
	public Color getColorBotonNormal() {
		return botonColorNormal;
	}
	
	public FormularioEmpleado getFormularioEmpleado() {
        return formularioEmpleado ;
    }
	
	//Getters y Setters para los JTextField y ComboBox
	public JTextField getNombreTxt() {
		return nombre;
	}

	public JTextField getApellidoPaternoTxt() {
		return apellidoPaterno;
	}

	public JTextField getApellidoMaternoTxt() {
		return apellidoMaterno;
	}

	public JTextField getPasswordTxt() {
		return password;
	}

	public JTextField getEmailTxt() {
		return email;
	}
	
	public JComboBox<String> getCboOpcionesEdad(){
		return cboOpcionesEdad;
	}
	
	public JComboBox<String> getCboOpcionesGenero(){
		return cboOpcionesGenero;
	}

	//Metodos
	public void inicializarComponentes() {

		addWindowListener(new WindowListener() {
			
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
		
		// Titulo de la ventana
		JLabel lblTitulo = new JLabel("Registro de Empleados");
		lblTitulo.setFont(AppFont.title());
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblTitulo, BorderLayout.NORTH);

		// GridBagPanel
		panelComponentes = new JPanel(new GridBagLayout());
		panelComponentes.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
		panelComponentes.setBackground(new Color(27, 38, 59));
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new java.awt.Insets(10, 10, 10, 10);
		c.fill = GridBagConstraints.HORIZONTAL;

		// Nombre del empleado 
		c.gridx = 0;
		c.gridy = 0;
		panelComponentes.add(crearLabel("Ingresa el nombre del empleado:"), c);
		c.gridx = 1;
		nombre =  new JTextField(20);
		panelComponentes.add(nombre, c);
		c.gridy = 1;
		lblErrorNombre = crearMensajeError("");
		crearMensajeError(lblErrorNombre.getText());
		panelComponentes.add(lblErrorNombre, c);
		
		// Apellido paterno del empleado
		c.gridx = 0;
		c.gridy = 2;
		panelComponentes.add(crearLabel("Ingresa el apellido paterno del empleado: "), c);
		c.gridx = 1;
		apellidoPaterno = new JTextField(20);
		panelComponentes.add(apellidoPaterno, c);
		c.gridy = 3;
		lblErrorApellidoPaterno = crearMensajeError("");
		crearMensajeError(lblErrorApellidoPaterno.getText());
		panelComponentes.add(lblErrorApellidoPaterno, c);
		
		// Apellido materno del empleado
		c.gridx = 0;
		c.gridy = 4;
		panelComponentes.add(crearLabel("Ingresa el apellido materno del empleado: "), c);
		c.gridx = 1;
		apellidoMaterno = new JTextField(20);
		panelComponentes.add(apellidoMaterno, c);
		c.gridy = 5;
		lblErrorApellidoMaterno = crearMensajeError("");
		crearMensajeError(lblErrorApellidoMaterno.getText());
		panelComponentes.add(lblErrorApellidoMaterno, c);

		// Email del empleado
		c.gridx = 0;
		c.gridy = 6;
		panelComponentes.add(crearLabel("Ingresa el email del empleado: "), c);
		c.gridx = 1;
		email = new JTextField(20);
		panelComponentes.add(email, c);
		lblErrorEmail = crearMensajeError("");
		c.gridy = 7;
		panelComponentes.add(lblErrorEmail, c);
		
		// Codigo unico del empleado
		c.gridx = 0;
		c.gridy = 8;
		panelComponentes.add(crearLabel("Ingresa la contraseña del empleado:"), c);
		c.gridx = 1;
		password = new JTextField(20);
		panelComponentes.add(password, c);
		c.gridy = 9;
		lblErrorPassword = crearMensajeError("");
		crearMensajeError(lblErrorPassword.getText());
		panelComponentes.add(lblErrorPassword, c);
		
		// Edad del empleado
		c.gridx = 0;
		c.gridy = 10;
		panelComponentes.add(crearLabel("Ingresa la edad del empleado:"), c);
		c.gridx = 1;
		String[] opcionesEdad = {"Seleccione", "18", "19", "20",
				"21", "22", "23", "24", "25", "26", "27", "28", "29", "30",
				"31", "32", "33", "34", "35", "36", "37", "38", "39", "40",
				"41", "42", "43", "44", "45", "46", "47", "48", "49", "50",
				"51", "52", "53", "54", "55", "56", "57", "58", "59", "60",
				"61", "62", "63", "64", "65", "66", "67", "68", "69", "70",
				"71", "72", "73", "74", "75", "76", "77", "78", "79", "80",
				"81", "82", "83", "84", "85", "86", "87", "88", "89", "90", 
				"91", "92", "93", "94", "95", "96", "97", "98", "99", "100"};
		cboOpcionesEdad = new JComboBox<>(opcionesEdad);
		panelComponentes.add(cboOpcionesEdad, c);
		lblErrorComboEdad = crearMensajeError("");
		c.gridy = 11;
		crearMensajeError(lblErrorComboEdad.getText());
		panelComponentes.add(lblErrorComboEdad, c);
		
		// Genero del empleado
		c.gridx = 0;
		c.gridy = 12;
		panelComponentes.add(crearLabel("Ingresa el genero del empleado:"), c);
		c.gridx = 1;
		String[] opcionesGenero = {"Seleccione" ,"Masculino", "Femenino", "Prefiero no decirlo" };
		cboOpcionesGenero = new JComboBox<>(opcionesGenero);
		panelComponentes.add(cboOpcionesGenero, c);
		c.gridy = 13;
		lblErrorCombo = crearMensajeError("");
		crearMensajeError(lblErrorCombo.getText());
		panelComponentes.add(lblErrorCombo, c);
		

		// Boton de registro
		c.gridx = 0;
		c.gridy = 14;
		c.insets = new java.awt.Insets(20, 10, 10, 10);
		ImageIcon ilustracionBotonEnviar = new ImageIcon("img/enviar.png");
		Image imagenActualizadaBotonEnviar = ilustracionBotonEnviar.getImage().getScaledInstance(18, 18, Image.SCALE_SMOOTH);
		ImageIcon iconoFinalBotonEnviar = new ImageIcon(imagenActualizadaBotonEnviar);
		enviarRegistro = new JButton("Enviar", iconoFinalBotonEnviar);
		botonColorNormal = enviarRegistro.getBackground();
		panelComponentes.add(enviarRegistro, c);

		// Boton atras
		c.gridx = 1;
		c.gridy = 14;	
		ImageIcon ilustracionBotonAtras = new ImageIcon("img/atras.png");
		Image imagenActualizadaBotonAtras = ilustracionBotonAtras.getImage().getScaledInstance(18, 18, Image.SCALE_SMOOTH);
		ImageIcon iconoFinalBotonAtras = new ImageIcon(imagenActualizadaBotonAtras);
		
		botonAtras = new JButton("Atras", iconoFinalBotonAtras);
		panelComponentes.add(botonAtras, c);

		// Scroll
		JScrollPane scroll = new JScrollPane(panelComponentes);
		add(scroll, BorderLayout.CENTER);
		botonColorNormal = botonAtras.getBackground();
	}

	private JLabel crearLabel(String texto) {
		JLabel label = new JLabel(texto);
		label.setFont(AppFont.normal());
		label.setForeground(Color.WHITE);
		return label;
	}
	
	private void handleClose() {
		int opcion = JOptionPane.showConfirmDialog(this, "Seguro que desea cerrar la ventana? Se perderan los datos del formulario");
		
		if(opcion == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
		
	}
	
	private JLabel crearMensajeError(String mensaje) {
		JLabel label = new JLabel();
		label.setText(mensaje);
		label.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		label.setForeground(Color.RED);
		label.setHorizontalAlignment(SwingConstants.LEFT);
		label.setMaximumSize(new Dimension(Integer.MAX_VALUE, label.getPreferredSize().height));

		return label;
	}
	
}