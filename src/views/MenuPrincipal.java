package views;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import utils.AppFont;

public class MenuPrincipal extends JFrame {

	// Atributos
	public static final String HOME = "HOME";
	public static final String USERS = "USERS";
	public static final String REPORTE_DIARIO = "REPORTE_DIARIO";
	private CardLayout cardLayout;
	private JPanel container;
	public UserView usersPanel;
	public ReporteDiarioView reporteDiarioPanel;
	public static final String VEHICULOS = "VEHICULOS";
	public VehiculoView vehiculosPanel;
	public JButton btnUsers;
	public JButton btnVehiculos;
	public JButton btnHome;
	private JMenuItem salir;
	private JMenuItem cerrarSesion;
	private JMenuItem registrarVehiculo;
	private JMenuItem estadoEstacionamiento;
	private JMenuItem reporteDiario;
	private JMenuItem reporteSemanal;
	private JMenuItem reporteMensual;

	// Constructores
	public MenuPrincipal() {
		setSize(1400, 700);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
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

	// Getters y setters
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

	public JButton getBtnUsers() {
		return btnUsers;
	}

	public void setBtnUsers(JButton btnUsers) {
		this.btnUsers = btnUsers;
	}

	public JButton getBtnHome() {
		return btnHome;
	}

	public JButton getBtnVehiculos() {
		return btnVehiculos;
	}

	public void setBtnVehiculos(JButton btnVehiculos) {
		this.btnVehiculos = btnVehiculos;
	}

	// Metodos
	public void createNavbar() {
		JPanel navbar = new JPanel();
		navbar.setBackground(new Color(27, 38, 59));
		navbar.setLayout(new FlowLayout(FlowLayout.LEFT));

		btnHome = crearBotones("Home");
		btnHome.putClientProperty("JButton.buttonType", "roundRect");
		navbar.add(btnHome);
		btnUsers = crearBotones("Usuarios");
		btnUsers.putClientProperty("JButton.buttonType", "roundRect");
		navbar.add(btnUsers);
		btnVehiculos = crearBotones("Vehiculos");
		btnVehiculos.putClientProperty("JButton.buttonType", "roundRect");
		navbar.add(btnVehiculos);

		add(navbar, BorderLayout.NORTH);
	}

	private JButton crearBotones(String text) {
		JButton btn = new JButton(text);
		btn.setForeground(Color.BLACK);
		btn.setFont(AppFont.normal());

		return btn;
	}

	private void createViews() {
		cardLayout = new CardLayout();
		container = new JPanel(cardLayout);

		JPanel homePanel = new HomeView();

		usersPanel = new UserView();
		vehiculosPanel = new VehiculoView();
		reporteDiarioPanel = new ReporteDiarioView();
		container.add(homePanel, HOME);
		container.add(usersPanel, USERS);
		container.add(vehiculosPanel, VEHICULOS);
		container.add(reporteDiarioPanel, REPORTE_DIARIO);

		add(container, BorderLayout.CENTER);
		
	}

	public void showView(String view) {
		cardLayout.show(container, view);
	}

	private void inicializarComponentes() {

		JMenuBar mb = new JMenuBar();
		mb.setBackground(new Color(27, 38, 59));
		setJMenuBar(mb);

		JMenu opciones = new JMenu("Opciones");
		opciones.setForeground(Color.WHITE);
		opciones.setMnemonic(KeyEvent.VK_A);
		mb.add(opciones);

		salir = new JMenuItem("Salir");
		salir.setMnemonic(KeyEvent.VK_B);
		opciones.add(salir);

		cerrarSesion = new JMenuItem("Cerrar Sesion");
		cerrarSesion.setMnemonic(KeyEvent.VK_B);
		opciones.add(cerrarSesion);

		JMenu registro = new JMenu("Registro");
		registro.setForeground(Color.WHITE);
		registro.setMnemonic(KeyEvent.VK_A);
		mb.add(registro);

		registrarVehiculo = new JMenuItem("Registrar Automovil");
		registrarVehiculo.setMnemonic(KeyEvent.VK_B);
		registro.add(registrarVehiculo);

		JMenu estacionamiento = new JMenu("Estacionamiento");
		estacionamiento.setForeground(Color.WHITE);
		estacionamiento.setMnemonic(KeyEvent.VK_B);
		mb.add(estacionamiento);

		estadoEstacionamiento = new JMenuItem("Ver estado del estacionamiento");
		estadoEstacionamiento.setMnemonic(KeyEvent.VK_B);
		estacionamiento.add(estadoEstacionamiento);

		JMenu reportes = new JMenu("Reportes");
		reportes.setForeground(Color.WHITE);
		reportes.setMnemonic(KeyEvent.VK_A);
		mb.add(reportes);

		reporteDiario = new JMenuItem("Ver reporte general del estacionamiento");
		reporteDiario.setMnemonic(KeyEvent.VK_B);
		reportes.add(reporteDiario);
	}

}
