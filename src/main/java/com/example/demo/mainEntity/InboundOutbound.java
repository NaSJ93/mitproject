package com.example.demo.mainEntity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class InboundOutbound { //입출고

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 20, nullable = false)
    private Long ioCode;

    private Long inboundQuantity; // 입고수량

    @Column(columnDefinition = "DATE")
    private Date inboundDate; // 입고일자

    private Long outboundQuantity; // 출고수량

    @Column(columnDefinition = "DATE")
    private Date outboundDate; // 출고일자

    @ManyToOne(fetch = FetchType.LAZY)
    private ItemInfo itemInfo;

}
