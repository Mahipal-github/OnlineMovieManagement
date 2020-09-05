package com.cg.onlinemovie.exception;

/*************************************************************************************************************************
 * @author : Mahipal Reddy
 * Description : This is the Exception class which raises MovieIdException when the MovieId is not valid.
 * Version : 1.0 
 * Created Date : 04-SEP-2020
*************************************************************************************************************************/

public class MovieIdException extends Exception {

	public MovieIdException() {
		super();
	}

	public MovieIdException(String msg) {
		super(msg);
	}
}
