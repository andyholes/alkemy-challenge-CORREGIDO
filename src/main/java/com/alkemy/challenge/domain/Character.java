package com.alkemy.challenge.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
@Entity
@Table (name = "characters")
public class Character implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long characterId;
    @OneToOne
    private Image image;
    private String name;
    private Integer age;
    private Float weight;
    private String history;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "character_movie",
            joinColumns = @JoinColumn(name = "character_id"),
            inverseJoinColumns = @JoinColumn(name = "movies_id"))
    private List<Movie> movies;

    public Character(Image image, String name, Integer age, Float weight, String history, List<Movie> movies) {
        this.image = image;
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.history = history;
        this.movies = movies;
    }
}
