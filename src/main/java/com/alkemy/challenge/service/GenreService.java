package com.alkemy.challenge.service;

import com.alkemy.challenge.domain.Genre;
import com.alkemy.challenge.domain.Image;
import com.alkemy.challenge.domain.Movie;
import com.alkemy.challenge.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@Service
public class GenreService {

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private ImageService imageService;

    @Transactional
    public Genre save(Genre genre, MultipartFile file) throws IOException {
        Image image = imageService.saveImage(file);
        genre.setImage(image);
        return genreRepository.save(genre);
    }

    public List<Genre> findAll(){
        return genreRepository.findAll();
    }

    public List<Movie> getMoviesByGenre(Long genreId) {
        Genre genre = genreRepository.getById(genreId);
        return genre.getMovies();
    }
}
