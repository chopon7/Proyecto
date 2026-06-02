package controllers;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JOptionPane;

import config.Config;
import views.EstacionamientoView;
import views.FormularioRegistro;
import views.MenuPrincipal;
import views.Ventana;

public class HomeController {

	private VehiculoController vehiculoController;
	private UserController userController;
	private ReporteController reporteController;
	private MenuPrincipal vista;
	private EstacionamientoView vistaEstacionamiento;

	public HomeController(MenuPrincipal vista) {
		this.vista = vista;
		loadWindowPreferences();
		asignarOyentes();

	}

	public void asignarOyentes() {

		vista.addWindowListener(new WindowListener() {

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
				saveWindowPreferences();
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

		vista.getSalir().addActionListener(e -> {
			int option = JOptionPane.showConfirmDialog(vista, "¿Seguro que deseas salir? Se perderán todos los datos",
					"¿Seguro?", JOptionPane.YES_NO_OPTION);

			if (option == JOptionPane.YES_OPTION) {
				vista.dispose();
			}
		});

		vista.getCerrarSesion().addActionListener(e -> {
			int option = JOptionPane.showConfirmDialog(vista,
					"¿Seguro que deseas cerrar sesion? Los datos no guardados se perderán", "¿Seguro?",
					JOptionPane.YES_NO_OPTION);

			if (option == JOptionPane.YES_OPTION) {
				new Ventana();
				vista.dispose();
			}
		});

		vista.getBtnUsers().addActionListener(e -> showUsers());

		vista.getBtnVehiculos().addActionListener(e -> showVehiculos());

		vista.getBtnHome().addActionListener(e -> {
			vista.showView(MenuPrincipal.HOME);
			updateMenuState(MenuPrincipal.HOME);
		});

		vista.getRegistrarVehiculo().addActionListener(e -> {
			FormularioRegistro formVista = new FormularioRegistro();
			new FormularioRegistroController(formVista, vistaEstacionamiento, vehiculoController);
		});
		
		vista.getReporteDiario().addActionListener(e -> {
			showReporteDiario();
		});


		vista.getEstadoEstacionamiento().addActionListener(e -> {
			if (vistaEstacionamiento == null || !vistaEstacionamiento.isDisplayable()) {
				vistaEstacionamiento = new EstacionamientoView();
			} else {
				vistaEstacionamiento.toFront();
				vistaEstacionamiento.requestFocus();
			}
		});
	}
	
	private void showReporteDiario() {

		if (reporteController == null) {
			reporteController = new ReporteController(vista.reporteDiarioPanel);
		} else {
			reporteController.cargarReporte();
		}

		vista.showView(MenuPrincipal.REPORTE_DIARIO);
		updateMenuState(MenuPrincipal.REPORTE_DIARIO);
	}


	private void showUsers() {

		if (userController == null) {
			userController = new UserController(vista.usersPanel);
		}

		userController.loadUsers();

		vista.showView(MenuPrincipal.USERS);
		updateMenuState(MenuPrincipal.USERS);

	}

	private void showVehiculos() {
		if (vehiculoController == null) {
			vehiculoController = new VehiculoController(vista.vehiculosPanel);
		}
		vehiculoController.loadVehiculos();
		vista.showView(MenuPrincipal.VEHICULOS);
		updateMenuState(MenuPrincipal.VEHICULOS);
	}

	private void updateMenuState(String viewName) {
		vista.btnUsers.setEnabled(!viewName.equals(MenuPrincipal.USERS));
		vista.btnHome.setEnabled(!viewName.equals(MenuPrincipal.HOME));
		vista.btnVehiculos.setEnabled(!viewName.equals(MenuPrincipal.VEHICULOS));
	}

	private void saveWindowPreferences() {
		Dimension size = vista.getSize();
		Point point = vista.getLocation();

		Config.set("registration.window.width", String.valueOf(size.width));

		Config.set("registration.window.height", String.valueOf(size.height));

		Config.set("registration.window.x", String.valueOf(point.x));

		Config.set("registration.window.y", String.valueOf(point.y));

	}

	private void loadWindowPreferences() {
		int width = Integer.parseInt(Config.get("registration.window.width", "700"));

		int height = Integer.parseInt(Config.get("registration.window.height", "1500"));

		String xValue = Config.get("registration.window.x", "");

		String yValue = Config.get("registration.window.y", "");

		if (!xValue.isBlank() && !yValue.isBlank()) {
			vista.setLocation(Integer.parseInt(xValue), Integer.parseInt(yValue));
		} else {
			vista.setLocationRelativeTo(null);
		}

		vista.setSize(width, height);
	}

	private void handleClose() {
		int opcion = JOptionPane.showConfirmDialog(vista, "¿Seguro que desea cerrar la ventana?", "Confirmar salida",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

		if (opcion == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}

}