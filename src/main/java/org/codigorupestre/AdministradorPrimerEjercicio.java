package org.codigorupestre;

import org.springframework.beans.factory.annotation.Autowired;

public class AdministradorPrimerEjercicio {

	private int idAdmin;
	private String nombre;

	//Para inyectar con spring
	@Autowired
	private DireccionPrimerEjercicio direccion;

	public AdministradorPrimerEjercicio() {

	}

	public AdministradorPrimerEjercicio(int idAdmin, String nombre) {
		this.idAdmin = idAdmin;
		this.nombre = nombre;
	}

	public DireccionPrimerEjercicio getDireccion() {
		return direccion;
	}

	public void setDireccion(DireccionPrimerEjercicio direccion) {
		this.direccion = direccion;
	}

	public void imprimirDireccion() {
		System.out.println("Encino 201");
	}

	public int getIdAdmin() {
		return idAdmin;
	}

	public void setIdAdmin(int idAdmin) {
		this.idAdmin = idAdmin;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Administrador [idAdmin=" + idAdmin + ", nombre=" + nombre + ", direccion=" + direccion + "]";
	}

}
