package com.example.demo.changeList;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ChangeIteminfoSearch {
    private String itemCode;
    private String itemName;
    private String itemLarge;
    private String itemMedium;
    private String itemStandard;

    private String itemTexture;
    private Long itemCount;
    private String drawingNumber;
    private String drawingImage;
    private String venderName;
    private String imageType;
    private String contract;
    private String ContractScan;

}
