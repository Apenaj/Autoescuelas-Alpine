/**
 * 
 */
package AutoescuelasAlpine.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author ja.conde
 *
 */
@Entity
public class Profesores {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idProfesor;
	
	private String nombreCompleto;
	private String dni;

	
	
	public Profesores() {
		super();
	}
	
	
	public Profesores(String nombreCompleto, String dni) {
		super();
		this.nombreCompleto = nombreCompleto;
		this.dni = dni;
	}


	public long getIdProfesor() {
		return idProfesor;
	}
	public void setIdProfesor(long idProfesor) {
		this.idProfesor = idProfesor;
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
	
	

}
