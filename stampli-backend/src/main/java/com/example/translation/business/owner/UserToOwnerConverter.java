package com.example.translation.business.owner;

import com.example.domain.context.authentication.user.entity.UserAggregate;
import com.example.domain.context.business.entity.owner.Owner;
import org.springframework.stereotype.Service;

@Service
public class UserToOwnerConverter {
    public Owner fromUser(UserAggregate userEntity) {
        return new Owner(userEntity.getId());
    }
}
