package com.example.demo.changeList;


import lombok.*;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ChangeProcurement {
    private String proCode;
    private String itemCode;

    private String itemName;

    private String standard;

    private String texture;

    private String drawingNumber;

    private String drawingImage;
    private String imageType;

    private Long LeadTime; //LeadTime

    private String vendorName;//거래처

    private Date productionDate;

    private Long productionQuantity;

    private Long itemCount;

    private Long inventory;

}
