package com.example.demo.mainService;

import com.example.demo.mainDTO.*;
import com.example.demo.mainEntity.*;
import com.example.demo.mainEntity.PurchaseOrderSheetId;
import com.example.demo.mainRepogitory.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j2
public class MainservImpl implements Mainserv {
    private final ItemInfoRepo itemInfoRepo;
    private final MediumRepo mediumRepo;
    private final LargeRepo largeRepo;
    private final ProductionPlanRepo productionPlanRepo;
    private final ContractRepo contractRepo;
    private final VendorRepo vendorRepo;
    private final PurchaseOrderRepo purchaseOrderRepo;
    private final ProcurementPlanRepo procurementPlanRepo;
    private final InspectionRepo inspectionRepo;
    private final InboundOutRepo inboundOutRepo;
    private final MemberRepository memberRepository;
  //  private final InspectionDateRepo inspectionDateRepo;

    private final ProductRepo productRepo;

  /*  @Override
    public List<ItemInfoDTO> aaa(ItemInfo entity) {
        entitytoDTO(entity);

        return null;
    }*/

    @Override
    public ProcurementPlan testtttttt(String procode, String itemcode) {
        return procurementPlanRepo.find(procode,itemcode);
    }

    @Override
    public void dummy() {
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

        vendorRepo.save(Vendor.builder().businessLicense("401-81-53863").vendorName("(주) 제이아이테크").vendorAddress("전라북도 군산시 중가도길 16 (우)54002").vendorPnumber("063-731-0088").vendorEmail("sp@ji-tech.co.kr").PIC("함석헌").build());
        vendorRepo.save(Vendor.builder().businessLicense("607-81-82587").vendorName("(주)대명").vendorAddress("창원시 마산회원구 자유무역4길 6").vendorPnumber("055-256-1188").vendorEmail("daemyung@krparachute.co.kr").PIC("손순자").build());
        vendorRepo.save(Vendor.builder().businessLicense("205-81-36868").vendorName("금하기계㈜").vendorAddress("경기도 김포시 대곶면 상마신기로 81-46").vendorPnumber("031-355-6497").vendorEmail("jmh@geumhwa.co.kr").PIC("조만현").build());
        vendorRepo.save(Vendor.builder().businessLicense("613-81-21469").vendorName("(주)세양").vendorAddress("경기도 화성시 봉담읍 효행로184번길 5-17").vendorPnumber("031-223-1943").vendorEmail("hg0305@seyeang.com").PIC("정현곤").build());
        vendorRepo.save(Vendor.builder().businessLicense("135-86-23166").vendorName("(주)스마트휴먼텍").vendorAddress("인천광역시 서구 원당대로480번길 2-1 가동").vendorPnumber("032-564-3080").vendorEmail("ntm@smart-ht.co.kr").PIC("노태문").build());
        vendorRepo.save(Vendor.builder().businessLicense("620-21-70775").vendorName("대연정밀").vendorAddress("경기도 광주시 곤지암읍 원지길 58").vendorPnumber("031-970-2002").vendorEmail("mh1210@daeyeon.com").PIC("최민호").build());
        vendorRepo.save(Vendor.builder().businessLicense("129-08-74643").vendorName("광동레이져").vendorAddress("경기도 양주시 은현면 화합로954번길 51-103").vendorPnumber("031-859-0492").vendorEmail("shlee@gwangdong.com").PIC("이성호").build());
        vendorRepo.save(Vendor.builder().businessLicense("212-81-33699").vendorName("국광플랜").vendorAddress("서울특별시 성동구 성수일로 99 서울숲 AK밸리 410호").vendorPnumber("02-3409-0412").vendorEmail("gukgwang@gwplan.com").PIC("김지연").build());

        itemInfoRepo.save(ItemInfo.builder().itemCode("BA001").itemName("메인보드1호").standard("7*15*1").texture("반도체").itemCount(1L).inventory(0L).drawingNumber("BA001_D").product(new Product("G1","갤럭시")).medium(new Medium("메인보드(A)",new Large("반도체(B)"))).build());
        itemInfoRepo.save(ItemInfo.builder().itemCode("BS001").itemName("메모리카드1호").standard("2*3*1").texture("반도체").itemCount(1L).inventory(3L).drawingNumber("BS001_D").product(new Product("G1","갤럭시")).medium(new Medium("메모리카드(S)",new Large("반도체(B)"))).build());
        itemInfoRepo.save(ItemInfo.builder().itemCode("CB001").itemName("브라켓1호").standard("3*5*1").texture("플라스틱").itemCount(6L).inventory(20L).drawingNumber("CB001_D").product(new Product("G1","갤럭시")).medium(new Medium("브라켓(B)",new Large("연결부품(C)"))).build());
        itemInfoRepo.save(ItemInfo.builder().itemCode("CB002").itemName("브라켓2호").standard("3*5*2").texture("플라스틱").itemCount(8L).inventory(65L).drawingNumber("CB002_D").product(new Product("G1","갤럭시")).medium(new Medium("브라켓(B)",new Large("연결부품(C)"))).build());
        itemInfoRepo.save(ItemInfo.builder().itemCode("CM001").itemName("나사1호").standard("2*4").texture("탄소강").itemCount(2L).inventory(250L).drawingNumber("CM001_D").product(new Product("G1","갤럭시")).medium(new Medium("나사(M)",new Large("연결부품(C)"))).build());
        itemInfoRepo.save(ItemInfo.builder().itemCode("CM005").itemName("나사5호").standard("2*8").texture("탄소강").itemCount(10L).inventory(324L).drawingNumber("CM005_D").product(new Product("G1","갤럭시")).medium(new Medium("나사(M)",new Large("연결부품(C)"))).build());
        itemInfoRepo.save(ItemInfo.builder().itemCode("GF001").itemName("액정1호").standard("15*18*1").texture("글라스").itemCount(1L).inventory(1L).drawingNumber("GF001_D").product(new Product("G1","갤럭시")).medium(new Medium("플랫(F)",new Large("액정(G)"))).build());

    }


