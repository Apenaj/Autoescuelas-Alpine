/**
 * 
 */
package AutoescuelasAlpine.modelo;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * @author ja.conde
 *
 */
@Entity
public class Clase {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idClase;
	
	private Timestamp fechaHoraComienzo;
	private int duracion;
	
	@OneToMany
	private Carnet carnet;
	
	@OneToMany
	private Profesores profesor;

	public Clase() {
		super();
	}

	public Clase(Timestamp fechaHoraComienzo, int duracion, Carnet carnet, Profesores profesor) {
		super();
		this.fechaHoraComienzo = fechaHoraComienzo;
		this.duracion = duracion;
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

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
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
	
	
}
