<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
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
<title>下班填单详细信息</title>
<script>
$(function(){$("#brief").show();
$("#improvement").hide();
$("#problem").hide();} );
function showBrief(){
	$("#brief").show();
	$("#improvement").hide();
	$("#problem").hide();
}
function showImprovement(){
	$("#brief").hide();
	$("#improvement").show();
	$("#problem").hide();
}
function showProblem(){
	$("#brief").hide();
	$("#improvement").hide();
	$("#problem").show();
}
</script>
</head>
<body>

	<center>
		<h1 class="ui-bar ui-bar-a">下班填单详细信息</h1>
	</center>
	<div class="ui-body">
		<p><font size="5">项目工地简称：${siteName }</font>
		</p>

			<p><font size="5">用户名：${userName }
		</font>
		</p>

			<p><font size="5">记录类型：下班填单
		</font>
		</p>

			<p><font size="5">
	填报日期：${date }
		</font>
		</p></div>


	<!-- <div data-role="header" data-theme="e">
		<h1></h1>
	</div>
	<div class="ui-body ui-body-a ui-corner-all">
		<h3>项目工地简称：南京众彩</h3>
		
	</div> -->
	<div data-role="tabs" id="tabs">
		<div data-role="navbar">
			<ul>
				<li><a href="brief" data-ajax="false" data-theme="e"
					class="ui-btn-active ui-state-persist" onclick="showBrief()"><font size="5">情况简述</font></a></li>
				<li><a href="improvement" data-ajax="false" data-theme="e" onclick="showImprovement()"><font size="5">改进意见</font></a></li>
				<li><a href="problem" data-ajax="false" data-theme="e" onclick="showProblem()"><font size="5">问题描述</font></a></li>
			</ul> 
		</div>
		<div id="brief" class="ui-body-d ui-content">
		<p><font size="4"><c:out value="${briefInfo}"/></font></p>
			<c:choose>
				<c:when test="${!empty briefList}">
					<c:forEach var="photo" items="${briefList}" varStatus="status">
						<c:if test="${(status.index%3)==0}">
							<hr/>
						</c:if>
						<a href="#popupbrief"+${status.index} data-rel="popup" data-position-to="window" data-transition="fade">
							<img class="popphoto"	src="<c:url value='${photo}'/>" style="width: 30%;height:300px"></a>
					<div data-role="popup" id="popupbrief"+${status.index} data-overlay-theme="b"
						data-theme="b" data-corners="false">
					<a href="javascript:void(0)" data-rel="back"
						class="ui-btn ui-corner-all ui-shadow ui-btn-a ui-icon-delete ui-btn-icon-notext ui-btn-right">Close</a><img
						class="popphoto" src="<c:url value='${photo}'/>"
						style="max-height: 450px;" >
						<p><c:out value="${briefDesList.get(status.index)}"/></p>
					</div>
					</c:forEach>
				</c:when>
			</c:choose>
		</div>
		<div id="improvement">
		<p><font size="4"><c:out value="${improveInfo}"/></font></p>
			<c:choose>
				<c:when test="${!empty improveList}">
					<c:forEach var="photo" items="${improveList}" varStatus="status">
						<c:if test="${(status.index%3)==0}">
							<hr/>
						</c:if>
						<a href="#popupimprovement"+${status.index} data-rel="popup" data-position-to="window" data-transition="fade">
							<img class="popphoto"	src="<c:url value='${photo}'/>" style="width: 30%"></a>
					<div data-role="popup" id="popupimprovement"+${status.index} data-overlay-theme="b"
						data-theme="b" data-corners="false">
					<a href="javascript:void(0)" data-rel="back"
						class="ui-btn ui-corner-all ui-shadow ui-btn-a ui-icon-delete ui-btn-icon-notext ui-btn-right">Close</a><img
						class="popphoto" src="<c:url value='${photo}'/>"
						style="max-height: 450px;" >
						<p><c:out value="${improveDesList.get(status.index)}"/></p>
				</div>
					</c:forEach>
				</c:when>
			</c:choose>
		</div>
		<div id="problem">
		<p><font size="4"><c:out value="${problemInfo}"/></font></p>
			<c:choose>
				<c:when test="${!empty problemList}">
					<c:forEach var="photo" items="${problemList}" varStatus="status">
						<c:if test="${(status.index%3)==0}">
							<hr/>
						</c:if>
						<a href="#popupproblem"+${status.index} data-rel="popup" data-position-to="window" data-transition="fade">
							<img class="popphoto"	src="<c:url value='${photo}'/>" style="width: 30%"></a>
					<div data-role="popup" id="popupproblem"+${status.index} data-overlay-theme="b"
						data-theme="b" data-corners="false">
					<a href="javascript:void(0)" data-rel="back"
						class="ui-btn ui-corner-all ui-shadow ui-btn-a ui-icon-delete ui-btn-icon-notext ui-btn-right">Close</a><img
						class="popphoto" src="<c:url value='${photo}'/>"
						style="max-height: 450px;">
						<p><c:out value="${problemDesList.get(status.index)}"/></p>
				</div>
					</c:forEach>
				</c:when>
			</c:choose>
		</div>
	</div>
</body>
</html>