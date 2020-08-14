package com.twuc.finalbackend.controller;

import com.twuc.finalbackend.repository.ItemRepo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@SpringBootTest
@AutoConfigureMockMvc
public class ItemControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void should_return_items_when_get_item_given_nothing() throws Exception {
        mockMvc.perform(get("/item"))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json"))
            .andExpect(jsonPath("$.data[0].name").value("可乐"))
            .andExpect(jsonPath("$.data[0].price").value("2"))
            .andExpect(jsonPath("$.data[0].unit").value("瓶"))
            .andExpect(jsonPath("$.data[1].name").value("雪碧"))
            .andExpect(jsonPath("$.data[1].price").value("12"))
            .andExpect(jsonPath("$.data[1].unit").value("箱"));
    }
}
