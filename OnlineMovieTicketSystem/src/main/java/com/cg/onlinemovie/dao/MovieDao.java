package com.cg.onlinemovie.dao;

import java.util.List;

import com.cg.onlinemovie.entity.Booking;
import com.cg.onlinemovie.entity.Movie;
import com.cg.onlinemovie.entity.Show;

/*************************************************************************************************************************
 * @author : Mahipal Reddy
 * Description : This is the dao interface for implementing JPA to Movie,Show,Booking Modules.
 * Version : 1.0 
 * Created Date : 04-SEP-2020
 *************************************************************************************************************************/

public interface MovieDao {

	public boolean addMovie(Movie movie);

	public Movie viewMovie(int movieId);

	public List<Movie> viewActiveMovies();

	public List<Movie> getMovies(String searchStr);

	public List<Show> getShows(int movieId);

	public boolean editShow(Show show);

	public Show getShow(int showId);

	public List<Show> getShows(String screenName);

	public List<Show> getShows();

	public boolean addBooking(Booking booking);

	public List<Booking> getBookingDetailsContact(String contact);

	public int countBookingInfo();

	public Booking getBookingDetails(String bookingId);

	public long getMaxBookingId(int showId);

	public boolean removeBooking(Booking booking);

}
