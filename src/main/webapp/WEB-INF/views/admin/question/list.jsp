<%@include file="/common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<c:url var="questionAPI" value="/api/question" />
<c:url var="questionURL" value="admin/question/list" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Danh sách bài viết</title>
</head>
<body>
	<div class="main-content">
		<form action="<c:url value='/admin/question/list?page=${model.page}&limit=${model.limit}&keyword=${keyword}'/>"
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
						
							Filter:
								<input type="text" name="keyword" id="keyword" size="50" value="${keyword}" required /> &nbsp;
								 <input type="button" value="Search" id="search" onclick="Search()" /> &nbsp; 
								 <input type="button" value="Clear" id="btnClear" onclick="clearSearch()" />
							 <br/>
							 <br/>
							 <br/>
							<div class="row">
								<div class="col-xs-12">
									<div class="table-responsive">
										<table class="table table-bordered">
											<thead>
												<tr>
													
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
			window.location.href = "/ThiTracNghiem/admin/question/list?page=1&limit=6&keyword="+keyword;
		}
		function clearSearch(){
			window.location.href = "/ThiTracNghiem/admin/question/list?page=1&limit=5";
		}
	</script>
</body>
</html>