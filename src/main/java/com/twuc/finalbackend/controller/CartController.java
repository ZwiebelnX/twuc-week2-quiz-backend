package com.twuc.finalbackend.controller;

import com.twuc.finalbackend.models.dto.PostCartDto;
import com.twuc.finalbackend.models.exception.ItemNotExistException;
import com.twuc.finalbackend.service.CartService;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/cart")
    public ResponseEntity<String> addItemToCart(@RequestBody PostCartDto postCartDto) throws ItemNotExistException {
        cartService.addItemToCart(postCartDto);
        return ResponseEntity.ok("");
    }

}
