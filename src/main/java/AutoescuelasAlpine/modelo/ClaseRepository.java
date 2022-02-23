/**
 * 
 */
package AutoescuelasAlpine.modelo;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author ja.conde
 *
 */
public interface ClaseRepository extends JpaRepository<Clase, Long> {

	//List<Clase> findByfechaHoraComienzo(Timestamp fechaHoraComienzo);
	List<Clase> findBycarnet(Carnet carnet);
	List<Clase> findByprofesor(Profesores profesor);
	//Clase findByfechaHoraComienzoAndprofesor(Timestamp fechaHoraComienzo,Profesores profesor);
	
}
