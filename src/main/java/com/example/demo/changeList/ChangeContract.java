package com.example.demo.changeList;

import jakarta.persistence.Column;
import lombok.*;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ChangeContract {
    private String ContractCode;

    private String VendorName;

    private String BusinessLicense;

    private Date ContractDate;

    private String itemName; // 품목명

    private String ContractScan; //ContractScan

    private String imageType;



}
