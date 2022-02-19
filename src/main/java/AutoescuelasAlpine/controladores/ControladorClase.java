/**
 * 
 */
package AutoescuelasAlpine.controladores;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import AutoescuelasAlpine.modelo.Clase;
import AutoescuelasAlpine.modelo.ClaseRepository;

/**
 * @author ja.conde
 *
 */
@Controller
public class ControladorClase {
	
	@Autowired
	private ClaseRepository clases;
	
	public static final String HORA_PRIMERA_CLASE="09:00";
	public static final String HORA_ULTIMA_CLASE="19:00";
	public static final String DURACION_CLASE="3600";//DURACION EN SEGUNDOS

	
	@GetMapping("/altaClase")
	public String altaClase(Model model) {
		model.addAttribute("name", "Autoescuelas Alpine, Alta nuevo clase");
		
		model.addAttribute("fechaMinima", ""+Calendar.getInstance().get(Calendar.YEAR)+"-"+
				Calendar.getInstance().get(Calendar.MONTH)+"-"+Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
		model.addAttribute("horaApertura", HORA_PRIMERA_CLASE);
		model.addAttribute("horaCierre", HORA_ULTIMA_CLASE);
		model.addAttribute("duracion", DURACION_CLASE);
		model.addAttribute("carnet", "");
		model.addAttribute("profesor", "");
		
		
		return "clase/Alta_clase";
	}
	
//	@PostMapping("/procesarAltaClase")
//	public String procesarAltaClase(Model model, @RequestParam String nombreCompleto,@RequestParam String dni) {
//		
//		Clase clase=clases.findByDni(dni);
//		if(clase==null) {
//			clases.save(new Clase(nombreCompleto, dni));
//			model.addAttribute("name", "Autoescuelas Alpine, Nuevo clase grabado correctamente");
//			
//			model.addAttribute("nombreCompleto", nombreCompleto);
//			model.addAttribute("dni", dni);
//			
//			
//			
//			return "Detalle_clase";
//		}else{
//			model.addAttribute("name", "Autoescuelas Alpine, Ya existe ese clase");
//			
//			model.addAttribute("nombreCompleto", clase.getNombreCompleto());
//			model.addAttribute("dni", clase.getDni());
//			
//			
//			
//			return "Detalle_clase";
//		}
//		
//	}
//	
//	@GetMapping("/buscaClase")
//	public String buscaClase(Model model) {
//		model.addAttribute("name", "Autoescuelas Alpine, Buscar clase");
//		
//		return "Consulta_clase";
//	}
//
//	@PostMapping("/procesarBuscaClase")
//	public String procesarBuscaClase(Model model, @RequestParam String dni) {
//		
//		Clase clase=clases.findByDni(dni);
//		if(clase==null) {
//			
//			model.addAttribute("name", "Autoescuelas Alpine, No existe ese clase");
//		
//			return "Consulta_clase";
//		}else{
//			model.addAttribute("name", "Autoescuelas Alpine, Clase");
//			
//			model.addAttribute("nombreCompleto", clase.getNombreCompleto());
//			model.addAttribute("dni", clase.getDni());
//			
//			
//			
//			return "Detalle_clase";
//		}
//		
//	}
//	
//	@GetMapping("/ModificaClase")
//	public String modificarClase(Model model,@RequestParam String dni) {
//		Clase clase=clases.findByDni(dni);
//		if(clase==null) {
//
//			model.addAttribute("name", "Autoescuelas Alpine, No existe ese clase");
//
//			return "Consulta_clase";
//		}else{
//			model.addAttribute("name", "Autoescuelas Alpine, Modificar clase");
//
//			model.addAttribute("nombreCompleto", clase.getNombreCompleto());
//			model.addAttribute("dni", clase.getDni());
//
//			return "Modificar_clase";
//		}
//	}
//	
//	@PostMapping("/procesarModificarClase")
//	public String procesarModificarClase(Model model, @RequestParam String nombreCompleto,@RequestParam String dni) {
//		
//		Clase clase=clases.findByDni(dni);
//		if(clase!=null) {
//			clase.setNombreCompleto(nombreCompleto);
//			clases.save(clase);
//			model.addAttribute("name", "Autoescuelas Alpine, Clase grabado correctamente");
//			
//			model.addAttribute("nombreCompleto", nombreCompleto);
//			model.addAttribute("dni", dni);
//			
//			return "Detalle_clase";
//		}else{
//			model.addAttribute("name", "Autoescuelas Alpine, no existe ese clase");
//			
//			
//			return "Consulta_clase";
//		}
//		
//	}
//	
//	@GetMapping("/borraClase")
//	public String borrarClase(Model model,@RequestParam String dni) {
//		Clase clase=clases.findByDni(dni);
//		if(clase==null) {
//
//			model.addAttribute("name", "Autoescuelas Alpine, No existe ese clase");
//
//			return "Consulta_clase";
//		}else{
//			model.addAttribute("name", "Autoescuelas Alpine,  Clase borrado correctamente");
//
//			clases.delete(clase);
//			
//			return "Consulta_clase";
//		}
//	}
	
}
