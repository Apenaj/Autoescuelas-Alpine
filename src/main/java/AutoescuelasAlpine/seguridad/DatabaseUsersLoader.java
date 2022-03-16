package AutoescuelasAlpine.seguridad;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import AutoescuelasAlpine.modelo.User;
import AutoescuelasAlpine.modelo.UserRepository;

@Component
public class DatabaseUsersLoader {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostConstruct
	private void initDatabase() {

		List<String> listaRolesAdmin=new ArrayList<String>();
		List<String> listaRolesUser=new ArrayList<String>();
		List<String> listaRolesProfesor=new ArrayList<String>();
		//listaRolesAdmin.add("ALUMNO");
		listaRolesAdmin.add("ADMIN");
		listaRolesUser.add("ALUMNO");	 
		listaRolesProfesor.add("PROFESOR");
		userRepository.save(
				new User("profesor",passwordEncoder.encode("profesor"),listaRolesProfesor));
		userRepository.save(
				new User("alumno",passwordEncoder.encode("alumno"),listaRolesUser));
		userRepository.save(
				new User("admin",passwordEncoder.encode("adminpass"),listaRolesAdmin));
	}
}







/*import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import AutoescuelasAlpine.modelo.User;
import AutoescuelasAlpine.modelo.UserRepository;

@Component
public class DatabaseUsersLoader {
 @Autowired
 private UserRepository userRepository;
 
 @Autowired
 
 private PasswordEncoder passwordEncoder;
 
 @PostConstruct
 private void initDatabase() {
	 
	 List<String> listaRolesAdmin=new ArrayList<String>();
	 List<String> listaRolesUser=new ArrayList<String>();
	 listaRolesAdmin.add("ROLE_USER");
	 listaRolesAdmin.add("ROLE_ADMIN");
	 listaRolesUser.add("ROLE_USER");
	 
	 
	 

 //userRepository.save(new User("user",passwordEncoder.encode("pass"),listaRolesUser));
 //userRepository.save(new User("admin",passwordEncoder.encode("admin"),listaRolesAdmin));
	userRepository.save(new User("user",passwordEncoder.encode("pass")));
	 userRepository.save(new User("admin",passwordEncoder.encode("admin")));
 //userRepository.save(new User("user","pass",listaRolesUser));
 //userRepository.save(new User("admin","admin",listaRolesAdmin));
 }
}
*/