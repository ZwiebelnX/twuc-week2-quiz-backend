package com.twuc.finalbackend.models.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartListDto {
    private int totalPage;

    private List<CartItemDto> data;


}
