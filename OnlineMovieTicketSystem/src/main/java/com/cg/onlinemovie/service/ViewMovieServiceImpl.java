
package com.cg.onlinemovie.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.onlinemovie.dao.MovieDao;
import com.cg.onlinemovie.entity.Movie;
import com.cg.onlinemovie.exception.MovieException;
import com.cg.onlinemovie.exception.MovieIdException;
import com.cg.onlinemovie.exception.MovieNotFoundException;
import com.cg.onlinemovie.util.MovieConstants;

/*************************************************************************************************************************
 * @author : Mahipal Reddy
 * Description : This is the service class for manipulating the data related to Movie Module.
 * Version : 1.0 
 * Created Date : 04-SEP-2020
 *************************************************************************************************************************/
@Transactional
@Service
public class ViewMovieServiceImpl implements ViewMovieService {

	@Autowired
	private MovieDao dao;

/*********************************************************************************************************************
	 * Method : addMovie
	 * Description : Adds the movie information into the database.
	 * Created By : Mahipal Reddy
	 * Created Date : 04-SEP-2020
	 * @throws MovieNotFoundException : If the information given is not valid then the exception is thrown 
*********************************************************************************************************************/

	public boolean addMovie(Movie movie) throws MovieException {
		dao.addMovie(movie);
		return true;
	}
	
/*********************************************************************************************************************
	 * Method : viewMovies 
	 * Description : Generates the list of movies that are active.
	 * Created By : Mahipal Reddy
	 * Created Date : 04-SEP-2020
	 * @throws MovieNotFoundException : when the Movies are not present in the list then the exception is thrown 
*********************************************************************************************************************/


	@Override
	public List<Movie> viewMovies() throws MovieNotFoundException {
		List<Movie> movielst = dao.viewActiveMovies();
		if (movielst.isEmpty())
			throw new MovieNotFoundException(MovieConstants.MOVIE_NOT_AVAILABLE);
		movielst.sort((m1, m2) -> m1.getMovieName().compareTo(m2.getMovieName()));
		return movielst;
	}
	
/*********************************************************************************************************************
	 * Method : viewNewMovies 
	 * Description : To give a list of new movies that are active in database.
	 * Created By : Mahipal Reddy
	 * Created Date : 04-SEP-2020
	 * @throws MovieNotFoundException : when the Movies are not present in the list then the exception is thrown 
*********************************************************************************************************************/
	
	@Override
	public List<Movie> viewNewMovies() throws MovieNotFoundException {
		List<Movie> movielst = dao.viewActiveMovies();
		if (movielst.isEmpty())
			throw new MovieNotFoundException(MovieConstants.MOVIE_NOT_AVAILABLE);
		List<Movie> newMovies = new ArrayList<>();
		newMovies.addAll(getMovies(movielst, MovieConstants.ENGLISH));
		newMovies.addAll(getMovies(movielst, MovieConstants.HINDI));
		newMovies.addAll(getMovies(movielst, MovieConstants.TELUGU));
		newMovies.addAll(getMovies(movielst, MovieConstants.KANNADA));

		return newMovies;
	}
	
/***********************************************************************************************************************

	 * Method : getMovies 
	 * Description : To give a list of movies by taking language as input.
	 * @param searchStr: movielst is the list having Movie details, language is the parameter for which we will give input.
	 * Created By : Mahipal Reddy
	 * Created Date : 04-SEP-2020
	 * @throws MovieNotFoundException : When a movie that is searched does not exist in database, exception is thrown.
****************************************************************************************************************************/

	public List<Movie> getMovies(List<Movie> movielst, String language) {
		return movielst.stream().filter(m -> m.getLanguage().equalsIgnoreCase(language))
				.sorted((m1, m2) -> m2.getReleaseDt().compareTo(m1.getReleaseDt())).limit(2)
				.collect(Collectors.toList());
	}
	
/***********************************************************************************************************************

	 * Method : getMovies 
	 * Description : To give a list of movies by taking movie name/movie director/ movie genre as input.
	 * @param searchStr: Movie's name/ movie genre/ movie director's name.
	 * Created By : Mahipal Reddy
	 * Created Date : 04-SEP-2020
	 * @throws MovieNotFoundException : When a movie that is searched does not exist in database, exception is thrown.
****************************************************************************************************************************/
	
	@Override
	public List<Movie> getMovies(String searchStr) throws MovieNotFoundException {
		List<Movie> movieLst = dao.getMovies(searchStr);
		if (movieLst.isEmpty())
			throw new MovieNotFoundException(MovieConstants.MOVIE_NOT_AVAILABLE);
		List<Movie> lst = movieLst.stream().filter(m -> m.getActive() == MovieConstants.ACTIVE)
				.sorted((m1, m2) -> m1.getMovieName().compareTo(m2.getMovieName())).collect(Collectors.toList());
		return lst;
	}

	@Override
	public boolean addMovieId(int movieId) throws MovieIdException {
		if (movieId == MovieConstants.NULL)
			throw new MovieIdException(MovieConstants.MOVIEID_NULL);
		else if (movieId <= MovieConstants.FOUR)
			throw new MovieIdException(MovieConstants.MOVIEID_NOTVALID);
		return true;
	}

	@Override
	public boolean addMovieName(String movieName) throws MovieNotFoundException {
		if (movieName == null)
			throw new MovieNotFoundException(MovieConstants.MOVIENAME_NULL);
		return true;
	}

}
