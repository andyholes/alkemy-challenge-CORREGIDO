package com.alkemy.challenge.service;

import com.alkemy.challenge.domain.Image;
import com.alkemy.challenge.domain.Movie;
import com.alkemy.challenge.repository.CharacterRepository;
import com.alkemy.challenge.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private CharacterRepository characterRepository;

    @Autowired
    private ImageService imageService;

    @Transactional
    public Movie save(Movie movie, MultipartFile file) throws IOException {
        Image image = imageService.saveImage(file);
        movie.setImage(image);
        return movieRepository.save(movie);
    }

    public ResponseEntity<Movie> deleteById(Long id){
        if (!movieRepository.existsById(id)){
            return ResponseEntity.notFound().build();}
        movieRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    public Optional<Movie> findById(Long id){
        return movieRepository.findById(id);
    }

    public List<Movie> findAll(){

        return movieRepository.findAll();
    }

    public List<Movie> findByName(String title){

        return movieRepository.findByTitle(title);
    }

    public List<Movie> findByGenre(String genre){

        return movieRepository.findByGenre(genre);
    }

    public List<Movie> findByOrder(String order){
        if (Objects.equals(order, "ASC")){
            return movieRepository.findByOrderByDateAsc();
        } else if (Objects.equals(order, "DESC")) {
            return movieRepository.findByOrderByDateDesc();
        }
        return movieRepository.findAll();
    }

    public List<Movie> findByOrderByDateAsc(){

        return movieRepository.findByOrderByDateAsc();
    }

    public List<Movie> findByOrderByDateDesc(){

        return movieRepository.findByOrderByDateDesc();
    }
}
