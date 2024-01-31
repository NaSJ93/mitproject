package com.example.demo.mainDTO;

import com.example.demo.mainEntity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
//VendorPlanController 에서 생산계획 등록할때 사용하는 DTO ProductionPlan.html의 30번째 줄
@Builder
@NoArgsConstructor//인수가 없는 생성자를 생성합니다.
@AllArgsConstructor//모든 인수를 갖는 생성자를 생성합니다.
@Data

public class ProductionPlanToEntityDTO {
    private String productionPk;
    private Long productionQuantity;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date productionDate;
    private Product product;

    @Builder.Default
    private Boolean complete = false;

//    private String ProductionCode; //PK
//
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
//    private Date ProductionDate;//생산 예정일
//
//
//    private Long ProductionQuantity; //생산 소요량



}
