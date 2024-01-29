package com.example.demo.mainRepogitory;

import com.example.demo.mainEntity.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@SpringBootTest
public class Testdummy {

    @Autowired
    ContractRepo contractRepo;
    @Autowired
    InboundOutRepo inboundOutRepo;
    @Autowired
    InspectionRepo inspectionRepo;
    @Autowired
    ItemInfoRepo itemInfoRepo;
    @Autowired
    LargeRepo largeRepo;
    @Autowired
    MediumRepo mediumRepo;
    @Autowired
    ProcurementPlanRepo procurementPlanRepo;
    @Autowired
    ProductionPlanRepo productionPlanRepo;
    @Autowired
    ProductRepo productRepo;
    @Autowired
    PurchaseOrderRepo purchaseOrderRepo;
    @Autowired
    VendorRepo vendorRepo;



    @Test
    public void ddds(){

    }

    @Test
    public void dd(){
        //제품정보
        productRepo.save(Product.builder().ProductCode("G1").ProductName("갤럭시").build());

        //대,중
        largeRepo.save(Large.builder().itemCodeLarge("반도체(B)").build());
        largeRepo.save(Large.builder().itemCodeLarge("연결부품(C)").build());
        largeRepo.save(Large.builder().itemCodeLarge("액정(G)").build());

        mediumRepo.save(Medium.builder().ItemCodeMedium("메인보드(A)").large(new Large("반도체(B)")).build());
        mediumRepo.save(Medium.builder().ItemCodeMedium("메모리카드(S)").large(new Large("반도체(B)")).build());
        mediumRepo.save(Medium.builder().ItemCodeMedium("브라켓(B)").large(new Large("연결부품(C)")).build());
        mediumRepo.save(Medium.builder().ItemCodeMedium("나사(M)").large(new Large("연결부품(C)")).build());
        mediumRepo.save(Medium.builder().ItemCodeMedium("플랫(F)").large(new Large("액정(G)")).build());
    }

