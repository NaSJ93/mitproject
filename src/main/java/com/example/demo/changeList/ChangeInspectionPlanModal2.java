package com.example.demo.changeList;

import lombok.*;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ChangeInspectionPlanModal2 {
    private String order;
    private String purchaseCode;
    private Date inspectionDate;
    private String inspection_content;

 /*   private Date inspectionDate1;
    private Date inspectionDate2;
    private Date inspectionDate3;

    private String inspectionContent1; // 검수내용
    private String inspectionContent2; // 검수내용
    private String inspectionContent3; // 검수내용*/

}
