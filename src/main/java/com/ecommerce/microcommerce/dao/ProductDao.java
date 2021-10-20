package com.ecommerce.microcommerce.dao;

import com.ecommerce.microcommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDao extends JpaRepository<Product, Integer> {
    public List<Product> findAll();
    public Product findById(int id);
    public List<Product> findByPriceGreaterThan(int limitPrice);
    public Product findByNameLike(String search);
    public Product save(Product p);
    public void deleteById(int id);
}
