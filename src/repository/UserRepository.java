package repository;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import config.DatabaseConnection;
import models.User;

public class UserRepository {

	public void save(User user) throws IOException {

		List<User> users = getUsers();

		String sql = "INSERT INTO users (userEmail, userPassword, userRole, userNombre, userApellidoPaterno, userApellidoMaterno,"
				+ "userEdad, userGenero) VALUES (?,?,?,?,?,?,?,?)";

		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement pst = connection.prepareStatement(sql)) {

			String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());

			pst.setString(1, user.getEmail());
			pst.setString(2, hashedPassword);
			pst.setString(3, "ADMIN");
			pst.setString(4, user.getNombre());
			pst.setString(5, user.getApellidoPaterno());
			pst.setString(6, user.getApellidoMaterno());
			pst.setString(7, user.getEdad());
			pst.setString(8, user.getGenero());

			int affectedRows = pst.executeUpdate();

			if (affectedRows > 0) {
				users.add(user);
				System.out.println("Nuevo usuario guardado");
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

	}

	public List<User> getUsers() throws IOException {

		List<User> users = new ArrayList<User>();

		try (Connection connection = DatabaseConnection.getConnection();
				Statement st = connection.createStatement();
				ResultSet rs = st.executeQuery("SELECT * FROM users");

		) {

			while (rs.next()) {
				int id = rs.getInt("idUser");
				String password = rs.getString("userPassword");
				String email = rs.getString("userEmail");
				String role = rs.getString("userRole");
				String nombre = rs.getString("userNombre");
				String apellidoPaterno = rs.getString("userApellidoPaterno");
				String apellidoMaterno = rs.getString("userApellidoMaterno");
				String edad = rs.getString("userEdad");
				String genero = rs.getString("userGenero");
				User user = new User(id, email, password, role, nombre, apellidoPaterno, apellidoMaterno, edad, genero);
				users.add(user);

			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return users;
	}

	public boolean delete(int id) {

		String sql = "DELETE FROM users WHERE idUser = ?";

		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement pst = connection.prepareStatement(sql)) {

			pst.setInt(1, id);
			int affectedRows = pst.executeUpdate();
			if (affectedRows > 0) {
				System.out.println("Se eliminó");
				return true;
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return false;
	}

	public boolean update(int index, User updatedUser) throws IOException {

		String sql = "UPDATE users SET userEmail = ?, userPassword = ?, userRole = ?, userNombre = ?, userApellidoPaterno = ?, userApellidoMaterno = ?, userEdad = ?, userGenero = ? WHERE idUser = ?";
		String sqlCheckPassword = "SELECT userPassword FROM users WHERE idUser = ?";

		try (Connection connection = DatabaseConnection.getConnection()) {

			String hashedPassword = "";

			try (PreparedStatement pstCheck = connection.prepareStatement(sqlCheckPassword)) {
				pstCheck.setInt(1, updatedUser.getId());
				try (ResultSet rs = pstCheck.executeQuery()) {
					if (rs.next()) {
						String currentHashInDb = rs.getString("userPassword");

						if (updatedUser.getPassword().equals(currentHashInDb)) {
							hashedPassword = currentHashInDb;
						} else {
							hashedPassword = BCrypt.hashpw(updatedUser.getPassword(), BCrypt.gensalt());
						}
					} else {
						return false;
					}
				}
			}

			try (PreparedStatement pst = connection.prepareStatement(sql)) {
				pst.setString(1, updatedUser.getEmail());
				pst.setString(2, hashedPassword);
				pst.setString(3, updatedUser.getRole());
				pst.setString(4, updatedUser.getNombre());
				pst.setString(5, updatedUser.getApellidoPaterno());
				pst.setString(6, updatedUser.getApellidoMaterno());
				pst.setString(7, updatedUser.getEdad());
				pst.setString(8, updatedUser.getGenero());
				pst.setInt(9, updatedUser.getId());

				int affectedRows = pst.executeUpdate();
				System.out.println("Filas afectadas: " + affectedRows);

				if (affectedRows > 0) {

					return true;

				}
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return false;
	}

}