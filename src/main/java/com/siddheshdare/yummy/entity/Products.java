package com.siddheshdare.yummy.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Table;

@Table(name="products")
public class Products{
    @Column(name="id")
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="price")
    private Long price;
}