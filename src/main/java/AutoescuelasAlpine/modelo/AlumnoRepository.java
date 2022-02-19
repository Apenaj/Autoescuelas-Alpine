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
public interface AlumnoRepository extends JpaRepository<Alumno, String> {
	
	List<Alumno> findByNombreCompletoIgnoreCase(String nombreCompleto);
	Alumno findByDni(String dni);

}
