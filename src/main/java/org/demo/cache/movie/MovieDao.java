package org.demo.cache.movie;

/**
 * 
 * @author rameshparsa
 *
 */
public interface MovieDao {

	Movie findByName(String name);
	
	Number addMoviePlayStatus(final Movie movie);

}