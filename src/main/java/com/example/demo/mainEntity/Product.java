package com.example.demo.mainEntity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Product { //제품

    @Id
    @Column(length = 20, nullable = false)
    private String ProductCode; //

    @Column(length = 20 ,nullable = false)
    private String ProductName; //

}
