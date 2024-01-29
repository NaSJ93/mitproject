package com.example.demo.changeList;

import lombok.*;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ChangeContractSearch {
    private String contractCode;
    private String vendorName;
    private String businessLicense;
    private Date contractdate;
    private String itemname;
    private String  contractScan;
    private String imageType;
}
