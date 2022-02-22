/**
 * 
 */
package AutoescuelasAlpine.modelo;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author ja.conde
 *
 */
public interface VehiculoRepository extends JpaRepository<Vehiculo, String> {
	Vehiculo findBymatricula(String matricula);
}
