package org.example;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class LibraryApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BookDAO bookDAO = new BookDAOImpl();
        MemberDAO memberDAO = new MemberDAOImpl();
        BorrowingDAO borrowingDAO = new BorrowingDAOImpl();
        Library library = new Library(); // for in memory collections.

        while (true) {
            System.out.println("\nLibrary Management System");
            System.out.println("1. Book Management");
            System.out.println("2. Member Management");
            System.out.println("3. Borrowing Management");
            System.out.println("4. Reports");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    bookManagementMenu(scanner, bookDAO, library);
                    break;
                case 2:
                    memberManagementMenu(scanner, memberDAO);
                    break;
                case 3:
                    borrowingManagementMenu(scanner, borrowingDAO, bookDAO, memberDAO, library);
                    break;
                case 4:
                    reportsMenu(scanner, bookDAO, memberDAO);
                    break;
                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void bookManagementMenu(Scanner scanner, BookDAO bookDAO, Library library) {
        while (true) {
            System.out.println("\nBook Management");
            System.out.println("1. Add Book");
            System.out.println("2. Update Book");
            System.out.println("3. Delete Book");
            System.out.println("4. Search Books");
            System.out.println("5. Display All Books");
            System.out.println("6. Sort Books By Title");
            System.out.println("7. Sort Books By Genre");
            System.out.println("8. Back to Main Menu");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addBook(scanner, bookDAO);
                    break;
                case 2:
                    updateBook(scanner, bookDAO);
                    break;
                case 3:
                    deleteBook(scanner, bookDAO);
                    break;
                case 4:
                    searchBooks(scanner, bookDAO, library);
                    break;
                case 5:
                    displayAllBooks(bookDAO);
                    break;
                case 6:
                    sortBooksByTitle(bookDAO, library);
                    break;
                case 7:
                    sortBooksByGenre(bookDAO, library);
                    break;
                case 8:
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void addBook(Scanner scanner, BookDAO bookDAO){
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        System.out.print("Enter author: ");
        String author = scanner.nextLine();
        System.out.print("Enter genre: ");
        String genre = scanner.nextLine();
        System.out.print("Enter available copies: ");
        int copies = scanner.nextInt();
        scanner.nextLine();

        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        book.setGenre(genre);
        book.setAvailableCopies(copies);

        bookDAO.addBook(book);
        Logger.log("Book added: " + title);
    }

    private static void memberManagementMenu(Scanner scanner, MemberDAO memberDAO) {
        while (true) {
            System.out.println("\nMember Management");
            System.out.println("1. Add Member");
            System.out.println("2. Update Member");
            System.out.println("3. Delete Member");
            System.out.println("4. Display All Members");
            System.out.println("5. Back to Main Menu");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addMember(scanner, memberDAO);
                    break;
                case 2:
                    updateMember(scanner, memberDAO);
                    break;
                case 3:
                    deleteMember(scanner, memberDAO);
                    break;
                case 4:
                    displayAllMembers(memberDAO);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void addMember(Scanner scanner, MemberDAO memberDAO) {
        System.out.print("Enter member name: ");
        String name = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter phone: ");
        String phone = scanner.nextLine();

        Member member = new Member();
        member.setName(name);
        member.setEmail(email);
        member.setPhone(phone);

        memberDAO.addMember(member);
        Logger.log("Member added: " + name);
    }

    private static void updateMember(Scanner scanner, MemberDAO memberDAO) {
        System.out.print("Enter member ID to update: ");
        int memberId = scanner.nextInt();
        scanner.nextLine();

        Member member = memberDAO.getMemberById(memberId);
        if (member == null) {
            System.out.println("Member not found.");
            return;
        }

        System.out.print("Enter new name: ");
        String name = scanner.nextLine();
        System.out.print("Enter new email: ");
        String email = scanner.nextLine();
        System.out.print("Enter new phone: ");
        String phone = scanner.nextLine();

        member.setName(name);
        member.setEmail(email);
        member.setPhone(phone);

        memberDAO.updateMember(member);
        Logger.log("Member updated: " + name);
    }

    private static void deleteMember(Scanner scanner, MemberDAO memberDAO) {
        System.out.print("Enter member ID to delete: ");
        int memberId = scanner.nextInt();
        scanner.nextLine();

        memberDAO.deleteMember(memberId);
        Logger.log("Member deleted: " + memberId);
    }

    private static void displayAllMembers(MemberDAO memberDAO) {
        List<Member> members = memberDAO.getAllMembers();
        for (Member member : members) {
            System.out.println(member.getMemberId() + ", " + member.getName() + ", " + member.getEmail() + ", " + member.getPhone());
        }
    }

    private static void borrowingManagementMenu(Scanner scanner, BorrowingDAO borrowingDAO, BookDAO bookDAO, MemberDAO memberDAO, Library library) {
        while (true) {
            System.out.println("\nBorrowing Management");
            System.out.println("1. Borrow Book");
            System.out.println("2. Return Book");
            System.out.println("3. Display All Borrowing Records");
            System.out.println("4. Back to Main Menu");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    borrowBook(scanner, borrowingDAO, bookDAO, memberDAO, library);
                    break;
                case 2:
                    returnBook(scanner, borrowingDAO, bookDAO, library);
                    break;
                case 3:
                    displayAllBorrowingRecords(borrowingDAO);
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void borrowBook(Scanner scanner, BorrowingDAO borrowingDAO, BookDAO bookDAO, MemberDAO memberDAO, Library library) {
        System.out.print("Enter book ID: ");
        int bookId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter member ID: ");
        int memberId = scanner.nextInt();
        scanner.nextLine();

        Book book = bookDAO.getBookById(bookId);
        Member member = memberDAO.getMemberById(memberId);

        if (book == null || member == null) {
            System.out.println("Book or member not found.");
            return;
        }

        if (book.getAvailableCopies() <= 0) {
            System.out.println("Book is not available.");
            return;
        }

        book.setAvailableCopies(book.getAvailableCopies() - 1);
        bookDAO.updateBook(book);

        BorrowingRecord record = new BorrowingRecord();
        record.setBookId(bookId);
        record.setMemberId(memberId);
        record.setBorrowDate(LocalDate.now());

        borrowingDAO.addBorrowingRecord(record);
        library.borrowBook(bookId, memberId);
        Logger.log("Book borrowed: " + book.getTitle() + " by " + member.getName());
    }

    private static void returnBook(Scanner scanner, BorrowingDAO borrowingDAO, BookDAO bookDAO, Library library) {
        System.out.print("Enter book ID to return: ");
        int bookId = scanner.nextInt();
        scanner.nextLine();

        List<BorrowingRecord> records = borrowingDAO.getAllBorrowingRecords();
        BorrowingRecord recordToUpdate = null;

        for (BorrowingRecord record : records) {
            if (record.getBookId() == bookId && record.getReturnDate() == null) {
                recordToUpdate = record;
                break;
            }
        }

        if (recordToUpdate == null) {
            System.out.println("Borrowing record not found.");
            return;
        }

        recordToUpdate.setReturnDate(LocalDate.now());
        borrowingDAO.updateBorrowingRecord(recordToUpdate);

        Book book = bookDAO.getBookById(bookId);
        book.setAvailableCopies(book.getAvailableCopies() + 1);
        bookDAO.updateBook(book);
        library.returnBook(bookId);
        Logger.log("Book returned: " + book.getTitle());
    }

    private static void displayAllBorrowingRecords(BorrowingDAO borrowingDAO) {
        List<BorrowingRecord> records = borrowingDAO.getAllBorrowingRecords();
        for (BorrowingRecord record : records) {
            System.out.println(record.getRecordId() + ", " + record.getBookId() + ", " + record.getMemberId() + ", " + record.getBorrowDate() + ", " + record.getReturnDate());
        }
    }

    private static void reportsMenu(Scanner scanner, BookDAO bookDAO, MemberDAO memberDAO) {
        while (true) {
            System.out.println("\nReports");
            System.out.println("1. Export Books to CSV");
            System.out.println("2. Export Members to CSV");
            System.out.println("3. Back to Main Menu");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    exportBooksToCSV(bookDAO);
                    break;
                case 2:
                    exportMembersToCSV(memberDAO);
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void exportBooksToCSV(BookDAO bookDAO) {
        List<Book> books = bookDAO.getAllBooks();
        CSVExporter.exportBooksToCSV(books, "books.csv");
        Logger.log("Books exported to CSV.");
    }

    private static void exportMembersToCSV(MemberDAO memberDAO) {
        List<Member> members = memberDAO.getAllMembers();
        CSVExporter.exportMembersToCSV(members, "members.csv");
        Logger.log("Members exported to CSV.");
    }
}