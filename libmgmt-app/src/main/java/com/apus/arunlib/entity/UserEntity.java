package com.apus.arunlib.entity;

import javax.persistence.*;
import java.util.Objects;
/**
 * @author Arun Kumar Raju
 */
@Entity
@Table(name = "lib_user", schema = "public", catalog = "librarydb")
public class UserEntity {
    private static final long serialVersionUID = 123L;
    private long userId;
    private String userName;
    private String userEncrytedPassword;
    private int enabled;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "user_id")
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "user_name")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "user_encryted_password")
    public String getUserEncrytedPassword() {
        return userEncrytedPassword;
    }

    public void setUserEncrytedPassword(String userEncrytedPassword) {
        this.userEncrytedPassword = userEncrytedPassword;
    }

    @Basic
    @Column(name = "enabled")
    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return userId == that.userId &&
                enabled == that.enabled &&
                Objects.equals(userName, that.userName) &&
                Objects.equals(userEncrytedPassword, that.userEncrytedPassword);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, userName, userEncrytedPassword, enabled);
    }
}
