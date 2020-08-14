package com.twuc.finalbackend.service;

import com.twuc.finalbackend.models.dto.ItemDto;
import com.twuc.finalbackend.models.dto.ItemListDto;
import com.twuc.finalbackend.models.exception.ItemExistException;
import com.twuc.finalbackend.models.po.ItemPo;
import com.twuc.finalbackend.repository.ItemRepo;

import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ItemService {

    private final ItemRepo itemRepo;

    public ItemService(ItemRepo itemRepo) {
        this.itemRepo = itemRepo;
    }

    public ItemListDto getItems() {
        ItemListDto itemListDto = ItemListDto.builder().totalPage(1).data(new ArrayList<>()).build();
        for (ItemPo itemPo : itemRepo.findAll()) {
            ItemDto itemDto = ItemDto.builder()
                .id(String.valueOf(itemPo.getId()))
                .name(itemPo.getName())
                .price(itemPo.getPrice())
                .unit(itemPo.getUnit())
                .imageUrl(itemPo.getImageUrl())
                .build();
            itemListDto.getData().add(itemDto);
        }
        return itemListDto;
    }

    public ItemDto addItem(ItemDto itemDto) throws ItemExistException {
        checkItemExist(itemDto.getName());
        ItemPo itemPo = ItemPo.builder()
            .name(itemDto.getName())
            .price(itemDto.getPrice())
            .unit(itemDto.getUnit())
            .imageUrl(itemDto.getImageUrl())
            .build();
        itemRepo.save(itemPo);
        itemDto.setId(String.valueOf(itemPo.getId()));
        return itemDto;
    }

    private void checkItemExist(String itemName) throws ItemExistException {
        if (itemRepo.existsByName(itemName)) {
            throw new ItemExistException("同名商品存在");
        }
    }
}
