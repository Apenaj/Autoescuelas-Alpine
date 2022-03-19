/**
 * 
 */
package AutoescuelasAlpine.controladores;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import AutoescuelasAlpine.modelo.Carnet;
import AutoescuelasAlpine.modelo.CarnetRepository;
import AutoescuelasAlpine.modelo.User;

/**
 * @author ja.conde
 *
 */
@Controller
public class ControladorCarnet {
	
	@Autowired
	private CarnetRepository carnets;

	@ModelAttribute
	
	public void addAttributes(Model model, HttpServletRequest request) {

		Principal principal = request.getUserPrincipal();

		if(principal != null) {
		
			//model.addAttribute("logged", true);		
			model.addAttribute("username", principal.getName());
			model.addAttribute("admin", request.isUserInRole(User.ROL_ADMIN));
			
		} else {
			model.addAttribute("logged", false);
			;
		}
	}
	@GetMapping("/altaCarnet")
	public String altaCarnet(Model model) {
		model.addAttribute("name", "Autoescuelas Alpine, Alta nuevo Carnet");
		
		model.addAttribute("tipo", "");
		
		return "carnet/Alta_Carnet";
	}
	
	@PostMapping("/procesarAltaCarnet")
	public String procesarAltaCarnet(Model model, @RequestParam String tipo) {
		
		if(!carnets.existsById(tipo)) {
			carnets.save(new Carnet(tipo));
			model.addAttribute("name", "Autoescuelas Alpine, Nuevo Carnet grabado correctamente");
			
			model.addAttribute("tipo", tipo);
			
			
			
			return "carnet/Detalle_Carnet";
		}else{
			model.addAttribute("name", "Autoescuelas Alpine, Ya existe ese Carnet");
			
			model.addAttribute("tipo", tipo);
			
			return "carnet/Detalle_Carnet";
		}
		
	}
	
	@GetMapping("/buscaCarnet")
	public String buscaCarnet(Model model) {
		model.addAttribute("name", "Autoescuelas Alpine, Buscar Carnet");
		
		return "carnet/Consulta_Carnet";
	}

	@PostMapping("/procesarBuscaCarnet")
	public String procesarBuscaCarnet(Model model, @RequestParam String tipo) {
		
		
		if(!carnets.existsById(tipo)) {
			
			model.addAttribute("name", "Autoescuelas Alpine, No existe ese Carnet");
		
			return "carnet/Consulta_Carnet";
		}else{
			Carnet carnet=carnets.getById(tipo);
			model.addAttribute("name", "Autoescuelas Alpine, Carnet");
			
			model.addAttribute("tipo", carnet.getTipo());
			
			
			return "carnet/Detalle_Carnet";
		}
		
	}
	
	
	
	@GetMapping("/borraCarnet")
	public String borrarCarnet(Model model,@RequestParam String tipo) {
		
		if(!carnets.existsById(tipo)) {

			model.addAttribute("name", "Autoescuelas Alpine, No existe ese Carnet");

			return "carnet/Consulta_Carnet";
		}else{
			Carnet carnet=carnets.getById(tipo);
			model.addAttribute("name", "Autoescuelas Alpine,  Carnet borrado correctamente");

			carnets.delete(carnet);
			
			return "carnet/Consulta_Carnet";
		}
	}
	
}
