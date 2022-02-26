/**
 * 
 */
package AutoescuelasAlpine.controladores;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import AutoescuelasAlpine.modelo.Alumno;
import AutoescuelasAlpine.modelo.AlumnoRepository;
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
	@Autowired
	private AlumnoRepository alumnos;
	
	public static final String HORA_PRIMERA_CLASE="09:00";
	public static final String HORA_ULTIMA_CLASE="19:00";
	public static final String DURACION_CLASE="3600";//DURACION EN SEGUNDOS

	
	@GetMapping("/altaClase")
	public String altaClase(Model model) {
		model.addAttribute("name", "Autoescuelas Alpine, Alta nuevo clase");
		SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
		model.addAttribute("fechaMinima", formatoFecha.format(new Date()));
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
			SimpleDateFormat formatoFechaHora = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			Date fechaD=formatoFechaHora.parse(fecha+" "+hora);
			if(carnets.existsById(carnet)) {
				Profesores profe=profesores.findByDni(profesor);
				if(profe!=null) {
					Carnet carnetC=carnets.getById(carnet);
					Clase clase=clases.save(new Clase(new Timestamp(fechaD.getTime()), carnetC,profe));
					model.addAttribute("name", "Autoescuelas Alpine, Nuevo clase grabado correctamente");
					SimpleDateFormat formatoFechaHoraSalida = new SimpleDateFormat("dd-MM-yyyy HH:mm");
					model.addAttribute("fechaHoraComiezo", formatoFechaHoraSalida.format(new Date(clase.getFechaHoraComienzo().getTime())));
					model.addAttribute("duracion", ""+(Integer.valueOf(DURACION_CLASE)/60));
					model.addAttribute("carnet", clase.getCarnet().getTipo());
					model.addAttribute("dniProfesor", profe.getDni());
					model.addAttribute("nombreProfesor", profe.getNombreCompleto());
					model.addAttribute("idClase", clase.getIdClase());
					
					model.addAttribute("siAlumnos", false);
					model.addAttribute("alumnos", new ArrayList<Alumno>());

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
						e.getMessage());
			SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
			model.addAttribute("fechaMinima", formatoFecha.format(new Date()));
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

				model.addAttribute("clases", listaClases);
				

				return "clase/Lista_clase";
			}
		}else {
			model.addAttribute("name", "Autoescuelas Alpine, No existe ese carnet");

			return "clase/Consulta_clase";
		}
	}
	
	
	@PostMapping("/procesarBuscaClaseProfesor")
	public String procesarBuscaClaseProfesor(Model model, @RequestParam String profesor) {
		
		Profesores profe=profesores.findByDni(profesor);
		if(profe!=null) {

			
			List<Clase> listaClases=clases.findByprofesor(profe);
			if(listaClases==null || listaClases.isEmpty()) {

				model.addAttribute("name", "Autoescuelas Alpine, No existen clases para ese carnet");

				return "clase/Consulta_clase";
			}else{
				model.addAttribute("name", "Autoescuelas Alpine, Alumno");

				model.addAttribute("clases", listaClases);
				

				return "clase/Lista_clase";
			}
		}else {
			model.addAttribute("name", "Autoescuelas Alpine, No existe ese profesor");

			return "clase/Consulta_clase";
		}
	}
	
	
	
	
	@PostMapping("/procesarBuscaClaseFecha")
	public String procesarBuscaClaseFecha(Model model,@RequestParam String fecha,@RequestParam String hora) {
		try {
			SimpleDateFormat formatoFechaHora = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			Date fechaD=formatoFechaHora.parse(fecha+" "+hora);

			List<Clase> listaClases=clases.findByfechaHoraComienzo(new Timestamp(fechaD.getTime()));
			if(listaClases==null || listaClases.isEmpty()) {

				model.addAttribute("name", "Autoescuelas Alpine, No existen clases para esa fecha");

				return "clase/Consulta_clase";
			}else{
				model.addAttribute("name", "Autoescuelas Alpine, lista de clases");

				model.addAttribute("clases", listaClases);


				return "clase/Lista_clase";
			}

		}catch (ParseException e) {

			model.addAttribute("name", "Autoescuelas Alpine, "
					+ "fecha erronea");

			return "clase/Consulta_clase";
		}
	}
	
	@GetMapping("/DetalleClase")
	public String detalleClase(Model model,@RequestParam long idClase) {

		if(clases.existsById(idClase)) {

			Clase clase=clases.getById(idClase);
			model.addAttribute("name", "Autoescuelas Alpine, Detalle de la clase");
			SimpleDateFormat formatoFechaHoraSalida = new SimpleDateFormat("dd-MM-yyyy HH:mm");
			
			model.addAttribute("fechaHoraComiezo", formatoFechaHoraSalida.format(new Date(clase.getFechaHoraComienzo().getTime())));
			model.addAttribute("duracion", ""+(Integer.valueOf(DURACION_CLASE)/60));
			model.addAttribute("carnet", clase.getCarnet().getTipo());
			model.addAttribute("dniProfesor", clase.getProfesor().getDni());
			model.addAttribute("nombreProfesor", clase.getProfesor().getNombreCompleto());
			model.addAttribute("idClase", clase.getIdClase());
			
			model.addAttribute("siAlumnos", !clase.getAlumnosApuntados().isEmpty());
			model.addAttribute("alumnos", clase.getAlumnosApuntados());

			return "clase/Detalle_clase";
		}else {
			model.addAttribute("name", "Autoescuelas Alpine, "
					+ "No existe la clase");

			return "clase/Consulta_clase";
		}
	}
	
	
	
	
	@GetMapping("/ModificaClase")
	public String modificarClase(Model model,@RequestParam long idClase) {
		Clase clase=clases.getById(idClase);
		if(clase==null) {

			model.addAttribute("name", "Autoescuelas Alpine, No existe ese clase");

			return "clase/Consulta_clase";
		}else{
			model.addAttribute("name", "Autoescuelas Alpine, Modificar clase");
			SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
			model.addAttribute("fechaMinima", formatoFecha.format(new Date()));
			model.addAttribute("horaApertura", HORA_PRIMERA_CLASE);
			model.addAttribute("horaCierre", HORA_ULTIMA_CLASE);
			model.addAttribute("duracion", DURACION_CLASE);
			model.addAttribute("idClase", clase.getIdClase());
			model.addAttribute("carnet", clase.getCarnet().getTipo());
			model.addAttribute("profesor", clase.getProfesor().getDni());
			Date fechaHora=new Date(clase.getFechaHoraComienzo().getTime());
			SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm");
			model.addAttribute("fechaComienzo",  formatoFecha.format(fechaHora));
			model.addAttribute("horaComienzo", formatoHora.format(fechaHora));

			return "clase/Modificar_clase";
		}
	}

	@PostMapping("/procesarModificarClase")
	public String procesarModificarClase(Model model,@RequestParam String fecha,@RequestParam String hora,@RequestParam String carnet,@RequestParam String profesor,@RequestParam long idClase ) {

		if(clases.existsById(idClase)) {
			Clase clase=clases.getById(idClase);
			try {
				SimpleDateFormat formatoFechaHora = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				Date fechaD=formatoFechaHora.parse(fecha+" "+hora);
				if(carnets.existsById(carnet)) {
					Profesores profe=profesores.findByDni(profesor);
					if(profe!=null) {
						Carnet carnetC=carnets.getById(carnet);
						clase.setCarnet(carnetC);
						clase.setFechaHoraComienzo(new Timestamp(fechaD.getTime()));
						clase.setProfesor(profe);
						clases.save(clase);
						model.addAttribute("name", "Autoescuelas Alpine, clase modificada correctamente");
						SimpleDateFormat formatoFechaHoraSalida = new SimpleDateFormat("dd-MM-yyyy HH:mm");
						model.addAttribute("fechaHoraComiezo", formatoFechaHoraSalida.format(new Date(clase.getFechaHoraComienzo().getTime())));
						model.addAttribute("duracion", ""+(Integer.valueOf(DURACION_CLASE)/60));
						model.addAttribute("carnet", clase.getCarnet().getTipo());
						model.addAttribute("dniProfesor", profe.getDni());
						model.addAttribute("nombreProfesor", profe.getNombreCompleto());
						model.addAttribute("idClase", clase.getIdClase());
						
						model.addAttribute("siAlumnos", !clase.getAlumnosApuntados().isEmpty());
						model.addAttribute("alumnos", clase.getAlumnosApuntados());

						return "clase/Detalle_clase";
					}else {
						model.addAttribute("name", "Autoescuelas Alpine, No existe el profesor");
						SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
						model.addAttribute("fechaMinima", formatoFecha.format(new Date()));
						model.addAttribute("horaApertura", HORA_PRIMERA_CLASE);
						model.addAttribute("horaCierre", HORA_ULTIMA_CLASE);
						model.addAttribute("duracion", DURACION_CLASE);
						model.addAttribute("idClase", clase.getIdClase());
						model.addAttribute("carnet", clase.getCarnet().getTipo());
						model.addAttribute("profesor", clase.getProfesor().getDni());
						Date fechaHora=new Date(clase.getFechaHoraComienzo().getTime());
						SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm");
						model.addAttribute("fechaComienzo",  formatoFecha.format(fechaHora));
						model.addAttribute("horaComienzo", formatoHora.format(fechaHora));
						
						return "clase/Modificar_clase";
					}
				}else {
					model.addAttribute("name", "Autoescuelas Alpine, no existe el carnet");

					SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
					model.addAttribute("fechaMinima", formatoFecha.format(new Date()));
					model.addAttribute("horaApertura", HORA_PRIMERA_CLASE);
					model.addAttribute("horaCierre", HORA_ULTIMA_CLASE);
					model.addAttribute("duracion", DURACION_CLASE);
					model.addAttribute("idClase", clase.getIdClase());
					model.addAttribute("carnet", clase.getCarnet().getTipo());
					model.addAttribute("profesor", clase.getProfesor().getDni());
					Date fechaHora=new Date(clase.getFechaHoraComienzo().getTime());
					SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm");
					model.addAttribute("fechaComienzo",  formatoFecha.format(fechaHora));
					model.addAttribute("horaComienzo", formatoHora.format(fechaHora));
					
					return "clase/Modificar_clase";
				}
			}catch (ParseException e) {
				model.addAttribute("name", "Autoescuelas Alpine, fecha erronea:"+fecha+" "+hora+"//"+
						e.getMessage());
				SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
				model.addAttribute("fechaMinima", formatoFecha.format(new Date()));
				model.addAttribute("horaApertura", HORA_PRIMERA_CLASE);
				model.addAttribute("horaCierre", HORA_ULTIMA_CLASE);
				model.addAttribute("duracion", DURACION_CLASE);
				model.addAttribute("idClase", clase.getIdClase());
				model.addAttribute("carnet", clase.getCarnet().getTipo());
				model.addAttribute("profesor", clase.getProfesor().getDni());
				Date fechaHora=new Date(clase.getFechaHoraComienzo().getTime());
				SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm");
				model.addAttribute("fechaComienzo",  formatoFecha.format(fechaHora));
				model.addAttribute("horaComienzo", formatoHora.format(fechaHora));
				
				return "clase/Modificar_clase";
			}
		} else {
			model.addAttribute("name", "Autoescuelas Alpine, "
					+ "No existe la clase");

			return "clase/Consulta_clase";
		}
	}
	
	@GetMapping("/borraClase")
	public String borrarClase(Model model,@RequestParam long idClase) {
		Clase clase=clases.getById(idClase);
		if(clase==null) {
			model.addAttribute("name", "Autoescuelas Alpine, No existe esa clase");

			return "clase/Consulta_clase";
		}else{
			model.addAttribute("name", "Autoescuelas Alpine,  Clase borrada correctamente");

			clases.delete(clase);
			
			return "clase/Consulta_clase";
		}
	}
	
	@GetMapping("/aniadeAlumno")
	public String aniadeAlumno(Model model,@RequestParam long idClase) {
		Clase clase=clases.getById(idClase);
		if(clase==null) {

			model.addAttribute("name", "Autoescuelas Alpine, No existe ese clase");

			return "clase/Consulta_clase";
		}else{
			model.addAttribute("name", "Autoescuelas Alpine, Añadir Alumno");

			model.addAttribute("idClase", clase.getIdClase());
			model.addAttribute("dni", "");
			

			return "clase/AniadeAlumno";
		}
	}
	
	@PostMapping("/procesarAniadeAlumno")
	public String procesarAniadeAlumno(Model model,@RequestParam String dni,@RequestParam long idClase ) {
		if(clases.existsById(idClase)) {
			Clase clase=clases.getById(idClase);
			
			Alumno alumno=alumnos.findByDni(dni);
			if(alumno==null) {
				model.addAttribute("name", "Autoescuelas Alpine, Añadir Alumno");

				model.addAttribute("idClase", clase.getIdClase());
				model.addAttribute("dni", "");
				

				return "clase/AniadeAlumno";
			}else {
				clase.getAlumnosApuntados().add(alumno);
				alumno.getClasesReservadas().add(clase);
				
				clases.save(clase);
				alumnos.save(alumno);
				
				model.addAttribute("name", "Autoescuelas Alpine, clase modificada correctamente");
				SimpleDateFormat formatoFechaHoraSalida = new SimpleDateFormat("dd-MM-yyyy HH:mm");
				model.addAttribute("fechaHoraComiezo", formatoFechaHoraSalida.format(new Date(clase.getFechaHoraComienzo().getTime())));
				model.addAttribute("duracion", ""+(Integer.valueOf(DURACION_CLASE)/60));
				model.addAttribute("carnet", clase.getCarnet().getTipo());
				model.addAttribute("dniProfesor", clase.getProfesor().getDni());
				model.addAttribute("nombreProfesor", clase.getProfesor().getNombreCompleto());
				model.addAttribute("idClase", clase.getIdClase());
				
				model.addAttribute("siAlumnos", !clase.getAlumnosApuntados().isEmpty());
				model.addAttribute("alumnos", clase.getAlumnosApuntados());

				return "clase/Detalle_clase";
			}
			
		}else {
			model.addAttribute("name", "Autoescuelas Alpine, No existe ese clase");

			return "clase/Consulta_clase";
		}
	}

	
}
