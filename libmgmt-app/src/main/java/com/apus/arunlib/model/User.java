package com.apus.arunlib.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;
/**
 * @author Arun Kumar Raju
 */
@Data
public class User {
    private static final long serialVersionUID = 654L;
    private long userId;
    private String userName;
    @JsonIgnore
    private String userEncrytedPassword;
    private int enabled;
    private List<Role> roleList;
}
