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

import AutoescuelasAlpine.modelo.Examen;
import AutoescuelasAlpine.modelo.ExamenRepository;

/**
 * @author ja.conde
 * @author a.penaj
 */
@Controller
public class ControladorExamen {
	
	@Autowired
	private ExamenRepository examenes;

	
	@GetMapping("/altaExamen")
	public String altaExamen(Model model) {
		model.addAttribute("name", "Autoescuelas Alpine, Alta nuevo Examen");
		
		model.addAttribute("tipo", "");
		
		return "examen/Alta_examen";
	}
	
	/*@PostMapping("/procesarAltaExamen")
	public String procesarAltaExamen(Model model, @RequestParam Carnet carnet, @RequestParam Profesores profesor, @RequestParam List<Alumno> alumnos) {
		
		
		if(examen.getClass()==null) {
			examen.save(new Examen(carnet, profesor,alumnos));
			model.addAttribute("name", "Autoescuelas Alpine, Nuevo examen grabado correctamente");
			
			model.addAttribute("Carnet", carnet);
			model.addAttribute("Profesor", profesor);
			model.addAttribute("Alumnos", alumnos);
			
			return "Detalle_examen";
		}else{
			model.addAttribute("name", "Autoescuelas Alpine, Ya existe ese examen");
			
			model.addAttribute("ID", examen.getIdExamen());
			model.addAttribute("Carnet", examen.getCarnet());
			model.addAttribute("Profesor", examen.getProfesor());
			model.addAttribute("Alumnos", examen.getAlumnos());			
			
			return "Detalle_examen";
		}
		
	}
	
	@GetMapping("/buscaExamen")
	public String buscaCarnet(Model model) {
		model.addAttribute("name", "Autoescuelas Alpine, Buscar Examen");
		
		return "Consulta_examen";
	}

	@PostMapping("/procesarBuscaExamen")
	public String procesarBuscaCarnet(Model model, @RequestParam long id) {
		
		
		if(!examen.existsById(id)) {
			
			model.addAttribute("name", "Autoescuelas Alpine, No existe ese Examen");
		
			return "Consulta_examen";
		}else{
			Examen examen=examen.getById(id);
			model.addAttribute("name", "Autoescuelas Alpine, Examen");
			
			model.addAttribute("ID", examen.getID());
			
			
			return "Detalle_examen";
		}
		
	}
	
	
	
	@GetMapping("/borraExamen")
	public String borrarCarnet(Model model,@RequestParam long id) {
		
		if(!examen.existsById(id)) {

			model.addAttribute("name", "Autoescuelas Alpine, No existe ese Examen");

			return "Consulta_examen";
		}else{
			Examen examen=examen.getById(id);
			model.addAttribute("name", "Autoescuelas Alpine,  Examen borrado correctamente");

			examen.delete(carnet);
			
			return "Consulta_examen";
		}
	}
	*/
}