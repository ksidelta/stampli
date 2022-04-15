package com.example.modules;

import com.example.modules.authentication.AuthenticationConfiguration;
import com.example.modules.business.BusinessConfiguration;
import com.example.modules.challenge.ChallengeConfiguration;
import com.example.modules.stamps.StampsConfiguration;
import org.springframework.context.annotation.Import;

@Import({
        BusinessConfiguration.class,
        ChallengeConfiguration.class,
        AuthenticationConfiguration.class,
        StampsConfiguration.class,
})
public class ModulesConfiguration {
}
