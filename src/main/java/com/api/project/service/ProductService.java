package com.api.project.service;

import com.api.project.domain.Product;
import com.api.project.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Mono<Product> save(Product book){
        return productRepository.save(book);
    }

    public Mono<Product> update(Product product,Long id){
        Mono<Product> oldProduct = productRepository.findById(id);

        return oldProduct.flatMap(x -> {
            x.setCategory(product.getCategory());
            x.setDescription(product.getDescription());
            x.setName(product.getName());
            x.setPrice(product.getPrice());
            x.setImage(product.getImage());

            return productRepository.save(x);
        });
    }

    public Mono<Product> findById(Long id){

        return productRepository.findById(id);
    }

    public Flux<Product> findAll(){

        return productRepository.findAll();
    }

    public Mono<Void> delete(Long id){
        return productRepository.deleteById(id);
    }

}
