package com.example.controller.authentication.business.profile;

import com.example.controller.business.BusinessController;
import com.example.controller.business.settings.BusinessProfileController;
import com.example.domain.business.entity.business.profile.BusinessName;
import com.example.service.business.service.BusinessService;
import com.example.service.business.service.profile.BusinessProfileService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@ExtendWith(MockitoExtension.class)
public class BusinessProfileControllerTest {
    MockMvc mockMvc;

    @InjectMocks
    BusinessProfileController businessController;

    @Mock
    BusinessProfileService businessProfileService;

    @BeforeEach
    public void setUp() {
        mockMvc = standaloneSetup(businessController)
                .build();
    }

    @Test
    public void whenNameIsUpdatedThenBusinessServiceIsProperlyExecuted() throws Exception {
        mockMvc.perform(
                put("/api/business/1/name")
                        .content("CONTENT")
                        .contentType("plain/text"))
                .andExpect(status().is(200));

        verify(businessProfileService).updateName(1, "CONTENT");
    }

    @Test
    public void whenNameIsGotThenItIsProperlyReturned() throws Exception {
        Mockito.when(businessProfileService.getName(1)).thenReturn(new BusinessName("name"));

        mockMvc.perform(get("/api/business/1/name").accept("plain/text"))
                .andExpect(status().is(200))
                .andExpect(content().contentType("plain/text; charset=UTF-8"))
                .andExpect(content().string("name"));


        verify(businessProfileService).getName(1);
    }

}
