package com.apus.arunlib.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author Arun Kumar Raju
 */
@Entity
@Table(name = "user_role", schema = "public", catalog = "librarydb")
public class UserRoleEntity {
    private static final long serialVersionUID = 234L;
    private long id;
    private UserEntity libUserByUserId;
    private RoleEntity libRoleByRoleId;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRoleEntity that = (UserRoleEntity) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
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
    @JoinColumn(name = "role_id", referencedColumnName = "role_id", nullable = false)
    public RoleEntity getLibRoleByRoleId() {
        return libRoleByRoleId;
    }

    public void setLibRoleByRoleId(RoleEntity libRoleByRoleId) {
        this.libRoleByRoleId = libRoleByRoleId;
    }
}
