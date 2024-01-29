package com.example.demo.mainRepogitory;

import com.example.demo.mainEntity.ProductionPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.Date;
import java.util.List;

public interface ProductionPlanRepo extends JpaRepository<ProductionPlan,String> {

//?월 ~ ?월까지 생산계획 검색
    List<ProductionPlan> findByProductionDateBetween(Date startDate, Date endDate);

// ?월 ~ ?월까지 생산계획 검색 및 complete가 ?인 것만 출력 (완료인지 아닌지)
    List<ProductionPlan> findByProductionDateBetweenAndComplete(Date startDate, Date endDate, Boolean complete);

    /*@Query("SELECT p FROM ProductionPlan p WHERE p.productionDate > :date ORDER BY p.productionDate ASC")
    ProductionPlan findNearestPlan(@Param("date") Date date);*/
}