/**
 * 
 */
package AutoescuelasAlpine.modelo;

import java.util.Optional;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import AutoescuelasAlpine.InicioAlpineApplication;

/**
 * @author ja.conde
 *
 */
@CacheConfig(cacheNames=InicioAlpineApplication.CACHE_CARNETS)
public interface CarnetRepository extends JpaRepository<Carnet, String> {

	@CacheEvict(allEntries=true)
	Carnet save(Carnet carnet);
	
	@CacheEvict
	void delete(Carnet carnet);
	
	@Cacheable
	Optional<Carnet> findById(String id);
	
}
