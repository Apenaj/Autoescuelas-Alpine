/**
 * 
 */
package AutoescuelasAlpine.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author ja.conde
 *
 */
@Controller
public class Controlador {
	
	@GetMapping("/bienvenida")
	public String bienvenida(Model model) {
		model.addAttribute("name", "Autoescuelas Alpine");
		
		return "Pagina_bienvenida";
	}
	
	

}
