

    document.getElementById('myButton').addEventListener('click',function(){
            alert('버튼이 클릭되었습니다.')
            });
        
    
    //클릭 이벤트에 대한 핸들러 함수 정의
    function handleClick(myCallback){
        //클릭시 경고창 띄움
        alert('콜백클릭 버튼 누르심')
        //전달받은 콜백 함수를 실행합니다.
        myCallback();
    
    };
        //콜백 함수를 정의
        function myCallback(){
            //콜백함수 실행시 콘솔에 메시지 출력함
            console.log( '콜백 함수가 실행되었습니다.')
        }
    //동적요소 div
        //html의 myDiv를 찾아서 myDiv를 선언
        const myDiv = document.getElementById('myDiv');

        function ontheDiv(onDivCallback){
            onDivCallback()
        }
            function onDivCallback(){
            //myDiv영역에 마우스 올리면 움직임
                //선택한 요소의 텍스트 내용을 변경
            myDiv.textContent='요소가 변경되었습니다!'
    }
    //동적으로 요소 추가

    /* div 요소를 생성하고 ID를 부여함 */
        //새로운  p요소 생성하기 document.creatElement('요소 이름')
        const newElement = document.createElement('p');

        //새로운 p요소의 텍스트 내용을 지정

        newElement.textContent = '새로운내용입니다.';

        //기존 요소에 새로운  p요소를 추가합니다.
        document.getElementById('container').appendChild(newElement);

    
        //버튼 클릭시 테이블의 하단에 한줄 추가
        const myTable = document.getElementById('table-body')
        
        function Table_Btn_Create_Click(Table_Create_Row){
            alert("테이블 1줄 생성 클릭확인")
            Table_Create_Row()

        }

            function Table_Create_Row(){
                // 새로운 행 추가 대상테이블바디.insertRow(-1)
                let row = myTable.insertRow(-1)

                let cell1 = row.insertCell(0);
                    cell1.innerHTML = '<input type="checkbox">'; //생성시 체크박스 구현되게
                let cell2 = row.insertCell(1);
                let cell3 = row.insertCell(2);
                let cell4 = row.insertCell(3);
            }

    
    //테이블 관련

        // 15개의 샘플 데이터 생성
        var sampleData = [
            { name: "홍길동", gender: "남성", phoneNumber: "010-1234-5678" },
            { name: "김영희", gender: "여성", phoneNumber: "010-9876-5432" },
            { name: "이철수", gender: "남성", phoneNumber: "010-5555-5555" },
            { name: "박미영", gender: "여성", phoneNumber: "010-9999-9999" },
            { name: "신지수", gender: "여성", phoneNumber: "010-1111-2222" },
            { name: "김철호", gender: "남성", phoneNumber: "010-7777-8888" },
            { name: "이미래", gender: "여성", phoneNumber: "010-3333-4444" },
            { name: "장영식", gender: "남성", phoneNumber: "010-6666-7777" },
            { name: "최지영", gender: "여성", phoneNumber: "010-8888-9999" },
            { name: "강도현", gender: "남성", phoneNumber: "010-1234-5678" },
            { name: "한아름", gender: "여성", phoneNumber: "010-9876-5432" },
            { name: "박승현", gender: "남성", phoneNumber: "010-5555-5555" },
            { name: "윤서율", gender: "여성", phoneNumber: "010-9999-9999" },
            { name: "손민수", gender: "남성", phoneNumber: "010-1111-2222" },
            { name: "김하늘", gender: "여성", phoneNumber: "010-7777-8888" },
        ];

        // 테이블에 데이터 추가
        var tableBody = document.getElementById("table-body");
        sampleData.forEach(function (item) {
            var row = tableBody.insertRow(-1);
            var cell1 = row.insertCell(0);
            var cell2 = row.insertCell(1);
            var cell3 = row.insertCell(2);
            var cell4 = row.insertCell(3);
        //테이블의 속성
            cell1.innerHTML = '<input type="checkbox">';
            cell2.textContent = item.name;
            cell3.textContent = item.gender;
            cell4.textContent = item.phoneNumber;
        });

        


