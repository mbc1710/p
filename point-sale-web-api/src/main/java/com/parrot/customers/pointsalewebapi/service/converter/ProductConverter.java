package com.parrot.customers.pointsalewebapi.service.converter;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.parrot.customers.pointsalewebapi.dto.ProductDTO;
import com.parrot.customers.pointsalewebapi.model.Product;

public class ProductConverter {

    public static List<ProductDTO> convertToProductDTOFromProduct (Iterable<Product> productList) {
        return StreamSupport.stream(productList.spliterator(), false)
                .map(mapper -> convertToProductDTOFromProduct(mapper))
                .collect(Collectors.toList());
    }

    public static ProductDTO convertToProductDTOFromProduct (Product product) {
        return ProductDTO.builder()
                .name(product.getName())
                .amount(product.getAmount())
                .unitPrice(product.getUnitPrice())
                .productId(product.getProductId())
                .build();
    }

    public static Product convertToProductFromProductDTO (ProductDTO productDTO) {
        return Product.builder()
                .name(productDTO.getName())
                .amount(productDTO.getAmount())
                .unitPrice(productDTO.getUnitPrice())
                .createDate(new Date())
                .build();
    }

    public static List<Product> convertToProductFromProductDTO (Iterable<ProductDTO> productDTOList) {
        return StreamSupport.stream(productDTOList.spliterator(), false)
                .map(mapper -> convertToProductFromProductDTO(mapper))
                .collect(Collectors.toList());
    }
}
