package com.twuc.finalbackend.models.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "cart")
public class CartPo {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;

    @OneToOne
    @JoinColumn(name = "item_id")
    private ItemPo itemPo;

    @Column(name = "quantity")
    private int quantity;
}
