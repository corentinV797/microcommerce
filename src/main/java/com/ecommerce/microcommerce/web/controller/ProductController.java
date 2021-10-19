package com.ecommerce.microcommerce.web.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
    @RequestMapping(value = "/Products", method = RequestMethod.GET)
    public String productList() {
        return "An example of product";
    }

    @RequestMapping(value = "/Products/{id}", method = RequestMethod.GET)
    public String displayAProduct(@PathVariable int id) {
        return "You have asked a product with the id " + id;
    }
}
