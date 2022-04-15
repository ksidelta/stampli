package com.example.modules.test.controller;

import com.example.BaseTestConfiguration;
import com.example.infrastructure.bson.BsonMessageConverter;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.undercouch.bson4jackson.BsonFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayOutputStream;
import java.util.List;

import static com.example.controller.business.profile.BusinessProfileControllerIntegration.getDefaultLogoImageAsBytes;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@ExtendWith(SpringExtension.class)
@SpringJUnitWebConfig({BaseTestConfiguration.class})
@Transactional
public class BsonImagesE2E {

    protected MockMvc mockMvc;

    protected ObjectMapper objectMapper = new ObjectMapper(new BsonFactory());

    @Autowired
    protected ImageUploadTestController imageUploadTestController;

    @BeforeEach
    public void setUp() {
        mockMvc = standaloneSetup(imageUploadTestController)
                .setMessageConverters(new BsonMessageConverter())
                .build();
    }

    @Test
    public void whenLogoIsUpdatedThenBusinessServiceIsProperlyExecuted() throws Exception {
        var images = new ImageUploadTestController.Images(List.of(getDefaultLogoImageAsBytes(), getDefaultLogoImageAsBytes()));
        var arr = new ByteArrayOutputStream(1000000);
        objectMapper.createGenerator(arr).writeObject(images);

        mockMvc.perform(
                        post("/test/images")
                                .content(arr.toByteArray())
                                .contentType("application/bson")
                                .accept("application/bson"))
                .andExpect(status().is(200));
    }
}
