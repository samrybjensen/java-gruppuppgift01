package dev.jensendev25.groupassignment.springboot;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

  @Autowired
  private BookRepository bookRepository;

  public Book addBook(String name, String genre) {
    Book book = new Book(name, genre);
    return bookRepository.save(book);
  }

  public ArrayList<Book> getAllBooks() {
    return new ArrayList<Book>(bookRepository.findAll());
  }
}
