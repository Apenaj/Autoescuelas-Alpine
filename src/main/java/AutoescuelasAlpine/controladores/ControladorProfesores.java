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
	public String altaProfesores(Model model) {
		model.addAttribute("name", "Autoescuelas Alpine, Alta nuevo profesor");
		
		model.addAttribute("nombreCompleto", "");
		model.addAttribute("dni", "");
		
		return "profesores/Alta_profesor";
	}

	@PostMapping("/procesarAltaProfesores")
	public String procesarAltaProfesores(Model model, @RequestParam String nombreCompleto,@RequestParam String dni) {
		
		Profesores profesor=profesores.findByDni(dni);
		if(profesor==null) {
			profesores.save(new Profesores(nombreCompleto, dni));
			model.addAttribute("name", "Autoescuelas Alpine, Nuevo profesor grabado correctamente");
			
			model.addAttribute("nombreCompleto", nombreCompleto);
			model.addAttribute("dni", dni);
			
			
			
			return "profesores/Detalle_profesor";
		}else{
			model.addAttribute("name", "Autoescuelas Alpine, Ya existe ese profesor");
			
			model.addAttribute("nombreCompleto", profesor.getNombreCompleto());
			model.addAttribute("dni", profesor.getDni());
			
			
			
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
			model.addAttribute("name", "Autoescuelas Alpine, Alumno");
			
			model.addAttribute("nombreCompleto", profesor.getNombreCompleto());
			model.addAttribute("dni", profesor.getDni());
			
			
			
			return "profesores/Detalle_profesor";
		}
		
	}
	
	@GetMapping("/ModificaProfesores")
	public String modificarProfesores(Model model,@RequestParam String dni) {
		Profesores profesor=profesores.findByDni(dni);
		if(profesor==null) {

			model.addAttribute("name", "Autoescuelas Alpine, No existe ese alumno");

			return "profesores/Consulta_profesor";
		}else{
			model.addAttribute("name", "Autoescuelas Alpine, Modificar alumno");

			model.addAttribute("nombreCompleto", profesor.getNombreCompleto());
			model.addAttribute("dni", profesor.getDni());

			return "profesores/Modificar_profesor";
		}
	}
	
	@PostMapping("/procesarModificarProfesores")
	public String procesarModificarProfesores(Model model, @RequestParam String nombreCompleto,@RequestParam String dni) {
		
		Profesores profesor=profesores.findByDni(dni);
		if(profesor!=null) {
			profesor.setNombreCompleto(nombreCompleto);
			profesor.setDni(dni);
			profesores.save(profesor);
			model.addAttribute("name", "Autoescuelas Alpine, Profesor grabado correctamente");
			
			model.addAttribute("nombreCompleto", nombreCompleto);
			model.addAttribute("dni", dni);
			
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
			
			return "profesores/Consulta_profesor";
		}
	}

}