    @Test //생산계획서
    public void ff(){
        String[] dateStrings = {  //product 추가
                "2024년 2월 01일",
                "2024년 2월 04일",
                "2024년 2월 05일",
                "2024년 2월 06일",
                "2024년 2월 07일",
                "2024년 2월 08일",
                "2024년 2월 11일",
                "2024년 2월 12일",
                "2024년 2월 13일",
                "2024년 2월 14일",
                "2024년 2월 15일",
                "2024년 2월 18일",
                "2024년 2월 19일",
                "2024년 2월 20일",
                "2024년 2월 21일",
                "2024년 2월 22일",
                "2024년 2월 26일",
                "2024년 2월 27일",
                "2024년 2월 28일",
                "2024년 2월 29일"
        };
        List<Date> sqlDateList = new ArrayList<>();

        for(int i=0;i<dateStrings.length;i++) {
            // SimpleDateFormat을 사용하여 문자열을 java.util.Date로 파싱
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy년 MM월 dd일");
            Date parsedDate = null;
            try {
                parsedDate = dateFormat.parse(dateStrings[i]);
            } catch (ParseException e) {
                e.printStackTrace(); // 날짜 형식이 잘못된 경우 예외 처리
            }
            // java.util.Date를 java.sql.Date로 변환
            Date sqlDate = new Date(parsedDate.getTime());
            sqlDateList.add(sqlDate);
        }

        productionPlanRepo.save(ProductionPlan.builder().productionPk("A00001").productionQuantity(1L).productionDate(sqlDateList.get(0)).product(new Product("G1","갤럭시")).complete(false).build());
        productionPlanRepo.save(ProductionPlan.builder().productionPk("A00002").productionQuantity(2L).productionDate(sqlDateList.get(1)).product(new Product("G1","갤럭시")).complete(false).build());
        productionPlanRepo.save(ProductionPlan.builder().productionPk("A00003").productionQuantity(3L).productionDate(sqlDateList.get(2)).product(new Product("G1","갤럭시")).complete(false).build());
        productionPlanRepo.save(ProductionPlan.builder().productionPk("A00004").productionQuantity(1L).productionDate(sqlDateList.get(3)).product(new Product("G1","갤럭시")).complete(false).build());
        productionPlanRepo.save(ProductionPlan.builder().productionPk("A00005").productionQuantity(3L).productionDate(sqlDateList.get(4)).product(new Product("G1","갤럭시")).complete(false).build());
        productionPlanRepo.save(ProductionPlan.builder().productionPk("A00006").productionQuantity(2L).productionDate(sqlDateList.get(5)).product(new Product("G1","갤럭시")).complete(false).build());
        /*productionPlanRepo.save(ProductionPlan.builder().PRODUCTION_PK("A00007").PRODUCTION_QUANTITY(5L).PRODUCTION_DATE(sqlDateList.get(6)).product(new Product("G1","갤럭시")).build());
        productionPlanRepo.save(ProductionPlan.builder().PRODUCTION_PK("A00008").PRODUCTION_QUANTITY(3L).PRODUCTION_DATE(sqlDateList.get(7)).product(new Product("G1","갤럭시")).build());
        productionPlanRepo.save(ProductionPlan.builder().PRODUCTION_PK("A00009").PRODUCTION_QUANTITY(1L).PRODUCTION_DATE(sqlDateList.get(8)).product(new Product("G1","갤럭시")).build());
        productionPlanRepo.save(ProductionPlan.builder().PRODUCTION_PK("A00010").PRODUCTION_QUANTITY(3L).PRODUCTION_DATE(sqlDateList.get(9)).product(new Product("G1","갤럭시")).build());
        productionPlanRepo.save(ProductionPlan.builder().PRODUCTION_PK("A00011").PRODUCTION_QUANTITY(4L).PRODUCTION_DATE(sqlDateList.get(10)).product(new Product("G1","갤럭시")).build());
        productionPlanRepo.save(ProductionPlan.builder().PRODUCTION_PK("A00012").PRODUCTION_QUANTITY(2L).PRODUCTION_DATE(sqlDateList.get(11)).product(new Product("G1","갤럭시")).build());
        productionPlanRepo.save(ProductionPlan.builder().PRODUCTION_PK("A00013").PRODUCTION_QUANTITY(1L).PRODUCTION_DATE(sqlDateList.get(12)).product(new Product("G1","갤럭시")).build());
        productionPlanRepo.save(ProductionPlan.builder().PRODUCTION_PK("A00014").PRODUCTION_QUANTITY(5L).PRODUCTION_DATE(sqlDateList.get(13)).product(new Product("G1","갤럭시")).build());
        productionPlanRepo.save(ProductionPlan.builder().PRODUCTION_PK("A00015").PRODUCTION_QUANTITY(5L).PRODUCTION_DATE(sqlDateList.get(14)).product(new Product("G1","갤럭시")).build());
        productionPlanRepo.save(ProductionPlan.builder().PRODUCTION_PK("A00016").PRODUCTION_QUANTITY(1L).PRODUCTION_DATE(sqlDateList.get(15)).product(new Product("G1","갤럭시")).build());
        productionPlanRepo.save(ProductionPlan.builder().PRODUCTION_PK("A00017").PRODUCTION_QUANTITY(3L).PRODUCTION_DATE(sqlDateList.get(16)).product(new Product("G1","갤럭시")).build());
        productionPlanRepo.save(ProductionPlan.builder().PRODUCTION_PK("A00018").PRODUCTION_QUANTITY(3L).PRODUCTION_DATE(sqlDateList.get(17)).product(new Product("G1","갤럭시")).build());
        productionPlanRepo.save(ProductionPlan.builder().PRODUCTION_PK("A00019").PRODUCTION_QUANTITY(5L).PRODUCTION_DATE(sqlDateList.get(18)).product(new Product("G1","갤럭시")).build());
        productionPlanRepo.save(ProductionPlan.builder().PRODUCTION_PK("A00020").PRODUCTION_QUANTITY(4L).PRODUCTION_DATE(sqlDateList.get(19)).product(new Product("G1","갤럭시")).build());*/

    }


