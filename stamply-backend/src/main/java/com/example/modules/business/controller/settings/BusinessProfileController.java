package com.example.modules.business.controller.settings;

import com.example.infrastructure.db.hibernate.ImageToBlobConverter;
import com.example.modules.business.service.profile.BusinessProfileService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/api/business/{id}")
public class BusinessProfileController {
    final BusinessProfileService businessProfileService;
    final ImageToBlobConverter imageToBlobConverter;

    public BusinessProfileController(BusinessProfileService businessProfileService, ImageToBlobConverter imageToBlobConverter) {
        this.businessProfileService = businessProfileService;
        this.imageToBlobConverter = imageToBlobConverter;
    }

    @GetMapping(path = "/name", produces = "plain/text;charset=UTF-8")
    public String getBusinessName(@PathVariable Integer id) {
        return businessProfileService.getName(id).getName();
    }

    @PutMapping(path = "/name", consumes = "plain/text;charset=UTF-8")
    public void updateBusinessName(@PathVariable Integer id, @RequestBody String body) {
        businessProfileService.updateName(id, body);
    }

    @PutMapping(path = "/logo", consumes = "image/png")
    public void updateBusinessLogo(@PathVariable Integer id, @RequestBody byte[] body) {
        businessProfileService.updateLogo(id, imageToBlobConverter.convertToEntityAttribute(body));
    }

    @GetMapping(path = "/logo", produces = "image/png")
    public byte[] getBusinessLogo(@PathVariable Integer id) {
        return imageToBlobConverter.convertToDatabaseColumn(businessProfileService.getLogo(id));
    }

    @PutMapping(path = "/offers", consumes = "image/png")
    public void updateOffers(@PathVariable Integer id, @RequestBody UpdateOffersDTO offers) {
        businessProfileService.updateOffers(id, offers.offers().stream().map(imageToBlobConverter::convertToEntityAttribute).collect(toList()));
    }

    @GetMapping(path = "/offers")
    public RetrieveOffersDTO getOffers(@PathVariable Integer id) {
        return new RetrieveOffersDTO(businessProfileService.getOffers(id).stream().map(imageToBlobConverter::convertToDatabaseColumn).collect(Collectors.toList()));
    }

    record UpdateOffersDTO(List<byte[]> offers) {
    }

    record RetrieveOffersDTO(List<byte[]> offers) {
    }

}
