package com.ecle.study.webflux.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Item {

    @Id
    private String id;
    private String name;
    private String description;
    private double price;

    public Item(String name, String description, double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

}
