package com.example.demo.mainEntity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class ProcurementPlan { //조달계획

    @Column(nullable = false)
    private Long ProcurementQuantity; //입고예정수량

    @Column(columnDefinition = "DATE", nullable = false)
    private Date ProcurementDate; //입고예정일

    @EmbeddedId
    private ProcurementId id;

}
