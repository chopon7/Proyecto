package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import config.DatabaseConnection;
import models.ReporteGeneral;

public class ReporteRepository {

	public ReporteGeneral obtenerReporteGeneral() {
		
		int totalVehiculos = contarRegistros("SELECT COUNT(*) FROM vehiculos");
		int vehiculosDentro = contarRegistros("SELECT COUNT(*) FROM espacios WHERE ocupado = 1");
		int espaciosDisponibles = contarRegistros("SELECT COUNT(*) FROM espacios WHERE ocupado = 0");
		int totalEspacios = contarRegistros("SELECT COUNT(*) FROM espacios");
		int salidasRegistradas = contarRegistros("SELECT COUNT(*) FROM ganancias_estacionamiento");
		double ganancias = calcularGananciasActuales();

		return new ReporteGeneral(totalVehiculos, vehiculosDentro, espaciosDisponibles, totalEspacios, salidasRegistradas, ganancias);
	}

	private int contarRegistros(String sql) {
		int total = 0;

		try {
			Connection conn = DatabaseConnection.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				total = rs.getInt(1);
			}

			rs.close();
			ps.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return total;
	}
	
	
	private double calcularGananciasActuales() {

		double total = 0.00;

		String sql = "SELECT IFNULL(SUM(totalPagar), 0) AS ganancias "
				+ "FROM ganancias_estacionamiento";

		try {
			Connection conn = DatabaseConnection.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				total = rs.getDouble("ganancias");
			}

			rs.close();
			ps.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return total;
	}
	
}
