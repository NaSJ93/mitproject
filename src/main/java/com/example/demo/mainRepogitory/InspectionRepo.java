package com.example.demo.mainRepogitory;

import com.example.demo.mainEntity.Inspection;
import com.example.demo.mainEntity.InspectionID;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface InspectionRepo extends JpaRepository<Inspection, Long> {
//Date1 값이 있음 ->검수 일정 등록했다
    @Query("select i from Inspection i where i.inspectionDate1 is not null")
    List<Inspection> findInspectionDate1isNotNull();
//Date1 값이 있음 ->검수 일정 등록했다  + 검수완료x 인것까지
    @Query("select i from Inspection i where i.inspectionDate1 is not null and i.inspectionComplete is null")
    List<Inspection> findInspectionDate1isNotNullandCompleisNull();

//미완료 진척검수
    @Query("select i from Inspection i where i.inspectionComplete is not null")
    List<Inspection> findInspecCompleteIsNotNull();

    @Query(value = "select * from inspection where purchase_order_sheet_purchase_code=:purcode" ,nativeQuery = true)
    Inspection findByPcode(String purcode);
    
//검수 내용 입력 3개
    @Transactional
    @Modifying
    @Query("UPDATE Inspection i SET i.inspectionContent1 = :con WHERE i.purchaseOrderSheet.id.purchaseCode=:purcode")
    void insertContent1(String con,String purcode);
    @Transactional
    @Modifying
    @Query("UPDATE Inspection i SET i.inspectionContent2 = :con WHERE i.purchaseOrderSheet.id.purchaseCode=:purcode")
    void insertContent2(String con,String purcode);
    @Transactional
    @Modifying
    @Query("UPDATE Inspection i SET i.inspectionContent3 = :con WHERE i.purchaseOrderSheet.id.purchaseCode=:purcode")
    void insertContent3(String con,String purcode);
    
//진척검수 완료 등록
    @Transactional
    @Modifying
    @Query("UPDATE Inspection i SET i.inspectionComplete = :date WHERE i.purchaseOrderSheet.id.purchaseCode=:purcode")
    void insertComplete(Date date,String purcode);




    /*@Query("insert into Inspection i (i.inspectionDate1,i.purchaseOrderSheet) value (:date,:purcode)")
    void insertDate1(Date date,String purcode);

    @Transactional      //현재 작동 x
    @Modifying
    @Query("UPDATE ItemInfo i SET i.contract = :contract WHERE i.itemCode IN :itemCodes")

*/
/*
    INSERT INTO 테이블 이름 (열1, 열2, ...)
    VALUE (값1, 값2 , ….)
    */

}
