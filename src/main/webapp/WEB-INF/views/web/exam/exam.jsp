<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.thitracnghiem.util.SecurityUtils" %>
<%@include file="/common/taglib.jsp"%>
<c:url var="resultAPI" value="/api/result" />
<c:url var="resultURL" value="web/exam/listQuestion" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đề thi</title>
</head>
<body>
	<div id="content">
	<div id="notify-toan" class="notify-section">
		<div class="content-section" id="question_1">
			<h2 class="section-heading text-white">Đề thi môn
				${exam.nameSubject} năm 2021</h2>
			<p class="section-sub-heading text-white">Bộ GT&ĐT - Mã đề: ${exam.codeExam}</p>
			<p class="section-sub-heading text-white">Thời gian thi: ${exam.time} phút</p>
			<!-- notify detail -->
			<div class="row places-list">
				<!-- Danh sách câu hỏi -->
				<div class="col col-two-third mt-16">

					<form action=" <c:url value='/web/exam/listQuestion'/>"
						method="get" >
						<c:forEach var="item" items="${questionDTO.listResult}"
							varStatus="count">
							<div class="place-body">
								<h3 class="place-heading">Câu ${count.count} :</h3>
								<p class="place-desc">${item.ques_content}</p>
								<div class="question-anwser-detail">
									<div class="question-item">
										<div class="radio-control">
											<input type="radio" class="custom-control-input"
												id="group${count.count}-1" name="cau${count.count}" value="A" onclick="hasChecked(${count.count-1})"> 
												<label for="group${count.count}-1" class="custom-control-label">${item.a_content}</label>
										</div>
									</div>
									<div class="question-item">
										<div class="radio-control">
											<input type="radio" class="custom-control-input"
												id="group${count.count}-2" name="cau${count.count}" value="B" onclick="hasChecked(${count.count-1})">
												 <label for="group${count.count}-2" class="custom-control-label">${item.b_content}</label>
										</div>
									</div>
									<div class="question-item">
										<div class="radio-control">
											<input type="radio" class="custom-control-input"
												id="group${count.count}-3" name="cau${count.count}" value="C" onclick="hasChecked(${count.count-1})"> 
												<label for="group${count.count}-3" class="custom-control-label">${item.c_content}</label>
										</div>
									</div>
									<div class="question-item" id="question_${count.count+1}">
										<div class="radio-control">
											<input type="radio" class="custom-control-input"
												id="group${count.count}-4" name="cau${count.count}" value="D" onclick="hasChecked(${count.count-1})"> 
												<label for="group${count.count}-4" class="custom-control-label">${item.d_content}</label>
										</div>
									</div>
								</div>
							</div>
							<input type="hidden" id="hidden${count.count}" value="${item.id}">
						</c:forEach>
						<input type="hidden" value="<%=SecurityUtils.getPrincipal().getUsername()%>" id="username" name="username" />
					</form>
				</div>
						<div class="col col-one-third mt-16">
							<div class="fixed-table">
								<div class="place-body">
									<h3 class="place-heading mb-4">Thời gian còn lại</h3>
									<div class="clock-block">
										<div class="clock-h" id="hourNum">00</div>
										<div class="clock-text">:</div>
										<div class="clock-m" id="minNum">00</div>
										<div class="clock-text">:</div>
										<div class="clock-s" id="secNum">00</div>
									</div>
									<!-- Bảng câu hỏi -->
									<h3 class="place-heading mb-4 mt-8">Các câu đã làm</h3>
									<div class="answer-block">
									<c:forEach var="item" items="${questionDTO.listResult}"
												varStatus="count">
											<div class="answer-item">
									<a href="#question_${count.count}"  class="js-check"> ${count.count}</a>
									</div>
									</c:forEach>
								</div>
									<button class="btn js-turn-into s-full-width my-color" id="turnInto">NỘP BÀI</button>
							</div>
						</div>
					</div>
						
		</div>
	</div>
