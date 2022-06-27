<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@include file="/common/taglib.jsp" %>
  <%@ page import="com.thitracnghiem.util.SecurityUtils" %> 
<div id="header">
            <div class="grid">
                <!-- Begin nav -->
                <ul id="nav">
                    <li><a href="<c:url value='/trang-chu'/>" class="title-logo">
                            <i class="ti-pencil-alt logo-icon"></i>
                            ThiTracNghiem</a>
                     </li>
                     <c:forEach var="item" items="${menu}">
                     	<li>
                     		 <a>Thi ${item.name}</a>
                     		 <ul class="subnav">
                     		 <c:forEach var="sub" items="${item.listSub}">
                     		 	<li><a href="<c:url value='/web/listExam?id=${sub.id}'/>">Đề thi ${sub.name}</a></li>
                     		 </c:forEach>
                     		 </ul>
                     	</li>
                     </c:forEach>
                     
                    <li> 
                        <a href="">Tài liệu</a>
                        <ul class="subnav">
                            <li><a href="https://www.elib.vn/hoc-tap/bai-hoc/" target="_blank">Bài học online</a></li>
                            <li><a href="https://www.elib.vn/tai-lieu/tag/luan-van-tot-nghiep.html" target="_blank">Luận
                                    văn tốt nghiệp</a></li>
                        </ul>
                    </li>
                    
                   <security:authorize access="isAnonymous()">
            		<li>
                        <a href="<c:url value='/dang-nhap'/>" class="login js-login login--border">Đăng nhập <i class="far fa-user"></i></a>
                    </li>
            	</security:authorize>
            	<security:authorize access="isAuthenticated()">
              	  <li > 
                    <a href="#" id="nameAfterLogin"><%=SecurityUtils.getPrincipal().getFullname()%> <i class="nav-arrow-down ti-angle-down"></i></a>
                    <ul class="subnav">
                        <li><a href='<c:url value="/web/infor"/>'>Thông tin cá nhân</a></li>
                        <li><a href="#">Đổi mật khẩu</a></li>
                        <li><a href='<c:url value="/admin/thoat"/>'>Đăng xuất</a></li>
                    </ul>
                </li> 
                   </security:authorize>
 
                    
                    <!-- <li>
                    <button class="register">Đăng ký</button>
                </li> -->
                </ul>
            </div>

        </div>