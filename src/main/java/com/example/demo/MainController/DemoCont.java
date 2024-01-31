package com.example.demo.MainController;

import com.example.demo.changeList.*;
import com.example.demo.dto.AuthMemberDTO;
import com.example.demo.mainDTO.*;
import com.example.demo.mainEntity.*;
import com.example.demo.mainService.InventoryReportService;
import com.example.demo.mainService.Mainserv;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.List;

@Controller
@Log4j2
@AllArgsConstructor
public class DemoCont {
    private Mainserv mainserv;
    private InventoryReportService inventoryReportService;

///////////////////여기부터 로그인 맵핑
    // 로그인 페이지
@GetMapping("/login")
public String login(Model model) {
    System.out.println("로그인 진입Get");
    model.addAttribute("memberDTO",
            new AuthMemberDTO("username",
                    "password",
                    List.of(new SimpleGrantedAuthority("ROLE_USER"))) //AuthoDTO에는 인가 관련 메서드가 컬렉션 타입 그런List.of로 불러옴
    );
    return "/login";
}
    @PostMapping("/login")
    public String loginProcess(AuthMemberDTO authMemberDTO){
        //여기서 로그인 처리를 수행
        //성공 시 리다이렉트를 할 경로를 반환
        return "/index";
    }
    @GetMapping("/logout")
    public void logout(){   }
    @PostMapping("/logout")
    public String logout_Post() {
        return "logout";
    }
    @PostMapping("/index")
    public String home() {
        return "index";
    }
    @GetMapping("/index")
    public void index(){   }
///////////////////////////로그인 끝//////////////////////////////////

/////////인쇄///////////
    @GetMapping("/order/Purchase")
    public String print1(@RequestParam(name = "purchaseCode", defaultValue = "") String purchaseCode, Model m){
        System.out.println("Purchase Code in Controller: " + purchaseCode);
        m.addAttribute("purchaseCode", purchaseCode);

    //20칸의 품목 가능
        List<PurchaseOrderSheet> list=mainserv.findItemInfobyCode(purchaseCode);
        List<ChangePrintPurchase> purlist=new ArrayList<>();
        for(int i=0;i< list.size();i++){
            purlist.add(ChangePrintPurchase.builder().itemName(list.get(i).getId().getItemInfo().getItemName()).
                    itemCount(list.get(i).getId().getItemInfo().getItemCount()).
                    Price(list.get(i).getId().getItemInfo().getPrice()).
                    ProcurementDate(mainserv.findItemfromProcurement(list.get(i).getId().getProductionPlan().getProductionPk(),list.get(i).getId().getItemInfo().getItemCode()).getProcurementDate()).build());
        }
        LocalDate now=LocalDate.now();
        m.addAttribute("date",now);
        m.addAttribute("list",list);
        m.addAttribute("pur",purlist);
        return "order/Purchase";
    }
    @GetMapping("/order/Invoice")
    public String print2(@RequestParam(name = "purchaseCode", defaultValue = "") String purchaseCode, Model m){
        System.out.println("Purchase Code in Controller: " + purchaseCode);
        m.addAttribute("purchaseCode", purchaseCode);

       
        List<PurchaseOrderSheet> list=mainserv.findItemInfobyCode(purchaseCode);
        List<InboundOutbound> inbound=new ArrayList<>();
        for(int i=0;i<list.size();i++){
            inbound.add(mainserv.findbyItemcode(list.get(i).getId().getItemInfo().getItemCode()));
        }
        List<ChangePrintPurchase> purlist=new ArrayList<>();
        List<ItemInfo> itemList=new ArrayList<>();
        for(int i=0;i< list.size();i++){
            itemList.add(ItemInfo.builder().itemCode(list.get(i).getId().getItemInfo().getItemCode()).itemName(list.get(i).getId().getItemInfo().getItemName()).
                    itemCount(inbound.get(i).getInboundQuantity()).price(list.get(i).getId().getItemInfo().getPrice()).build());
        }

        Long count=0L;
        for(int i=0;i< itemList.size();i++){
            count+=itemList.get(i).getItemCount()*itemList.get(i).getPrice();
        }

            Date now=new Date();
        m.addAttribute("date",now);
        m.addAttribute("count",count);
        m.addAttribute("itemList",itemList);
        return "order/Invoice";
    }
///////////인쇄 끝////////

    @GetMapping("/")
    public String dd(Model m) {
        m.addAttribute("countBA",mainserv.countBA());
        m.addAttribute("countBS",mainserv.countBS());
        m.addAttribute("countCB",mainserv.countCB());
        m.addAttribute("countCM",mainserv.countCM());
        m.addAttribute("countGF",mainserv.countGF());
        return "/tables1-1";
    }
    @GetMapping({"/tables1-3", "/tables2-3", "/tables3-1","/InvoicePrint", "PurchasePrint", "/tables2-3copy"})
    public void aa() {
        log.info("왔냐");
    }


    @GetMapping("/tables1-1") //품목정보조회 페이지
    public void intoItems(Model m) {

        m.addAttribute("countBA",mainserv.countBA());
        m.addAttribute("countBS",mainserv.countBS());
        m.addAttribute("countCB",mainserv.countCB());
        m.addAttribute("countCM",mainserv.countCM());
        m.addAttribute("countGF",mainserv.countGF());


    }

    @PostMapping("/regiitem") //품목정보등록 페이지
    public String c(@RequestParam("s1") String code, @RequestParam("s2") String name, @RequestParam("s3") String large, @RequestParam("s4") String medium,
                    @RequestParam("s5") String standard, @RequestParam("s6") String texture, @RequestParam("s7") Long count,
                    @RequestParam("s8") String dnumber, @RequestParam("s9") MultipartFile file) throws IOException {

        log.info("안녕");

        String fileName = file.getOriginalFilename();
        log.info(fileName);
        String str=file.getContentType();

        //이걸 dto로 변경하고
        ItemInfoDTO dto = new ItemInfoDTO();          //large와 미디움 이미지
        dto.setItemCode(code);
        dto.setItemName(name);
        dto.setLarge(large);
        dto.setMedium(medium);
        dto.setStandard(standard);
        dto.setTexture(texture);
        dto.setItemCount(count);
        dto.setDrawingNumber(dnumber);
        dto.setDrawingImage(Base64.getEncoder().encodeToString(file.getBytes()));
        dto.setImageType(str.substring(str.indexOf("/")+1));

        log.info("dto다  : " + dto);
        //dto를 entity로 변경해서 등록하는 서비스
        mainserv.regiItemInfo(dto);


        return "redirect:/tables1-1";
    }

    @PostMapping("/tables1-1/Search")
    public String showItems(Model m) {
        log.info("조회버튼 누름  ");
        List<ItemInfo> list=mainserv.readItemInfo();

        m.addAttribute("s", list);


        m.addAttribute("countBA",mainserv.countBA());
        m.addAttribute("countBS",mainserv.countBS());
        m.addAttribute("countCB",mainserv.countCB());
        m.addAttribute("countCM",mainserv.countCM());
        m.addAttribute("countGF",mainserv.countGF());
        return "/tables1-1";
    }



