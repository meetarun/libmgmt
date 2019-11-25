package com.apus.arunlib.repository;

import com.apus.arunlib.entity.BookEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * @author Arun Kumar Raju
 */
@Repository
public interface BookRepository extends CrudRepository<BookEntity,Integer>, JpaSpecificationExecutor<BookEntity> {
    BookEntity getBookByBookId(long bookId);
    List<BookEntity> findBookEntitiesByBookNameContainingIgnoreCase(String bookName);
    // Search books using pagination
    Slice<BookEntity> findBookEntitiesByBookNameContainingIgnoreCase(String bookName, Pageable pageable);
}
