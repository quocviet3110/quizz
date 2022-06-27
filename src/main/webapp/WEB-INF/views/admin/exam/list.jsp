<%@include file="/common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<c:url var="examAPI" value="/api/exam" />
<c:url var="examURL" value="/admin/exam/list" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Danh sách bài viết</title>
</head>
<body>
	<div class="main-content">
		<form action="<c:url value='/admin/exam/list?page=${model.page}&limit=${model.limit}&keyword=${keyword}'/>"
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
											<c:url var="createURL" value="/admin/exam/update" />
											<a flag="info"
												class="dt-button buttons-colvis btn btn-white btn-primary btn-bold"
												data-toggle="tooltip" title='Thêm câu hỏi'
												href='${createURL}'> <span> <i
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
							Filter:
								<input type="text" name="keyword" id="keyword" size="50" value="${keyword}" required /> &nbsp;
								 <input type="button" value="Search" id="search" onclick="Search()" /> &nbsp; 
								 <input type="button" value="Clear" id="btnClear" onclick="clearSearch()" />
							 <br/>
							<div class="row">
								<div class="col-xs-12">
									<div class="table-responsive">
										<table class="table table-bordered">
											<thead>
												<tr>
													<th><input type="checkbox" id="checkAll"></th>
													<th>Id</th>
													<th>Ngày tạo</th>
													<th>Thời gian thi</th>
													<th>Số lượng câu hỏi</th>
													<th>Môn học</th>
													<th>Mã đề</th>
													<th>Trạng thái</th>
					
												</tr>
											</thead>
											<tbody>
												<c:forEach var="item" items="${model.listResult}">
													<tr>
														<td><input type="checkbox" id="checkbox_${item.id}"
															value="${item.id}"></td>
														<td>${item.id}</td>
														<td>${item.convertDate}</td>
														<td>${item.time}</td>
														<td>${item.number}</td>
														<td>${item.nameSubject}</td>
														<td>${item.codeExam}</td>
														<c:choose>
   															<c:when test="${item.status == '1'}">
   																<td>Công khai</td>
   															</c:when> 												
   															 <c:otherwise><td>Cá nhân</td></c:otherwise>  
														</c:choose>
														<td><c:url var="updateQuestionURL"
																value="/admin/exam/update">
																<c:param name="id" value="${item.id}" />
															</c:url> <a class="btn btn-sm btn-primary btn-edit" data-toggle="tooltip" title="Cập nhật đề thi" href='${updateQuestionURL}'>
															<i class="fa fa-pencil-square-o" aria-hidden="true"></i> </a></td>
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
				visiblePages : 5,
				startPage : currentPage,
				onPageClick : function(event, page) {
					if (currentPage != page) {
						$('#limit').val(5);
						$('#page').val(page);
						$('#formSubmit').submit();
					}
				}
			});
		});
		function Search() {
			var keyword = document.getElementById('keyword').value;
			window.location.href = "/ThiTracNghiem/admin/exam/list?page=1&limit=6&keyword="+keyword;
		}
		function clearSearch(){
			window.location.href = "/ThiTracNghiem/admin/exam/list?page=1&limit=6";
		}
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
					deleteQuestion(ids);
			  }
			});
		}  
		function deleteQuestion(data) {
			$.ajax({
			url : '${examAPI}',
			type : 'DELETE',
			contentType : 'application/json',
			data : JSON.stringify(data),
			success : function(result) {
				window.location.href = "${examURL}?page=1&limit=6&message=delete_success";
			},
			error : function(error) {
				window.location.href = "${questionURL}?page=1&limit=6&message=error_system";
			}
			});
		}
	</script>
</body>
</html>