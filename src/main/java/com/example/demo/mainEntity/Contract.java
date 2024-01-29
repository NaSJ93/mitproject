package com.example.demo.mainEntity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Builder
@AllArgsConstructor@NoArgsConstructor
@Getter
public class Contract { //계약서

    @Id
    @Column(length = 20, nullable = false)
    private String contractCode; //

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date contractDate; //ContractDate

    @Lob
    @Column(columnDefinition = "MEDIUMTEXT",nullable = false)
    private String contractScan; //ContractScan

    @Column
    private String imageType;

    @Column(columnDefinition = "TEXT")
    private String significant; //비고 Significant

    @ManyToOne(fetch = FetchType.LAZY)
    private Vendor vendor;

}