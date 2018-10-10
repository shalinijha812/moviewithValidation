package com.stackroute.movieservice.service;

import com.stackroute.movieservice.domain.Movie;
import com.stackroute.movieservice.exception.MovieAlreadyExistsException;
import com.stackroute.movieservice.exception.MovieNotFoundException;
import com.stackroute.movieservice.repository.MovieRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MovieServiceTest {

   Movie movie;

    //Create a mock for UserRepository
    @Mock//test double
    MovieRepository movieRepository;

    //Inject the mocks as dependencies into UserServiceImpl
    @InjectMocks
    MovieServiceImpl movieService;
    List<Movie> list= null;
    @Before
    public void setUp(){
        //Initialising the mock object
        MockitoAnnotations.initMocks(this);
        movie = new Movie(6,"Raaz","hindi","2 star");
//        user.setLastName("John");
//        user.setId(101);
//        user.setFirstName("Jenny");
//        user.setAge(10);
        list = new ArrayList<>();
        list.add(movie);


    }
    @Test
    public void saveMovieTestSuccess() throws MovieAlreadyExistsException {

        when(movieRepository.save((Movie) any())).thenReturn(movie);
        Movie savedMovie = movieService.saveMovie(movie);
        Assert.assertEquals(movie,savedMovie);

        //verify here verifies that userRepository save method is only called once
        verify(movieRepository,times(1)).save(movie);

    }
    @Test(expected = MovieAlreadyExistsException.class)
    public void saveMovieTestFailure() throws MovieAlreadyExistsException {
        when(movieRepository.save((Movie) any())).thenReturn(null);
        Movie savedMovie = movieService.saveMovie(movie);
        System.out.println("savedMovie" + savedMovie);
        Assert.assertEquals(movie, savedMovie);
    }
    @Test(expected=MovieNotFoundException.class)
   public void updateMovieTest()throws MovieAlreadyExistsException,MovieNotFoundException {
        //movieRepository.save(movie);

        movieRepository.save(movie);
        Movie updatedMovie = movieService.updateComments(movie.getId(), "bad movie");
        Assert.assertEquals("bad movie", updatedMovie.getComments());
    }

//        updatedMovie.setComments("1 star movie");
//        Movie updatedSavedMovie = movieRepository.save(updatedMovie);
//        Assert.assertEquals("1 star movie",updatedSavedMovie.getComments());






//    }
    @Test
    public void getallMovieTest()
   {

        movieRepository.save(movie);
        //stubbing the mock to return specific data
        when(movieRepository.findAll()).thenReturn(list);
        List<Movie> movielist = movieService.getAllMovie();
        Assert.assertEquals(list,movielist);

        //add verify
    }
    @Test(expected=MovieNotFoundException.class)
    public void  deleteMovieTest()throws MovieNotFoundException,MovieAlreadyExistsException{


            Movie savedMovie = movieRepository.save(movie);
//        Movie movie2 = new Movie(6,"Raaz","hindi","2 star");
//        movieRepository.save(movie2);
            String expectedmsg = movieService.deleteMovie(movie.getId());
            Assert.assertEquals(expectedmsg, "Succesfully deleted");

    }



}
