/**
 * 
 */
package org.demo.cache.movie;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

/**
 * @author rameshparsa
 *
 */
public class MovieRowMapper implements RowMapper<Movie> {

	/**
	 * Return Movie 
	 */
	public Movie mapRow(ResultSet rs, int rowNum) throws SQLException {
		Movie movie = new Movie();
		movie.setId(rs.getInt("id"));
		movie.setName(rs.getString("name"));
		movie.setDirectory(rs.getString("director"));
		return movie;
	}

}
