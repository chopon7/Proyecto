package tablemodels;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import models.Vehiculo;

public class VehiculoTableModel extends AbstractTableModel {

	private List<Vehiculo> vehiculos;

	private final String[] columns = { "ID", "Placa", "Marca", "Modelo", "Color", "Tipo de Vehiculo" };
	
	public VehiculoTableModel(List<Vehiculo> vehiculos) {
		this.vehiculos = vehiculos;
	}
	
	@Override
	public int getRowCount() {
		return vehiculos.size();
	}

	@Override
	public int getColumnCount() {
		return columns.length;
	}
	
	@Override
	public String getColumnName(int column) {
		return columns[column];
	}

	public List<Vehiculo> getVehiculos() {
		return vehiculos;
	}

	public String[] getColumns() {
		return columns;
	}

	public void setVehiculos(List<Vehiculo> vehiculos) {
		this.vehiculos = vehiculos;
		fireTableDataChanged();
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		
		Vehiculo miVehiculo = vehiculos.get(rowIndex);
		
		switch(columnIndex) {
		case 0:
			return miVehiculo.getIdVehiculo();
		case 1:
			return miVehiculo.getPlaca();
		case 2:
			return miVehiculo.getMarca();
		case 3:
			return miVehiculo.getModelo();
		case 4:
			return miVehiculo.getColor();
		case 5: 
			return miVehiculo.getTipoVehiculo();
		}
		
		return null;
		
	}
	
	public Vehiculo getVehiculoAt(int row) {
		return vehiculos.get(row);
	}

	public void setVehiculo (List<Vehiculo> vehiculos) {
		this.vehiculos = vehiculos;
		fireTableDataChanged();
	}

	public void removeRow(int row) {
		vehiculos.remove(row);
		fireTableRowsDeleted(row, row);
	}

	public void addRow(Vehiculo miVehiculo) {
		int row = vehiculos.size();
		vehiculos.add(miVehiculo);
		fireTableRowsInserted(row, row);
	}
	
	public void updateRow(int row, Vehiculo miVehiculo) {
		vehiculos.set(row, miVehiculo);
		fireTableRowsUpdated(row, row);
	}
	
}
