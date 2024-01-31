package com.example.demo.mainDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor//인수가 없는 생성자를 생성합니다.
@AllArgsConstructor//모든 인수를 갖는 생성자를 생성합니다.
@Data

public class VendorDTO {

    private String businessLicense;    //사업자번호
    private String vendorName;//	거래처 이름
    private String vendorAddress;//	거래처 주소
    private String vendorPnumber;    //거래처 전화번호
    private String vendorEmail;    //거래처 이메일
    private String PIC;// 거래담당자
}
