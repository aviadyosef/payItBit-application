package com.ay.payitbit.cucumber;

import com.ay.payitbit.PayItBitApplicationApp;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;

@CucumberContextConfiguration
@SpringBootTest(classes = PayItBitApplicationApp.class)
@WebAppConfiguration
public class CucumberTestContextConfiguration {}
