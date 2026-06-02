package repository;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import config.DatabaseConnection;
import models.Vehiculo;

public class VehiculoRepository {

	public int save(Vehiculo vehiculo) {
		String sql = "INSERT INTO vehiculos (placa, marca, modelo, color, tipoVehiculo) VALUES (?, ?, ?, ?, ?)";

		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement pst = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			pst.setString(1, vehiculo.getPlaca());
			pst.setString(2, vehiculo.getMarca());
			pst.setString(3, vehiculo.getModelo());
			pst.setString(4, vehiculo.getColor());
			pst.setString(5, vehiculo.getTipoVehiculo());

			int affectedRows = pst.executeUpdate();

			if (affectedRows > 0) {

				try (ResultSet generatedKeys = pst.getGeneratedKeys()) {
					if (generatedKeys.next()) {
						int idGenerado = generatedKeys.getInt(1);
						System.out.println("Nuevo vehículo guardado con ID: " + idGenerado);
						return idGenerado;
					}
				}
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return -1;
	}

	public List<Vehiculo> getVehiculos() throws IOException {
		List<Vehiculo> vehiculos = new ArrayList<>();
		String sql = "SELECT * FROM vehiculos";

		try (Connection connection = DatabaseConnection.getConnection();
				Statement st = connection.createStatement();
				ResultSet rs = st.executeQuery(sql)) {

			while (rs.next()) {
				int id = rs.getInt("idVehiculo");
				String placa = rs.getString("placa");
				String marca = rs.getString("marca");
				String modelo = rs.getString("modelo");
				String color = rs.getString("color");
				String tipo = rs.getString("tipoVehiculo");

				Vehiculo miVehiculo = new Vehiculo(id, placa, marca, modelo, color, tipo);
				vehiculos.add(miVehiculo);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return vehiculos;
	}

	public boolean delete(int id) {
		String sql = "DELETE FROM vehiculos WHERE idVehiculo = ?";

		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement pst = connection.prepareStatement(sql)) {

			pst.setInt(1, id);
			int affectedRows = pst.executeUpdate();
			return affectedRows > 0;

		} catch (SQLException ex) {
			ex.printStackTrace();
			return false;
		}
	}

	public boolean update(int index, Vehiculo updatedVehiculo) {
		String sql = "UPDATE vehiculos SET placa = ?, marca = ?, modelo = ?, color = ?, tipoVehiculo = ? WHERE idVehiculo = ?";

		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement pst = connection.prepareStatement(sql)) {

			pst.setString(1, updatedVehiculo.getPlaca());
			pst.setString(2, updatedVehiculo.getMarca());
			pst.setString(3, updatedVehiculo.getModelo());
			pst.setString(4, updatedVehiculo.getColor());
			pst.setString(5, updatedVehiculo.getTipoVehiculo());
			pst.setInt(6, updatedVehiculo.getIdVehiculo());

			int affectedRows = pst.executeUpdate();
			System.out.println("Filas de vehiculos afectadas en el indice " + index + ": " + affectedRows);

			return affectedRows > 0;

		} catch (SQLException ex) {
			ex.printStackTrace();
			return false;
		}
	}

	public boolean existePlaca(String placa) {
		String sql = "SELECT COUNT(*) FROM vehiculos WHERE placa = ?";
		try (Connection connection = config.DatabaseConnection.getConnection();
				PreparedStatement pst = connection.prepareStatement(sql)) {

			pst.setString(1, placa);
			try (ResultSet rs = pst.executeQuery()) {
				if (rs.next()) {
					if (rs.getInt(1) > 0) {
						return true;
					}
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return false;
	}
}