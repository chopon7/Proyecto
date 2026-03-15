package views;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class MenuPrincipal extends JFrame{

	JMenuItem salir;
	JMenuItem cerrarSesion;
	JMenuItem registrarVehiculo;
	JMenuItem estadoEstacionamiento;
	JMenuItem reporteDiario;
	JMenuItem reporteSemanal;
	JMenuItem reporteMensual;
	
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
		
		addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				System.out.println("Se abrió la ventana");
				
			}
			
			@Override
			public void windowIconified(WindowEvent e) {
				System.out.println("Se minimizó");
				
			}
			
			@Override
			public void windowDeiconified(WindowEvent e) {
				System.out.println("Se volvió a abrir");
				
			}
			
			@Override
			public void windowDeactivated(WindowEvent e) {
				System.out.println("Perdió el focus");
				
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
				handleClose();
				
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				System.out.println("Se cerró");
				
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
				System.out.println("Obtuvo el focus");
				
			}
		});
		
		
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

		
		JMenu estacionamiento = new JMenu("Estacionamiento");
		estacionamiento.setMnemonic(KeyEvent.VK_B);
		mb.add(estacionamiento);
		
		estadoEstacionamiento = new JMenuItem("Ver estado del estacionamiento");
		estadoEstacionamiento.setMnemonic(KeyEvent.VK_B);
		estacionamiento.add(estadoEstacionamiento);
		
		
		//Posible opcion que podriamos dejar aparte o combinarla con la de estacionamiento
		JMenu reportes = new JMenu("Reportes");
		reportes.setMnemonic(KeyEvent.VK_A);
		mb.add(reportes);
		
		reporteDiario = new JMenuItem("Ver reporte diario del estacionamiento");
		reporteDiario.setMnemonic(KeyEvent.VK_B);
		reportes.add(reporteDiario);
		
		reporteSemanal = new JMenuItem("Ver reporte semanal del estacionamiento");
		reporteSemanal.setMnemonic(KeyEvent.VK_B);
		reportes.add(reporteSemanal);
		
		reporteMensual = new JMenuItem("Ver reporte mensual del estacionamiento");
		reporteMensual.setMnemonic(KeyEvent.VK_B);
		reportes.add(reporteMensual);
		
		
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
			int option = JOptionPane.showConfirmDialog(this, "¿Seguro que deseas cerrar sesion? Los datos no guardados se perderán", "¿Seguro?", JOptionPane.YES_NO_OPTION);
			
			if(option == JOptionPane.YES_OPTION) {
				new Ventana();
				dispose();
			}
		});
	}
	
	private void handleClose() {
		int opcion = JOptionPane.showConfirmDialog(this, "Seguro que desea cerrar la ventana?");
		
		if(opcion == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
		
	}
	
}
