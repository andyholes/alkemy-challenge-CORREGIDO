package com.alkemy.challenge.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "images")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imageId;
    private String name;
    private String mime;
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] content;
}
