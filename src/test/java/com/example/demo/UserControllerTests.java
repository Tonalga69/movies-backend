package com.example.demo;


import com.example.demo.user.controllers.UserController;
import com.example.demo.user.entities.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


@SpringBootTest(classes = DemoApplication.class)
@AutoConfigureMockMvc
public class UserControllerTests {


    @Autowired
    private MockMvc mockMvc;



    @Test
    public void getAllUsersShouldReturnAList() throws Exception {
        this.mockMvc.perform(get("/api/v1/user/")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(5));

    }
}
