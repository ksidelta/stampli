package com.example.modules.test.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.image.BufferedImage;
import java.util.List;

@RestController
@RequestMapping(path = "/test")
public class ImageUploadTestController {
    @PostMapping(path = "/images", consumes = "application/bson", produces = "application/bson")
    public Images getAndSend(@RequestBody Images images) {
        return images;
    }

    record Images(List<byte[]> images) {
    }
}
