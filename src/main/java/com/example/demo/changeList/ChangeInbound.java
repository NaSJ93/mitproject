package com.example.demo.changeList;

import lombok.*;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ChangeInbound{
    private String purchaseCode;
    private String itemCode;
    private String itemName;
    private Long quantity;
    private Long inventory;
    private Date ProcurementDate;
}
