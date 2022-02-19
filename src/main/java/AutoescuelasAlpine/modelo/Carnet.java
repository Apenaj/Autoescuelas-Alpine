/**
 * 
 */
package AutoescuelasAlpine.modelo;

import javax.persistence.Entity;
import javax.persistence.Id;

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
