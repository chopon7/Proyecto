package services;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.DeviceGray;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;

import models.User;
import models.ReporteGeneral;

public class PDFExporter {
	
	public void exportUsers(List<User> users, File file) throws IOException {

		try (PdfDocument pdfDoc = new PdfDocument(new PdfWriter(file));
				Document doc = new Document(pdfDoc, PageSize.LETTER.rotate());) {
			InputStream is = getClass().getResourceAsStream("/assets/img/icono.png");

			if (is != null) {
				ImageData data = ImageDataFactory.create(is.readAllBytes());
				Image img = new Image(data).scaleAbsolute(50, 50);

				float altoPagina = PageSize.LETTER.rotate().getHeight();
				float margen = 40;

				img.setFixedPosition(margen, altoPagina - margen - 50);

				doc.add(img);
			}

			doc.add(new Paragraph("Reporte de Usuarios").setBold().setFontSize(12)
					.setTextAlignment(TextAlignment.CENTER));

			doc.add(new Paragraph("").setMarginTop(30));

			float[] columnsWidth = { 1, 3, 3, 3, 4, 2, 2 };

			Table table = new Table(UnitValue.createPercentArray(columnsWidth)).useAllAvailableWidth();

			PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA);

			Cell cell = new Cell(1, 7).add(new Paragraph("Usuarios del sistema")).setFont(font).setFontSize(14)
					.setFontColor(DeviceGray.WHITE).setBackgroundColor(new DeviceRgb(45, 111, 164))
					.setTextAlignment(TextAlignment.CENTER);

			table.addHeaderCell(cell);

			for (int i = 0; i < 1; i++) {

				Cell[] headerFooter = new Cell[] {
						new Cell().setTextAlignment(TextAlignment.CENTER).setBorderTop(new SolidBorder(1f))
								.setBackgroundColor(new DeviceGray(0.80f)).add(new Paragraph("#")),

						new Cell().setTextAlignment(TextAlignment.CENTER).setBorderTop(new SolidBorder(1f))
								.setBackgroundColor(new DeviceGray(0.80f)).add(new Paragraph("Nombre")),

						new Cell().setTextAlignment(TextAlignment.CENTER).setBorderTop(new SolidBorder(1f))
								.setBackgroundColor(new DeviceGray(0.80f)).add(new Paragraph("Apellido Paterno")),

						new Cell().setTextAlignment(TextAlignment.CENTER).setBorderTop(new SolidBorder(1f))
								.setBackgroundColor(new DeviceGray(0.80f)).add(new Paragraph("Apellido Materno")),

						new Cell().setTextAlignment(TextAlignment.CENTER).setBorderTop(new SolidBorder(1f))
								.setBackgroundColor(new DeviceGray(0.80f)).add(new Paragraph("Email")),
						
						new Cell().setTextAlignment(TextAlignment.CENTER).setBorderTop(new SolidBorder(1f))
								.setBackgroundColor(new DeviceGray(0.80f)).add(new Paragraph("Género")), 
								
						new Cell().setTextAlignment(TextAlignment.CENTER).setBorderTop(new SolidBorder(1f))
								.setBackgroundColor(new DeviceGray(0.80f)).add(new Paragraph("Edad")),
			};
				
				

				for (Cell celda : headerFooter) {
						table.addHeaderCell(celda);
				}
			}
			
			int indice = 1;
			
			for(User u : users) {
				table.addCell(new Cell().setTextAlignment(TextAlignment.CENTER)
                        .add(new Paragraph(String.valueOf(indice))));

                table.addCell(new Cell().setTextAlignment(TextAlignment.CENTER)
                        .add(new Paragraph(u.getNombre())));

                table.addCell(new Cell().setTextAlignment(TextAlignment.CENTER)
                        .add(new Paragraph(u.getApellidoPaterno())));

                table.addCell(new Cell().setTextAlignment(TextAlignment.CENTER)
                        .add(new Paragraph(u.getApellidoMaterno())));

                table.addCell(new Cell().setTextAlignment(TextAlignment.CENTER)
                        .add(new Paragraph(u.getEmail())));
                
                table.addCell(new Cell().setTextAlignment(TextAlignment.CENTER)
                        .add(new Paragraph(u.getGenero())));
                
                table.addCell(new Cell().setTextAlignment(TextAlignment.CENTER)
                        .add(new Paragraph(u.getEdad())));

                indice++;
			}

			doc.add(table);
		}

	}
	
	public void exportReporteGeneral(models.ReporteGeneral reporte, File file) throws IOException {

		try (PdfDocument pdfDoc = new PdfDocument(new PdfWriter(file));
				Document doc = new Document(pdfDoc, PageSize.LETTER);) {

			InputStream is = getClass().getResourceAsStream("/assets/img/icono.png");

			if (is != null) {
				ImageData data = ImageDataFactory.create(is.readAllBytes());
				Image img = new Image(data).scaleAbsolute(50, 50);

				float altoPagina = PageSize.LETTER.getHeight();
				float margen = 40;

				img.setFixedPosition(margen, altoPagina - margen - 50);

				doc.add(img);
			}

			doc.add(new Paragraph("Reporte General del Estacionamiento").setBold().setFontSize(14)
					.setTextAlignment(TextAlignment.CENTER));
			
			doc.add(new Paragraph("").setMarginTop(30));

			float[] columnsWidth = { 4, 3 };

			Table table = new Table(UnitValue.createPercentArray(columnsWidth)).useAllAvailableWidth();

			PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA);

			Cell tituloTabla = new Cell(1, 2).add(new Paragraph("Datos generales del reporte")).setFont(font).setFontSize(14)
					.setFontColor(DeviceGray.WHITE).setBackgroundColor(new DeviceRgb(45, 111, 164))
					.setTextAlignment(TextAlignment.CENTER);

			table.addHeaderCell(tituloTabla);

			for (int i = 0; i < 1; i++) {

				Cell[] headerFooter = new Cell[] {
						new Cell().setTextAlignment(TextAlignment.CENTER).setBorderTop(new SolidBorder(1f))
								.setBackgroundColor(new DeviceGray(0.80f)).add(new Paragraph("Concepto")),

						new Cell().setTextAlignment(TextAlignment.CENTER).setBorderTop(new SolidBorder(1f))
								.setBackgroundColor(new DeviceGray(0.80f)).add(new Paragraph("Resultado")),
				};

				for (Cell celda : headerFooter) {
					table.addHeaderCell(celda);
				}
			}

			table.addCell(new Cell().setTextAlignment(TextAlignment.CENTER)
					.add(new Paragraph("Vehículos registrados")));

			table.addCell(new Cell().setTextAlignment(TextAlignment.CENTER)
					.add(new Paragraph(String.valueOf(reporte.getTotalVehiculos()))));

			table.addCell(new Cell().setTextAlignment(TextAlignment.CENTER)
					.add(new Paragraph("Vehículos actualmente dentro")));

			table.addCell(new Cell().setTextAlignment(TextAlignment.CENTER)
					.add(new Paragraph(String.valueOf(reporte.getVehiculosDentro()))));

			table.addCell(new Cell().setTextAlignment(TextAlignment.CENTER)
					.add(new Paragraph("Espacios disponibles")));

			table.addCell(new Cell().setTextAlignment(TextAlignment.CENTER)
					.add(new Paragraph(reporte.getEspaciosDisponibles() + " de 30")));

			table.addCell(new Cell().setTextAlignment(TextAlignment.CENTER)
					.add(new Paragraph("Tarifa por hora")));

			table.addCell(new Cell().setTextAlignment(TextAlignment.CENTER)
					.add(new Paragraph("$30.00")));

			table.addCell(new Cell().setTextAlignment(TextAlignment.CENTER)
					.add(new Paragraph("Ganancias totales")));

			table.addCell(new Cell().setTextAlignment(TextAlignment.CENTER)
					.add(new Paragraph("$" + String.format("%.2f", reporte.getGanancias()))));

			doc.add(table);

			doc.add(new Paragraph("").setMarginTop(25));

			doc.add(new Paragraph("Observaciones").setBold().setFontSize(13));

			doc.add(new Paragraph(
					"Este reporte muestra el estado general actual del estacionamiento, "
					+ "los vehículos dentro se calculan con base en los espacios ocupados y "
					+ "las ganancias se estiman tomando la tarifa de 30 pesos por hora o fracción.")
					.setFontSize(11)
					.setTextAlignment(TextAlignment.JUSTIFIED));
		}
		
	}
}
