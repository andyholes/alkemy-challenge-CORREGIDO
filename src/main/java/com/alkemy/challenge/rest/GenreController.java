package com.alkemy.challenge.rest;

import com.alkemy.challenge.domain.Genre;
import com.alkemy.challenge.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequestMapping("/genres")
@RestController
public class GenreController {
    @Autowired
    private GenreService genreService;

    @GetMapping
    public List<Genre> findAll(){
        return genreService.findAll();
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Genre create(@RequestPart Genre genre, @RequestPart MultipartFile image) throws IOException {
        return genreService.save(genre, image);
    }
}
