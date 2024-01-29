package com.example.demo.mainEntity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;


@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
public class Inspection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 20, nullable = false)
    private Long inspectionPk;

    @Column(columnDefinition = "DATE", nullable = true)
    private Date inspectionDate1;
    @Column(columnDefinition = "DATE", nullable = true)
    private Date inspectionDate2;
    @Column(columnDefinition = "DATE", nullable = true)
    private Date inspectionDate3;

    @Column(length = 50, nullable = true)
    private String inspectionContent1; // 검수내용
    @Column(length = 50, nullable = true)
    private String inspectionContent2; // 검수내용
    @Column(length = 50, nullable = true)
    private String inspectionContent3; // 검수내용

    @Column(columnDefinition = "DATE",nullable = true)
    private Date inspectionComplete; // 검수완료일자

    @ManyToOne
    private PurchaseOrderSheet purchaseOrderSheet;
}