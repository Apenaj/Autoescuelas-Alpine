/**
 * 
 */
package AutoescuelasAlpine.controladores;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import AutoescuelasAlpine.modelo.Carnet;
import AutoescuelasAlpine.modelo.CarnetRepository;
import AutoescuelasAlpine.modelo.Clase;
import AutoescuelasAlpine.modelo.ClaseRepository;
import AutoescuelasAlpine.modelo.Profesores;
import AutoescuelasAlpine.modelo.ProfesoresRepository;

/**
 * @author ja.conde
 * @author a.penaj
 */
@Controller
public class ControladorClase {
	
	@Autowired
	private ClaseRepository clases;
	@Autowired
	private CarnetRepository carnets;
	@Autowired
	private ProfesoresRepository profesores;
	
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
	public String procesarAltaClase(Model model, @RequestParam String fecha,@RequestParam String hora,@RequestParam String carnet,@RequestParam String profesor) {	
		try {			
			Date fechaD=DateFormat.getDateTimeInstance(DateFormat.SHORT,DateFormat.SHORT).parse(fecha.replaceAll("-", "/").substring(2)+" "+hora);
			if(carnets.existsById(carnet)) {
				Profesores profe=profesores.findByDni(profesor);
				if(profe!=null) {
					Carnet carnetC=carnets.getById(carnet);
					Clase clase=clases.save(new Clase(new Timestamp(fechaD.getTime()), carnetC,profe));
					model.addAttribute("name", "Autoescuelas Alpine, Nuevo clase grabado correctamente");

					model.addAttribute("fechaHoraComiezo", DateFormat.getDateTimeInstance(DateFormat.SHORT,DateFormat.SHORT).format(fechaD));
					model.addAttribute("duracion", DURACION_CLASE);
					model.addAttribute("carnet", clase.getCarnet().getTipo());
					model.addAttribute("dniProfesor", profe.getDni());
					model.addAttribute("nombreProfesor", profe.getNombreCompleto());
					model.addAttribute("idClase", clase.getIdClase());

					return "clase/Detalle_clase";
				}else {
					model.addAttribute("name", "Autoescuelas Alpine, no existe el profesor");

					model.addAttribute("fechaMinima", ""+Calendar.getInstance().get(Calendar.YEAR)+"-"+
							Calendar.getInstance().get(Calendar.MONTH)+"-"+Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
					model.addAttribute("horaApertura", HORA_PRIMERA_CLASE);
					model.addAttribute("horaCierre", HORA_ULTIMA_CLASE);
					model.addAttribute("duracion", DURACION_CLASE);
					model.addAttribute("carnet", "");
					model.addAttribute("profesor", "");


					return "clase/Alta_clase";
				}
			}else {
				model.addAttribute("name", "Autoescuelas Alpine, no existe el carnet");

				model.addAttribute("fechaMinima", ""+Calendar.getInstance().get(Calendar.YEAR)+"-"+
						Calendar.getInstance().get(Calendar.MONTH)+"-"+Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
				model.addAttribute("horaApertura", HORA_PRIMERA_CLASE);
				model.addAttribute("horaCierre", HORA_ULTIMA_CLASE);
				model.addAttribute("duracion", DURACION_CLASE);
				model.addAttribute("carnet", "");
				model.addAttribute("profesor", "");


				return "clase/Alta_clase";
			}
		}catch (ParseException e) {
			model.addAttribute("name", "Autoescuelas Alpine, fecha erronea:"+fecha+" "+hora+"//"+
						e.getMessage()+"//"+DateFormat.getDateTimeInstance(DateFormat.SHORT,DateFormat.SHORT).format(new Date()));

			model.addAttribute("fechaMinima", ""+Calendar.getInstance().get(Calendar.YEAR)+"-"+
					Calendar.getInstance().get(Calendar.MONTH)+"-"+Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
			model.addAttribute("horaApertura", HORA_PRIMERA_CLASE);
			model.addAttribute("horaCierre", HORA_ULTIMA_CLASE);
			model.addAttribute("duracion", DURACION_CLASE);
			model.addAttribute("carnet", "");
			model.addAttribute("profesor", "");


			return "clase/Alta_clase";
		}
	}
	
	@GetMapping("/buscaClase")
	public String buscaClase(Model model) {
		model.addAttribute("name", "Autoescuelas Alpine, Buscar clase");
		
		return "clase/Consulta_clase";
	}

	
	
	
	@PostMapping("/procesarBuscaClaseCarnet")
	public String procesarBuscaClaseCarnet(Model model, @RequestParam String carnet) {
		
		if(carnets.existsById(carnet)) {

			
			List<Clase> listaClases=clases.findBycarnet(carnets.getById(carnet));
			if(listaClases==null || listaClases.isEmpty()) {

				model.addAttribute("name", "Autoescuelas Alpine, No existen clases para ese carnet");

				return "clase/Consulta_clase";
			}else{
				model.addAttribute("name", "Autoescuelas Alpine, Alumno");

				model.addAttribute("clases", clases);
				

				return "clase/Lista_clase";
			}
		}else {
			model.addAttribute("name", "Autoescuelas Alpine, No existe ese carnet");

			return "clase/Consulta_clase";
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