    @GetMapping("/tables1-2") // 계약조회 페이지
    public void vender(Model m){
        List<ItemInfo> itemlist=mainserv.findContractIsNull();
        List<Changedddddd> list=new ArrayList<>();
        //if(!itemlist.isEmpty()){
        for(int i=0;i<itemlist.size();i++){
            list.add(new Changedddddd(itemlist.get(i).getItemCode(),itemlist.get(i).getItemName(),itemlist.get(i).getTexture()));
        }
        //}
        List<Vendor> vendors=mainserv.findVendorAll();

        int count=mainserv.countContract();
        log.info(count+"몇개");
        String name="";
        if(count==0){
            name+="A00"+count;
            m.addAttribute("name",name);
        } else if (count>0 && count<10) {
            name+="A00"+count;
            m.addAttribute("name",name);
        } else if (count>9 && count<100) {
            name+="A0"+count;
            m.addAttribute("name",name);
        } else if (count>100) {
            name+="A"+count;
            m.addAttribute("name",name);
        }



        m.addAttribute("item",list);
        m.addAttribute("vendor",vendors);
    }

    @PostMapping("/abcd")     //계약등록 페이지
    public String contract(Model m, @RequestParam("s1") String business, @RequestParam("s2") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
                           @RequestParam("s3") String[] itemcode, @RequestParam("s4") Long[] price,
                           @RequestParam("s5") Long[] leadtime, @RequestParam("s6") String sig,@RequestParam("contractCode") String code,
                           @RequestParam("file") MultipartFile file) throws IOException {

        String str=file.getContentType();

        ContractDTO dto = new ContractDTO();
        dto.setContractCode(code);
        dto.setContractDate(date);
        dto.setContractScan(Base64.getEncoder().encodeToString(file.getBytes()));
        dto.setImageType(str.substring(str.indexOf("/")+1));
        dto.setSignificant(sig);
        log.info(dto.toString());

        for(int i=0;i<itemcode.length;i++){
            log.info("아템"+itemcode[i]);
            mainserv.connectItemAndContract(mainserv.regiContract(dto, mainserv.findvendor(business)), itemcode[i], price[i], leadtime[i]);
        }

        int count=mainserv.countContract();
        log.info(count+"몇개");
        String name="";
        if(count==0){
            name+="A00"+count;
            m.addAttribute("name",name);
        } else if (count>0 && count<10) {
            name+="A00"+count;
            m.addAttribute("name",name);
        } else if (count>9 && count<100) {
            name+="A0"+count;
            m.addAttribute("name",name);
        } else if (count>99) {
            name+="A"+count;
            m.addAttribute("name",name);
        }

        return "redirect:/tables1-2";
    }

    @PostMapping("/tables1-2/Search")
    public String ContractSearch(Model m,
                                 @RequestParam("fromDate")
                                 @DateTimeFormat(pattern = "yyyy-MM-dd") Date dat1,
                                 @RequestParam("toDate")
                                 @DateTimeFormat(pattern = "yyyy-MM-dd") Date dat2) {


        m.addAttribute("contractSearch", mainserv.findVendorAll());
        List<Contract> contractSearchDate = mainserv.findByContactDateBetween(dat1, dat2);
        List<ItemInfo> contractItems = mainserv.findContractIsNotNull();
        log.info(contractItems.get(0).getItemCode());

        log.info("선택된 시작 일  :  " + dat1);
        log.info("선택된 끝 일 : " + dat2);
        List<ChangeContractSearch> serches = new ArrayList<>();
        for (int i = 0; i < contractSearchDate.size(); i++) {
            //log.info("검색된 계약번호 : " + contract.getContractCode());
            serches.add(ChangeContractSearch
                    .builder()
                    .contractCode(contractItems.get(i).getContract().getContractCode())
                    .vendorName(contractSearchDate.get(i).getVendor().getVendorName())
                    .businessLicense(contractItems.get(i).getContract().getVendor().getBusinessLicense())
                    .contractdate(contractItems.get(i).getContract().getContractDate())
                    .itemname(contractItems.get(i).getItemName())
                    .contractScan(contractSearchDate.get(i).getContractScan())
                    .imageType(contractItems.get(i).getImageType())

                    .build());
//        log.info("나오나?" + contractItems);
        }
        log.info(serches + "kkkk");

        //검색결과를 모델에 추가
        m.addAttribute("contract", serches);
        m.addAttribute("vendor",mainserv.findVendorAll());
        return "/tables1-2";
    }

