package AutoescuelasAlpine.seguridad;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import AutoescuelasAlpine.InicioAlpineApplication;
import AutoescuelasAlpine.modelo.User;

@Configuration
public class CSRFHandlerConfiguration implements WebMvcConfigurer {
	
	@Resource
    private Environment environment;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new CSRFHandlerInterceptor(environment));
	}
}

class CSRFHandlerInterceptor implements HandlerInterceptor {
	
	
    public CSRFHandlerInterceptor(Environment environment) {
		super();
		this.environment = environment;
	}

	private Environment environment;

	@Override
	public void postHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler,
			final ModelAndView modelAndView) throws Exception {

		if (modelAndView != null) {

			CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
			if (token != null) {
				modelAndView.addObject("token", token.getToken());
			}
			modelAndView.addObject("servidor", environment.getProperty(InicioAlpineApplication.NOMBRE_INSTANCIA));
			modelAndView.addObject("admin", request.isUserInRole(User.ROL_ADMIN));
		}
	}
}

