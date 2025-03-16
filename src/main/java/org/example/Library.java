package org.example;
import java.util.*;

public class Library {
    private Map<Integer, Integer> borrowedBooks = new HashMap<>();

    public void borrowBook(int bookId, int memberId) {
        borrowedBooks.put(bookId, memberId);
    }

    public void returnBook(int bookId) {
        borrowedBooks.remove(bookId);
    }

    public Map<Integer, Integer> getBorrowedBooks() {
        return borrowedBooks;
    }

    public static List<Book> searchBooksInMemory(List<Book> books, String searchTerm) {
        List<Book> results = new ArrayList<>();
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(searchTerm.toLowerCase()) ||
                    book.getAuthor().toLowerCase().contains(searchTerm.toLowerCase())) {
                results.add(book);
            }
        }
        return results;
    }

    public static void sortBooksByTitle(List<Book> books) {
        Collections.sort(books, Comparator.comparing(Book::getTitle));
    }

    public static void sortBooksByGenre(List<Book> books) {
        Collections.sort(books, Comparator.comparing(Book::getGenre));
    }

}