package com.apus.arunlib.controller;

import com.apus.arunlib.model.BorrowerModel;
import com.apus.arunlib.model.LibMgmtWrappedResponse;
import com.apus.arunlib.service.BorrowService;
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

import java.util.List;
/**
 * @author Arun Kumar Raju
 */
@RestController
@RequestMapping("/v1/borrower")
public class BorrowerController {
    private static final long serialVersionUID = 400L;
    @Autowired
    BorrowService borrowService;

    private static final Logger logger = LogManager.getLogger(BorrowerController.class);

    @Secured({LibMgmtConstants.LIB_ROLE_ADMIN, LibMgmtConstants.LIB_ROLE_STUDENT, LibMgmtConstants.LIB_ROLE_SUPERVISOR})
    @GetMapping("/{id}")
    public LibMgmtWrappedResponse<BorrowerModel> getBorrowedBooksByUserId(@PathVariable int id) {
        // TODO : return empty or null if a user tries to view other users issued/borrowed books with the help of auth principal
        LibMgmtWrappedResponse response = new LibMgmtWrappedResponse();
        List<BorrowerModel> borrowedBooksList =borrowService.getBorrowedBooksByUserId(id);
        response.setMessage("Fetched issued books for the specific user successfully");
        response.setStatus(HttpStatus.OK.value());
        response.setResult(borrowedBooksList);
        logger.info("Fetched the issued books successfully for the user with id : {}",id);
        String userId = ((borrowedBooksList.size() > 0) ?  borrowedBooksList.get(0).getUserId()+"" : "NaN" );
        logger.debug("Total number of issued books for the user with id : {} is :{}",userId,borrowedBooksList.size());
        return response;
    }

    @Secured({LibMgmtConstants.LIB_ROLE_ADMIN, LibMgmtConstants.LIB_ROLE_STUDENT, LibMgmtConstants.LIB_ROLE_SUPERVISOR})
    @GetMapping("/user/{userName}")
    public LibMgmtWrappedResponse<BorrowerModel> getBorrowedBooksByUserName(@PathVariable String userName) {
        // TODO : return empty or null if a user tries to view other users issued/borrowed books with the help of auth principal
        LibMgmtWrappedResponse response = new LibMgmtWrappedResponse();
        List<BorrowerModel> borrowedBooksList =borrowService.getBorrowedBooksByUserName(userName);
        response.setMessage("Fetched issued books for the specific user successfully");
        response.setStatus(HttpStatus.OK.value());
        response.setResult(borrowedBooksList);
        logger.info("Fetched the issued books successfully for the user with username : {}",userName);
        String userId = ((borrowedBooksList.size() > 0) ?  borrowedBooksList.get(0).getUserId()+"" : "NaN" );
        logger.debug("Total number of issued books for the user with id : {} is :{}",userId,borrowedBooksList.size());
        return response;
    }

    @Secured({LibMgmtConstants.LIB_ROLE_ADMIN, LibMgmtConstants.LIB_ROLE_SUPERVISOR})
    @GetMapping
    public LibMgmtWrappedResponse<BorrowerModel> getAllBorrowedBooks() {
        LibMgmtWrappedResponse response = new LibMgmtWrappedResponse();
        List<BorrowerModel> borrowedBooksList =borrowService.getAllIssuedBooks();
        response.setMessage("Fetched all the issued books successfully");
        response.setStatus(HttpStatus.OK.value());
        response.setResult(borrowedBooksList);
        logger.info("Fetched all the issued books successfully");
        logger.debug("Total number of issued books are : {}",borrowedBooksList.size());
        return response;
    }
}
