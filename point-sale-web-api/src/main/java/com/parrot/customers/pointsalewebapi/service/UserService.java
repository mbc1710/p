package com.parrot.customers.pointsalewebapi.service;

import java.util.List;

import com.parrot.customers.pointsalewebapi.dto.UserDTO;

public interface UserService {

    public List<UserDTO> getUserList ();

    public UserDTO saveUser (UserDTO userDTO);

    public UserDTO getUserByEmail (String email);
}
