x<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/common/taglib.jsp"%>
<%@ page import="com.thitracnghiem.util.SecurityUtils" %>
<c:url var="questionURL" value="/admin/question/list" />
<c:url var="editQuestionURL" value="/admin/questions/update" />
<c:url var="questionAPI" value="/api/question" />
<html>
<head>
<title>Chỉnh sửa câu hỏi</title>
</head>
<body>
	<div class="main-content">
		<div class="main-content-inner">
			<div class="breadcrumbs" id="breadcrumbs">
				<script type="text/javascript">
					try {
						ace.settings.check('breadcrumbs', 'fixed')
					} catch (e) {
					}
				</script>

				<ul class="breadcrumb">
					<li><i class="ace-icon fa fa-home home-icon"></i> <a href="#">Home</a>
					</li>

					<li><a href="/ThiTracNghiem/admin/questions/update">Forms</a></li>
					<li class="active">Form Elements</li>
				</ul>
				<!-- /.breadcrumb -->
			</div>
			<div class="page-content">
				<div class="row">
					<div class="col-xs-12">
						<c:if test="${not empty message}">
							<div class="alert alert-${alert}">${message}</div>
						</c:if>
						<form:form class="form-horizontal" role="form" id="formSubmit"
							modelAttribute="model">
							<div class="form-group">
								<label for="nameSubject"
									class="col-sm-3 control-label no-padding-right">Môn
									học:</label>
								<div class="col-sm-9">
									<form:select path="nameSubject" id="nameSubject">
										<form:option value="" label="-- Chọn Môn Học --" />
											  <form:options items="${subjects}" itemValue="name" itemLabel="name" />
									</form:select>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right"
									for="form-field-1">Nội dung câu hỏi:</label>
								<div class="col-sm-9">
									<form:textarea path="ques_content" rows="2" cols="5" onblur="checkQuestion();"
										cssClass="form-control" id="ques_content" />
										<span style="color: red;" id="errorQuestion"></span>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right"
									for="form-field-1">Nội dung đáp án A:</label>
								<div class="col-sm-9">
									<form:textarea path="a_content" rows="2" cols="10" onblur="checkA();"
										cssClass="form-control" id="a_content" />
										<span style="color: red;" id="errorA"></span>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right"
									for="form-field-1">Nội dung đáp án B:</label>
								<div class="col-sm-9">
									<form:textarea path="b_content" rows="2" cols="10" onblur="checkB();"
										cssClass="form-control" id="b_content" />
										<span style="color: red;" id="errorB"></span>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right"
									for="form-field-1">Nội dung đáp án C:</label>
								<div class="col-sm-9">
									<form:textarea path="c_content" rows="2" cols="10" onblur="checkC();"
										cssClass="form-control" id="c_content" />
										<span style="color: red;" id="errorC"></span>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right"
									for="form-field-1">Nội dung đáp án D:</label>
								<div class="col-sm-9">
									<form:textarea path="d_content" rows="2" cols="10" onblur="checkD();"
										cssClass="form-control" id="d_content" />
										<span style="color: red;" id="errorD"></span>
								</div>
							</div>
							<div class="form-group">
								<label for="answer"
									class="col-sm-3 control-label no-padding-right">Đáp án:</label>
								<div class="col-sm-9">
									<form:select path="answer" id="answer">
										<form:option value="" label="-- Chọn Đáp Án --" />
										<form:option value="A" label="A" />
										<form:option value="B" label="B" />
										<form:option value="C" label="C" />
										<form:option value="D" label="D" />
									</form:select>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right">Mức độ:</label>
								<div class="col-sm-9">
									<form:select path="lever" id="level">
										<form:option value="" label="-- Chọn Mức Độ  --" />
										<form:option value="DE" label="Dễ" />
										<form:option value="KHA" label="Khá" />
										<form:option value="TB" label="Trung bình" />
										<form:option value="KHO" label="Khó" />
									</form:select>
								</div>
							</div>
							<form:hidden path="id" id="questionId" />
							<form:hidden path="nameTeacher" id="nameTeacher" value="<%=SecurityUtils.getPrincipal().getFullname()%>" />
							<form:hidden path="idSubject" id="idSubject" />
							<div class="clearfix form-actions">
								<div class="col-md-offset-3 col-md-9">
									<c:if test="${not empty model.id}">
										<button class="btn btn-info" type="button"
											id="btnAddOrUpdateQuestion">
											<i class="ace-icon fa fa-check bigger-110"></i> Cập nhật câu hỏi
										</button>
									</c:if>
									<c:if test="${empty model.id}">
										<button class="btn btn-info" type="button"
											id="btnAddOrUpdateQuestion">
											<i class="ace-icon fa fa-check bigger-110"></i> Thêm câu hỏi
										</button>
									</c:if>

									&nbsp; &nbsp; &nbsp;
									<button class="btn" type="reset">
										<i class="ace-icon fa fa-undo bigger-110"></i> Hủy
									</button>
								</div>
							</div>
						</form:form>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script>
		/*   var editor = '';
		$(document).ready(function() {
			editor = CKEDITOR.replace('ques_content');
		});  */
		function checkQuestion() {
			var ques = document.getElementById('ques_content').value;
			var error = document.getElementById('errorQuestion');
			if(ques == ''|| ques==null){
				error.innerHTML="Câu hỏi không được để trống!";
			}else {
				error.innerHTML="";
				return ques;
			}
		}
		 function checkA() {
			var a = document.getElementById('a_content').value;
			var error = document.getElementById('errorA');
			if(a == ''|| a==null){
				error.innerHTML="Đáp án A không được để trống!";
			}else {
				error.innerHTML="";
				return a;
			}
		}
		 function checkB() {
			var b = document.getElementById('b_content').value;
			var error = document.getElementById('errorB');
			if(b == ''|| b==null){
				error.innerHTML="Đáp án B không được để trống!";
			}else {
				error.innerHTML="";
				return b;
			}
		}
		function checkC() {
			var c = document.getElementById('c_content').value;
			var error = document.getElementById('errorC');
			if(c == ''|| c==null){
				error.innerHTML="Đáp án C không được để trống!";
			}else {
				error.innerHTML="";
				return c;
			}
		}
		function checkD() {
			var d = document.getElementById('d_content').value;
			var error = document.getElementById('errorD');
			if(d == ''|| d==null){
				error.innerHTML="Đáp án D không được để trống!";
			}else {
				error.innerHTML="";
				return d;
			}
		} 
		$('#btnAddOrUpdateQuestion').click(function(e) {
			e.preventDefault();
			if(checkQuestion() && checkA() && checkC() && checkB() && checkD()){
			var data = {};
			var formData = $('#formSubmit').serializeArray();
			console.log(formData);
			$.each(formData, function(i, v) {
				data["" + v.name + ""] = v.value;
			});
			/*  data['ques_content']= editor.getData(); */
			var id = $('#questionId').val();
			if (id == "") {
				addQuestion(data);
			} else {
				updateQuestion(data);
			}
			}else{
				alert("Vui lòng kiểm tra lại thông tin nhập!!!");
			}
		});

		function addQuestion(data) {
			$.ajax({
				url : '${questionAPI}',
				type : 'POST',
				contentType : 'application/json',
				data : JSON.stringify(data),
				dataType : 'json',
				success : function(result) {
					window.location.href = "${editQuestionURL}?id="+ result.id + "&message=insert_success";
				},
				error : function(error) {
					window.location.href = "${questionURL}?page=1&limit=6&message=error_system";
				}
			});
		}

		function updateQuestion(data) {
			$.ajax({
				url : '${questionAPI}',
				type : 'PUT',
				contentType : 'application/json',
				data : JSON.stringify(data),
				dataType : 'json',
				success : function(result) {
					window.location.href = "${editQuestionURL}?id=" + result.id
							+ "&message=update_success";
				},
				error : function(error) {
					window.location.href = "${editQuestionURL}?id=" + result.id
							+ "&message=error_system";
				}
			});
		}
	</script>
</body>
</html>