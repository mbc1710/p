package com.parrot.customers.pointsalewebapi.service.converter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.parrot.customers.pointsalewebapi.dto.UserDTO;
import com.parrot.customers.pointsalewebapi.model.User;

public class UserConverter {

    public static List<UserDTO> convertToUserDTOFromUser (Iterable<User> userList) {
        return StreamSupport.stream(userList.spliterator(), false)
                .map(mapper -> convertToUserDTOFromUser(mapper))
                .collect(Collectors.toList());
    }

    public static UserDTO convertToUserDTOFromUser (User user) {
        return UserDTO.builder()
                .email(user.getEmail())
                .name(user.getName())
                .userId(user.getUserId())
                .ordersDTOList(OrderConverter.convertToOrderDTOFromOrder(user.getOrdersList()))
                .build();
    }

    public static User convertToUserFromUserDTO (UserDTO userDTO) {
        return User.builder()
                .email(userDTO.getEmail())
                .name(userDTO.getName())
                .createDate(new Date())
                .ordersList(userDTO.getOrdersDTOList() != null ? OrderConverter.convertToOrderFromOrderDTO(userDTO.getOrdersDTOList()) : new ArrayList<>())
                .build();
    }

}
