package com.apus.arunlib.entity;

import javax.persistence.*;
import java.util.Objects;
/**
 * @author Arun Kumar Raju
 */
@Entity
@Table(name = "lib_role", schema = "public", catalog = "librarydb")
public class RoleEntity {
    private static final long serialVersionUID = 900L;
    private long roleId;
    private String roleName;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "role_id")
    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    @Basic
    @Column(name = "role_name")
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleEntity that = (RoleEntity) o;
        return roleId == that.roleId &&
                Objects.equals(roleName, that.roleName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleId, roleName);
    }
}
