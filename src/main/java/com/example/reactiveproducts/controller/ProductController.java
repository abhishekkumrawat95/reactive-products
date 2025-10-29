package com.example.reactiveproducts.controller;

import com.example.reactiveproducts.model.Product;
import com.example.reactiveproducts.repository.ProductRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/products") // All URLs start with /products
public class ProductController {

    private final ProductRepository productRepository;

    // Constructor Injection: Spring provides the repository instance
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // Handles GET requests to /products
    @GetMapping
    public Flux<Product> getAllProducts() {
        System.out.println("Request received for all products");
        // Just return the Flux from the repository
        return productRepository.findAll();
    }

    // Handles GET requests to /products/{id} (e.g., /products/1)
    @GetMapping("/{id}")
    public Mono<ResponseEntity<Product>> getProductById(@PathVariable Integer id) {
        System.out.println("Request received for product ID: " + id);
        // Get the Mono from the repository
        return productRepository.findById(id)
                // If the Mono has a value (product found):
                .map(product -> ResponseEntity.ok(product)) // Transform it to a 200 OK response
                // If the Mono is empty (product not found):
                .defaultIfEmpty(ResponseEntity.notFound().build()); // Return a 404 Not Found response
    }
}