    @Override
    public List<ProcurementPlan> findById_ProductionPlan_ProductionPk(String productionPlanProductionPk) {
        return procurementPlanRepo.findById_ProductionPlan_ProductionPk(productionPlanProductionPk);
    }
    @Override
    public List<ProcurementPlan> findById_ItemInfo_ItemCode(String itemInfoItemCode){
        return procurementPlanRepo.findById_ItemInfo_ItemCode(itemInfoItemCode);
    }


    @Override
    public List<ItemInfo> readItemInfo() {
        return itemInfoRepo.findAll();
    }

    @Override
    public List<Medium> readMedium() {
        return mediumRepo.findAll();
    }

    @Override
    public List<Large> readLarge() {
        return largeRepo.findAll();
    }

    @Override
    public void regiItemInfo(ItemInfoDTO dto) {
        itemInfoRepo.save(itemdtotoEntity(dto));
    }

    @Override
    public int countBA() {
        return itemInfoRepo.countBA();
    }
    @Override
    public int countBS() {
        return itemInfoRepo.countBS();
    }
    @Override
    public int countCB() {
        return itemInfoRepo.countCB();
    }
    @Override
    public int countCM() {
        return itemInfoRepo.countCM();
    }
    @Override
    public int countGF() {
        return itemInfoRepo.countGF();
    }

    @Override
    public List<ProductionPlan> getProductionPlansByDateRange(Date startDate, Date endDate) {

        return productionPlanRepo.findByProductionDateBetween(startDate, endDate);
    }

    @Override
    public List<ProductionPlan> findByProductionComple(Date startDate, Date endDate, Boolean complete) {
        return productionPlanRepo.findByProductionDateBetweenAndComplete(startDate,endDate,complete);
    }

    @Override
    public int countContract() {
        return contractRepo.countContract();
    }

    @Override
    public List<ItemInfo> findContractfromItem(String code) {
        return itemInfoRepo.findContractfromItem(code);
    }

    @Override
    public Contract regiContract(ContractDTO dto,Vendor ven) {
        contractRepo.save(toContract(dto,ven));
        return contractRepo.findById(dto.getContractCode()).get();
    }
///////////////////////////////////////////
    @Override
    public void connectItemAndContract(Contract con, String itemcode,Long price,Long leadtime) {
        Optional<ItemInfo> optionalItemInfo = itemInfoRepo.findById(itemcode);

        if (optionalItemInfo.isPresent()) {
            ItemInfo itemInfo = optionalItemInfo.get();
            ItemInfo updatedItemInfo = new ItemInfo(itemInfo.getItemCode(), itemInfo.getItemName(), itemInfo.getStandard(), itemInfo.getTexture(), itemInfo.getItemCount(), itemInfo.getInventory(), itemInfo.getDrawingNumber(), itemInfo.getDrawingImage(), itemInfo.getImageType(),price,leadtime, itemInfo.getProduct(), con,itemInfo.getMedium());
            itemInfoRepo.save(updatedItemInfo);
        }
    }

