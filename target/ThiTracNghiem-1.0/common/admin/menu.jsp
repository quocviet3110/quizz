<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<div id="sidebar" class="sidebar  responsive  ace-save-state">
    <script type="text/javascript">
        try{ace.settings.loadState('sidebar')}catch(e){}
    </script>
    <div class="sidebar-shortcuts">
        <div class="sidebar-shortcuts-large">
            <button class="btn btn-success">
                <i class="ace-icon fa fa-signal"></i>
            </button>

            <button class="btn btn-info">
                <i class="ace-icon fa fa-pencil"></i>
            </button>

            <button class="btn btn-warning">
                <i class="ace-icon fa fa-users"></i>
            </button>

            <button class="btn btn-danger">
                <i class="ace-icon fa fa-cogs"></i>
            </button>
        </div>
        <div class="sidebar-shortcuts-mini">
            <span class="btn btn-success"></span>

            <span class="btn btn-info"></span>

            <span class="btn btn-warning"></span>

            <span class="btn btn-danger"></span>
        </div>
    </div>
    <ul class="nav nav-list">
        <li >
            <a href="#" class="dropdown-toggle">
                <i class="menu-icon fa fa-list"></i>
                <span class="menu-text"></span>
                Quản lý câu hỏi
                <b class="arrow fa fa-angle-down"></b>
            </a>
            <b class="arrow"></b>
            <ul class="submenu">
                <li>
                    <a href='<c:url value="/admin/question/list?page=1&limit=5"/>'>
                        <i class="menu-icon fa fa-caret-right"></i>
                        Danh sách câu hỏi
                    </a>
                    <b class="arrow"></b>
                </li>
            </ul>
             <ul class="submenu">
                <li>
                    <a href='<c:url value="/admin/myQuestion/list?page=1&limit=6"/>'>
                        <i class="menu-icon fa fa-caret-right"></i>
                        Câu hỏi cá nhân
                    </a>
                    <b class="arrow"></b>
                </li>
            </ul>
        </li>
    </ul>
     <ul class="nav nav-list">
        <li >
            <a href="#" class="dropdown-toggle">
                <i class="menu-icon fa fa-list"></i>
                <span class="menu-text"></span>
                Quản lý bài thi
                <b class="arrow fa fa-angle-down"></b>
            </a>
            <b class="arrow"></b>
            <ul class="submenu">
                <li>
                    <a href='<c:url value="/admin/examAll/list?page=1&limit=6"/>'>
                        <i class="menu-icon fa fa-caret-right"></i>
                        Danh sách bài thi
                    </a>
                    <b class="arrow"></b>
                </li>
            </ul>
             <ul class="submenu">
                <li>
                    <a href='<c:url value="/admin/exam/list?page=1&limit=6"/>'>
                        <i class="menu-icon fa fa-caret-right"></i>
                        Đề thi cá nhân
                    </a>
                    <b class="arrow"></b>
                </li>
            </ul>
        </li>
    </ul>
    <ul class="nav nav-list">
        <li >
            <a href="#" class="dropdown-toggle">
                <i class="menu-icon fa fa-list"></i>
                <span class="menu-text"></span>
                Quản lý môn học
                <b class="arrow fa fa-angle-down"></b>
            </a>
            <b class="arrow"></b>
            <ul class="submenu">
                <li>
                    <a href='<c:url value="/admin/subject/list?page=1&limit=6"/>'>
                        <i class="menu-icon fa fa-caret-right"></i>
                        Danh sách môn học
                    </a>
                    <b class="arrow"></b>
                </li>
                <li>
                    <a href='<c:url value="/admin/level/list?page=1&limit=6"/>'>
                        <i class="menu-icon fa fa-caret-right"></i>
                        Cấp độ môn học
                    </a>
                    <b class="arrow"></b>
                </li>
 
             </ul>
        </li>
    </ul>
    <ul class="nav nav-list">
        <li >
            <a href="#" class="dropdown-toggle">
                <i class="menu-icon fa fa-list"></i>
                <span class="menu-text"></span>
                Quản lý sinh viên
                <b class="arrow fa fa-angle-down"></b>
            </a>
            <b class="arrow"></b>
            <ul class="submenu">
                <li>
                    <a href='<c:url value="/admin/student/list?page=1&limit=6"/>'>
                        <i class="menu-icon fa fa-caret-right"></i>
                        Danh sách sinh viên
                    </a>
                    <b class="arrow"></b>
                </li>
                <li>
                    <a href='<c:url value="/admin/result/list?page=1&limit=6"/>'>
                        <i class="menu-icon fa fa-caret-right"></i>
                        Danh sách điểm
                    </a>
                    <b class="arrow"></b>
                </li>
             </ul>
        </li>
    </ul>
 
   	 <div class="sidebar-toggle sidebar-collapse">
        <i class="ace-icon fa fa-angle-double-left ace-save-state" data-icon1="ace-icon fa fa-angle-double-left" data-icon2="ace-icon fa fa-angle-double-right"></i>
    </div>
</div>