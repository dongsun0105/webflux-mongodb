package com.ecle.study.webflux;

import com.ecle.study.webflux.domain.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.mongodb.core.MongoOperations;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class WebfluxApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebfluxApplication.class, args);
	}

	@Autowired
	MongoOperations mongoOperations;

	@EventListener(ApplicationReadyEvent.class)
	public void doSometing() {
		// db hang 방지용 논블로킹 작업
		mongoOperations.save(new Item("ps5", "description of item1", 20.00));
		mongoOperations.save(new Item("ps5", "description of item2", 10.00));
		mongoOperations.save(new Item("ps5", "description of item3", 40.00));
		mongoOperations.save(new Item("xsx", "description of item4", 20.00));
		mongoOperations.save(new Item("xsx", "description of item5", 15.00));
		mongoOperations.save(new Item("swt", "description of item6", 100.00));
	}

}
