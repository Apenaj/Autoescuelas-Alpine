package AutoescuelasAlpine.seguridad;

import java.security.SecureRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private RepositoryUserDetailsService userDetailsService;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(10, new SecureRandom());
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}


	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// Public pages
		http.authorizeRequests().antMatchers("/").permitAll();
		http.authorizeRequests().antMatchers("/login").permitAll();
		http.authorizeRequests().antMatchers("/loginerror").permitAll();
		http.authorizeRequests().antMatchers("/logout").permitAll();
		// Private pages (all other pages)
		//http.authorizeRequests().anyRequest().authenticated();
		//para varios usuarios.
		//http.authorizeRequests().antMatchers("/private").hasAnyRole("USER");
		//http.authorizeRequests().antMatchers("/admin").hasAnyRole("ADMIN");
		//Todo el mundo tiene que loguearse
		http.authorizeRequests().antMatchers("/a*").hasAnyRole("PROFESOR","ADMIN").antMatchers("/*").authenticated();
		http.authorizeRequests().antMatchers("/bienvenida").hasAnyRole("ALUMNO").antMatchers("/*").authenticated();
		
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
		//http.csrf().disable();
	}

}

	 
		 
	 
	


	

/*@Autowired
RepositoryUserDetailsService userDetailsService;

@Bean
public PasswordEncoder passwordEncoder() {
	return new BCryptPasswordEncoder(10, new SecureRandom());
}*/

/*@Override
protected void configure(AuthenticationManagerBuilder auth) throws Exception {

	auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
}
	
	
	
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
	}*/




	/*@Override
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
		//http.formLogin().failureUrl("/bienvenida");
		// Logout
		http.logout().logoutUrl("/logout");
		http.logout().logoutSuccessUrl("/");

		// Disable CSRF at the moment
		http.csrf().disable();
	}
	

	
 

}*/
