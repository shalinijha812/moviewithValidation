package com.stackroute.movieservice;

import com.stackroute.movieservice.domain.Movie;
import com.stackroute.movieservice.repository.MovieRepository;
import com.stackroute.movieservice.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

@SpringBootApplication
public class MovieServiceApplication implements  ApplicationListener<ContextRefreshedEvent>, CommandLineRunner {

	MovieRepository movieRepository;

	@Autowired
	public MovieServiceApplication(MovieRepository movieRepository) {
		this.movieRepository = movieRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		Movie movie = new Movie(4, "Titanic", "English", "Ultimate movie");
		movieRepository.save(movie);
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
		Movie movie = new Movie(1, "3idiots", "hindi", "best movie");
		movieRepository.save(movie);
		movie = new Movie(2, "Guru", "hindi", "avg movie");
		movieRepository.save(movie);
		movie = new Movie(3, "Dangal", "hindi", "awesome movie");
		movieRepository.save(movie);
	}
	public static void main(String[] args) {
		SpringApplication.run(MovieServiceApplication.class, args);
	}
}

//	"id": 1,
//			"title": "3idiots",
//			"language": "hindi",
//			"comments": "best movie"
//},
//		{
//		"id": 2,
//		"title": "Guru",
//		"language": "hindi",
//		"comments": "avg movie"
//		},
//		{
//		"id": 3,
//		"title": "Devdas",
//		"language": "hindi",
//		"comments": "awesome movie"
//		}
//		]



