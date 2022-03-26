/**
 * 
 */
package servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * @author ja.conde
 *
 */
@Component
public class EnvioCorreo {
	
	@Autowired
	private JavaMailSender sender;
	
	public boolean send(Message mensaje) {
		
		MimeMessage email = sender.createMimeMessage();
		
		try {
			
			MimeMessageHelper helper = new MimeMessageHelper(email,true);
			
			//Campos necesarios para mandar el correo 
			helper.setFrom(Message.CORREO_ORIGEN); // dirección origen
			helper.setTo(mensaje.getTo()); // dirección destino
			helper.setSubject(mensaje.getAsunto()); // asunto del correo
			helper.setText(mensaje.getBody(), true); // cuerpo del mensaje
			
			sender.send(email);
			
			return true;
			
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}

}
