package com.example.demo.movies.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "\"movie\"")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String title;

    String genre;

    String director;

    Date releaseDate;
}
