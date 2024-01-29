package com.example.demo.mainEntity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = {"product","contract","medium"})
public class ItemInfo { //품목

    @Id
    @Column(length = 20, nullable = false)
    private String itemCode;

    @Column(length = 20, nullable = false)
    private String itemName;

    @Column(length = 20, nullable = false)
    private String standard;

    @Column(length = 20, nullable = false)
    private String texture;

    @Column(nullable = false)
    private Long itemCount;

    @Column
    private Long inventory;

    @Column(length = 20)
    private String drawingNumber;

    @Lob
    @Column(name = "drawingImage", columnDefinition = "MEDIUMTEXT")
    private String drawingImage;

    @Column
    private String imageType;

    @Column
    private Long price; // Price

    @Column
    private Long LeadTime; //LeadTime

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    private Contract contract;

    @ManyToOne(fetch = FetchType.LAZY)
    private Medium medium;


}