package AutoescuelasAlpine.modelo;


import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
	
	public static final String ROL_ALUMNO="ALUMNO";
	public static final String ROL_PROFESOR="PROFESOR";
	public static final String ROL_ADMIN="ADMIN";

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(unique=true)
	private String name;

	private String passwordHash;

	@ElementCollection(fetch = FetchType.EAGER)
	private List<String> roles;

	public User() {
	}

	public User(String name, String passwordHash,List<String> roles) {
		this.name = name;
		this.passwordHash = passwordHash;
		//this.roles = List.of(roles);
		this.roles=roles;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getpasswordHash() {
		return passwordHash;
	}

	public void setEncodedPassword(String encodedPassword) {
		this.passwordHash = encodedPassword;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
}





/*import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	//private String passwordHash;
	private String encodedPassword;
	@ElementCollection(fetch = FetchType.EAGER)
	private List<String> roles;
	//Constructor, getters and setters
	public User(String name, String encodedPassword, List<String> roles) {
		super();
		this.name = name;
		this.encodedPassword = encodedPassword;
		this.roles = roles;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEncodedPassword() {
		return encodedPassword;
	}
	public void setEncodedPassword(String encodedPassword) {
		this.encodedPassword = encodedPassword;
	}
	public List<String> getRoles() {
		return roles;
	}
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	
	
	
	
	
	


}*/