</div>
				
	<!-- Tài liệu tham khảo -->
	</div>
	<script type="text/javascript">
      	const btnTurnInto = document.querySelector('.js-turn-into');
      	const arrChecks = document.querySelectorAll('.js-check');
        const arrAnswers = document.querySelectorAll('.answer-item');
        var lengthArrChecks = arrChecks.length;
        function hasChecked(i) {
            arrAnswers[i].classList.add('check');
        }
        var hour = 0;
        var min = ${exam.time};
        var sec = 5;
        var stopTime = false;

        function stopTimeRun() {
            stopTime = true;
        }

        btnTurnInto.addEventListener('click',stopTimeRun);

        function changeNum(num) {
            if (num < 10) {
                return '0' + num;
            } else {
                return num;
            }
        }

        function sleep(ms) {
            return new Promise(resolve => setTimeout(resolve, ms));
        }
        async function timeRunBack() {
            document.getElementById("hourNum").innerHTML = changeNum(hour);
            document.getElementById("minNum").innerHTML = changeNum(min);
            document.getElementById("secNum").innerHTML = changeNum(sec);
            while (hour != 0 || min != 0 || sec != 0) {
                await sleep(1000);
                if (sec == 0) {
                    sec = 59;
                    if (min == 0) {
                        hour--;
                        min = 59;
                        document.getElementById("hourNum").innerHTML = changeNum(hour);
                    } else {
                        min--;
                    }
                    document.getElementById("minNum").innerHTML = changeNum(min);
                } else if (sec < 60) {
                    sec--;
                }
                document.getElementById("secNum").innerHTML = changeNum(sec);
                if (stopTime == true) {
                    break;
                }
            }
        }
        timeRunBack();
        
        const turnInto = document.getElementById("turnInto");
        var cau;
        var idquestion;
        var list = [];
        var lenght=${questionDTO.listResult.size()};
        var ids=[];
        var count = 0;
       	var dsDapAn = ${trueAnswer};
        const arrQuestion = document.querySelectorAll('.question-item')
        var arrRadioBtn = document.querySelectorAll('.custom-control-input')
        
        function listAnwser() {
				  for (var i = 1; i < lenght+1; i++) {
					  	cau = 'cau'+i;
					  	idquestion ='hidden'+i;
		                soCau = 1;
		                const rbs = document.querySelectorAll('input[name="' + cau + '"]');
		                const rbs2 = document.querySelector('#'+idquestion+'','input[type="hidden"]');
		                ids.push(rbs2.value);
		                for (const rb of rbs) {
		                    if (rb.checked) {
		                        list.push(rb.value); 
		                        count = 0;
		                        break;
		                    }
		                    count++;
		                }
		                if (count != 0) {
		                    list.push('0');
		                    count = 0;
		                }
		            }
				   	var result={}
				   	var username = document.getElementById('username').value;
				   	var idExam =${exam.id};
				   	
				   	result['ids'] = ids;
				   	result['idExam']=idExam;
				   	result['answer']= list;
				   	result['username']=username;   
				   	
		           	 insertReSult(result); 
		           	turnInto.setAttribute("disabled", "disabled");
			  }  
        function indexTrueAnswer(count,i) {
            if (dsDapAn[i - 1] == 'A') {
                count = 0;
            } else if (dsDapAn[i - 1] == 'B') {
                count = 1;
            } else if (dsDapAn[i - 1] == 'C') {
                count = 2;
            } else {
                count = 3;
            }
            return count;
        }

        function changeColor() {
            var vitri;
             for (var i = 1; i < lenght+1; i++){
                vitri = (i - 1) * 4;
                cau = 'cau' + i;
                const rbs = document.querySelectorAll('input[name="' + cau + '"]');
                for (const rb of rbs) {
                    if (rb.checked) {
                        if (dsDapAn[i - 1] === list[i - 1]) {
                            arrQuestion[vitri + count].classList.add('colorAnswer');
                        } else {
                            arrQuestion[vitri + count].classList.add('colorWrongAnswer');
                            count = indexTrueAnswer(count,i);
                            arrQuestion[vitri + count].classList.add('colorAnswer');
                        }
                        count = 0;
                        break;
                    }
                    count++;
                }
                if (count != 0) {
                    count = indexTrueAnswer(count,i);
                    arrQuestion[vitri + count].classList.add('colorAnswer');
                    count = 0;
                }
            }
            var lengthRadioBtn = arrRadioBtn.length;
            for (var i = 0; i < lengthRadioBtn; i++) {
                arrRadioBtn[i].setAttribute("disabled", "disabled");
            }
        }

        turnInto.addEventListener('click',listAnwser);
		function insertReSult(result){
			$.ajax({
			url : '${resultAPI}',
			type : 'POST',
			contentType : 'application/json',
			data : JSON.stringify(result),
			dataType: 'json',
			success : function(markDTO) {
				var  mark = markDTO.mark;
				 swal({
					  title: "Kết quả",
					  text:"Bạn đã hoàn thành bài thi đạt được "+mark+" điểm",
					  type: "success",
					  showCancelButton: false,
					  confirmButtonClass: "btn-success",
					  confirmButtonText: "Xác nhận",
					},
					function(isConfirm) {
						  if (isConfirm) {
							  changeColor();
						  }
						});	
					},
					error : function(error) {
					}
					});
				}

    </script>
</body>
</html>