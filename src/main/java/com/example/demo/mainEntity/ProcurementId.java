package com.example.demo.mainEntity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProcurementId implements Serializable {
    @ManyToOne
    private ProductionPlan productionPlan;
    @ManyToOne
    private ItemInfo itemInfo;
}
