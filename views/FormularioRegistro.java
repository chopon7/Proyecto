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
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class FormularioRegistro  extends JFrame{

	public FormularioRegistro() {
		
		setSize(800, 400);
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
		
		//Titulo de la ventana
		JLabel lblTitulo = new JLabel("Registro de Autos");
		lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblTitulo, BorderLayout.NORTH);

		//GridBagPanel
		JPanel panelComponentes = new JPanel(new GridBagLayout()); 
	    panelComponentes.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
	    panelComponentes.setBackground(new Color(27, 38, 59));
	    GridBagConstraints c = new GridBagConstraints();
	    c.insets = new java.awt.Insets(5, 5, 5, 5); 
	    c.fill = GridBagConstraints.HORIZONTAL; 

	    //Marca del auto
	    c.gridx = 0; 
	    c.gridy = 0;
	    panelComponentes.add(crearLabel("Ingresa la marca del auto:"), c);
	    c.gridx = 1; 
	    panelComponentes.add(new JTextField(20), c);

	    //Placa del auto
	    c.gridx = 0; 
	    c.gridy = 1;
	    panelComponentes.add(crearLabel("Ingresa la placa del auto:"), c);
	    c.gridx = 1;
	    panelComponentes.add(new JTextField(20), c);

	    //Modelo del auto
	    c.gridx = 0; 
	    c.gridy = 2;
	    panelComponentes.add(crearLabel("Ingresa el modelo del auto:"), c);
	    c.gridx = 1;
	    panelComponentes.add(new JTextField(20), c);

	    //Color del auto
	    c.gridx = 0; 
	    c.gridy = 3;
	    panelComponentes.add(crearLabel("Ingresa el color del auto:"), c);
	    c.gridx = 1;
	    panelComponentes.add(new JTextField(20), c);

	    //Tipo de auto
	    c.gridx = 0; 
	    c.gridy = 4;
	    panelComponentes.add(crearLabel("Ingresa el tipo de auto:"), c);
	    c.gridx = 1;
	    String[] opcionesTipoCarro = {"Camioneta", "Sedan", "Motocicleta", "Super Deportivo"};
	    panelComponentes.add(new JComboBox<>(opcionesTipoCarro), c);

	    //Boton de registro
	    c.gridx = 0; 
	    c.gridy = 5;
	    c.gridwidth = 2;
	    c.insets = new java.awt.Insets(20, 0, 0, 0); 
	    JButton enviarRegistro = new JButton("Enviar");
	    panelComponentes.add(enviarRegistro, c);

	    //Scroll
	    JScrollPane scroll = new JScrollPane(panelComponentes);
	    add(scroll, BorderLayout.CENTER);
		
	}
	
	private JLabel crearLabel(String texto) {
	    JLabel label = new JLabel(texto);
	    label.setForeground(Color.WHITE);
	    return label;
	}
	
	
	
	
	
}