    @PostMapping("tables1-3") //조달계획등록 페이지 출력
    public String procurement_plan(Model m, @RequestParam(name = "fromDate", defaultValue = "") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dat1,
                                   @RequestParam(name = "toDate", defaultValue = "") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dat2) {
        List<ProductionPlan> productionPlans=new ArrayList<>();

        if(dat1!=null && dat2!=null) {
            log.info("시작날짜 : " + new SimpleDateFormat("yyyy년 MM월 dd일").format(dat1));
            log.info("마지막날짜 : " + new SimpleDateFormat("yyyy년 MM월 dd일").format(dat2));

            //java.sql.Date sqlStartDate = new java.sql.Date(dat1.getTime());
            //java.sql.Date sqlEndDate = new java.sql.Date(dat2.getTime());

            productionPlans = mainserv.findByProductionComple(dat1, dat2,false); //A00001,A00002,A00003....
        }


        if(!productionPlans.isEmpty()) {
            log.info("3");
            log.info("dddddddddddddd  :  " + productionPlans.get(0).getProductionDate());
            log.info("나오나?" + productionPlans.size());

            //원래는 여기에 어떤 제품을 생산하는건지 (ex 'G1') 고르는 것도 넣어야 하는데    //
            //여기는 하나의 제품만 생산한다고 가정했기에 생략함                           //
            //그래서 List<ItemInfo> itemInfo= mainserv.readItemInfo(); 이와같이 모든 품목을 다 불러오는거임

            //itemcount*4*120>inventory
            List<ItemInfo> itemInfo;//
            List<ProcurementPlan> registeredPlan;
            List<ChangeProcurement> pro = new ArrayList<>();

            for (int j = 0; j < productionPlans.size(); j++) {
                registeredPlan=mainserv.findById_ProductionPlan_ProductionPk(productionPlans.get(j).getProductionPk()); //조달계획서에 등록되어 있는 품목 출력
                itemInfo= mainserv.findContractIsNotNull(); //계약서 등록된 모든 품목 출력 (위와 비교할 거임
                log.info(itemInfo.size());
                log.info("등록된게 몇개냐"+registeredPlan.size());
                for (int y = 0; y < registeredPlan.size(); y++) { //여긴 조달계획서에 등록되어 있는거 확인하는 테스트 코드 (생산계획id + 품목 id)
                    log.info(registeredPlan.get(y).getId().getProductionPlan().getProductionPk()+" "+registeredPlan.get(y).getId().getItemInfo().getItemCode());
                }

                if(!registeredPlan.isEmpty()) { //조달계획서에 등록되어 있는게 있다면 -> 전체 품목에서 해당 부분을 삭제해야함
                    log.info("삭제 전 " + itemInfo.size());
                        for (int v = 0; v < registeredPlan.size(); v++) {
                            for (int b = 0; b < itemInfo.size(); b++)
                                itemInfo.remove(registeredPlan.get(v).getId().getItemInfo());
                        }
                    log.info("삭제 후 " + itemInfo.size());
                }
                log.info("삭제 됐? " + itemInfo.size());
                        String str="ㅇㅇ";
                        for (int i = 0; i < itemInfo.size(); i++) {
                            if (itemInfo.get(i).getPrice() != null) { //가격이 등록되어 있다는 것은 계약이 되어 있다는 소리
                                if (itemInfo.get(i).getItemCount() * 1.20 * productionPlans.get(j).getProductionQuantity() > itemInfo.get(i).getInventory()) { //기본재고가 120% 보다 많으면 생산 필요 x
                                    //조달계획서에 품목 등록
                                    log.info("숫자"+i);
                                    if(i<(itemInfo.size()-1)) {
                                        pro.add(new ChangeProcurement(productionPlans.get(j).getProductionPk(), itemInfo.get(i).getItemCode(), itemInfo.get(i).getItemName(), itemInfo.get(i).getStandard(),
                                                itemInfo.get(i).getTexture(), itemInfo.get(i).getDrawingNumber(), itemInfo.get(i).getDrawingImage(), itemInfo.get(i).getImageType(),
                                                itemInfo.get(i).getLeadTime(), itemInfo.get(i).getContract().getVendor().getVendorName(), productionPlans.get(j).getProductionDate(),
                                                productionPlans.get(j).getProductionQuantity(), itemInfo.get(i).getItemCount(), itemInfo.get(i).getInventory(), str));
                                    }
                                }

                            }
                            if(i==(itemInfo.size()-1)){
                                log.info("없냐");
                                pro.add(new ChangeProcurement(productionPlans.get(j).getProductionPk(), itemInfo.get(i).getItemCode(), itemInfo.get(i).getItemName(), itemInfo.get(i).getStandard(),
                                        itemInfo.get(i).getTexture(), itemInfo.get(i).getDrawingNumber(), itemInfo.get(i).getDrawingImage(), itemInfo.get(i).getImageType(),
                                        itemInfo.get(i).getLeadTime(), itemInfo.get(i).getContract().getVendor().getVendorName(), productionPlans.get(j).getProductionDate(),
                                        productionPlans.get(j).getProductionQuantity(), itemInfo.get(i).getItemCount(), itemInfo.get(i).getInventory(), ""));
                            }

                        }

            }
            m.addAttribute("item", pro);
            m.addAttribute("nothing", ""); // 빈 문자열로 설정
        }else{
            m.addAttribute("nothing","생산계획서에 없는 날짜입니다.");
        }
        return "/tables1-3";
    }


    @GetMapping("/tables2-1") //품목정보조회 페이지
    public void aashowItems(Model m) {
        List<PurchaseOrderSheet> pur = mainserv.findPurchaseDateNull();
        List<String> manyData=new ArrayList<>();
        for(int i=0;i<pur.size();i++)
            manyData.add(pur.get(i).getId().getPurchaseCode());

        Set<String> uniqueSet = new HashSet<>(manyData); //중복 제거
        List<String> uniqueData = new ArrayList<>(uniqueSet); //중복 제거된 발주코드
        Collections.sort(uniqueData); //정렬

        m.addAttribute("choice", uniqueData);
    }

    @PostMapping("/purchoice")
    public String dddad(Model m, @RequestParam("sel") String code) {

            log.info("제대로 왔나" + code);

            List<PurchaseOrderSheet> info = mainserv.findItemInfobyCode(code);
            log.info("1차 : " + info.get(0).toString());
            log.info("2차 : " + info.get(0).getId().getItemInfo());

            List<ItemInfo> itemList = new ArrayList<>();
            List<ProcurementPlan> proList = new ArrayList<>();


            for (int i = 0; i < info.size(); i++) {
                log.info(i);
                itemList.add(mainserv.test(info.get(i).getId().getItemInfo().getItemCode()));
                log.info(i);
                proList.add(mainserv.findItemfromProcurement(info.get(i).getId().getProductionPlan().getProductionPk(), info.get(i).getId().getItemInfo().getItemCode()));
                log.info("dddddddddddd");
            }
            log.info(itemList.size());
            log.info(itemList.get(0));
            log.info("================================");
            log.info(proList.size());
            log.info(proList.get(0));

            List<ChangePurchaseOrder> change = new ArrayList<>();
            for (int i = 0; i < info.size(); i++) {
                change.add(new ChangePurchaseOrder(itemList.get(i).getItemCode(), itemList.get(i).getItemName(),
                        proList.get(i).getProcurementQuantity(), proList.get(i).getProcurementDate(), itemList.get(i).getContract().getVendor().getBusinessLicense(),
                        itemList.get(i).getContract().getVendor().getVendorName(), itemList.get(i).getPrice()));
                log.info(proList.get(i).getProcurementDate());
            }
            log.info("실험 " + change.get(0).toString());
            List<PurchaseOrderSheet> pur = mainserv.findPurchaseDateNull();
            List<String> manyData = new ArrayList<>();
            for (int i = 0; i < pur.size(); i++)
                manyData.add(pur.get(i).getId().getPurchaseCode());

            Set<String> uniqueSet = new HashSet<>(manyData); //중복 제거
            List<String> uniqueData = new ArrayList<>(uniqueSet); //중복 제거된 발주코드
            Collections.sort(uniqueData); //정렬

            m.addAttribute("choice", uniqueData);
            m.addAttribute("code", code);
            m.addAttribute("mail",pur.get(0).getId().getItemInfo().getContract().getVendor().getVendorEmail());
            m.addAttribute("list", change);
            log.info("끝?");

        return "tables2-1";
    }

