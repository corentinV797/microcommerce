package com.ecommerce.microcommerce.web.controller;

import com.ecommerce.microcommerce.model.Product;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {
    @GetMapping(value = "/Products")
    public String productList() {
        return "An example of product";
    }

    @GetMapping(value = "/Products/{id}")
    public Product displayAProduct(@PathVariable int id) {
        Product p = new Product(id, "Fridge", 500);
        return p;
    }
}
