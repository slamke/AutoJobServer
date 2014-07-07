<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
	 <title>泰伦智能工作管理系统</title>
	<base href="<%=basePath%>">
 	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <!-- Bootstrap -->
    <link href="<c:url value='/assets/css/bootstrap.min.css'/>" rel="stylesheet" media="screen">
    <link href="<c:url value='/assets/css/base.css'/>" rel="stylesheet" type="text/css" >
    <link href="<c:url value='/assets/css/header.css'/>" rel="stylesheet" type="text/css" >
    <link href="<c:url value='/assets/css/jquery.pagination.css'/>" rel="stylesheet" type="text/css" >
    <link href="<c:url value='/assets/css/talent.css'/>" rel="stylesheet" type="text/css" >
    <link href="<c:url value='/assets/css/jquery-ui-1.10.3.custom.css'/>" rel="stylesheet" type="text/css" >
    <link href="<c:url value='/assets/css/cerabox.css'/>" rel="stylesheet"  media="screen" />
    <link href="<c:url value='/assets/css/select2.css'/>" rel="stylesheet"  media="screen" />
    <link href="<c:url value='/assets/css/flexslider.css'/>" rel="stylesheet"  media="screen" />
    
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="<c:url value='/assets/js/jquery-1.10.2.js'/>"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="<c:url value='/assets/js/bootstrap.min.js'/>"></script>
    <script src="<c:url value='/assets/js/talent.js'/>"></script>
    <script src="<c:url value='/assets/js/jquery.pagination.js'/>"></script>
    <script src="<c:url value='/assets/js/jquery.validate.js'/>"></script>
    <script src="<c:url value='/assets/js/jquery.metadata.js'/>"></script>
    <script src="<c:url value='/assets/js/jquery.cookie.js'/>"></script>
    <script src="<c:url value='/assets/js/jquery.cookie.js'/>"></script>
    <script src="<c:url value='/assets/js/jquery-ui-1.10.3.custom.js'/>"></script>
    <script src="<c:url value='/assets/js/mootools-core-1.4.5.js'/>"></script>
    <script src="<c:url value='/assets/js/mootools-more-1.4.0.1-assets.js'/>"></script>
    <script src="<c:url value='/assets/js/cerabox.min.js'/>"></script>
    <script src="<c:url value='/assets/js/jquery.truncate.js'/>"></script>
    <script src="<c:url value='/assets/js/talent-task.js'/>"></script>
    <script src="<c:url value='/assets/js/talent-utils.js'/>"></script>
    <script src="<c:url value='/assets/js/ajaxfileupload.js'/>"></script>
   	<script src="<c:url value='/assets/js/jquery.flexslider.js'/>"></script>
 <%--    <script src="<c:url value='/assets/js/jquery.mobile-1.4.0.js'/>"></script> --%>
	