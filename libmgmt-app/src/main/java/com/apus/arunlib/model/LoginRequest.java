package com.apus.arunlib.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
/**
 * @author Arun Kumar Raju
 */
@Getter
@Setter
public class LoginRequest {
    private static final long serialVersionUID = 987L;
    @NotBlank
    private String userName;
    @NotBlank
    private String password;
}
