package me.rogerioferreira.lavajato.application;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Seeder implements CommandLineRunner {

  @Override
  public void run(String... args) throws Exception {

    System.out.println("Seeders executados");
  }

}
