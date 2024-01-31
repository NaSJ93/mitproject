package com.example.demo.MainController;

import com.example.demo.changeList.ChangeInspectionPlanModal;
import com.example.demo.changeList.ChangeInspectionPlanModal2;
import com.example.demo.changeList.SelectedItemDto;
import com.example.demo.mainEntity.*;
import com.example.demo.mainService.Mainserv;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.activation.DataHandler;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.util.ByteArrayDataSource;
import java.io.IOException;
import java.util.*;


@RestController
@Log4j2
@AllArgsConstructor
public class RestCont {

    private Mainserv mainserv;


    //조달계획 등록(1-3)
    @PostMapping(value = "/ssaa", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void asdfff(@RequestBody List<SelectedItemDto> selectedItems) {
        log.info("에이");
        String str ="";
        // 선택된 아이템들에 대한 로직 수행
        for (SelectedItemDto selectedItem : selectedItems) {
            // 로직 수행
            if (selectedItem.getItemCode() != null) {
                log.info("Pro Code: " + selectedItem.getProCode());
                log.info("Item Code: " + selectedItem.getItemCode());
                log.info("Count: " + selectedItem.getCount());
                log.info("Date: " + selectedItem.getDate());
                // 일단은 동일 거래처로만 처리한다고 가정하고 하자
                //ItemInfo item = mainserv.test(selectedItem.getItemCode());
                mainserv.updateProcure(selectedItem.getDate(), selectedItem.getCount(), selectedItem.getProCode() ,selectedItem.getItemCode());
                //입출고 항목 생성
                mainserv.createInOutbound(selectedItem.getItemCode());
            }
            List<String> purString=new ArrayList<>();
            List<PurchaseOrderSheet> count=mainserv.countPurchase();
                for(int i=0;i<count.size();i++)
                    purString.add(count.get(i).getId().getPurchaseCode());

            log.info("중복 제거 전 "+purString.size());
            Set<String> uniqueSet = new HashSet<>(purString); //중복 제거
            log.info("중복 제거 후 "+uniqueSet.size());

            str = "p0" + uniqueSet.size();
            log.info("str : " + str);
            //mainserv.firstPurchase(str); 과거의 잔재물,,,

        }

        for (int i = 0; i < selectedItems.size(); i++) {
            mainserv.savePurandItem(selectedItems.get(i).getItemCode(), str,selectedItems.get(i).getProCode());
        }
    }


    //발주서 발행 및 발송
    @PostMapping("/gogogo")
    @ResponseBody
    public void asdf(@RequestParam("code") String code,@RequestParam("type") String type,
                     @RequestParam("email") String email,
                     @RequestParam("managerEmail") String managerEmail,
                     @RequestParam("password") String password,
                     @RequestParam("attachment") MultipartFile attachment) {
        /*
        @RequestParam("attachment") MultipartFile attachment,*/
        log.info("type: "+type);
        log.info("Email: " + email);
        log.info("Manager Email: " + managerEmail);


        Properties prop = new Properties();
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.ssl.protocols", "TLSv1.2");

        log.info("1차");

        Session session = Session.getDefaultInstance(prop, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(managerEmail, password);
            }
        });


log.info("2차");
        try {
            MimeMessage message = new MimeMessage(session); log.info("2-1차");
            message.setFrom(new InternetAddress(email));    log.info("2-2차");        //수신자메일주소
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email)); log.info("2-3차");
            if(type.equals("purchase")) {    //발주서 관련 처리
                message.setSubject(code+" 발주서 발송했습니다."); //메일 제목을 입력
            }else{                            //명세서 관련 처리
                message.setSubject(code+" 명세서 발송했습니다."); //메일 제목을 입력
            }

            // Text
            message.setText("첨부파일 확인 바랍니다.");    //메일 내용을 입력

            // 파일 첨부
            MimeBodyPart attachmentPart = new MimeBodyPart();
            attachmentPart.setDataHandler(new DataHandler(new ByteArrayDataSource(attachment.getBytes(), attachment.getContentType())));
            attachmentPart.setFileName(attachment.getOriginalFilename());

            // 파일 첨부한 MimeBodyPart를 메시지에 추가
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(attachmentPart);
            message.setContent(multipart);

            // 메일 전송
            Transport.send(message); log.info("2-4차");////전송
            System.out.println("message sent successfully...");
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        log.info("3차");

        Date date = new Date();
        if(type.equals("purchase")) {            //발주서 관련 처리
            log.info("pur");
            mainserv.starPurchsae(date,code);
        }else{                            //명세서 관련 처리
            log.info("inv");
            mainserv.StartInvoice(date,code);
        }


    }

    @GetMapping("/getDataForPurchaseCode")
    @ResponseBody
    public List<ChangeInspectionPlanModal> getDataForPurchaseCode(@RequestParam String pCode) {
        List<PurchaseOrderSheet> pur=mainserv.findPurchaseDatePresent();  //발행된 발주서 검색 p001, p002 가 있다고 가정
        List<ItemInfo> itemList = new ArrayList<>();
        List<ProcurementPlan> proList = new ArrayList<>();
        List<ChangeInspectionPlanModal> changemodal = new ArrayList<>();
            List<PurchaseOrderSheet> info =mainserv.findItemInfobyCode(pCode); //첫번째 발주서로 품목 검색
        log.info("1번");

            for (int j = 0; j < info.size(); j++) { //해당 DB 값 추출
                itemList.add(mainserv.test(info.get(j).getId().getItemInfo().getItemCode()));
                proList.add(mainserv.findItemfromProcurement(info.get(j).getId().getProductionPlan().getProductionPk(),info.get(j).getId().getItemInfo().getItemCode()));
            }
        log.info(itemList.size());
        log.info(itemList.get(0).toString());
        log.info("2번");
            for (int k = 0; k < itemList.size(); k++) { //타임리프로 출력할 값만 집어넣기
                log.info("2-1번");
                changemodal.add(new ChangeInspectionPlanModal(pCode,itemList.get(k).getItemCode(),
                        itemList.get(k).getItemName(),itemList.get(k).getTexture(),proList.get(k).getProcurementQuantity(),proList.get(k).getProcurementDate(),
                        itemList.get(k).getContract().getVendor().getVendorName(),itemList.get(k).getContract().getVendor().getVendorEmail()));
                log.info("2-2번");
            }
        log.info("3번");
            itemList.clear(); proList.clear();


        log.info(changemodal.toString());
        return changemodal;
    }

    @GetMapping("/getDataForPurchaseCode2")
    @ResponseBody
    public List<ChangeInspectionPlanModal> getDataForPurchaseCode2(@RequestParam String pCode) {
        log.info("어");
        List<PurchaseOrderSheet> pur=mainserv.findPurchaseDatePresent();  //발행된 발주서 검색 p001, p002 가 있다고 가정
        List<ItemInfo> itemList = new ArrayList<>();
        List<ProcurementPlan> proList = new ArrayList<>();
        List<ChangeInspectionPlanModal> changemodal = new ArrayList<>();
        List<PurchaseOrderSheet> info =mainserv.findItemInfobyCode(pCode); //첫번째 발주서로 품목 검색
        log.info("1번");

        for (int j = 0; j < info.size(); j++) { //해당 DB 값 추출
            itemList.add(mainserv.test(info.get(j).getId().getItemInfo().getItemCode()));
            proList.add(mainserv.findItemfromProcurement(info.get(j).getId().getProductionPlan().getProductionPk(),info.get(j).getId().getItemInfo().getItemCode()));
        }
        log.info(itemList.size());
        log.info(itemList.get(0).toString());
        log.info("2번");
        for (int k = 0; k < itemList.size(); k++) { //타임리프로 출력할 값만 집어넣기
            log.info("2-1번");
            changemodal.add(new ChangeInspectionPlanModal(pCode,itemList.get(k).getItemCode(),
                    itemList.get(k).getItemName(),itemList.get(k).getTexture(),proList.get(k).getProcurementQuantity(),proList.get(k).getProcurementDate(),
                    itemList.get(k).getContract().getVendor().getVendorName(),itemList.get(k).getContract().getVendor().getVendorEmail()));
            log.info("2-2번");
        } //itemList.get(k).getContract().getVendor().getVendorEmail()
        log.info("3번");
        itemList.clear(); proList.clear();


        log.info(changemodal.toString());

        return changemodal;
    }


    @GetMapping("/getDataForPurchaseCode3")
    @ResponseBody
    public List<ChangeInspectionPlanModal2> getDataForPurchaseCode3(@RequestParam String pCode) {
        log.info("아아아아아12333333333333333");
        List<ChangeInspectionPlanModal2> changemodal2 = new ArrayList<>();
        Inspection aa=mainserv.findPcode(pCode);

        if(aa.getInspectionDate2()!=null && aa.getInspectionDate3()!=null){ //3
            changemodal2.add(ChangeInspectionPlanModal2.builder().order("1차").purchaseCode(pCode).inspectionDate(aa.getInspectionDate1()).inspection_content(aa.getInspectionContent1()).build());
            changemodal2.add(ChangeInspectionPlanModal2.builder().order("2차").purchaseCode(pCode).inspectionDate(aa.getInspectionDate2()).inspection_content(aa.getInspectionContent2()).build());
            changemodal2.add(ChangeInspectionPlanModal2.builder().order("3차").purchaseCode(pCode).inspectionDate(aa.getInspectionDate3()).inspection_content(aa.getInspectionContent3()).build());
        }else if(aa.getInspectionDate2()!=null && aa.getInspectionDate3()==null){ //2
            changemodal2.add(ChangeInspectionPlanModal2.builder().order("1차").purchaseCode(pCode).inspectionDate(aa.getInspectionDate1()).inspection_content(aa.getInspectionContent1()).build());
            changemodal2.add(ChangeInspectionPlanModal2.builder().order("2차").purchaseCode(pCode).inspectionDate(aa.getInspectionDate2()).inspection_content(aa.getInspectionContent2()).build());
        }else if(aa.getInspectionDate1()!=null && aa.getInspectionDate2()==null && aa.getInspectionDate3()==null){ //1
            changemodal2.add(ChangeInspectionPlanModal2.builder().order("1차").purchaseCode(pCode).inspectionDate(aa.getInspectionDate1()).inspection_content(aa.getInspectionContent1()).build());
        }

        return changemodal2;
    }

    //입고수량 등록
    @PostMapping("/insertInbound")
    public void processForm(@RequestBody Map<String, Object> requestData, Model m) {
        // 로직 수행
        String itemCode = (String) requestData.get("itemCode");
        Long inputQty = ((Number) requestData.get("inputQty")).longValue();

        log.info("됐>"+itemCode);
        log.info(String.valueOf(inputQty));
        //LocalDate now = LocalDate.now();
        /*Date utilDate = Date.from(now.atStartOfDay(ZoneId.systemDefault()).toInstant());
        mainserv.updateQuanDate(itemCode, utilDate, inputQty);*/
        ItemInfo inven=mainserv.test(itemCode);
        mainserv.updateInven(itemCode,(inven.getInventory()+inputQty));
        Date now=new Date();
        mainserv.updateQuanDate(itemCode, now,inputQty);


    }

    //출고수량 등록
    @PostMapping("/insertOutbound")
    public void processForm2(@RequestBody Map<String, Object> requestData, Model m) {
        // 로직 수행
        String itemCode = (String) requestData.get("itemCode");
        Long inputQty = ((Number) requestData.get("inputQty")).longValue();

        log.info("됐>"+itemCode);
        log.info(String.valueOf(inputQty));
        //LocalDate now = LocalDate.now();
        /*Date utilDate = Date.from(now.atStartOfDay(ZoneId.systemDefault()).toInstant());
        mainserv.updateQuanDate(itemCode, utilDate, inputQty);*/
        ItemInfo inven=mainserv.test(itemCode);
        mainserv.updateInven(itemCode,(inven.getInventory()-inputQty));
        Date now=new Date();
        mainserv.updateOutDateQuan(itemCode, now,inputQty);
    }



