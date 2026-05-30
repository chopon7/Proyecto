package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

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
        setBorder(BorderFactory.createEmptyBorder(25, 40, 25, 40));

        JLabel lblTitulo = new JLabel("Reporte Diario del Estacionamiento");
        lblTitulo.setFont(AppFont.mediumTitle());
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblTitulo, BorderLayout.NORTH);

        JPanel panelCentro = new JPanel(new GridBagLayout());
        panelCentro.setBackground(new Color(27, 38, 59));

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(12, 12, 12, 12);
        c.fill = GridBagConstraints.BOTH;

        lblFecha = new JLabel("Fecha: --/--/----");
        lblFecha.setFont(AppFont.title());
        lblFecha.setForeground(Color.WHITE);
        lblFecha.setHorizontalAlignment(SwingConstants.CENTER);

        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        panelCentro.add(lblFecha, c);

        c.gridwidth = 1;
        c.gridy = 1;
        c.gridx = 0;
        panelCentro.add(crearTarjeta("Ingresos de hoy", "0"), c);

        c.gridx = 1;
        panelCentro.add(crearTarjeta("Vehiculos dentro", "0"), c);

        c.gridy = 2;
        c.gridx = 0;
        panelCentro.add(crearTarjeta("Salidas del dia", "0"), c);

        c.gridx = 1;
        panelCentro.add(crearTarjeta("Ganancias", "$0.00"), c);

        txtResumen = new JTextArea();
        txtResumen.setText("Resumen del dia:\n\n- Aun no hay datos cargados.\n- Si lo conectamos a la base de datos con el registro de los vehiculos, aqui se mostrara el movimiento general del estacionamiento.");
        txtResumen.setFont(AppFont.normal());
        txtResumen.setForeground(new Color(220, 220, 220));
        txtResumen.setBackground(new Color(27, 38, 59));
        txtResumen.setLineWrap(true);
        txtResumen.setWrapStyleWord(true);
        txtResumen.setEditable(false);
        txtResumen.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 2;
        c.weightx = 1;
        c.weighty = 1;
        panelCentro.add(txtResumen, c);

        add(panelCentro, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelBotones.setBackground(new Color(27, 38, 59));

        btnActualizar = new JButton("Actualizar reporte");
        btnActualizar.setFont(AppFont.normal());

        btnExportarPdf = new JButton("Exportar PDF");
        btnExportarPdf.setFont(AppFont.normal());

        panelBotones.add(btnActualizar);
        panelBotones.add(btnExportarPdf);

        add(panelBotones, BorderLayout.SOUTH);
    }

    private JPanel crearTarjeta(String titulo, String valor) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 25, 20, 25));

        JLabel lblTitulo = new JLabel(titulo);
        lblTitulo.setFont(AppFont.normal());
        lblTitulo.setForeground(new Color(27, 38, 59));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel lblValor = new JLabel(valor);
        lblValor.setFont(AppFont.mediumTitle());
        lblValor.setForeground(new Color(65, 90, 119));
        lblValor.setHorizontalAlignment(SwingConstants.CENTER);

        if (titulo.equals("Ingresos de hoy")) {
            lblIngresos = lblValor;
        } else if (titulo.equals("Vehiculos dentro")) {
            lblVehiculosDentro = lblValor;
        } else if (titulo.equals("Salidas del dia")) {
            lblSalidas = lblValor;
        } else if (titulo.equals("Ganancias")) {
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
