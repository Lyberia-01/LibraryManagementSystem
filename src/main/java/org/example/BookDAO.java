package org.example;
import java.util.List;

public interface BookDAO {
    void addBook(Book book);
    void updateBook(Book book);
    void deleteBook(int bookId);
    List<Book> getAllBooks();
    Book getBookById(int bookId);
    List<Book> searchBooks(String searchTerm);
}