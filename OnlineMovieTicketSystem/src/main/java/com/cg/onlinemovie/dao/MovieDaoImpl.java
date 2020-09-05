
package com.cg.onlinemovie.dao;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.stereotype.Repository;
import com.cg.onlinemovie.entity.Booking;
import com.cg.onlinemovie.entity.Movie;
import com.cg.onlinemovie.entity.Show;
import com.cg.onlinemovie.exception.MovieNotFoundException;

/*************************************************************************************************************************
 * @author : Mahipal Reddy
 * Description : This is the dao class for implementing JPA to Movie,Show,Booking Modules.
 * Version : 1.0 
 * Created Date : 04-SEP-2020
*************************************************************************************************************************/

@DynamicInsert
@DynamicUpdate
@Repository
public class MovieDaoImpl implements MovieDao {

	@PersistenceContext
	private EntityManager em;

/*********************************************************************************************************************
	 * Method : addMovie
	 * Description : Adds the movie information into the database.
	 * Created By : Mahipal Reddy
	 * Created Date : 04-SEP-2020
*********************************************************************************************************************/
	
	@Override
	public boolean addMovie(Movie movie) {
		em.persist(movie);
		return true;
	}
	
/*********************************************************************************************************************
	 * Method : viewMovie 
	 * Description : This method will find the movie details in the database for the given primary key.
	 * Created By : Mahipal Reddy
	 * Created Date : 04-SEP-2020
*********************************************************************************************************************/

	@Override
	public Movie viewMovie(int movieId) {

		return em.find(Movie.class, movieId);
	}

/*********************************************************************************************************************
	 * Method : viewActiveMovies
	 * Description : This method will find the movies in the database which are in active status with the help of query.
	 * Created By : Mahipal Reddy
	 * Created Date : 04-SEP-2020
*********************************************************************************************************************/

	@Override
	public List<Movie> viewActiveMovies() {
		String jpql = "from Movie m where m.active=1";
		TypedQuery<Movie> query = em.createQuery(jpql, Movie.class);

		return query.getResultList();
	}

/*********************************************************************************************************************
	 * Method : getshows
	 * Description : This method will find the  available shows in the database for the given movieId with the help of query.
	 * Created By : Poojith
	 * Created Date : 04-SEP-2020
*********************************************************************************************************************/

	@Override
	public List<Show> getShows(int movieId) {
		String jpql = "from Show s inner join fetch s.movie m where m.movieId=:mid and s.showDate >= :dt";
		TypedQuery<Show> query = em.createQuery(jpql, Show.class);

		query.setParameter("mid", movieId);
		query.setParameter("dt", LocalDate.now());
		return query.getResultList();
	}
	
/*********************************************************************************************************************
	 * Method : editshow
	 * Description : This method will edit the show details and updates the show.
	 * Created By : Poojith
	 * Created Date : 04-SEP-2020
*********************************************************************************************************************/

	@Override
	public boolean editShow(Show show) {
		em.merge(show);
		return true;
	}

/*********************************************************************************************************************
	 * Method : addBooking
	 * Description : This method will add the booking information in to the database when user book a movie for a show.
	 * Created By : Arshad
	 * Created Date : 04-SEP-2020
*********************************************************************************************************************/

	@Override
	public boolean addBooking(Booking booking) {
		em.persist(booking);
		return true;
	}

/*********************************************************************************************************************
	 * Method : addBooking
	 * Description : This method will fetch the booking details for a given contact number by jpql query.
	 * Created By : Arshad
	 * Created Date : 04-SEP-2020
*********************************************************************************************************************/

	@Override
	public List<Booking> getBookingDetailsContact(String contact) {
		String jpql = "from Booking b inner join fetch b.show s inner join fetch s.movie m where b.contact=:phone";
		TypedQuery<Booking> query = em.createQuery(jpql, Booking.class);

		query.setParameter("phone", contact);
		return query.getResultList();
	}

/*********************************************************************************************************************
	 * Method : addBooking
	 * Description : This method will fetch the booking details for a given booking Id by jpql query.
	 * Created By : Arshad
	 * Created Date : 04-SEP-2020
*********************************************************************************************************************/

