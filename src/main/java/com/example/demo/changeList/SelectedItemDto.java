package com.example.demo.changeList;

import lombok.*;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SelectedItemDto {
    private String proCode;
    private String itemCode;
    private Long count;
    private Date date;
}
