/*
 * *
 *  *  @author Arun Kumar Raju
 *  *
 *  *
 */

package com.apus.arunlib.service;

import com.apus.arunlib.entity.UserEntity;
import com.apus.arunlib.libexception.LibUserNotFound;
import com.apus.arunlib.model.User;
import com.apus.arunlib.repository.UserRepository;
import com.apus.arunlib.utils.LibMgmtConstants;
import com.apus.arunlib.utils.LibUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Arun Kumar Raju
 **/
@Service
public class LibUserServiceImpl implements LibUserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User getUserById(Long userId) {
        UserEntity userEntity = userRepository.findByUserId(userId)
                .orElseThrow(() -> new LibUserNotFound(String.format(LibMgmtConstants.LIB_USERID_NOT_FOUND,userId+"")));
        return LibUtils.convertUserEntityToUser(userEntity);
    }

    @Override
    public User getUserByUserName(String userName) {
        return LibUtils.convertUserEntityToUser((userRepository.findByUserNameIgnoreCase(userName).get()));
    }
}
