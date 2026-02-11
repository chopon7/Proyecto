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
		
		setBackground(new Color(27,38,59));
		setLayout(null);
		
		//Texto de Bienvenida
		JLabel saludo = new JLabel ("Bienvenido");
		saludo.setFont(new Font ("Arial", Font.PLAIN, 60));
		saludo.setBounds(100, -30, 300, 200);
		saludo.setForeground(new Color(224, 225, 221));
		add(saludo);
		
		//Texto de Inciar Sesion
		JLabel textoIniciarSesion = new JLabel ("Iniciar Sesion:");
		textoIniciarSesion.setFont(new Font ("Arial", Font.PLAIN, 25));
		textoIniciarSesion.setBounds(160, 25, 200, 200);
		textoIniciarSesion.setForeground(new Color(224, 225, 221));
		add(textoIniciarSesion);
		
		
		//Texto Usuario
		JLabel textoUsuario = new JLabel ("Empleado:");
		textoUsuario.setFont(new Font ("Arial", Font.PLAIN, 20));
		textoUsuario.setBounds(135, 85, 200, 200);
		textoUsuario.setForeground(new Color(224, 225, 221));
		add(textoUsuario);
		
		//Usuario
		JTextField usuario = new JTextField();
		usuario.setFont(new Font ("Arial", Font.PLAIN,30));
		usuario.setBounds(135,200,200,50);
		usuario.setBackground(new Color(65, 90, 119));
		usuario.setForeground(Color.WHITE);
		add(usuario);
		
		//Texto de Clave de Seguridad
		JLabel textoClaveDeSeguridad = new JLabel ("Clave de Seguridad:");
		textoClaveDeSeguridad.setFont(new Font ("Arial", Font.PLAIN, 20));
		textoClaveDeSeguridad.setBounds(135, 185, 200, 200);
		textoClaveDeSeguridad.setForeground(new Color(224, 225, 221));
		add(textoClaveDeSeguridad);
		
		//Clave de seguridad
		JPasswordField claveDeSeguridad = new JPasswordField();
		claveDeSeguridad.setBounds(135, 300, 200, 50);
		claveDeSeguridad.setFont(new Font("Arial", Font.PLAIN, 40));
		claveDeSeguridad.setBackground(new Color(65, 90, 119));
		claveDeSeguridad.setForeground(Color.WHITE);
		add(claveDeSeguridad);
		
		
	}

}
