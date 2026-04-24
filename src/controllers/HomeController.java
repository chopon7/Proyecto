package controllers;

import javax.swing.JOptionPane;
import views.MenuPrincipal;
import views.Ventana;

public class HomeController {

	private UserController userController;
	private MenuPrincipal vista;

	public HomeController(MenuPrincipal vista) {

		this.vista = vista;
		asignarOyentes();

	}

	public void asignarOyentes() {
		vista.getSalir().addActionListener(e -> {
			int option = JOptionPane.showConfirmDialog(vista, 
					"¿Seguro que deseas salir? Se perderán todos los datos",
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
		
		vista.getBtnUsers().addActionListener(e -> {
			showUsers();
		});
		
		
		vista.getBtnHome().addActionListener(e -> {
			vista.showView(MenuPrincipal.HOME);
			updateMenuState(MenuPrincipal.HOME);
		});
		
	
	}

	private void showUsers() {
		
		if(userController == null) {
			userController = new UserController(vista.usersPanel);
		}
		
		
		userController.loadUsers();
		
		vista.showView(MenuPrincipal.USERS);
		updateMenuState(MenuPrincipal.USERS);
		
	}
	
	private void updateMenuState(String viewName) {
		vista.btnUsers.setEnabled(!viewName.equals(MenuPrincipal.USERS));
		vista.btnHome.setEnabled(!viewName.equals(MenuPrincipal.HOME));
	}

}