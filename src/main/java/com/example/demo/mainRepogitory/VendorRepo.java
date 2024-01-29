package com.example.demo.mainRepogitory;

import com.example.demo.mainEntity.ItemInfo;
import com.example.demo.mainEntity.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface VendorRepo extends JpaRepository<Vendor,String> {

    @Query("select i from Vendor i where i.vendorName=:name")
    Vendor findbyName(@Param("name") String name);


}
