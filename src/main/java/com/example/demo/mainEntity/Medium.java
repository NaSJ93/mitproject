package com.example.demo.mainEntity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.web.bind.annotation.GetMapping;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Medium { //중

    @Id
    @Column(length = 10, nullable = false)
    private String ItemCodeMedium; //

    @ManyToOne(fetch = FetchType.LAZY)
    private Large large;

}