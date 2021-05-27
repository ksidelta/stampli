package com.example.controller.business.profile;

import com.example.controller.business.settings.BusinessProfileController;
import com.example.domain.context.business.entity.business.BusinessAggregate;
import com.example.domain.context.business.entity.business.profile.BusinessName;
import com.example.infrastructure.db.hibernate.ImageToBlobConverter;
import com.example.service.business.service.profile.BusinessProfileService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@ExtendWith(MockitoExtension.class)
public class BusinessProfileControllerIntegration {
    MockMvc mockMvc;

    BusinessProfileController businessController;

    @Mock
    BusinessProfileService businessProfileService;

    ImageToBlobConverter imageToBlobConverter = new ImageToBlobConverter();

    @BeforeEach
    public void setUp() {
        businessController = new BusinessProfileController(businessProfileService, imageToBlobConverter);

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

    @Test
    public void whenLogoIsUpdatedThenBusinessServiceIsProperlyExecuted() throws Exception {
        mockMvc.perform(
                put("/api/business/1/logo")
                        .content(getDefaultImageAsBytes())
                        .contentType("image/png"))
                .andExpect(status().is(200));

        verify(businessProfileService).updateLogo(eq(1), argThat(
                (img) -> img.getWidth() == 512 && img.getHeight() == 512)
        );
    }

    @Test
    public void whenLogoIsGotThenBusinessServiceIsProperlyExecuted() throws Exception {
        when(businessProfileService.getLogo(1)).thenReturn(BusinessAggregate.defaultImage());

        mockMvc.perform(
                get("/api/business/1/logo")
                        .content(getDefaultImageAsBytes())
                        .accept("image/png"))
                .andExpect(status().is(200))
                .andExpect(content().bytes(getDefaultImageAsBytes()));

        verify(businessProfileService).getLogo(eq(1));
    }

    static byte[] getDefaultImageAsBytes() {
        return new ImageToBlobConverter().convertToDatabaseColumn(BusinessAggregate.defaultImage());
    }

}
