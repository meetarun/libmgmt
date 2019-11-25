package com.apus.arunlib.model;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @author Arun Kumar Raju
 */
@Data
public class Book {
    private static final long serialVersionUID = 456L;
    private long bookId;
    private String bookName;
    private String bookDesc;
    private String bookIsbn;
    private String bookAuthor;
    private String bookPublication;
    private String bookCategory;
    private String bookMediaType;
    private String bookPrice;
    private String bookEdition;
    private Timestamp bookInductionDate;
    private String bookAvailability;
}
