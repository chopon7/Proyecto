	package repository;
	
	import java.sql.Connection;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;

	import config.DatabaseConnection;
	import models.User;
	
	public class LoginRepository {
	
		public User login(String email, String password) {
			/*
			 * String sql = "SELECT id, email, password FROM users WHERE email = '" + email
			 * + "' AND password = '" + password + "'";
			 */
	
			String sql = "SELECT idUser, userEmail, userPassword FROM User WHERE userEmail = ? AND userPassword = ?";
	
			try (
					Connection conn = DatabaseConnection.getConnection();
					PreparedStatement stmt = conn.prepareStatement(sql);
				) {
	
				stmt.setString(1, email);
				stmt.setString(2, password);
				ResultSet rs = stmt.executeQuery();
	
				if (rs.next()) {
					User user = new User();
					user.setId(rs.getInt("idUser"));
					user.setEmail(rs.getString("userEmail"));
	
					return user;
				}
	
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
	
			return null;
		}
	
	}
