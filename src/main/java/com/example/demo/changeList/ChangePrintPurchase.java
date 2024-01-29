package com.example.demo.changeList;

import lombok.*;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ChangePrintPurchase {

    private String itemName;
    private Long itemCount;
    private Long Price;
    private Date ProcurementDate; //입고예정일



}
