package controllers;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.JOptionPane;

import models.User;
import repository.UserRepository;
import services.PDFExporter;
import tablemodels.UserTableModel;
import views.UserFormDialog;
import views.UserView;

public class UserController {

	private UserView vista;
	private UserRepository repo;
	private UserTableModel model;
	private PDFExporter pdfExporter;

	public UserController(UserView vista) {
		this.vista = vista;
		repo = new UserRepository();
		pdfExporter = new PDFExporter();

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
		
		this.vista.getBtnDelete().addActionListener(e -> {
			int row = vista.getSelectedRow();
			
			if (row == -1) {
				JOptionPane.showMessageDialog(vista, "Selecciona un usuario");
				return;
			}
			
			int confirm = JOptionPane.showConfirmDialog(
				vista,
				"¿Seguro que deseas eliminar este usuario?",
				"Confirmar",
				JOptionPane.YES_NO_OPTION
			);
			
			if (confirm == JOptionPane.YES_OPTION) {
				try {
					repo.delete(row);
					loadUsers();
				} catch (IOException ex) {
					JOptionPane.showMessageDialog(vista, ex.getMessage());
				}
			}
					
		});
		
		this.vista.getBtnPdf().addActionListener(e -> generatePdf());

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
	
	public void generatePdf() {
		
		File file = vista.selectPdfFile();
		
		if(file == null) {
			return;
		}
		
		try {
			pdfExporter.exportUsers(repo.getUsers(), file);
			if(Desktop.isDesktopSupported()) {
				Desktop.getDesktop().open(file);
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(vista, "Error al exportar");
		}
		
		
	}
	
	

}