package com.example.reactiveproducts.repository;

import com.example.reactiveproducts.model.Product;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository // Tells Spring this is a data access component
public class ProductRepository {

    // Simple in-memory storage (like a temporary database)
    private static final Map<Integer, Product> productMap = new ConcurrentHashMap<>();

    // Add some sample data when the class loads
    static {
        productMap.put(1, new Product(1, "Laptop", 1200.50));
        productMap.put(2, new Product(2, "Mouse", 45.99));
        productMap.put(3, new Product(3, "Keyboard", 110.00));
        productMap.put(4, new Product(4, "Monitor", 350.75));
    }

    // Method to get all products
    public Flux<Product> findAll() {
        // Flux.fromIterable converts our list to a reactive stream
        return Flux.fromIterable(productMap.values())
                .delayElements(Duration.ofMillis(500)); // Add 0.5s delay between items
    }

    // Method to get one product by ID
    public Mono<Product> findById(Integer id) {
        // Get product from map. If ID doesn't exist, productMap.get(id) is null.
        // Mono.justOrEmpty converts the result (or null) into a Mono.
        return Mono.justOrEmpty(productMap.get(id));
    }
}