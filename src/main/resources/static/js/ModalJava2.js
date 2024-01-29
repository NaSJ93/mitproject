
addEventListener("DOMContentLoaded",function(){ //문서가 불러와지면
    const modal = document.querySelector('.modal'); //modal 선언
    const btnOpenModal=document.querySelector('#btn_open_modal');//열기 버튼 선언
    const btnCloseModal=document.querySelector('.btn_close_modal');//닫기 버튼 선언
    const btnConfirm = document.querySelector('.btn_confirm');
    var textInput = document.getElementById('textInput');
    var printText = document.getElementById('printText');


    modal.style.display="none";

 

    btnOpenModal.addEventListener("click",function(e){ //열기버튼 클릭시
        e.preventDefault();
        modal.style.display="flex";
    });
  

    btnCloseModal.addEventListener("click",function(){ //닫기버튼 클릭시
        modal.style.display="none";
    });

    btnConfirm.addEventListener("click",function(e){ //입력 확인 버튼 클릭시
        e.preventDefault();
        /*var enterdText = textInput.value;  // 모달창의 textInput에 입력된 value를 enterText에 저장

        const outputContainer = document.createElement('div'); //html에 div타입의 객채 생성
        //이름을 outputContainer로 명명
        
        outputContainer.textContent=enterdText;
        //그리고 입력값이 저장된 enterdText를 주입함 
        
        btnOpenModal.parentNode.replaceChild(outputContainer,btnOpenModal);
        //다음  부모선언.자식과 바뀐다(자식,부모);
        
        // printText.value = enterdText; //enterText를 printText에 출력

        //입력값 초기화
        textInput.value = ' ' ;

        alert("메일 발송 완료 " + enterdText); // 입력값 알림
        */

                    var formData = new FormData();
                    formData.append("email", document.getElementById("email").value);
                    formData.append("managerEmail", document.getElementById("managerEmail").value);
                    formData.append("password", document.getElementById("password").value);

                    formData.append("attachment", document.getElementById("attachment").files[0]); // 파일 추가
                    formData.append("code", document.getElementById("code").value);
                    formData.append("type", document.getElementById("type").value);

                    fetch("/gogogo", {
                        method: "POST",
                        body: formData
                    })
                    .then(response => {
                        console.log(response);
                        // 여기에 추가적인 처리를 할 수 있습니다.
                    })
                    .catch(error => {
                        console.error("Error:", error);
                    });

                    alert("메일 발송 완료");


    });

    modal.addEventListener("click",function(event){ //모달창 바깥 클릭시 닫힘
        if (event.target === modal) {
            modal.style.display = "none";
        }
    });


});
