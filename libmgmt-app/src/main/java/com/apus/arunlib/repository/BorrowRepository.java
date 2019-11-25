package com.apus.arunlib.repository;

import com.apus.arunlib.entity.LibBorrowerEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * @author Arun Kumar Raju
 */
@Repository
public interface BorrowRepository  extends CrudRepository<LibBorrowerEntity , Integer>, JpaSpecificationExecutor<LibBorrowerEntity> {
    List<LibBorrowerEntity>  findLibBorrowerEntitiesByLibUserByUserId_UserId(long userId);
    List<LibBorrowerEntity>  findAll();
}
