package models;

public class User {

	// Atributos
	private int id;
	private String email;
	private String password;
	private String nombre;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private String genero;
	private String edad;
	private String role;

	// Constructores
	public User() {

	}

	public User(String email, String password) {
		this.email = email;
		this.password = password;
	}

	public User(String nombre, String apellidoPaterno, String apellidoMaterno, String email, String genero,
			String edad) {
		this.nombre = nombre;
		this.apellidoPaterno = apellidoPaterno;
		this.apellidoMaterno = apellidoMaterno;
		this.email = email;
		this.genero = genero;
		this.edad = edad;
	}

	public User(int id, String email, String role, String nombre, String apellidoPaterno, String apellidoMaterno, String edad,
			String genero) {
		this.id = id;
		this.role = role;
		this.email = email;
		this.nombre = nombre;
		this.apellidoPaterno = apellidoPaterno;
		this.apellidoMaterno = apellidoMaterno;
		this.email = email;
		this.genero = genero;
		this.edad = edad;
	}

	public User(String email, String password, String role, String nombre, String apellidoPaterno,
			String apellidoMaterno, String edad, String genero) {
		this.email = email;
		this.password = password;
		this.nombre = nombre;
		this.role = role;
		this.apellidoPaterno = apellidoPaterno;
		this.apellidoMaterno = apellidoMaterno;
		this.edad = edad;
		this.genero = genero;
	}

	public User(int id, String email, String password, String role, String nombre, String apellidoPaterno,
			String apellidoMaterno, String edad, String genero) {
		this.email = email;
		this.nombre = nombre;
		this.role = role;
		this.password = password;
		this.apellidoPaterno = apellidoPaterno;
		this.apellidoMaterno = apellidoMaterno;
		this.genero = genero;
		this.edad = edad;
		this.id = id;
	}

	// Getters y setters
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidoPaterno() {
		return apellidoPaterno;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	public String getApellidoMaterno() {
		return apellidoMaterno;
	}

	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getEdad() {
		return edad;
	}

	public void setEdad(String edad) {
		this.edad = edad;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String toString() {
		return "Nombre: " + nombre + "\nApellido Paterno: " + apellidoPaterno + "\nApellido Materno: " + apellidoMaterno
				+ "\nEdad: " + edad + "\nEmail: " + email + "\nGénero: " + genero;
	}

	public String toCsv() {
		return nombre + "," + email + "," + apellidoPaterno + "," + apellidoMaterno + "," + edad + "," + genero;
	}

	public static User fromCsv(String userData) {
		String data[] = userData.split(",");

		String nombre = data[0];
		String email = data[1];
		String apellidoPaterno = data[2];
		String apellidoMaterno = data[3];
		String edad = data[4];
		String genero = data[5];

		return new User(nombre, apellidoPaterno, apellidoMaterno, email, genero, edad);

	}

}
