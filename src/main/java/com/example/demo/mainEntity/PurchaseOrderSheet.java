package com.example.demo.mainEntity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PurchaseOrderSheet { //발주서

    @Column(columnDefinition = "MEDIUMTEXT")
    private String PurchaseImage; // 발주서 이미지

    @Column
    private String PurchaimageType;

    @Column(columnDefinition = "DATE")
    private Date PurchaseDate; // 발주서 발행일자

    @Column(columnDefinition = "DATE")
    private Date PurchaseDeadline; // 발주마감일자

    @Column(columnDefinition = "DATE")
    private Date InvoiceDate; // 거래명세서 발행일자

    @Column(columnDefinition = "MEDIUMTEXT")
    private String InvoiceImage; // 거래명세서 이미지

    @Column
    private String InvoimageType;

    @EmbeddedId
    private PurchaseOrderSheetId id;

}
