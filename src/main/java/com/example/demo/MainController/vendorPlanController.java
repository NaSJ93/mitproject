package com.example.demo.MainController;

import com.example.demo.mainDTO.ProductionPlanToEntityDTO;
import com.example.demo.mainDTO.VendorDTO;
import com.example.demo.mainEntity.ProductionPlan;
import com.example.demo.mainEntity.Vendor;
import com.example.demo.mainService.Mainserv;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@Log4j2

public class vendorPlanController {
    @Autowired
    private Mainserv mainService;

    @GetMapping("/ProductionPlan")
    public String ProductionPlan(Model model) {
        log.info("ProductionPlan 진입");
        List<ProductionPlan> productionPlanList = mainService.ShowProductionPlanList();
        //DB의 생상계획목록을 출력
        model.addAttribute("showProductionPlanList", productionPlanList);
        //품목리스트를 읽어오는 모델
        model.addAttribute("productList", mainService.ProductList());
        // 항목에 입력한 값을 th->productPlanDTO로 전송 저장을 누르면
        model.addAttribute("productionPlanToEntityDTO", new ProductionPlanToEntityDTO());
        //삭제시 뭘 지웠는지 로그로 표현
        log.info(model.getAttribute("removePlan"));
        return "ProductionPlan";
    }

    @PostMapping("/saveProduct") //입력받은 제품키 , 제품명 저장
    public String saveProduction(@ModelAttribute ProductionPlanToEntityDTO productionPlanToEntityDTO) {
        log.info("저장버튼 클릭 확인");


        mainService.SaveProduction(productionPlanToEntityDTO);
        log.info("저장 완료 리다이렉트 합니다.");
        return "redirect:/ProductionPlan";
    }



    @PostMapping("/requestDelete")
    public String removePlan(@RequestParam String productionPk, RedirectAttributes redirectAttributes) {
        log.info("삭제버튼 누름");
        mainService.removePlan(productionPk); // 서비스 삭제기능 호출
        redirectAttributes.addFlashAttribute("removePlan", "삭제한 행 번호 :" + productionPk);
        return "redirect:/ProductionPlan";
    }

//==========아래는 Vendor 콘트롤 관련============

    @GetMapping("/Vendor")
    public String Vendor(Model model) {
        log.info("Vendor 진입");
        List<Vendor> vendorList = mainService.ShowVendorList();
        //DB의 등록된 거래처 목록을 출력
        model.addAttribute("showVendorList", vendorList);


//         th에서 주입할 VendorDTO 바인딩 ( th->vendorDTO)
        model.addAttribute("vendorDTO", new VendorDTO());

        //삭제시 뭘 지웠는지 로그로 표현
        log.info(model.getAttribute("removeVendor"));
        return "/vendorRegister";
    }

    @PostMapping("/registerVendor") //입력받은 제품키 , 제품명 저장
    public String registerVendor(@ModelAttribute VendorDTO vendorDTO) {
        log.info("거래처 등록 버튼 클릭 확인");

        mainService.RegisterVendor(vendorDTO);
        log.info("저장 완료 리다이렉트 합니다.");
        return "redirect:/Vendor";
    }


    @PostMapping("/requestDeleteVendor")
    public String removeVendor(@RequestParam String businessLicense, RedirectAttributes redirectAttributes) {
        mainService.removeVendor(businessLicense); //서비스 삭제기능 호출
        redirectAttributes.addFlashAttribute("removeVendor", "삭제한 행 번호 :" + businessLicense);
        return "redirect:/Vendor";
    }

    @PostMapping("/requestUpdate")
    public String dataUpdate(@ModelAttribute VendorDTO vendorDTO){
        mainService.findRowDataByButton(vendorDTO);
        return "Vendor";
    }


}
