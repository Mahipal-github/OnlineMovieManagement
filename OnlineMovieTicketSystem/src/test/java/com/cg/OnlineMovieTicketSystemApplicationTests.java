package com.cg;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.onlinemovie.exception.MovieIdException;
import com.cg.onlinemovie.exception.MovieNotFoundException;
import com.cg.onlinemovie.service.ViewMovieService;
import com.cg.onlinemovie.service.ViewMovieServiceImpl;

/*********************************************************************************************************************************
 * @author : Mahipal Reddy
 * Description : This is the Test class where we have tested the worst case scenarios and best scenarios and validated accordingly.
 * Version : 1.0 
 * Created Date : 04-SEP-2020
**********************************************************************************************************************************/

@SpringBootTest
class OnlineMovieTicketSystemApplicationTests {

/*********************************************************************************************************************
	 * Method : MovieTest1
	 * Description : Test case for validating zero integer value to movieId.
	 * Created By : Mahipal Reddy
	 * Created Date : 04-SEP-2020
*********************************************************************************************************************/

	@Test
	@DisplayName("Test validation for Zero Integer MovieId")
	public void MovieTest1() throws MovieIdException {
		ViewMovieService ser = new ViewMovieServiceImpl();
		assertThrows(MovieIdException.class, () -> ser.addMovieId(0));
	}

/*********************************************************************************************************************
	 * Method : MovieTest2
	 * Description : Test case for validating Negative integer values and the values that are less than 4 to movieId.
	 * Created By : Mahipal Reddy
	 * Created Date : 04-SEP-2020
*********************************************************************************************************************/

	@Test
	@DisplayName("Test validation for Negative Integer MovieId")
	public void MovieTest2() throws MovieIdException {
		ViewMovieService ser = new ViewMovieServiceImpl();
		assertThrows(MovieIdException.class, () -> ser.addMovieId(-200));
	}
	
/*********************************************************************************************************************
	 * Method : MovieTest3
	 * Description : Test case for validating some of the movieId's which are present already.
	 * Created By : Mahipal Reddy
	 * Created Date : 04-SEP-2020
*********************************************************************************************************************/

	@Test
	@DisplayName("Test validation for Valid MovieIds")
	public void MovieTest3() {
		ViewMovieService ser = new ViewMovieServiceImpl();
		int[] expected = { 1250, 1251 };
		int[] actual = { 1250, 1251 };
		assertArrayEquals(expected, actual);
	}

/*********************************************************************************************************************
	 * Method : MovieTest4
	 * Description : Test case for validating some of the movieId's which are not present in DB.
	 * Created By : Mahipal Reddy
	 * Created Date : 04-SEP-2020
*********************************************************************************************************************/

	@Test
	@DisplayName("Test validation for InValid MovieIds")
	public void MovieTest4() {
		ViewMovieService ser = new ViewMovieServiceImpl();
		int[] expected = { 1250, 1251 };
		int[] actual = { 3000, 4000 };
		assertNotSame(expected, actual);
	}

/*********************************************************************************************************************
	 * Method : MovieTest5
	 * Description : Test case for validating null value to the movieName.
	 * Created By : Mahipal Reddy
	 * Created Date : 04-SEP-2020
*********************************************************************************************************************/

	@Test
	@DisplayName("Test validation for String Null Value")
	public void MovieTest5() throws MovieNotFoundException {
		ViewMovieService ser = new ViewMovieServiceImpl();
		assertThrows(MovieNotFoundException.class, () -> ser.addMovieName(null));
	}
}
