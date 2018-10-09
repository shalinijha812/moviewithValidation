package com.stackroute.movieservice.controller;

import com.stackroute.movieservice.domain.Movie;
import com.stackroute.movieservice.exception.MovieAlreadyExistsException;
import com.stackroute.movieservice.exception.MovieNotFoundException;
import com.stackroute.movieservice.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/movie/")
public class MovieController {

    private MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping()
    public ResponseEntity<?> saveMovie(@RequestBody Movie movie) {
        ResponseEntity responseEntity;
        try {
            Movie savedMovie = movieService.saveMovie(movie);
            responseEntity = new ResponseEntity<Movie>(savedMovie, HttpStatus.OK);
        }
        catch(MovieAlreadyExistsException e)
        {
            responseEntity=new ResponseEntity<String>(e.getMessage(),HttpStatus.OK);
        }
        return responseEntity;
    }
//    @PostMapping("movie/list")
//    public  ResponseEntity<?> saveMoreMovies(@RequestBody List<Movie> listMovies) {
//        for(int i=0;i<listMovies.size();i++) {
//            Movie savedMovie = movieService.saveMovie(listMovies.get(i));
//        }
//        ResponseEntity responseEntity = new ResponseEntity<List<Movie >>(movieService.getAllMovie(), HttpStatus.OK);
//
//        return responseEntity;
//
//    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateMovie(@PathVariable("id") int id ,@RequestBody String comments) {
        ResponseEntity responseEntity;
        try {

            Movie updatedMovie = movieService.updateComments(id, comments);
            responseEntity = new ResponseEntity<Movie>(updatedMovie, HttpStatus.OK);
        }
        catch(MovieNotFoundException e)
        {
            responseEntity=new ResponseEntity<String>(e.getMessage(),HttpStatus.OK);
        }
        return responseEntity;
    }


//    public boolean deleteMovie(@PathVariable("id") int id)
//    {
//        List<Movie> movieList;
//        movieList=movieService.deleteMovie(id);
//        return true;
//    }
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteMovie(@PathVariable("id") int id) {
//        List<Movie> movieList;
        ResponseEntity responseEntity;

        try {
            String message = movieService.deleteMovie(id);
            responseEntity = new ResponseEntity<String>(message, HttpStatus.OK);
        }
        catch(MovieNotFoundException e)
        {
            responseEntity=new ResponseEntity<String>(e.getMessage(),HttpStatus.OK);
        }
        return responseEntity;
    }
    @GetMapping()
    public ResponseEntity<?> getAllMovie()
    {
        List<Movie> movieList;
        movieList=movieService.getAllMovie();
        ResponseEntity responseEntity=new ResponseEntity<List<Movie>>(movieList,HttpStatus.OK);
        return responseEntity;
    }
    @GetMapping("{title}")
    public ResponseEntity<?>  searchByTitle(@PathVariable("title") String title)
    {
        ResponseEntity responseEntity;
        try{
            Movie searchedMovie=movieService.searchMovieByName(title);
            responseEntity = new ResponseEntity<Movie>(searchedMovie, HttpStatus.OK);

        }
        catch(MovieNotFoundException e)
        {
            responseEntity=new ResponseEntity<String>(e.getMessage(),HttpStatus.OK);
        }

        return responseEntity;

    }


}
