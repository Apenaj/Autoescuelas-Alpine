/**
 * 
 */
package AutoescuelasAlpine.modelo;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author ja.conde
 * @author a.penaj
 */
@Entity
public class Profesores {
	
	
	@Id
	private String dni;
	private String nombreCompleto;

	
	
	public Profesores() {
		super();
	}
	
	
	public Profesores(String nombreCompleto, String dni) {
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
	
	

}
