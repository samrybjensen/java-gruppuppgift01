package dev.jensendev25.groupassignment.springboot;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class Book {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "genre", nullable = false)
  private String genre;

  public Book() {
  }

  public Book(String name, String genre) {
    this.name = name;
    this.genre = genre;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setGenre(String genre) {
    this.genre = genre;
  }

  public String getGenre() {
    return genre;
  }

  public int getId() {
    return id;
  }
}
