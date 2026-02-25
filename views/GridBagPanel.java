package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class GridBagPanel extends JPanel {
	
	//Por ahora todo se mantiene en el centro
	public GridBagPanel() {
		
		setLayout(new GridBagLayout());
		setBackground(new Color(27,38,59));
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.insets = new Insets (8,8,8,8);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.CENTER; //Esto hace que nada se salga del centro
		
		//Texto de bienvenida
		JLabel saludo = new JLabel ("Bienvenido");
		saludo.setFont(new Font("Arial", Font.PLAIN, 60));
		saludo.setForeground(Color.WHITE);
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		
		add (saludo, gbc);
		
		//Texto Usuario
		gbc.gridwidth = 1;

        JLabel textoUsuario = new JLabel("Empleado:");
        textoUsuario.setFont(new Font("Arial", Font.PLAIN, 20));
        textoUsuario.setForeground(Color.WHITE);

        gbc.gridx = 0;
        gbc.gridy = 1;

        add(textoUsuario,gbc);

        JTextField usuario = new JTextField(15);
        gbc.gridx = 1;

        add(usuario,gbc);

		//Para el mensaje del usuario
        JLabel mensajeUsuario = new JLabel("Empleado no encontrado");
        mensajeUsuario.setFont(new Font("Arial", Font.PLAIN, 12));
        mensajeUsuario.setForeground(Color.YELLOW);

        gbc.gridx = 1;
        gbc.gridy = 2;

        add(mensajeUsuario,gbc);

		//Texto contraseña
        JLabel textoContrasena = new JLabel("Contraseña:");
        textoContrasena.setFont(new Font("Arial", Font.PLAIN, 20));
        textoContrasena.setForeground(Color.WHITE);

        gbc.gridx = 0;
        gbc.gridy = 3;

        add(textoContrasena,gbc);
        JPasswordField contrasena = new JPasswordField(15);
        gbc.gridx = 1;

        add(contrasena,gbc);
        
        //Para el mensaje de la contraseña
        JLabel mensajeContrasena = new JLabel("Contraseña incorrecta");
        mensajeContrasena.setFont(new Font("Arial", Font.PLAIN, 12));
        mensajeContrasena.setForeground(Color.YELLOW);

        gbc.gridx = 1;
        gbc.gridy = 4;

        add(mensajeContrasena,gbc);
        
        //Estos son los botones, se colocan en las coordenadas indicadas
        JButton iniciarSesion= new JButton ("Iniciar sesion");
        gbc.gridx = 0;
        gbc.gridy = 5;
        
        add(iniciarSesion, gbc);
        
        JButton salir = new JButton ("Salir");
        gbc.gridx = 1;
        
        add(salir, gbc);

	}

}
