package com.alkemy.challenge.repository;

import com.alkemy.challenge.domain.Character;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CharacterRepository extends JpaRepository<Character, Long> {

    public Character getById(Long id);

    public List<Character> findAll();
    public Optional<Character> findById(Long id);

    public List<Character> findByName(String name);

    public List<Character> findByAge(Integer age);

    public List<Character> findByMovies(Long id);
}
