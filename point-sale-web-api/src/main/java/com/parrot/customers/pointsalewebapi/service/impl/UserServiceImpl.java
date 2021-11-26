package com.parrot.customers.pointsalewebapi.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parrot.customers.pointsalewebapi.dto.UserDTO;
import com.parrot.customers.pointsalewebapi.exceptions.ServiceException;
import com.parrot.customers.pointsalewebapi.repository.UserRepository;
import com.parrot.customers.pointsalewebapi.service.UserService;
import com.parrot.customers.pointsalewebapi.service.converter.UserConverter;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;
    
    /**
     * Funcion para obtener la lista de usuarios de BD
     */
    @Override
    public List<UserDTO> getUserList () {
        List<UserDTO> response;
        try {
            response = UserConverter.convertToUserDTOFromUser(userRepository.findAll());
        } catch (Exception ex) {
            LOGGER.error("Error in get information of user: "+ ex.getCause(), ex);
            throw new ServiceException("Error in get information of user: "+ ex.getCause(), ex);
        }
        return response;
    }

    /**
     * Funcion para guardar un usuario nuevo
     */
    @Override
    public UserDTO saveUser (UserDTO userDTO) {
        UserDTO response;
        try {
            response = UserConverter.convertToUserDTOFromUser(userRepository.saveAndFlush(UserConverter.convertToUserFromUserDTO(userDTO)));
        } catch (Exception ex) {
            LOGGER.error("Error in save the user: " + userDTO.toString()+ " " + ex.getCause(), ex);
            throw new ServiceException("Error in save the user: " + userDTO.toString() + " " + ex.getCause(), ex);
        }
        return response;
    }

    /**
     * Funcion para obtener la informacion de un usuario en especifico
     */
    @Override
    public UserDTO getUserByEmail (String email) {
        UserDTO response;
        try {
            response = UserConverter.convertToUserDTOFromUser(userRepository.findByEmail(email));
        } catch (Exception ex) {
            LOGGER.error("Error in get information of user: " + email+ " " + ex.getCause(), ex);
            throw new ServiceException("Error in get information of user: " + email+ " " + ex.getCause(), ex);
        }
        return response;
    }

}