    @GetMapping("tables2-2")
    public void sdfassddds(Model m) {
        /*
        1.발행된 발주서 검색 p001, p002 가 있다고 가정

        2.발행된 발주서로 해당하는 품목 검색
        ex p001(GF001,GF002), p002(BS001)

        3. change에 해당하는 내용 집어넣음

        .map<p001,change>

        ++inspection_date1에 값이 있으면 출력x
         */
        List<PurchaseOrderSheet> pur=mainserv.findPurchaseDatePresent();  //발행된 발주서 검색 p001, p002,p003 가 있다고 가정
        log.info("1번"+pur.size());                                          //수정 : p00, p00 이렇게 나옴 -> 이걸로 숫자를 알수 있긴한데,,,,
        List<Inspection> dateInspect = mainserv.findInspectionDate1IsNotNull();
        List<ItemInfo> itemList = new ArrayList<>();
        List<ProcurementPlan> proList = new ArrayList<>();
        List<ChangeInspectionPlan> change = new ArrayList<>();

        log.info("몇"+dateInspect.size());

///////////중복제거
        List<String> aa=new ArrayList<>();//(발주서코드 저장)
        for (PurchaseOrderSheet purItem : pur) {
            boolean isMatched = false;  //매치되지 않은 상태

            for (Inspection inspectItem : dateInspect) {
                if (purItem.getId().getPurchaseCode().equals(inspectItem.getPurchaseOrderSheet().getId().getPurchaseCode())) {
                    // 발주서 코드가 서로 일치하는 경우, 일치하는 것이 있다고 표시
                    isMatched = true;
                    break;
                }
            }

            // 발주서 코드가 서로 일치하지 않는 경우에만 aa에 추가
            if (!isMatched) { //매치되지 않으면 저장
                aa.add(purItem.getId().getPurchaseCode());
            }
        }
        Set<String> uniqueSet = new HashSet<>(aa); //중복 제거
        List<String> uniqueaa = new ArrayList<>(uniqueSet); //중복 제거된 발주코드
        Collections.sort(uniqueaa); //정렬
        log.info("몇개인가"+uniqueaa);
//////////////

        for(int i=0;i< uniqueaa.size();i++){   //일단은 2번
            log.info(i+" i");
            List<PurchaseOrderSheet> info =mainserv.findItemInfobyCode(uniqueaa.get(i)); //첫번째 발주서로 품목 검색  (a1,a2 - 한바퀴 돌면서 또 a1.a2)
            //log.info("2번 "+info.size()+" "+info.get(i).getPurchaseOrderSheet().getPurchaseCode());

            for (int j = 0; j < info.size(); j++) { //해당 DB 값 추출
                log.info("여긴가"+info.get(j).getId().getItemInfo().getItemCode());
                itemList.add(mainserv.test(info.get(j).getId().getItemInfo().getItemCode()));
                proList.add(mainserv.findItemfromProcurement(info.get(j).getId().getProductionPlan().getProductionPk(),info.get(j).getId().getItemInfo().getItemCode()));
            }
            log.info(proList.size());
            log.info("3번 "+itemList.size());
            for (int k = 0; k < itemList.size(); k++) { //타임리프로 출력할 값만 집어넣기
                change.add(new ChangeInspectionPlan(uniqueaa.get(i),itemList.get(k).getItemCode(),
                        itemList.get(k).getItemName(),itemList.get(k).getTexture(),itemList.get(k).getPrice(),
                        proList.get(k).getProcurementQuantity(),proList.get(k).getProcurementDate()));
            }
            log.info("4번");
            itemList.clear(); proList.clear();
            //info.clear();
        }
        log.info("4번"+change.size());
        log.info(change.toString());
        m.addAttribute("list2",change); //진척검수계획 tab1


//////////////////////////////mainserv.findInspecCompleteIsNotNull()
        List<Inspection> datelist = mainserv.findInspectionDate1IsNotNullandComple(); //진척검수 일정 입력한 p00, p01 출력하기
        //발주서번호	품목코드	품목명	규격	진척도	입고예정일	진척검수	거래처	담당자메일	진척검수
        List<PurchaseOrderSheet> purchaseOrderSheetItemInfos=new ArrayList<>();
        List<ItemInfo> itemInfos =new ArrayList<>();
        List<ProcurementPlan> procurementPlans=new ArrayList<>();
        Map<String,List<ChangeInspectionProcess>> map =new HashMap<>();
        for(int i=0;i<datelist.size();i++) {
            purchaseOrderSheetItemInfos=mainserv.findItemInfobyCode(datelist.get(i).getPurchaseOrderSheet().getId().getPurchaseCode());
            List<ChangeInspectionProcess> changeInspectionProcesses=new ArrayList<>();

            for(int j=0;j<purchaseOrderSheetItemInfos.size();j++) {
                itemInfos.add(mainserv.test(purchaseOrderSheetItemInfos.get(j).getId().getItemInfo().getItemCode()));
                procurementPlans.add(mainserv.findItemfromProcurement(purchaseOrderSheetItemInfos.get(j).getId().getProductionPlan().getProductionPk(),purchaseOrderSheetItemInfos.get(j).getId().getItemInfo().getItemCode()));
                changeInspectionProcesses.add(ChangeInspectionProcess.builder().itemCode(itemInfos.get(j).getItemCode()).itemName(itemInfos.get(j).getItemName())
                                .texture(itemInfos.get(j).getTexture()).procurementDate(procurementPlans.get(j).getProcurementDate()).
                        vendorName(itemInfos.get(j).getContract().getVendor().getVendorName()).vendorEmail(itemInfos.get(j).getContract().getVendor().getVendorEmail())
                        .build());
            }
            map.put(datelist.get(i).getPurchaseOrderSheet().getId().getPurchaseCode(),changeInspectionProcesses);
            itemInfos.clear(); procurementPlans.clear();
        }

        m.addAttribute("maplist",map);


    }

//진척검수일정 등록
    @PostMapping("/adcess")
    public String asdfsd(@RequestParam("a1") @DateTimeFormat(pattern = "yyyy-MM-dd") Date[] dat,@RequestParam("purcode") String purcode,@RequestParam("itemcode") String[] itemcode) {
        String procode=mainserv.findProcodbyPurItem(purcode,itemcode[0]);
        log.info(procode);
        log.info("오긴하나 " + dat[0]);
        log.info(purcode);
        List<Date> list = new ArrayList<>();
        for (int i = 0; i < dat.length; i++) {
            list.add(dat[i]);
            log.info("ddd " + list.get(i));
        }

        if(list.size()==1){
            mainserv.saveDate1(list.get(0),purcode,procode,itemcode[0]);
        } else if (list.size()==2) {
            mainserv.saveDate2(list.get(0),list.get(1),purcode,procode,itemcode[0]);
        } else if (list.size()==3) {
            mainserv.saveDate3(list.get(0),list.get(1),list.get(2),purcode,procode,itemcode[0]);
        }
        return "redirect:tables2-2";
    }

//진척검수처리
    @GetMapping("/insertContent")
    public String fasdf(@RequestParam(name = "purcode", required = true) String purcode,
                        @RequestParam(name = "content", required = true) String content,
                        @RequestParam(name = "order", required = true) String order) {


        // 예시로 받아온 값을 콘솔에 출력하는 부분
        System.out.println("Received purcode: " + purcode);
        System.out.println("Received content: " + content);
        System.out.println("Received order: " + order);
        mainserv.insertContent(content,purcode,order);

        Inspection aa=mainserv.findPcode(purcode);
        Date dat=new Date();

        if(aa.getInspectionDate2()!=null && aa.getInspectionDate3()!=null && order.equals("3차")){ //3
            mainserv.insertComplete(dat,purcode);
        }else if(aa.getInspectionDate2()!=null && aa.getInspectionDate3()==null && order.equals("2차")){ //2
            mainserv.insertComplete(dat,purcode);
        }else if(aa.getInspectionDate1()!=null && aa.getInspectionDate2()==null && aa.getInspectionDate3()==null){ //1
            mainserv.insertComplete(dat,purcode);
        }


        return "/tables2-2";
    }


