package com.alkemy.challenge.rest;

import com.alkemy.challenge.domain.Character;
import com.alkemy.challenge.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequestMapping("/characters")
@RestController
public class CharacterController {

    @Autowired
    private CharacterService characterService;

    @GetMapping
    public List<Character> getAll(){

        return characterService.getAll();
    }

    @GetMapping(params = "name")
    public List<Character> findByName(@RequestParam(required = false) String name){

        return characterService.findByName(name);
    }

    @GetMapping(params = "age")
    public List<Character> findByAge(@RequestParam(required = false) Integer age){

        return characterService.findByAge(age);
    }

    @GetMapping(params = "movies")
    public List<Character> findByMovieId(@RequestParam(required = false) Long id){

        return characterService.findByMovies(id);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Character create(@RequestPart Character character, @RequestPart MultipartFile image) throws IOException {

        return characterService.save(character, image);
    }

    @PutMapping(params = "id", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Character update(@RequestParam("id") Long id, @RequestPart Character character, @RequestPart MultipartFile image) throws IOException {

        return characterService.save(character, image);
    }

    @DeleteMapping(params = "id")
    public ResponseEntity<Character> deleteById(@RequestParam("id") Long id){

        return characterService.deleteById(id);
    }
}
