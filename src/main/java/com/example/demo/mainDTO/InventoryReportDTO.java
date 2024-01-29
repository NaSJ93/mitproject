package com.example.demo.mainDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventoryReportDTO {
    private String itemCode;
    private String itemName;
    private Long price;
    private Long initialQuantity;
    private Long inboundQuantity;
    private Long outboundQuantity;
}
