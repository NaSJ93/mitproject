package com.example.demo.changeList;

import lombok.*;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ChangeOutbound {
    private String itemCode;
    private String itemName;
    private Long itemCount;
    private Date productionDate;
    private Long inventory;
}