    @Override
    public List<Contract> findByContactDateBetween(java.util.Date fromDate, java.util.Date toDate) {
        return contractRepo.findByContractDateBetween(fromDate, toDate);
    }

    @Override
    public List<ItemInfo> findContractIsNotNull() {
        return itemInfoRepo.findContractIsNotNull();
    }

    @Override
    public List<ItemInfo> findContractIsNull() {
        return itemInfoRepo.findContractIsNull();
    }

      /*  @Override
    public void connectItemAndContract(ContractDTO dto) {
        List<String> itemCodesToUpdate = Arrays.asList("BS001", "BS003");
        //itemInfoRepo.updateContractForItems(new Contract(), itemCodesToUpdate);
    }*/

    @Override
    public Vendor findvendor(String name) {
        return vendorRepo.findbyName(name);
    }

    @Override
    public List<Vendor> findVendorAll() {
        return vendorRepo.findAll();
    }

    @Transactional
    @Override
    public void updateProcure(java.util.Date date, Long quantity, String procode,String itemcode) {
        procurementPlanRepo.updateProcure(date,quantity,procode,itemcode);
    }


    @Override
    public void createInOutbound(String item) {
        inboundOutRepo.save(InboundOutbound.builder().itemInfo(itemInfoRepo.findItemCode(item)).build());
    }

    @Override
    public List<PurchaseOrderSheet> countPurchase() {
        return purchaseOrderRepo.countPurchase();
    }

    @Override
    public void firstPurchase(String str) {
        purchaseOrderRepo.save(PurchaseOrderSheet.builder().
                id(PurchaseOrderSheetId.builder().purchaseCode(str).build()).build());
    }
    @Transactional
    @Override
    public void savePurandItem(String item, String pur, String pro) {
        purchaseOrderRepo.savePurandItem(item,pur,pro);
    }

    @Override
    public List<PurchaseOrderSheet> findPurchaseDateNull() {
        return purchaseOrderRepo.findnyPurchaseDateIsNull();
    }

    @Override
    public void starPurchsae(java.util.Date date, String code) {
        purchaseOrderRepo.startPurchase(date,code);
    }

    @Override
    public List<PurchaseOrderSheet> findItemInfobyCode(String purcode) {
        return purchaseOrderRepo.findItemInfobyPurchaseCode(purcode);
    }

    @Override
    public ProcurementPlan findItemfromProcurement(String procode,String itemcode) {
        return procurementPlanRepo.find(procode, itemcode);
        //RestCont
    }
    @Override
    public ItemInfo test(String code) { return itemInfoRepo.findItemCode(code);
    }



    @Override
    public void regiInspectionDate(java.util.Date date,String purCode) {
        /*for(int i=0;i<date.size();i++){
            inspectionDateRepo.save(InspectionDate.builder().date(date.get(i)).inspection(Inspection.builder().Inspection.builder().id(new InspectionID(itemInfoRepo.findById("GF001").get(),purchaseOrderRepo.findById("P001").get())).build()).build());
        }*/

       /* inspectionRepo.save(Inspection.builder().inspectionDates(date)
                .id(new InspectionID(itemInfoRepo.findById(itemCode).get(),purchaseOrderRepo.findById(purCode).get())).build());*/
    }

    public List<Inspection> test3(){
        return inspectionRepo.findAll();
    }

    @Override
    public List<PurchaseOrderSheet> findPurchaseDatePresent() {
        return purchaseOrderRepo.findnyPurchaseDateIsNotNull();
    }

    @Override
    public List<Inspection> findInspectionDate1IsNotNull() {
        return inspectionRepo.findInspectionDate1isNotNull();
    }

    @Override
    public List<Inspection> findInspectionDate1IsNotNullandComple() {
        return inspectionRepo.findInspectionDate1isNotNullandCompleisNull();
    }

