package com.webflux.demo;

import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Slf4j
@EnableTransactionManagement
@SpringBootTest(classes = SpringWebfluxApplication.class)
public class SpringWebfluxApplicationTests {

}
