package com.cg.onlinemovie.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.cg.onlinemovie.dto.MovieMessage;
import com.cg.onlinemovie.entity.Movie;
import com.cg.onlinemovie.exception.MovieException;
import com.cg.onlinemovie.exception.MovieIdException;
import com.cg.onlinemovie.exception.MovieNotFoundException;
import com.cg.onlinemovie.service.ViewMovieService;
import com.cg.onlinemovie.util.MovieConstants;

/*************************************************************************************************************************
 * @author : Mahipal Reddy
 * Description : This is the RestController class which creates Restful web services using http protocol.
 * Version : 1.0 
 * Created Date : 04-SEP-2020
*************************************************************************************************************************/

@RestController
public class MovieController {

	@Autowired
	private ViewMovieService service;

/*************************************************************************************************************************************
	 * Method : addMovie
	 * Description : Adds the movie information into the database when post request is made from the different platforms.
	 * Created By : Mahipal Reddy
	 * Created Date : 04-SEP-2020
	 * @throws MovieidException,MovieException : If the information given is not valid then the exception is thrown during the request
**************************************************************************************************************************************/

	@CrossOrigin
	@PostMapping("/addmovie")
	public MovieMessage addMovie(@RequestBody Movie movie) throws MovieIdException, MovieException {
		try {
			service.addMovie(movie);
			MovieMessage msg = new MovieMessage();
			msg.setMessage(MovieConstants.MOVIE_ADDED);
			return msg;
		} catch (DataIntegrityViolationException ex) {
			throw new MovieIdException(MovieConstants.ID_EXISTS);
		}
	}
	
/*************************************************************************************************************************************
	 * Method : viewMovies
	 * Description : Returns the movie information which are active from the database when get request is made from the browser.
	 * Created By : Mahipal Reddy
	 * Created Date : 04-SEP-2020
	 * @throws MovieNotFoundException : If the information given is not valid then the exception is thrown during the request
**************************************************************************************************************************************/

	@CrossOrigin
	@GetMapping("/viewmovies")
	public List<Movie> viewMovies() throws MovieNotFoundException {
		return service.viewMovies();
	}

/*************************************************************************************************************************************
	 * Method : viewNewMovies
	 * Description : Returns the movies that are added as per order and are active from the database when get request is made from the browser.
	 * Created By : Mahipal Reddy
	 * Created Date : 04-SEP-2020
	 * @throws MovieNotFoundException : If the information given is not valid then the exception is thrown during the request
**************************************************************************************************************************************/

	@CrossOrigin
	@GetMapping("/viewnewmovies")
	public List<Movie> viewNewMovies() throws MovieNotFoundException {
		return service.viewNewMovies();
	}

/*************************************************************************************************************************************
	 * Method : searchMovie
	 * Description : Returns the movie from the database if it matches the given input when get request is made from the browser.
	 * Created By : Mahipal Reddy
	 * Created Date : 04-SEP-2020
	 * @throws MovieNotFoundException : If the information given is not valid then the exception is thrown during the request
**************************************************************************************************************************************/

	@CrossOrigin
	@GetMapping("searchmovie/{name}")
	public List<Movie> searchMovie(@PathVariable("name") String name) throws MovieNotFoundException {
		List<Movie> movieList = service.getMovies(name);
		if (movieList.isEmpty()) {
			throw new MovieNotFoundException(MovieConstants.MOVIE_NOT_AVAILABLE);
		}
		return movieList;
	}
}
