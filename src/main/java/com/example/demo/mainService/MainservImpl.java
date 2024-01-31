package com.example.demo.mainService;

import com.example.demo.mainDTO.ContractDTO;
import com.example.demo.mainDTO.ItemInfoDTO;
import com.example.demo.mainDTO.ProductDTO;
import com.example.demo.mainDTO.ProductionPlanDTO;
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
}