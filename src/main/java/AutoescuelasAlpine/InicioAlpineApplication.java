package AutoescuelasAlpine;

import java.util.Collections;

import javax.annotation.Resource;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.session.hazelcast.config.annotation.web.http.EnableHazelcastHttpSession;

import com.hazelcast.config.Config;
import com.hazelcast.config.JoinConfig;

@EnableCaching
@SpringBootApplication
@EnableHazelcastHttpSession
public class InicioAlpineApplication {
	
	public static final String NOMBRE_INSTANCIA="NOMBRE_INSTANCIA";

	private static final Log LOG = (Log) LogFactory.getLog(InicioAlpineApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(InicioAlpineApplication.class, args);
	}
	
	@Bean
	public CacheManager cachemanager() {
		LOG.info("Activando caché");
		return new ConcurrentMapCacheManager("alumnos");
	}
	
	@Resource
    private Environment environment;
	
	@Bean
	public Config config() {
		
		Config config = new Config();
		JoinConfig joinConfig = config.getNetworkConfig().getJoin();
		
		joinConfig.getMulticastConfig().setEnabled(true);
//		joinConfig.getTcpIpConfig().setEnabled(true).setMembers(Collections.singletonList(environment.getProperty(InicioAlpineApplication.NOMBRE_INSTANCIA)));
		
		return config;
	}
	
}
