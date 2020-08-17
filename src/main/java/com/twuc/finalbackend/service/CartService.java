package com.twuc.finalbackend.service;

import com.twuc.finalbackend.models.dto.CartItemDto;
import com.twuc.finalbackend.models.dto.CartListDto;
import com.twuc.finalbackend.models.dto.PostCartDto;
import com.twuc.finalbackend.models.exception.ItemNotExistException;
import com.twuc.finalbackend.models.po.CartPo;
import com.twuc.finalbackend.models.po.ItemPo;
import com.twuc.finalbackend.repository.CartRepo;

import org.springframework.stereotype.Service;

import java.util.ArrayList;

import javax.transaction.Transactional;

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

    public CartListDto getCartItemList() {
        CartListDto cartListDto = CartListDto.builder().totalPage(1).data(new ArrayList<>()).build();
        for (CartPo cartPo : cartRepo.findAll()) {
            CartItemDto cartItemDto = CartItemDto.builder()
                .itemId(String.valueOf(cartPo.getItemPo().getId()))
                .name(cartPo.getItemPo().getName())
                .price(cartPo.getItemPo().getPrice())
                .unit(cartPo.getItemPo().getUnit())
                .quantity(cartPo.getQuantity())
                .build();
            cartListDto.getData().add(cartItemDto);
        }
        return cartListDto;
    }

    public void deleteItem(PostCartDto postCartDto) throws ItemNotExistException {
        ItemPo itemPo = itemService.getItemPo(Long.parseLong(postCartDto.getId()));
        cartRepo.deleteByItemPo(itemPo);
    }
}
