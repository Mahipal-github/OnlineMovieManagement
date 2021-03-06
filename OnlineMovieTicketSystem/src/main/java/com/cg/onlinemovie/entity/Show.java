package com.cg.onlinemovie.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/*************************************************************************************************************************
 * @author : Mahipal Reddy
 * Description : This is the Entity class where we have mapped the java fields to database entities for Show Module.
 * Version : 1.0 
 * Created Date : 04-SEP-2020
*************************************************************************************************************************/

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "lpu_show")
public class Show {
	@Id
	@Column(name = "show_id")
	private int showId;

	@Column(name = "show_name", length = 25)
	private String showName;

	@Column(name = "seats")
	private int seats;

	@Column(name = "show_date")
	private LocalDate showDate;

	@Column(name = "screen_name", length = 25)
	private String screenName;

	@Column(name = "screen_img", length = 25)
	private String screenImg;

	@ManyToOne
	@JoinColumn(name = "movie_id", referencedColumnName = "movie_id")
	private Movie movie = new Movie();

	public LocalDate getShowDate() {
		return showDate;
	}

	public void setShowDate(LocalDate showDate) {
		this.showDate = showDate;
	}

	public int getShowId() {
		return showId;
	}

	public void setShowId(int showId) {
		this.showId = showId;
	}

	public String getShowName() {
		return showName;
	}

	public void setShowName(String showName) {
		this.showName = showName;
	}

	public int getSeats() {
		return seats;
	}

	public void setSeats(int seats) {
		this.seats = seats;
	}

	public String getScreenName() {
		return screenName;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public String getScreenImg() {
		return screenImg;
	}

	public void setScreenImg(String screenImg) {
		this.screenImg = screenImg;
	}

}
