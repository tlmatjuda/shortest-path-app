package com.toob.service.shortest;

import com.toob.service.shortest.service.CalculatorService;
import com.toob.service.shortest.service.SupportDataFileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@Slf4j
@TestConfiguration
@ComponentScan(basePackageClasses = {Application.class})
public class BaseTestConfiguration {}