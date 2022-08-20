package com.alkemy.challenge.service;

import com.alkemy.challenge.domain.Character;
import com.alkemy.challenge.domain.Image;
import com.alkemy.challenge.repository.CharacterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CharacterService {

    @Autowired
    private CharacterRepository characterRepository;
    @Autowired
    private ImageService imageService;

    @Transactional
    public Character save(Character character, MultipartFile file) throws IOException {
        Image image = imageService.saveImage(file);
        character.setImage(image);
        return characterRepository.save(character);
    }

    public ResponseEntity<Character> deleteById(Long id){
        if (!characterRepository.existsById(id)){
            return ResponseEntity.notFound().build();}
        characterRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    public Character getById(Long id){
        return characterRepository.getById(id);
    }

    public List<Character> findByName(String name){
        return characterRepository.findByName(name);
    }

    public List<Character> findByAge(Integer age){
        return characterRepository.findByAge(age);
    }

    public List<Character> findByMovies(Long id){
        return characterRepository.findByMovies(id);
    }

    public List<Character> getAll(){
        return characterRepository.findAll();
    }
}
