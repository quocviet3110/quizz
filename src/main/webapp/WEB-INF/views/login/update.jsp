<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>	
<c:url var="APIurl" value="/api/web/account"/>
<c:url var="APICustomerURL" value="/api/web/student"/>
<c:url var="SignupURL" value="/dang-ky"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Đăng ký</title>
</head>
<body>
	<div class="container">
		<!-- <h1 class="form-heading">login Form</h1> -->
		<div class="login-form">
			<div class="main-div">
				<c:if test="${not empty message}">
							<div class="alert alert-${alert}">${message}</div>
						</c:if>
						<div class="panel">
   					<h2>Chỉnh sửa tài khoản giáo viên</h2>
   					<br/>
   					</div>
			<form action="/ThiTracNghiem/dang-nhap/update"  id="formSignup" >
			
					 <div class="form-group">
						<input type="text" onblur="checkEmail();" class="form-control" id="email" name="email" placeholder="Email"/>
						<span style="color: red;" id="errorEmail"></span>
					</div> 
					<div class="form-group">
						<input type="text"  onblur="checkUsername();"  class="form-control"  id="username" name="username" placeholder="Tên đăng nhập"/>
						<span style="color: red;" id="errorUsername"></span>
					</div>
					
					<div class="form-group">
						<input type="password" onbler="checkPassword();" class="form-control" id="password" name="password" placeholder="Mật khẩu mới"/>
						<span style="color: red;" id="errorPassword"></span>
					</div>
					
					<div class="form-group">
						<input type="password" onblur="checkPass2();" class="form-control" id="xacnhanmatkau" name="xacnhanmatkau" placeholder="Nhập lại mật khẩu">
						<span style="color: red;" id="errorPass2"></span>
					</div> 
					<input type="hidden" value="${account.id}" id="id"  name="id"/>
					<input type="hidden" value="${account.idRole}" id="idRole" name="idRole"/>
					<input type="hidden" value="1" id="status" name="status"/>
					<input type="hidden" value="${teacher.email}" id="emailCheck" name="emailCheck"/>
					<input type="hidden" value="${account.username}" id="usernameCheck" name="usernameCheck"/>
					<div class="form-group">
						<div class="col-sm-12">
							<input type="button" class="btn btn-white btn-warning btn-bold" value="Chỉnh sửa tài khoản" id="btnAdd" />
						</div>
					</div>
					</form>
					
			</div>
		</div>
	</div>
	<script>
	function checkEmail() {
		var email = document.getElementById('email').value;
		var emailCheck = document.getElementById('emailCheck').value;
		
		var errorEmail = document.getElementById('errorEmail');
		var regexEmail = /^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$/;
		if(email == ''|| email==null){
			errorEmail.innerHTML="Email không được để trống!";
		}else if(!regexEmail.test(email)){
			errorEmail.innerHTML="Email không hợp lệ!";
		}else if(email != emailCheck){
			errorEmail.innerHTML="Email không trùng khớp với email đã đăng ký!";
		}else {
			errorEmail.innerHTML="";
			return email;
		}
	}
	function checkUsername(){
		var userName = document.getElementById('username').value;
		var userNameCheck = document.getElementById('usernameCheck').value;
		var errorUsername = document.getElementById('errorUsername');
		var regexUsername = /^(?=[a-zA-Z0-9._]{8,20}$)(?!.*[_.]{2})[^_.].*[^_.]$/;
		if(userName == ''|| userName==null){
			errorUsername.innerHTML="Tài khoản không được để trống!";
		}else if(!regexUsername.test(userName)){
			errorUsername.innerHTML="Tài khoản không hợp lệ!";
		}else if(userName != userNameCheck){
			errorUsername.innerHTML="Tài khoản không trùng khớp với tài khoản đã đăng ký!";
		}else {
			errorUsername.innerHTML="";
			return userName;
		}
	}
	function checkPassword() {
		var password = document.getElementById('password').value;
		var errorPassword = document.getElementById('errorPassword');
		var regexPassword = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{8,}$/;
		if(password == ''|| password==null){
			errorPassword.innerHTML="Mật khẩu không được để trống!";
		}else if(!regexPassword.test(password)){
			errorPassword.innerHTML="Mật khẩu không hợp lệ!(Mật khẩu gồm số chữ hoa và thường)";
		}else {
			errorPassword.innerHTML="";
			return password;
		}
	}
	function checkPass2(){
		var pass2 = document.getElementById('xacnhanmatkau').value;
		var errorPass2 = document.getElementById('errorPass2');
		var password = document.getElementById('password').value;
		if(pass2 == ''|| pass2 == null){
			errorPass2.innerHTML="Nhập lại mật khẩu không được để trống!";
		}else if(pass2 != password){
			errorPass2.innerHTML="Mật khẩu không trung khớp";
		}else {
			errorPass2.innerHTML="";
			return pass2;
		}
	}
	$('#btnAdd').click(function (e){
		e.preventDefault(); 
		 var data = {};
	     var formData = $('#formSignup').serializeArray();
	     $.each(formData, function (i, v) {
	            data[""+v.name+""] = v.value;   
	      }); 
	     var account={username:username.value,password:password.value,status:"1",idRole:idRole.value,id:id.value};
	    		 update(account);
	    		 console.log(account);
		});
	 function update(account){
	        $.ajax({
	            url: '${APIurl}',
	            type: 'PUT',
	            contentType: 'application/json',
	            data: JSON.stringify(account),
	            dataType: 'json',
	            success: function (acount) {
	           
	            },
	            error: function (error) {
	            }
	        });
 	}
	 </script>
</body>
</html>