package views;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Panel extends JPanel{
	
	Font fuente;
	
	public Panel() {
		setBackground(Color.BLACK);
		setLayout(null);
		
		/*
		JButton boton = new JButton ("Mi boton");
		boton.setBounds(0, 0, 100, 30);
		boton.setBackground(Color.WHITE);
		boton.setForeground(Color.BLACK);
		boton.setToolTipText("Haz click aqui");

		add(boton);
		*/
		
		JLabel saludo = new JLabel ("Bienvenido");
		saludo.setFont(new Font ("Arial", Font.PLAIN, 30));
		saludo.setBounds(0, 0, 300, 200);
		saludo.setBackground(Color.GRAY);
		add(saludo);
		
		JTextField textField = new JTextField();
		textField.setFont(new Font ("Arial", Font.PLAIN,30));
		textField.setBounds(10,180,200,50);
		add(textField);
		
		JPasswordField password = new JPasswordField();
		password.setBounds(10, 240, 200, 50);
		password.setFont(new Font("Arial", Font.PLAIN, 40));
		add (password);
		
		
	}

}
