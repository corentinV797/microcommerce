package com.ecommerce.microcommerce.web.controller;

import com.ecommerce.microcommerce.dao.ProductDao;
import com.ecommerce.microcommerce.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class ProductController {
    @Autowired
    ProductDao productDao;
    @GetMapping(value = "/Products")
    public List<Product> productList() {
        return productDao.findAll();
    }

    @GetMapping(value = "/Products/{id}")
    public Product displayAProduct(@PathVariable int id) {
        return productDao.findById(id);
    }

    @PostMapping(value = "/Products")
    public ResponseEntity<Void> addProduct(@RequestBody Product p) {
        Product productToAdd = productDao.save(p);
        if (productToAdd == null) {
            return ResponseEntity.noContent().build();
        }
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(productToAdd.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
}
