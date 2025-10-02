package dev.jensendev25.groupassignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import dev.jensendev25.groupassignment.junit.Program;

@SpringBootApplication
public class Main {
  public static void main(String[] args) {
    if (args.length == 0) {
      System.out.println("Usage: java -jar groupassignment.jar [1|2]");
      System.out.println("1 = Run Spring Boot Application");
      System.out.println("2 = Run Program");
      return;
    }

    String choice = args[0].trim();

    if ("1".equals(choice)) {
      SpringApplication.run(Main.class, args);
    } else if ("2".equals(choice)) {
      new Program().run();
    } else {
      System.out.println("Invalid argument: " + choice);
      System.out.println("Use 1 for Spring Boot or 2 for Program.");
    }
  }
}
