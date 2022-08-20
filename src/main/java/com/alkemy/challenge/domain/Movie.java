package com.alkemy.challenge.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@NoArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
@Entity
@Table(name="movies")
public class Movie implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long movieId;
    @OneToOne
    private Image image;
    private String title;
    @JsonFormat(pattern = "yyyy/MM/dd")
    private Date date;
    private Integer score;

    @ManyToMany(mappedBy = "movies", fetch = FetchType.EAGER)
    private List<Character> characters;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "genre_Id")
    @JsonIgnore
    private Genre genre;

    public Movie(Image image, String title, Date date, Integer score, List<Character> characters, Genre genre) {
        this.image = image;
        this.title = title;
        this.date = date;
        this.score = score;
        this.characters = characters;
        this.genre = genre;
    }
}
