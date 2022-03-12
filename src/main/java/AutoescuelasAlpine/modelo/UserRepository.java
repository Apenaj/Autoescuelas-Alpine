/**
 * 
 */
package AutoescuelasAlpine.modelo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author ja.conde
 *
 */
public interface UserRepository extends JpaRepository<User, Long> {
	User findByname(String name);
	Optional<User> findByName(String name);
	
}
