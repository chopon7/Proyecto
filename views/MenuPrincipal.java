package views;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;

import utils.AppFont;

public class MenuPrincipal extends JFrame{

	MenuPrincipal() {
		setSize(1000, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(true);
		setTitle("Menu Principal");
		setLocationRelativeTo(null);

		Toolkit tk = Toolkit.getDefaultToolkit();
		Image icono = tk.getImage("img/imagenVehiculo.png");
		setIconImage(icono);
		inicializarComponentes();
		setVisible(true);
		
	}
	
	private void inicializarComponentes() {
		
		JMenuBar mb = new JMenuBar();
		setJMenuBar(mb);
		
		JMenu opciones = new JMenu ("Opciones");
		opciones.setMnemonic(KeyEvent.VK_A);
		mb.add(opciones);
		
		JMenuItem guardar = new JMenuItem("Guardar");
		guardar.setMnemonic(KeyEvent.VK_B);
		opciones.add(guardar);
		
		JMenuItem salir = new JMenuItem("Salir");
		salir.setMnemonic(KeyEvent.VK_B);
		opciones.add(salir);
		
		JMenuItem cerrarSesion = new JMenuItem("Cerrar Sesion");
		cerrarSesion.setMnemonic(KeyEvent.VK_B);
		opciones.add(cerrarSesion);
		
		
		JMenu registro = new JMenu("Registro");
		registro.setMnemonic(KeyEvent.VK_A);
		mb.add(registro);
		
		JMenuItem registrarVehiculo = new JMenuItem("Registrar Automovil");
		registrarVehiculo.setMnemonic(KeyEvent.VK_B);
		registro.add(registrarVehiculo);
		
		
		
		
	}
	
	
	
	
	
	
}
