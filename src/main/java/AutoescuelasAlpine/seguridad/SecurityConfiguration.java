package AutoescuelasAlpine.seguridad;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Value("${security.user}")
	private String user;

	@Value("${security.encodedPassword}")
	private String encodedPassword;



	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//para guardar encriptado en el disco(propeties)
		PasswordEncoderFactories.createDelegatingPasswordEncoder();

		auth.inMemoryAuthentication().withUser(user).password("{bcrypt}" + encodedPassword).roles("USER");


		//prueba segundo usuario en ram
		PasswordEncoder encoder=PasswordEncoderFactories.createDelegatingPasswordEncoder();

		String encodedPassword=encoder.encode("admin");

		auth.inMemoryAuthentication()
		.withUser("admin").password(encodedPassword).roles("ADMIN","USER");	
	}




	@Override
	protected void configure(HttpSecurity http) throws Exception {

		// Public pages
		http.authorizeRequests().antMatchers("/").permitAll();
		http.authorizeRequests().antMatchers("/login").permitAll();
		http.authorizeRequests().antMatchers("/loginerror").permitAll();
		http.authorizeRequests().antMatchers("/logout").permitAll();
		// Private pages (all other pages)
		http.authorizeRequests().anyRequest().authenticated();
		//para varios usuarios.
		//http.authorizeRequests().antMatchers("/private").hasAnyRole("USER");
		//http.authorizeRequests().antMatchers("/admin").hasAnyRole("ADMIN");
		// Login form
		http.formLogin().loginPage("/login");
		http.formLogin().usernameParameter("username");
		http.formLogin().passwordParameter("password");
		http.formLogin().defaultSuccessUrl("/bienvenida");
		http.formLogin().failureUrl("/loginerror");
		// Logout
		http.logout().logoutUrl("/logout");
		http.logout().logoutSuccessUrl("/");

		// Disable CSRF at the moment
		http.csrf().disable();
	}
	//...

	
 

}
