<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Đăng nhập</title>
</head>
<body>
	<div class="container">
		<!-- <h1 class="form-heading">login Form</h1> -->
		<div class="login-form">
			<div class="main-div">
				<c:if test="${param.incorrectAccount != null}">
					<div class="alert alert-danger">	
							Username or password incorrect
					</div>
				</c:if>
				<c:if test="${param.accessDenied != null}">
					<div class="alert alert-danger">	
							you Not authorize
					</div>
				</c:if>
				 <div class="panel">
   					<h2>Đăng nhập</h2>
   					<br/>
   					</div>
				<form action="j_spring_security_check" id="formLogin" method="post">
					<div class="form-group">
						<input type="text" class="form-control" id="username" name="j_username"
							placeholder="Tên đăng nhập">
					</div>

					<div class="form-group">
						<input type="password" class="form-control" id="password" name="j_password"
							placeholder="Mật khẩu">
					</div>
					<div>
					<p class="modal-help">Nếu bạn chưa có tài khoản? <a  href="<c:url value='/dang-ky'/>" class="register js-register">Đăng ký
                        ngay</a></p>
					<button type="submit" class="btn btn-primary" >Đăng nhập</button>
				</form>
				  
			</div>
		</div>
	</div>
</body>
</html>