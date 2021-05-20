package com.example.translation.business.owner;

import com.example.domain.context.authentication.user.entity.UserEntity;
import com.example.domain.context.business.entity.owner.Owner;
import org.springframework.stereotype.Service;

@Service
public class UserToOwnerConverter {
    public Owner fromUser(UserEntity userEntity) {
        return new Owner(userEntity.getId());
    }
}
