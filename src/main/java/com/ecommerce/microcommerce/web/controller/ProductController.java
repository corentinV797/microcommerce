package com.ecommerce.microcommerce.web.controller;

import com.ecommerce.microcommerce.dao.ProductDao;
import com.ecommerce.microcommerce.model.Product;
import com.ecommerce.microcommerce.web.exceptions.ProductNotFindException;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@Api(description = "API for CRUD operations on products")
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

    @ApiOperation("Retrieve a product thanks to its id if the product exists")
    @GetMapping(value = "/Products/{id}")
    public Product displayAProduct(@PathVariable int id) throws ProductNotFindException {
        Product product = productDao.findById(id);
        if (product == null) {
            throw new ProductNotFindException("Product with id : " + id + " cannot be found");
        }
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
    public ResponseEntity<Void> addProduct(@RequestBody @Valid Product p) {
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

    @PutMapping(value = "/Products")
    public void updateProduct(@RequestBody Product product) {
        productDao.save(product);
    }

    @GetMapping(value = "/Products/CheapPrice/{price}")
    public List<Product> getCheapProduct(@PathVariable int price) {
        return productDao.lookForCheapProduct(price);
    }

    @GetMapping(value = "/test/Products/{id}")
    public Product toBeHidden(@PathVariable int id){
        return productDao.findById(id);
    }
}
