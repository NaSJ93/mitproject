package com.example.demo.mainService;

import com.example.demo.mainDTO.ContractDTO;
import com.example.demo.mainDTO.ItemInfoDTO;
import com.example.demo.mainEntity.*;

import java.util.Date;
import java.util.List;

public interface Mainserv {
//test 완성되면 정식으로 변경
    ProcurementPlan testtttttt(String procode,String itemcode);






    List<ProcurementPlan> findById_ProductionPlan_ProductionPk(String productionPlanProductionPk);



    List<ItemInfo> fidne();
//품목정보등록 관련
    List<ItemInfo> readItemInfo(); //품목정보등록 읽어보기
    List<Medium> readMedium(); //Medium 읽어오기
    List<Large> readLarge(); // Large 읽어오기
    void regiItemInfo(ItemInfoDTO dto); //품목 등록

//품목정보 검색(ItemCode 이용)
    ItemInfo test(String code);


//생산계획서에서 날짜범위 검색
    //?월 ~ ?월까지 생산계획 검색
    List<ProductionPlan> getProductionPlansByDateRange(Date startDate, Date endDate);
    // ?월 ~ ?월까지 생산계획 검색 및 complete가 ?인 것만 출력 (완료 미완료 구분)
    List<ProductionPlan> findByProductionComple(Date startDate, Date endDate, Boolean complete);



//계약서 저장
    Contract regiContract(ContractDTO dto,Vendor ven);
//품목에 계약서 연동
    void connectItemAndContract(Contract con,String itemcode,Long price,Long leadtime);
//계약서 조회
    List<Contract> findByContactDateBetween(java.util.Date fromDate, java.util.Date toDate);
    List<ItemInfo> findContractIsNotNull();


//Vendor 검색
    Vendor findvendor(String name); //거래처 이름으로 해당 Vendor 검색
    List<Vendor> findVendorAll(); // 모든 Vendor 검색 (여기서 거래처만 빼와서 Select Box 만든다던지 등등)

//조달계획 등록
    void updateProcure(java.util.Date date, Long quantity, String procode, String itemcode);
//입출고 항목 생성
    void createInOutbound(String item);

//
    //발주서 코드 자동 생성을 위한 숫자 세기 위한 용도
    List<PurchaseOrderSheet> countPurchase();
    void firstPurchase(String str);
    //발주서 등록
    void savePurandItem(String item,String pur,String pro);

//미발행 발주서 검색
    List<PurchaseOrderSheet> findPurchaseDateNull();
//발주서 발행
    void starPurchsae(java.util.Date date, String code);
//발주서 코드 입력해서 관련 정보 출력
    List<PurchaseOrderSheet> findItemInfobyCode(String code);
//조달계획서에서 품목코드로 검색
    ProcurementPlan findItemfromProcurement(String procode,String itemcode);



//진척검수 관련
    //진척검수 일정 입력
    void regiInspectionDate(java.util.Date date,String purCode);
    //(log.info 테스트용) 일정 입력됐는지 확인용
    List<Inspection> test3();
    //발행 발주서 검색
    List<PurchaseOrderSheet> findPurchaseDatePresent();
    //일정 입력 됐는지 확인
    List<Inspection> findInspectionDate1IsNotNull();
    //일정 입력 및 미완 진척검수
    List<Inspection> findInspectionDate1IsNotNullandComple();
    //생산계획코드 검색용
    String findProcodbyPurItem(String purcode,String itemcode);
    void saveDate1(java.util.Date date1,String purcode,String procode,String itemcode);
    void saveDate2(java.util.Date date1,java.util.Date date2,String purcode,String procode,String itemcode);
    void saveDate3(java.util.Date date1,java.util.Date date2,java.util.Date date3,String purcode,String procode,String itemcode);
    //발주코드로 찾기
    Inspection findPcode(String purcode);
    //검수내용 입력
    void insertContent(String con,String purcode,String order);
    //완료
    void insertComplete(Date date,String purcode);



//자재입고 관련
    List<ProcurementPlan> findfromDateProcure(java.util.Date startDate, java.util.Date endDate);
    PurchaseOrderSheet findPOSIfromItem(String procode,String itemcode);
    void updateQuanDate(String code, java.util.Date date, Long Quantity);
    List<InboundOutbound> findbyInboundDateisNotNull();
    int checkBounding(String itemcode);
    int countPurcode(String purcode);
    //해당 아이템 코드가 입력된 입고출고DB 출력
    InboundOutbound findbyItemcode(String itemcode);
    //해당 아이템 코드가 입력되어 있고 Inbound가 Null이 아닌 입고출고DB 출력
    InboundOutbound findbyItemCodeAndInisNull(String itemcode);

    List<Inspection> findInspecCompleteIsNotNull();

    void updateInven(String itemcode,Long inven);



//명세서 관련
    //미발행 명세서 찾기
    List<PurchaseOrderSheet> findInvoice();
    //명세서 발행
    void StartInvoice(java.util.Date date, String code);


//출고 관련
    //출고처리하지 않은 것들
    List<InboundOutbound> findNotStartOutbound();
    void updateOutDateQuan(String code,Date date,Long quantity);







    default ItemInfoDTO entitytoDTO(ItemInfo entity){ //이건 일단 무시
        return ItemInfoDTO.builder().itemCode(entity.getItemCode()).standard(entity.getStandard()).texture(entity.getTexture()).itemCount(entity.getItemCount()).inventory(entity.getInventory()).drawingNumber(entity.getDrawingNumber()).drawingImage(entity.getDrawingImage()).build();
    }


    default ItemInfo itemdtotoEntity(ItemInfoDTO dto){
        //특이사항 : Inventory(재고)는 0으로 입력함 (최초 설정시에는 품목이 0이라는 설정)
        return
                ItemInfo.builder().itemCode(dto.getItemCode()).itemName(dto.getItemName()).standard(dto.getStandard()).
                                   texture(dto.getTexture()).itemCount(dto.getItemCount()).inventory(0L).
                                   drawingNumber(dto.getDrawingNumber()).drawingImage(dto.getDrawingImage()).imageType(dto.getImageType()).
                                   medium(new Medium(dto.getMedium(),new Large(dto.getLarge()))).product(Product.builder().ProductName("갤럭시").ProductCode("G1").build()).build();
    }

   /* default void iteminfoplusContract(ItemInfo item,Contract con){

    }*/

    //Contract
    default ContractDTO toContractDTO(Contract contract) {
        ContractDTO contractDTO = new ContractDTO();
        contractDTO.setContractCode(contract.getContractCode());
        contractDTO.setContractDate(contract.getContractDate());
        contractDTO.setContractScan(contract.getContractScan());
        contractDTO.setImageType(contract.getImageType());
        contractDTO.setSignificant(contract.getSignificant());

        // Vendor 정보 추가
        Vendor vendor = contract.getVendor();
        if (vendor != null) {
            contractDTO.setVendorBusinessLicense(vendor.getBusinessLicense());
            contractDTO.setVendorName(vendor.getVendorName());
            contractDTO.setVendorAddress(vendor.getVendorAddress());
            contractDTO.setVendorPhoneNumber(vendor.getVendorPnumber());
            contractDTO.setVendorEmail(vendor.getVendorEmail());
            contractDTO.setVendorPIC(vendor.getPIC());
        }

        return contractDTO;
    }

    default Contract toContract(ContractDTO dto,Vendor ven) {
        return Contract.builder().contractCode(dto.getContractCode()).contractDate(dto.getContractDate()).contractScan(dto.getContractScan()).
                significant(dto.getSignificant()).imageType(dto.getImageType()).
                vendor(ven).
                build();
    }

}
