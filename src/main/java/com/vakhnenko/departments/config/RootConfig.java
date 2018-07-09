package com.vakhnenko.departments.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"com.vakhnenko.departments.*"})
public class RootConfig {
}
