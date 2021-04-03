package com.example.modules.authentication.login;

import com.example.AppConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@ExtendWith(SpringExtension.class)
@SpringJUnitWebConfig({AppConfig.class})
public class LoginControllerTest {
    protected MockMvc mockMvc;

    @Autowired
    protected LoginController loginController;

    @BeforeEach
    public void setUp() {
        mockMvc = standaloneSetup(loginController).build();
    }

    @Test
    public void incorrectLoginReturns404() throws Exception {
        mockMvc.perform(
                post("/api/login/basic")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"incorrect\", \"password\": \"incorrect\"}")
        ).andExpect(status().is(404));
    }
}