    @PostMapping("tables3-1")
    public String findinbound(Model m, @RequestParam(name = "fromDate", defaultValue = "") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dat1,
                                   @RequestParam(name = "toDate", defaultValue = "") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dat2) {


        /*
        1.조달계획날짜에 연관된 품목 불러오기
        2.품목을 토대로 진척검수 완료된 발주서 불러오기
        3.진척검수 완료된 발주서로 입고안된 품목 불러오기
        4.모델로 보내기
         */

        List<ProcurementPlan> procurementPlans;
        List<ChangeInbound> changeInbounds =new ArrayList<>();

        if (dat1 != null && dat2 != null) { //이게 안되면 전체 실행 안됨
            log.info("시작날짜 : " + new SimpleDateFormat("yyyy년 MM월 dd일").format(dat1));
            log.info("마지막날짜 : " + new SimpleDateFormat("yyyy년 MM월 dd일").format(dat2));

//1.조달계획날짜에 연관된 품목 불러오기
            procurementPlans = mainserv.findfromDateProcure(dat1, dat2); //(A0001,a1) ,A0000~,a2....(조달계획에 해당하는 모든 품목 및 생산계획 출력)

            log.info("1번");
//2.품목을 토대로 진척검수 완료된 발주서 불러오기
            List<String> aa=new ArrayList<>();//(발주서코드 저장)
            for(int i=0;i<procurementPlans.size();i++){ //조달계획에 해당하는 발주코드 추출 (품목이 여러개면 발주서도 중복됨)
                aa.add(mainserv.findPOSIfromItem(procurementPlans.get(i).getId().getProductionPlan().getProductionPk(),procurementPlans.get(i).getId().getItemInfo().getItemCode()).getId().getPurchaseCode());
            }
            Set<String> uniqueSet = new HashSet<>(aa); //중복 제거
            List<String> uniqueaa = new ArrayList<>(uniqueSet); //중복 제거된 발주코드
            Collections.sort(uniqueaa); //정렬 p00,p01,,,,


            if(!uniqueaa.isEmpty()) {
            log.info(""+uniqueaa.size());
            log.info(""+uniqueaa.get(0));
            log.info("2번");

                List<String> last = new ArrayList<>();//(조달계획에 해당하고 + '진척검수 완료된' 발주코드 저장)
                List<Inspection> dd = mainserv.findInspecCompleteIsNotNull();  //진척검수 완료된 발주코드 찾기
                for (int i = 0; i < dd.size(); i++) {
                    log.info("i " + dd.get(i).getPurchaseOrderSheet().getId().getPurchaseCode());
                    for (int j = 0; j < uniqueaa.size(); j++) {
                        if ((dd.get(i).getPurchaseOrderSheet().getId().getPurchaseCode()).equals(uniqueaa.get(j))) {
                            last.add(uniqueaa.get(j));
                            log.info(("j " + uniqueaa.get(j)));
                        }
                    }
                }
                // last에 (조달계획에 해당하고 진척검수 완료된 발주코드 저장)됨
                if(!last.isEmpty()) {
                   // log.info("" + last.get(0));
                    log.info("3번");
                    List<PurchaseOrderSheet> ccc = new ArrayList<>();
                    for (int i = 0; i < last.size(); i++) { //last.size() p00, p01 같은 해당하는 것만 출력 가능
                        List<PurchaseOrderSheet> test = mainserv.findItemInfobyCode(last.get(i));
                        for (int j = 0; j < test.size(); j++) {
                            ccc.add(test.get(j));
                        }
                    }
                    log.info("" + ccc.get(0).getId().toString());
                    log.info("4번");
                    List<String> bb = new ArrayList<>(); //(진척 + 입고x 품목 저장)
                    List<String> pp = new ArrayList<>(); //(           발주서 코드 저장)
                    List<String> pro= new ArrayList<>(); //(            생산계획 코드 저장)
                    List<Long> proquant=new ArrayList<>();
                    for (int i = 0; i < ccc.size(); i++) {        //a1,a2...(입고 안된 품목 저장된거 )
                        if (mainserv.findbyItemCodeAndInisNull(ccc.get(i).getId().getItemInfo().getItemCode()) != null) { //null이 아님 => 입고 안된거
                            bb.add(mainserv.findbyItemCodeAndInisNull(ccc.get(i).getId().getItemInfo().getItemCode()).getItemInfo().getItemCode());
                            pp.add(ccc.get(i).getId().getPurchaseCode());
                            pro.add(ccc.get(i).getId().getProductionPlan().getProductionPk());
                           //proquant.add((ccc.get(i).))
                            log.info(mainserv.findbyItemCodeAndInisNull(ccc.get(i).getId().getItemInfo().getItemCode()).getItemInfo().getItemCode() + " sdsd");
                        }
                    }
                    //bb : 진척검수되고 '입고' 안된 품목들 a1,a2,,,,
                    //pp : "    "           "   발주코스 p00,p01,,,
                    //log.info("" + bb.get(0) + " " + pp.get(0));
                    log.info(ccc.size());

//4.모델로 보내기
                    log.info("5번");
                    if (bb != null) {
                        for (int i = 0; i < bb.size(); i++) {
                            ItemInfo xx = mainserv.test(bb.get(i));
                            changeInbounds.add(ChangeInbound.builder().purchaseCode(pp.get(i)).
                                    itemCode(mainserv.test(bb.get(i)).getItemCode()).
                                    itemName(xx.getItemName()).
                                    quantity(mainserv.findById_ItemInfo_ItemCode(xx.getItemCode()).get(0).getProcurementQuantity()).
                                    inventory(xx.getInventory()).
                                    ProcurementDate(mainserv.findItemfromProcurement(pro.get(i), bb.get(i)).getProcurementDate()).build());

                        }
                    }
                }
            }

        }

        log.info(changeInbounds.size());
        //log.info(changeInbounds.get(0).toString());
        m.addAttribute("list",changeInbounds);

        return "tables3-1";
    }


