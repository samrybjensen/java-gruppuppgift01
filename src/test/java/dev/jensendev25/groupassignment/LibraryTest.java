package dev.jensendev25.groupassignment;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import dev.jensendev25.groupassignment.junit.*;

public class LibraryTest {

    @Test
    void borrowingMoreThanOneBookPerDayIsNotAllowed() {
        Library library = new Library();

        // Borrow first book
        library.borrowBook("Harry Potter");
        assertEquals(1, library.listBorrowedBooks(false).size(), "first borrow should succeed");

        // Try borrowing a second book the same day
        library.borrowBook("Hitchhiker's guide to the galaxy");
        assertEquals(1, library.listBorrowedBooks(false).size(), "second borrow on same day must be rejected");

        // Advance to the next day and try borrowing again
        library.advanceDay();
        library.borrowBook("Hitchhiker's guide to the galaxy");
        assertEquals(2, library.listBorrowedBooks(false).size(), "borrowing should be allowed on the next day");
    }

    @Test
    void cannotBorrowMoreThanFiveBooksAtOnce() {
        Library library = new Library();

        String[] titles = {
            "Harry Potter",
            "Hitchhiker's guide to the galaxy",
            "It ends with us",
            "Ondskan",
            "Tempelriddaren"
        };

        // Borrow up to 5 books (one per day)
        for (int i = 0; i < titles.length; i++) {
            library.borrowBook(titles[i]);
            assertEquals(i + 1, library.listBorrowedBooks(false).size(), "borrowing a new title on a new day should succeed");
            library.advanceDay();
        }

        int borrowedBeforeLimitAttempt = library.listBorrowedBooks(false).size();

        // Attempt to borrow one more book (should fail)
        library.borrowBook("Harry Potter");
        assertEquals(borrowedBeforeLimitAttempt, library.listBorrowedBooks(false).size(),
            "library must reject attempts to exceed 5 simultaneously borrowed books");
    }

    @Test
    void cannotBorrowMultipleCopiesOfSameTitle() {
        Library library = new Library();

        // Borrow one copy of a title
        library.borrowBook("Harry Potter");
        assertEquals(1, library.listBorrowedBooks(false).size(), "first borrow should succeed");

        // Try borrowing the same title again
        library.borrowBook("Harry Potter");
        assertEquals(1, library.listBorrowedBooks(false).size(), "should not be able to borrow the same title twice");
    }

    @Test
    void canReturnMultipleBooksInOneDay() {
        Library library = new Library();

        // Borrow two books on different days
        library.borrowBook("Harry Potter");
        library.advanceDay();
        library.borrowBook("Hitchhiker's guide to the galaxy");

        // Return both books on the same day
        library.returnBook("Harry Potter");
        library.returnBook("Hitchhiker's guide to the galaxy");

        assertEquals(0, library.listBorrowedBooks(false).size(), "should be able to return multiple books in one day");
    }

    @Test
    void returningOnSeventhDayDoesNotIncurAFee() {
        Library library = new Library();

        library.borrowBook("Harry Potter");

        // Advance 6 days (7th day is today)
        for (int i = 0; i < 6; i++) {
            library.advanceDay();
        }

        int fee = library.returnBook("Harry Potter");
        assertEquals(0, fee, "returning on the seventh day should not incur a fee");
    }

    @Test
    void returningLateCostsTwentyPerDay() {
        Library library = new Library();

        library.borrowBook("Harry Potter");

        // Advance 10 days (3 days late)
        for (int i = 0; i < 10; i++) {
            library.advanceDay();
        }

        int fee = library.returnBook("Harry Potter");
        assertEquals(3 * 20, fee, "should charge 20 kr per day for each late day");
    }

    @Test
    void extendingResetsDaysAndConsumesDailyBorrow() {
        Library library = new Library();

        library.borrowBook("Harry Potter");

        // Extend the borrowed book (counts as daily borrow)
        library.extendTime("Harry Potter");

        // Check that days borrowed is reset
        assertEquals(0, library.listBorrowedBooks(false).get(0).getDaysBorrowed(),
            "extending should reset days borrowed to 0");

        // Try borrowing another book the same day (should fail)
        library.borrowBook("Hitchhiker's guide to the galaxy");
        assertEquals(1, library.listBorrowedBooks(false).size(), "extending counts as borrowing for the day");
    }
}
