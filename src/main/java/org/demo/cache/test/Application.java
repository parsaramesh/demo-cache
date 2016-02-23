package org.demo.cache.test;

import org.demo.cache.movie.AppConfig;
import org.demo.cache.movie.MovieDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 
 * @author rameshparsa
 *
 */
public class Application {

	private static final Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {

		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		MovieDao obj = (MovieDao) context.getBean("movieDao");

		log.debug("Result : {}", obj.findByName("The Intouchables"));

		log.debug("Result : {}", obj.findByName("Avatar"));
		
		/* just insert many times to check sequence generation */
		for(long number=0; number<100000; number++)
		{
			log.debug("Result : {}", obj.addMoviePlayStatus(obj.findByName("Avatar")));
		}
		((ConfigurableApplicationContext) context).close();

	}
}
