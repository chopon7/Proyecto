package views;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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
		
		JLabel lblTitulo = new JLabel("Registro de Autos");
		lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
		add(lblTitulo, BorderLayout.NORTH);
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);

		
		JPanel panelComponentes = new JPanel();
		panelComponentes.setLayout(new BoxLayout(panelComponentes, BoxLayout.Y_AXIS));
		panelComponentes.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
		panelComponentes.setBackground(new Color(27,38,59));
		
		JLabel lblMarcaCarro = new JLabel("Ingresa la marca del auto: ");
		lblMarcaCarro.setForeground(Color.WHITE);
		panelComponentes.add(lblMarcaCarro);
		JTextField txtMarcaCarro = new JTextField(20);
		panelComponentes.add(txtMarcaCarro);
		
		JLabel lblPlacaCarro = new JLabel("Ingresa la placa del auto:");
		lblPlacaCarro.setForeground(Color.WHITE);
		panelComponentes.add(lblPlacaCarro);
		JTextField txtPlacaCarro = new JTextField(20);
		panelComponentes.add(txtPlacaCarro);
		
		
		JLabel lblCantidadRuedas = new JLabel("Ingresa el modelo del auto:");
		lblCantidadRuedas.setForeground(Color.WHITE);
		panelComponentes.add(lblCantidadRuedas);
		JTextField txtCantidadRuedas = new JTextField(20);
		panelComponentes.add(txtCantidadRuedas);

		JLabel lblColorCarro = new JLabel("Ingresa el color del auto:");
		lblColorCarro.setForeground(Color.WHITE);
		panelComponentes.add(lblColorCarro);
		JTextField txtColorCarro = new JTextField(20);
		panelComponentes.add(txtColorCarro);
		
		JLabel lblTipoCarro = new JLabel("Ingresa el tipo de auto:");
		lblTipoCarro.setForeground(Color.WHITE);
		panelComponentes.add(lblTipoCarro);
		
		String[] opcionesTipoCarro = {"Camioneta", "Sedan", "Motocicleta", "Super Deportivo"};
		JComboBox<String> cbTipoCarro = new JComboBox<String> (opcionesTipoCarro);
		cbTipoCarro.setSelectedIndex(0);
		panelComponentes.add(cbTipoCarro);
		
		
		panelComponentes.add(Box.createRigidArea(new Dimension(0,15))); 
		JButton enviarRegistro = new JButton ("Enviar");
		panelComponentes.add(enviarRegistro, BorderLayout.SOUTH);
		
		
		JScrollPane scroll = new JScrollPane(panelComponentes);
		scroll.setHorizontalScrollBar(null);
		
		
		add(scroll);
		
		
	}
	
	
	
	
	
	
}
