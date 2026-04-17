package views;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class MenuPrincipal extends JFrame{
	
	//Atributos
	public static final String HOME = "HOME";
	public static final String USERS = "USERS";
	private CardLayout cardLayout;
	private JPanel container;
	public JButton btnUsers;
	public UserView usersPanel;
	private JMenuItem salir;
	private JMenuItem cerrarSesion;
	private JMenuItem registrarVehiculo;
	private JMenuItem estadoEstacionamiento;
	private JMenuItem reporteDiario;
	private JMenuItem reporteSemanal;
	private JMenuItem reporteMensual;
	
	//Constructores
	public MenuPrincipal() {
		setSize(1000, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(true);
		setTitle("Menu Principal");
		setLocationRelativeTo(null);

		Toolkit tk = Toolkit.getDefaultToolkit();
		Image icono = tk.getImage("img/imagenVehiculo.png");
		setIconImage(icono);
		createNavbar();
		createViews();
		inicializarComponentes();
		setVisible(true);
		
	}
	
	//Getters y setters
	public JMenuItem getSalir() {
		return salir;
	}

	public JMenuItem getCerrarSesion() {
		return cerrarSesion;
	}

	public JMenuItem getRegistrarVehiculo() {
		return registrarVehiculo;
	}

	public JMenuItem getEstadoEstacionamiento() {
		return estadoEstacionamiento;
	}

	public JMenuItem getReporteDiario() {
		return reporteDiario;
	}

	public JMenuItem getReporteSemanal() {
		return reporteSemanal;
	}

	public JMenuItem getReporteMensual() {
		return reporteMensual;
	}

	public void setSalir(JMenuItem salir) {
		this.salir = salir;
	}

	public void setCerrarSesion(JMenuItem cerrarSesion) {
		this.cerrarSesion = cerrarSesion;
	}

	public void setRegistrarVehiculo(JMenuItem registrarVehiculo) {
		this.registrarVehiculo = registrarVehiculo;
	}

	public void setEstadoEstacionamiento(JMenuItem estadoEstacionamiento) {
		this.estadoEstacionamiento = estadoEstacionamiento;
	}

	public void setReporteDiario(JMenuItem reporteDiario) {
		this.reporteDiario = reporteDiario;
	}

	public void setReporteSemanal(JMenuItem reporteSemanal) {
		this.reporteSemanal = reporteSemanal;
	}

	public void setReporteMensual(JMenuItem reporteMensual) {
		this.reporteMensual = reporteMensual;
	}
	
	public JButton getBtnUsers() {
		return btnUsers;
	}

	public void setBtnUsers(JButton btnUsers) {
		this.btnUsers = btnUsers;
	}

	//Metodos
	public void createNavbar() {
		JPanel navbar = new JPanel();
		navbar.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		btnUsers = new JButton("Usuarios");
		navbar.add(btnUsers);
		
		add(navbar, BorderLayout.NORTH);
	}
	
	private void createViews() {
		cardLayout = new CardLayout();
		container = new JPanel(cardLayout);
		
		JPanel homePanel = new JPanel();
		homePanel.add(new JLabel("Bienvenido al Sistema"));
		
		usersPanel = new UserView();
		
		container.add(homePanel, HOME);
		container.add(usersPanel, USERS);
		
		add(container, BorderLayout.CENTER);
		
	}
	
	public void showView(String view) {
		cardLayout.show(container, view);
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
	}
	
	private void handleClose() {
		int opcion = JOptionPane.showConfirmDialog(this, "Seguro que desea cerrar la ventana?");

		if (opcion == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}
	
}
