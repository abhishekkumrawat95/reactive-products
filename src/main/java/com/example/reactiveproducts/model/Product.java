package com.example.reactiveproducts.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Makes getters, setters, toString, etc.
@NoArgsConstructor // Makes default constructor
@AllArgsConstructor // Makes constructor with all fields
public class Product {
    private Integer id;
    private String name;
    private Double price;
}