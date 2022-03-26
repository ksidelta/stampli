package com.example.translation.business.owner;

import com.example.modules.authentication.domain.user.entity.AbstractUserAggregate;
import com.example.modules.business.domain.Owner;
import org.springframework.stereotype.Service;

@Service
public class UserToOwnerConverter {
    public Owner fromUser(AbstractUserAggregate userEntity) {
        return new Owner(userEntity.getId());
    }
}
