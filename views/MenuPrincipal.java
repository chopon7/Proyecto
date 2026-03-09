package views;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
 



public class MenuPrincipal extends JFrame{

	JMenuItem salir;
	JMenuItem cerrarSesion;
	JMenuItem registrarVehiculo;
	
	
	
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
		
		salir = new JMenuItem("Salir");
		salir.setMnemonic(KeyEvent.VK_B);
		opciones.add(salir);
		
		cerrarSesion = new JMenuItem("Cerrar Sesion");
		cerrarSesion.setMnemonic(KeyEvent.VK_B);
		opciones.add(cerrarSesion);
		
		
		JMenu registro = new JMenu("Registro");
		registro.setMnemonic(KeyEvent.VK_A);
		mb.add(registro);
		
		registrarVehiculo = new JMenuItem("Registrar Automovil");
		registrarVehiculo.setMnemonic(KeyEvent.VK_B);
		registro.add(registrarVehiculo);
		
		JMenuItem registrarEmpleado = new JMenuItem("Registrar Empleado");
		registrarEmpleado.setMnemonic(KeyEvent.VK_B);
		registro.add(registrarEmpleado);

		asignarOyentes();
	}
	
	public void asignarOyentes() {
		salir.addActionListener(e -> {
			int option = JOptionPane.showConfirmDialog(this, "¿Seguro que deseas salir? Se perderán todos los datos", "¿Seguro?", JOptionPane.YES_NO_OPTION);
			
			if(option == JOptionPane.YES_OPTION) {
				dispose();
			}
		});
		
		cerrarSesion.addActionListener(e -> {
			int option = JOptionPane.showConfirmDialog(this, "¿Seguro que deseas cerrar sesion? Se perderán todos los datos", "¿Seguro?", JOptionPane.YES_NO_OPTION);
			
			if(option == JOptionPane.YES_OPTION) {
				new Ventana();
				dispose();
			}
		});
	}
	
}
