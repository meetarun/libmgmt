package com.apus.arunlib.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @author Arun Kumar Raju
 */
@Entity
@Table(name = "lib_borrower", schema = "public", catalog = "librarydb")
public class LibBorrowerEntity {
    private static final long serialVersionUID = 800L;
    private long borId;
    private long issuedBy;
    private Timestamp dateOfIssue;
    private Timestamp dateOfReturn;
    private UserEntity libUserByUserId;
    private BookEntity libInventoryByBookId;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "bor_id")
    public long getBorId() {
        return borId;
    }

    public void setBorId(long borId) {
        this.borId = borId;
    }

    @Basic
    @Column(name = "issued_by")
    public long getIssuedBy() {
        return issuedBy;
    }

    public void setIssuedBy(long issuedBy) {
        this.issuedBy = issuedBy;
    }

    @Basic
    @Column(name = "date_of_issue")
    public Timestamp getDateOfIssue() {
        return dateOfIssue;
    }

    public void setDateOfIssue(Timestamp dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }

    @Basic
    @Column(name = "date_of_return")
    public Timestamp getDateOfReturn() {
        return dateOfReturn;
    }

    public void setDateOfReturn(Timestamp dateOfReturn) {
        this.dateOfReturn = dateOfReturn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LibBorrowerEntity that = (LibBorrowerEntity) o;
        return borId == that.borId &&
                issuedBy == that.issuedBy &&
                Objects.equals(dateOfIssue, that.dateOfIssue) &&
                Objects.equals(dateOfReturn, that.dateOfReturn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(borId, issuedBy, dateOfIssue, dateOfReturn);
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    public UserEntity getLibUserByUserId() {
        return libUserByUserId;
    }

    public void setLibUserByUserId(UserEntity libUserByUserId) {
        this.libUserByUserId = libUserByUserId;
    }

    @ManyToOne
    @JoinColumn(name = "book_id", referencedColumnName = "book_id", nullable = false)
    public BookEntity getLibInventoryByBookId() {
        return libInventoryByBookId;
    }

    public void setLibInventoryByBookId(BookEntity libInventoryByBookId) {
        this.libInventoryByBookId = libInventoryByBookId;
    }
}
