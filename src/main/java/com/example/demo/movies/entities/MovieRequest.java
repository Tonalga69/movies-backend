package com.example.demo.movies.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
@Data
@AllArgsConstructor
public class MovieRequest {

    private String title;

    private String genre;

    private String director;

    private Date releaseDate;
}
