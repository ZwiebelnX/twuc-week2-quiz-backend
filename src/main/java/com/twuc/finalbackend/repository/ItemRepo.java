package com.twuc.finalbackend.repository;

import com.twuc.finalbackend.models.po.ItemPo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ItemRepo extends PagingAndSortingRepository<ItemPo, Long> {

    ItemPo findByName(String name);

    boolean existsByName(String name);

    void deleteByName(String name);
}
