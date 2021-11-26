package com.parrot.customers.pointsalewebapi.dto;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDTO implements Serializable{

    private static final long serialVersionUID = 1L;

    private Integer userId;
    private String email;
    private String name;
    private List<OrderDTO> ordersDTOList;
}
