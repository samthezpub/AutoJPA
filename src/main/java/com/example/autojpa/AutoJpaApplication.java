package com.example.autojpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class AutoJpaApplication {

    public static void main(String[] args) {
       ApplicationContext context =  SpringApplication.run(AutoJpaApplication.class, args);
       Dispatch dispatch = new Dispatch(context);

       dispatch.findRequestAlgorithm();

    }

}
