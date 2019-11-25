package com.apus.arunlib.model;

import com.apus.arunlib.entity.BookEntity;
import com.apus.arunlib.entity.UserEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.sql.Timestamp;
/**
 * @author Arun Kumar Raju
 */
@Data
public class BorrowerModel {
    private static final long serialVersionUID = 567L;
    private long issueId;
    private Timestamp dateOfIssue;
    private Timestamp dateOfReturn;
    private long userId;
    private long bookId;
    private String userName;
    private String bookName;
    private String authorName;
    private String isbnNumber;
    private String edition;
    private String category;
    private long issuedBy;
    @JsonIgnore
    private UserEntity libUserByUserId;
    @JsonIgnore
    private BookEntity libInventoryByBookId;

}
