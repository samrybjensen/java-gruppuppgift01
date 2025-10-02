package dev.jensendev25.groupassignment.springboot;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
public class BookController {
  @Autowired
  private BookService bookService;

  @PostMapping()
  public Book addBook(@RequestBody Book book) {
    return bookService.addBook(book.getName(), book.getGenre());
  }

  @GetMapping
  public ArrayList<Book> getBooks() {
    return bookService.getAllBooks();
  }

}
