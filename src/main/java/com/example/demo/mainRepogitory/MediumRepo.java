package com.example.demo.mainRepogitory;

import com.example.demo.mainEntity.Medium;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MediumRepo extends JpaRepository<Medium,String> {

    @Query("select i from Medium i where i.large.itemCodeLarge = :itemCodeLarge")
    List<Medium> findByLarge_ItemCodeLarge(String itemCodeLarge);
}
