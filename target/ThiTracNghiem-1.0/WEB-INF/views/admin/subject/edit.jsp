<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/common/taglib.jsp"%>
<%@ page import="com.thitracnghiem.util.SecurityUtils" %>
<c:url var="subjectURL" value="/admin/subject/list" />
<c:url var="editSubjectURL" value="/admin/subject/update" />
<c:url var="subjectAPI" value="/api/subject" />
<html>
<head>
<title>Chỉnh sửa môn học</title>
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
									class="col-sm-3 control-label no-padding-right">Cấp độ</label>
								<div class="col-sm-9">
									<form:select path="level" id="level">
										<form:option value="" label="-- Chọn Cấp Độ --" />
											   <form:options items="${levels}" itemValue="name" itemLabel="name" />
									</form:select>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right"
									for="form-field-1">Tên môn học:</label>
								<div class="col-sm-3">
									<form:input path="name" cssClass="form-control" id="name" />
								</div>
							</div>
							<form:hidden path="id" id="subjectId" />
							<div class="clearfix form-actions">
								<div class="col-md-offset-3 col-md-9">
									<c:if test="${not empty model.id}">
										<button class="btn btn-info" type="button"
											id="btnAddOrUpdateSubject">
											<i class="ace-icon fa fa-check bigger-110"></i> Cập nhật bài
											viết
										</button>
									</c:if>
									<c:if test="${empty model.id}">
										<button class="btn btn-info" type="button"
											id="btnAddOrUpdateSubject">
											<i class="ace-icon fa fa-check bigger-110"></i> Thêm bài viết
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
		/*  var editor = '';
		$(document).ready(function() {
			editor = CKEDITOR.replace('ques_content');
		}); */
		$('#btnAddOrUpdateSubject').click(function(e) {
			e.preventDefault();
			var data = {};
			var formData = $('#formSubmit').serializeArray();
			console.log(formData);
			$.each(formData, function(i, v) {
				data["" + v.name + ""] = v.value;
			});
			var id = $('#subjectId').val();
			if (id == "") {
				addSubject(data);
			} else {
				updateSubject(data);
			}
		});

		function addSubject(data) {
			$.ajax({
				url : '${subjectAPI}',
				type : 'POST',
				contentType : 'application/json',
				data : JSON.stringify(data),
				dataType : 'json',
				success : function(result) {
					window.location.href = "${editSubjectURL}?id="+ result.id + "&message=insert_success";
				},
				error : function(error) {
					window.location.href = "${subjectURL}?page=1&limit=2&message=error_system";
					console.log(lỗi);
				}
			});
		}

		function updateSubject(data) {
			$.ajax({
				url : '${subjectAPI}',
				type : 'PUT',
				contentType : 'application/json',
				data : JSON.stringify(data),
				dataType : 'json',
				success : function(result) {
					window.location.href = "${editSubjectURL}?id=" + result.id+ "&message=update_success";
				},
				error : function(error) {
					window.location.href = "${editSubjectURL}?id=" + result.id+ "&message=error_system";
				}
			});
		}
	</script>
</body>
</html>