    @GetMapping("/tables3-2")
    public void dfafd(Model m){

       /* 1.입고처리 된것들만 가져오고
          2.해당하는 발주서만 가져오기
            2-1. 이 중에 발주서에 해당하는 품목 전체가 입고처리 되어야 함( P01-a1,a2 => a1만 입고처리시 x)
        */

        List<PurchaseOrderSheet> list;
        List<PurchaseOrderSheet> find= mainserv.findInvoice(); //p01, p02  //미발행 '명세서' 출력
        List<String> choice=new ArrayList<>();

        for (int i = 0; i < find.size(); i++) {
            list = mainserv.findItemInfobyCode(find.get(i).getId().getPurchaseCode()); // a1, a2
            boolean allItemsInbound = true;

            for (int j = 0; j < list.size(); j++) {
                if (mainserv.checkBounding(list.get(j).getId().getItemInfo().getItemCode())!=1) { //1이 아니다 = 입고처리된게 아님
                    allItemsInbound = false;
                    break;
                }
            }
            if (allItemsInbound) {
                choice.add(find.get(i).getId().getPurchaseCode());
            }
            list.clear();
        }

        ///////////중복제거
        Set<String> uniqueSet = new HashSet<>(choice); //중복 제거
        List<String> uniqueaa = new ArrayList<>(uniqueSet); //중복 제거된 발주코드
        Collections.sort(uniqueaa); //정렬
//////////////


        m.addAttribute("choice",uniqueaa);

    }


    @PostMapping("/invochoice")
    public String dddaddd(Model m, @RequestParam("sel") String code) {
        log.info("제대로 왔나" + code);

        List<PurchaseOrderSheet> info = mainserv.findItemInfobyCode(code);
        log.info("1차 : " + info.get(0).toString());
        log.info("2차 : " + info.get(0).getId().getItemInfo());

        List<ItemInfo> itemList = new ArrayList<>();
        List<InboundOutbound> inboundList = new ArrayList<>();
        for (int i = 0; i < info.size(); i++) {
            log.info(i);
            itemList.add(mainserv.test(info.get(i).getId().getItemInfo().getItemCode()));
            log.info(i);
            inboundList.add(mainserv.findbyItemcode(itemList.get(i).getItemCode()));
        }

        List<ChangeInvoiceOrder> change = new ArrayList<>();
        for (int i = 0; i < info.size(); i++) {
            change.add(ChangeInvoiceOrder.builder().purchaseCode(code).itemCode(itemList.get(i).getItemCode()).
                    itemName(itemList.get(i).getItemName()).texture(itemList.get(i).getTexture()).
                    price(itemList.get(i).getPrice()).InboundQuantity(inboundList.get(i).getInboundQuantity()).build());
        }

        List<PurchaseOrderSheet> list=new ArrayList<>();
        List<PurchaseOrderSheet> find= mainserv.findPurchaseDatePresent(); //p01, p02
        List<String> choice=new ArrayList<>();

        for (int i = 0; i < find.size(); i++) {
            list = mainserv.findItemInfobyCode(find.get(i).getId().getPurchaseCode()); // a1, a2
            boolean allItemsInbound = true;

            for (int j = 0; j < list.size(); j++) {
                if (mainserv.checkBounding(list.get(j).getId().getItemInfo().getItemCode())!=1) {
                    allItemsInbound = false;
                    break;
                }
            }
            if (allItemsInbound) {
                choice.add(find.get(i).getId().getPurchaseCode());
            }
            list.clear();
        }

        ///////////중복제거
        Set<String> uniqueSet = new HashSet<>(choice); //중복 제거
        List<String> uniqueaa = new ArrayList<>(uniqueSet); //중복 제거된 발주코드
        Collections.sort(uniqueaa); //정렬
//////////////


        m.addAttribute("choice", uniqueaa);
        m.addAttribute("code", code);
        m.addAttribute("mail",itemList.get(0).getContract().getVendor().getVendorEmail());
        m.addAttribute("list", change);

        return "tables3-2";
    }

    @GetMapping("/tables3-3")
    public void sdfsdfd(){     }


    @PostMapping("/tables3-3") //자재출고
    public String outbound(Model m, @RequestParam(name = "fromDate", defaultValue = "") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dat1,
                                   @RequestParam(name = "toDate", defaultValue = "") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dat2) {
        /*
          먼저 날짜에 해당하고 완료되지 않은 생산계획서를 가져옴 A001 A002...
          그 중에서 입고 마감되고, 출고일자 등록 안된 것들을 가져온다

          생산계획서 기준으로 >> 근데 조달계획등록과는 다르게 모든 생산계획서를 가져오면 안됨
          입고처리라든지 등등 그거 끝난 것 까지만 가져와야함


          출고처리가 끝나면 재고수량 변경 및 생산계획 완료(모든 품목이 다 됐을시)-count조달계획-품목 해서 하면 될듯?
          */

///1-3 조달계획서꺼 그대로 가져왔음 수정해야함
        List<ProductionPlan> productionPlans=new ArrayList<>();

        if(dat1!=null && dat2!=null) {
            log.info("시작날짜 : " + new SimpleDateFormat("yyyy년 MM월 dd일").format(dat1));
            log.info("마지막날짜 : " + new SimpleDateFormat("yyyy년 MM월 dd일").format(dat2));

            productionPlans = mainserv.findByProductionComple(dat1,dat2,Boolean.FALSE); //완료되지 않은 생산계획서만 출력

        }

        //mainserv.findById_ProductionPlan_ProductionPk() 생산계획서에 해당하는 품목 출력
        if(!productionPlans.isEmpty()) {
            log.info("dddddddddddddd  :  " + productionPlans.get(0).getProductionDate());
            log.info("나오나?" + productionPlans.size());



            List<InboundOutbound> outbounds = mainserv.findNotStartOutbound(); //입고 마감 및 출고 x 품목들 a1, a2....
            List<ChangeOutbound> out = new ArrayList<>();
            List<ProcurementPlan> pro; //생산계획서에 해당하는 품목 저장
            List<String> outItems =new ArrayList<>();
            List<Long> proQuan=new ArrayList<>();
            List<Date> proDate =new ArrayList<>();
            for (int j = 0; j < productionPlans.size(); j++) {
                pro=mainserv.findById_ProductionPlan_ProductionPk(productionPlans.get(j).getProductionPk()); //생산계획서에 해당하는 품목 출력
                for (int i = 0; i < pro.size(); i++) {
                    for(int k=0;k<outbounds.size();k++){
                        if(pro.get(i).getId().getItemInfo().getItemCode().equals(outbounds.get(k).getItemInfo().getItemCode())){
                            outItems.add(outbounds.get(k).getItemInfo().getItemCode());
                            proQuan.add(pro.get(j).getProcurementQuantity());
                            proDate.add(productionPlans.get(j).getProductionDate());
                        }

                    }
                }
            }
            List<ItemInfo> itemInfos =new ArrayList<>();
            for(int i = 0; i < outItems.size(); i++){
                itemInfos.add(mainserv.test(outItems.get(i)));
            }
//품목코드	품목명	소요량(EA)	생산요청일	기초재고(EA)
            List<ChangeOutbound> list=new ArrayList<>();
            for(int i = 0; i < itemInfos.size(); i++){
                list.add(ChangeOutbound.builder().
                        itemCode(itemInfos.get(i).getItemCode()).
                        itemName(itemInfos.get(i).getItemName()).
                        itemCount(proQuan.get(i)).
                        productionDate(proDate.get(i)).
                        inventory(itemInfos.get(i).getInventory())
                        .build());
            }

            m.addAttribute("list",list);

        }else{
            m.addAttribute("nothing","생산계획서에 없는 날짜입니다.");
        }
        return "/tables3-3";
    }




