package com.example.controller.business.settings;

import com.example.controller.business.BusinessController;
import com.example.service.business.service.profile.BusinessProfileService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/api/business/{id}/name")
public class BusinessProfileController {
    BusinessProfileService businessProfileService;

    public BusinessProfileController(BusinessProfileService businessProfileService) {
        this.businessProfileService = businessProfileService;
    }

    @GetMapping(produces = "plain/text;charset=UTF-8")
    public String getBusinessName(@PathVariable Integer id) {
        return businessProfileService.getName(id).getName();
    }

    @PutMapping(consumes = "plain/text;charset=UTF-8")
    public void updateBusinessName(@PathVariable Integer id, @RequestBody String body) {
        businessProfileService.updateName(id, body);
    }

}
