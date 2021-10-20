package com.ecommerce.microcommerce.web.controller;

import com.ecommerce.microcommerce.dao.ProductDao;
import com.ecommerce.microcommerce.model.Product;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class ProductController {
    @Autowired
    ProductDao productDao;
    @GetMapping(value = "/Products")
    public MappingJacksonValue productList() {
        Iterable<Product> products = productDao.findAll();
        SimpleBeanPropertyFilter myFilter = SimpleBeanPropertyFilter.serializeAllExcept("purchasePrice");
        FilterProvider filterList = new SimpleFilterProvider().addFilter("myDynamicFilter", myFilter);
        MappingJacksonValue filteredProducts = new MappingJacksonValue(products);
        filteredProducts.setFilters(filterList);
        return filteredProducts;
    }

    @GetMapping(value = "/Products/{id}")
    public Product displayAProduct(@PathVariable int id) {
        return productDao.findById(id);
    }

    @GetMapping(value = "/Products/LimitPrice/{limitPrice}")
    public List<Product> displayProductPriceGreaterThan(@PathVariable int limitPrice) {
        return productDao.findByPriceGreaterThan(limitPrice);
    }

    @GetMapping(value = "/Products/Name/{search}")
    public Product displayProductByName(@PathVariable String search) {
        return productDao.findByNameLike("%" + search + "%");
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

    @DeleteMapping(value = "/Products/{id}")
    public void deleteProduct(@PathVariable int id) {
        productDao.deleteById(id);
    }
}
