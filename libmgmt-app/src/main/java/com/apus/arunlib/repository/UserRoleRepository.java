package com.apus.arunlib.repository;

import com.apus.arunlib.entity.UserRoleEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * @author Arun Kumar Raju
 */
@Repository
public interface UserRoleRepository extends CrudRepository<UserRoleEntity, Long> {
    List<UserRoleEntity> findAllByLibUserByUserId_UserId(long userId);
}
