/**
 * 
 */
package AutoescuelasAlpine.controladores;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

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
import AutoescuelasAlpine.modelo.Clase;
import AutoescuelasAlpine.modelo.Profesores;
import AutoescuelasAlpine.modelo.ProfesoresRepository;
import AutoescuelasAlpine.modelo.User;
import AutoescuelasAlpine.modelo.UserRepository;
import AutoescuelasAlpine.servicioInterno.NotificacionService;

import javax.servlet.http.HttpServletRequest;
/**
 * @author ja.conde
 * @author a.penaj
 */
@Controller
public class ControladorAlumnos {
	
	@Autowired
	private ProfesoresRepository profesores;
	
	@Autowired
	private AlumnoRepository alumnos;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private NotificacionService notificacionService;
	
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
	
	@GetMapping("/altaAlumno")
	public String altaAlumno(Model model) {
		model.addAttribute("name", "Autoescuelas Alpine, Alta nuevo alumno");
		
		model.addAttribute("nombreCompleto", "");
		model.addAttribute("dni", "");
		model.addAttribute("password", "");
		model.addAttribute("email", "");
		
		return "alumno/Alta_alumno";
	}
	
	@PostMapping("/procesarAltaAlumno")
	public String procesarAltaAlumno(Model model, @RequestParam String nombreCompleto,@RequestParam String dni,@RequestParam String password,@RequestParam String email) {
		Alumno alumno=alumnos.findByDni(dni);
		User usuario=userRepository.findByname(dni);
		if(alumno==null && usuario==null) {
			alumnos.save(new Alumno(nombreCompleto, dni,email));
			String mensaje="Se ha dado de alta en la Autoescuela Alpine, estos son sus datos para entrar:";
			mensaje=mensaje+"</br>usuario:"+dni;
			mensaje=mensaje+"</br>password:"+password;
			mensaje=mensaje+"</br> Un saludo.";
			String respMensaje;
			if(notificacionService.enviarNotificacion(email, mensaje)) {
				respMensaje="Correo enviado correctamente";
			}else {
				respMensaje="Error al enviar el correo.";
			}
			List<String> listaRolesUser=new ArrayList<String>();
			listaRolesUser.add(User.ROL_ALUMNO);	 
			userRepository.save(
					new User(dni,passwordEncoder.encode(password),listaRolesUser));
			
			model.addAttribute("name", "Autoescuelas Alpine, Nuevo alumno grabado correctamente,"+respMensaje);
			
			model.addAttribute("nombreCompleto", nombreCompleto);
			model.addAttribute("dni", dni);
			model.addAttribute("email", email);
			
			model.addAttribute("siClases", false);
			model.addAttribute("clases", new ArrayList<Clase>());
			
			return "alumno/Detalle_alumno";
		}else{
			if(alumno==null) {
				model.addAttribute("name", "Autoescuelas Alpine, Ya existe un profesor con ese dni no se puede dar de alta al alumno");
				Profesores profesor=profesores.findByDni(dni);
				model.addAttribute("nombreCompleto", profesor.getNombreCompleto());
				model.addAttribute("dni", profesor.getDni());
				model.addAttribute("email", email);
				
				model.addAttribute("siClases", null);
				model.addAttribute("clases", null);
				
			}else {
				model.addAttribute("name", "Autoescuelas Alpine, Ya existe un alumno  con ese dni, no se puede dar de alta al  alumno");
				model.addAttribute("nombreCompleto", alumno.getNombreCompleto());
				model.addAttribute("dni", alumno.getDni());
				model.addAttribute("email", email);
				
				model.addAttribute("siClases", !alumno.getClasesReservadas().isEmpty());
				model.addAttribute("clases", alumno.getClasesReservadas());
			}
			
			
			
			return "alumno/Detalle_alumno";
		}
		
	}
	
	@GetMapping("/buscaAlumno")
	public String buscaAlumno(Model model) {
		System.out.println("busca alumno");
		model.addAttribute("name", "Autoescuelas Alpine, Buscar alumno");
		
		return "alumno/Consulta_alumno";
	}
	
	@PostMapping("/procesarBuscaAlumno")
	public String procesarBuscaAlumno(Model model, @RequestParam String dni) {
		System.out.println("procesa busca alumno");
		Alumno alumno=alumnos.findByDni(dni);
		if(alumno==null) {
			
			model.addAttribute("name", "Autoescuelas Alpine, No existe ese alumno");
		
			return "alumno/Consulta_alumno";
		}else{
			model.addAttribute("name", "Autoescuelas Alpine, Alumno");
			
			model.addAttribute("nombreCompleto", alumno.getNombreCompleto());
			model.addAttribute("dni", alumno.getDni());
			model.addAttribute("email", alumno.getEmail());
			
			model.addAttribute("siClases", !alumno.getClasesReservadas().isEmpty());
			model.addAttribute("clases", alumno.getClasesReservadas());
			
			return "alumno/Detalle_alumno";
		}
		
	}
	
	@GetMapping("/ModificaAlumno")
	public String modificarAlumno(Model model,@RequestParam String dni) {
		Alumno alumno=alumnos.findByDni(dni);
		if(alumno==null) {

			model.addAttribute("name", "Autoescuelas Alpine, No existe ese alumno");

			return "alumno/Consulta_alumno";
		}else{
			model.addAttribute("name", "Autoescuelas Alpine, Modificar alumno");

			model.addAttribute("nombreCompleto", alumno.getNombreCompleto());
			model.addAttribute("dni", alumno.getDni());
			model.addAttribute("email", alumno.getEmail());

			return "alumno/Modificar_alumno";
		}
	}
	
	@PostMapping("/procesarModificarAlumno")
	public String procesarModificarAlumno(Model model, @RequestParam String nombreCompleto,@RequestParam String dni,@RequestParam String email) {
		
		Alumno alumno=alumnos.findByDni(dni);
		if(alumno!=null) {
			alumno.setNombreCompleto(nombreCompleto);
			alumno.setEmail(email);
			alumnos.save(alumno);
			model.addAttribute("name", "Autoescuelas Alpine, Alumno grabado correctamente");
			
			model.addAttribute("nombreCompleto", nombreCompleto);
			model.addAttribute("dni", dni);
			model.addAttribute("email", alumno.getEmail());
			
			model.addAttribute("siClases", !alumno.getClasesReservadas().isEmpty());
			model.addAttribute("clases", alumno.getClasesReservadas());
			
			return "alumno/Detalle_alumno";
		}else{
			model.addAttribute("name", "Autoescuelas Alpine, no existe ese alumno");
			
			
			return "alumno/Consulta_alumno";
		}
		
	}
	
	@GetMapping("/borraAlumno")
	public String borrarAlumno(Model model,@RequestParam String dni) {
		Alumno alumno=alumnos.findByDni(dni);
		if(alumno==null) {

			model.addAttribute("name", "Autoescuelas Alpine, No existe ese alumno");

			return "alumno/Consulta_alumno";
		}else{
			model.addAttribute("name", "Autoescuelas Alpine,  Alumno borrado correctamente");
			
			alumnos.delete(alumno);
			userRepository.delete(userRepository.findByname(dni));
			
			return "alumno/Consulta_alumno";
		}
	}
	
}
