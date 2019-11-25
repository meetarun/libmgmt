package com.apus.arunlib.service;

import com.apus.arunlib.model.BorrowerModel;

import java.util.List;
/**
 * @author Arun Kumar Raju
 */
public interface BorrowService {
    List<BorrowerModel> getBorrowedBooksByUserId(long userId);
    List<BorrowerModel> getBorrowedBooksByUserName(String userName);
    List<BorrowerModel> getAllIssuedBooks();
}
