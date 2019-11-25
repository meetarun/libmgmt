package com.apus.arunlib.repository;

import com.apus.arunlib.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
/**
 * @author Arun Kumar Raju
 */
@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {
    Optional<UserEntity> findByUserName(String userName);
    Optional<UserEntity> findByUserNameIgnoreCase(String userName);
    Optional<UserEntity> findByUserId(long id);
}
