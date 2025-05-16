package com.api.project.controller;

import com.api.project.domain.Product;
import com.api.project.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.spring6.context.webflux.IReactiveDataDriverContextVariable;
import org.thymeleaf.spring6.context.webflux.ReactiveDataDriverContextVariable;
import reactor.core.publisher.Flux;

import java.time.Duration;

@Controller
@RequiredArgsConstructor
@RequestMapping("/web/products")
public class ProductWebController {

    private final ProductRepository productRepository;

    @GetMapping()
    public String findAll(Model model){
        Flux<Product> all = productRepository.findAll();
        Flux<Product> productFlux = Flux.merge(all.skipLast(1), all.skip(1)).delayElements(Duration.ofSeconds(1));
        IReactiveDataDriverContextVariable products = new ReactiveDataDriverContextVariable(productFlux,1);
        model.addAttribute("products",products);
        return "products";
    }

}
