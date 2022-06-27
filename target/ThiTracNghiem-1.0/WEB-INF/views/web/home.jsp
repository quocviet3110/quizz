<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Trang chủ</title>
</head>
<body>
	<div id="content">
		<div id="notify" class="notify-section">
			<div class="content-section">
				<h2 class="section-heading text-white">TRẮC NGHIỆM ONLINE</h2>
				<p class="section-sub-heading text-white">ĐA DẠNG - THÔNG MINH -
					CHÍNH XÁC</p>

				<!-- Places -->
				<div class="row places-list">
					<div class="col col-third mt-16">
						<img src="<c:url value='/template/webb/assets/img/box1.jpg'/> "
							alt="New York" class="place-img">
						<div class="place-body">
							<h3 class="place-heading">ĐỀ THI HỌC KỲ</h3>
							<p class="place-time">Fri 27 Nov 2016</p>
							<p class="place-desc">Ngân hàng câu hỏi đầy đủ các môn cấp
								1,2,3 được trộn để theo cấu trúc phân loại giúp các em dễ dàng
								ôn tập.</p>
							<button class="btn js-buy-ticket s-full-width">LÀM NGAY</button>
						</div>
					</div>

					<div class="col col-third mt-16">
						<img src="<c:url value='/template/webb/assets/img/box2.jpg'/> "
							alt="Paris" class="place-img">
						<div class="place-body">
							<h3 class="place-heading">ĐỀ THI THPT QG</h3>
							<p class="place-time">Sat 28 Nov 2016</p>
							<p class="place-desc">Tổng hợp đề thi trắc nghiệm online THPT
								QG của các môn Toán, Tiếng Anh, Vật Lý, Hoá Học, Sinh Học, Địa
								Lý.</p>
							<button class="btn js-buy-ticket s-full-width">LÀM NGAY</button>
						</div>
					</div>

					<div class="col col-third mt-16">
						<img src="<c:url value='/template/webb/assets/img/box3.jpg'/> "
							alt="San Francisco" class="place-img">
						<div class="place-body">
							<h3 class="place-heading">ĐỀ KIỂM TRA</h3>
							<p class="place-time">Sun 29 Nov 2016</p>
							<p class="place-desc">Hệ thống bài kiểm tra 1 tiết, kiểm tra
								15 phút được thiết kế theo hình thức trắc nghiệm online.</p>
							<button class="btn js-buy-ticket s-full-width">LÀM NGAY</button>
						</div>
					</div>
				</div>
			</div>
		</div>

		<!-- English -->
		<div id="notify" class="eng-section">
			<div class="content-section">

				<!-- Places -->
				<div class="row places-list">
					<div class="col col-half mt-128">
						<div class="place-body">
							<h3 class="place-heading">TRẮC NGHIỆM TIẾNG ANH</h3>
							<p class="place-desc">Ngân hàng câu hỏi trắc nghiệm online
								đầy đủ các môn học đại cương, môn học chuyên ngành giúp các bạn
								Sinh Viên ôn thi kết thúc học phần/tín chỉ ở trường DH-CĐ đạt
								kết quả tốt.</p>
							<button class="btn js-buy-ticket s-full-width">LÀM NGAY</button>
						</div>
					</div>

					<div class="col col-half mt-16">
						<img src="<c:url value='/template/webb/assets/img/english.jpg'/> "
							alt="Paris" class="place-img" style="height: 400px;">
					</div>
				</div>
			</div>
		</div>

		<!-- IT -->
		<div id="notify" class="it-section">
			<div class="content-section">

				<!-- Places -->
				<div class="row places-list">


					<div class="col col-half mt-16">
						<img src="<c:url value='/template/webb/assets/img/it.jpg'/> " alt="Paris"
							class="place-img" style="height: 400px;">
					</div>
					<div class="col col-half mt-128">
						<div class="place-body">
							<h3 class="place-heading">TRẮC NGHIỆM IT</h3>
							<p class="place-desc">Hệ thống các bài trắc nghiệm online về
								tin học văn phòng, tin học cơ bản, lập trình, quản trị hệ thống,
								chứng chỉ quốc tế giúp các bạn ôn luyện và thi các chứng chỉ về
								CNTT.</p>
							<button class="btn js-buy-ticket s-full-width">LÀM NGAY</button>
						</div>
					</div>
				</div>
			</div>
		</div>

		<!-- Đinh hướng -->
		<div id="notify" class="guide-section">
			<div class="content-section">
				<h2 class="section-heading text-white">ĐỊNH HƯỚNG TƯƠNG LAI</h2>

				<!-- Places -->
				<div class="row places-list">
					<div class="col col-half mt-16">
						<img src="<c:url value='/template/webb/assets/img/daihoc.jpg'/> "
							alt="New York" class="place-img">
						<div class="place-body">
							<h3 class="place-heading">ĐẠI HỌC</h3>
							<p class="place-time">Fri 27 Nov 2016</p>
							<p class="place-desc">Ngân hàng câu hỏi trắc nghiệm online
								đầy đủ các môn học đại cương, môn học chuyên ngành giúp các bạn
								Sinh Viên ôn thi kết thúc học phần/tín chỉ ở trường DH-CĐ đạt
								kết quả tốt.</p>
							<button class="btn js-buy-ticket s-full-width">LÀM NGAY</button>
						</div>
					</div>

					<div class="col col-half mt-16">
						<img src="<c:url value='/template/webb/assets/img/huongnghiep.jpg'/> "
							alt="Paris" class="place-img">
						<div class="place-body">
							<h3 class="place-heading">HƯỚNG NGHIỆP</h3>
							<p class="place-time">Sat 28 Nov 2016</p>
							<p class="place-desc">Hệ thống ngân hàng câu hỏi và các bài
								thi trắc nghiệp về nghề nghiệp như ôn thi bằng lái xe, trắc
								nghiệm công viên chức, trắc nghiệm tính cách, MBTI TEST, IQ/EQ
								TEST.</p>
							<button class="btn js-buy-ticket s-full-width">LÀM NGAY</button>
						</div>
					</div>
				</div>
			</div>
		</div>

		<!-- Tài liệu tham khảo -->
		
	</div>

</body>
</html>