package views;

import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JFrame;

public class Ventana extends JFrame{
	
	public Ventana () {
		
		//setSize(500,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setLocation(100,100);
		setBounds (100,100,500,500);
		setResizable(true);
		setTitle("Sistema de Estacionamiento");
		setLocationRelativeTo(null);
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		Image icono = tk.getImage("img/imagenVehiculo.png");
		setIconImage(icono);
		
		//Panel panelito = new Panel();
		
		BorderPanel panelito = new BorderPanel();
		add(panelito);
		setVisible(true);
	}

}

