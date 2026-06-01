package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import config.DatabaseConnection;

public class EspacioRepository {

	public boolean ocuparEspacio(int numeroEspacio, int idVehiculo) {
		String sql = "UPDATE espacios SET ocupado = TRUE, horaEntrada = ?, idVehiculo = ? WHERE numeroEspacio = ?";

		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement pst = connection.prepareStatement(sql)) {

			pst.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
			pst.setInt(2, idVehiculo);
			pst.setInt(3, numeroEspacio);

			int affectedRows = pst.executeUpdate();
			return affectedRows > 0;

		} catch (SQLException ex) {
			ex.printStackTrace();
			return false;
		}
	}

	public boolean liberarEspacio(int numeroEspacio) {
		String sql = "UPDATE espacios SET ocupado = FALSE, horaEntrada = NULL, idVehiculo = NULL WHERE numeroEspacio = ?";

		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement pst = connection.prepareStatement(sql)) {

			pst.setInt(1, numeroEspacio);

			int affectedRows = pst.executeUpdate();
			return affectedRows > 0;

		} catch (SQLException ex) {
			ex.printStackTrace();
			return false;
		}
	}

	public List<Integer> getNumerosCajonesLibres() {
		List<Integer> libres = new ArrayList<>();
		String sql = "SELECT numeroEspacio FROM espacios WHERE ocupado = FALSE ORDER BY numeroEspacio ASC";

		try (Connection connection = DatabaseConnection.getConnection();
				Statement st = connection.createStatement();
				ResultSet rs = st.executeQuery(sql)) {

			while (rs.next()) {
				libres.add(rs.getInt("numeroEspacio"));
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return libres;
	}

	public boolean liberarEspacioPorVehiculo(int idVehiculo) {
		String sql = "UPDATE espacios SET ocupado = FALSE, horaEntrada = NULL, idVehiculo = NULL WHERE idVehiculo = ?";
		try (Connection connection = config.DatabaseConnection.getConnection();
				PreparedStatement pst = connection.prepareStatement(sql)) {

			pst.setInt(1, idVehiculo);
			int affectedRows = pst.executeUpdate();
			return affectedRows > 0;
		} catch (SQLException ex) {
			ex.printStackTrace();
			return false;
		}
	}
}