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

import AutoescuelasAlpine.modelo.Alumno;
import AutoescuelasAlpine.modelo.AlumnoRepository;

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
