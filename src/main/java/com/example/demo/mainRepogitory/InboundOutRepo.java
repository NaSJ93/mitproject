package com.example.demo.mainRepogitory;

import com.example.demo.mainEntity.InboundOutbound;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;


public interface InboundOutRepo extends JpaRepository<InboundOutbound,String> {

    /*@Query("update InboundOutbound i set i.InboundDate=:date, i.InboundQuantity=:quantity where i.itemInfo=:code")
    void updateDateQuan(String code,Date date,Long quantity);*/

//입고 관련
    //입고처리
    @Query(value = "update inbound_outbound set inbound_date=:date, inbound_quantity=:quantity where item_info_item_code=:code",nativeQuery = true)
    void updateDateQuan(String code,Date date,Long quantity);
    //입고처리 된것 출력
    @Query(value = "select * from inbound_outbound where inbound_date is not null",nativeQuery = true)
    List<InboundOutbound> findbyInboundDateisNotNull();
    //입고처리 된 품목 출력
    @Query(value = "select count(*) from inbound_outbound where item_info_item_code=:itemcode and inbound_date is not null",nativeQuery = true)
    int checkInbounding(String itemcode);
    //해당 아이템 코드가 입력된 입고출고DB 출력
    @Query(value = "select * from inbound_outbound where item_info_item_code=:itemcode",nativeQuery = true)
    InboundOutbound findbyItemCode(String itemcode);
    //해당 아이템 코드가 입력되고 입고되지 않은 입고출고DB 출력
    @Query(value = "select * from inbound_outbound where inbound_date is null and item_info_item_code=:itemcode",nativeQuery = true)
    InboundOutbound findbyItemCodeAndInisNull(String itemcode);


//출고관련
    //출고처리
    @Query(value = "update inbound_outbound set outbound_date=:date, outbound_quantity=:quantity where item_info_item_code=:code",nativeQuery = true)
    void updateOutDateQuan(String code,Date date,Long quantity);

    //출고목록 안된거 뽑기
    @Query("select i from InboundOutbound i where i.inboundQuantity is not null and i.outboundQuantity is null")
    List<InboundOutbound> findOutbound();
    @Query("select i from InboundOutbound i where i.inboundDate is not null and i.outboundDate is null")
    List<InboundOutbound> findNotStartOutbound();


}
