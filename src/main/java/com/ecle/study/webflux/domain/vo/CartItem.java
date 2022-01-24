package com.ecle.study.webflux.domain.vo;

import com.ecle.study.webflux.domain.Item;
import lombok.Data;

@Data
public class CartItem {

    private Item item;
    private int quantity;

    public void increment() {
        this.quantity += 1;
    }

    public void decrement() {
        this.quantity -= 1;
    }

    public boolean isOne() {
        return quantity == 1;
    }
}
