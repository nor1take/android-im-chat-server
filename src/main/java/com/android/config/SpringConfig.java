package com.android.config;

import org.springframework.context.annotation.*;

@Configuration
@ComponentScan({"com.android.service"})
@PropertySource("classpath:jdbc.properties")
@Import({JdbcConfig.class, MybatisConfig.class})
public class SpringConfig {
}