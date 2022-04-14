package com.example.modules.business.service;

import java.awt.image.BufferedImage;

public class BusinessDto {
    Integer id;
    String name;

    public BusinessDto() {
    }

    public BusinessDto(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