    @Override
    public String findProcodbyPurItem(String purcode, String itemcode) {
        return purchaseOrderRepo.findProcodbyPurItem(purcode,itemcode);
    }

    @Override
    public void saveDate1(java.util.Date date1, String purcode,String procode,String itemcode) {
       inspectionRepo.save(Inspection.builder().inspectionDate1(date1).purchaseOrderSheet(purchaseOrderRepo.findById(PurchaseOrderSheetId.builder().purchaseCode(purcode).productionPlan(productionPlanRepo.findById(procode).get()).itemInfo(itemInfoRepo.findById(itemcode).get()).build()).get()).build());
    }

    @Override
    public void saveDate2(java.util.Date date1, java.util.Date date2, String purcode,String procode,String itemcode) {
        inspectionRepo.save(Inspection.builder().inspectionDate1(date1).inspectionDate2(date2).purchaseOrderSheet(purchaseOrderRepo.findById(PurchaseOrderSheetId.builder().purchaseCode(purcode).productionPlan(productionPlanRepo.findById(procode).get()).itemInfo(itemInfoRepo.findById(itemcode).get()).build()).get()).build());
    }

    @Override
    public void saveDate3(java.util.Date date1, java.util.Date date2, java.util.Date date3, String purcode,String procode,String itemcode) {
        inspectionRepo.save(Inspection.builder().inspectionDate1(date1).inspectionDate2(date2).inspectionDate3(date3).purchaseOrderSheet(purchaseOrderRepo.findById(PurchaseOrderSheetId.builder().purchaseCode(purcode).productionPlan(productionPlanRepo.findById(procode).get()).itemInfo(itemInfoRepo.findById(itemcode).get()).build()).get()).build());
    }

    @Override
    public Inspection findPcode(String purcode) {
        return inspectionRepo.findByPcode(purcode);
    }

    @Override
    public List<ProcurementPlan> findfromDateProcure(java.util.Date startDate, java.util.Date endDate) {
        return procurementPlanRepo.findByDateBetween(startDate,endDate); //
    }

    @Override
    public PurchaseOrderSheet findPOSIfromItem(String procode,String itemcode) {
        return purchaseOrderRepo.findItemInfobyItemCode(procode,itemcode);
    }

    @Override
    public void updateQuanDate(String code, java.util.Date date, Long Quantity) {
        inboundOutRepo.updateDateQuan(code, date,Quantity);
    }

    @Override
    public List<InboundOutbound> findbyInboundDateisNotNull() {
        return inboundOutRepo.findbyInboundDateisNotNull();
    }

    @Override
    public int checkBounding(String itemcode) {
        return inboundOutRepo.checkInbounding(itemcode);
    }

    @Override
    public int countPurcode(String purcode) {
        return 1; //purchaseOrderSheetId.countPurcode(purcode);
    }

    @Override
    public InboundOutbound findbyItemcode(String itemcode) {
        return inboundOutRepo.findbyItemCode(itemcode);
    }

    @Override
    public InboundOutbound findbyItemCodeAndInisNull(String itemcode) {
        return inboundOutRepo.findbyItemCodeAndInisNull(itemcode);
    }

    @Override
    public List<Inspection> findInspecCompleteIsNotNull() {
        return inspectionRepo.findInspecCompleteIsNotNull();
    }

    @Override
    public void updateInven(String itemcode, Long inven) {
        itemInfoRepo.updateInven(itemcode,inven);
    }

    @Override
    public void insertContent(String con, String purcode, String order) {
        if(order.equals("1차")){
            inspectionRepo.insertContent1(con,purcode);
        }else if(order.equals("2차")){
            inspectionRepo.insertContent2(con,purcode);
        }else{
            inspectionRepo.insertContent3(con,purcode);
        }
    }

    @Override
    public void insertComplete(Date date, String purcode) {
        inspectionRepo.insertComplete(date,purcode);
    }

    @Override
    public List<PurchaseOrderSheet> findInvoice() {
        return purchaseOrderRepo.findNotStarInvoiceDate();
    }

    @Override
    public void StartInvoice(java.util.Date date, String code) {
        purchaseOrderRepo.startInvoice(date,code);
    }

    @Override
    public List<InboundOutbound> findNotStartOutbound() {
        return inboundOutRepo.findOutbound();
    }

