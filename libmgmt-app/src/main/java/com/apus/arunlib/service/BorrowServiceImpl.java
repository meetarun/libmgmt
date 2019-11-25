package com.apus.arunlib.service;

import com.apus.arunlib.entity.LibBorrowerEntity;
import com.apus.arunlib.entity.UserEntity;
import com.apus.arunlib.model.BorrowerModel;
import com.apus.arunlib.repository.BorrowRepository;
import com.apus.arunlib.repository.UserRepository;
import com.apus.arunlib.utils.LibUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Arun Kumar Raju
 */
@Service
public class BorrowServiceImpl implements BorrowService {
    private static final long serialVersionUID = 7390329493L;
    @Autowired
    BorrowRepository borrowRepository;

    @Autowired
    UserRepository userRepository;

    private static final Logger logger = LogManager.getLogger(BorrowServiceImpl.class);

    @Override
    public List<BorrowerModel> getBorrowedBooksByUserId(long userId) {
        List<LibBorrowerEntity> booksBorrowedEntityList = borrowRepository.findLibBorrowerEntitiesByLibUserByUserId_UserId(userId);
        List<BorrowerModel> booksBorrowedList = LibUtils.mapBorrowerEntityListToBorrowerModelList(booksBorrowedEntityList);
        logger.info("Found {} issued books for the user id :{}",booksBorrowedList.size(),userId);
        return booksBorrowedList;
    }

    @Override
    public List<BorrowerModel> getBorrowedBooksByUserName(String userName) {
        Optional<UserEntity> userEntity = userRepository.findByUserNameIgnoreCase(userName);
        long userId = userEntity.get().getUserId();
        List<LibBorrowerEntity> booksBorrowedEntityList = borrowRepository.findLibBorrowerEntitiesByLibUserByUserId_UserId(userId);
        List<BorrowerModel> booksBorrowedList = LibUtils.mapBorrowerEntityListToBorrowerModelList(booksBorrowedEntityList);
        logger.info("Found {} issued books for the user id :{}",booksBorrowedList.size(),userId);
        return booksBorrowedList;
    }

    @Override
    public List<BorrowerModel> getAllIssuedBooks() {
        List<LibBorrowerEntity> booksBorrowedEntityList = borrowRepository.findAll();
        return LibUtils.mapBorrowerEntityListToBorrowerModelList(booksBorrowedEntityList);
    }
}
