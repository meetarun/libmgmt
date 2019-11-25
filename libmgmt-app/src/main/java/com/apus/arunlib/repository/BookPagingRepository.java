/*
 * *
 *  *  @author Arun Kumar Raju
 *  *
 *  *
 */

package com.apus.arunlib.repository;

import com.apus.arunlib.entity.BookEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author Arun Kumar Raju
 **/
public interface BookPagingRepository extends PagingAndSortingRepository<BookEntity , Long> {
    Page<BookEntity> findBookEntitiesByBookNameContainingIgnoreCase(String bookName, Pageable pageable);
}
