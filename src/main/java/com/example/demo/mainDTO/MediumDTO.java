package com.example.demo.mainDTO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MediumDTO {
    private String ITEM_CODE_MEDIUM;

    private LargeDTO largeDTO;
}