    @Test //거래처정보
    public void ee(){
        vendorRepo.save(Vendor.builder().businessLicense("401-81-53863").vendorName("(주) 제이아이테크").vendorAddress("전라북도 군산시 중가도길 16 (우)54002").vendorPnumber("063-731-0088").vendorEmail("sp@ji-tech.co.kr").PIC("함석헌").build());
        vendorRepo.save(Vendor.builder().businessLicense("607-81-82587").vendorName("(주)대명").vendorAddress("창원시 마산회원구 자유무역4길 6").vendorPnumber("055-256-1188").vendorEmail("daemyung@krparachute.co.kr").PIC("손순자").build());
        vendorRepo.save(Vendor.builder().businessLicense("205-81-36868").vendorName("금하기계㈜").vendorAddress("경기도 김포시 대곶면 상마신기로 81-46").vendorPnumber("031-355-6497").vendorEmail("jmh@geumhwa.co.kr").PIC("조만현").build());
        vendorRepo.save(Vendor.builder().businessLicense("613-81-21469").vendorName("(주)세양").vendorAddress("경기도 화성시 봉담읍 효행로184번길 5-17").vendorPnumber("031-223-1943").vendorEmail("hg0305@seyeang.com").PIC("정현곤").build());
        vendorRepo.save(Vendor.builder().businessLicense("135-86-23166").vendorName("(주)스마트휴먼텍").vendorAddress("인천광역시 서구 원당대로480번길 2-1 가동").vendorPnumber("032-564-3080").vendorEmail("ntm@smart-ht.co.kr").PIC("노태문").build());
        vendorRepo.save(Vendor.builder().businessLicense("620-21-70775").vendorName("대연정밀").vendorAddress("경기도 광주시 곤지암읍 원지길 58").vendorPnumber("031-970-2002").vendorEmail("mh1210@daeyeon.com").PIC("최민호").build());
        vendorRepo.save(Vendor.builder().businessLicense("129-08-74643").vendorName("광동레이져").vendorAddress("경기도 양주시 은현면 화합로954번길 51-103").vendorPnumber("031-859-0492").vendorEmail("shlee@gwangdong.com").PIC("이성호").build());
        vendorRepo.save(Vendor.builder().businessLicense("212-81-33699").vendorName("국광플랜").vendorAddress("서울특별시 성동구 성수일로 99 서울숲 AK밸리 410호").vendorPnumber("02-3409-0412").vendorEmail("gukgwang@gwplan.com").PIC("김지연").build());

    }

   /* @Test //계약서
    public void aa() throws UnsupportedEncodingException {  //vendor 추가해야함
        String[] dateStrings = {
                "2020-11-20",
                "2021-01-22",
                "2021-03-10",
                "2021-03-15",
                "2021-09-16",
                "2022-10-19",
                "2022-10-19",
                "2022-10-20",
                "2022-11-30",
                "2023-02-16"
        };
        List<Date> sqlDateList = new ArrayList<>();

        for(int i=0;i<dateStrings.length;i++) {
            // SimpleDateFormat을 사용하여 문자열을 java.util.Date로 파싱
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date parsedDate = null;
            try {
                parsedDate = dateFormat.parse(dateStrings[i]);
            } catch (ParseException e) {
                e.printStackTrace(); // 날짜 형식이 잘못된 경우 예외 처리
            }
            // java.util.Date를 java.sql.Date로 변환
            java.sql.Date sqlDate = new java.sql.Date(parsedDate.getTime());
            sqlDateList.add(sqlDate);
        }

        String str="C1.jpg";
        byte[] bytes=str.getBytes("utf-8");
        contractRepo.save(Contract.builder().ContractCode("C00001").ContractDate(sqlDateList.get(0)).Significant("운임비착불").ContractScan(bytes).build());
        contractRepo.save(Contract.builder().ContractCode("C00002").ContractDate(sqlDateList.get(1)).Significant("운임비착불").ContractScan(bytes).build());
        contractRepo.save(Contract.builder().ContractCode("C00003").ContractDate(sqlDateList.get(2)).Significant("운임비착불").ContractScan(bytes).build());
        contractRepo.save(Contract.builder().ContractCode("C00004").ContractDate(sqlDateList.get(3)).Significant("운임비착불").ContractScan(bytes).build());
        contractRepo.save(Contract.builder().ContractCode("C00005").ContractDate(sqlDateList.get(4)).Significant("운임비착불").ContractScan(bytes).build());
        contractRepo.save(Contract.builder().ContractCode("C00006").ContractDate(sqlDateList.get(5)).Significant("운임비착불").ContractScan(bytes).build());
        contractRepo.save(Contract.builder().ContractCode("C00007").ContractDate(sqlDateList.get(6)).Significant("운임비착불").ContractScan(bytes).build());
        contractRepo.save(Contract.builder().ContractCode("C00008").ContractDate(sqlDateList.get(7)).Significant("운임비착불").ContractScan(bytes).build());
        contractRepo.save(Contract.builder().ContractCode("C00009").ContractDate(sqlDateList.get(8)).Significant("운임비착불").ContractScan(bytes).build());
        contractRepo.save(Contract.builder().ContractCode("C00010").ContractDate(sqlDateList.get(9)).Significant("운임비착불").ContractScan(bytes).build());
    }*/


