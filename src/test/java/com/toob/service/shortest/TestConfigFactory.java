package com.toob.service.shortest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.ComponentScan;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@Slf4j
@TestConfiguration
@ComponentScan(basePackageClasses = {Application.class})
public class TestConfigFactory {}