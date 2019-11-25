package com.apus.arunlib.model;

import com.apus.arunlib.entity.RoleEntity;
import com.apus.arunlib.entity.UserEntity;
import lombok.Data;
/**
 * @author Arun Kumar Raju
 */
@Data
public class UserRole {
    private static final long serialVersionUID = 6432L;
    private long id;
    private UserEntity libUserByUserId;
    private RoleEntity libRoleByRoleId;
}
