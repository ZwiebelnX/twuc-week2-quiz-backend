package com.twuc.finalbackend.models.dto;

import com.twuc.finalbackend.models.po.ItemPo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemListDto {
    private int totalPage;

    private List<ItemDto> data;
}
