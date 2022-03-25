import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificationController{
	@PostMapping("/notificacion")
	public ResponseEntity<Void> addItem(@RequestBody Message msg){
		System.out.println("Enviar mensaje: " + msg.getBody() + " a " + msg.getTo());
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
}