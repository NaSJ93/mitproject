//문서가 불러와지면
addEventListener("DOMContentLoaded", function () {
    console.log("Script loaded!");

    //('대상') Id를 가진 대상들을 찾고 선언함.
    const table = document.getElementById('table_Item_Info');

   





    // //자재품목리스트 테이블의 기본 데이터 테이블에 넣기
    // var Products = [

    //     {
    //         id: "BA001",
    //         name: "메인보드1호",
    //         category: "반도체(B)",
    //         subCategory: "메인보드(A)",
    //         size: "7*15*1",
    //         material: "반도체",
    //         quantity: 1,
    //         code: "BA001_D",
    //         image: "BA001_D.JPG",
    //         status: ""
    //     },
    //     {
    //         id: "BS001",
    //         name: "메모리카드1호",
    //         category: "반도체(B)",
    //         subCategory: "메모리카드(S)",
    //         size: "2*3*1",
    //         material: "반도체",
    //         quantity: 1,
    //         code: "BS001_D",
    //         image: "BS001_D.JPG",
    //         status: "(주)대명"
    //     },
    //     {
    //         id: "BS002",
    //         name: "메모리카드2호",
    //         category: "반도체(B)",
    //         subCategory: "메모리카드(S)",
    //         size: "2*4*1",
    //         material: "반도체",
    //         quantity: 1,
    //         code: "BS002_D",
    //         image: "BS002_D.JPG",
    //         status: ""
    //     },
    //     {
    //         id: "BS003",
    //         name: "메모리카드3호",
    //         category: "반도체(B)",
    //         subCategory: "메모리카드(S)",
    //         size: "3*4*1",
    //         material: "반도체",
    //         quantity: 1,
    //         code: "BS003_D",
    //         image: "BS003_D.JPG",
    //         status: ""
    //     },
    //     {
    //         id: "CB001",
    //         name: "브라켓1호",
    //         category: "연결부품(C)",
    //         subCategory: "브라켓(B)",
    //         size: "3*5*1",
    //         material: "플라스틱",
    //         quantity: 6,
    //         code: "CB001_D",
    //         image: "CB001_D.JPG",
    //         status: "(주)제이아이테크"
    //     },
    //     {
    //         id: "CB002",
    //         name: "브라켓2호",
    //         category: "연결부품(C)",
    //         subCategory: "브라켓(B)",
    //         size: "3*5*2",
    //         material: "플라스틱",
    //         quantity: 8,
    //         code: "CB002_D",
    //         image: "CB002_D.JPG",
    //         status: "(주)제이아이테크"
    //     },
    //     {
    //         id: "CB003",
    //         name: "브라켓3호",
    //         category: "연결부품(C)",
    //         subCategory: "브라켓(B)",
    //         size: "4*5*1",
    //         material: "플라스틱",
    //         quantity: 10,
    //         code: "CB003_D",
    //         image: "CB003_D.JPG",
    //         status: ""
    //     },
    //     {
    //         id: "CB004",
    //         name: "브라켓4호",
    //         category: "연결부품(C)",
    //         subCategory: "브라켓(B)",
    //         size: "4*5*2",
    //         material: "플라스틱",
    //         quantity: 4,
    //         code: "CB004_D",
    //         image: "CB004_D.JPG",
    //         status: ""
    //     },
    //     {
    //         id: "CB005",
    //         name: "브라켓5호",
    //         category: "연결부품(C)",
    //         subCategory: "브라켓(B)",
    //         size: "5*5*1",
    //         material: "플라스틱",
    //         quantity: 6,
    //         code: "CB005_D",
    //         image: "CB005_D.JPG",
    //         status: ""
    //     },
    //     {
    //         id: "CM001",
    //         name: "나사1호",
    //         category: "연결부품(C)",
    //         subCategory: "나사(M)",
    //         size: "2*4",
    //         material: "탄소강",
    //         quantity: 2,
    //         code: "CM001_D",
    //         image: "CM001_D.JPG",
    //         status: ""
    //     },
    //     {
    //         id: "CM002",
    //         name: "나사2호",
    //         category: "연결부품(C)",
    //         subCategory: "나사(M)",
    //         size: "2*5",
    //         material: "탄소강",
    //         quantity: 4,
    //         code: "CM002_D",
    //         image: "CM002_D.JPG",
    //         status: ""
    //     },
    //     {
    //         id: "CM003",
    //         name: "나사3호",
    //         category: "연결부품(C)",
    //         subCategory: "나사(M)",
    //         size: "2*6",
    //         material: "탄소강",
    //         quantity: 6,
    //         code: "CM003_D",
    //         image: "CM003_D.JPG",
    //         status: ""
    //     },
    //     {
    //         id: "CM004",
    //         name: "나사4호",
    //         category: "연결부품(C)",
    //         subCategory: "나사(M)",
    //         size: "2*7",
    //         material: "탄소강",
    //         quantity: 8,
    //         code: "CM004_D",
    //         image: "CM004_D.JPG",
    //         status: ""
    //     },
    //     {
    //         id: "CM005",
    //         name: "나사5호",
    //         category: "연결부품(C)",
    //         subCategory: "나사(M)",
    //         size: "2*8",
    //         material: "탄소강",
    //         quantity: 10,
    //         code: "CM005_D",
    //         image: "CM005_D.JPG",
    //         status: ""
    //     },
    //     {
    //         id: "CM006",
    //         name: "나사6호",
    //         category: "연결부품(C)",
    //         subCategory: "나사(M)",
    //         size: "2*9",
    //         material: "탄소강",
    //         quantity: 4,
    //         code: "CM006_D",
    //         image: "CM006_D.JPG",
    //         status: ""
    //     },
    //     {
    //         id: "CM007",
    //         name: "나사7호",
    //         category: "연결부품(C)",
    //         subCategory: "나사(M)",
    //         size: "2*10",
    //         material: "탄소강",
    //         quantity: 6,
    //         code: "CM007_D",
    //         image: "CM007_D.JPG",
    //         status: ""
    //     },
    //     {
    //         id: "CM008",
    //         name: "나사8호",
    //         category: "연결부품(C)",
    //         subCategory: "나사(M)",
    //         size: "2*11",
    //         material: "탄소강",
    //         quantity: 8,
    //         code: "CM008_D",
    //         image: "CM008_D.JPG",
    //         status: ""
    //     },
    //     {
    //         id: "CM009",
    //         name: "나사9호",
    //         category: "연결부품(C)",
    //         subCategory: "나사(M)",
    //         size: "2*12",
    //         material: "탄소강",
    //         quantity: 10,
    //         code: "CM009_D",
    //         image: "CM009_D.JPG",
    //         status: ""
    //     },
    //     {
    //         id: "CM010",
    //         name: "나사10호",
    //         category: "연결부품(C)",
    //         subCategory: "나사(M)",
    //         size: "2*13",
    //         material: "탄소강",
    //         quantity: 4,
    //         code: "CM010_D",
    //         image: "CM010_D.JPG",
    //         status: ""
    //     },
    //     {
    //         id: "GF001",
    //         name: "액정1호",
    //         category: "액정(G)",
    //         subCategory: "플랫(F)",
    //         size: "15*18*1",
    //         material: "글라스",
    //         quantity: 1,
    //         code: "GF001_D",
    //         image: "GF001_D.JPG",
    //         status: ""
    //     }
    //     // ... 나머지 데이터 추가
    // ];
    // //JS->html 테이블에 데이터 추가

    // let tableBody = document.querySelector(".table_Body"); //table_Body 클래스를 찾음
    // Products.forEach(function (item_Info_Products) {  //function(item)의 내용을 각 셀에 주입
    //     // 테이블의 각 셀이름 및 위치 지정
    //     var row = tableBody.insertRow(-1);
    //     var cell_Item_Code = row.insertCell(0); //품목코드
    //     var cell_Item_Name = row.insertCell(1); //품목명
    //     var cell_Large = row.insertCell(2);//대분류
    //     var cell_Medium = row.insertCell(3);//중분류
    //     var cell_Standard = row.insertCell(4);//규격
    //     var cell_Texture = row.insertCell(5);//재질
    //     var cell_Item_Count = row.insertCell(6);//개수
    //     var cell_Drawing_Number = row.insertCell(7);//도면번호
    //     var cell_Drawing_Image = row.insertCell(8);//도면이미지
    //     var cell_Vender_Name = row.insertCell(9);//거래처 유/무 (기본값 무)

    //     //원본 샘플데이터(Product)를  text 형식으로 출력

    //     cell_Item_Code.textContent = item_Info_Products.id;
    //     cell_Item_Name.textContent = item_Info_Products.name;
    //     cell_Large.textContent = item_Info_Products.category;
    //     cell_Medium.textContent = item_Info_Products.subCategory;
    //     cell_Standard.textContent = item_Info_Products.size;
    //     cell_Texture.textContent = item_Info_Products.material;
    //     cell_Item_Count.textContent = item_Info_Products.quantity;
    //     cell_Drawing_Number.textContent = item_Info_Products.code;
    //     cell_Drawing_Image.textContent = item_Info_Products.image;
    //     cell_Vender_Name.textContent = item_Info_Products.status;
    // });
    // //테이블에 데이터 넣기 끝


    //버튼 기능 설정  설정

    let btn_Item_Info_Confirm = document.getElementById("btn_Item_Info_Confirm");
    let btn_Item_Info_Search = document.getElementById("btn_Item_Info_Search")

    //품목 등록버튼 클릭
    btn_Item_Info_Confirm.addEventListener("click", function() {

        alert("품목 등록 완료")
        console.log("품목등록 버튼 클릭")
    });
    //품목등록 버튼  누를시 끝
    
    //품목조회 버튼 누를시
    btn_Item_Info_Search.addEventListener("click",function(){
        console.log("품목조회 버튼 클릭")

    });
    //품목조회 버튼 누를시 끝  

    // var dropdownLarge = document.getElementById("Dropdown_Large");

    // var categoryLargeOptions = [
    //     {"value": "0", "text": "대분류 선택"},
    //     {"value": "B", "text": "반도체(B)"},
    //     {"value": "C", "text": "연결부품(C)"},
    //     {"value": "G", "text": "액정(G)"}
    // ];

    // categoryLargeOptions.forEach(function(optionLarge) {   //categoryLargeOptions 배열내용에 function(optionLarge)를 반복한다.

    //     var newOptionLarge = document.createElement("option"); //newOptionLarge = 드롭박스에 option을 생성한다 선언

    //     newOptionLarge.value = optionLarge.value;//방금 생성한 option의 value는 optionLarge.value 값과 같다고 처리 

    //     newOptionLarge.text = optionLarge.text; // 위와 같이 text도 같은 내용 주입처리

    //     dropdownLarge.add(newOptionLarge); // 방금 생성되서 value와text가 주입된 옵션을 dropdownLarge에 추가
    // });


});

