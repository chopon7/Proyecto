package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BorderPanel extends JPanel{
	
	public BorderPanel() {
		setLayout(new BorderLayout());
		setBackground(Color.BLUE);
		
		//PANEL SUPERIOR
		JPanel panelSuperior = new JPanel();
		panelSuperior.setBackground(new Color(27,38,59));
		add(panelSuperior, BorderLayout.NORTH);
		
		JLabel saludo = new JLabel ("Bienvenido");
		saludo.setFont(new Font ("Arial", Font.PLAIN, 60));
		saludo.setBounds(100, -30, 300, 200);
		saludo.setForeground(new Color(224, 225, 221));

		panelSuperior.add(saludo);
		crearPanelCentro();
		crearPanelIzquierda();
		
		
		JButton btnSur = new JButton("ni");
		add(btnSur, BorderLayout.SOUTH);
	}
	
	public void crearPanelCentro() {
		
		
		//PANEL CENTRO
		JPanel panelCentro = new JPanel(new BorderLayout());
		panelCentro.setBackground(new Color(27,38,59));

		
		//PANEL CENTRO SUR
		JPanel panelCentroSur = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		panelCentro.add(panelCentroSur, BorderLayout.SOUTH);
		panelCentroSur.setBackground(new Color(27,38,59));
		
		JButton btnInicio = new JButton("Iniciar sesión");
		panelCentroSur.add(btnInicio);
		
		JButton btnCancelar = new JButton("Salir");
		panelCentroSur.add(btnCancelar);
		
		add(panelCentro, BorderLayout.CENTER);
	
	}
	
	public void crearPanelIzquierda() {
		
		//PANEL IZQUIERDA
		JPanel panelIzquierda = new JPanel(new BorderLayout());
		panelIzquierda.setBackground(new Color(0,0,0));
		
		//Texto de Inciar Sesion
		JLabel textoIniciarSesion = new JLabel ("Iniciar Sesion:");
		textoIniciarSesion.setFont(new Font ("Arial", Font.PLAIN, 25));
		textoIniciarSesion.setForeground(Color.WHITE);
		panelIzquierda.add(textoIniciarSesion, BorderLayout.WEST);
		
		//PANEL IZQUIERDA
		JPanel panelIzquierdaAbajo = new JPanel(new BorderLayout());
		panelIzquierdaAbajo.setBackground(new Color(0,0,0));
		
		//Texto Usuario
		JLabel textoUsuario = new JLabel ("Empleado:");
		textoUsuario.setFont(new Font ("Arial", Font.PLAIN, 20));
		textoUsuario.setForeground(Color.WHITE);
		panelIzquierdaAbajo.add(textoUsuario, BorderLayout.WEST);
		
		
		add(panelIzquierda, BorderLayout.WEST);
		
		

		
	}
	
	
}







