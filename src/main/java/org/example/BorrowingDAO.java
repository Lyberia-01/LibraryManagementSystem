package org.example;

import java.util.List;

public interface BorrowingDAO {
    void addBorrowingRecord(BorrowingRecord record);
    void updateBorrowingRecord(BorrowingRecord record);
    void deleteBorrowingRecord(int recordId);
    List<BorrowingRecord> getAllBorrowingRecords();
    BorrowingRecord getBorrowingRecordById(int recordId);
}