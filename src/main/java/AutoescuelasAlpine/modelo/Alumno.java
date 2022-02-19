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
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idAlumno;
	
	private String nombreCompleto;
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




	public long getIdAlumno() {
		return idAlumno;
	}
	public void setIdAlumno(long idAlumno) {
		this.idAlumno = idAlumno;
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
		return "Alumno [idAlumno=" + idAlumno + ", nombreCompleto=" + nombreCompleto + ", dni=" + dni + "]";
	}
	
	
	
}
