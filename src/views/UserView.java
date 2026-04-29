package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

import tablemodels.UserTableModel;
import utils.AppFont;

public class UserView extends JPanel {

	private JTable table;
	private JButton btnAdd;
	private JButton btnEdit;
	private JButton btnDelete;

	public UserView() {
		setLayout(new BorderLayout());
		table = new JTable();
		styleTable();
		add(new JScrollPane(table), BorderLayout.CENTER);

		JPanel panelButtons = new JPanel();
		panelButtons.setLayout(new FlowLayout(FlowLayout.LEFT));

		btnAdd = new JButton("Agregar");
		btnEdit = new JButton("Editar");
		btnDelete = new JButton("Eliminar");

		panelButtons.add(btnAdd);
		panelButtons.add(btnEdit);
		panelButtons.add(btnDelete);

		add(panelButtons, BorderLayout.NORTH);

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

	public void setTableModel(UserTableModel model) {
		
		table.setModel(model);
		
		
		DefaultTableCellRenderer center = new DefaultTableCellRenderer();
		center.setHorizontalAlignment(SwingConstants.CENTER);
		
		if(table.getColumnCount() >= 1) {
			table.getColumnModel().getColumn(0).setCellRenderer(center);
		}
		
	    if(table.getColumnCount() >= 1) {
	        table.getColumnModel().getColumn(0).setPreferredWidth(125);
	    }
	    
	    if(table.getColumnCount() >= 2) {
	        table.getColumnModel().getColumn(1).setPreferredWidth(125);
	    }

	    if(table.getColumnCount() >= 3) {
	        table.getColumnModel().getColumn(2).setPreferredWidth(135);
	    }

	    if(table.getColumnCount() >= 4) {
	        table.getColumnModel().getColumn(3).setPreferredWidth(230);
	    }

	    if(table.getColumnCount() >= 5) {
	        table.getColumnModel().getColumn(4).setPreferredWidth(40);
	        table.getColumnModel().getColumn(4).setCellRenderer(center);
	    }

	    if(table.getColumnCount() >= 6) {
	        table.getColumnModel().getColumn(5).setPreferredWidth(130);
	        table.getColumnModel().getColumn(5).setCellRenderer(center);
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

	public int getSelectedRow() {
		return table.getSelectedRow();
	}
}