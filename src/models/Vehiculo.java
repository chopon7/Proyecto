package models;

public class Vehiculo {

	// Atributos
	private int idVehiculo;
	private String placa;
	private String marca;
	private String modelo;
	private String color;
	private String tipoVehiculo;

	// Constructores
	public Vehiculo(String placa, String marca, String modelo, String color, String tipoVehiculo) {
		this.placa = placa;
		this.marca = marca;
		this.modelo = modelo;
		this.color = color;
		this.tipoVehiculo = tipoVehiculo;
	}

	public Vehiculo(int idVehiculo, String placa, String marca, String modelo, String color, String tipoVehiculo) {
		this.idVehiculo = idVehiculo;
		this.placa = placa;
		this.marca = marca;
		this.modelo = modelo;
		this.color = color;
		this.tipoVehiculo = tipoVehiculo;
	}

	// Getters y setters
	public int getIdVehiculo() {
		return idVehiculo;
	}

	public void setIdVehiculo(int idVehiculo) {
		this.idVehiculo = idVehiculo;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getTipoVehiculo() {
		return tipoVehiculo;
	}

	public void setTipoVehiculo(String tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
	}
}