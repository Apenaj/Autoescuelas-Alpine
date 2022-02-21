/**
 * 
 */
package AutoescuelasAlpine.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import AutoescuelasAlpine.modelo.Alumno;
import AutoescuelasAlpine.modelo.Carnet;
import AutoescuelasAlpine.modelo.Examen;
import AutoescuelasAlpine.modelo.ExamenRepository;
import AutoescuelasAlpine.modelo.Profesores;

/**
 * @author ja.conde
 *
 */
@Controller
public class ControladorExamen {
	
	@Autowired
	private ExamenRepository examenes;

	
	@GetMapping("/altaExamen")
	public String altaExamen(Model model) {
		model.addAttribute("name", "Autoescuelas Alpine, Alta nuevo Carnet");
		
		model.addAttribute("carnet", "");
		model.addAttribute("profesor", "");
		model.addAttribute("alumnos", "");	
		
		return "examen/Alta_Examen";
	}
	
	@PostMapping("/procesarAltaExamen")
	public String procesarAltaExamen(Model model, @RequestParam Carnet carnet,@RequestParam Profesores profesor,@RequestParam List<Alumno> alumnos){
		
			examenes.save(new Examen(carnet,profesor,alumnos));
			model.addAttribute("name", "Autoescuelas Alpine, Nuevo Examen grabado correctamente");
			
			model.addAttribute("carnet", carnet);
			model.addAttribute("profesor", profesor);
			model.addAttribute("alumnos", alumnos);	
			
			return "examen/Detalle_Examen";
	}
	
	@GetMapping("/buscaExamen")
	public String buscaExamen(Model model) {
		model.addAttribute("name", "Autoescuelas Alpine, Buscar Examen");
		
		return "examen/Consulta_Examen";
	}

	@PostMapping("/procesarBuscaExamen")
	public String procesarBuscaExamen(Model model, @RequestParam long id) {
		
		
		if(!examenes.existsById(id)) {
			
			model.addAttribute("name", "Autoescuelas Alpine, No existe ese Examen");
		
			return "examen/Consulta_Examen";
		}else{
			Examen examen=examenes.getById(id);
			model.addAttribute("name", "Autoescuelas Alpine, Examen");
			
			model.addAttribute("id", examen.getIdExamen());
			model.addAttribute("carnet", examen.getCarnet());
			model.addAttribute("alumnos", examen.getAlumnos());
			model.addAttribute("profesor", examen.getProfesor());
			
			
			return "examen/Detalle_Examen";
		}
		
	}
	
	
	
	@GetMapping("/borraExamen")
	public String borrarExamen(Model model,@RequestParam long id) {
		
		if(!examenes.existsById(id)) {

			model.addAttribute("name", "Autoescuelas Alpine, No existe ese Examen");

			return "examen/Consulta_Examen";
		}else{
			Examen examen=examenes.getById(id);
			model.addAttribute("name", "Autoescuelas Alpine,  Examen borrado correctamente");

			examenes.delete(examen);
			
			return "examen/Consulta_Examen";
		}
	}
	
}