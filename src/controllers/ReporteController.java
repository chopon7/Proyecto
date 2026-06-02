package controllers;

import java.awt.Desktop;
import java.io.File;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import models.ReporteGeneral;
import repository.ReporteRepository;
import views.ReporteDiarioView;

import services.PDFExporter;

public class ReporteController {
	
	private ReporteDiarioView vista;
	private ReporteRepository reporteRepository;
	private ReporteGeneral reporteActual;
	private PDFExporter pdfExporter;

	public ReporteController(ReporteDiarioView vista) {
		this.vista = vista;
		this.reporteRepository = new ReporteRepository();
		this.pdfExporter = new PDFExporter();

		asignarOyentes();
		cargarReporte();
	}

	private void asignarOyentes() {
		vista.getBtnActualizar().addActionListener(e -> {
			cargarReporte();
			JOptionPane.showMessageDialog(vista, "Reporte actualizado correctamente.");
		});

		vista.getBtnExportarPdf().addActionListener(e -> {
			exportarReportePdf();
		});
	}

	public void cargarReporte() {
		reporteActual = reporteRepository.obtenerReporteGeneral();
		ReporteGeneral reporte = reporteActual;

		DecimalFormat formatoDinero = new DecimalFormat("$#,##0.00");
		DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		vista.setFecha(LocalDate.now().format(formatoFecha));

		vista.setResumen(String.valueOf(reporte.getTotalVehiculos()), String.valueOf(reporte.getEspaciosDisponibles()),
				String.valueOf(reporte.getSalidasRegistradas()), formatoDinero.format(reporte.getGanancias()));

		vista.setTextoResumen(
				"Resumen general del estacionamiento:\n\n" 
				+ "- Vehículos registrados: " + reporte.getTotalVehiculos() + "\n" 
				+ "- Espacios disponibles: " + reporte.getEspaciosDisponibles() + " de " + reporte.getTotalEspacios() + "\n"
				+ "- Salidas registradas: " + reporte.getSalidasRegistradas() + "\n"
				+ "- Tarifa actual: $30 por hora o fracción\n"
				+ "- Ganancias cobradas: " + formatoDinero.format(reporte.getGanancias()) + "\n\n"
				+ "Estado operativo:\n"
				+ "- El reporte se actualiza con base en la tabla de vehículos y espacios.\n"
				+ "- Los espacios ocupados representan los vehículos que siguen dentro del estacionamiento.\n"
				+ "- Las salidas se registran cuando un vehículo abandona el estacionamiento.\n"
				+ "- Las ganancias se registran cuando un vehículo sale del estacionamiento."
		);		
	}
	
	private void exportarReportePdf() {

		try {
			cargarReporte();

			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setDialogTitle("Guardar reporte general");
			fileChooser.setSelectedFile(new File("reporte_general_estacionamiento.pdf"));

			int opcion = fileChooser.showSaveDialog(vista);

			if (opcion == JFileChooser.APPROVE_OPTION) {

				File archivo = fileChooser.getSelectedFile();

				if (!archivo.getName().toLowerCase().endsWith(".pdf")) {
					archivo = new File(archivo.getAbsolutePath() + ".pdf");
				}

				pdfExporter.exportReporteGeneral(reporteActual, archivo);

				JOptionPane.showMessageDialog(vista, "PDF generado correctamente.");
				
				if (Desktop.isDesktopSupported()) {
					Desktop.getDesktop().open(archivo);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(vista, "Error al generar el PDF.");
		}
	}


}
