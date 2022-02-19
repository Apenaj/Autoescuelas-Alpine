/**
 * 
 */
package AutoescuelasAlpine.modelo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * @author ja.conde
 *
 */
@Entity
public class Vehiculo {
	
	@Id
	private String matricula;
	
	private String modelo;
	
	@OneToOne
	private Profesores profesor;

	public Vehiculo() {
		super();
	}

	public Vehiculo(String matricula, String modelo, Profesores profesor) {
		super();
		this.matricula = matricula;
		this.modelo = modelo;
		this.profesor = profesor;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public Profesores getProfesor() {
		return profesor;
	}

	public void setProfesor(Profesores profesor) {
		this.profesor = profesor;
	}
	
	

}
