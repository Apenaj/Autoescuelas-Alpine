package AutoescuelasAlpine.cache;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import AutoescuelasAlpine.InicioAlpineApplication;

@RestController
public class CacheController{
	
	@Autowired
	private CacheManager cacheManager;
	
	@RequestMapping(value="/cache",method=RequestMethod.GET)
	public Map<Object, Object> getCacheContent(){
		ConcurrentMapCacheManager cacheMgr = (ConcurrentMapCacheManager) cacheManager;
		ConcurrentMapCache cacheAlumnos = (ConcurrentMapCache) cacheMgr.getCache(InicioAlpineApplication.CACHE_ALUMNOS);
		ConcurrentMapCache cacheCarnets = (ConcurrentMapCache) cacheMgr.getCache(InicioAlpineApplication.CACHE_CARNETS);
		Map<Object, Object> mapaTotal=new HashMap<>();
		mapaTotal.put(InicioAlpineApplication.CACHE_ALUMNOS, cacheAlumnos.getNativeCache());
		mapaTotal.put(InicioAlpineApplication.CACHE_CARNETS, cacheCarnets.getNativeCache());
		return mapaTotal;
	}
	
}