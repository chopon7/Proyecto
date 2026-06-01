package models;

import java.time.LocalDateTime;

public class Espacio {

	// Atributos
	private int numero;
	private int idVehiculo;
	private Vehiculo miVehiculo;
	private boolean ocupado;
	private LocalDateTime horaEntrada;

	// Constructores
	public Espacio(int numero) {
		this.numero = numero;
		this.ocupado = false;
		this.idVehiculo = -1;
		this.miVehiculo = null;
	}

	// Getters y setters
	public int getNumero() {
		return numero;
	}

	public boolean isOcupado() {
		return ocupado;
	}

	public LocalDateTime getHoraEntrada() {
		return horaEntrada;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public void setOcupado(boolean ocupado) {
		this.ocupado = ocupado;
	}


	public void setHoraEntrada(LocalDateTime horaEntrada) {
		this.horaEntrada = horaEntrada;
	}

	public int getIdVehiculo() {
		return idVehiculo;
	}

	public Vehiculo getMiVehiculo() {
		return miVehiculo;
	}

	public void setIdVehiculo(int idVehiculo) {
		this.idVehiculo = idVehiculo;
	}

	public void setMiVehiculo(Vehiculo miVehiculo) {
		this.miVehiculo = miVehiculo;
	}

	// Metodos
    public void registrarEntrada(int idVehiculo, Vehiculo miVehiculo) {
    	this.ocupado = true;
		this.miVehiculo = miVehiculo;
		this.idVehiculo = idVehiculo;
		this.horaEntrada = LocalDateTime.now();
    }

    public void registrarSalida() {
    	this.ocupado = false;
		this.miVehiculo = null;
		this.idVehiculo = -1;
		this.horaEntrada = null;
    }

}
