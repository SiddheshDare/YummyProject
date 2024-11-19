package com.siddheshdare.yummy.repo;

import com.siddheshdare.yummy.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    
    @Query("SELECT p FROM Product p WHERE p.price BETWEEN 15 AND 30 ORDER BY p.price ASC LIMIT 2")
    List<Product> findTop2ProductsByPriceRange();
}