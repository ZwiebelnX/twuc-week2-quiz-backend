package com.twuc.finalbackend.service;

import com.twuc.finalbackend.models.dto.PostCartDto;
import com.twuc.finalbackend.models.exception.ItemNotExistException;
import com.twuc.finalbackend.models.po.CartPo;
import com.twuc.finalbackend.models.po.ItemPo;
import com.twuc.finalbackend.repository.CartRepo;

import org.springframework.stereotype.Service;

@Service
public class CartService {

    private final CartRepo cartRepo;

    private final ItemService itemService;

    public CartService(CartRepo cartRepo, ItemService itemService) {
        this.cartRepo = cartRepo;
        this.itemService = itemService;
    }

    public void addItemToCart(PostCartDto postCartDto) throws ItemNotExistException {
        ItemPo itemPo = itemService.getItemPo(Long.parseLong(postCartDto.getId()));
        CartPo cartPo = cartRepo.findByItemPo(itemPo);
        if (cartPo == null) {
            cartPo = CartPo.builder().itemPo(itemPo).quantity(1).build();
        } else {
            cartPo.setQuantity(cartPo.getQuantity() + 1);
        }
        cartRepo.save(cartPo);
    }
}
