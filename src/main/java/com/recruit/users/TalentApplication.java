package com.recruit.users;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
 * Main class for Talent Application
 */
@SpringBootApplication
public class TalentApplication {

  public static void main(String[] args) {
    SpringApplication.run(TalentApplication.class, args);
    System.out.println("Application started successfully on port 8080");
  }
}
