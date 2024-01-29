package com.example.demo.mainEntity;

import jakarta.persistence.*;
import lombok.*;

@Embeddable
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PurchaseOrderSheetId {

    @Column(length = 20, nullable = false)
    private String purchaseCode;

    @ManyToOne(fetch = FetchType.LAZY)
    private ProductionPlan productionPlan;

    @ManyToOne(fetch = FetchType.LAZY)
    private ItemInfo itemInfo;



}