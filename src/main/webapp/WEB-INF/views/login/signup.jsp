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
   					<h2>Đăng ký</h2>
   					<br/>
   					</div>
			<form:form action="/ThiTracNghiem/dang-ky"  modelAttribute="student" id="formSignup" >
					<div class="form-group">
						<form:input  onblur="checkName();" class="form-control" id="fullname" path="name" placeholder="Họ và tên khách hàng"/>
						<span style="color: red;" id="errorName"></span>
					</div>
					<div class="form-group">
						<form:input type="text" onblur="checkPhone();"  class="form-control" id="phone" path="phone" placeholder="Số điện thoại" />
						<span style="color: red;" id="errorPhone"></span>
					</div>
					<div>
						<label>Giới tính:</label>
						<form:radiobutton  path="gender" value="Nam" label="Nam"/>
						<form:radiobutton path="gender" value="Nữ" label="Nữ"/>
						<form:radiobutton path="gender" value="Khác" label="Khác"/>
					</div>
					<div class="form-group">
						<form:input type="text" onblur="checkEmail();" class="form-control" id="email" path="email" placeholder="Email"/>
						<span style="color: red;" id="errorEmail"></span>
					</div>
					<div class="form-group">
						<form:input type="text" onblur="checkAddress();" class="form-control" id="address"  path="address" placeholder="Địa chỉ"/>
						<span style="color: red;" id="errorAddress"></span>
					</div>
					<div class="form-group">
						<form:input type="text"  onblur="checkUsername();"  class="form-control"  id="username" path="${account.username}" placeholder="Tên đăng nhập"/>
						<span style="color: red;" id="errorUsername"></span>
					</div>
					
					<div class="form-group">
						<form:input type="password" onbler="checkPassword();" class="form-control" id="password" path="${account.password}" placeholder="Mật khẩu"/>
						<span style="color: red;" id="errorPassword"></span>
					</div>
					
					<form:input type="hidden" value="" id="id"  path="id"/>
					<form:input type="hidden" value="1" id="idRole" path="${account.idRole}"/>
					<form:input type="hidden" value="1" id="status" path="${account.status}"/>
					</form:form>
					
					<div class="form-group">
						<input type="password" onblur="checkPass2();" class="form-control" id="xacnhanmatkau" name="xacnhanmatkau" placeholder="Nhập lại mật khẩu">
						<span style="color: red;" id="errorPass2"></span>
					</div> 
					<div class="form-group">
						<div class="col-sm-12">
							<input type="button" class="btn btn-white btn-warning btn-bold" value="Đăng ký" id="btnAdd" />
						</div>
					</div>
			</div>
		</div>
	</div>
	<script>
	function checkName() {
		var name = document.getElementById('fullname').value;
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
	function checkEmail() {
		var email = document.getElementById('email').value;
		var errorEmail = document.getElementById('errorEmail');
		var regexEmail = /^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$/;
		if(email == ''|| email==null){
			errorEmail.innerHTML="Email không được để trống!";
		}else if(!regexEmail.test(email)){
			errorEmail.innerHTML="Email không hợp lệ!";
		}else {
			errorEmail.innerHTML="";
			return email;
		}
	}
	function checkPhone() {
		var phone = document.getElementById('phone').value;
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
		var address = document.getElementById('address').value;
		var error = document.getElementById('errorAddress');
		if(address == ''|| address==null){
			error.innerHTML="Địa chỉ không được để trống!";
		}else {
			error.innerHTML="";
			return address;
		}
	}
	function checkUsername(){
		var userName = document.getElementById('username').value;
		var errorUsername = document.getElementById('errorUsername');
		var regexUsername = /^(?=[a-zA-Z0-9._]{8,20}$)(?!.*[_.]{2})[^_.].*[^_.]$/;
		if(userName == ''|| userName==null){
			errorUsername.innerHTML="Tài khoản không được để trống!";
		}else if(!regexUsername.test(userName)){
			errorUsername.innerHTML="Tài khoản không hợp lệ!";
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
	     console.log(data);
	     var account={username:username.value,password:password.value,status:"1",idRole:idRole.value,id:""};
	     addUser(account);
		});
	 function addUser(account){
	        $.ajax({
	            url: '${APIurl}',
	            type: 'POST',
	            contentType: 'application/json',
	            data: JSON.stringify(account),
	            dataType: 'json',
	            success: function (acount) {
	             var student={username:username.value,phone:phone.value,email:email.value,address:address.value,id:"",gender:"Nữ",name:fullname.value}
	                addCustomer(student)
	            },
	            error: function (error) {
	            }
	        });
    	}
	 function addCustomer(student) {
	        $.ajax({
	            url: '${APICustomerURL}',
	            type: 'POST',
	            contentType: 'application/json',
	            data: JSON.stringify(student),
	            dataType: 'json',
	            success: function (khachhang) {
	            	console.log(khachhang);
	            	window.location.href = "${SignupURL}?&message=signup_success";
	            },
	            error: function (error) {
	            	window.location.href = "${SignupURL}?&message=error_system";
	            }
	        });
	    }
	 </script>
</body>
</html>