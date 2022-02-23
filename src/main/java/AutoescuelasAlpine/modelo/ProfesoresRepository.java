/**
 * 
 */
package AutoescuelasAlpine.modelo;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author ja.conde
 * @author a.penaj
 */
public interface ProfesoresRepository extends JpaRepository<Profesores, Long> {
	Profesores findByDni(String dni);
}
