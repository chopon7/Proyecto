package controllers;

import java.io.IOException;
import java.util.List;
import javax.swing.JOptionPane;
import models.User;
import repository.UserRepository;
import tablemodels.UserTableModel;
import views.MenuPrincipal;
import views.Ventana;

public class HomeController {

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
	}

	private void showUsers() {
		UserRepository repository = new UserRepository();

		try {
			List<User> users = repository.getUsers();

			UserTableModel model = new UserTableModel(users);

			vista.usersPanel.setTableModel(model);

			vista.showView(MenuPrincipal.USERS);

		} catch (IOException ex) {
			JOptionPane.showMessageDialog(vista, ex.getMessage());
		}

	}

}