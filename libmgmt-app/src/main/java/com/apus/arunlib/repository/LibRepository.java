package com.apus.arunlib.repository;

import com.apus.arunlib.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * @author Arun Kumar Raju
 */
public interface LibRepository extends JpaRepository<BookEntity, Long> {
}
