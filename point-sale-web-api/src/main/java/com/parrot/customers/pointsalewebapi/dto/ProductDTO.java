package com.parrot.customers.pointsalewebapi.dto;

import java.io.Serializable;

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
public class ProductDTO implements Serializable{

    private static final long serialVersionUID = 1L;

    private Integer productId;
    private String name;
    private Integer amount;
    private Double unitPrice;
}
