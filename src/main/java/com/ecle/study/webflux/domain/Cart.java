package com.ecle.study.webflux.domain;

import com.ecle.study.webflux.domain.vo.CartItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class Cart {

    @Id
    private String id;
    private List<CartItem> cartItemList;

    public Cart(String id) {
        this(id, new ArrayList<CartItem>());
    }

    public void removeItem(CartItem cartItem) {
        this.cartItemList.remove(cartItem);
    }

}
