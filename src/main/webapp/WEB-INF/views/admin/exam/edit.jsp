<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/common/taglib.jsp"%>
<%@ page import="com.thitracnghiem.util.SecurityUtils" %>
<c:url var="examURL" value="/admin/exam/list" />
<c:url var="editExamURL" value="/admin/exam/update" />
<c:url var="examAPI" value="/api/exam" />
<c:url var="detailExamURL" value="/admin/detailExam/update" />
<html>
<head>
<title>Bài thi</title>
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

					<li><a href="#">Forms</a></li>
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
									class="col-sm-3 control-label no-padding-right">Môn học:</label>
								<div class="col-sm-9">
									<form:select path="nameSubject" id="nameSubject">
										<form:option value="" label="-- Chọn Môn Học --" />
											  <form:options items="${subjects}" itemValue="name" itemLabel="name" />
									</form:select>
								</div>
							</div>
							<c:if test="${not empty model.id}">
							<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right"
									for="form-field-1">Ngày thi:</label>
								<div class="col-sm-3">
									<form:input type="Date" path="convertDate"  onblur="checkDate();"
										cssClass="form-control" id="date" />
										<span style="color: red;" id="errorDate"></span>
									</div>
							</div>
							</c:if>
							<c:if test="${empty model.id}">
							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right"
									for="form-field-1">Ngày thi:</label>
										<div class="col-sm-3">
											<form:input type="Date" path="date"  onblur="checkDate();"
										cssClass="form-control" id="date" />
										<span style="color: red;" id="errorDate"></span>
									</div>
							</div>
							</c:if>
							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right"
									for="form-field-1">Thời gian thi(phút):</label>
								<div class="col-sm-3">
									<form:input type="number" path="time" onblur="checkTime();" cssClass="form-control" id="time" />
									<span style="color: red;" id="errorTime"></span>
								</div>
							</div>
							 <div class="form-group">
								<label class="col-sm-3 control-label no-padding-right"
									for="form-field-1">Mã đề thi:</label>
								<div class="col-sm-3">
									<form:input  path="codeExam" cssClass="form-control" id="codeExam" onblur="checkNum();"  />
									<span style="color: red;" id="errorNum"></span>
								</div>
							</div> 
						  <div class="form-group">
								<label 
									class="col-sm-3 control-label no-padding-right">Trạng thái:</label>
								<div class="col-sm-9">
									<form:select path="status" id="status">
										<form:option value="2" label="-- Trạng thái đề thi --" />
										<form:option value="1" label="Công khai" />
										<form:option value="0" label="Cá nhân" />
									</form:select>
								</div>
							</div> 
							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right"
									for="form-field-1">Số lượng câu hỏi:</label>
								<div class="col-sm-3">
									<form:input type="number" path="number" cssClass="form-control" id="number"  onblur="checkNumber();" />
									<span style="color: red;" id="errorNumber"></span>
								</div>
							</div>
							 <form:hidden path="id" id="idExam" />
							<form:hidden path="username" id="username" value="<%=SecurityUtils.getPrincipal().getFullname()%>" />
							<form:hidden path="idSubject" id="idSubject" />
							<div class="clearfix form-actions">
								<div class="col-md-offset-3 col-md-9">
									<c:if test="${empty model.id}">
										<button class="btn btn-info" type="button"
											id="btnAddOrUpdate">
											<i class="ace-icon fa fa-check bigger-110"></i> Thêm đề thi
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
	function checkDate() {
		var date = document.getElementById('date').value;
		var error = document.getElementById('errorDate');
		if(date == ''|| date==null){
			error.innerHTML="Vui lòng chọn thời gian thi!";
		}else {
			error.innerHTML="";
			return date;
		}
	}
	 function checkTime() {
		let time = document.getElementById('time').value;
		var error = document.getElementById('errorTime');
		if(time < 10 ){
			error.innerHTML="Thời gian của bài thi lớn hơn 10 phút!";
		}else {
			error.innerHTML="";
			return time;
		}
	}
	 function checkNum() {
		var num = document.getElementById('codeExam').value;
		var error = document.getElementById('errorNum');
		if(num == ''|| num==null){
			error.innerHTML="Mã đề thi không được để trống!";
		}else {
			error.innerHTML="";
			return num;
		}
	}
	 function checkNumber() {
			var number = document.getElementById('number').value;
			var error = document.getElementById('errorNumber');
			if(number == ''|| number==null){
				error.innerHTML="Số lượng câu hỏi không được để trống!";
			}else {
				error.innerHTML="";
				return number;
			}
		}
	
		$('#btnAddOrUpdate').click(function(e) {
			e.preventDefault();
			var data = {};
			var formData = $('#formSubmit').serializeArray();
			console.log(formData);
			$.each(formData, function(i, v) {
				data["" + v.name + ""] = v.value;
			});
			var date = $('#date').val();
			data['date']=date;
			var id = $('#idExam').val();
			
				addExam(data);
		
		});

		function addExam(data) {
			$.ajax({
				url : '${examAPI}',
				type : 'POST',
				contentType : 'application/json',
				data : JSON.stringify(data),
				dataType : 'json',
				success : function(result) {
					window.location.href = "${detailExamURL}?id="+ result.id;
				},
				error : function(error) {
					window.location.href = "${examURL}?page=1&limit=2&message=error_system";
				}
			});
		}
	</script>
</body>
</html>