/**
 * 
 */
package AutoescuelasAlpine.modelo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;


/**
 * @author ja.conde
 *
 */
@Entity
public class Alumno {
	
	private String nombreCompleto;
	
	@Id
	private String dni;
	
	@ManyToMany
	private List<Examen> examenes;
	
	
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
	
	
	
}
