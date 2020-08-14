package com.twuc.finalbackend.controller;

import com.twuc.finalbackend.models.dto.ItemListDto;
import com.twuc.finalbackend.service.ItemService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ItemController {
    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/item")
    public ResponseEntity<ItemListDto> getItems() {
        return ResponseEntity.ok(itemService.getItems());
    }
}
