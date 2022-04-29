package AutoescuelasAlpine;

import org.apache.juli.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;

import com.mysql.cj.log.Log;

@EnableCaching
@SpringBootApplication
public class InicioAlpineApplication {

	private static final Log LOG = (Log) LogFactory.getLog(InicioAlpineApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(InicioAlpineApplication.class, args);
	}
	
	@Bean
	public CacheManager cachemanager() {
		LOG.logInfo("Activando caché");
		return new ConcurrentMapCacheManager("alumnos");
	}
	
}
