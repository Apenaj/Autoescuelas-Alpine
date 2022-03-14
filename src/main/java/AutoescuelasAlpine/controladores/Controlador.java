/**
 * 
 */
package AutoescuelasAlpine.controladores;

import javax.servlet.http.HttpServletRequest;

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
	public String bienvenida(Model model, HttpServletRequest request) {
		model.addAttribute("name", "Autoescuelas Alpine");
		model.addAttribute("username", request.getUserPrincipal().getName());
		model.addAttribute("alumno", request.isUserInRole("alumno"));
		return "Pagina_bienvenida";
	}
	
	

}
