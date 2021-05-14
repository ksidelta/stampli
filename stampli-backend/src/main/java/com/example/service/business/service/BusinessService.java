package com.example.service.business.service;

import java.util.Optional;

public interface BusinessService {
    void createBusiness(Integer userId);

    Optional<BusinessDto> findBusinessByUserId(Integer userId);
}
