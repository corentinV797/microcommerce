package com.ecommerce.microcommerce.dao;

import com.ecommerce.microcommerce.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductDaoImpl implements ProductDao {
    public static List<Product> productList = new ArrayList<Product>();
    static {
        productList.add(new Product(1, new String("vacuum cleaner"), 200, 100));
        productList.add(new Product(2, new String("dish washer"), 300, 175));
        productList.add(new Product(3, new String("fridge"), 550, 400));
    }
    @Override
    public List<Product> findAll() {
        return productList;
    }

    @Override
    public Product findById(int id) {
        Product p = productList.stream()
                .filter(product -> id == product.getId())
                .findAny()
                .orElse(null);
        return p;
    }

    @Override
    public Product save(Product p) {
        productList.add(p);
        return p;
    }
}
