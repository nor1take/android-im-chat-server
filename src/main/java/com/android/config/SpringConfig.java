package com.android.config;

import org.springframework.context.annotation.*;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan({"com.android.service"})
@PropertySource("classpath:jdbc.properties")
@EnableTransactionManagement
@Import({JdbcConfig.class, MybatisConfig.class})
public class SpringConfig {
}