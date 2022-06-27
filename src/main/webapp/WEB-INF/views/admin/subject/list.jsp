<%@include file="/common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<c:url var="subjectAPI" value="/api/subject" />
<c:url var="subjectURL" value="/admin/subject/list" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Danh sách môn học</title>
</head>

<body>
	<div class="main-content">
		<form action="<c:url value='/admin/subject/list?page=${model.page}&limit=${model.limit}'/>"
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
										<div class="dt-buttons btn-overlap btn-group">
											<c:url var="createNewURL" value="/admin/subject/update" />
											<a flag="info"
												class="dt-button buttons-colvis btn btn-white btn-primary btn-bold"
												data-toggle="tooltip" title='Thêm môn học'
												href='${createNewURL}'> <span> <i
													class="fa fa-plus-circle bigger-110 purple"></i>
											</span>
											</a>
											<button id="btnDelete" type="button" onclick="warningBeforeDelete()"
												class="dt-button buttons-html5 btn btn-white btn-primary btn-bold"
												data-toggle="tooltip" title='Xóa câu hỏi'>
												<span> <i class="fa fa-trash-o bigger-110 pink"></i>
												</span>
											</button>
										</div>
									</div>
								</div>
							</div>
							<%-- Filter:
								<input type="text" name="keyword" id="keyword" size="50" th:value="${keyword}" required /> &nbsp;
								 <input type="submit" value="Search" id="formSubmit" /> &nbsp; 
								 <input type="button" value="Clear" id="btnClear" onclick="clearSearch()" /> --%>
							<div class="row">
								<div class="col-xs-12">
									<div class="table-responsive">
										<table class="table table-bordered">
											<thead>
												<tr>
													<th><input type="checkbox" id="checkAll"></th>
													<th>Id</th>
													<th>Môn học</th>
													<th>Cấp độ</th>
													<th></th>
												</tr>
											</thead>
											<tbody>
												<c:forEach var="item" items="${model.listResult}">
													<tr>
														<td><input type="checkbox" id="checkbox_${item.id}"
															value="${item.id}"></td>
														<td>${item.id}</td>
														<td>${item.name}</td>
														<td>${item.level}</td>
												
														<td><c:url var="updateNewURL"
																value="/admin/subject/update">
																<c:param name="id" value="${item.id}" />
															</c:url> <a class="btn btn-sm btn-primary btn-edit"
															data-toggle="tooltip" title="Cập nhật môn học"
															href='${updateNewURL}'><i
																class="fa fa-pencil-square-o" aria-hidden="true"></i> </a></td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
										<ul class="pagination" id="pagination"></ul>
										<input type="hidden" value="" id="page" name="page" /> 
										<input type="hidden" value="" id="limit" name="limit" />
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
		var totalPages = ${model.totalPage};
		var currentPage = ${model.page};
		$(function() {
			window.pagObj = $('#pagination').twbsPagination({
				totalPages : totalPages,
				visiblePages : 10,
				startPage : currentPage,
				onPageClick : function(event, page) {
					if (currentPage != page) {
						$('#limit').val(6);
						$('#page').val(page);
						$('#formSubmit').submit();
					}
				}
			});
		});
		function warningBeforeDelete() {
			swal({
			  title: "Xác nhận xóa",
			  text: "Bạn có chắc chắn muốn xóa hay không",
			  type: "warning",
			  showCancelButton: true,
			  confirmButtonClass: "btn-success",
			  cancelButtonClass: "btn-danger",
			  confirmButtonText: "Xác nhận",
			  cancelButtonText: "Hủy bỏ",
			},
			function(isConfirm) {
			  if (isConfirm) {
					var ids = $('tbody input[type=checkbox]:checked').map(function () {
			            return $(this).val();
			        }).get();
					deleteSubject(ids);
			  }
			});
		}  
		function deleteSubject(data) {
			$.ajax(
			{
				url : '${subjectAPI}',
				type : 'DELETE',
				contentType : 'application/json',
				data : JSON.stringify(data),
				success : function(result) {
					window.location.href = "${subjectURL}?page=1&limit=6&message=delete_success";
				},
				error : function(error) {
					window.location.href = "${subjectURL}?page=1&limit=6&message=error_system";
				}
			});
		}
	</script>
</body>
</html>