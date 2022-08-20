package com.alkemy.challenge.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "genres")
public class Genre {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long genreId;
    private String name;
    @OneToOne
    private Image image;
    @OneToMany(mappedBy = "genre", fetch = FetchType.LAZY)
    private List<Movie> movies;

    public Genre(String name, Image image, List<Movie> movies) {
        this.name = name;
        this.image = image;
        this.movies = movies;
    }
}
