package servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificacionController{
	
	@Autowired
	private EnvioCorreo envioCorreo;
	
	@PostMapping("/notificacion")
	public ResponseEntity<Void> enviarNotificacion(@RequestBody Message msg){
		System.out.println("Enviar mensaje: " + msg.getBody() + " a " + msg.getTo());
		boolean respuesta=envioCorreo.send(msg);
		if(respuesta){
			return new ResponseEntity<>(HttpStatus.CREATED);
		}else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}