package com.api.project.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    void testGetNames(){
        Flux<String> names = userService.getNames();

        names.subscribe(System.out::println);

        StepVerifier.create(names)
                .expectNextCount(4)
                .verifyComplete();
    }

    @Test
    void testGetCharsFromNames(){
        Flux<String> names = userService.getCharsFromNames();

        names.subscribe(System.out::println);

        StepVerifier.create(names)
                .expectNextCount(30)
                .verifyComplete();
    }

    @Test
    void testTransformsGetNames(){
        Flux<String> names = userService.getNames();

        Flux<String> transform = names.transform(x -> x.map(String::toUpperCase));

        transform.subscribe(System.out::println);

        StepVerifier.create(transform)
                .expectNextCount(4)
                .verifyComplete();
    }

    @Test
    void testFilterGetNames(){
        Flux<String> names = userService.getNames();

        Flux<String> filter = names
                .filter(x -> x.length() <= 5)
                //.switchIfEmpty(names);
                .defaultIfEmpty("No name");

        filter.subscribe(System.out::println);

        StepVerifier.create(filter)
                .expectNextCount(1)
                .verifyComplete();
    }

    @Test
    void testConcatGetNames(){
        Flux<String> names = userService.getNames();
        Flux<String> lastnames = userService.getLastnames();

        Flux<String> concat = Flux.concat(names, lastnames);

        concat.subscribe(System.out::println);

        StepVerifier.create(concat)
                .expectNextCount(8)
                .verifyComplete();
    }

    @Test
    void testMergeGetNames(){
        Flux<String> names = userService.getNames();
        Flux<String> lastnames = userService.getLastnames();

        Flux<String> merged = Flux.merge(names, lastnames);

        merged.subscribe(System.out::println);

        StepVerifier.create(merged)
                .expectNextCount(8)
                .verifyComplete();
    }

}