/*
 * *
 *  *  @author Arun Kumar Raju
 *  *
 *  *
 */

package com.apus.arunlib.service;

import com.apus.arunlib.model.User;

/**
 * @author Arun Kumar Raju
 **/
public interface LibUserService {
    User getUserById(Long userId);
    User getUserByUserName(String userName);
}
