package views;

import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JFrame;

public class Ventana extends JFrame{
	
	public Ventana () {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds (100,100,500,500);
		setResizable(true);
		setTitle("Sistema de Estacionamiento");
		setLocationRelativeTo(null);
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		Image icono = tk.getImage("img/imagenVehiculo.png");
		setIconImage(icono);
		
		GridBagPanel panelito = new GridBagPanel();
		add(panelito);
		setVisible(true);
	}

}

