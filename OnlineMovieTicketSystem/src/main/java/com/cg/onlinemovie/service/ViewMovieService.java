
package com.cg.onlinemovie.service;

import java.util.List;

import com.cg.onlinemovie.entity.Movie;
import com.cg.onlinemovie.exception.MovieException;
import com.cg.onlinemovie.exception.MovieIdException;
import com.cg.onlinemovie.exception.MovieNotFoundException;

/*************************************************************************************************************************
 * @author : Mahipal Reddy
 * Description : This is the service interface that provides the implementation to Movie Module.
 * Version : 1.0 
 * Created Date : 04-SEP-2020
*************************************************************************************************************************/

public interface ViewMovieService {

	public boolean addMovie(Movie movie) throws MovieException;

	public List<Movie> viewNewMovies() throws MovieNotFoundException;

	public List<Movie> getMovies(String searchStr) throws MovieNotFoundException;

	boolean addMovieId(int movieId) throws MovieIdException;

	public List<Movie> viewMovies() throws MovieNotFoundException;

	boolean addMovieName(String movieName) throws MovieNotFoundException;
}
