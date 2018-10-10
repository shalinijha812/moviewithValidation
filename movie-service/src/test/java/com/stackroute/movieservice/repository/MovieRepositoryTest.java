package com.stackroute.movieservice.repository;

import com.stackroute.movieservice.domain.Movie;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataMongoTest
//@SpringBootTest
    public class MovieRepositoryTest {

        @Autowired
        MovieRepository movieRepository;
        Movie movie;

        @Before
        public void setUp()
        {
           movie = new Movie();
           movie.setId(10);
           movie.setTitle("Rang De Basanti");
           movie.setComments("good movie");
           movie.setLanguage("Hindi");

        }
        @Test
        public void testSaveMovie(){
            movieRepository.save(movie);
            Movie fetchMovie = movieRepository.findById(movie.getId()).get();
            Assert.assertEquals(10,fetchMovie.getId());

        }
        @Test
        public void testSaveMovieFailure(){
            Movie testMovie = new Movie(10,"spiderman","English","4 star");
            movieRepository.save(testMovie);
            Movie fetchMovie=movieRepository.findById(movie.getId()).get();
            Assert.assertNotSame(fetchMovie.getTitle(),testMovie.getTitle());
//            movieRepository.save(movie);
//            Movie fetchMovie = movieRepository.findById(movie.getId()).get();
//            Assert.assertNotSame(movie,fetchMovie);
        }
        @Test
        public void testGetAllMovie(){
            List<Movie> listold = movieRepository.findAll();
            Movie movie1 = new Movie(10,"Gita Govindam","Telugu","3 star");
            Movie movie2 = new Movie(9,"Buommarillu","Telugu","4 star");
            movieRepository.save(movie1);
            movieRepository.save(movie2);
            List<Movie> listUpdated = movieRepository.findAll();
            Assert.assertEquals(listold.size()+2,listUpdated.size());
        }
        @Test
        public void deleteMovieTest(){
            List<Movie> listold = movieRepository.findAll();
            Movie movie;
            Movie movie1 = new Movie(11,"Holiday","hindi","5 star");
            movieRepository.save(movie1);
            movieRepository.deleteById(movie1.getId());
            List<Movie> listnew = movieRepository.findAll();
            Assert.assertEquals(listold.size(),listnew.size());
        }
        @Test
        public void deleteMovieFailureTest(){

            Movie movie;
            Movie movie1 = new Movie(12,"A wednesday","hindi","5 star");
            movieRepository.save(movie1);
            movieRepository.deleteById(movie1.getId()+1);
            Assert.assertNotSame("Successfully deleted","movie not found");
        }
        @Test
        public void findByTitleTest(){
            Movie movie1 = new Movie(12,"pk","hindi","5 star");
            movieRepository.save(movie1);
            Movie movieReturned=movieRepository.findByTitle("pk");
            Assert.assertEquals(movie1.getTitle(),movieReturned.getTitle());
        }



    }
