package com.example.demo.mainRepogitory;

import com.example.demo.mainEntity.ProductionPlan;
import com.example.demo.mainEntity.PurchaseOrderSheet;
import com.example.demo.mainEntity.PurchaseOrderSheetId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface PurchaseOrderRepo extends JpaRepository<PurchaseOrderSheet, PurchaseOrderSheetId> {
    //발주서 코드 자동 생성을 위한 숫자 세기 용도
    @Query(value = "select * from purchase_order_sheet", nativeQuery = true)
    List<PurchaseOrderSheet> countPurchase();

    //발주서 등록
    @Modifying
    @Query(value ="INSERT INTO purchase_order_sheet (item_info_item_code, purchase_code, production_plan_production_pk) VALUES (:item, :pur, :pro)", nativeQuery = true)
    void savePurandItem(@Param("item") String item,@Param("pur") String pur,@Param("pro") String pro);

    //PurchaseDate값이 비어있는 발주서 검색 => 미발행 발주서
    @Query(value ="select * from purchase_order_sheet where purchase_date is null " ,nativeQuery = true)
    List<PurchaseOrderSheet> findnyPurchaseDateIsNull();

//발주서번호 입력하면 해당하는 값만 출력
    @Query(value = "select * from purchase_order_sheet where purchase_code=:purcode",nativeQuery = true)
    List<PurchaseOrderSheet> findItemInfobyPurchaseCode(String purcode);

//생산번호 입력하면 해당하는 값만 출력
    @Query(value = "select * from purchase_order_sheet where production_plan_production_pk=:procode",nativeQuery = true)
    List<PurchaseOrderSheet> findItemInfobyProCode(String procode);

//PurchaseDate 값 입력 => 발주서 발행
    @Query(value ="update purchase_order_sheet set purchase_date=:date where purchase_code=:code",nativeQuery = true)
    void startPurchase(@Param("date") Date date,@Param("code") String code);

//PurchaseDate값이 들어있는 발주서 검색 => 발행 발주서
    @Query(value ="select * from purchase_order_sheet where purchase_date is not null " ,nativeQuery = true)
    List<PurchaseOrderSheet> findnyPurchaseDateIsNotNull();

//발주서 검색 (품목과 발주서 번호로 -> 생산계획id 찾기 위해)
    @Query(value = "select production_plan_production_pk from purchase_order_sheet where purchase_code=:purcode and item_info_item_code=:itemcode LIMIT 1",nativeQuery = true)
    String findProcodbyPurItem(String purcode,String itemcode);

//InvoiceDate값이 없고 PurchaseDate값이 있는 명세서 검색 => 미발행 명세서
    @Query(value ="select * from purchase_order_sheet where invoice_date is null and purchase_date is not null " ,nativeQuery = true)
    List<PurchaseOrderSheet> findNotStarInvoiceDate();




//명세서 발행
    @Query(value ="update purchase_order_sheet set invoice_date=:date where purchase_code=:code",nativeQuery = true)
    void startInvoice(@Param("date") Date date,@Param("code") String code);
/*


    @Query(value = "select count(*) from purchase_code",nativeQuery = true)
    int countPurchase();



//InvoiceDate값이 들어있는 명세서 검색 => 발행 명세서
    @Query(value ="select * from purchase_order_sheet where invoice_date is not null " ,nativeQuery = true)
    List<PurchaseOrderSheet> findnyInvoiceDateIsNotNull();

*/




//////////////PurchaseOrderSheetItemInfo
//itemcode 입력하면 해당하는 발주서 번호 출력
    @Query(value = "select * from purchase_order_sheet where production_plan_production_pk=:procode and item_info_item_code=:itemcode limit 1",nativeQuery = true)
    PurchaseOrderSheet findItemInfobyItemCode(String procode,String itemcode);

    @Query(value = "select production_plan_production_pk from purchase_order_sheet where item_info_item_code=:itemcode limit 1",nativeQuery = true)
    String findProStringobyItemCode(String itemcode);


/*




    @Modifying
    @Query(value ="INSERT INTO purchase_order_sheet_item_info (item_info_item_code, purchase_order_sheet_purchase_code) VALUES (:item, :pur)", nativeQuery = true)
    void savePurandItem(@Param("item") String item,@Param("pur") String pur);


    @Query(value = "select count(*) from purchase_order_sheet_item_info where purchase_order_sheet_purchase_code=:purcode",nativeQuery = true)
    int countPurcode(String purcode);*/

}