    /*@Test //품목정보
    public void hh() throws UnsupportedEncodingException {  //product, contract, medium 추가
        String str="BA001_D.JPG";
        byte[] bytes=str.getBytes("utf-8");
        itemInfoRepo.save(ItemInfo.builder().itemCode("BA001").itemName("메인보드1호").standard("7*15*1").texture("반도체").itemCount(1L).inventory(0L).drawingNumber("BA001_D").drawingImage(bytes).product(new Product("G1","갤럭시")).medium(new Medium("메인보드(A)",new Large("반도체(B)"))).build());
        itemInfoRepo.save(ItemInfo.builder().itemCode("BS001").itemName("메모리카드1호").standard("2*3*1").texture("반도체").itemCount(1L).inventory(3L).drawingNumber("BS001_D").drawingImage(bytes).product(new Product("G1","갤럭시")).medium(new Medium("메모리카드(S)",new Large("반도체(B)"))).build());
        itemInfoRepo.save(ItemInfo.builder().itemCode("BS002").itemName("메모리카드2호").standard("2*4*1").texture("반도체").itemCount(1L).inventory(3L).drawingNumber("BS002_D").drawingImage(bytes).product(new Product("G1","갤럭시")).medium(new Medium("메모리카드(S)",new Large("반도체(B)"))).build());
        itemInfoRepo.save(ItemInfo.builder().itemCode("BS003").itemName("메모리카드3호").standard("3*4*1").texture("반도체").itemCount(1L).inventory(3L).drawingNumber("BS003_D").drawingImage(bytes).product(new Product("G1","갤럭시")).medium(new Medium("메모리카드(S)",new Large("반도체(B)"))).build());
        itemInfoRepo.save(ItemInfo.builder().itemCode("CB001").itemName("브라켓1호").standard("3*5*1").texture("플라스틱").itemCount(6L).inventory(20L).drawingNumber("CB001_D").drawingImage(bytes).product(new Product("G1","갤럭시")).medium(new Medium("브라켓(B)",new Large("연결부품(C)"))).build());
        itemInfoRepo.save(ItemInfo.builder().itemCode("CB002").itemName("브라켓2호").standard("3*5*2").texture("플라스틱").itemCount(8L).inventory(65L).drawingNumber("CB002_D").drawingImage(bytes).product(new Product("G1","갤럭시")).medium(new Medium("브라켓(B)",new Large("연결부품(C)"))).build());
        itemInfoRepo.save(ItemInfo.builder().itemCode("CB003").itemName("브라켓3호").standard("4*5*1").texture("플라스틱").itemCount(10L).inventory(34L).drawingNumber("CB003_D").drawingImage(bytes).product(new Product("G1","갤럭시")).medium(new Medium("브라켓(B)",new Large("연결부품(C)"))).build());
        itemInfoRepo.save(ItemInfo.builder().itemCode("CB004").itemName("브라켓4호").standard("4*5*2").texture("플라스틱").itemCount(4L).inventory(30L).drawingNumber("CB004_D").drawingImage(bytes).product(new Product("G1","갤럭시")).medium(new Medium("브라켓(B)",new Large("연결부품(C)"))).build());
        itemInfoRepo.save(ItemInfo.builder().itemCode("CB005").itemName("브라켓5호").standard("5*5*1").texture("플라스틱").itemCount(6L).inventory(15L).drawingNumber("CB005_D").drawingImage(bytes).product(new Product("G1","갤럭시")).medium(new Medium("브라켓(B)",new Large("연결부품(C)"))).build());
        itemInfoRepo.save(ItemInfo.builder().itemCode("CM001").itemName("나사1호").standard("2*4").texture("탄소강").itemCount(2L).inventory(250L).drawingNumber("CM001_D").drawingImage(bytes).product(new Product("G1","갤럭시")).medium(new Medium("나사(M)",new Large("연결부품(C)"))).build());
        itemInfoRepo.save(ItemInfo.builder().itemCode("CM002").itemName("나사2호").standard("2*5").texture("탄소강").itemCount(4L).inventory(235L).drawingNumber("CM002_D").drawingImage(bytes).product(new Product("G1","갤럭시")).medium(new Medium("나사(M)",new Large("연결부품(C)"))).build());
        itemInfoRepo.save(ItemInfo.builder().itemCode("CM003").itemName("나사3호").standard("2*6").texture("탄소강").itemCount(6L).inventory(458L).drawingNumber("CM003_D").drawingImage(bytes).product(new Product("G1","갤럭시")).medium(new Medium("나사(M)",new Large("연결부품(C)"))).build());
        itemInfoRepo.save(ItemInfo.builder().itemCode("CM004").itemName("나사4호").standard("2*7").texture("탄소강").itemCount(8L).inventory(12L).drawingNumber("CM004_D").drawingImage(bytes).product(new Product("G1","갤럭시")).medium(new Medium("나사(M)",new Large("연결부품(C)"))).build());
        itemInfoRepo.save(ItemInfo.builder().itemCode("CM005").itemName("나사5호").standard("2*8").texture("탄소강").itemCount(10L).inventory(324L).drawingNumber("CM005_D").drawingImage(bytes).product(new Product("G1","갤럭시")).medium(new Medium("나사(M)",new Large("연결부품(C)"))).build());
        itemInfoRepo.save(ItemInfo.builder().itemCode("CM006").itemName("나사6호").standard("2*9").texture("탄소강").itemCount(4L).inventory(125L).drawingNumber("CM006_D").drawingImage(bytes).product(new Product("G1","갤럭시")).medium(new Medium("나사(M)",new Large("연결부품(C)"))).build());
        itemInfoRepo.save(ItemInfo.builder().itemCode("CM007").itemName("나사7호").standard("2*10").texture("탄소강").itemCount(6L).inventory(321L).drawingNumber("CM007_D").drawingImage(bytes).product(new Product("G1","갤럭시")).medium(new Medium("나사(M)",new Large("연결부품(C)"))).build());
        itemInfoRepo.save(ItemInfo.builder().itemCode("CM008").itemName("나사8호").standard("2*11").texture("탄소강").itemCount(8L).inventory(245L).drawingNumber("CM008_D").drawingImage(bytes).product(new Product("G1","갤럭시")).medium(new Medium("나사(M)",new Large("연결부품(C)"))).build());
        itemInfoRepo.save(ItemInfo.builder().itemCode("CM009").itemName("나사9호").standard("2*12").texture("탄소강").itemCount(10L).inventory(81L).drawingNumber("CM009_D").drawingImage(bytes).product(new Product("G1","갤럭시")).medium(new Medium("나사(M)",new Large("연결부품(C)"))).build());
        itemInfoRepo.save(ItemInfo.builder().itemCode("CM010").itemName("나사10호").standard("2*13").texture("탄소강").itemCount(4L).inventory(245L).drawingNumber("CM010_D").drawingImage(bytes).product(new Product("G1","갤럭시")).medium(new Medium("나사(M)",new Large("연결부품(C)"))).build());
        itemInfoRepo.save(ItemInfo.builder().itemCode("GF001").itemName("액정1호").standard("15*18*1").texture("글라스").itemCount(1L).inventory(1L).drawingNumber("GF001_D").drawingImage(bytes).product(new Product("G1","갤럭시")).medium(new Medium("플랫(F)",new Large("액정(G)"))).build());
        itemInfoRepo.save(ItemInfo.builder().itemCode("GF002").itemName("액정1호").standard("15*18*1").texture("글라스").itemCount(1L).inventory(1L).drawingNumber("GF001_D").drawingImage(bytes).product(new Product("G1","갤럭시")).medium(new Medium("플랫(F)",new Large("액정(G)"))).build());
        itemInfoRepo.save(ItemInfo.builder().itemCode("GF203").itemName("액정1호").standard("15*18*1").texture("글라스").itemCount(1L).inventory(1L).drawingNumber("GF001_D").drawingImage(bytes).product(new Product("G1","갤럭시")).medium(new Medium("브라켓(B)",new Large("액정(B)"))).build());
    }
*/
    @Test //조달계획 저장
    public void gsdgas(){
        String[] dateStrings = {
                "2024-3-1",
                "2021-3-2",
                "2021-3-3",
        };
        List<Date> sqlDateList = new ArrayList<>();

        for(int i=0;i<dateStrings.length;i++) {
            // SimpleDateFormat을 사용하여 문자열을 java.util.Date로 파싱
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date parsedDate = null;
            try {
                parsedDate = dateFormat.parse(dateStrings[i]);
            } catch (ParseException e) {
                e.printStackTrace(); // 날짜 형식이 잘못된 경우 예외 처리
            }
            // java.util.Date를 java.sql.Date로 변환
            java.sql.Date sqlDate = new java.sql.Date(parsedDate.getTime());
            sqlDateList.add(sqlDate);
        }
      //  procurementPlanRepo.save(ProcurementPlan.builder().ProcurementPk("1").ProcurementQuantity(4L).ProcurementDate(sqlDateList.get(0)).itemInfo(itemInfoRepo.findById("GF001").get()).build());
      //  procurementPlanRepo.save(ProcurementPlan.builder().ProcurementPk("2").ProcurementQuantity(5L).ProcurementDate(sqlDateList.get(1)).itemInfo(itemInfoRepo.findById("GF002").get()).build());
    }
 /*   @Commit
    @Transactional
    @Test
    public void balju(){
        purchaseOrderRepo.save(PurchaseOrderSheet.builder().PurchaseCode("P001").build());

        puritemRepo.save(com.example.demo.mainEntity.PurchaseOrderSheetId.builder().purchaseOrderSheet(purchaseOrderRepo.findAll().get(0)).itemInfo(itemInfoRepo.findById("GF001").get()).build());
        puritemRepo.save(com.example.demo.mainEntity.PurchaseOrderSheetId.builder().purchaseOrderSheet(purchaseOrderRepo.findAll().get(0)).itemInfo(itemInfoRepo.findById("GF002").get()).build());
        // 사용 예시
       *//* List<PurchaseOrderSheetItemInfo> itemsForP001 = puritemRepo.findByPurchaseOrderSheet_PurchaseCode("P001");
        System.out.println(itemsForP001);*//*
        List<com.example.demo.mainEntity.PurchaseOrderSheetId> list=puritemRepo.findItemInfobyPurchaseCode("P001");
        System.out.println("크기"+list.size());
        for(int i=0;i<list.size();i++)
            System.out.println(list.get(i).getItemInfo());
        for (com.example.demo.mainEntity.PurchaseOrderSheetId list2 : list) {
            System.out.println(list2.getItemInfo().getItemCode());
        }


        List<String> aa=new ArrayList<>();
        aa.add(purchaseOrderRepo.findnyPurchaseDateIsNull().get(0).getPurchaseCode());
        System.out.println("나와라 "+aa);
    }*/
    @Commit
    @Transactional
    @Test
    public void dsdfsd(){

    }

}