	@Override
	public Booking getBookingDetails(String bookingId) {
		String jpql = "from Booking b inner join fetch b.show s inner join fetch s.movie m where b.bookingId=:bid";
		TypedQuery<Booking> query = em.createQuery(jpql, Booking.class);
		query.setParameter("bid", bookingId);
		return query.getSingleResult();
	}

/*********************************************************************************************************************
	 * Method : addBooking
	 * Description : This method will count how  many bookings has been done by user with the help of jpql query.
	 * Created By : Arshad
	 * Created Date : 04-SEP-2020
*********************************************************************************************************************/

	@Override
	public int countBookingInfo() {
		String jpql = "select count(bookingId) from Booking";
		TypedQuery<Integer> query = em.createQuery(jpql, Integer.class);
		return query.getSingleResult();

	}

/*********************************************************************************************************************
	 * Method : getMovies
	 * Description : This method will fetch the movie details for a given moviename/director/language by jpql query.
	 * Created By : Mahipal Reddy
	 * Created Date : 04-SEP-2020
*********************************************************************************************************************/

	@Override
	public List<Movie> getMovies(String searchStr) {
		String jpql = "from Movie m where m.movieName like :str or m.director like :str or m.language like :str or m.genre like :str and m.active=1";
		TypedQuery<Movie> query = em.createQuery(jpql, Movie.class);

		query.setParameter("str", searchStr);
		return query.getResultList();
	}

/*********************************************************************************************************************
	 * Method : getshow
	 * Description : This method will find the show details for a given showId in the database.
	 * Created By : Poojith
	 * Created Date : 04-SEP-2020
*********************************************************************************************************************/

	@Override
	public Show getShow(int showId) {
		return em.find(Show.class, showId);
	}
	
/*********************************************************************************************************************
	 * Method : getMaxBookingId
	 * Description : This method will find the booking details for a given showId in the database.
	 * Created By : Arshad
	 * Created Date : 04-SEP-2020
*********************************************************************************************************************/

	@Override
	public long getMaxBookingId(int showId) {
		String jpql = "select count(b.bookingId) from Booking b join b.show s where s.showId=:showid";
		TypedQuery<Long> query = em.createQuery(jpql, Long.class);
		query.setParameter("showid", showId);
		return query.getSingleResult();
	}

/*********************************************************************************************************************
	 * Method : getshows
	 * Description : This method will find the shows for a given screenName.
	 * Created By : Poojith
	 * Created Date : 04-SEP-2020
*********************************************************************************************************************/

	@Override
	public List<Show> getShows(String screenName) {
		String jpql = "from Show s inner join fetch s.movie m where s.screenName=:screenname and s.showDate >= :dt";
		TypedQuery<Show> query = em.createQuery(jpql, Show.class);

		query.setParameter("screenname", screenName);
		query.setParameter("dt", LocalDate.now());
		return query.getResultList();
	}

/*********************************************************************************************************************
	 * Method : getshows
	 * Description : This method will find the movies for a given showDate.
	 * Created By : Poojith
	 * Created Date : 04-SEP-2020
*********************************************************************************************************************/

	@Override
	public List<Show> getShows() {
		String jpql = "from Show s inner join fetch s.movie m where  s.showDate >= :dt";
		TypedQuery<Show> query = em.createQuery(jpql, Show.class);
		query.setParameter("dt", LocalDate.now());
		return query.getResultList();

	}

/*********************************************************************************************************************
	 * Method : removeBooking
	 * Description : This method will remove the booking done by a user when user wants to remove it.
	 * Created By : Arshad
	 * Created Date : 04-SEP-2020
*********************************************************************************************************************/

	@Override
	public boolean removeBooking(Booking booking) {
		em.remove(booking);
		return true;
	}
}
