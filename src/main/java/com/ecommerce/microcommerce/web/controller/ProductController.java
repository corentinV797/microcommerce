package com.ecommerce.microcommerce.web.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {
    @GetMapping(value = "/Products")
    public String productList() {
        return "An example of product";
    }

    @GetMapping(value = "/Products/{id}")
    public String displayAProduct(@PathVariable int id) {
        return "You have asked a product with the id " + id;
    }
}
