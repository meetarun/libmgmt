package com.apus.arunlib.utils;

import com.apus.arunlib.entity.BookEntity;
import com.apus.arunlib.entity.LibBorrowerEntity;
import com.apus.arunlib.entity.UserEntity;
import com.apus.arunlib.model.Book;
import com.apus.arunlib.model.BorrowerModel;
import com.apus.arunlib.model.User;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/**
 * @author Arun Kumar Raju
 */
public class LibUtils {
    private static final long serialVersionUID = 7590944L;

    private static HashMap<String, String> sortByFieldMap = new HashMap<>();

    private LibUtils() {
    }

    public static Book convertBEtoBook(BookEntity be) {
        ModelMapper modelMapper =  new ModelMapper();
        return modelMapper.map(be,Book.class);
    }

    public static BookEntity convertBooktoBE(Book b) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(b,BookEntity.class);
    }

    public static List<Book> convertBEListToBookList(List<BookEntity> beList) {
        ModelMapper modelMapper = new ModelMapper();
        Type listType = new TypeToken<List<Book>>(){}.getType();
        return  modelMapper.map(beList,listType);
    }

    // Convert borrower entities to borrower model
    public static List<BorrowerModel> convertBorrowerEntityListBorrowerModelList(List<LibBorrowerEntity> borrowerEntityList) {
        ModelMapper modelMapper = new ModelMapper();
        Type listType = new TypeToken<List<BorrowerModel>>(){}.getType();
        return  modelMapper.map(borrowerEntityList,listType);
    }

    public static List<BorrowerModel> mapBorrowerEntityListToBorrowerModelList(List<LibBorrowerEntity> borrowerEntityList) {
        List<BorrowerModel> issuedBooksList = new ArrayList<>();
        for(LibBorrowerEntity entity:borrowerEntityList) {
            BorrowerModel issuedBook = new BorrowerModel();
            issuedBook.setIssueId(entity.getBorId());
            issuedBook.setDateOfIssue(entity.getDateOfIssue());
            issuedBook.setDateOfReturn(entity.getDateOfReturn());
            issuedBook.setIssuedBy(entity.getIssuedBy());

            issuedBook.setAuthorName(entity.getLibInventoryByBookId().getBookAuthor());
            issuedBook.setBookName(entity.getLibInventoryByBookId().getBookName());
            issuedBook.setBookId(entity.getLibInventoryByBookId().getBookId());
            issuedBook.setCategory(entity.getLibInventoryByBookId().getBookCategory());
            issuedBook.setEdition(entity.getLibInventoryByBookId().getBookEdition());
            issuedBook.setIsbnNumber(entity.getLibInventoryByBookId().getBookIsbn());

            issuedBook.setUserId(entity.getLibUserByUserId().getUserId());
            issuedBook.setUserName(entity.getLibUserByUserId().getUserName());
            issuedBooksList.add(issuedBook);
        }

        return issuedBooksList;
    }
    // TODO : generic object mapper

    public static Boolean validateSortByField(String sortBy) {
        sortByFieldMap.put("BOOKNAME","bookName");
        sortByFieldMap.put("BOOKID","bookId");
        sortByFieldMap.put("BOOKISBN","bookIsbn");
        sortByFieldMap.put("BOOKCATEGORY","bookCategory");
        sortByFieldMap.put("BOOKPRICE","bookPrice");
        return  sortByFieldMap.containsKey(sortBy.toUpperCase());
    }

    public static String getSortByValue(String sortBy) {
        return sortByFieldMap.get(sortBy.toUpperCase());
    }

    public static User convertUserEntityToUser(UserEntity userEntity) {
        User user = new User();
        user.setUserId(userEntity.getUserId());
        user.setEnabled(userEntity.getEnabled());
        user.setUserName(userEntity.getUserName());
        return user;
    }
}
