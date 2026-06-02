package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;

import utils.AppFont;

public class ReporteDiarioView extends JPanel {

    private JLabel lblFecha;
    private JLabel lblIngresos;
    private JLabel lblVehiculosDentro;
    private JLabel lblSalidas;
    private JLabel lblGanancias;
    private JTextArea txtResumen;
    private JButton btnActualizar;
    private JButton btnExportarPdf;

    public ReporteDiarioView() {
        inicializarComponentes();
    }

    private void inicializarComponentes() {

    	setLayout(new BorderLayout());
    	setBackground(new Color(27, 38, 59));
    	setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

    	JPanel panelSuperior = new JPanel(new GridLayout(2, 1, 0, 0));
    	panelSuperior.setBackground(new Color(27, 38, 59));
    	panelSuperior.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));

    	JLabel lblTitulo = new JLabel("Reporte General del Estacionamiento");
    	lblTitulo.setFont(new Font("Times New Roman", Font.BOLD, 40));
    	lblTitulo.setForeground(Color.WHITE);
    	lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);

    	lblFecha = new JLabel("Fecha: --/--/----");
    	lblFecha.setFont(AppFont.title());
    	lblFecha.setForeground(Color.WHITE);
    	lblFecha.setHorizontalAlignment(SwingConstants.CENTER);

    	panelSuperior.add(lblTitulo, BorderLayout.NORTH);
    	panelSuperior.add(lblFecha, BorderLayout.SOUTH);

    	add(panelSuperior, BorderLayout.NORTH);

    	JPanel panelCentro = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
    	panelCentro.setBackground(new Color(27, 38, 59));

    	JPanel panelContenido = new JPanel(new BorderLayout(0, 22));
    	panelContenido.setBackground(new Color(27, 38, 59));
    	panelContenido.setPreferredSize(new Dimension(1130, 460));

    	JPanel panelTarjetas = new JPanel(new GridLayout(2, 2, 22, 22));
    	panelTarjetas.setBackground(new Color(27, 38, 59));
    	panelTarjetas.setPreferredSize(new Dimension(1130, 210));

    	panelTarjetas.add(crearTarjeta("Vehículos Activos", "0"));
    	panelTarjetas.add(crearTarjeta("Espacios Disponibles", "0"));
    	panelTarjetas.add(crearTarjeta("Salidas Registradas", "0"));
    	panelTarjetas.add(crearTarjeta("Ganancias Totales", "$0.00"));

    	panelContenido.add(panelTarjetas, BorderLayout.NORTH);

    	JPanel panelResumen = new JPanel(new BorderLayout());
    	panelResumen.setBackground(new Color(27, 38, 59));
    	panelResumen.setBorder(BorderFactory.createTitledBorder(
    			BorderFactory.createLineBorder(Color.WHITE),
    			"",
    			0,
    			0,
    			AppFont.normal(),
    			Color.WHITE
    	));

    	txtResumen = new JTextArea();
    	txtResumen.setText("Resumen general del estacionamiento:\n\n"
    			+ "- Aún no hay datos cargados.\n"
    			+ "- Aquí se mostrará la ocupación, disponibilidad y ganancias estimadas.\n"
    			+ "- La tarifa actual es de $30 por hora o fracción.");
    	txtResumen.setFont(AppFont.normal());
    	txtResumen.setForeground(Color.WHITE);
    	txtResumen.setBackground(new Color(27, 38, 59));
    	txtResumen.setLineWrap(true);
    	txtResumen.setWrapStyleWord(true);
    	txtResumen.setEditable(false);
    	txtResumen.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

    	JScrollPane scrollResumen = new JScrollPane(txtResumen);
    	scrollResumen.setBorder(BorderFactory.createEmptyBorder());
    	scrollResumen.getViewport().setBackground(new Color(27, 38, 59));

    	panelResumen.add(scrollResumen, BorderLayout.CENTER);

    	panelContenido.add(panelResumen, BorderLayout.CENTER);

    	JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
    	panelBotones.setBackground(new Color(27, 38, 59));

    	btnActualizar = new JButton("Actualizar reporte");
    	btnActualizar.setFont(AppFont.normal());

    	btnExportarPdf = new JButton("Exportar PDF");
    	btnExportarPdf.setFont(AppFont.normal());

    	panelBotones.add(btnActualizar);
    	panelBotones.add(btnExportarPdf);

    	panelContenido.add(panelBotones, BorderLayout.SOUTH);

    	panelCentro.add(panelContenido);
    	add(panelCentro, BorderLayout.CENTER);
    }
    
    private JPanel crearTarjeta(String titulo, String valor) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
        		BorderFactory.createLineBorder(new Color(220, 220, 220)),
    			BorderFactory.createEmptyBorder(18, 25, 18, 25)
    	));

        JLabel lblTitulo = new JLabel(titulo);
        lblTitulo.setFont(AppFont.normal());
        lblTitulo.setForeground(new Color(27, 38, 59));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel lblValor = new JLabel(valor);
        lblValor.setFont(AppFont.mediumTitle());
        lblValor.setForeground(new Color(65, 90, 119));
        lblValor.setHorizontalAlignment(SwingConstants.CENTER);

        if (titulo.equals("Vehículos Activos")) {
            lblIngresos = lblValor;
        } else if (titulo.equals("Espacios Disponibles")) {
            lblVehiculosDentro = lblValor;
        } else if (titulo.equals("Salidas Registradas")) {
            lblSalidas = lblValor;
        } else if (titulo.equals("Ganancias Totales")) {
            lblGanancias = lblValor;
        }

        panel.add(lblTitulo, BorderLayout.NORTH);
        panel.add(lblValor, BorderLayout.CENTER);

        return panel;
    }

    public void setFecha(String fecha) {
        lblFecha.setText("Fecha: " + fecha);
    }

    public void setResumen(String ingresos, String vehiculosDentro, String salidas, String ganancias) {
        lblIngresos.setText(ingresos);
        lblVehiculosDentro.setText(vehiculosDentro);
        lblSalidas.setText(salidas);
        lblGanancias.setText(ganancias);
    }

    public void setTextoResumen(String texto) {
        txtResumen.setText(texto);
    }

    public JButton getBtnActualizar() {
        return btnActualizar;
    }

    public JButton getBtnExportarPdf() {
        return btnExportarPdf;
    }
}
