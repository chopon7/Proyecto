package controllers;

import java.io.IOException;
import java.util.List;

import javax.swing.JOptionPane;

import models.User;
import repository.UserRepository;
import tablemodels.UserTableModel;
import views.UserFormDialog;
import views.UserView;

public class UserController {

	private UserView vista;
	private UserRepository repo;
	private UserTableModel model;

	public UserController(UserView vista) {
		this.vista = vista;
		repo = new UserRepository();

		this.vista.getBtnAdd().addActionListener(e -> {
			openForm(null);
		});

		this.vista.getBtnEdit().addActionListener(e -> {
			int row = vista.getSelectedRow();
			if (row == -1) {
				JOptionPane.showMessageDialog(vista, "Selecciona un usuario");
				return;
			}

			openForm(model.getUserAt(row));
		});

	}

	public void loadUsers() {
		System.out.println("Carga usuarios");
		try {
			List<User> users = repo.getUsers();

			if (model == null) {
				model = new UserTableModel(users);
				vista.setTableModel(model);
			} else {
				model.setUsers(users);
			}

		} catch (IOException ex) {
			JOptionPane.showMessageDialog(vista, ex.getMessage());
		}
	}

	private void openForm(User user) {

		UserFormDialog dialog = new UserFormDialog(null, user);

		if (dialog.isSaved()) {
			User savedUser = dialog.getUser();

			try {
				if (user == null) {
					repo.save(savedUser);
				} else {
					int row = vista.getSelectedRow();
					repo.update(row, savedUser);
				}
				loadUsers();
			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(vista, e.getMessage());
			}

		}

	}

}