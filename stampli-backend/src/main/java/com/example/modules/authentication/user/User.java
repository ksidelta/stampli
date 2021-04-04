package com.example.modules.authentication.user;

import java.util.List;

public interface User {
    String createToken();


    Integer getId();

    List<String> getRoles();
}
