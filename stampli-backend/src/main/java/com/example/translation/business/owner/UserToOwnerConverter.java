package com.example.translation.business.owner;

import com.example.domain.authentication.user.entity.UserEntity;
import com.example.domain.business.entity.owner.Owner;
import org.springframework.stereotype.Service;

@Service
public class UserToOwnerConverter {
    public Owner fromUser(UserEntity userEntity) {
        return new Owner(userEntity.getId());
    }
}
