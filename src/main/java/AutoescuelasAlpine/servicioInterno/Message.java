package AutoescuelasAlpine.servicioInterno;

public class Message {

	private String to;
	private String body;
	
	
	public Message() {}
	
	public Message(String to, String body) {
		this.to = to;
		this.body = body;
	}
	
	public String getTo() { return to; }
	
	public String getBody() { return body; }
	
	public void setTo(String to) { this.to = to; }

	public void setBody (String body) { this.body = body; }
	
}