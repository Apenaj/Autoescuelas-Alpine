/**
 * 
 */
package AutoescuelasAlpine.modelo;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

/**
 * @author ja.conde
 *
 */
@Entity
//@Table(uniqueConstraints=
//@UniqueConstraint(columnNames = {"fechaHoraComienzo", "profesor"})) 
public class Clase {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idClase;
	
	private Timestamp fechaHoraComienzo;
	
	@ManyToOne
	private Carnet carnet;
	
	@ManyToOne
	private Profesores profesor;
	
	@ManyToMany
	private List<Alumno> alumnosApuntados;

	public Clase() {
		super();
	}

	public Clase(Timestamp fechaHoraComienzo, Carnet carnet, Profesores profesor) {
		super();
		this.fechaHoraComienzo = fechaHoraComienzo;
		this.carnet = carnet;
		this.profesor = profesor;
	}

	public long getIdClase() {
		return idClase;
	}

	public void setIdClase(long idClase) {
		this.idClase = idClase;
	}

	public Timestamp getFechaHoraComienzo() {
		return fechaHoraComienzo;
	}

	public void setFechaHoraComienzo(Timestamp fechaHoraComienzo) {
		this.fechaHoraComienzo = fechaHoraComienzo;
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

	public List<Alumno> getAlumnosApuntados() {
		return alumnosApuntados;
	}

	public void setAlumnosApuntados(List<Alumno> alumnosApuntados) {
		this.alumnosApuntados = alumnosApuntados;
	}

	
	
}
