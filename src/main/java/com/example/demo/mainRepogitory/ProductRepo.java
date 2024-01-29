package com.example.demo.mainRepogitory;

import com.example.demo.mainEntity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product,String> {
}
