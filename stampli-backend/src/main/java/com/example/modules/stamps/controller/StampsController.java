package com.example.modules.stamps.controller;

import com.example.modules.stamps.service.StampService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/stamps")
public class StampsController {
    StampService stampService;

    public StampsController(StampService stampService) {
        this.stampService = stampService;
    }

    @GetMapping("/{businessId}")
    public StampsInfoDTO getAmountOfMyStamps(Authentication authentication, @PathVariable Integer businessId) {
        final Integer userId = (Integer) authentication.getPrincipal();

        final var quantity = stampService.getNumberOfStamps(userId, businessId);

        return new StampsInfoDTO(quantity);
    }

    @GetMapping("/{businessId}/{userId}")
    public StampsInfoDTO getAmountOfStampsForUser(@PathVariable Integer businessId, @PathVariable Integer userId) {
        final var quantity = stampService.getNumberOfStamps(userId, businessId);

        return new StampsInfoDTO(quantity);
    }
}
