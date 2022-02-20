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

	
	@GetMapping("/altaVehiculo")
	public String altaVehiculo(Model model) {
		model.addAttribute("name", "Autoescuelas Alpine, Alta nuevo vehiculo");
		
		model.addAttribute("matricula", "");
		model.addAttribute("modelo", "");
		
		return "Alta_vehiculo";
	}
	
	@PostMapping("/procesarAltaVehiculo")
	public String procesarAltaVehiculo(Model model, @RequestParam String matricula,@RequestParam String modelo,@RequestParam Profesores profesor) {
		
		Vehiculo vehiculo=vehiculos.getById(matricula);
		if(vehiculo==null) {
			vehiculos.save(new Vehiculo(matricula, modelo, profesor));
			model.addAttribute("name", "Autoescuelas Alpine, Nuevo vehiculo grabado correctamente");
			
			model.addAttribute("modelo", modelo);
			model.addAttribute("matricula", matricula);
			model.addAttribute("profesor", profesor);
			
			
			
			
			return "Detalle_vehiculo";
		}else{
			model.addAttribute("name", "Autoescuelas Alpine, Ya existe ese vehiculo");
			
			model.addAttribute("modelo", vehiculo.getModelo());
			model.addAttribute("matricula", vehiculo.getMatricula());
			model.addAttribute("profesor", vehiculo.getProfesor());
			
			return "Detalle_vehiculo";
		}
		
	}
	
	@GetMapping("/buscaVehiculo")
	public String buscaVehiculo(Model model) {
		model.addAttribute("name", "Autoescuelas Alpine, Buscar vehiculo");
		
		return "Consulta_vehiculo";
	}

	@PostMapping("/procesarBuscaVehiculo")
	public String procesarBuscaVehiculo(Model model, @RequestParam String matricula) {
		
		Vehiculo vehiculo=vehiculos.getById(matricula);
		if(vehiculo==null) {
			
			model.addAttribute("name", "Autoescuelas Alpine, No existe ese vehiculo");
		
			return "Consulta_vehiculo";
		}else{
			model.addAttribute("name", "Autoescuelas Alpine, Vehiculo");
			
			model.addAttribute("modelo", vehiculo.getModelo());
			model.addAttribute("matricula", vehiculo.getMatricula());
			model.addAttribute("profesor", vehiculo.getProfesor());
			
			
			
			return "Detalle_vehiculo";
		}
		
	}
	
	@GetMapping("/ModificaVehiculo")
	public String modificarVehiculo(Model model,@RequestParam String matricula) {
		Vehiculo vehiculo=vehiculos.getById(matricula);
		if(vehiculo==null) {

			model.addAttribute("name", "Autoescuelas Alpine, No existe ese vehiculo");

			return "Consulta_vehiculo";
		}else{
			model.addAttribute("name", "Autoescuelas Alpine, Modificar vehiculo");

			model.addAttribute("modelo", vehiculo.getModelo());
			model.addAttribute("matricula", vehiculo.getMatricula());
			model.addAttribute("profesor", vehiculo.getProfesor());

			return "Modificar_vehiculo";
		}
	}
	
	@PostMapping("/procesarModificarVehiculo")
	public String procesarModificarVehiculo(Model model, @RequestParam String modelo,@RequestParam String matricula) {
		
		Vehiculo vehiculo=vehiculos.getById(matricula);
		if(vehiculo!=null) {
			vehiculo.setModelo(modelo);
			vehiculos.save(vehiculo);
			model.addAttribute("name", "Autoescuelas Alpine, Vehiculo grabado correctamente");
			
			model.addAttribute("modelo", vehiculo.getModelo());
			model.addAttribute("matricula", vehiculo.getMatricula());
			model.addAttribute("profesor", vehiculo.getProfesor());
			
			return "Detalle_vehiculo";
		}else{
			model.addAttribute("name", "Autoescuelas Alpine, no existe ese vehiculo");
			
			
			return "Consulta_vehiculo";
		}
		
	}
	
	@GetMapping("/borraVehiculo")
	public String borrarVehiculo(Model model,@RequestParam String matricula) {
		Vehiculo vehiculo=vehiculos.getById(matricula);
		if(vehiculo==null) {

			model.addAttribute("name", "Autoescuelas Alpine, No existe ese vehiculo");

			return "Consulta_vehiculo";
		}else{
			model.addAttribute("name", "Autoescuelas Alpine,  Vehiculo borrado correctamente");

			vehiculos.delete(vehiculo);
			
			return "Consulta_vehiculo";
		}
	}
	
}