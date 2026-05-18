package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.mindrot.jbcrypt.BCrypt;

import config.DatabaseConnection;
import models.User;
import utils.PasswordUtils;

public class LoginRepository {

	public User login(String email, String password) {

		String sql = "SELECT idUser, userEmail, userPassword, userRole, userNombre FROM users WHERE userEmail = ?";

		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);) {

			stmt.setString(1, email);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				String hashedPassword = rs.getString("userPassword");
				boolean correctPassword = PasswordUtils.checkPassword(password, hashedPassword);

				if (!correctPassword)
					return null;

				User user = new User();
				user.setId(rs.getInt("idUser"));
				user.setEmail(rs.getString("userEmail"));
				user.setNombre(rs.getString("userNombre"));
				user.setRole(rs.getString("userRole"));

				return user;
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return null;
	}

}
