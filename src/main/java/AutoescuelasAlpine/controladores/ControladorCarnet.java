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

import AutoescuelasAlpine.modelo.Carnet;
import AutoescuelasAlpine.modelo.CarnetRepository;

/**
 * @author ja.conde
 *
 */
@Controller
public class ControladorCarnet {
	
	@Autowired
	private CarnetRepository carnets;

	
	@GetMapping("/altaCarnet")
	public String altaCarnet(Model model) {
		model.addAttribute("name", "Autoescuelas Alpine, Alta nuevo Carnet");
		
		model.addAttribute("tipo", "");
		
		return "Alta_Carnet";
	}
	
	@PostMapping("/procesarAltaCarnet")
	public String procesarAltaCarnet(Model model, @RequestParam String tipo) {
		
		if(!carnets.existsById(tipo)) {
			carnets.save(new Carnet(tipo));
			model.addAttribute("name", "Autoescuelas Alpine, Nuevo Carnet grabado correctamente");
			
			model.addAttribute("tipo", tipo);
			
			
			
			return "Detalle_Carnet";
		}else{
			model.addAttribute("name", "Autoescuelas Alpine, Ya existe ese Carnet");
			
			model.addAttribute("tipo", tipo);
			
			return "Detalle_Carnet";
		}
		
	}
	
	@GetMapping("/buscaCarnet")
	public String buscaCarnet(Model model) {
		model.addAttribute("name", "Autoescuelas Alpine, Buscar Carnet");
		
		return "Consulta_Carnet";
	}

	@PostMapping("/procesarBuscaCarnet")
	public String procesarBuscaCarnet(Model model, @RequestParam String tipo) {
		
		
		if(!carnets.existsById(tipo)) {
			
			model.addAttribute("name", "Autoescuelas Alpine, No existe ese Carnet");
		
			return "Consulta_Carnet";
		}else{
			Carnet carnet=carnets.getById(tipo);
			model.addAttribute("name", "Autoescuelas Alpine, Carnet");
			
			model.addAttribute("tipo", carnet.getTipo());
			
			
			return "Detalle_Carnet";
		}
		
	}
	
	
	
	@GetMapping("/borraCarnet")
	public String borrarCarnet(Model model,@RequestParam String tipo) {
		
		if(!carnets.existsById(tipo)) {

			model.addAttribute("name", "Autoescuelas Alpine, No existe ese Carnet");

			return "Consulta_Carnet";
		}else{
			Carnet carnet=carnets.getById(tipo);
			model.addAttribute("name", "Autoescuelas Alpine,  Carnet borrado correctamente");

			carnets.delete(carnet);
			
			return "Consulta_Carnet";
		}
	}
	
}
