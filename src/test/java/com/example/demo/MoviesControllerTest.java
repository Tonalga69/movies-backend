package com.example.demo;

import com.example.demo.movies.entities.Movie;
import com.example.demo.movies.entities.MovieRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class MoviesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getAllMoviesShouldReturnMovies() throws Exception {
        mockMvc.perform(get("/api/v1/movie/all"))
                .andDo(print())
                .andExpect(jsonPath("$.length()").value(4))
                .andExpect(status().isOk());
    }


    @Test
    public void getMovieByIdShouldReturnMovie() throws Exception {
        mockMvc.perform(get("/api/v1/movie/2"))
                .andDo(print())
                .andExpect(jsonPath("$.id").value(2))
                .andExpect(jsonPath("$.title").value("Movie Two"))
                .andExpect(status().isOk());
    }

    @Test
    public void addMovieShouldReturnMovie() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(new MovieRequest("Movie Five", "Action", "Director Five", new Date()));
        mockMvc.perform(post("/api/v1/movie/")
                        .contentType("application/json")
                        .content(json))
                .andDo(print())
                .andExpect(jsonPath("$.title").value("Movie Five"))
                .andExpect(status().isOk());
    }

    @Test
    public void updateMovieShouldReturnMovie() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(new Movie(1L, "Movie changed", "Action", "Director One", new Date()));
        mockMvc.perform(post("/api/v1/movie/")
                        .contentType("application/json")
                        .content(json))
                .andDo(print())
                .andExpect(jsonPath("$.title").value("Movie changed"))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteMovieShouldReturnMessage() throws Exception {
        mockMvc.perform(post("/api/v1/movie/3"))
                .andDo(print())
                .andExpect(jsonPath("$").value("Movie deleted"))
                .andExpect(status().isOk());
        mockMvc.perform(get("/api/v1/movie/3"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }


}
