package com.twuc.finalbackend.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartItemDto {

    private String itemId;

    private String name;

    private int price;

    private String unit;

    private int quantity;

}
