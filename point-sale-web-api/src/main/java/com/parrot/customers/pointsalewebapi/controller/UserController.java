package com.parrot.customers.pointsalewebapi.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.parrot.customers.pointsalewebapi.constans.Constans;
import com.parrot.customers.pointsalewebapi.dto.UserDTO;
import com.parrot.customers.pointsalewebapi.exceptions.ServiceException;
import com.parrot.customers.pointsalewebapi.service.UserService;
import com.parrot.customers.pointsalewebapi.util.Response;

@RestController
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@RequestMapping("/pointsale")
public class UserController {

    final static Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @GetMapping(path = "/user/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Response<List<UserDTO>>> getUserList () {
        Response<List<UserDTO>> response = null;
        try {
            response = new Response<>(userService.getUserList(), Constans.MESSAGE_SUCCESS);
        } catch (ServiceException ex) {
            LOGGER.error(ex.getMessage(), ex);
            response = new Response<>(new ArrayList<>(), Constans.MESSAGE_FAIL + ex.getMessage());
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }
    
    @PostMapping(path = "/user/save", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Response<UserDTO>> saveUser(@RequestBody UserDTO userDTO) {
        Response<UserDTO> response;
        try {
            response = new Response<>(userService.saveUser(userDTO), Constans.MESSAGE_SAVE_SUCCESS);
        } catch (ServiceException ex) {
            LOGGER.error(ex.getMessage(), ex);
            response = new Response<>(null, ex.getMessage());
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }
    
    @GetMapping(path = "/user/finByEmail/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Response<UserDTO>> getUserByEmail(@PathVariable(name = "email") String email) {
        Response<UserDTO> response;
        try {
            response = new Response<>(userService.getUserByEmail(email));
        } catch (ServiceException ex) {
            LOGGER.error(ex.getMessage(), ex);
            response = new Response<>(null, ex.getMessage());
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }
}
