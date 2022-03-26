package servicio;

public class Message {
	
	public static final String CORREO_ORIGEN="alpinedad2022@gmail.com";

	private String to;
	private String asunto;
	private String body;
	
	
	public Message() {}
	
	public Message(String to,String asunto, String body) {
		this.to = to;
		this.asunto = asunto;
		this.body = body;
	}
	
	public String getTo() { return to; }
	
	public String getBody() { return body; }
	
	public void setTo(String to) { this.to = to; }

	public void setBody (String body) { this.body = body; }

	public String getAsunto() {
		return asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}
	
	

}
