package com.example.demo.mainRepogitory;

import com.example.demo.mainEntity.Large;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LargeRepo extends JpaRepository<Large,String> {
}
