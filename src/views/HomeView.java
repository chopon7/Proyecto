package views;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import utils.AppFont;

public class HomeView extends JPanel {
	
	
	public HomeView() {
		createHomeView();
		
	}
	
	private void createHomeView() {
	    this.setLayout(new GridBagLayout()); 
	    this.setBackground(new Color(27, 38, 59));
	    
	    GridBagConstraints gbc = new GridBagConstraints();
	    gbc.insets = new Insets(20, 10, 20, 10);

	    gbc.gridwidth = 1;
	    gbc.gridy = 1;
	    gbc.gridx = 0;
	    JLabel textoTituloFuera = new JLabel("Estacionamiento (Por fuera)");
	    textoTituloFuera.setFont(AppFont.mediumTitle());
	    textoTituloFuera.setForeground(Color.WHITE);
	    this.add(textoTituloFuera, gbc);
	    
	    gbc.gridx = 1;
	    JLabel textoTituloDentro = new JLabel("Estacionamiento (Por dentro)");
	    textoTituloDentro.setForeground(Color.WHITE);
	    textoTituloDentro.setFont(AppFont.mediumTitle());
	    this.add(textoTituloDentro, gbc);

	    gbc.gridy = 2;
	    gbc.gridx = 0;
	    ImageIcon iconoOriginalFuera = new ImageIcon("img/estacionamientoFuera.jpg");
	    Image imagenEscaladaFuera = iconoOriginalFuera.getImage().getScaledInstance(600, 300, Image.SCALE_SMOOTH);
	    this.add(new JLabel(new ImageIcon(imagenEscaladaFuera)), gbc);
	    
	    gbc.gridx = 1;
	    ImageIcon iconoOriginalDentro = new ImageIcon("img/estacionamientoDentro.jpg");
	    Image imagenEscaladaDentro = iconoOriginalDentro.getImage().getScaledInstance(600, 300, Image.SCALE_SMOOTH);
	    this.add(new JLabel(new ImageIcon(imagenEscaladaDentro)), gbc);

	    gbc.gridy = 3;
	    gbc.gridx = 0;
	    gbc.gridwidth = 2;
	    gbc.fill = GridBagConstraints.BOTH; 
	    gbc.insets = new Insets(20, 10, 10, 10); 

	    JTextArea desc = new JTextArea();
	    desc.setText("RESUMEN OPERATIVO: El complejo cuenta con una infraestructura de 30 cajones exclusivos diseñados para optimizar el flujo vehicular. "
	            + "Ofrecemos una tarifa de 30 pesos por hora, garantizando un servicio estándar de alta calidad.\n\n"
	            + "UBICACIÓN ESTRATÉGICA: Situado en la colonia Paraíso de Dubái, entre Bahía Yeing y Bahía de Nigrut. "
	            + "Instalaciones acondicionadas para todo tipo de vehículos de cuatro ruedas.");

	    desc.setBackground(new Color(27, 38, 59));
	    desc.setForeground(new Color(220, 220, 220));
	    desc.setLineWrap(true);
	    desc.setWrapStyleWord(true);
	    desc.setEditable(false);
	    desc.setFont(AppFont.normal()); 

	    desc.setMargin(new Insets(10, 10, 10, 10)); 

	    this.add(desc, gbc);
	}
	
	
	

}
