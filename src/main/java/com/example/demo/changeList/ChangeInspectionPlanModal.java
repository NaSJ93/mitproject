package com.example.demo.changeList;

import lombok.*;

import java.util.Date;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ChangeInspectionPlanModal {
    private String purchaseCode;
    private String itemCode;
    private String itemName;
    private String texture;
    private Long quantity;
    private Date contractDate;

    private String vendorName;
    private String vendorEmail;

}
