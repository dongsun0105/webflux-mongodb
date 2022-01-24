package com.ecle.study.webflux.domain.repository;

import com.ecle.study.webflux.domain.Cart;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface CartReactiveRepository extends ReactiveCrudRepository<Cart, String> {
}
