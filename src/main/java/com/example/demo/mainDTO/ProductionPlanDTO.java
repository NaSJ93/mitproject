package com.example.demo.mainDTO;

import com.example.demo.mainEntity.Product;
import lombok.*;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ProductionPlanDTO {

    private String productionPk;
    private Long productionQuantity;
    private Date productionDate;
    private ProductDTO product;



}
