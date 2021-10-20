package com.ecommerce.microcommerce.dao;

import com.ecommerce.microcommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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
    @Query(value = "SELECT id, name, price, purchase_price FROM Product p WHERE p.price < :priceLimit", nativeQuery = true)
    public List<Product> lookForCheapProduct(@Param("priceLimit") int price);
}
