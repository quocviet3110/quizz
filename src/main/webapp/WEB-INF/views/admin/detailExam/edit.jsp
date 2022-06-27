<%@include file="/common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<c:url var="detailExamAPI" value="/api/detailExam" />
<c:url var="detailExamURL" value="/admin/detailExam/update" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Danh sách bài viết</title>
</head>
<body>
	<div class="main-content">
		<form action="<c:url value='/admin/detailExam/update?id=${exam.id}&keyword=${keyword}'/>"
			id="formSubmit" method="get">

			<div class="main-content-inner">
				<div class="breadcrumbs ace-save-state" id="breadcrumbs">
					<ul class="breadcrumb">
						<li><i class="ace-icon fa fa-home home-icon"></i> <a href="#">Trang
								chủ</a></li>
					</ul>
					<!-- /.breadcrumb -->
				</div>
				<div class="page-content">
					<div class="row">
						<div class="col-xs-12">
							<c:if test="${not empty message}">
								<div class="alert alert-${alert}">${message}</div>
							</c:if>
							<div class="widget-box table-filter">
								<div class="table-btn-controls">
									<div class="pull-right tableTools-container">
									
									</div>
								</div>
							</div>
							Filter:
								<input type="text" name="keyword" id="keyword" size="50" value="${keyword}" required /> &nbsp;
								 <input type="button" value="Search" id="search" onclick="Search()" /> &nbsp; 
								 <input type="button" value="Clear" id="btnClear" onclick="clearSearch()" />
							 <br/>
							 <br />
							 <button class="btn btn-info" type="button"
											id="btnAdd">
											<i class="ace-icon fa fa-check bigger-110"></i> Thêm dach sách câu hỏi vào đề thi
										</button>
							<br/>
							<br/>
							<div>Số lương câu hỏi: <span id="qt-tick">0/${exam.number}</span></div>
							 <br/>
							 <br />
							<div class="row">
								<div class="col-xs-12">
									<div class="table-responsive">
										<table class="table table-bordered">
											<thead>
												<tr>
													<th><input type="checkbox" id="checkAll"></th>
													<th>Id</th>
													<th>Câu hỏi</th>
													<th>Đáp án A</th>
													<th>Đáp án B</th>
													<th>Đáp án C</th>
													<th>Đáp án D</th>
													<th>Đáp án</th>
													<th>Mức độ</th>
													<th>Môn học</th>
													<th>Giáo viên</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach var="item" items="${model.listResult}">
													<tr>
														<td><input type="checkbox" id="checkbox_${item.id}"
															value="${item.id}" onclick="totalQuestion()"></td>
														<td>${item.id}</td>
														<td>${item.ques_content}</td>
														<td>${item.a_content}</td>
														<td>${item.b_content}</td>
														<td>${item.c_content}</td>
														<td>${item.d_content}</td>
														<td>${item.answer}</td>
														<td>${item.lever}</td>
														<td>${item.nameSubject}</td>
														<td>${item.nameTeacher}</td>
												</c:forEach>
											</tbody>
										</table>
										<input type="hidden" value="${exam.id}" id="id" name="id" />
										
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</form>
	</div>
	<!-- /.main-content -->
	<script>
		function Search() {
			var keyword = document.getElementById('keyword').value;
			window.location.href = "/ThiTracNghiem/admin/detailExam/update?id=${exam.id}&keyword="+ keyword;
		}
		function clearSearch() {
			window.location.href = "/ThiTracNghiem/admin/detailExam/update?id=${exam.id}";
		}
		var arrQtCheckbox = document.querySelectorAll('input[type="checkbox"]');
        var len = arrQtCheckbox.length;
        var number;
        var numberExam = ${exam.number};
        var flag = false;
        
        function totalQuestion() {
            number = 0;
            for (var i = 0;i < len;i++) {
                if (arrQtCheckbox[i].checked) {
                    number++;
                    document.getElementById('qt-tick').innerHTML = number+"/"+numberExam;
                }
            }
            if (number == 0) {
                document.getElementById('qt-tick').innerHTML = number +"/"+numberExam;
            }
            validateNumber();
        }

        function validateNumber() {
            if (number == numberExam) {
                for (var i = 0;i < len;i++) {
                    flag = true;
                    if (arrQtCheckbox[i].checked == false) {
                        arrQtCheckbox[i].setAttribute("disabled","disabled");
                    }
                }
            } else if (flag == true){
                flag = false;
                for (var i = 0;i < len;i++) {
                    if (arrQtCheckbox[i].disabled) {
                        arrQtCheckbox[i].removeAttribute("disabled","disabled");
                    }
                }
            }
        }
		
		$("#btnAdd").click(function() {
			var data = {};
			var ids = $('tbody input[type=checkbox]:checked').map(function() {
				return $(this).val();
			}).get();
			data['ids'] = ids;
			var id = $('#id').val();
			data['idExam'] = id;
			insert(data);
		});
		function insert(data) {
			$.ajax({
				url : '${detailExamAPI}',
				type : 'POST',
				contentType : 'application/json',
				data : JSON.stringify(data),
				success : function(result) {
					window.location.href = "${detailExamURL}?id=${exam.id}&message=insert_success";
				},
				error : function(error) {
					window.location.href = "${detailExamURL}?id=2";
					}
					});
		}
	</script>
</body>
</html>