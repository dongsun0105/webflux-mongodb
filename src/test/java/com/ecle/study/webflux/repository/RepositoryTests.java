package com.ecle.study.webflux.repository;

import com.ecle.study.webflux.domain.Item;
import com.ecle.study.webflux.domain.repository.CartReactiveRepository;
import com.ecle.study.webflux.domain.repository.ItemReactiveRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
public class RepositoryTests {

    @Autowired
    ItemReactiveRepository itemReactiveRepository;

    @Autowired
    CartReactiveRepository cartReactiveRepository;

    public Long itemCnt;

    @BeforeEach
    void setUp() {
        StepVerifier
                .create(itemReactiveRepository.deleteAll())
                .verifyComplete();

        List<Item> itemList = Arrays.asList(
                new Item("ps5", "description of item1", 20.00),
                new Item("ps5", "description of item2", 10.00),
                new Item("ps5", "description of item3", 40.00),
                new Item("xsx", "description of item4", 20.00),
                new Item("xsx", "description of item5", 15.00),
                new Item("swt", "description of item6", 100.00));

        itemCnt = Long.valueOf(itemList.size());

        StepVerifier
                .create(itemReactiveRepository.saveAll(itemList))
                .expectNextMatches(item -> {
                    System.out.println(item.toString());
                    return true;
                })
                .expectNextCount(itemCnt - 1)
                .verifyComplete();
    }

    @Test
    public void itemRepositoryCount() {
        StepVerifier
                .create(itemReactiveRepository.findAll().count())
                .expectNextMatches(cnt -> {
                    assertThat(cnt).isEqualTo(itemCnt);
                    return true;
                })
                .verifyComplete();
    }

    @Test
    public void itemSearchName() {
        StepVerifier
                .create(itemReactiveRepository.findByNameContaining("ps"))
                .expectNextMatches(item -> {
                    System.out.println(item.toString());
                    return true;
                })
                .expectNextCount(2)
                .verifyComplete();
    }

    @Test
    public void itemSearchNameDetail() {
        StepVerifier
                .create(itemReactiveRepository.findByName("swt"))
                .expectNextMatches(item -> {
                    assertThat(item.getId()).isNotNull();
                    assertThat(item.getName()).isEqualTo("swt");
                    assertThat(item.getDescription()).isEqualTo("description of item6");
                    assertThat(item.getPrice()).isEqualTo(100.00);
                    
                    return true;
                })
                .verifyComplete();
    }

    @Test
    public void itemSearchNameCount() {
        StepVerifier
                .create(itemReactiveRepository.findByNameContaining("ps").count())
                .expectNextMatches(cnt -> {
                    assertThat(cnt).isEqualTo(3);
                    return true;
                })
                .verifyComplete();
    }

}
