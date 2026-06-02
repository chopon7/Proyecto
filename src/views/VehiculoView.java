package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

import config.Config;
import tablemodels.VehiculoTableModel;
import utils.AppFont;

public class VehiculoView extends JPanel {

    private JTable table;
    private JButton btnAdd;
    private JButton btnEdit;
    private JButton btnDelete;
    private JButton btnPdf;

    public VehiculoView() {
        setLayout(new BorderLayout());
        setBackground(new Color(27, 38, 59));

        table = new JTable();
        styleTable();
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel panelButtons = new JPanel();
        panelButtons.setBackground(new Color(27, 38, 59));
        panelButtons.setLayout(new FlowLayout(FlowLayout.LEFT));

        btnAdd = new JButton("Agregar");
        btnEdit = new JButton("Editar");
        btnDelete = new JButton("Eliminar");
        btnPdf = new JButton("Exportar a PDF");

        panelButtons.add(btnAdd);
        panelButtons.add(btnEdit);
        panelButtons.add(btnDelete);
        panelButtons.add(btnPdf);

        add(panelButtons, BorderLayout.NORTH);
    }

    public File selectPdfFile() {
        String path = Config.get("vehiculos.export.pdf", System.getProperty("user.home"));
        JFileChooser chooser = new JFileChooser(path);

        chooser.setSelectedFile(new File("reporte-vehiculos.pdf"));
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);

        FileNameExtensionFilter filter = new FileNameExtensionFilter("Documentos PDF", "pdf");
        chooser.addChoosableFileFilter(filter);
        chooser.setFileFilter(filter);

        int option = chooser.showDialog(this, "Exportar PDF de vehículos");

        if (option != JFileChooser.APPROVE_OPTION) {
            return null;
        }

        File file = chooser.getSelectedFile();
        Config.set("vehiculos.export.pdf", file.getParent());

        if (!file.getName().toLowerCase().endsWith(".pdf")) {
            file = new File(file.getAbsolutePath() + ".pdf");
        }

        return file;
    }

    public void styleTable() {
        table.setRowHeight(40);
        table.setShowGrid(true);
        table.setGridColor(new Color(230, 230, 230));
        table.setBackground(Color.WHITE);
        table.setForeground(Color.BLACK);
        table.setFont(AppFont.title());

        table.setSelectionBackground(new Color(224, 231, 240));
        table.setSelectionForeground(new Color(27, 38, 59));
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JTableHeader header = table.getTableHeader();
        header.setBackground(new Color(27, 38, 59));
        header.setForeground(Color.WHITE);
        header.setFont(AppFont.title());
        header.setPreferredSize(new Dimension(0, 45));
        header.setReorderingAllowed(false);

        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                           boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                setHorizontalAlignment(SwingConstants.LEFT);

                if (!isSelected) {
                    if (row % 2 == 0) {
                        c.setBackground(Color.WHITE);
                    } else {
                        c.setBackground(new Color(248, 249, 250));
                    }
                    c.setForeground(new Color(50, 50, 50));
                }

                if (column == 0) {
                    c.setFont(AppFont.title());
                    if (!isSelected) {
                        c.setForeground(new Color(65, 90, 119));
                    }
                } else {
                    c.setFont(AppFont.normal());
                }
                return c;
            }
        });
    }

    public void setTableModel(VehiculoTableModel model) {
        table.setModel(model);

        DefaultTableCellRenderer center = new DefaultTableCellRenderer();
        center.setHorizontalAlignment(SwingConstants.CENTER);

        if (table.getColumnCount() >= 1) {
            table.getColumnModel().getColumn(0).setCellRenderer(center);
            table.getColumnModel().getColumn(0).setPreferredWidth(80); // ID
        }
        if (table.getColumnCount() >= 2) {
            table.getColumnModel().getColumn(1).setPreferredWidth(120); // Placa
        }
        if (table.getColumnCount() >= 3) {
            table.getColumnModel().getColumn(2).setPreferredWidth(120); // Marca
        }
        if (table.getColumnCount() >= 4) {
            table.getColumnModel().getColumn(3).setPreferredWidth(120); // Modelo
        }
        if (table.getColumnCount() >= 5) {
            table.getColumnModel().getColumn(4).setPreferredWidth(100); // Color
        }
        if (table.getColumnCount() >= 6) {
            table.getColumnModel().getColumn(5).setPreferredWidth(150); // Tipo de Vehiculo
        }
    }

    public JTable getTable() {
        return table;
    }

    public JButton getBtnAdd() {
        return btnAdd;
    }

    public JButton getBtnEdit() {
        return btnEdit;
    }

    public JButton getBtnDelete() {
        return btnDelete;
    }

    public JButton getBtnPdf() {
        return btnPdf;
    }

    public int getSelectedRow() {
        return table.getSelectedRow();
    }
}
