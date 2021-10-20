package com.ecommerce.microcommerce.web.controller;

import com.ecommerce.microcommerce.dao.ProductDao;
import com.ecommerce.microcommerce.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        return productDao.ProductfindById(id);
    }
}
