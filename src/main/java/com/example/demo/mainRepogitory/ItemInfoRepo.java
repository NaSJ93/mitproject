package com.example.demo.mainRepogitory;

import com.example.demo.mainDTO.InventoryReportDTO;
import com.example.demo.mainEntity.*;
import com.example.demo.mainEntity.ItemInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public interface ItemInfoRepo extends JpaRepository<ItemInfo,String> {
    @Transactional      //현재 작동 x
    @Modifying
    @Query("UPDATE ItemInfo i SET i.contract = :contract WHERE i.itemCode IN :itemCodes")
    void updateContractForItems(@Param("contract") Contract contract, @Param("itemCodes") List<String> itemCodes);

    /*@Query("select i,p from ItemInfo i join ProcurementPlan p where i.itemCode=:code")
    List<Object[]> find2(@Param("code") String code);
*/
  /*  @Query("select i,p from ItemInfo i join ProcurementPlan p using(itemCode)")
    List<Objects[]> find2();*//*
    @Query("select i,p from ItemInfo i join ProcurementPlan p where i.itemCode=:code")
    List<Object[]> find2(@Param("code") String code);*/

    /*@Query("select i,p from ItemInfo i join ProcurementPlan p on i.itemCode = p.itemInfo.itemCode where i.itemCode=:code")
    List<Object[]> find2(@Param("code") String code);*/

    @Query("select i from ItemInfo i where i.itemCode=:code")
    ItemInfo findItemCode(@Param("code") String code);
    @Query(value = "select * from item_info where contract_contract_code is not null ",nativeQuery = true)
    List<ItemInfo> findContractIsNotNull();

//재고수량에 값 입력 (이거 할때 주의 사항! : [원래 품목에 있는 기초재고]+[입고수량] 을 입력해야함
    @Transactional
    @Modifying
    @Query("UPDATE ItemInfo i SET i.inventory = :inven WHERE i.itemCode=:itemcode")
    void updateInven(String itemcode,Long inven);

    ////////////////////////////////

}