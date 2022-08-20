package com.alkemy.challenge.rest;

import com.alkemy.challenge.domain.Movie;
import com.alkemy.challenge.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequestMapping("/movies")
@RestController
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping()
    public List<Movie> findAllMovies(){
        return movieService.findAll();
    }

    @GetMapping(params = "name")
    public List<Movie> findMoviesByName(@RequestParam("name") String name){
        return movieService.findByName(name);
    }

    @GetMapping(params = "genre")
    public List<Movie> findMoviesByGenre(@RequestParam("genre") String genre){
        return movieService.findByGenre(genre);
    }

    @GetMapping(params = "order")
    public List<Movie> findMoviesByOrder(@RequestParam("order") String order){
        return movieService.findByOrder(order);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Movie create(@RequestPart Movie movie, @RequestPart MultipartFile image) throws IOException {
        return movieService.save(movie, image);
    }

    @PutMapping(params = "id", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Movie update(@RequestParam("id") Long id, @RequestPart Movie movie, @RequestPart MultipartFile image) throws IOException {

        return movieService.save(movie, image);
    }

    @DeleteMapping(params = "id")
    public ResponseEntity<Movie> deleteById(@RequestParam("id") Long id){

        return movieService.deleteById(id);
    }
}
