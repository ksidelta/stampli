package com.example.modules.business.domain;

import com.example.modules.authentication.domain.user.entity.AbstractUserAggregate;
import org.springframework.stereotype.Service;

@Service
public class UserToOwnerConverter {
    public BusinessAggregate.Owner fromUser(AbstractUserAggregate userEntity) {
        return new BusinessAggregate.Owner(userEntity.getId());
    }
}
