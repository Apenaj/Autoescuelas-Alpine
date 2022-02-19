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
import javax.persistence.OneToMany;

/**
 * @author ja.conde
 *
 */
@Entity
public class Examen {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idExamen;
	
	@OneToMany
	private Carnet carnet;
	
	@OneToMany
	private Profesores profesor;
	
	@ManyToMany
	private List<Alumno> alumnos;

	public Examen() {
		super();
	}

	public Examen(Carnet carnet, Profesores profesor, List<Alumno> alumnos) {
		super();
		this.carnet = carnet;
		this.profesor = profesor;
		this.alumnos = alumnos;
	}

	public long getIdExamen() {
		return idExamen;
	}

	public void setIdExamen(long idExamen) {
		this.idExamen = idExamen;
	}

	public Carnet getCarnet() {
		return carnet;
	}

	public void setCarnet(Carnet carnet) {
		this.carnet = carnet;
	}

	public Profesores getProfesor() {
		return profesor;
	}

	public void setProfesor(Profesores profesor) {
		this.profesor = profesor;
	}

	public List<Alumno> getAlumnos() {
		return alumnos;
	}

	public void setAlumnos(List<Alumno> alumnos) {
		this.alumnos = alumnos;
	}
	
	
	

}
