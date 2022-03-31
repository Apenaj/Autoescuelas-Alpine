/**
 * 
 */
package AutoescuelasAlpine.controladores;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import AutoescuelasAlpine.modelo.Alumno;
import AutoescuelasAlpine.modelo.AlumnoRepository;
import AutoescuelasAlpine.modelo.Profesores;
import AutoescuelasAlpine.modelo.ProfesoresRepository;
import AutoescuelasAlpine.modelo.User;
import AutoescuelasAlpine.modelo.UserRepository;
import AutoescuelasAlpine.servicioInterno.NotificacionService;

/**
 * @author ja.conde
 * @author a.penaj
 */
@Controller
public class ControladorProfesores {
	
	@Autowired
	private AlumnoRepository alumnos;
	
	@Autowired
	private ProfesoresRepository profesores;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@ModelAttribute
	public void addAttributes(Model model, HttpServletRequest request) {

		Principal principal = request.getUserPrincipal();

		if(principal != null) {
		
			//model.addAttribute("logged", true);		
			model.addAttribute("username", principal.getName());
			model.addAttribute("profesor", request.isUserInRole(User.ROL_PROFESOR) || request.isUserInRole(User.ROL_ADMIN));
			model.addAttribute("admin", request.isUserInRole(User.ROL_ADMIN));
			
		} else {
			model.addAttribute("logged", false);
			;
		}
	}
	
	@GetMapping("/altaProfesores")
	public String altaProfesores(Model model) {
		model.addAttribute("name", "Autoescuelas Alpine, Alta nuevo profesor");
		
		model.addAttribute("nombreCompleto", "");
		model.addAttribute("dni", "");
		model.addAttribute("password", "");
		model.addAttribute("email", "");
		return "profesores/Alta_profesor";
	}

	@PostMapping("/procesarAltaProfesores")
	//public String procesarAltaProfesores(Model model, @RequestParam String nombreCompleto,@RequestParam String dni,@RequestParam String password) {
		public String procesarAltaProfesores(Model model, @RequestParam String nombreCompleto,@RequestParam String dni,@RequestParam String password,@RequestParam String email){
		Profesores profesor=profesores.findByDni(dni);
		User usuario=userRepository.findByname(dni);
		if(profesor==null && usuario==null) {
			//profesores.save(new Profesores(nombreCompleto, dni));
			
			//test
			profesores.save(new Profesores(nombreCompleto, dni,email));
			String mensaje="Se ha dado de alta en la Autoescuela Alpine, estos son sus datos para entrar:";
			mensaje=mensaje+"</br>usuario:"+dni;
			mensaje=mensaje+"</br>password:"+password;
			mensaje=mensaje+"</br> Un saludo.";
			String respMensaje;
			if(NotificacionService.enviarNotificacion(email, mensaje)) {
				respMensaje="Correo enviado correctamente";
			}else {
				respMensaje="Error al enviar el correo.";
			}
			
			
			
			//
			List<String> listaRolesUser=new ArrayList<String>();
			listaRolesUser.add(User.ROL_PROFESOR);	 
			userRepository.save(
					new User(dni,passwordEncoder.encode(password),listaRolesUser));
			
			model.addAttribute("name", "Autoescuelas Alpine, Nuevo profesor grabado correctamente"+respMensaje);
			
			model.addAttribute("nombreCompleto", nombreCompleto);
			model.addAttribute("dni", dni);
			model.addAttribute("email", email);
			
			
			return "profesores/Detalle_profesor";
		}else{
			//si uno de los 2 usuario o profesor existen.
			if(profesor==null) {
				model.addAttribute("name", "Autoescuelas Alpine, Ya existe un alumno con ese dni no se puede dar de alta al profesor");
				Alumno alumno=alumnos.findByDni(dni);
				model.addAttribute("nombreCompleto", alumno.getNombreCompleto());
				model.addAttribute("dni", alumno.getDni());
				model.addAttribute("email", email);
			}else {
				model.addAttribute("name", "Autoescuelas Alpine, Ya existe un profesor con ese dni no puede darse de alta al profesor");
				model.addAttribute("nombreCompleto", profesor.getNombreCompleto());
				model.addAttribute("dni", profesor.getDni());
				model.addAttribute("email", email);
			}
			
				
			
				
			
				
	
			
			
			return "profesores/Detalle_profesor";
		}
		
	}
	
	@GetMapping("/buscaProfesores")
	public String buscaProfesores(Model model) {
		model.addAttribute("name", "Autoescuelas Alpine, Buscar profesor");
		
		return "profesores/Consulta_profesor";
	}

	@PostMapping("/procesarBuscaProfesores")
	public String procesarBuscaProfesores(Model model, @RequestParam String dni) {
		
		Profesores profesor=profesores.findByDni(dni);
		if(profesor==null) {
			
			model.addAttribute("name", "Autoescuelas Alpine, No existe ese profesor");
		
			return "profesores/Consulta_profesor";
		}else{
			model.addAttribute("name", "Autoescuelas Alpine, Profesor");
			
			model.addAttribute("nombreCompleto", profesor.getNombreCompleto());
			model.addAttribute("dni", profesor.getDni());
			model.addAttribute("email", profesor.getEmail());
			
			
			
			return "profesores/Detalle_profesor";
		}
		
	}
	
	@GetMapping("/ModificaProfesores")
	public String modificarProfesores(Model model,@RequestParam String dni) {
		Profesores profesor=profesores.findByDni(dni);
		if(profesor==null) {

			model.addAttribute("name", "Autoescuelas Alpine, No existe ese profesor");

			return "profesores/Consulta_profesor";
		}else{
			model.addAttribute("name", "Autoescuelas Alpine, Modificar profesor");

			model.addAttribute("nombreCompleto", profesor.getNombreCompleto());
			model.addAttribute("dni", profesor.getDni());
			model.addAttribute("email", profesor.getEmail());

			return "profesores/Modificar_profesor";
		}
	}
	
	@PostMapping("/procesarModificarProfesores")
	public String procesarModificarProfesores(Model model, @RequestParam String nombreCompleto,@RequestParam String dni,@RequestParam String email) {
		
		Profesores profesor=profesores.findByDni(dni);
		if(profesor!=null) {
			profesor.setNombreCompleto(nombreCompleto);
			profesor.setDni(dni);
			profesor.setEmail(email);
			profesores.save(profesor);
			model.addAttribute("name", "Autoescuelas Alpine, Profesor grabado correctamente");
			
			model.addAttribute("nombreCompleto", nombreCompleto);
			model.addAttribute("dni", dni);
			model.addAttribute("email", profesor.getEmail());
			
			return "profesores/Detalle_profesor";
		}else{
			model.addAttribute("name", "Autoescuelas Alpine, no existe ese profesor");
			
			
			return "profesores/Consulta_profesor";
		}
		
	}
	
	@GetMapping("/borraProfesores")
	public String borrarProfesores(Model model,@RequestParam String dni) {
		Profesores profesor=profesores.findByDni(dni);
		if(profesor==null) {

			model.addAttribute("name", "Autoescuelas Alpine, No existe ese profesor");

			return "profesores/Consulta_profesor";
		}else{
			model.addAttribute("name", "Autoescuelas Alpine,  Profesor borrado correctamente");

			profesores.delete(profesor);
			userRepository.delete(userRepository.findByname(dni));
			
			return "profesores/Consulta_profesor";
		}
	}

}