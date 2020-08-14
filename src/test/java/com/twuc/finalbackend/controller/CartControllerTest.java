package com.twuc.finalbackend.controller;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.twuc.finalbackend.models.dto.PostCartDto;
import com.twuc.finalbackend.models.po.ItemPo;
import com.twuc.finalbackend.repository.CartRepo;
import com.twuc.finalbackend.repository.ItemRepo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CartControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper;

    @Autowired
    private ItemRepo itemRepo;

    @Autowired
    private CartRepo cartRepo;

    public CartControllerTest() {
        this.objectMapper = new ObjectMapper();
        objectMapper.configure(MapperFeature.USE_ANNOTATIONS, false);
    }


    @Test
    @Transactional
    public void should_add_new_item_to_cart_when_post_cart_given_new_item() throws Exception {

        ItemPo itemPo = itemRepo.findById((long)1).get();
        cartRepo.deleteByItemPo(itemPo);
        PostCartDto postCartDto = PostCartDto.builder().id("1").build();
        mockMvc.perform(post("/cart").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(postCartDto)))
            .andExpect(status().isOk());
        assertTrue(cartRepo.existsByItemPo(itemPo));
    }

    @Test
    @Transactional
    public void should_add_quantity_when_post_cart_given_exist_item() throws Exception {
        ItemPo itemPo = itemRepo.findById((long)1).get();
        cartRepo.deleteByItemPo(itemPo);
        PostCartDto postCartDto = PostCartDto.builder().id("1").build();
        mockMvc.perform(post("/cart").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(postCartDto)))
            .andExpect(status().isOk());
        mockMvc.perform(post("/cart").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(postCartDto)))
            .andExpect(status().isOk());
        assertEquals(2, cartRepo.findByItemPo(itemPo).getQuantity());
    }

}
