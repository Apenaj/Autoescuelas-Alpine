/**
 * 
 */
package AutoescuelasAlpine.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import AutoescuelasAlpine.modelo.Profesores;
import AutoescuelasAlpine.modelo.ProfesoresRepository;

/**
 * @author ja.conde
 * @author a.penaj
 */
@Controller
public class ControladorProfesores {
	
	@Autowired
	private ProfesoresRepository profesores;

	
	@GetMapping("/altaProfesores")
	public String altaAlumno(Model model) {
		model.addAttribute("name", "Autoescuelas Alpine, Alta nuevo profesor");
		
		model.addAttribute("nombreCompleto", "");
		model.addAttribute("dni", "");
		
		return "Alta_profesor";
	}
	/*
	@PostMapping("/procesarAltaProfesor")
	public String procesarAltaProfesor(Model model, @RequestParam String nombreCompleto,@RequestParam String dni) {
		
		Profesores profesor=profesores.findByDni(dni);
		if(profesor==null) {
			profesores.save(new Profesores(nombreCompleto, dni));
			model.addAttribute("name", "Autoescuelas Alpine, Nuevo profesor grabado correctamente");
			
			model.addAttribute("nombreCompleto", nombreCompleto);
			model.addAttribute("dni", dni);
			
			
			
			return "Detalle_profesor";
		}else{
			model.addAttribute("name", "Autoescuelas Alpine, Ya existe ese profesor");
			
			model.addAttribute("nombreCompleto", profesor.getNombreCompleto());
			model.addAttribute("dni", profesor.getDni());
			
			
			
			return "Detalle_profesor";
		}
		
	}
	
	@GetMapping("/buscaProfesor")
	public String buscaAlumno(Model model) {
		model.addAttribute("name", "Autoescuelas Alpine, Buscar profesor");
		
		return "Consulta_profesor";
	}

	@PostMapping("/procesarBuscaProfesor")
	public String procesarBuscaAlumno(Model model, @RequestParam String dni) {
		
		Profesores profesor=profesores.findByDni(dni);
		if(profesor==null) {
			
			model.addAttribute("name", "Autoescuelas Alpine, No existe ese profesor");
		
			return "Consulta_profesor";
		}else{
			model.addAttribute("name", "Autoescuelas Alpine, Alumno");
			
			model.addAttribute("nombreCompleto", profesor.getNombreCompleto());
			model.addAttribute("dni", profesor.getDni());
			
			
			
			return "Detalle_profesor";
		}
		
	}
	
	@GetMapping("/ModificaProfesor")
	public String modificarAlumno(Model model,@RequestParam String dni) {
		Profesores profesor=profesores.findByDni(dni);
		if(profesor==null) {

			model.addAttribute("name", "Autoescuelas Alpine, No existe ese alumno");

			return "Consulta_profesor";
		}else{
			model.addAttribute("name", "Autoescuelas Alpine, Modificar alumno");

			model.addAttribute("nombreCompleto", profesor.getNombreCompleto());
			model.addAttribute("dni", profesor.getDni());

			return "Modificar_profesor";
		}
	}
	
	@PostMapping("/procesarModificarProfesor")
	public String procesarModificarAlumno(Model model, @RequestParam String nombreCompleto,@RequestParam String dni) {
		
		Profesores profesor=profesores.findByDni(dni);
		if(profesor!=null) {
			profesor.setNombreCompleto(nombreCompleto);
			profesores.save(profesor);
			model.addAttribute("name", "Autoescuelas Alpine, Profesor grabado correctamente");
			
			model.addAttribute("nombreCompleto", nombreCompleto);
			model.addAttribute("dni", dni);
			
			return "Detalle_profesor";
		}else{
			model.addAttribute("name", "Autoescuelas Alpine, no existe ese profesor");
			
			
			return "Consulta_profesor";
		}
		
	}
	
	@GetMapping("/borraProfesor")
	public String borrarAlumno(Model model,@RequestParam String dni) {
		Profesores profesor=profesores.findByDni(dni);
		if(profesor==null) {

			model.addAttribute("name", "Autoescuelas Alpine, No existe ese profesor");

			return "Consulta_profesor";
		}else{
			model.addAttribute("name", "Autoescuelas Alpine,  Profesor borrado correctamente");

			examenes.delete(examen);
			
			return "Consulta_profesor";
		}
	}
	*/
}