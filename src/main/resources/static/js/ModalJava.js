


addEventListener("DOMContentLoaded",function(){ //문서가 불러와지면

    const modal = document.querySelector('.modal'); //modal 선언
    const btnOpenModal=document.querySelector('.btn_open_modal');//열기 버튼 선언
    const btnCloseModal=document.querySelector('.btn_close_modal');//닫기 버튼 선언
    const btnConfirm = document.querySelector('.btn_confirm');
    var textInput = document.getElementById('textInput');
    var drawing_Number = document.getElementById('drawing_Number');


    modal.style.display="none";

 

    btnOpenModal.addEventListener("click",function(){ //열기버튼 클릭시
        modal.style.display="flex";
    });
  

    btnCloseModal.addEventListener("click",function(){ //닫기버튼 클릭시
        modal.style.display="none";
    });

    btnConfirm.addEventListener("click",function(){ //입력 확인 버튼 클릭시
        var enterdText = textInput.value;  // 모달창의 textInput에 입력된 value를 enterText에 저장

        const outputContainer = document.createElement('div'); //html에 div타입의 객채 생성

        outputContainer.id='uploadImageName';
        
        outputContainer.textContent=enterdText;
        //그리고 입력값이 저장된 enterdText를 주입함 
        
        btnOpenModal.parentNode.replaceChild(outputContainer,btnOpenModal);
        //다음  부모선언.자식과 바뀐다(자식,부모);
        
        // drawing_Number.value = enterdText; //enterText를 drawing_Number에 출력

        //입력값 초기화
        textInput.value = ' ' ;

        alert("도면 업로드 완료 " + enterdText); // 입력값 알림
        

        modal.style.display="none"; //그리고 닫힘

    });

    modal.addEventListener("click",function(event){ //모달창 바깥 클릭시 닫힘
        if (event.target === modal) {
            modal.style.display = "none";
        }


    });
});
