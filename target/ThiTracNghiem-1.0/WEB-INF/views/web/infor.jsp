<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<c:url var="studentAPI" value="/api/student" />
<c:url var="studentURL" value="/web/infor" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Thông tin khách hàng</title>
</head>
<body>
	<div id="content">
		<!-- Contact -->
		<div id="personal-info">
			<div class="content-section">
				<h2 class="section-heading text-white">THÔNG TIN CÁ NHÂN</h2>

				<form:form action="<c:if value='/web/infor'/>" id="formSubmit"
					modelAttribute="student">
					<c:if test="${not empty message}">
						<div class="alert alert-${alert}">${message}</div>
					</c:if>
					<div class="col col-half contact-info">
						<div class="ml-8 text-bold">Họ và tên:</div>
						<div>
							<form:input type="text" onblur="checkName()" class="info"
								id="hoten" path="name" value="${student.name}" />
							<span style="color: red;" id="errorName"></span>
						</div>
					</div>
					<div class="col col-half contact-info">
						<div class="ml-8 text-bold">Số điện thoại:</div>
						<div>
							<form:input type="text" class="info" onblur="checkPhone();"
								id="sdt" path="phone" value="${student.phone}" />
							<span style="color: red;" id="errorPhone"></span>
						</div>
					</div>
					<div class="col col-half contact-info">
						<div class="ml-8 text-bold">Email:</div>
						<div>
							<form:input type="email" onblur="checkEmail();" class="info"
								id="email" path="email" value="${student.email}" />
							<span style="color: red;" id="errorEmail"></span>
						</div>
					</div>
					<div class="col col-half contact-info">
						<div class="ml-8 text-bold">Địa chỉ:</div>
						<div>
							<form:input type="text" onblur="checkAddress();" class="info"
								id="diachi" path="address" value="${student.address}" />
							<span style="color: red;" id="errorAddress"></span>
						</div>
					</div>
					<input type="button" class="btn btn-white btn-warning btn-bold"
						value="Cập nhật thông tin" id="btnUpdate" />
					<input type="hidden" value="${student.id}" id="id" name="id" />
				<br/>
				<br/>
				<div id="mark-table">
					<div class="content-section">
						<h2 class="section-heading text-white">BẢNG ĐIỂM</h2>

						<div class="row contact-content">
							<div class="col col-full contact-info">
								<table class="table-i">
									<thead>
											<tr>
												<th>Id</th>
													<th>Họ và tên</th>
													<th>Bài thi</th>
													<th>Mã đề</th>													
													<th>Điểm</th>
													<th>Ngày thi</th>
													<th>Môn thi</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach var="item" items="${mark}">
													<tr>
														<td>${item.id}</td>
														<td>${item.studentMark.name}</td>
														<td>${item.examMark.id}</td>
														<td>${item.examMark.codeExam}</td>
														<td>${item.mark}</td>
														<td>${item.convertDate}</td>
														<td>${item.nameSubject}</td>
													</tr>
												</c:forEach>
											</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
				</form:form>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		function checkName() {
			var name = document.getElementById('hoten').value;
			var errorName = document.getElementById('errorName');
			var regexName = /^[^\d+]*[\d+]{0}[^\d+]*$/;
			if (name == '' || name == null) {
				errorName.innerHTML = "Họ tên không được để trống!";
			} else if (!regexName.test(name)) {
				errorName.innerHTML = "Họ tên không hợp lệ!";
			} else {
				errorName.innerHTML = "";
				return name;
			}
		}
		function checkEmail() {
			var email = document.getElementById('email').value;
			var errorEmail = document.getElementById('errorEmail');
			var regexEmail = /^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$/;
			if (email == '' || email == null) {
				errorEmail.innerHTML = "Email không được để trống!";
			} else if (!regexEmail.test(email)) {
				errorEmail.innerHTML = "Email không hợp lệ!";
			} else {
				errorEmail.innerHTML = "";
				return email;
			}
		}
		function checkAddress() {
			var address = document.getElementById('diachi').value;
			var error = document.getElementById('errorAddress');
			if (address == '' || address == null) {
				error.innerHTML = "Địa chỉ không được để trống!";
			} else {
				error.innerHTML = "";
				return address;
			}
		}
		function checkPhone() {
			var phone = document.getElementById('sdt').value;
			var errorPhone = document.getElementById('errorPhone');
			var regexPhone = /(84|0[1|5|7|8|9])+([0-9]{8})\b/;
			if (phone == '' || phone == null) {
				errorPhone.innerHTML = "SĐT không được để trống!";
			} else if (!regexPhone.test(phone)) {
				errorPhone.innerHTML = "SĐT không hợp lệ!";
			} else {
				errorPhone.innerHTML = "";
				return phone;
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
				url : '${studentAPI}',
				type : 'PUT',
				contentType : 'application/json',
				data : JSON.stringify(data),
				dataType : 'json',
				success : function(result) {
					window.location.href = "${studentURL}?message=update_success";
				},
				error : function(error) {
					window.location.href = "${studentURL}";
				}
			});
		}
	</script>
</body>

</html>