package com.example.demo.mainRepogitory;

import com.example.demo.mainEntity.ProcurementId;
import com.example.demo.mainEntity.ProcurementPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface ProcurementPlanRepo extends JpaRepository<ProcurementPlan, ProcurementId> {

    @Query(value = "select * from procurement_plan where production_plan_production_pk=:procode and item_info_item_code=:itemcode",nativeQuery = true)
    ProcurementPlan find(@Param("procode") String procode, @Param("itemcode") String itemcode);

//생산계획코드로 검색해서 저장되어 있는것만 출력
    List<ProcurementPlan> findById_ProductionPlan_ProductionPk(String productionPlanProductionPk);
//품목코드로 검색해서 저장되어 있는것만 출력
    List<ProcurementPlan> findById_ItemInfo_ItemCode(String itemInfoItemCode);

    @Modifying
    @Query(value = "INSERT INTO procurement_plan (procurement_date, procurement_quantity, production_plan_production_pk,item_info_item_code) VALUES (:date,:quantity,:procode,:itemcode)", nativeQuery = true)
    void updateProcure(@Param("date") Date date, @Param("quantity") Long quantity,@Param("procode") String procode, @Param("itemcode") String itemcode);


    @Query("SELECT p FROM ProcurementPlan p WHERE p.ProcurementDate BETWEEN :startDate AND :endDate")
    List<ProcurementPlan> findByDateBetween(Date startDate, Date endDate);

}
