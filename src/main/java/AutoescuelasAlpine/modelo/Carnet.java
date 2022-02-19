/**
 * 
 */
package AutoescuelasAlpine.modelo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * @author ja.conde
 *
 */
@Entity
public class Carnet {
	
	@Id
	private String tipo;
	
	

	public Carnet() {
		super();
	}

	public Carnet(String tipo) {
		super();
		this.tipo = tipo;
	}






	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	

}
