/**
 * 
 */
package AutoescuelasAlpine.modelo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author ja.conde
 * @author a.penaj
 */
public interface ProfesoresRepository extends JpaRepository<Profesores, Long> {
	Profesores findByDni(String dni);
}
