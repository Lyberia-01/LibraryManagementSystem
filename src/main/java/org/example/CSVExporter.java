package org.example;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CSVExporter {

    public static void exportBooksToCSV(List<Book> books, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            //Created Write header
            writer.write("book_id,title,author,genre,available_copies");
            writer.newLine();

            //Created Write book data
            for (Book book : books) {
                writer.write(book.getBookId() + "," + book.getTitle() + "," + book.getAuthor() + "," + book.getGenre() + "," + book.getAvailableCopies());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void exportMembersToCSV(List<Member> members, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            //Created Write header
            writer.write("member_id,name,email,phone");
            writer.newLine();

            //Created Write member data
            for (Member member : members) {
                writer.write(member.getMemberId() + "," + member.getName() + "," + member.getEmail() + "," + member.getPhone());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}