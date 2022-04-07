package com.example.modules.business.controller;

import com.example.modules.business.service.BusinessDto;
import com.example.modules.business.service.BusinessService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/business/")
public class BusinessController {
    BusinessService businessService;

    public BusinessController(BusinessService businessService) {
        this.businessService = businessService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createBusinessForCurrentUser(Authentication authentication) {
        businessService.createBusiness((Integer) authentication.getPrincipal());
    }

    @GetMapping
    public ResponseEntity<BusinessDto> getCurrentUserBusiness(Authentication authentication) {
        return ResponseEntity.of(businessService.findBusinessByUserId((Integer) authentication.getPrincipal()));
    }
}
