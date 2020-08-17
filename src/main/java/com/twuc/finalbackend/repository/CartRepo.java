package com.twuc.finalbackend.repository;

import com.twuc.finalbackend.models.po.CartPo;
import com.twuc.finalbackend.models.po.ItemPo;

import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

public interface CartRepo extends CrudRepository<CartPo, Long> {

    CartPo findByItemPo(ItemPo itemPo);

    boolean existsByItemPo(ItemPo itemPo);

    @Transactional
    void deleteByItemPo(ItemPo itemPo);

}
