package com.example.demo.mainEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Large { //대

    @Id
    @Column(length = 10, nullable = false)
    private String itemCodeLarge; //

}
