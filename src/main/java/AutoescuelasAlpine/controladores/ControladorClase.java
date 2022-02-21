/**
 * 
 */
package AutoescuelasAlpine.controladores;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import AutoescuelasAlpine.modelo.Carnet;
import AutoescuelasAlpine.modelo.Clase;
import AutoescuelasAlpine.modelo.ClaseRepository;
import AutoescuelasAlpine.modelo.Profesores;

/**
 * @author ja.conde
 * @author a.penaj
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
	
	@PostMapping("/procesarAltaClase")
	public String procesarAltaClase(Model model, @RequestParam Timestamp fechaHoraComienzo,@RequestParam Carnet carnet,@RequestParam Profesores profesor) {	
		clases.save(new Clase(fechaHoraComienzo, carnet,profesor));
		model.addAttribute("name", "Autoescuelas Alpine, Nuevo clase grabado correctamente");
		
		model.addAttribute("fechaHoraComienzo", fechaHoraComienzo);
		model.addAttribute("carnet", carnet);
		model.addAttribute("profesor", profesor);	
			
		return "clase/Detalle_clase";
	}
	
	@GetMapping("/buscaClase")
	public String buscaClase(Model model) {
		model.addAttribute("name", "Autoescuelas Alpine, Buscar clase");
		
		return "clase/Consulta_clase";
	}

	@PostMapping("/procesarBuscaClase")
	public String procesarBuscaClase(Model model, @RequestParam long id) {
		
		Clase clase=clases.getById(id);
		if(clase==null) {
			
			model.addAttribute("name", "Autoescuelas Alpine, No existe esa clase");
		
			return "clase/Consulta_clase";
		}else{
			model.addAttribute("name", "Autoescuelas Alpine, Alumno");
			
			model.addAttribute("fechaHoraComienzo", clase.getFechaHoraComienzo());
			model.addAttribute("carnet", clase.getCarnet());
			model.addAttribute("profesor", clase.getProfesor());
			
			return "clase/Detalle_clase";
		}
	}
	
	@GetMapping("/ModificaClase")
	public String modificarClase(Model model,@RequestParam long id) {
		Clase clase=clases.getById(id);
		if(clase==null) {

			model.addAttribute("name", "Autoescuelas Alpine, No existe ese clase");

			return "clase/Consulta_clase";
		}else{
			model.addAttribute("name", "Autoescuelas Alpine, Modificar clase");
			model.addAttribute("fechaHoraComienzo", clase.getFechaHoraComienzo());
			model.addAttribute("carnet", clase.getCarnet());
			model.addAttribute("profesor", clase.getProfesor());

			return "clase/Modificar_clase";
		}
	}
	
	@PostMapping("/procesarModificarClase")
	public String procesarModificarClase(Model model, @RequestParam Timestamp fechaHoraComienzo,@RequestParam Carnet carnet,@RequestParam Profesores profesor,@RequestParam long id) {
		
		Clase clase=clases.getById(id);
		if(clase!=null) {
			clase.setFechaHoraComienzo(fechaHoraComienzo);
			clase.setCarnet(carnet);
			clase.setProfesor(profesor);
			clases.save(clase);
			model.addAttribute("name", "Autoescuelas Alpine, Clase grabada correctamente");
			
			model.addAttribute("fechaHoraComienzo", clase.getFechaHoraComienzo());
			model.addAttribute("carnet", clase.getCarnet());
			model.addAttribute("profesor", clase.getProfesor());
			
			return "clase/Detalle_clase";
		}else{
			model.addAttribute("name", "Autoescuelas Alpine, no existe ese clase");
			
			
			return "clase/Consulta_clase";
		}
		
	}
	
	@GetMapping("/borraClase")
	public String borrarClase(Model model,@RequestParam long id) {
		Clase clase=clases.getById(id);
		if(clase==null) {
			model.addAttribute("name", "Autoescuelas Alpine, No existe esa clase");

			return "clase/Consulta_clase";
		}else{
			model.addAttribute("name", "Autoescuelas Alpine,  Clase borrada correctamente");

			clases.delete(clase);
			
			return "clase/Consulta_clase";
		}
	}
	
}
