package com.viewnext.kidaprojects.cursosbdd.model;

import java.util.Objects;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


/**
 * Clase que representa un curso en la aplicación.
 * 
 * <p>
 * La entidad {@code Curso} contiene información sobre un curso específico,
 * incluido su código, nombre, número de horas y precio. Esta clase se utiliza
 * para representar los cursos en la aplicación y se mapea a una tabla en la
 * base de datos.
 * </p>
 *
 * <p>
 * El autor de esta clase es Víctor Colorado "Kid A".
 * </p>
 *
 * @version 1.0
 * @since 27 de septiembre de 2023
 */
@Entity
@Table(name = "cursos")
public class Curso {

	@Id
	private String codigo;
	private String nombre;
	private int numeroHoras;
	private int precio;
	
	
	public Curso(String codigo, String nombre, int numeroHoras, int precio) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.numeroHoras = numeroHoras;
		this.precio = precio;
	}
	
	public Curso() {
		
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getNumeroHoras() {
		return numeroHoras;
	}

	public void setNumeroHoras(int numeroHoras) {
		this.numeroHoras = numeroHoras;
	}

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Curso other = (Curso) obj;
		return Objects.equals(codigo, other.codigo);
	}

	@Override
	public String toString() {
		return "Curso [codigo=" + codigo + ", nombre=" + nombre + ", numeroHoras=" + numeroHoras + ", precio=" + precio
				+ "]";
	}
	
	
	
}