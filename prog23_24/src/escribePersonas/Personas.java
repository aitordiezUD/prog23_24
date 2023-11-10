package escribePersonas;

import java.io.Serializable;

public class Personas implements Serializable{
	int dni;
	String nombre;
	int edad;
	
	
	public int getDni() {
		return dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public Personas(int dni, String nombre, int edad) {
		this.dni = dni;
		this.nombre = nombre;
		this.edad = edad;
	}

	@Override
	public String toString() {
		return "Personas [dni=" + dni + ", nombre=" + nombre + ", edad=" + edad + "]";
	}
	
	
}
