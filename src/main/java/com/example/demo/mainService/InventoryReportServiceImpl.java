package com.example.demo.mainService;

import com.example.demo.mainDTO.InventoryReportDTO;
import com.example.demo.mainEntity.Large;
import com.example.demo.mainEntity.Medium;
import com.example.demo.mainRepogitory.ItemInfoRepo;
import com.example.demo.mainRepogitory.LargeRepo;
import com.example.demo.mainRepogitory.MediumRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
@Service
@RequiredArgsConstructor

public class InventoryReportServiceImpl implements InventoryReportService{

    private final ItemInfoRepo itemInfoRepo;
    private final LargeRepo largeRepo;
    private final MediumRepo mediumRepo;

/*
    @Override
    public List<InventoryReportDTO> findInventoryReportByDateSizeRange(LocalDate startDate, LocalDate endDate, String selectBoxLarge, String selectBoxMedium) {

        if(selectBoxLarge.equals("반도체(B)")) {
            selectBoxLarge="B";
            if(selectBoxMedium.equals("메인보드(A)")) {
                selectBoxMedium="A";
            } else if (selectBoxMedium.equals("메모리카드(S)")) {
                selectBoxMedium="S";
            } else if (selectBoxMedium.equals("allMedium")) {
                selectBoxMedium = "%";
            }

        } else if (selectBoxLarge.equals("연결부품(C)")) {
            selectBoxLarge = "C";
            if (selectBoxMedium.equals("브라켓(B)")) {
                selectBoxMedium = "B";
            } else if (selectBoxMedium.equals("나사(M)")) {
                selectBoxMedium = "M";
            } else if (selectBoxMedium.equals("allMedium")) {
                selectBoxMedium = "%";
            }
        }


        else if(selectBoxLarge.equals("액정(G)")) {
            selectBoxLarge="G";
            if(selectBoxMedium.equals("플랫(F)")) {
                selectBoxMedium="F";
            } else if (selectBoxMedium.equals("allMedium")) {
                selectBoxMedium = "%";
            }

        } else {
            selectBoxLarge="%";
            selectBoxMedium="%";
        }

        return itemInfoRepo.findInventoryReportByDateSizeRange(startDate, endDate, selectBoxLarge, selectBoxMedium);
    }*/

    @Override
    public List<Large> getAllLargeItems() {
        return largeRepo.findAll();
    }

    @Override
    public List<Medium> getMediumsByLargeItemCode(String itemCodeLarge) {
        return mediumRepo.findByLarge_ItemCodeLarge(itemCodeLarge);
    }
}
