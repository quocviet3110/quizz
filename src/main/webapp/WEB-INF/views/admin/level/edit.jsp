<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/common/taglib.jsp"%>
<%@ page import="com.thitracnghiem.util.SecurityUtils" %>
<c:url var="levelURL" value="/admin/level/list" />
<c:url var="editLevelURL" value="/admin/level/update" />
<c:url var="levelAPI" value="/api/level" />
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
								<label class="col-sm-3 control-label no-padding-right"
									for="form-field-1">Tên cấp độ:</label>
								<div class="col-sm-3">
									<form:input path="name" cssClass="form-control" id="name" />
								</div>
							</div>
							<form:hidden path="id" id="levelId" />
							<div class="clearfix form-actions">
								<div class="col-md-offset-3 col-md-9">
									<c:if test="${not empty model.id}">
										<button class="btn btn-info" type="button"
											id="btnAddOrUpdate">
											<i class="ace-icon fa fa-check bigger-110"></i> Cập nhật bài
											viết
										</button>
									</c:if>
									<c:if test="${empty model.id}">
										<button class="btn btn-info" type="button"
											id="btnAddOrUpdate">
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
		$('#btnAddOrUpdate').click(function(e) {
			e.preventDefault();
			var data = {};
			var formData = $('#formSubmit').serializeArray();
			console.log(formData);
			$.each(formData, function(i, v) {
				data["" + v.name + ""] = v.value;
			});
			var id = $('#levelId').val();
			if (id == "") {
				addLevel(data);
			} else {
				updateLevel(data);
			}
		});

		function addLevel(data) {
			$.ajax({
				url : '${levelAPI}',
				type : 'POST',
				contentType : 'application/json',
				data : JSON.stringify(data),
				dataType : 'json',
				success : function(result) {
					window.location.href = "${editLevelURL}?id="+ result.id + "&message=insert_success";
				},
				error : function(error) {
					window.location.href = "${levelURL}?page=1&limit=2&message=error_system";
					console.log(lỗi);
				}
			});
		}

		function updateLevel(data) {
			$.ajax({
				url : '${levelAPI}',
				type : 'PUT',
				contentType : 'application/json',
				data : JSON.stringify(data),
				dataType : 'json',
				success : function(result) {
					window.location.href = "${editLevelURL}?id=" + result.id+ "&message=update_success";
				},
				error : function(error) {
					window.location.href = "${editLevelURL}?id=" + result.id+ "&message=error_system";
				}
			});
		}
	</script>
</body>
</html>