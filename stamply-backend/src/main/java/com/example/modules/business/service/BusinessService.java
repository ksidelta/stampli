package com.example.modules.business.service;

import java.util.Optional;

public interface BusinessService {
    // id of created business
    Integer createBusiness(Integer userId);

    Optional<BusinessDto> findBusinessByUserId(Integer userId);
}
