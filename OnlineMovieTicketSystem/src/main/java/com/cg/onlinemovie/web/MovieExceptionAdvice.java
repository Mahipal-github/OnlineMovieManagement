package com.cg.onlinemovie.web;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.cg.onlinemovie.exception.MovieException;
import com.cg.onlinemovie.exception.MovieIdException;
import com.cg.onlinemovie.exception.MovieNotFoundException;


/*************************************************************************************************************************
 * @author : Mahipal Reddy
 * Description : This is the MovieExceptionAdvice class where we can define all exceptions using Global Exception Handler.
 * Version : 1.0 
 * Created Date : 04-SEP-2020
*************************************************************************************************************************/

public class MovieExceptionAdvice {

	Logger logger = LoggerFactory.getLogger(MovieExceptionAdvice.class);

	@CrossOrigin
	@ExceptionHandler(value = { MovieNotFoundException.class, MovieIdException.class, MovieException.class })
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public Map<String, String> handleException(Exception ex) {
		logger.error(ex.getMessage());
		;
		Map<String, String> map = new HashMap<>();
		map.put("message", ex.getMessage());
		return map;
	}
}
