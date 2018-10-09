package com.stackroute.movieservice.service;

import com.stackroute.movieservice.domain.Movie;
import com.stackroute.movieservice.exception.MovieAlreadyExistsException;
import com.stackroute.movieservice.exception.MovieNotFoundException;
import com.stackroute.movieservice.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
@Qualifier("implementation1")
public class MovieServiceImpl implements MovieService{

    private MovieRepository movieRepository;
    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public Movie saveMovie(Movie movie) throws MovieAlreadyExistsException {
        if (movieRepository.existsById(movie.getId())) {
            throw new MovieAlreadyExistsException("movie already exists");
        }

        Movie savedMovie = movieRepository.save(movie);
        if (savedMovie == null) {
            throw new MovieAlreadyExistsException("movie already exists");
        }
        return savedMovie;
    }

//            else
//                throw new MovieAlreadyExistsException();
//        }
//        catch(MovieAlreadyExistsException e)
//        {
//            e.printStackTrace();
//        }
//        return movie;


//    @Override
//    public void viewInfo() {
//        List<Movie> movieList=(List<Movie>)movieRepository.findAll();
//        for(Movie movie:movieList)
//        {
//            System.out.println(movie);
//        }
//
//       // return  (List<Movie>)movieRepository.findAll();
//    }

    @Override
    public Movie updateComments(int id,String comments) throws MovieNotFoundException
    {
           if (movieRepository.existsById(id)) {
               Movie updatedMovie = movieRepository.findById(id).get();
               updatedMovie.setComments(comments);
               Movie updatedSavedMovie = movieRepository.save(updatedMovie);
               return updatedSavedMovie;

           }
           else
               throw new MovieNotFoundException("Movie not found");


    }

    @Override
    public String deleteMovie(int id) throws MovieNotFoundException {
            if (movieRepository.existsById(id)) {
                movieRepository.deleteById(id);
                return "Succesfully deleted";
            } else
                throw new MovieNotFoundException("Movie Not Found");
    }




    @Override
    public List<Movie> getAllMovie() {
        return  (List<Movie>)movieRepository.findAll();
    }

    @Override
    public Movie searchMovieByName(String name) throws MovieNotFoundException{
            if (movieRepository.findByTitle(name) != null) {
                Movie searchedMovie = movieRepository.findByTitle(name);

                return searchedMovie;
            } else
                throw new MovieNotFoundException("Movie Not Found");

    }
}