/*    @PostMapping("/getMediumItems")
    @ResponseBody
    public List<Medium> getMediumItems(@RequestParam(name = "selectedLargeItemCode") String selectedLargeItemCode) {

        List<Medium> mediumItems = inventoryReportService.getMediumsByLargeItemCode(selectedLargeItemCode);
        return mediumItems;
    }

    @PostMapping("/InvenSearch")
    @ResponseBody
    public List<InventoryReportDTO> getInventoryData(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @RequestParam("selectBoxLarge") String selectBoxLarge,
            @RequestParam("selectBoxMedium") String selectBoxMedium) {

       // List<InventoryReportDTO> inventoryReport = inventoryReportService.findInventoryReportByDateSizeRange(startDate, endDate, selectBoxLarge, selectBoxMedium);

        return null;
    }*/





/*
    @GetMapping("/Purchase")
    public String prin(){
        return "/order/Purchase";
    }*/

/*    @GetMapping("/getMediumByLarge")
    public ResponseEntity<List<MediumDTO>> getMediumByLarge(@RequestParam("largeCode") String largeCode) {
        try {
            // largeCode를 기반으로 Medium 정보를 조회하는 서비스 메소드 호출
            List<MediumDTO> mediumList = mainserv.getMediumByLargeCode(largeCode);

            // 조회된 Medium 정보를 JSON 형태로 응답
            return new ResponseEntity<>(mediumList, HttpStatus.OK);
        } catch (Exception e) {
            // 예외가 발생하면 에러 응답
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/
//달력에서 날짜 받아오기
    /*@PostMapping("aaa")
    public ResponseEntity<List<ProductionPlanDTO>> ss(@RequestParam("s01") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dat1,
                                                      @RequestParam("s02") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dat2){
        log.info("날짜 : "+new SimpleDateFormat("yyyy년 MM월 dd일").format(dat1));

        java.sql.Date sqlStartDate = new java.sql.Date(dat1.getTime());
        java.sql.Date sqlEndDate = new java.sql.Date(dat2.getTime());

        List<ProductionPlanDTO> productionPlans = mainserv.getProductionPlansByDateRange(sqlStartDate, sqlEndDate);

        log.info("dddddddddddddd  :  "+productionPlans.get(1).);
        log.info("dddddddddddddd  :  "+productionPlans.get(2));
        if (productionPlans != null && !productionPlans.isEmpty()) {
            return new ResponseEntity<>(productionPlans, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

    }*/

}
