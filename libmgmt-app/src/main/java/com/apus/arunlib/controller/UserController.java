package com.apus.arunlib.controller;

import com.apus.arunlib.model.LibMgmtWrappedResponse;
import com.apus.arunlib.model.User;
import com.apus.arunlib.service.LibUserService;
import com.apus.arunlib.utils.LibMgmtConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * @author Arun Kumar Raju
 */
@RestController
@RequestMapping ("/v1/users")
public class UserController {
    private static final long serialVersionUID = 600L;

    @Autowired
    LibUserService libUserService;

    private static final Logger logger = LogManager.getLogger(UserController.class);

    @Secured({LibMgmtConstants.LIB_ROLE_ADMIN , LibMgmtConstants.LIB_ROLE_SUPERVISOR})
    @GetMapping("/{id}")
    public LibMgmtWrappedResponse getUser(@PathVariable long id) {
            User user = libUserService.getUserById(id);
            logger.info("Successfully fetched the User with id {}", id);
            LibMgmtWrappedResponse response = new LibMgmtWrappedResponse();
            response.setStatus(HttpStatus.OK.value());
            response.setResult(user);
            response.setMessage("Successfully fetched the user with id:" + id);
            return response;
    }

}
