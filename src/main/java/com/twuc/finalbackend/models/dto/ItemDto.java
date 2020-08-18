package com.twuc.finalbackend.models.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class ItemDto {
    private String id;

    @NotEmpty
    private String name;

    @Min(0)
    private int price;

    @NotEmpty
    private String unit;

    @NotEmpty
    private String imageUrl;
}
