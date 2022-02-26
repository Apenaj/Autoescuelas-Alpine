/**
 * 
 */
package AutoescuelasAlpine.controladores;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import AutoescuelasAlpine.modelo.Alumno;
import AutoescuelasAlpine.modelo.AlumnoRepository;
import AutoescuelasAlpine.modelo.Clase;

/**
 * @author ja.conde
 *
 */
@Controller
public class ControladorAlumnos {
	
	@Autowired
	private AlumnoRepository alumnos;

	
	@GetMapping("/altaAlumno")
	public String altaAlumno(Model model) {
		model.addAttribute("name", "Autoescuelas Alpine, Alta nuevo alumno");
		
		model.addAttribute("nombreCompleto", "");
		model.addAttribute("dni", "");
		
		return "alumno/Alta_alumno";
	}
	
	@PostMapping("/procesarAltaAlumno")
	public String procesarAltaAlumno(Model model, @RequestParam String nombreCompleto,@RequestParam String dni) {
		
		Alumno alumno=alumnos.findByDni(dni);
		if(alumno==null) {
			alumnos.save(new Alumno(nombreCompleto, dni));
			model.addAttribute("name", "Autoescuelas Alpine, Nuevo alumno grabado correctamente");
			
			model.addAttribute("nombreCompleto", nombreCompleto);
			model.addAttribute("dni", dni);
			
			model.addAttribute("siClases", false);
			model.addAttribute("clases", new ArrayList<Clase>());
			
			return "alumno/Detalle_alumno";
		}else{
			model.addAttribute("name", "Autoescuelas Alpine, Ya existe ese alumno");
			
			model.addAttribute("nombreCompleto", alumno.getNombreCompleto());
			model.addAttribute("dni", alumno.getDni());
			
			model.addAttribute("siClases", !alumno.getClasesReservadas().isEmpty());
			model.addAttribute("clases", alumno.getClasesReservadas());
			
			return "alumno/Detalle_alumno";
		}
		
	}
	
	@GetMapping("/buscaAlumno")
	public String buscaAlumno(Model model) {
		model.addAttribute("name", "Autoescuelas Alpine, Buscar alumno");
		
		return "alumno/Consulta_alumno";
	}

	@PostMapping("/procesarBuscaAlumno")
	public String procesarBuscaAlumno(Model model, @RequestParam String dni) {
		
		Alumno alumno=alumnos.findByDni(dni);
		if(alumno==null) {
			
			model.addAttribute("name", "Autoescuelas Alpine, No existe ese alumno");
		
			return "alumno/Consulta_alumno";
		}else{
			model.addAttribute("name", "Autoescuelas Alpine, Alumno");
			
			model.addAttribute("nombreCompleto", alumno.getNombreCompleto());
			model.addAttribute("dni", alumno.getDni());
			
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

			return "alumno/Modificar_alumno";
		}
	}
	
	@PostMapping("/procesarModificarAlumno")
	public String procesarModificarAlumno(Model model, @RequestParam String nombreCompleto,@RequestParam String dni) {
		
		Alumno alumno=alumnos.findByDni(dni);
		if(alumno!=null) {
			alumno.setNombreCompleto(nombreCompleto);
			alumnos.save(alumno);
			model.addAttribute("name", "Autoescuelas Alpine, Alumno grabado correctamente");
			
			model.addAttribute("nombreCompleto", nombreCompleto);
			model.addAttribute("dni", dni);
			
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
			
			return "alumno/Consulta_alumno";
		}
	}
	
}
