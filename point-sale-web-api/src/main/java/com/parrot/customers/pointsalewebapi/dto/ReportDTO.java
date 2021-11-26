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
public class ReportDTO implements Serializable{

    private static final long serialVersionUID = 1L;

    private String productName;
    private Integer totalAmount;
    private Double totalPrice;
}
