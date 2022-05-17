package com.doeasy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class Main {
 
  public static void main(String[] args) throws Exception {
    SpringApplication.run(Main.class, args);
  }

}
