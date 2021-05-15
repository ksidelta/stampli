package com.example.controller.business.settings;

import com.example.service.business.service.profile.BusinessProfileService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/business/{id}/name")
public class BusinessProfileController {
    BusinessProfileService businessProfileService;

    public BusinessProfileController(BusinessProfileService businessProfileService) {
        this.businessProfileService = businessProfileService;
    }

    @GetMapping
    public String getBusinessName(@PathVariable Integer id) {
        return businessProfileService.getName(id).getName();
    }

    @PutMapping
    public void updateBusinessName(@PathVariable Integer id, @RequestBody String body) {
        businessProfileService.updateName(id, body);
    }

}
