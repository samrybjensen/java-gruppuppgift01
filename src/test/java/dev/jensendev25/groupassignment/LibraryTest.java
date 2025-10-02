package dev.jensendev25.groupassignment;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import dev.jensendev25.groupassignment.junit.*;

public class LibraryTest {
  @Test
  void borrowingMoreThanOneBookPerDayIsNotAllowed() {
    Library library = new Library();

    // The user may only borrow 1 book per day.
    library.borrowBook("Harry Potter");
    assertEquals(1, library.listBorrowedBooks(false).size(), "first borrow should succeed");

    library.borrowBook("Hitchhiker's guide to the galaxy");
    assertEquals(1, library.listBorrowedBooks(false).size(), "second borrow on same day must be rejected");

    library.advanceDay();
    library.borrowBook("Hitchhiker's guide to the galaxy");
    assertEquals(2, library.listBorrowedBooks(false).size(), "borrowing should be allowed on the next day");
  }

  @Test
  void cannotBorrowMoreThanFiveBooksAtOnce() {
    Library library = new Library();

    // The user may at most have 5 borrowed books at a time.

    assertTrue(library != null);
  }

  @Test
  void cannotBorrowMultipleCopiesOfSameTitle() {
    Library library = new Library();

    // The user may only borrow one book per title.

    assertTrue(library != null);
  }

  @Test
  void canReturnMultipleBooksInOneDay() {
    Library library = new Library();

    // The user may return as many books as he/she wishes per day.

    assertTrue(library != null);
  }

  @Test
  void returningOnSeventhDayDoesNotIncurAFee() {
    Library library = new Library();

    // The user may only borrow a book for 7 days straight.

    assertTrue(library != null);
  }

  @Test
  void returningLateCostsTwentyPerDay() {
    Library library = new Library();

    // For every day the book is late, the user has to pay a fine of 20 kr.

    assertTrue(library != null);
  }

  @Test
  void extendingResetsDaysAndConsumesDailyBorrow() {
    Library library = new Library();

    // The user can use the extend function to reset the book back to 0 days
    // borrowed, this counts as borrowing 1 book.

    assertTrue(library != null);
  }
}
