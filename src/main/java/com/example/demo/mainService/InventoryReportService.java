package com.example.demo.mainService;

import com.example.demo.mainDTO.InventoryReportDTO;
import com.example.demo.mainEntity.Large;
import com.example.demo.mainEntity.Medium;

import java.time.LocalDate;
import java.util.List;

public interface InventoryReportService {
    //List<InventoryReportDTO> findInventoryReportByDateSizeRange(LocalDate startDate, LocalDate endDate, String selectBoxLarge, String selectBoxMedium);

    List<Large> getAllLargeItems();
    public List<Medium> getMediumsByLargeItemCode(String itemCodeLarge);
}
