package controllers;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.JOptionPane;

import models.Vehiculo;
import repository.VehiculoRepository;
import services.PDFExporter;
import tablemodels.VehiculoTableModel;
import views.VehiculoFormDialog;
import views.VehiculoView;

public class VehiculoController {

	private VehiculoView vista;
	private VehiculoRepository repo;
	private VehiculoTableModel model;
	private PDFExporter pdfExporter;

	public VehiculoController(VehiculoView vista) {
		this.vista = vista;
		repo = new VehiculoRepository();
		pdfExporter = new PDFExporter();

		this.vista.getBtnAdd().addActionListener(e -> {
			openForm(null);
			loadVehiculos();
		});

		this.vista.getBtnEdit().addActionListener(e -> {
			int row = vista.getSelectedRow();
			if (row == -1) {
				JOptionPane.showMessageDialog(vista, "Selecciona un vehiculo");
				return;
			}
			openForm(model.getVehiculoAt(row));
			loadVehiculos();
		});

		this.vista.getBtnDelete().addActionListener(e -> {

			int row = vista.getSelectedRow();

			if (row == -1) {
				JOptionPane.showMessageDialog(vista, "Selecciona un vehiculo");
				return;
			}

			Vehiculo vehiculoAEliminar = model.getVehiculoAt(row);
			int idVehiculo = vehiculoAEliminar.getIdVehiculo();
			repository.EspacioRepository espacioRepo = new repository.EspacioRepository();
			espacioRepo.liberarEspacioPorVehiculo(idVehiculo);
			boolean deleted = repo.delete(idVehiculo);

			if (deleted) {
				JOptionPane.showMessageDialog(vista,
						"El vehículo con placas [" + vehiculoAEliminar.getPlaca() + "] ha salido del sistema.",
						"Vehículo Eliminado", JOptionPane.INFORMATION_MESSAGE);

				loadVehiculos();
			} else {
				JOptionPane.showMessageDialog(vista, "Error al intentar eliminar el registro de la base de datos.",
						"Error", JOptionPane.ERROR_MESSAGE);
			}
			loadVehiculos();
		});

		this.vista.getBtnPdf().addActionListener(e -> {
			generatePdf();
		});

		loadVehiculos();
	}

	public void loadVehiculos() {
		System.out.println("Carga vehiculos");
		try {
			List<Vehiculo> vehiculos = repo.getVehiculos();

			if (model == null) {
				model = new VehiculoTableModel(vehiculos);
				vista.setTableModel(model);
			} else {
				model.setVehiculos(vehiculos);
			}

		} catch (IOException ex) {
			JOptionPane.showMessageDialog(vista, ex.getMessage());
		}
	}

	private void openForm(Vehiculo miVehiculo) {

		VehiculoFormDialog dialog = new VehiculoFormDialog(null, miVehiculo);

		if (dialog.isSaved()) {
			Vehiculo savedVehiculo = dialog.getVehiculo();

			try {
				if (miVehiculo == null) {					
					model.addRow(savedVehiculo);
				} else {
					int row = vista.getSelectedRow();

					boolean updated = repo.update(row, savedVehiculo);
					if (updated) {
						model.updateRow(row, savedVehiculo); // Actualiza el registro de la tabla
					}
				}
				loadVehiculos();
			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(vista, e.getMessage());
			}

		}

	}

	public void generatePdf() {

		File file = vista.selectPdfFile();

		if (file == null) {
			return;
		}

		try {
			pdfExporter.exportVehiculos(repo.getVehiculos(), file);
			if (Desktop.isDesktopSupported()) {
				Desktop.getDesktop().open(file);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(vista, "Error al exportar");
		}

	}

}
