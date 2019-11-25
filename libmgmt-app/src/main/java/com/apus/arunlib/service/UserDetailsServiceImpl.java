package com.apus.arunlib.service;

import com.apus.arunlib.entity.UserEntity;
import com.apus.arunlib.entity.UserRoleEntity;
import com.apus.arunlib.repository.UserRepository;
import com.apus.arunlib.repository.UserRoleRepository;
import com.apus.arunlib.utils.LibMgmtConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
/**
 * @author Arun Kumar Raju
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private static final long serialVersionUID = 532439348L;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    private static final Logger logger = LogManager.getLogger(UserDetailsServiceImpl.class);

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<UserEntity> user = userRepository.findByUserNameIgnoreCase(userName);
        if(!user.isPresent()) {
            logger.info(LibMgmtConstants.LIB_USER_NOT_FOUND,userName);
            throw new UsernameNotFoundException(String.format(LibMgmtConstants.LIB_USER_NOT_FOUND,userName));
        } else if(0 == user.get().getEnabled()) {
            logger.info("User with username {} is found and the user status is DISABLED",userName);
            throw new UsernameNotFoundException("User with username " + userName + " is found with DISABLED state");
        }
        //then fetch the roles of user
            Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
            List<UserRoleEntity> userRolesList = userRoleRepository.findAllByLibUserByUserId_UserId(user.get().getUserId());
        if(null == userRolesList || userRolesList.isEmpty())
        {
            logger.info("Roles not found for the user {}",userName);
            throw new UsernameNotFoundException("User with username " + userName + " is found wit NO ROLES attached");
        } else {
            for(UserRoleEntity role : userRolesList){
                grantedAuthorities.add(new SimpleGrantedAuthority(role.getLibRoleByRoleId().getRoleName()));
            }
        }


        return new org.springframework.security.core.userdetails.User(user.get().getUserName(), user.get().getUserEncrytedPassword(),
               grantedAuthorities);
    }
}
