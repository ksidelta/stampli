package com.example.modules;

import com.example.modules.business.BusinessConfiguration;
import org.springframework.context.annotation.Import;

@Import(BusinessConfiguration.class)
public class ModulesConfiguration {
}
