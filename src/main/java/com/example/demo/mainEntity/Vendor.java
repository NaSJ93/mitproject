package com.example.demo.mainEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Vendor { //거래처

    @Id
    @Column(length = 20, nullable = false)
    private String businessLicense; //
    @Column(length = 50, nullable = false)
    private String vendorName; //
    @Column(length = 255)
    private String vendorAddress; //
    @Column(length = 20)
    private String vendorPnumber; //
    @Column(length = 50)
    private String vendorEmail; //
    @Column(length = 20, nullable = false)
    private String PIC; //

}
