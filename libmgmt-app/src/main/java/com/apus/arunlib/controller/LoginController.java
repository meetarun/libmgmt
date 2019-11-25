package com.apus.arunlib.controller;

import com.apus.arunlib.model.LibMgmtWrappedResponse;
import com.apus.arunlib.model.LoginRequest;
import com.apus.arunlib.security.JwtTokenProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
/**
 * @author Arun Kumar Raju
 */
@RestController
@RequestMapping("/v1")
public class LoginController {
    private static final long serialVersionUID = 500L;
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    private static final Logger logger = LogManager.getLogger(LoginController.class);

    @PostMapping("/login")
    public LibMgmtWrappedResponse<String> login(@Valid @RequestBody LoginRequest request) {
        LibMgmtWrappedResponse response = new LibMgmtWrappedResponse();
        try {
            Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(auth);
            String token = jwtTokenProvider.generateToken(request.getUserName(), auth);

            response.setStatus(HttpStatus.OK.value());
            response.setMessage("Token Generated Successfully");
            response.setMessage(token);
            logger.info("User {} logged/token generated successfully",request.getUserName());
        } catch (Exception ex) {
            logger.error("Exception occurred while logging/generating token for the user :" + request.getUserName(), ex);
            response.setMessage("Login Failed");
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setResult(null);
        }
        return response;
    }

}
