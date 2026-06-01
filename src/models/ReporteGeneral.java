package models;

public class ReporteGeneral {
	
	private int totalVehiculos;
	private int vehiculosDentro;
	private int espaciosDisponibles;
	private int totalEspacios;
	private double ganancias;

	public ReporteGeneral(int totalVehiculos, int vehiculosDentro, int espaciosDisponibles, int totalEspacios,
			double ganancias) {
		this.totalVehiculos = totalVehiculos;
		this.vehiculosDentro = vehiculosDentro;
		this.espaciosDisponibles = espaciosDisponibles;
		this.totalEspacios = totalEspacios;
		this.ganancias = ganancias;
	}

	public int getTotalVehiculos() {
		return totalVehiculos;
	}

	public int getVehiculosDentro() {
		return vehiculosDentro;
	}

	public int getEspaciosDisponibles() {
		return espaciosDisponibles;
	}

	public int getTotalEspacios() {
		return totalEspacios;
	}

	public double getGanancias() {
		return ganancias;
	}


}
