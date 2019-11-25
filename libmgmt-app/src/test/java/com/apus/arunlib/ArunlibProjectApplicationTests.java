package com.apus.arunlib;


import com.apus.arunlib.libexception.BookNotFoundException;
import com.apus.arunlib.model.Book;
import com.apus.arunlib.model.LibMgmtWrappedResponse;
import com.apus.arunlib.model.LoginRequest;
import com.apus.arunlib.utils.LibMgmtConstants;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;


/**
 * @author Arun Kumar Raju
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@EnableConfigurationProperties
@TestPropertySource(locations = "classpath:application-test.properties")
public class ArunlibProjectApplicationTests {
    private static final long serialVersionUID = 8459987L;
    @LocalServerPort
    private int randomTestServerPort;

    public String baseURL;
    public static String jwtToken;

    @Value("${libmgmt.test.user.username}")
    private String APP_TEST_USER_USERNAME;

    @Value("${libmgmt.test.user.password}")
    private String APP_TEST_USER_PASSWORD;

    @Test
    @BeforeAll
    public  void setupLoginTest() throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();
        String loginURL = LibMgmtTestConstants.APP_URL +  randomTestServerPort + LibMgmtTestConstants.APP_VERSION+LibMgmtTestConstants.URI_SEPERATOR+"login";
        URI loginURI = new URI(loginURL);
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUserName(APP_TEST_USER_USERNAME);
        loginRequest.setPassword(APP_TEST_USER_PASSWORD);
        HttpEntity<LoginRequest> request = new HttpEntity<>(loginRequest);
        ResponseEntity<LibMgmtWrappedResponse> response = restTemplate.postForEntity(loginURI, request, LibMgmtWrappedResponse.class);

        Assert.assertEquals(HttpStatus.OK.value(),response.getStatusCodeValue());
        jwtToken = response.getBody().getMessage();
    }

/*    @Test
    public void initloginTest() throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();
        baseURL = LibMgmtTestConstants.APP_URL +  randomTestServerPort + LibMgmtTestConstants.APP_VERSION+LibMgmtTestConstants.URI_SEPERATOR+"login";
        URI loginURI = new URI(baseURL);
        LoginRequest loginRequest = new LoginRequest();
//        loginRequest.setUserName(LibMgmtTestConstants.APP_TEST_USER_USERNAME);
//        loginRequest.setPassword(LibMgmtTestConstants.APP_TEST_USER_PASSWORD);
        loginRequest.setUserName("admin1");
        loginRequest.setPassword("admin$1");
        HttpEntity<LoginRequest> request = new HttpEntity<>(loginRequest);
        ResponseEntity<LibMgmtWrappedResponse> response = restTemplate.postForEntity(loginURI, request, LibMgmtWrappedResponse.class);


        Assert.assertEquals(HttpStatus.OK.value(),response.getStatusCodeValue());
        jwtToken = response.getBody().getMessage();

    }*/


    @Test
    public void testGetBookByIdSuccess() throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();
        baseURL = LibMgmtTestConstants.APP_URL + randomTestServerPort + LibMgmtTestConstants.APP_VERSION;
        URI bookURI = new URI(baseURL + LibMgmtTestConstants.GET_BOOK_URL + "/" + LibMgmtTestConstants.BOOK_ID);
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, jwtToken);
        HttpEntity<Book> entity = new HttpEntity<>(headers);
        ResponseEntity<LibMgmtWrappedResponse<Book>> result = restTemplate.exchange(bookURI, HttpMethod.GET, entity,
                new ParameterizedTypeReference<LibMgmtWrappedResponse<Book>>() {
        });
        Assert.assertEquals(HttpStatus.OK.value(), result.getStatusCodeValue());
        Book book = (Book) result.getBody().getResult();
        Assert.assertEquals(LibMgmtTestConstants.BOOK_ID, book.getBookId());
    }

    @Test(expected = HttpClientErrorException.BadRequest.class)
    public void testGetBookByIdFailure() throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();
        baseURL = LibMgmtTestConstants.APP_URL + randomTestServerPort + LibMgmtTestConstants.APP_VERSION;
        URI bookURI = new URI(baseURL + LibMgmtTestConstants.GET_BOOK_URL + "/" + LibMgmtTestConstants.BOOK_ID+123);
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, jwtToken);
        HttpEntity<Book> entity = new HttpEntity<>(headers);
        ResponseEntity<LibMgmtWrappedResponse<Book>> result = restTemplate.exchange(bookURI, HttpMethod.GET, entity,
                new ParameterizedTypeReference<LibMgmtWrappedResponse<Book>>() {
               });
    }

}
