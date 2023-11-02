package com.diatoz.task1.controller;

import com.diatoz.task1.customException.IdException;
import com.diatoz.task1.customException.LoginDataException;
import com.diatoz.task1.customException.PasswordException;
import com.diatoz.task1.entity.AccountDetails;
import com.diatoz.task1.model.JwtRequest;
import com.diatoz.task1.model.JwtResponse;
import com.diatoz.task1.security.JwtHelper;
import com.diatoz.task1.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class EntryPointController {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    LoginService loginService;

    @Autowired
    private JwtHelper helper;

    private Logger logger = LoggerFactory.getLogger(EntryPointController.class);


    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {

        this.doAuthenticate(request.getUserName(), request.getPassword());


        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUserName());
        logger.info("UserDetails = {}",userDetails);
        String token = this.helper.generateToken(userDetails);

        JwtResponse response = new JwtResponse();
        response.setJwtToken(token);
        response.setUserName(userDetails.getUsername());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private void doAuthenticate(String email, String password) {

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password, Collections.singleton(new SimpleGrantedAuthority("USER")));

        logger.info("User data is inside  authentication method {}",authentication);
        try {

            manager.authenticate(authentication);
            logger.info("done with  authentication");



        } catch (UsernameNotFoundException e)
        {
            throw new UsernameNotFoundException(e.getMessage());
        }
        catch (BadCredentialsException e) {
            throw new BadCredentialsException(" Invalid Username or Password  !!");
        }

    }

    @RequestMapping(value = "/create",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createAccount(@Validated @RequestBody AccountDetails accountDetails,BindingResult result) throws Exception {
        if((result.hasErrors()))
        {
            Map<String, String> errorMap = new HashMap<>();
            for (FieldError fieldError : result.getFieldErrors()) {
                errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
            }

            logger.info(errorMap.toString());
            throw  new LoginDataException(errorMap);
        }


        return ResponseEntity.ok(loginService.createAccount(accountDetails));
    }

    @RequestMapping(value = "/remove",method = RequestMethod.DELETE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> removeAccount(@Validated @RequestBody AccountDetails accountDetails,  BindingResult result) throws IdException, LoginDataException, PasswordException {
        if((result.hasErrors()))
        {
            Map<String, String> errorMap = new HashMap<>();
            for (FieldError fieldError : result.getFieldErrors()) {
                errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
            }

            logger.info(errorMap.toString());
            throw  new LoginDataException(errorMap);
        }


        return ResponseEntity.ok(loginService.deleteAccount(accountDetails));
    }
    @RequestMapping(value = "/getAllUser",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AccountDetails>> getAllAccount() {
        return ResponseEntity.ok(loginService.getAllUser());

    }
    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler() {
        return "Credentials Invalid !!";
    }
    @ExceptionHandler(UsernameNotFoundException.class)
    public String userNotFoundExceptionHandler(UsernameNotFoundException e) {
        return e.getMessage();
    }


}
