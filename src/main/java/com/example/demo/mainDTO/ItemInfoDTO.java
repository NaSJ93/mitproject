package com.example.demo.mainDTO;

import com.example.demo.mainEntity.Large;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemInfoDTO {

    private String itemCode;

    private String itemName;

    private String standard;

    private String texture;

    private Long itemCount;

    private Long inventory;

    private String drawingNumber;

    private String drawingImage;

    private String imageType;

    private ProductDTO ProductDTO;    //product

    private ContractDTO ContractDTO; //Contract

    private String Medium; // Medium

    private String large;

    private Long price;

    private Long leadTime;

}
