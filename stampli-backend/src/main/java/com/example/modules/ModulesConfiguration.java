package com.example.modules;

import com.example.modules.business.BusinessConfiguration;
import com.example.modules.challenge.ChallengeConfiguration;
import org.springframework.context.annotation.Import;

@Import({BusinessConfiguration.class, ChallengeConfiguration.class})
public class ModulesConfiguration {
}
