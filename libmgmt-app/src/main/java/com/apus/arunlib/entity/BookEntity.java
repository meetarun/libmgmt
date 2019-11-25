package com.apus.arunlib.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;
/**
 * @author Arun Kumar Raju
 */
@Entity
@Table(name = "lib_inventory", schema = "public", catalog = "librarydb")
public class BookEntity {
    private static final long serialVersionUID = 700L;
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

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "book_id")
    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    @Basic
    @Column(name = "book_name")
    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    @Basic
    @Column(name = "book_desc")
    public String getBookDesc() {
        return bookDesc;
    }

    public void setBookDesc(String bookDesc) {
        this.bookDesc = bookDesc;
    }

    @Basic
    @Column(name = "book_isbn")
    public String getBookIsbn() {
        return bookIsbn;
    }

    public void setBookIsbn(String bookIsbn) {
        this.bookIsbn = bookIsbn;
    }

    @Basic
    @Column(name = "book_author")
    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    @Basic
    @Column(name = "book_publication")
    public String getBookPublication() {
        return bookPublication;
    }

    public void setBookPublication(String bookPublication) {
        this.bookPublication = bookPublication;
    }

    @Basic
    @Column(name = "book_category")
    public String getBookCategory() {
        return bookCategory;
    }

    public void setBookCategory(String bookCategory) {
        this.bookCategory = bookCategory;
    }

    @Basic
    @Column(name = "book_media_type")
    public String getBookMediaType() {
        return bookMediaType;
    }

    public void setBookMediaType(String bookMediaType) {
        this.bookMediaType = bookMediaType;
    }

    @Basic
    @Column(name = "book_price")
    public String getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(String bookPrice) {
        this.bookPrice = bookPrice;
    }

    @Basic
    @Column(name = "book_edition")
    public String getBookEdition() {
        return bookEdition;
    }

    public void setBookEdition(String bookEdition) {
        this.bookEdition = bookEdition;
    }

    @Basic
    @Column(name = "book_induction_date")
    public Timestamp getBookInductionDate() {
        return bookInductionDate;
    }

    public void setBookInductionDate(Timestamp bookInductionDate) {
        this.bookInductionDate = bookInductionDate;
    }

    @Basic
    @Column(name = "book_availability")
    public String getBookAvailability() {
        return bookAvailability;
    }

    public void setBookAvailability(String bookAvailability) {
        this.bookAvailability = bookAvailability;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookEntity that = (BookEntity) o;
        return bookId == that.bookId &&
                Objects.equals(bookName, that.bookName) &&
                Objects.equals(bookDesc, that.bookDesc) &&
                Objects.equals(bookIsbn, that.bookIsbn) &&
                Objects.equals(bookAuthor, that.bookAuthor) &&
                Objects.equals(bookPublication, that.bookPublication) &&
                Objects.equals(bookCategory, that.bookCategory) &&
                Objects.equals(bookMediaType, that.bookMediaType) &&
                Objects.equals(bookPrice, that.bookPrice) &&
                Objects.equals(bookEdition, that.bookEdition) &&
                Objects.equals(bookInductionDate, that.bookInductionDate) &&
                Objects.equals(bookAvailability, that.bookAvailability);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookId, bookName, bookDesc, bookIsbn, bookAuthor, bookPublication, bookCategory, bookMediaType, bookPrice, bookEdition, bookInductionDate, bookAvailability);
    }
}
