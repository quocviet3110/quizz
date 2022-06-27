<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
	<div id="content">	<!-- Notify -->
		<div id="notify-toan" class="notify-section">
			<div class="content-section">
				<h2 class="section-heading text-white">TRẮC NGHIỆM ONLINE MÔN ${subject.name}</h2>
				<p class="section-sub-heading text-white">THI THPTQG</p>

				<!-- notify detail -->
				
				<div class="row places-list">
					<c:forEach var="item" items="${exam.listResult}">
					<div class="col col-third mt-16">
						<div class="place-body">
							<h3 class="place-heading">Đề thi môn ${item.nameSubject} năm 2021</h3>
							<p class="place-desc">Bộ GT&ĐT - Mã đề ${item.codeExam}</p>
							<p class="place-time">
								<i class="far fa-calendar-alt"></i> ${item.convertDate} <span
									class="test-number"> <i class="fas fa-user"></i> 276
									lượt thi
								</span>
							</p>
							<div class="process">
								<div class="senNumber">25/50</div>
								<div class="process-bar"></div>
							</div>
							<a href="<c:url value='/web/exam/listQuestion?id=${item.id}'/> " class="btn js-buy-ticket s-full-width">LÀM NGAY</a>
						</div>
					</div>
				</c:forEach>
					
					</div>
				</div>
			</div>
		</div>
</body>
</html>