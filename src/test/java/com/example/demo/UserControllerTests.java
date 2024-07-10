package com.example.demo;
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

    @Test
    public void getUserByIdShouldReturnAUserWithOutPassword() throws Exception {
        this.mockMvc.perform(get("/api/v1/user/1")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("User One"))
                .andExpect(jsonPath("$.password").value(""));
    }

    @Test
    public void getUserByIdShouldReturnNotFound() throws Exception {
        this.mockMvc.perform(get("/api/v1/user/100")).andDo(print()).andExpect(status().isNotFound())
                .andExpect(jsonPath("$").doesNotExist());
    }
    @Test
    public void shouldReturnOnlyClients() throws Exception {
        this.mockMvc.perform(get("/api/v1/user/role/Client")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].userRole").value("Client"))
                .andExpect(jsonPath("$[1].userRole").value("Client"))
                .andExpect(jsonPath("$[0].password").value(""))
                .andExpect(jsonPath("$[1].password").value(""));
    }

    @Test
    public void shouldReturnNotFound() throws Exception {
        this.mockMvc.perform(get("/api/v1/user/role/manager")).andDo(print()).andExpect(status().isNotFound())
                .andExpect(jsonPath("$").doesNotExist());
    }

}
