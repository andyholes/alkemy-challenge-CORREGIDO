package com.alkemy.challenge.domain;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity
@Table(name="alkemy_users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String username;

    private String email;

    private String password;


    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
}
