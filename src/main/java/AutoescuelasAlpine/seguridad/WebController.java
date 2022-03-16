package AutoescuelasAlpine.seguridad;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WebController {
	@GetMapping("/")
	public String index() {
		return "login/index";

	}
	@GetMapping("/login")
	public String login() {
		return "login/login";

	}


	@GetMapping("/loginerror")
	public String loginerror() {
		return "login/loginerror";
	}
	//@GetMapping("/home")
	//public String home() {
	//return "login/home";
	// return "pagina_bienvenida";
	//}
	
	
	
	
}
