/**
 * 
 */
package AutoescuelasAlpine.modelo;

import java.util.List;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import AutoescuelasAlpine.InicioAlpineApplication;

/**
 * @author ja.conde
 * @author a.penaj
 */
@CacheConfig(cacheNames=InicioAlpineApplication.CACHE_ALUMNOS)
public interface AlumnoRepository extends JpaRepository<Alumno, String> {
	
	@CacheEvict(allEntries=true)
	Alumno save(Alumno alumno);
	
	@CacheEvict
	void delete(Alumno alumno);
	
	@Cacheable
	List<Alumno> findByNombreCompletoIgnoreCase(String nombreCompleto);
	
	@Cacheable
	Alumno findByDni(String dni);

}
