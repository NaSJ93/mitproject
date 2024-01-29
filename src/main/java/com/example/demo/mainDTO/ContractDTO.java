package com.example.demo.mainDTO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContractDTO {

    private String contractCode;
    private Date contractDate;
    private String contractScan;
    private String imageType;
    private String significant;

///Vendor
    private String vendorBusinessLicense;
    private String vendorName;
    private String vendorAddress;
    private String vendorPhoneNumber;
    private String vendorEmail;
    private String vendorPIC;

}
