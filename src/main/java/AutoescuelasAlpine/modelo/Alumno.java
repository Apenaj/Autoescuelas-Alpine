/**
 * 
 */
package AutoescuelasAlpine.modelo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

/**
 * @author ja.conde
 * @author a.penaj
 */
@Entity
public class Alumno {
	
	@Id
	private String dni;
	private String nombreCompleto;
	
	@ManyToMany
	private List<Clase> clasesReservadas;
	
	public Alumno() {
		super();
	}
			
	public Alumno(String nombreCompleto, String dni) {
		super();
		this.nombreCompleto = nombreCompleto;
		this.dni = dni;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}

	@Override
	public String toString() {
		return "Alumno [ nombreCompleto=" + nombreCompleto + ", dni=" + dni + "]";
	}

	public List<Clase> getClasesReservadas() {
		return clasesReservadas;
	}

	public void setClasesReservadas(List<Clase> clasesReservadas) {
		this.clasesReservadas = clasesReservadas;
	}	
	
	
	
}
