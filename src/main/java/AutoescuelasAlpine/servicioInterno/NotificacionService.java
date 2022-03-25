package AutoescuelasAlpine.servicioInterno;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NotificacionService{
	
	private static final String MAIL_SERVICE_URL = "http://localhost:8443";
	
	public void enviarNotificacion (String mensaje) {
		
		Message msg = new Message("ejemplo@gmail.com", mensaje);
		
		HttpEntity<Message> httpEntity = new HttpEntity<>(msg);
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity res =restTemplate.postForEntity(MAIL_SERVICE_URL + "/notificacion", httpEntity, ResponseEntity.class);
		
		if(res.getStatusCode() == HttpStatus.CREATED) {
			System.out.println("Enviado correctamente");
		}else{
			System.out.println("Error enviando");
		}
		
		
	}
}