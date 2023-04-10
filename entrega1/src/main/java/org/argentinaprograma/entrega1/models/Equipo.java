package org.argentinaprograma.entrega1.models;

public class Equipo {
	//Atributos
	private String nombre;
	private String descripcion;
	
	//Constructores
	public Equipo(String nombre, String descripcion) {
		this.nombre = nombre;
		this.descripcion = descripcion;
	}
	
	public Equipo(String nombre) {
		this.nombre = nombre;
		this.descripcion = "Sin Descripcion";
	}
	
	//Setters and Getters
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
    //Metodo para ver si tiene el mismo nombre
	public boolean esEl(Equipo equipo1) {
		if(this.getNombre().equals(equipo1.getNombre())) {
			return true;
		}
		return false;
	}

	
}
