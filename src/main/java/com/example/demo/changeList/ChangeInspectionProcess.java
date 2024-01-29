package com.example.demo.changeList;

import lombok.*;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ChangeInspectionProcess {

    //발주서번호	품목코드	품목명	규격	진척도	입고예정일	진척검수	거래처	담당자메일	진척검수

   // private String purchaseCode;
    private String itemCode;
    private String itemName;
    private String texture;
    //진척도
    private Date procurementDate;
    //진척검수 정도
    private String vendorName;
    private String vendorEmail;

}
