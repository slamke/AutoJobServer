<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- JSTL prefix -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>    
               
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet"
	href="<c:url value='/assets/css/jquery.mobile-1.4.0.min.css'/>" />
<script src="<c:url value='/assets/js/jquery-1.10.2.js'/>"></script>
<script src="<c:url value='/assets/js/jquery.mobile-1.4.0.js'/>"></script>
<title>上班签到详细信息</title>
</head>
<body>

<center><h1 class="ui-bar ui-bar-a">上班签到详细信息</h1></center>
<div class="ui-grid-a">
    <div class="ui-block-a"><div>
<a href="#popupCheckIn" data-rel="popup" data-position-to="window" data-transition="fade">
<center>
				<img class="popphoto"	src="<c:url value='${photoBeforeWork}'/>" style="width: 300px;height:200px"></center></a>
				<div data-role="popup" id="popupCheckIn" data-overlay-theme="b"
				data-theme="b" data-corners="false">
				<a href="#" data-rel="back"
					class="ui-btn ui-corner-all ui-shadow ui-btn-a ui-icon-delete ui-btn-icon-notext ui-btn-right">Close</a><img
					class="popphoto" src="<c:url value='${photoBeforeWork}'/>"
					style="max-height: 450px;" >
				<p><c:out value="${photoBeforeWorkDes}"/></p>
			</div>
</div></div>
    <div class="ui-block-b"><div><div class="ui-body">
<p><font size="5">项目工地简称：${siteName}</font>
		</p>
			<p><font size="5">用户名：${userName}
		</font>
		</p>
			<p><font size="5">记录类型：上班签到
		</font>
		</p>
			<p><font size="5">
	填报日期：${date}
		</font>
		</p>          
</div></div></div>
</div><!-- /grid-a -->
      
      

<div class="ui-corner-all custom-corners">
  <div class="ui-bar ui-bar-a">
    <font size="4">开工前照片</font>
  </div>
  <div class="ui-body ui-body-a">
  		<c:choose>
			<c:when test="${!empty photoList}">
				<c:forEach var="photo" items="${photoList}" varStatus="status">
					<c:if test="${(status.index%3)==0}">
						<hr/>
					</c:if>
					<a href="#popup"+${status.index} data-rel="popup" data-position-to="window" data-transition="fade">
						<img class="popphoto"	src="<c:url value='${photo}'/>" style="width: 30%"></a>
				<div data-role="popup" id="popup"+${status.index} data-overlay-theme="b"
					data-theme="b" data-corners="false">
				<a href="#" data-rel="back"
					class="ui-btn ui-corner-all ui-shadow ui-btn-a ui-icon-delete ui-btn-icon-notext ui-btn-right">Close</a><img
					class="popphoto" src="<c:url value='${photo}'/>"
					style="max-height: 450px;" >
					<p><c:out value="${photoDesList.get(status.index)}"/></p>
			</div>
				</c:forEach>
			</c:when>
		</c:choose>
  </div>
</div>
</body>
</html>