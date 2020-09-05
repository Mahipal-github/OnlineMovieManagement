package com.cg.onlinemovie.exception;

/*************************************************************************************************************************
 * @author : Mahipal Reddy
 * Description : This is the Exception class which raises MovieNotFoundException when the MovieName is not valid.
 * Version : 1.0 
 * Created Date : 04-SEP-2020
*************************************************************************************************************************/

public class MovieNotFoundException extends Exception {

	public MovieNotFoundException() {
		super();
	}

	public MovieNotFoundException(String msg) {
		super(msg);
	}
}