    @GetMapping("/tables3-4")
    public void asdfsdf(Model m){
        List<Large> largeItems = inventoryReportService.getAllLargeItems();
        m.addAttribute("largeItems", largeItems);
    }

    ///////////////////////////////////////////// ////Test///////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/*    @GetMapping({"/test/tables1-3copy"})
    public void originalhtml() {   }

    @GetMapping("/test/tables1-1copy")
    public void cc(Model m) {
        log.info("왔어?");
        *//*log.info(mainserv.sss().get(0).getMedium().getITEM_CODE_MEDIUM());
        log.info(mainserv.sss().get(0).getMedium().getLarge().getITEM_CODE_LARGE());
        log.info(mainserv.sss().get(0).getMedium().toString());*//*
        //log.info(mainserv.sss().get(0).getContract().getVendor().getVENDOR_NAME());

        m.addAttribute("s", mainserv.readItemInfo());
        m.addAttribute("z", mainserv.readMedium());
        m.addAttribute("x", mainserv.readLarge());

    }*/
/*
    @PostMapping("/test/abc") //품목등록
    public String cc(@RequestParam("s1") String code, @RequestParam("s2") String name, @RequestParam("s3") String large, @RequestParam("s4") String medium,
                     @RequestParam("s5") String standard, @RequestParam("s6") String texture, @RequestParam("s7") Long count,
                     @RequestParam("s8") String dnumber, @RequestParam("file") MultipartFile file) throws IOException {
      *//*  String str = "BA001_D.JPG";
        byte[] bytes = str.getBytes("utf-8");*//*
        log.info("파일"+file);
        log.info("안녕");

        String fileName = file.getOriginalFilename();
        log.info(fileName);
        log.info(file.getContentType());
        String str=file.getContentType();



        //이걸 dto로 변경하고
        ItemInfoDTO dto = new ItemInfoDTO();          //large와 미디움 이미지
        dto.setItemCode(code);
        dto.setItemName(name);
        dto.setLarge(large);
        dto.setMedium(medium);
        dto.setStandard(standard);
        dto.setTexture(texture);
        dto.setItemCount(count);
        dto.setDrawingNumber(dnumber);
        dto.setDrawingImage(Base64.getEncoder().encodeToString(file.getBytes()));
        dto.setImageType(str.substring(str.indexOf("/")+1));

        log.info("dto다  : " + dto);
        //dto를 entity로 변경해서 등록하는 서비스
        mainserv.regiItemInfo(dto);

        return "redirect:/test/tables1-1copy";
    }


    @GetMapping("/test/tables1-2copy")
    public void fsdfsdf(Model m){
        m.addAttribute("vendor",mainserv.findVendorAll());
    }
    @PostMapping("test/abcd")       //Itemcode의 경우 현재 단일로만 연동되어 있음
    public String asdfsd(Model m, @RequestParam("s1") String business, @RequestParam("s2") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
                         @RequestParam("s3") String itemcode, @RequestParam("s4") Long price,
                         @RequestParam("s5") Long leadtime, @RequestParam("s6") String sig,@RequestParam("contractCode") String code,
                         @RequestParam("file") MultipartFile file) throws IOException {

        String str=file.getContentType();

        ContractDTO dto = new ContractDTO();
        dto.setContractCode(code);
        dto.setContractDate(date);
        dto.setContractScan(Base64.getEncoder().encodeToString(file.getBytes()));
        dto.setImageType(str.substring(str.indexOf("/")+1));
        dto.setSignificant(sig);
        log.info(dto.toString());
        mainserv.connectItemAndContract(mainserv.regiContract(dto, mainserv.findvendor(business)), itemcode, price, leadtime);

        return "redirect:/test/tables1-2copy";
    }*/


