/**
 * 
 */
package AutoescuelasAlpine.controladores;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import AutoescuelasAlpine.modelo.User;
import AutoescuelasAlpine.servicioInterno.NotificacionService;

/**
 * @author ja.conde
 *
 */
@Controller
public class Controlador {
	
	@Autowired
	private NotificacionService notificacionService;
	
	@GetMapping("/bienvenida")
	public String bienvenida(Model model, HttpServletRequest request) {
		model.addAttribute("name", "Autoescuelas Alpine");
		model.addAttribute("username", request.getUserPrincipal().getName());
		model.addAttribute("rol", getRol(request));
		model.addAttribute("alumno", request.isUserInRole(User.ROL_ALUMNO));
		model.addAttribute("admin", request.isUserInRole(User.ROL_ADMIN));
		return "Pagina_bienvenida";
	}
	
	
	@PostMapping("/pruebaSI")
	public String pruebaServInter(Model model, HttpServletRequest request) {
		notificacionService.enviarNotificacion("alpinedad2022@gmail.com" , "Esto es una prueba");
		model.addAttribute("name", "Autoescuelas Alpine");
		model.addAttribute("username", request.getUserPrincipal().getName());
		model.addAttribute("rol", getRol(request));
		model.addAttribute("alumno", request.isUserInRole(User.ROL_ALUMNO));
		model.addAttribute("admin", request.isUserInRole(User.ROL_ADMIN));
		return "Pagina_bienvenida";
	}
	
	public String getRol(HttpServletRequest request) {
		if(request.isUserInRole(User.ROL_ALUMNO)) {
			return User.ROL_ALUMNO;
		}else if(request.isUserInRole(User.ROL_PROFESOR)) {
			return User.ROL_PROFESOR;
		}else if(request.isUserInRole(User.ROL_ADMIN)) {
			return User.ROL_ADMIN;
		}else {
			return "No tienes rol";
		}
	}

}
