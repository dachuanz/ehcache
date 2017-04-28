

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.Configuration;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.xml.XmlConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author 张大川
 *
 */
public class CacheUtil {
	private static Logger log = LoggerFactory.getLogger(CacheUtil.class);
	private static Configuration xmlConfig;
	static {
		// log.info(xmlConfig.toString());
		xmlConfig = new XmlConfiguration(CacheUtil.class.getClassLoader().getResource("ehcache_config.xml"));
		log.info(xmlConfig.toString());
	}

	private static final class CacheManagerInstance {

		public final static CacheManager myCacheManager;
		static {
			myCacheManager = CacheManagerBuilder.newCacheManager(xmlConfig);
			// myCacheManager.init();
		}
	}
/**
 *  关闭缓存
 */
	public static final void close() {
		CacheManagerInstance.myCacheManager.close();
	}
/**
 * 
 */
	public static final void init() {
		CacheManagerInstance.myCacheManager.init();
	}

	public static Cache<String, String> getCache() {

		return CacheManagerInstance.myCacheManager.getCache("dbcache", String.class, String.class);
	}

}
