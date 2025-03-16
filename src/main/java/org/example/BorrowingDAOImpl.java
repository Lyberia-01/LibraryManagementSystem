package org.example;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BorrowingDAOImpl implements BorrowingDAO {

    @Override
    public void addBorrowingRecord(BorrowingRecord record) {
        String query = "INSERT INTO borrowing_records (book_id, member_id, borrow_date, return_date) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, record.getBookId());
            stmt.setInt(2, record.getMemberId());
            stmt.setDate(3, java.sql.Date.valueOf(record.getBorrowDate()));
            stmt.setDate(4, record.getReturnDate() == null ? null : java.sql.Date.valueOf(record.getReturnDate()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateBorrowingRecord(BorrowingRecord record) {
        String query = "UPDATE borrowing_records SET book_id = ?, member_id = ?, borrow_date = ?, return_date = ? WHERE record_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, record.getBookId());
            stmt.setInt(2, record.getMemberId());
            stmt.setDate(3, java.sql.Date.valueOf(record.getBorrowDate()));
            stmt.setDate(4, record.getReturnDate() == null ? null : java.sql.Date.valueOf(record.getReturnDate()));
            stmt.setInt(5, record.getRecordId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteBorrowingRecord(int recordId) {
        String query = "DELETE FROM borrowing_records WHERE record_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, recordId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<BorrowingRecord> getAllBorrowingRecords() {
        List<BorrowingRecord> records = new ArrayList<>();
        String query = "SELECT * FROM borrowing_records";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                BorrowingRecord record = new BorrowingRecord();
                record.setRecordId(rs.getInt("record_id"));
                record.setBookId(rs.getInt("book_id"));
                record.setMemberId(rs.getInt("member_id"));
                record.setBorrowDate(rs.getDate("borrow_date").toLocalDate());
                if(rs.getDate("return_date") != null){
                    record.setReturnDate(rs.getDate("return_date").toLocalDate());
                }
                records.add(record);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return records;
    }

    @Override
    public BorrowingRecord getBorrowingRecordById(int recordId) {
        String query = "SELECT * FROM borrowing_records WHERE record_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, recordId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                BorrowingRecord record = new BorrowingRecord();
                record.setRecordId(rs.getInt("record_id"));
                record.setBookId(rs.getInt("book_id"));
                record.setMemberId(rs.getInt("member_id"));
                record.setBorrowDate(rs.getDate("borrow_date").toLocalDate());
                if(rs.getDate("return_date") != null){
                    record.setReturnDate(rs.getDate("return_date").toLocalDate());
                }
                return record;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}