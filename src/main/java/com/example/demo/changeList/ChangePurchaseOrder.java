package com.example.demo.changeList;

import lombok.*;

import java.util.Date;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ChangePurchaseOrder {

    private String itemCode;
    private String itemName;
    private Long quantity;
    private Date contractDate;
    private String businessLicense;
    private String vendorName;
    private Long price;
}
