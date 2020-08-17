package com.twuc.finalbackend.models.po;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
@Table(name = "item")
public class ItemPo {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;

    @Column(name = "name", length = 40)
    private String name;

    @Column(name = "price")
    private int price;

    @Column(name = "unit", length = 20)
    private String unit;

    @Column(name = "image_url", length = 255)
    private String imageUrl;

    @OneToOne(cascade = CascadeType.REMOVE, mappedBy = "itemPo")
    private CartPo cartPo;
}