    @Override
    public void updateOutDateQuan(String code, Date date, Long quantity) {
        inboundOutRepo.updateOutDateQuan(code,date,quantity);
    }

    @Override
    public String findProStringobyItemCode(String itemcode) {
        return purchaseOrderRepo.findProStringobyItemCode(itemcode);
    }

    @Override
    public int countOutbound(String itemcode) {
        return inboundOutRepo.countOutbound(itemcode);
    }

    @Override
    public List<PurchaseOrderSheet> findItemInfobyProCode(String procode) {
        return purchaseOrderRepo.findItemInfobyProCode(procode);
    }

    @Override
    public void updateCompleteByProductionPk(String procode) {
        productionPlanRepo.updateCompleteByProductionPk(procode);
    }


    private ProductionPlanDTO convertToDTO(ProductionPlan productionPlan) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductCode(productionPlan.getProduct().getProductCode());
        productDTO.setProductName(productionPlan.getProduct().getProductName());

        ProductionPlanDTO productionPlanDTO = new ProductionPlanDTO();
        productionPlanDTO.setProductionPk(productionPlan.getProductionPk());
        productionPlanDTO.setProductionQuantity(productionPlan.getProductionQuantity());
        productionPlanDTO.setProductionDate(productionPlan.getProductionDate());
        productionPlanDTO.setProduct(productDTO);

        return productionPlanDTO;
    }

   /* @Override
    public List<ItemInfo> aaa() {

        return itemInfoRepo.findAll();
    }*/
////////////////거래처관리,생산계획관리 추가 시작 /////////////////////////


    @Override   // 수정 버튼 누르면 해당 버튼의 열의 데이터를 읽음
    public void findRowDataByButton(VendorDTO vendorDTO) {
        VendorDTO rowData = new VendorDTO();
        rowData.setBusinessLicense(vendorDTO.getBusinessLicense());
        rowData.setVendorName(vendorDTO.getVendorName());
        rowData.setVendorAddress(vendorDTO.getVendorAddress());
        rowData.setVendorPnumber(vendorDTO.getVendorPnumber());
        rowData.setVendorEmail(vendorDTO.getVendorEmail());
        rowData.setPIC(vendorDTO.getVendorEmail());
    }


    @Override
    public void SaveProduction(ProductionPlanToEntityDTO productionPlanToEntityDTO) {
        productionPlanRepo.save(ProductionPlan.builder()
                .productionPk(productionPlanToEntityDTO.getProductionPk())
                .productionQuantity(productionPlanToEntityDTO.getProductionQuantity())
                .productionDate(productionPlanToEntityDTO.getProductionDate())
                .product(new Product(productionPlanToEntityDTO.getProduct().getProductCode(), productionPlanToEntityDTO.getProductionPk()))
                .complete(productionPlanToEntityDTO.getComplete())
                .build());

    }

    //거래처 등록/저장
    @Override
    public void RegisterVendor(VendorDTO vendorDTO) {

        //VendorRepo 를 통해서 DB에 저장
        vendorRepo.save(Vendor.builder()
                .businessLicense(vendorDTO.getBusinessLicense())
                .vendorName(vendorDTO.getVendorName())
                .vendorAddress(vendorDTO.getVendorAddress())
                .vendorPnumber(vendorDTO.getVendorPnumber())
                .vendorEmail(vendorDTO.getVendorEmail())
                .vendorEmail(vendorDTO.getVendorEmail())
                .PIC(vendorDTO.getPIC())
                .build());
    }

    @Override //리포짓토리에서 바로 구현 A1 DB에서 출력
    public List<ProductionPlan> ShowProductionPlanList() {
        return productionPlanRepo.findAll();
    }

    @Override//DB에서 출력
    public List<Vendor> ShowVendorList() {
        return vendorRepo.findAll();
    }

    @Override
    public List<Product> ProductList() {
        //제품 리스트 불러오는 메소드
        return productRepo.findAll();
    }


    //계획 삭제
    @Override
    public void removePlan(String productionPk) {
        productionPlanRepo.deleteById(productionPk);

    }

    //거래처 삭제
    @Override
    public void removeVendor(String businessLicense) {
        vendorRepo.deleteById(businessLicense);

    }
////////////////거래처관리,생산계획관리 추가 끝 /////////////////////////

}