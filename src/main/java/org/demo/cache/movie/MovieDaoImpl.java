package org.demo.cache.movie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.inject.Named;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author rameshparsa
 *
 */
@Repository("movieDao")
@Named
public class MovieDaoImpl implements MovieDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setJdbcTemplate(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Cacheable(value = "movieCache", key = "#name")
	public Movie findByName(String name) {
		final long before = System.currentTimeMillis();
		System.out.println("******* findByName is retrieving from Database ******** ");
		String query = "SELECT * FROM MOVIE WHERE name = ?";
		final Movie movie = jdbcTemplate.queryForObject(query, new Object[] { name }, new MovieRowMapper());
		final long after = System.currentTimeMillis();
		System.out.println("Time taken to retrieve from database in ms : " + (after - before));
		return movie;
	}
	
	public Number addMoviePlayStatus(final Movie movie) {
		final String INSERT_MOVIE_STATUS 
	       = "insert into movie_play_status (movie_name, status, release_date) values(?, ?, ?)";

	KeyHolder keyHolder = new GeneratedKeyHolder();
	    jdbcTemplate.update(new PreparedStatementCreator() {
	        public PreparedStatement createPreparedStatement(
	            Connection connection) throws SQLException {
	                PreparedStatement ps = connection.prepareStatement(
	                		INSERT_MOVIE_STATUS, new String[] { "id" });
	                ps.setString(1, movie.getName());
	                ps.setString(2, "RECVD");
	                ps.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
	                return ps;
	            }
	        }, keyHolder);
		return keyHolder.getKey();
	}

}