   /* @PostMapping("test/tables1-3copy")
    public String ss(Model m, @RequestParam("fromDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dat1,
                     @RequestParam("toDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dat2) {
        log.info("시작날짜 : " + new SimpleDateFormat("yyyy년 MM월 dd일").format(dat1));
        log.info("마지막날짜 : " + new SimpleDateFormat("yyyy년 MM월 dd일").format(dat2));

        java.sql.Date sqlStartDate = new java.sql.Date(dat1.getTime());
        java.sql.Date sqlEndDate = new java.sql.Date(dat2.getTime());

        List<ProductionPlanDTO> productionPlans = mainserv.getProductionPlansByDateRange(sqlStartDate, sqlEndDate);

        log.info("dddddddddddddd  :  " + productionPlans.get(0).getProductionDate());
        log.info("나오나?" + productionPlans.get(0).getProductionQuantity());

        //itemcount*4*120>inventory
        List<ItemInfo> itemInfo = mainserv.readItemInfo();
        List<List<ItemInfo>> itemInfo2 = new ArrayList<>();
        List<ItemInfo> plus = new ArrayList<>();

        List<ProductionPlanDTO> pro = new ArrayList<>();
        for (int j = 0; j < productionPlans.size(); j++) {
            plus.clear(); // 각 productionPlan에 대한 plus 리스트 초기화
            for (int i = 0; i < itemInfo.size(); i++) {
                if (itemInfo.get(i).getPrice() != null) {
                    if (itemInfo.get(i).getItemCount() * 1.20 * productionPlans.get(j).getProductionQuantity() > itemInfo.get(i).getInventory()) {
                        log.info("숫자" + itemInfo.get(i).getItemCount() * 1.20 * productionPlans.get(j).getProductionQuantity() + " " + itemInfo.get(i).getInventory());
                        plus.add(itemInfo.get(i));
                        log.info("계속 : " + plus.get(0).toString());
                        pro.add(productionPlans.get(j));
                    }
                }
            }
            itemInfo2.add(new ArrayList<>(plus));
        }


        log.info("몇개? " + productionPlans.toArray().length);
        log.info("몇개2 " + itemInfo2.size());
        log.info(itemInfo2.get(0).size());
        log.info(itemInfo2.get(0).get(0).getItemCode());

        for (int i = 0; i < itemInfo2.size(); i++) {
            for (int j = 0; j < itemInfo2.get(i).size(); j++) {
                log.info("리스트" + i + ", " + j + ": " + itemInfo2.get(i).get(j).toString());
            }
        }

        // m.addAttribute("count",itemInfo.toArray().length);
        m.addAttribute("item", itemInfo2);
        m.addAttribute("plan", pro);
        return "test/tables1-3copy";
    }*/

/*    @PostMapping("test/aaaaa")
    public void sdfsfs(@RequestParam("s1") Long[] s1,@RequestParam("s2") @DateTimeFormat(pattern = "yyyy-MM-dd") Date[] s2){
        log.info("되는건가 "+s1.length);
        log.info(s2[0]);
    }

    @GetMapping("test/tables2-1copy")
    public void sdfaf(Model m) {
        List<PurchaseOrderSheet> pur = mainserv.findPurchaseDateNull();
        m.addAttribute("choice", pur);
    }*/
/*
    //Join을 통해서 Object로 받아서 타임리프를 쓰려고 별짓을 다했는데 도무지 답이 안나와서
    //따로 따로 받은다음 DTO 클래스 같은거 하나 만든거에 해당하는 값만 집어 넣어서 그걸 타임리프로 보내는 방식을 사용함
    @PostMapping("test/tables2-1copy")
    public String ddd(Model m, @RequestParam("sel") String code) {
        log.info("제대로 왔나" + code);
        *//*
        List<PurchaseOrderSheetItemInfo> info=mainserv.findItemInfobyCode(code);
        log.info("1차 : "+info.get(0).toString());
        log.info("2차 : "+info.get(0).getItemInfo());

        List<ProcurementPlan> list=new ArrayList<>();
        for(int i=0;i<info.size();i++){
            list.add(mainserv.findItemfromProcurement(info.get(i).getItemInfo().getItemCode()));
        }
        log.info("과연 : "+mainserv.test("GF001"));
        Object[] list2=mainserv.test("GF001").get(0);
        log.info("실험"+list2[0]);
        *//**//*log.info("실험2"+ Arrays.toString(list2.get(0)));
        log.info("가자"+Arrays.toString(list2.get(0)));*//**//*
        ItemInfo itemInfo = (ItemInfo) list2[0];
        log.info("itemCode: " + itemInfo.getItemCode());

        log.info("조달계획 : "+list.size());
        m.addAttribute("info",info);
        m.addAttribute("procure",list);
        m.addAttribute("code",code);
        m.addAttribute("go",list2);*//*

        List<PurchaseOrderSheetItemInfo> info = mainserv.findItemInfobyCode(code);
        log.info("1차 : " + info.get(0).toString());
        log.info("2차 : " + info.get(0).getItemInfo());

        List<ItemInfo> itemList = new ArrayList<>();
        List<ProcurementPlan> proList = new ArrayList<>();
        for (int i = 0; i < info.size(); i++) {
            itemList.add(mainserv.test(info.get(i).getItemInfo().getItemCode()));
            proList.add(mainserv.findItemfromProcurement(info.get(i).getItemInfo().getItemCode()));
        }

        List<ChangePurchaseOrder> change = new ArrayList<>();
        for (int i = 0; i < info.size(); i++) {
            change.add(new ChangePurchaseOrder(itemList.get(i).getItemCode(), itemList.get(i).getItemName(),
                    proList.get(i).getProcurementQuantity(), proList.get(i).getProcurementDate(), itemList.get(i).getContract().getVendor().getBusinessLicense(),
                    itemList.get(i).getContract().getVendor().getVendorName(), itemList.get(i).getPrice()));
        }
        log.info("실험 " + change.get(0).toString());
        List<PurchaseOrderSheet> pur = mainserv.findPurchaseDateNull();
        m.addAttribute("choice", pur);
        m.addAttribute("code", code);
        m.addAttribute("list", change);

        return "test/tables2-1copy";
    }*/
/*    @GetMapping("test/tables2-2copy")
    public void sdfasss(Model m) {
        *//*
        1.발행된 발주서 검색 p001, p002 가 있다고 가정

        2.발행된 발주서로 해당하는 품목 검색
        ex p001(GF001,GF002), p002(BS001)

        3. change에 해당하는 내용 집어넣음

        .map<p001,change>
         *//*

        List<PurchaseOrderSheet> pur=mainserv.findPurchaseDatePresent();  //발행된 발주서 검색 p001, p002 가 있다고 가정
        log.info("1번"+pur.toString());
        List<ItemInfo> itemList = new ArrayList<>();
        List<ProcurementPlan> proList = new ArrayList<>();
        List<ChangeInspectionPlan> change = new ArrayList<>();
        Map<String,List<ChangeInspectionPlan>> map=new HashMap<>();
        for(int i=0;i< pur.size();i++){
            List<PurchaseOrderSheetItemInfo> info =mainserv.findItemInfobyCode(pur.get(i).getPurchaseCode()); //첫번째 발주서로 품목 검색
            for (int j = 0; j < info.size(); j++) {
                itemList.add(mainserv.test(info.get(j).getItemInfo().getItemCode()));
                proList.add(mainserv.findItemfromProcurement(info.get(j).getItemInfo().getItemCode()));
            }
            for (int k = 0; k < itemList.size(); k++) {
                change.add(new ChangeInspectionPlan(pur.get(i).getPurchaseCode(),itemList.get(k).getItemCode(),
                        itemList.get(k).getItemName(),itemList.get(k).getTexture(),itemList.get(k).getPrice(),
                        proList.get(k).getProcurementQuantity(),proList.get(k).getProcurementDate()));

            }
            map.put(pur.get(i).getPurchaseCode(),change);
            //info.clear();

        }*/
        /*List<PurchaseOrderSheetItemInfo> info =mainserv.findItemInfobyCode(pur.get(0).getPurchaseCode()); //발행된 발주서로 해당하는 품목 검색
                                                                                                         //ex p001(GF001,GF002), p002(BS001)
        log.info("2번"+info.size());
        List<ItemInfo> itemList = new ArrayList<>();
        List<ProcurementPlan> proList = new ArrayList<>();
        //그렇다면 이건 2번 회전하게 됨
        for (int i = 0; i < info.size(); i++) {
            itemList.add(mainserv.test(info.get(i).getItemInfo().getItemCode()));
            proList.add(mainserv.findItemfromProcurement(info.get(i).getItemInfo().getItemCode()));
        }
        log.info("3번"+proList.size());
        List<ChangeInspectionPlan> change = new ArrayList<>();
        Map<String,List<ChangeInspectionPlan>> map=new HashMap<>();
        for (int i = 0; i < itemList.size(); i++) {
            change.add(new ChangeInspectionPlan(pur.get(0).getPurchaseCode(),itemList.get(i).getItemCode(),
                    itemList.get(i).getItemName(),itemList.get(i).getTexture(),itemList.get(i).getPrice(),
            proList.get(i).getProcurementQuantity(),proList.get(i).getProcurementDate()));
            map.put("P001",change);
        }
        log.info("4번"+change.size());
        m.addAttribute("list",map);


    }
*/
    /*@PostMapping("/test/adcess")
    public String asdfsd(@RequestParam("a1") @DateTimeFormat(pattern = "yyyy-MM-dd") Date[] dat) {
        log.info("오긴하나 " + dat[0]);
        List<Date> list = new ArrayList<>();
        for (int i = 0; i < dat.length; i++) {
            list.add(dat[i]);
        }
        log.info("ddd " + list.get(0));
        mainserv.regiInspectionDate(list);

        log.info(mainserv.test3().get(0).getInspectionDates());

        return "test/tables2-2copy";
        return null;
    }*/
}