package com.twuc.finalbackend.controller;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.twuc.finalbackend.models.dto.ItemDto;
import com.twuc.finalbackend.models.po.ItemPo;
import com.twuc.finalbackend.repository.ItemRepo;

import org.junit.jupiter.api.BeforeEach;
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
public class ItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ItemRepo itemRepo;

    private final ObjectMapper objectMapper;

    public ItemControllerTest() {
        this.objectMapper = new ObjectMapper();
        objectMapper.configure(MapperFeature.USE_ANNOTATIONS, false);
    }

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

    @Test
    @Transactional
    public void should_add_item_when_post_item_given_item_info() throws Exception {
        ItemDto itemDto = ItemDto.builder()
            .name("测试洋葱")
            .price(100)
            .unit("头")
            .imageUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&se" +
                "c=1597404506561&di=6a64ba2bfedc2f5ee3a75c937ddfd331&imgtype=0&src=http%3A%2" +
                "F%2Fimg4.imgtn.bdimg.com%2Fit%2Fu%3D1126039374%2C2932139796%26fm%3D214%26gp%3D0.jpg")
            .build();
        itemRepo.deleteByName("测试洋葱");
        mockMvc.perform(post("/item")
            .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(itemDto)))
            .andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON));
        assertTrue(itemRepo.existsByName("测试洋葱"));
    }

    @Test
    public void should_throw_error_when_post_item_given_error_info() throws Exception {
        ItemDto itemDto = ItemDto.builder()
            .name("测试洋葱")
            .price(-1)
            .unit("头")
            .imageUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&se" +
                "c=1597404506561&di=6a64ba2bfedc2f5ee3a75c937ddfd331&imgtype=0&src=http%3A%2" +
                "F%2Fimg4.imgtn.bdimg.com%2Fit%2Fu%3D1126039374%2C2932139796%26fm%3D214%26gp%3D0.jpg")
            .build();
        mockMvc.perform(post("/item")
            .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(itemDto)))
            .andExpect(status().isBadRequest());
    }
}
