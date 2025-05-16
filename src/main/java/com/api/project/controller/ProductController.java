package com.api.project.controller;

import com.api.project.domain.Product;
import com.api.project.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

    public final ProductService productService;

    @PostMapping
    public ResponseEntity<Mono<Product>> create(@RequestBody Product product){
        Mono<Product> saved = productService.save(product);

        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Mono<Product>> update(Product product,Long id){
        Mono<Product> updated = productService.update(product, id);

        return new ResponseEntity<>(updated,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mono<Product>> findById(@PathVariable Long id){

        Mono<Product> product = productService.findById(id);

        return new ResponseEntity<>(product,HttpStatus.OK);
    }

    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public ResponseEntity<Flux<Product>> findAll(){

        Flux<Product> products = productService.findAll().delayElements(Duration.ofMillis(500));

        return new ResponseEntity<>(products,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Mono<Void>> delete(@PathVariable Long id){
        Mono<Void> deleted = productService.delete(id);

        return new ResponseEntity<>(deleted,HttpStatus.NO_CONTENT);
    }
}
