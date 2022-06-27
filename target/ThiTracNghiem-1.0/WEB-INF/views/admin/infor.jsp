<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<%@ page import="com.thitracnghiem.util.SecurityUtils" %>
<c:url var="editQuestionURL" value="/admin-inforTeacher" />
<c:url var="teacherAPI" value="/api/teacher" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Thông tin cá nhân</title>
</head>
<body>
<div class="main-content">
		<div class="main-content-inner">
				<div class="page-content">
				
							<h1>Thông tin cá nhân</h1>
			<form:form action="<c:if value='/admin-inforTeacher'/>"  enctype="multipart/form-data"  id="formSubmit" modelAttribute="model">
						<c:if test="${not empty message}">
							<div class="alert alert-${alert}">${message}</div>
						</c:if>
                        <div class="row">
                            <div class="col-md-3">
                                    <div class="form-group">
                                        <label for="name">Họ và tên</label>
                                       <form:input type="text" onblur="checkName()" class="form-control" id="hovaten" path="name" value="${model.name}" />
										<span style="color: red;" id="errorName"></span>
                                    </div>
                                    <div class="form-group">
                                        <label for="phone">SĐT:</label>
                                        <form:input type="text" onblur="checkPhone();" class="form-control" id="sdt" path="phone" value="${model.phone}" />
										<span style="color: red;" id="errorPhone"></span>
                                    </div>
                                    <div class="form-group">
                                        <label for="phone">Địa chỉ:</label>
                                        <form:input  onblur="checkAddress();" class="form-control" id="diachi" path="address" value="${model.address}" />
										<span style="color: red;" id="errorAddress"></span>
                                    </div>
                                    </div>
                             
                            <div class="col-md-3">
                                    <div class="form-group">
                                      <label for="phone">Học vị:</label>
                                      <form:input class="form-control" id="hocvi" path="degree" value="${model.degree}" />
										<span style="color: red;" id="errorAddress"></span>
                                    </div>
                                    <div class="form-group">
                                        <label for="email">Email:</label>
                                        <form:input type="email"  class="form-control" id="email" path="email" value="${model.email}" />
										<span style="color: red;" id="errorAddress"></span>
                                    </div>
                                    <div class="form-group">
                                        <label for="email">Ảnh:</label>
                                        <form:input type="file"  class="form-control" id="avatar" path="avatar" />
                                    </div>
                            </div>
                       </div>
                        <div class="row">
                            <button class="btn btn-info" type="button" id="btnUpdate">
								<i class="ace-icon fa fa-check bigger-110"></i> Cập nhật thông tin
							</button>
							 <a class="btn btn-info" href="<c:url value='/dang-nhap/updateAccount?id=${model.id}'/>" >
								<i class="ace-icon fa fa-check bigger-110"></i> Đổi mật khẩu
							</a>
                            </div>
							<form:input type="hidden" value="<%=SecurityUtils.getPrincipal().getFullname()%>" id="username" path="username" />
							<form:input type="hidden" value="${model.id}" id="id" path="id" />
					</form:form>
					
				</div>			
			</div>
			</div>
		<script type="text/javascript">
		function checkName() {
		var name = document.getElementById('hovaten').value;
		var errorName = document.getElementById('errorName');
		var regexName = /^[^\d+]*[\d+]{0}[^\d+]*$/;
		if(name == ''|| name==null){
			errorName.innerHTML="Họ tên không được để trống!";
		}else if(!regexName.test(name)){
			errorName.innerHTML="Họ tên không hợp lệ!";
		}else {
			errorName.innerHTML="";
			return name;
		}
		}
		function checkPhone() {
			var phone = document.getElementById('sdt').value;
			var errorPhone = document.getElementById('errorPhone');
			var regexPhone = /(84|0[1|5|7|8|9])+([0-9]{8})\b/;
			if(phone == ''|| phone==null){
				errorPhone.innerHTML="SĐT không được để trống!";
			}else if(!regexPhone.test(phone)){
				errorPhone.innerHTML="SĐT không hợp lệ!";
			}else {
				errorPhone.innerHTML="";
				return phone;
			}
		}
	function checkAddress() {
		var address = document.getElementById('diachi').value;
		var error = document.getElementById('errorAddress');
		if(address == ''|| address==null){
			error.innerHTML="Địa chỉ không được để trống!";
		}else {
			error.innerHTML="";
			return address;
		}
	}
	$('#btnUpdate').click(function(e) {
		e.preventDefault();
		var data = {};
		var formData = $('#formSubmit').serializeArray();
		console.log(formData);
		$.each(formData, function(i, v) {
			data["" + v.name + ""] = v.value;
		});
			
			update(data);
	});

	function update(data) {
		$.ajax({
			url : '${teacherAPI}',
			type : 'PUT',
			contentType : 'application/json',
			data : JSON.stringify(data),
			dataType : 'json',
			success : function(result) {
				window.location.href = "${editQuestionURL}?message=update_success";
			},
			error : function(error) {
				window.location.href = "${editQuestionURL}";
			}
		});
	}
	</script>
</body>
	
</html>