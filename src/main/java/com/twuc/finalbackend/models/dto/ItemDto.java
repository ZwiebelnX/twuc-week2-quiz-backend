package com.twuc.finalbackend.models.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ItemDto {
    private String id;

    private String name;

    private int price;

    private String unit;

    private String imageUrl;
}
