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

import AutoescuelasAlpine.modelo.Profesores;
import AutoescuelasAlpine.modelo.ProfesoresRepository;
import AutoescuelasAlpine.modelo.Vehiculo;
import AutoescuelasAlpine.modelo.VehiculoRepository;

/**
 * @author ja.conde
 * @author a.penaj
 */
@Controller
public class ControladorVehiculo {
	
	@Autowired
	private VehiculoRepository vehiculos;
	@Autowired
	private ProfesoresRepository profesores;
	
	@GetMapping("/altaVehiculo")
	public String altaVehiculo(Model model) {
		model.addAttribute("name", "Autoescuelas Alpine, Alta nuevo vehiculo");
		
		//model.addAttribute("matricula", "");
		//model.addAttribute("modelo", "");
		
		return "vehiculo/Alta_vehiculo";
	}
	
	@PostMapping("/procesarAltaVehiculo")
	public String procesarAltaVehiculo(Model model, @RequestParam String matricula,@RequestParam String modelo,@RequestParam String dni) {
		
		Vehiculo vehiculo=vehiculos.findBymatricula(matricula);
		if(vehiculo==null) {
			
			
			Profesores profesor=profesores.findByDni(dni);

			if(profesor!=null) {

				vehiculos.save(new Vehiculo(matricula, modelo, profesor));
				model.addAttribute("name", "Autoescuelas Alpine, Nuevo vehiculo grabado correctamente");

				model.addAttribute("modelo", modelo);
				model.addAttribute("matricula", matricula);
				model.addAttribute("dni", dni); //1º parametro tiene que estar igual entre corchetes en el detalle_vehiculo que referenica a dni




				return "vehiculo/Detalle_vehiculo";
			}else {
				model.addAttribute("name", "Autoescuelas Alpine, no existe ese profesor");
				
				return "vehiculo/Alta_vehiculo";
			}
		}else{
			model.addAttribute("name", "Autoescuelas Alpine, Ya existe ese vehiculo");
			
			return "vehiculo/Alta_vehiculo";
		}
		
	}
	
	@GetMapping("/buscaVehiculo")
	public String buscaVehiculo(Model model) {
		model.addAttribute("name", "Autoescuelas Alpine, Buscar vehiculo");
		
		return "vehiculo/Consulta_vehiculo";
	}

	@PostMapping("/procesarBuscaVehiculo")
	public String procesarBuscaVehiculo(Model model, @RequestParam String matricula) {
		
		
		if(!vehiculos.existsById(matricula)) {
			
			model.addAttribute("name", "Autoescuelas Alpine, No existe ese vehiculo");
		
			return "vehiculo/Consulta_vehiculo";
		}else{
			
			Vehiculo vehiculo=vehiculos.getById(matricula);
			model.addAttribute("name", "Autoescuelas Alpine, Vehiculo");
			
			model.addAttribute("modelo", vehiculo.getModelo());
			model.addAttribute("matricula", vehiculo.getMatricula());
			model.addAttribute("dni", vehiculo.getProfesor().getDni());
			
			
			
			return "vehiculo/Detalle_vehiculo";
		}
		
	}
	
	@GetMapping("/ModificaVehiculo")
	public String modificarVehiculo(Model model,@RequestParam String matricula) {
		Vehiculo vehiculo=vehiculos.getById(matricula);
		if(vehiculo==null) {

			model.addAttribute("name", "Autoescuelas Alpine, No existe ese vehiculo");

			return "vehiculo/Consulta_vehiculo";
		}else{
			model.addAttribute("name", "Autoescuelas Alpine, Modificar vehiculo");

			model.addAttribute("modelo", vehiculo.getModelo());
			model.addAttribute("matricula", vehiculo.getMatricula());
			model.addAttribute("dni", vehiculo.getProfesor().getDni());

			return "vehiculo/Modificar_vehiculo";
		}
	}
	
	@PostMapping("/procesarModificarVehiculo")
	public String procesarModificarVehiculo(Model model, @RequestParam String modelo,@RequestParam String matricula,@RequestParam String dni) {
		
		Vehiculo vehiculo=vehiculos.getById(matricula);
		if(vehiculo!=null) {
			
			Profesores profesor=profesores.findByDni(dni);
			if(profesor!=null) {
				vehiculo = vehiculos.save(new Vehiculo(matricula, modelo, profesor));

				model.addAttribute("name", "Autoescuelas Alpine, Vehiculo grabado correctamente");

				model.addAttribute("modelo", vehiculo.getModelo());
				model.addAttribute("matricula", vehiculo.getMatricula());
				model.addAttribute("dni", vehiculo.getProfesor().getDni());

				return "vehiculo/Detalle_vehiculo";
			}else {
				model.addAttribute("name", "Autoescuelas Alpine, Vehiculo, no existe ese profesor");
				
				model.addAttribute("modelo", vehiculo.getModelo());
				model.addAttribute("matricula", vehiculo.getMatricula());
				model.addAttribute("dni", vehiculo.getProfesor().getDni());
				
				
				
				return "vehiculo/Detalle_vehiculo";
			}
		}else{
			model.addAttribute("name", "Autoescuelas Alpine, no existe ese vehiculo");
			
			
			return "vehiculo/Consulta_vehiculo";
		}
		
	}
	
	@GetMapping("/borraVehiculo")
	public String borrarVehiculo(Model model,@RequestParam String matricula) {
		Vehiculo vehiculo=vehiculos.getById(matricula);
		if(vehiculo==null) {

			model.addAttribute("name", "Autoescuelas Alpine, No existe ese vehiculo");

			return "vehiculo/Consulta_vehiculo";
		}else{
			model.addAttribute("name", "Autoescuelas Alpine,  Vehiculo borrado correctamente");

			vehiculos.delete(vehiculo);
			
			return "vehiculo/Consulta_vehiculo";
		}
	}
	
}