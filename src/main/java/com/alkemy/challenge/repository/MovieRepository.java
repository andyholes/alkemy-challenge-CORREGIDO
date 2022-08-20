package com.alkemy.challenge.repository;

import com.alkemy.challenge.domain.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    public Movie getById (Long id);

    public Optional<Movie> findById(Long id);

    public List<Movie> findByTitle(String title);

    public List<Movie> findByGenre(String genre);

    public List<Movie> findByOrderByDateAsc();

    public List<Movie> findByOrderByDateDesc();
}
