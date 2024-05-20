package com.dominio.conexion;
/**
 * @project petroleospain
 * @autor AdQuinti on 18/05/2024
 * 
 * MVC Vista
 * BBDD petroleo2024spain
 */

public class VistaPetrol {
	private int petrol_id;
	private String fecha;
	private String comunidad;
	private String provincia;
	private String producto;
	private int consumo;
	
	public VistaPetrol(int petrol_id, String fecha, String comunidad, String provincia, String producto, int consumo) {
		this.petrol_id = petrol_id;
		this.fecha = fecha;
		this.comunidad = comunidad;
		this.provincia = provincia;
		this.producto = producto;
		this.consumo = consumo;
	}
	
	// copy constructor modificar
	public VistaPetrol(String fecha, String comunidad, String provincia, String producto, int consumo) {
		this.fecha = fecha;
		this.comunidad = comunidad;
		this.provincia = provincia;
		this.producto = producto;
		this.consumo = consumo;
	}

	//getters and setters
	public int getPetrol_id() {
		return petrol_id;
	}

	public void setPetrol_id(int petrol_id) {
		this.petrol_id = petrol_id;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getComunidad() {
		return comunidad;
	}

	public void setComunidad(String comunidad) {
		this.comunidad = comunidad;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getProducto() {
		return producto;
	}

	public void setProducto(String producto) {
		this.producto = producto;
	}

	public int getConsumo() {
		return consumo;
	}

	public void setConsumo(int consumo) {
		this.consumo = consumo;
	}

	//ToSTring
	@Override
	public String toString() {
		return "Vista [petrol_id=" + petrol_id + ", fecha=" + fecha + ", comunidad=" + comunidad + ", provincia="
				+ provincia + ", producto=" + producto + ", consumo=" + consumo + "]";
	}
	
	
}
